# CRServer
使用 Spring + SpringMVC + MyBatis + Maven 构建一个简单的客户管理系统


#一、7 月 11 号第一次更新 (v1.0)
###项目使用的框架

1.Spring 业务层框架

2.SpringMVC 前端框架

3.Mybatis 持久层框架

4.Maven 构建工具

### 主要功能
1. 添加客户：添加客户的信息，主要包括客户姓名，客户性别，客户电话邮箱以及备注等
2. 查询客户：可以对查询出来客户以列表的形式进行展示，同时可以对查询出来的客户进行编辑操作，除了客户 ID 不可以修改外，其他的所有信息都是可以修改的
3. 删除客户：删除客户，初始版本是从数据库中删除。在后面的版本中将会修正此方式，此方式有一定的弊端。

**切记：数据不能从数据库真正删除，这是开发人员的一个大忌。**


### 项目演示图
![这里写图片描述](http://img.blog.csdn.net/20170711162241589?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbHF3X3N0dWRlbnQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170711162336435?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbHF3X3N0dWRlbnQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170711162356735?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbHF3X3N0dWRlbnQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170711162420158?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbHF3X3N0dWRlbnQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 搭建环境
主要使用工具：
1. intellij
2. MySQL

我拒绝使用一个已经过时的经典 IDE，不可否认 Eclipse 是一个英雄，但是只是曾经，现在的 Inteillj 比他好太多了。这个谁用谁知道，刚开始上手可能有点懵，用习惯了你就会爱上 Intellij，相信我，没错。



#二、7 月 11 号 第二次更新 (v1.1)

### 更新功能

1. 地址添加 restful 支持，所有的访问请求都能够支持 restful。
2. 新增条件查询页面，同时支持多条件组合查询。

### 相关截图展示
![这里写图片描述](http://img.blog.csdn.net/20170711162455703?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbHF3X3N0dWRlbnQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170711162521805?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbHF3X3N0dWRlbnQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170711162551059?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbHF3X3N0dWRlbnQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


#版本前瞻
下个版本将修正数据库真实删除数据的问题，以及添加登录功能。