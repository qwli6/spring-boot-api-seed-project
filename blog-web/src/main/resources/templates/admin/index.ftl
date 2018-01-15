<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="/static/admin/css/layui.css"/>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">Simple-Blog 管理后台</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right">

            <li class="layui-nav-item"><a>欢迎您！ <span>不荒</span></a></li>
            <li class="layui-nav-item"><a>当前时间: <span id="mytime"></span></a></li>
            <li class="layui-nav-item"><a href="/"><i class="layui-icon">&#xe609;</i>&nbsp;&nbsp; 去首页</a></li>
        </ul>
    </div>

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
                    <a href="javascript:;">多说评论</a>
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
                <th>标题</th>
                <th>状态</th>
                <th>评论数</th>
                <th>点击数</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#list contents as content>
                <tr>
                    <td>
                        <a href="/article/show/${content.visitUrl}" style="font-size: 16px;">
                            ${content.title}
                                <span style="font-family: Georgia,serif">
                                (${(content.createDt*1000)?c?number?number_to_datetime})
                            </span>
                        </a>
                    </td>
                    <td style="font-size: 16px;">${content.state}</td>
                    <td style="font-size: 16px;"><span style="font-family: Georgia,serif" class="layui-badge-rim layui-bg-blue">${content.remarkCount}</span></td>
                    <td style="font-size: 16px;"><span style="font-family: Georgia,serif" class="layui-badge-rim layui-bg-blue">${content.visitCount}</span></td>
                    <td>
                        <a href="/admin/content/del/${content.cid}" class="layui-btn layui-btn-danger">删除</a>
                        <a href="/admin/content/modify/${content.cid}" class="layui-btn layui-btn-normal">编辑</a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
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