<!DOCTYPE html>
<html>
<head>

    <link rel="icon" href="http://obyg3yq9k.bkt.clouddn.com/favicon.png"/>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="/static/jquery/jquery-3.2.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="/static/common/base.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <div class="row">
        <h1 style="padding-top: 32px;padding-bottom: 32px; padding-left: 16px;">
            <a href="/" style="color: black; text-decoration: none;font-family: 'Hiragino Sans GB',serif">码农全家桶</a>
        </h1>
    </div>
    <nav class="navbar navbar-default" style="background-color: white">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/" style="font-size: 18px;font-family: 'Hiragino Sans GB',serif">码农全家桶</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <#list categoryList as category>
                        <#if category.id != 1>
                            <li><a href="/${category.url}" style="font-size: 16px;font-family: 'Hiragino Sans GB',serif">${category.name}</a></li>
                        </#if>
                    </#list>
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
            <#include "content_header.ftl"/>

            <#list contents as content>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h1><a href="/article/show/${content.visitUrl}" style="text-decoration: none;
                        font-family: 'Hiragino Sans GB',serif; color: dodgerblue">${content.title}</a></h1>
                        <p class="text-left" style="font-family: Georgia,serif; font-size: 16px; color: gray">
                            ${(content.createDt*1000)?c?number?number_to_datetime}
                        </p>
                        <hr/>

                        <div style="font-size: 16px;line-height: 24px;font-family: 'Hiragino Sans GB',serif">
                            ${content.contentHtml}
                        </div>
                    </div>
                </div>
            </#list>
        </div>
        <div class="col-lg-3">
            <#include "personal.ftl"/>
        </div>
    </div>


</div>

<#include "../common/copyright.ftl"/>
</body>


</html>