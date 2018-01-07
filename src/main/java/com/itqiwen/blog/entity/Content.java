package com.itqiwen.blog.entity;

import java.util.Date;

/**
 * 文章实体类
 */
public class Content {

    private String cid;
    private String title;//文章标题
    private String digest; //文章摘要
    private String content;//文章内容
    private String contentMd;//文章 md
    private Date createDt; //文章创建时间
    private Date updateDt; //文章上一次修改时间
    private String visitUrl;//文章访问 url
    private Long visitCount;//文章点击次数

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
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

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl;
    }

    public Long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }

    @Override
    public String toString() {
        return "Content{" +
                "cid='" + cid + '\'' +
                ", title='" + title + '\'' +
                ", digest='" + digest + '\'' +
                ", content='" + content + '\'' +
                ", contentMd='" + contentMd + '\'' +
                ", createDt=" + createDt +
                ", updateDt=" + updateDt +
                ", visitUrl='" + visitUrl + '\'' +
                ", visitCount=" + visitCount +
                '}';
    }
}
