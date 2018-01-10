package com.itqiwen.blog.domain;

import javax.persistence.*;

@Entity
@Table(name = "s_metas")
public class Metas {

    @Id
    @GeneratedValue
    private Integer mid;
    private String name;
    private String desc;
    private String url;

    @Column(name = "create_dt")
    private Integer createDt;

    //类型，该类型对应三种值：category， link，以及 tag
    private String type;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Integer createDt) {
        this.createDt = createDt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
