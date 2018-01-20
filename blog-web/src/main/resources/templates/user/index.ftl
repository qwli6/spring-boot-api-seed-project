<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <!-- 网站logo  这些东西全部要缓存起来-->
    <link rel="icon" href="http://obyg3yq9k.bkt.clouddn.com/favicon.png"/>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="/static/jquery/jquery-3.2.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="/static/common/base.css" rel="stylesheet"/>
    <link href="http://cdn.bootcss.com/highlight.js/8.0/styles/monokai_sublime.min.css" rel="stylesheet">
    <script src="http://cdn.bootcss.com/highlight.js/8.0/highlight.min.js"></script>
    <script >hljs.initHighlightingOnLoad();</script>
</head>
<body>
<div class="container">
    <div class="row">
        <h1 style="padding-top: 32px;padding-bottom: 32px; padding-left: 16px;">
            <a href="/" style="color: black; text-decoration: none;font-family: 'Hiragino Sans GB',serif">码农全家桶</a>
        </h1>
    </div>
    <nav class="navbar navbar-default" style="background-color: white;box-shadow: 0 0 1px 1px #CCCCCC;border-radius: 4px;">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">码农全家桶</a>
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <#list menuList as menu>
                        <#if (menu.id > 1)>
                            <li><a href="/${menu.url}">${menu.title}</a></li>
                        </#if>
                    </#list>
                        <li><a href="/list">日志列表</a></li>

                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="https://weibo.com/5091257436" target="_blank">微博</a></li>
                    <li><a href="https://www.zhihu.com/people/www.lqwit.com/activities" target="_blank">知乎</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="row">
        <div class="col-lg-9">
            <!-- 文章列表头部 bolg 声明-->
            <div class="panel panel-default" style="box-shadow: 0 0 1px 1px #CCCCCC;border-radius: 4px;">
                <div class="panel-body" style="font-size: 16px; line-height: 28px;font-family: 'Georgia',serif;">
                    <span>Simple-Blog</span> 是一个利用 SpringBoot、SpringDataJpa、SpringMVC、Maven 以及 Freemarker 开发的一个项目，所有新学的知识都会在这上面展开。
                    <span style="color: red">注意：这套系统本来是准备给自己用的，因为比较符合自己的习惯</span>！如果你们要使用的话，难免会出现
                    一些不可描述的 Bug，如果有兴趣的话，可以来我的 <a href="https://github.com/selfassu/simple-blog" style="text-decoration: none; color: dodgerblue" target="_blank"> Github</a>上看看，另外如果对你的胃口的话，给我一个 Star 鼓励鼓励我可好？非常感谢！
                </div>
            </div>

            <#list articlePage.content as article>
                <div class="panel panel-default" style="box-shadow: 0 0 1px 1px #CCCCCC;border-radius: 4px;">
                    <div class="panel-body blog">
                        <a class="blog-title" href="/article/show/${article.urlName}">${article.title}</a>
                        <p class="blog-create-time">
                            ${(article.createDate*1000)?c?number?number_to_datetime}
                        </p>
                        <div class="blog-divider"></div>

                        <div class="blog-content">
                            ${article.html}
                        </div>
                    </div>
                </div>
            </#list>
            <!-- 总页数大于 1才展示页脚，否则不展示-->
            <#if articlePage.totalPages gt 1>
                <#include "page.ftl"/>
            </#if>
        </div>
        <div class="col-lg-3" style="padding-left: 8px">
            <#include "personal.ftl"/>
        </div>
    </div>


</div>
<#include "../common/copyright.ftl"/>
<a href="#" title="回到顶部" style="display: none" class="am-icon-btn am-icon-arrow-up" id="goTop">Top</a>
<script>
    $('#goTop').click(function(){
        $('html,body').animate({
            scrollTop: '0px'
        }, 800);
        return false;
    });
    window.onscroll = function () {
        if (document.documentElement.scrollTop + document.body.scrollTop > 100) {
            document.getElementById("goTop").style.display = "block";
        }
        else {
            document.getElementById("goTop").style.display = "none";
        }
    }
</script>
</body>
</html>