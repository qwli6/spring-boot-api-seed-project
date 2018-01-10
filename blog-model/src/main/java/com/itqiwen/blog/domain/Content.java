package com.itqiwen.blog.domain;

import javax.persistence.*;

@Entity
@Table(name = "s_content")
public class Content {

    @Id
    @GeneratedValue
    private Integer cid;
    private String title;
    private String digest;
    private String content;
    @Column(name = "content_md")
    private String contentMd;

    @Column(name = "create_dt")
    private Integer createDt;

    @Column(name = "update_dt")
    private Integer updateDt;

    @Column(name = "visit_url")
    private String visitUrl;

    @Column(name = "visit_count")
    private Integer visitCount;

    @Column(name = "remark_count")
    private String remarkCount;

    //发布，预览/草稿箱, 删除， 从回收站删除，四种状态
    private String state;
    //对应文件私密或者公开
    private String type;

    //该字段参考自 meta 中的 type 类型中的 category
    @Column(name = "cate_id")
    private Integer cateId;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentMd() {
        return contentMd;
    }

    public void setContentMd(String contentMd) {
        this.contentMd = contentMd;
    }

    public Integer getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Integer createDt) {
        this.createDt = createDt;
    }

    public Integer getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Integer updateDt) {
        this.updateDt = updateDt;
    }

    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public String getRemarkCount() {
        return remarkCount;
    }

    public void setRemarkCount(String remarkCount) {
        this.remarkCount = remarkCount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }
}
