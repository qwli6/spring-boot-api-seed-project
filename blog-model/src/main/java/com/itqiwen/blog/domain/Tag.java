package com.itqiwen.blog.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sblog_tags_t", catalog = "simple_blog")
public class Tag implements Serializable{
	private static final long serialVersionUID = -7128396494920042866L;
	
	private Integer tagId;
	private String  title;
	private String  urlName;
	private Long count;
	
	public Tag(){
		
	}
	
	public Tag(Integer id){
		
	}
	
	public Tag(String tagTitle) {
		this.title = tagTitle;
		this.urlName = tagTitle;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	@Column(name = "title", length = 45,unique = true)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "url_name",unique = true)
	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	@Column(name = "count")
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
