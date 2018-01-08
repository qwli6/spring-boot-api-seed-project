package com.itqiwen.blog.entity;

public class Log {

    private Integer lid; //日志id
    private String action; //日志动作，比如删除文章，发布文章，访问后台等等
    private Integer createDt; //日志的操作时间
    private String ipAddress;//操作的ip地址


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
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
                "lid=" + lid +
                ", action='" + action + '\'' +
                ", createDt=" + createDt +
                '}';
    }
}
