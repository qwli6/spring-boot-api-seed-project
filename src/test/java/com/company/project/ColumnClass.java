package com.company.project;

/**
 * 描述:
 * 数据库字段封装类
 *
 * @author liqiwen
 * @date 2018-04-02 09:48
 */
public class ColumnClass {

    //数据库字段名称 phone_no
    private String columnName;
    //数据库字段类型 varchar  --> 实际存储 String
    private String columnType;
    //数据库字段注释  --> 注释部分
    private String columnComment;
    //数据库字段小写并转驼峰命名 --> phoneNo
    private String columnJavaName;

    public String getColumnName() {
        return columnName;
    }

    public ColumnClass setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public String getColumnType() {
        return columnType;
    }

    public ColumnClass setColumnType(String columnType) {
        this.columnType = columnType;
        return this;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public ColumnClass setColumnComment(String columnComment) {
        this.columnComment = columnComment;
        return this;
    }

    public String getColumnJavaName() {
        return columnJavaName;
    }

    public ColumnClass setColumnJavaName(String columnJavaName) {
        this.columnJavaName = columnJavaName;
        return this;
    }

    @Override
    public String toString() {
        return "ColumnClass{" +
                "columnName='" + columnName + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", columnJavaName='" + columnJavaName + '\'' +
                '}';
    }
}
