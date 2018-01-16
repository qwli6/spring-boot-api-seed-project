<!DOCTYPE html>
<html>
<head>
    <!--
        有些分类文章中的布局一样，所以就复用了
        比如 spring-boot 和 java8 这两个页面内容是一致的，因此复用界面
    -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="/static/jquery/jquery-3.2.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">
        body{
            background-color: #F4F4F4;
        }

        .nav>li>a:focus{
            color: dodgerblue;
        }

        .nav>li>a:hover{
            color: .dodgerblue;
        }
    </style>
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
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/" style="font-size: 18px;font-family: 'Hiragino Sans GB',serif">码农全家桶</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <#list categoryList as category>
                        <#if category.id != 1>
                            <li><a href="/${category.url}" style="font-size: 16px;font-family: 'Hiragino Sans GB',serif">${category.name}</a></li>
                        </#if>
                    </#list>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a style="font-size: 16px;font-family: 'Hiragino Sans GB',serif">RSS</a></li>
                    <li><a style="font-size: 16px;font-family: 'Hiragino Sans GB',serif">微博</a></li>
                    <li><a style="font-size: 16px;font-family: 'Hiragino Sans GB',serif">知乎</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="row">
        <div class="col-lg-9">
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