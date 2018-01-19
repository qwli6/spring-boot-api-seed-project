package com.itqiwen.blog.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于在「日志列表」中以年月为依据的分组数据
 *
 */
public class ArticleDateMap {
	//年月格式的时间
	private String date;
	//该年月发布的日志列表
	private List<Article> articleList;
	
	public ArticleDateMap(String date){
		this.date = date;
		this.articleList = new ArrayList<>();
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<Article> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}
}
