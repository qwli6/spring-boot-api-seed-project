package com.itqiwen.blog.domain;

import javax.persistence.*;

/**
 * 日志实体类
 */
@Table(name = "s_logs")
@Entity
public class Log {

    @Id
    @GeneratedValue
    private Integer id; //日志id
    private String action; //日志动作，比如删除文章，发布文章，访问后台等等

    @Column(name = "create_dt")
    private Integer createDt; //日志的操作时间
    private String username;//操作人账号
    private String nickname;//操作人昵称

    @Column(name = "ip_address")
    private String ipAddress;//操作的ip地址



    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Integer createDt) {
        this.createDt = createDt;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", createDt=" + createDt +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
