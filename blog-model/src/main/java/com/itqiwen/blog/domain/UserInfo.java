package com.itqiwen.blog.domain;

import javax.persistence.*;

/**
 * 用户其他的信息
 */
@Entity
@Table(name = "s_user_info")
public class UserInfo {

    @Id
    @GeneratedValue
    private Integer uiid;//用户信息表 id

    private String name; //用户的真实姓名
    private String sex; //用户的性别
    private String address; //用户的地址
    private Integer age; //用户的年龄
    @Column(name = "company_name")
    private String companyName;//用户公司名称
    @Column(name ="company_address")
    private String companyAddress;//用户公司地址

    private String email;
    private String qq;
    private String wxchat;
    private String phone;

    private String uid;//参考用户表中的主键字段

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getUiid() {
        return uiid;
    }

    public void setUiid(Integer uiid) {
        this.uiid = uiid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWxchat() {
        return wxchat;
    }

    public void setWxchat(String wxchat) {
        this.wxchat = wxchat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", wxchat='" + wxchat + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
