package com.itqiwen.blog.domain;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 传递的公共数据，
 * 比如标题，备案，meta信息等
 */
@Component
public class BlogCommon {
	private Boolean blogInit   = false;
	private String  blogTitle  = "";
	private String  blogRecord = "";
	private String  blogMetas  = "";
	private String  blogUrl    = "";
	private String  blogJs	   = "";
	private String  blogDescription = "";
	private String  blogKeywords = "";
	private String  blogEmail  = "";
	
	private List<Menu> leftMenus  = null;
	private List<Menu> rightMenus = null;
	
	private List<Archive> archives = null;
	
	private List<Article> widgetList = null;
	
	private List<Tag> tags = null;
	
	public String getBlogJs() {
		return blogJs;
	}
	public void setBlogJs(String blogJs) {
		this.blogJs = blogJs;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogRecord() {
		return blogRecord;
	}
	public void setBlogRecord(String blogRecord) {
		this.blogRecord = blogRecord;
	}
	public String getBlogMetas() {
		return blogMetas;
	}
	public void setBlogMetas(String blogMetas) {
		this.blogMetas = blogMetas;
	}
	public String getBlogUrl() {
		return blogUrl;
	}
	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}
	public Boolean getBlogInit() {
		return blogInit;
	}
	public void setBlogInit(Boolean blogInit) {
		this.blogInit = blogInit;
	}
	public String getBlogDescription() {
		return blogDescription;
	}
	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}
	public String getBlogKeywords() {
		return blogKeywords;
	}
	public void setBlogKeywords(String blogKeywords) {
		this.blogKeywords = blogKeywords;
	}
	public List<Menu> getLeftMenus() {
		return leftMenus;
	}
	public void setLeftMenus(List<Menu> leftMenus) {
		this.leftMenus = leftMenus;
	}
	public List<Menu> getRightMenus() {
		return rightMenus;
	}
	public void setRightMenus(List<Menu> rightMenus) {
		this.rightMenus = rightMenus;
	}
	public List<Archive> getArchives() {
		return archives;
	}
	public void setArchives(List<Archive> archives) {
		this.archives = archives;
	}
	public List<Article> getWidgetList() {
		return widgetList;
	}
	public void setWidgetList(List<Article> widgetList) {
		this.widgetList = widgetList;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public void setKeyValue(String key, String value) {
		switch(key){
		case BlogSystem.KEY_METAS:
			this.blogMetas = value;
			break;
		case BlogSystem.KEY_BLOGTITLE:
			this.blogTitle = value;
			break;
		case BlogSystem.KEY_DESC:
			this.blogDescription = value;
			break;
		case BlogSystem.KEY_JS :
			this.blogJs = value;
			break;
		case BlogSystem.KEY_KEYWORDS :
			this.blogKeywords = value;
			break;
		case BlogSystem.KEY_RECORD :
			this.blogRecord = value;
			break;
		case BlogSystem.KEY_URL :
			this.blogUrl = value;
			break;
		}
	}
	public String getBlogEmail() {
		return blogEmail;
	}
	public void setBlogEmail(String blogEmail) {
		this.blogEmail = blogEmail;
	}
}
