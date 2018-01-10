package com.itqiwen.blog.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author liqiwen
 * 文章和标签的关系表
 */
@Entity
@Table(name = "s_relationship")
public class RelationShip implements Serializable{

    @Id
    @GeneratedValue
    private Integer mid;
    private Integer cid;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
