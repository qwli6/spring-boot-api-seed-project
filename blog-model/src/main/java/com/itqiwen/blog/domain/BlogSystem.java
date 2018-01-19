package com.itqiwen.blog.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sblog_system", catalog = "simple_blog")
public class BlogSystem implements Serializable{
	
	public static final String KEY_USERNAME = "username";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_BLOGTITLE= "blog_name";
	public static final String KEY_RECORD   = "blog_record";
	public static final String KEY_METAS	= "blog_metas";
	public static final String KEY_URL 		= "blog_url";
	public static final String KEY_JS		= "blog_js";
	public static final String KEY_DESC		= "blog_description";
	public static final String KEY_KEYWORDS = "blog_keywords";
	public static final String KEY_QNACCESS = "qiniu_access_key";
	public static final String KEY_QNSECRET = "qiniu_secret_key";
	public static final String KEY_DUOSHUO  = "duoshuo_id";
	public static final String KEY_GEETESTAK = "geetest_ak";
	public static final String KEY_GEETESTSK = "geetest_sk";
	public static final String KEY_EMAIL    = "blog_email";
	public static final Object KEY_GUEST_USERNAME = "guest_username";
	public static final Object KEY_GUEST_PASSWORD = "guest_password";
	
	private static final long serialVersionUID = -2839886268016617303L;
	
	
	
	private Integer	systemId;
	private String  key;
	private String  value;
	private String  append;
	
	public BlogSystem(){
		this.value = "";
	}
	
	public BlogSystem(Integer systemId){
		this.systemId = systemId;
	}
	
	public BlogSystem(String key, String value){
		this.key = key;
		this.value = value;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getSystemId() {
		return systemId;
	}
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}
	
	@Column(name = "skey", length = 45)
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	@Column(name = "svalue")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * 扩展信息，一般不需要用到
	 */
	@Column(name = "append" , length = 45)
	public String getAppend() {
		return append;
	}
	public void setAppend(String append) {
		this.append = append;
	}
}