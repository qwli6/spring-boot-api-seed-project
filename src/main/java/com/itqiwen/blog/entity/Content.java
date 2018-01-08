package com.itqiwen.blog.entity;


import java.util.HashSet;
import java.util.Set;

/**
 * 文章实体类
 * 文章-分类： 1对1  一个实体类
 * 文章-标签： 1对n  一个 Set 集合
 * @author liqiwen
 */
public class Content {

    private Integer cid;
    private String title;//文章标题
    private String digest; //文章摘要
    private String content;//文章内容
    private String contentMd;//文章 md
    private Integer createDt; //文章创建时间
    private Integer updateDt; //文章上一次修改时间
    private String visitUrl;//文章访问 url
    private Integer visitCount;//文章点击次数
    private Integer remarkCount; //文章评论次数
    private String status;//文章状态 0 正常文章， 1 私密文章
    private String type; //文章类型 post 正常发布， draft 存为草稿（预览的时候把文章先存为草稿，在进行预览）, delete 删除文章，保存在回收站中

    /**
     * 文章分类，一篇文章对应一个分类
     */
    private Meta category;

    public Meta getCategory() {
        return category;
    }

    public void setCategory(Meta category) {
        this.category = category;
    }

    /**
     * 一篇文章对应多个 tag，在一方配置 set 集合，并且需要手动初始化
     */
    private Set<Meta> tagSet = new HashSet<>();


    public Set<Meta> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<Meta> tagSet) {
        this.tagSet = tagSet;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRemarkCount() {
        return remarkCount;
    }

    public void setRemarkCount(Integer remarkCount) {
        this.remarkCount = remarkCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
