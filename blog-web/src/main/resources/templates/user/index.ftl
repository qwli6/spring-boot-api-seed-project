<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <!-- 网站logo  这些东西全部要缓存起来-->
    <link rel="icon" href="http://obyg3yq9k.bkt.clouddn.com/favicon.png"/>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="/static/jquery/jquery-3.2.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="/static/common/base.css" rel="stylesheet"/>
    <link href="http://cdn.bootcss.com/highlight.js/8.0/styles/monokai_sublime.min.css" rel="stylesheet"/>
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
    <nav class="navbar navbar-default">
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
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/list">归档</a></li>
                    <li><a href="https://weibo.com/5091257436" target="_blank">微博</a></li>
                    <li><a href="https://www.zhihu.com/people/www.lqwit.com/activities" target="_blank">知乎</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="row">
        <div class="col-lg-9">
            <#list articlePage.content as article>
                <div class="panel panel-default">
                    <div class="panel-body blog">
                        <a class="blog-title" href="/article/show/${article.url}">${article.title}</a>
                        <p class="blog-create-time">
                            ${(article.createDate*1000)?c?number?number_to_datetime}
                            <span style="float: right">${article.clickCount}</span>
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