package com.itqiwen.blog.domain;

import javax.persistence.*;

/**
 * 日志实体类
 */
@Table(name = "sblog_logs",catalog = "simple_blog")
@Entity
public class Logs {

    private Integer id; //日志id
    private String action; //日志动作，比如删除文章，发布文章，访问后台等等
    private Integer createDt; //日志的操作时间
    private String username;//操作人账号
    private String nickname;//操作人昵称
    private String ipAddress;//操作的ip地址


    @Column(name = "ip_address")
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "username", length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "nickname", length = 45)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "action",length = 45)
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Column(name = "create_dt")
    public Integer getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Integer createDt) {
        this.createDt = createDt;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", createDt=" + createDt +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
