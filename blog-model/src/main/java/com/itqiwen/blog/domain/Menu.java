package com.itqiwen.blog.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JblogMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sblog_menu", catalog = "simple_blog")
public class Menu implements Serializable {
	private static final long serialVersionUID = 3140725422939385736L;
	
	public static final String MENU_INDEX = "index";
	
	private Integer id;
	private String title;
	private String urlName;
	private Boolean isLeft;
	private String url;
	private Boolean isShow;


	public Menu() {
	}
	
	public Menu(Integer id){
		this.id = id;
	}

	public Menu(String title, Boolean isLeft, Boolean isShow) {
		this.title = title;
		this.isLeft = isLeft;
		this.isShow = isShow;
	}

	public Menu(String title, String urlName, Boolean isLeft, String url,
			Boolean isShow) {
		this.title = title;
		this.urlName = urlName;
		this.isLeft = isLeft;
		this.url = url;
		this.isShow = isShow;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "title",length = 45)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "url_name", length = 45)
	public String getUrlName() {
		return this.urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	@Column(name = "url", length = 80)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	@Column(name = "is_left")
	public Boolean getLeft() {
		return isLeft;
	}

	public void setLeft(Boolean left) {
		isLeft = left;
	}

	@Column(name = "is_show")
	public Boolean getShow() {
		return isShow;
	}

	public void setShow(Boolean show) {
		isShow = show;
	}

	@Override
	public String toString() {
		return "Menu{" +
				"id=" + id +
				", title='" + title + '\'' +
				", urlName='" + urlName + '\'' +
				", isLeft=" + isLeft +
				", url='" + url + '\'' +
				", isShow=" + isShow +
				'}';
	}
}