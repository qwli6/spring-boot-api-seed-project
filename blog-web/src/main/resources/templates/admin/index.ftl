<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Simple-Blog 后台管理系统</title>
    <link rel="stylesheet" href="/static/admin/css/layui.css"/>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "admin_header.ftl"/>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:">Article 文章</a>
                    <dl class="layui-nav-child">
                        <dd class="layui-this"><a>文章列表</a></dd>
                        <dd><a href="/admin/content/new">添加文章</a></dd>
                        <dd><a href="/admin/content/garbage">回收站</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">目录和标签</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">目录</a></dd>
                        <dd><a href="javascript:;">标签</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">畅言评论</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">评论列表</a></dd>
                        <dd><a href="javascript:;">功能管理</a></dd>
                        <dd><a href="">分析统计</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">插件管理</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:">系统设置</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">静态数据</a></dd>
                        <dd><a href="javascript:;">账号设置</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="/admin/log/1">日志记录</a></li>
                <li class="layui-nav-item"><a href="">退出登录</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body" id="target_body" style="margin-left: 8px; margin-right: 8px;">
        <table class="layui-table">
            <colgroup>
                <col>
                <col width="8%">
                <col width="8%">
                <col width="8%">
                <col width="20%">
            </colgroup>
            <thead>
            <tr>
                <th style="font-size: 18px;">标题</th>
                <th style="font-size: 18px;">状态</th>
                <th style="font-size: 18px;">评论数</th>
                <th style="font-size: 18px;">点击数</th>
                <th style="font-size: 18px;">操作</th>
            </tr>
            </thead>
            <tbody>
            <#list contentPage.content as content>
                <tr>
                    <td>
                        <a href="/article/show/${content.visitUrl}" style="font-size: 16px; color: dodgerblue">
                            ${content.title}
                                <span style="font-family: Georgia,serif;color: gray">
                                (${(content.createDt*1000)?c?number?number_to_datetime})
                            </span>
                        </a>
                    </td>
                    <td style="font-size: 16px;"><i class="layui-icon">&#xe618;</i></td>
                    <td style="font-size: 16px;"><span style="font-family: Georgia,serif" class="layui-badge-rim layui-bg-blue">${content.remarkCount}</span></td>
                    <td style="font-size: 16px;"><span style="font-family: Georgia,serif" class="layui-badge-rim layui-bg-blue">${content.visitCount}</span></td>
                    <td>
                        <a href="/admin/content/del/${content.cid}" class="layui-btn layui-btn-danger"> <i class="layui-icon">&#xe640;</i></a>
                        <a href="/admin/content/modify/${content.cid}" class="layui-btn layui-btn-normal"><i class="layui-icon">&#xe642;</i></a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
        <!-- 后台文章列表分页-->
        <div class="layui-btn-group">
            <#if contentPage.number + 1 == 1>
                <a class="layui-disabled layui-btn-normal layui-btn-radius layui-btn"><i class="layui-icon">&#xe65a;</i></a>
                <#else>
                    <a href="/admin/content/${contentPage.number}"
                       class="layui-btn-normal layui-btn-radius layui-btn"><i class="layui-icon">&#xe65a;</i></a>
            </#if>
            <#list 1 .. contentPage.totalPages as i>
                <#if contentPage.number + 1 == i>
                    <a class="layui-btn-normal layui-btn-radius layui-btn layui-disabled">#{i}</a>
                <#else>
                    <a href="/admin/content/${i}" class="layui-btn-normal layui-btn-radius layui-btn">#{i}</a>
                </#if>
            </#list>

            <#if contentPage.number + 1 == contentPage.totalPages>
                <a class="layui-disabled layui-btn-normal layui-btn-radius layui-btn"><i class="layui-icon">&#xe65b;</i></a>
                <#else>
                    <a href="/admin/content/${contentPage.number+2}" class=" layui-btn-normal layui-btn-radius layui-btn"><i class="layui-icon">&#xe65b;</i></a>

            </#if>

        </div>
    </div>

    <div class="layui-footer">
        © lqwit.com - 码农全家桶管理后台
    </div>
</div>
<script src="/static/admin/js/layui.all.js"></script>
<script src="/static/jquery/jquery-3.2.1.min.js"></script>

<script>
    function showTime(){
        nowtime=new Date();
        year=nowtime.getFullYear();
        month=nowtime.getMonth()+1;
        date=nowtime.getDate();
        document.getElementById("mytime").innerText=year+"年"+month+"月"+date+" "+nowtime.toLocaleTimeString();
    }
    setInterval("showTime()",1000);
</script>
</body>
</html>