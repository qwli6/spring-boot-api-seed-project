# spring-boot-api-seed-project
我做的项目不多，从最原始的 `ssh` 整合到 `ssm` 整合，经历了太多找 `jar` 包，找配置文件的过程。这个过程是十分痛苦的，后来有了 `gradle` 和 `maven` 对 `jar` 包进行依赖管理，可是还是需要自己去找一些配置文件对框架进行整合。

再后来出现了 `springboot`，这个简直就是开发者的福音。让开发者不用过多的去关注配置，更多的是关心业务的实现。可是即便这样，还是有很多步骤是
比较基本的，但是做多了又感觉比较浪费时间。比如单表的增删改查，分页等等。虽然这些可以自己手动去撸，如果表少的话还可以，如果一旦表变多了，简直就是噩梦。

因为基本的操作太多，并且有太多一致性，遂有了一个用 `springboot` 做一个种子项目的想法。我知道 `github` 上有很多人都写了自己的种子项目，可是我还是想自己
动手试试，毕竟会拷贝并不代表你会，自己会写才是真的会。希望达到的目的就是以后来一个项目可以省略搭建框架，写单表增删改查的方法，然后复杂的业务在该项目上继续迭代即可。

整合关系到哪些框架：`springboot`，`mybatis`，`pagehelper`，`通用 mapper`

> 其实刚开始本来不打算用 `pagehelper` 和 `通用 mapper` 的，想自己手撸，但是太麻烦了。所以最后还是使用了这两个比较流行的框架。（ps：这两个是个人开发者做的，其实挺好用的。）
最后还顺口说句吧，如果有好的三方插件，可以考虑用一下，毕竟可以节省很多的时间，当然如果想练手的话，可以考虑自己撸，没啥问题。

### 思路
刚开始没有想用 `mybatis` 的 `逆向工程` 去生成代码，也是想手撸，后来手撸试了后，只能实现基本的功能，比如分页条件查询就无法实现（可能是我的能力不够），后来想想，还是用 `逆向工程` 吧，
`mapper.xml` 和 `model` 让它生成，`service` 和 `controller` 我自己手撸，这样就比较合适了。

说说自己是怎么生成 `service` 和 `controller` 的吧，其实也不难。主要就是利用 `jdbc` 去获取数据库中的表的字段以及字段属性，然后用 `freemarker` 模板引擎生成需要的代码即可。代码在下面：

```java

 private static List<ColumnClass> getColumnNameAndType(String tableName) throws Exception{
        List<ColumnClass> columnClassList = new ArrayList<>();
        Connection connection = getConnection();
        //给执行的 sql 中表名加上 `表名`
        //不加有些表会报错，比如 order 表
        // select * from order;  这样写执行可能会报错
        String sql = ProjectConstants.EXECUTABLE_SQL + "`" + tableName + "`";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSetMetaData metaData = statement.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            ColumnClass columnClass = new ColumnClass();
            String columnType = metaData.getColumnTypeName(i + 1);
            columnClass.setColumnType(mysqlColumnType2JavaType(columnType));
            String columnJavaName = metaData.getColumnName(i + 1);
            columnClass.setColumnJavaName(columnJavaName);
            String columnName = metaData.getColumnName(i + 1);
            columnClass.setColumnName(columnName);
            columnClassList.add(columnClass);
        }
        return columnClassList;
    }

```

下面是自己手动撸的一个 `mysql` 列类型和 `java` 类型相互转换的方法，主要是给生成实体类和 `xml` 用的，后来用了逆向工程，所以这个也没啥用了。
```java

    /**
     * mysql 列类型转 java 类型
     * @param columnType 列类型
     * @return java 类型
     * jdk1.8 以上 switch 才支持字符串，如果 jdk 版本小于 1.8，请自行更改成 if else 的方式
     **/
    private static String mysqlColumnType2JavaType(String columnType){
        String javaType;
        switch (columnType){
            case ProjectConstants.MYSQL_TYPE_VARCHAR:
            case ProjectConstants.MYSQL_TYPE_CHAR:
                javaType = ProjectConstants.JAVA_TYPE_STRING;
                break;
            case ProjectConstants.MYSQL_TYPE_INTEGER:
            case ProjectConstants.MYSQL_TYPE_INT:
            case ProjectConstants.MYSQL_TYPE_SMALLINT:
                javaType = ProjectConstants.JAVA_TYPE_INTEGER;
                break;
            case ProjectConstants.MYSQL_TYPE_FLOAT:
                javaType = ProjectConstants.JAVA_TYPE_FLOAT;
                break;
            case ProjectConstants.MYSQL_TYPE_DOUBLE:
                javaType = ProjectConstants.JAVA_TYPE_DOUBLE;
                break;
            case ProjectConstants.MYSQL_TYPE_DATE:
            case ProjectConstants.MYSQL_TYPE_TIME:
            case ProjectConstants.MYSQL_TYPE_TIMESTAMP:
            case ProjectConstants.MYSQL_TYPE_DATETIME:
                javaType = ProjectConstants.JAVA_TYPE_DATE;
                break;
            case ProjectConstants.MYSQL_TYPE_TINYINT:
            case ProjectConstants.MYSQL_TYPE_BOOL:
            case ProjectConstants.MYSQL_TYPE_BOOLEAN:
                javaType = ProjectConstants.JAVA_TYPE_SHORT;
                break;
            case ProjectConstants.MYSQL_TYPE_BIGINT:
            case ProjectConstants.MYSQL_TYPE_MEDIUMINT:
                javaType = ProjectConstants.JAVA_TYPE_LONG;
                break;
                default:
                    javaType = ProjectConstants.JAVA_TYPE_STRING;
                    break;
        }
        return javaType;
    }

```

生成 `service` 和 `controller` 的话，主要是用了 `freemarker` 模板。如果对 `freemarker` 不够了解的话，建议先去看看 `freemarker` 相关的文档。我这里提供了一个 `freemarker` 中文版的文档，有需要的可以自己下载下来看看。

```java

private void generateFileByTemplate(String templateName,String packageSuffix, String classSuffix, Map<String, Object> dataMap) throws Exception {
        Template template = FreemarkerUtils.loadTemplate(templateName);
        dataMap.put(ProjectConstants.AUTHOR_KEY, AUTHOR_VALUE);
        dataMap.put(ProjectConstants.EMAIL_KEY, EMAIL_VALUE);
        dataMap.put(ProjectConstants.COMPANY_KEY, COMPANY_VALUE);
        dataMap.put(ProjectConstants.BASE_PACKAGE_KEY, BASE_TARGET_PACKAGE);
        String path =PathUtils.packageNameToPath(dataMap.get(ProjectConstants.BASE_PACKAGE_KEY) + packageSuffix);
        File file;
        if(classSuffix.equals(ProjectConstants.MAPPER_XML_FILE_SUFFIX)) {
            file = new File(ProjectConstants.PROJECT_PATH + ProjectConstants.RESOURCES_PATH + packageSuffix);
        }else {
            file = new File(ProjectConstants.PROJECT_PATH + ProjectConstants.JAVA_PATH + path);
        }
        if(!file.exists()){
            file.mkdirs();
        }else{
            file.delete();
            file.mkdirs();
        }
        Writer out = new FileWriter(new File(file, dataMap.get(ProjectConstants.CLASS_NAME) + classSuffix));
        template.process(dataMap, out);
        out.flush();
    }

```

以上就是该种子项目的代码生成过程。