package com.itqiwen.blog.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "sblog_article", catalog = "simple_blog")
public class Article implements java.io.Serializable {

	private static final long serialVersionUID = 9169337066300818107L;

	public static final int ADMIN_PAGE_SIZE = 8; //后台文章页数大小
	public static final int PAGE_SIZE = 3; //前台文章页数大小

	public static final short TYPE_ARTICLE = 0;	//正式文章
	public static final short TYPE_ARCHIVE_HEAD = 1;	//头部不带标题文字块
	public static final short TYPE_ARCHIVE_FOOT = 2;	//底部不带标题文字块
	public static final short TYPE_DATA    = 3;			//外部数据引用，其html部分由url通过HTTPGET获取
	public static final short TYPE_WIDGET  = 11;		//自定义插件
	public static final short TYPE_WIDGET_TAGS = 12; 	//标签插件
	public static final short TYPE_WIDGET_ARCHIVE = 13;	//存档插件
	
	public static final short STATE_EDIT    = 0;	//编辑状态，暂时不显示
	public static final short STATE_PUBLISH = 1;	//发布状态，正常显示
	public static final short STATE_DELETE  = 2;	//删除状态，前台后台都不显示，垃圾箱中
	
	private Integer   articleId;
	private String    title;
	private String    html;
	private String    markdown;
	private Short	  type = TYPE_ARTICLE; // 文章类型
	private String    url;
	private String    urlName;
	private Short     state = STATE_PUBLISH; //文章状态
	private Long      rate = 0L; //文章点击量
	private Integer   index = 0;
	private Integer createDate = ((int) (new Date().getTime() / 1000L));
	private Integer lastEditDate = ((int) (new Date().getTime()/ 1000L));
	private Boolean   showInList = false;	//是否在「日志列表」中显示

	private Short menuId;
	private Short tagId;
	private Short archiveId;

	public Article() {
	}
	
	public Article(Integer articleId) {
		this.articleId=articleId;
	}
	
	public Article(Integer articleId, String title, Short type, String url, String urlName, Short state, Long rate,
				   Integer createDate, Integer lastEditDate) {
		this.articleId = articleId;
		this.title = title;
		this.type = type;
		this.url = url;
		this.urlName = urlName;
		this.state = state;
		this.rate = rate;
		this.createDate = createDate;
		this.lastEditDate = lastEditDate;
	}
	
	public Article(String html){
		this.html = html;
	}
	
	public Article(Integer articleId, String title, String urlName, Integer createDate, String html){
		this.articleId = articleId;
		this.title = title;
		this.urlName = urlName;
		this.createDate = createDate;
		this.html = html;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	@Column(name = "create_dt")
	public Integer getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Integer createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "last_edit_dt")
	public Integer getLastEditDate() {
		return this.lastEditDate;
	}

	public void setLastEditDate(Integer lastEditDate) {
		this.lastEditDate = lastEditDate;
	}

	@Column(name = "html")
	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	@Column(name = "markdown")
	public String getMarkdown() {
		return markdown;
	}

	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}

	@Column(name = "title", length = 45)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "type" , nullable = false)
	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "url", unique = true)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "url_name")
	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	
	@Column(name = "state", nullable = false)
	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "rate" , nullable = false)
	public Long getRate() {
		return this.rate;
	}

	public void setRate(Long rate) {
		this.rate = rate;
	}
	
	@Column(name = "page_index")
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	

	@Column(name = "show_in_list")
	public Boolean getShowInList() {
		return showInList;
	}

	public void setShowInList(Boolean showInList) {
		this.showInList = showInList;
	}


	public void setTagId(Short tagId) {
		this.tagId = tagId;
	}

	public void setMenuId(Short menuId) {
		this.menuId = menuId;
	}

	@Column(name = "menu_id")
	public Short getMenuId() {
		return menuId;
	}

	@Column(name = "tag_id")
	public Short getTagId() {
		return tagId;
	}

	@Column(name = "archive_id")
	public Short getArchiveId() {
		return archiveId;
	}

	public void setArchiveId(Short archiveId) {
		this.archiveId = archiveId;
	}
}