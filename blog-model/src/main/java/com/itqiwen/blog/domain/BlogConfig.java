package com.itqiwen.blog.domain;

/**
 *	静态存储一些系统配置
 *	非数据库模型
 *
 */
public class BlogConfig {
	public final static String CAPTCHA		= "captcha";
	public final static String PUNISH_TIME	= "punish_time";
	public final static String POST_NUM		= "post_num";
	public final static String SYSTEM		= "system";
	
	public static String QN_ACCESSKEY = "";
	public static String QN_SECRETKEY = "";
	
	public static String GEETEST_AK = "";
	public static String GEETEST_SK = "";
	
	public static String DUOSHUO_ID = "";
	
	//登录失败次数的惩罚阀值
	public static int PUNISH_NUM;
	public void setPunishNum(int punishNum){
		BlogConfig.PUNISH_NUM=punishNum;
	}
	
	//登录失败的惩罚分钟数
	public static int PUNISH_MINUTE;
	public void setPunishMinute(int punishMinute){
		BlogConfig.PUNISH_MINUTE=punishMinute;
	}
	
	//分页显示文章列表时的每页个数
	public static int ARTICLES_PER_PAGE;
	public void setArticlesPerPage(int articlesPerPage){
		BlogConfig.ARTICLES_PER_PAGE=articlesPerPage;
	}
	
	//文章最多在URL上显示几页
	public static int ARTICLES_SHOW_PAGE;
	public void setArticlesShowPage(int articlesShowPage){
		BlogConfig.ARTICLES_SHOW_PAGE=articlesShowPage;
	}
	
	//分页显示文章列表时的每页个数
	public static int ADMIN_ARTICLES_PER_PAGE;
	public void setAdminArticlesPerPage(int adminArticlesPerPage){
		BlogConfig.ADMIN_ARTICLES_PER_PAGE = adminArticlesPerPage;
	}
	
	//文章最多在URL上显示几页
	public static int ADMIN_ARTICLES_SHOW_PAGE;
	public void setAdminArticlesShowPage(int adminArticlesShowPage){
		BlogConfig.ADMIN_ARTICLES_SHOW_PAGE = adminArticlesShowPage;
	}
	
	//分页显示评论列表时的每页个数
	public static int COMMENTS_PER_PAGE;
	public void setCommentsPerPage(int commentsPerPage){
		BlogConfig.COMMENTS_PER_PAGE=commentsPerPage;
	}
	
	// 评论最多在URL上显示几页
	public static int COMMENTS_SHOW_PAGE;
	public void setCommentsShowPage(int commentsShowPage) {
		BlogConfig.COMMENTS_SHOW_PAGE = commentsShowPage;
	}
	
	/**
	 * 首页显示的默认菜单组，一般是index，也可以在配置文件中定义，对应数据库Menu中的urlName
	 */
	public static String MENU_INDEX_URL_NAME;
	public void setMenuIndexUrlName(String miun){
		BlogConfig.MENU_INDEX_URL_NAME = miun;
	}
	
	public static int RSS_COUNT = 30;
	public void setRssCount(int rssCount){
		BlogConfig.RSS_COUNT = rssCount;
	}
	
	public static void setKeyValue(String key, String value) {
		switch(key){
		case BlogSystem.KEY_DUOSHUO :
			BlogConfig.DUOSHUO_ID = value;
			break;
		case BlogSystem.KEY_QNACCESS :
			BlogConfig.QN_ACCESSKEY = value;
			break;
		case BlogSystem.KEY_QNSECRET :
			BlogConfig.QN_SECRETKEY = value;
			break;
		case BlogSystem.KEY_GEETESTAK :
			BlogConfig.GEETEST_AK = value;
			break;
		case BlogSystem.KEY_GEETESTSK :
			BlogConfig.GEETEST_SK = value;
			break;
		}
		
	}
	
}
