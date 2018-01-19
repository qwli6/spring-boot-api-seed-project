<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="http://obyg3yq9k.bkt.clouddn.com/favicon.png"/>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="/static/jquery/jquery-3.2.1.min.js"/>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="/static/common/base.css" rel="stylesheet"/>
    <link href="http://cdn.bootcss.com/highlight.js/8.0/styles/monokai_sublime.min.css" rel="stylesheet">
    <script src="http://cdn.bootcss.com/highlight.js/8.0/highlight.min.js"></script>
    <script >hljs.initHighlightingOnLoad();</script>
</head>
<body>
<div class="container">
    <div class="row">
        <h1 style="padding-top: 32px;padding-bottom: 32px; padding-left: 16px;"><a href="/" style="color: black; text-decoration: none">码农全家桶</a></h1>
    </div>
    <nav class="navbar navbar-default" style="background-color: white">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/" style="font-size: 18px;">码农全家桶</a>
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <#list categoryList as category>
                        <#if category.id != 1>
                            <li><a href="/${category.url}">${category.name}</a></li>
                        </#if>
                    </#list>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="https://weibo.com/5091257436" target="_blank">微博</a></li>
                    <li><a href="https://www.zhihu.com/people/www.lqwit.com/activities">知乎</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-body blog">
                    <a class="blog-title">${content.title}</a>
                    <p class="blog-create-time">${(content.createDt*1000)?c?number?number_to_datetime}</p>
                    <div class="blog-divider"></div>
                    <div class="blog-content">
                        ${content.contentHtml}
                    </div>
                </div>
            </div>


            <div class="panel panel-default">
                <div class="panel-body">
                    <!--PC版-->
                    <div id="SOHUCS" sid="${content.cid}"></div>
                    <script charset="utf-8" type="text/javascript" src="https://changyan.sohu.com/upload/changyan.js" ></script>
                    <script type="text/javascript">
                        window.changyan.api.config({
                            appid: 'cytq2ja8d',
                            conf: 'prod_b6e1bfb11fb49f8a49cea12d721257f3'
                        });
                    </script>
                </div>
            </div>


        </div>
    </div>


</div>
<#include "../common/copyright.ftl"/>
<a title="回到顶部" style="display: none" class="am-icon-btn am-icon-arrow-up" id="goTop">Top</a>
<script>
    $('#goTop').click(function(){$('html,body').animate({scrollTop: '0px'}, 800);return false;});
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