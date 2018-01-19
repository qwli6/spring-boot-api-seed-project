package com.itqiwen.blog.domain;

import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 日志归档类，一般对应的是年月格式的字符串归档
 * 此实体类对应的是一个视图
 * 增、删、改的操作需要使用SQL重写
 */
@Entity
@Table(name = "sblog_archive_v", catalog = "simple_blog")
public class Archive implements java.io.Serializable {
	private static final long serialVersionUID = -1789453625297575029L;
	
	private Integer archiveId;
	private String  title; //比如 2018年1月
	private String  urlName;  //201801
	private Long count = 0L; //3 篇文章

	public Archive() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getArchiveId() {
		return this.archiveId;
	}

	public void setArchiveId(Integer archiveId) {
		this.archiveId = archiveId;
	}

	@Column(name = "title", length = 10, nullable = false, unique = true)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "url_name")
	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	@Column(name = "count", nullable = false)
	public Long getCount() {
		return this.count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}