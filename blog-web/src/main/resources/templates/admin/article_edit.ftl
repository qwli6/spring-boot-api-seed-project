<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="icon" href="http://obyg3yq9k.bkt.clouddn.com/favicon.png"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Simple-blog 管理后台</title>
    <link rel="stylesheet" href="/static/admin/css/layui.css"/>
    <script type="text/javascript" src="/static/jquery/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="/static/editormd/css/editormd.min.css"/>
    <script type="text/javascript" src="/static/editormd/editormd.min.js"></script>
    <style type="text/css">
        body{
            background-color: #EEEEEE;
        }

        .on{
            background-color: dodgerblue;
            padding: 4px 16px;
            font-size: 16px;
            font-family: "Hiragino Sans GB",serif;
            color: white;
            outline: none;
            border-radius: 90px;
            cursor:pointer;
            border-width: 0;
            text-align: center;
        }

        .off{
            background-color: #bbbbbb;
            padding: 4px 16px;
            font-family: "Hiragino Sans GB",serif;
            font-size: 16px;
            text-align: center;
            color: #757575;
            outline: none;
            border-width: 0;
            border-radius: 90px;
            cursor:pointer;
        }

        .on:active{
            background-color: #1e50ff;
        }

        .off:active{
            background-color: #999999;
        }

        .yes{
            color: white;
            background-color:dodgerblue;
            cursor: pointer;
            outline: none;
            border-width: 0;
            display:inline-block;
            -moz-border-radius:4px;
            -webkit-border-radius:4px;
            font-size: 16px;
            padding: 4px 16px;
            border-radius: 4px;
            text-align: center;
        }

        .no{
            background-color: #bbbbbb;
            padding: 4px 16px;
            font-size: 16px;
            text-align: center;
            color: #757575;
            outline: none;
            border-width: 0;
            display:inline-block;
            -moz-border-radius:4px;
            -webkit-border-radius:4px;
            border-radius:4px;
            cursor:pointer;
        }

        .yes:active{
            background-color: #1e50ff;
        }

        .no:active{
            background-color: #999999;
        }

    </style>


</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "admin_header.ftl"/>
    <div class="layui-side layui-bg-black" style="background-color: #666666">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:">Article 文章</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/article/list/1">文章列表</a></dd>
                        <dd class="layui-this"><a>添加文章</a></dd>
                        <dd><a href="/admin/article/trash">回收站</a></dd>
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
                <li class="layui-nav-item"><a href="/logout">退出登录</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="margin-left: 8px; margin-right: 8px; padding-right: 8px;">
        <form class="layui-form" action="/admin/article/save" method="post">
            <#if article??>
                <div class="layui-form-item">
                    <input type="hidden" name="articleId" value="${article.articleId}">
                </div>
            <#else>
                <div class="layui-form-item">
                    <input type="hidden" name="articleId" autocomplete="off">
                </div>
            </#if>

            <#if content??>
                <div class="layui-form-item">
                    <input type="text" name="title"
                           placeholder="请输入标题" value="${article.title}" autocomplete="off" class="layui-input">
                </div>
            <#else>
                <div class="layui-form-item">
                    <input type="text" name="title"
                           placeholder="请输入标题，不要超过 60 个字符" class="layui-input" style="
                    height: 36px; font-size: 16px; padding-left: 16px; border: 1px solid #DDDDDD; ">
                </div>
            </#if>

            <#if content??>
                <div class="layui-form-item">
                    <div id="test-editormd">
                        <textarea class="editormd-md-textarea" name="markdown" style="display:none;">${article.markdown}</textarea>
                        <!-- 接受 html 格式数据-->
                        <textarea class="editormd-html-textarea"  name="html"></textarea>

                    </div>
                </div>
            <#else>
                <div class="layui-form-item">
                    <div id="test-editormd">
                        <textarea class="editormd-md-textarea" name="markdown" style="display:none;"></textarea>
                        <!-- 接受 html 格式数据-->
                        <textarea class="editormd-html-textarea"  name="html"></textarea>
                    </div>
                </div>
            </#if>

            <#if content??>
                <div class="layui-form-item">
                    <input type="text" class="layui-input" name="tags" style="height: 36px; font-size: 18px; padding-left: 16px; border: 1px solid #DDDDDD; "/>
                </div>
            <#else>
                <div class="layui-form-item">
                    <input type="text" class="layui-input" name="tags" style="height: 36px; font-size: 18px; padding-left: 16px; border: 1px solid #DDDDDD; "/>
                </div>
            </#if>


            <#if content??>
                <div class="layui-form-item">
                    <input type="text" name="url" autocomplete="off" class="layui-input" style="height: 36px; font-size: 16px; padding-left: 16px; border: 1px solid #DDDDDD;"/>
                </div>
                <#else>
                <div class="layui-form-item">
                    <input type="text" name="url" placeholder="友好的 URL 链接名称（选填）" class="layui-input" style="height: 36px; font-size: 16px; padding-left: 16px; border: 1px solid #DDDDDD; "/>
                </div>
            </#if>

                <input id="menuId" name="menuId" type="hidden"/>
                <input id="showInIndex" name="showInIndex" type="hidden"/>
            <div class="layui-form-item menu">
                <span class="showIndex">
                    <input type="button" value="是" name="1"/>&nbsp;&nbsp;
                    <input type="button" value="否" name="2"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </span>
                <span class="menu1">
                    <#list menuList as menu>
                        <input type="button" value="${menu.title}" name="${menu.id}">&nbsp;&nbsp;
                    </#list>
                </span>
            </div>

            <div class="layui-form-item">
                <button class="layui-btn layui-btn-normal">保存文章</button>
            </div>
        </form>


    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © lqwit.com - 码农全家桶管理后台
    </div>


        <script type="text/javascript">

            var buttonInIndex = $(".menu .showIndex").find("input");

            for(var i = 0; i < buttonInIndex.length; i++){
                if(i == 0){
                    var value = $(buttonInIndex[0]).attr("name");
                    $("#showInIndex").val(value);
                    $(buttonInIndex[i]).addClass("yes");
                }else{
                    $(buttonInIndex[i]).addClass("no");
                }
            }
            for(var i = 0; i < buttonInIndex.length; i++){
                (function(){
                    var index = i;
                    $(buttonInIndex[index]).click(function(event) {
                        if($(buttonInIndex[index]).hasClass("no")){
                            $(buttonInIndex[index]).removeClass("no");
                            $(buttonInIndex[index]).addClass("yes");
                            var val = $(buttonInIndex[index]).attr("name");
                            $("#showInIndex").val(val);
                            for(var j = 0; j < buttonInIndex.length; j++){
                                if(j !== index){
                                    $(buttonInIndex[j]).removeClass("yes");
                                    $(buttonInIndex[j]).addClass("no");
                                }
                            }
                        }
                    });
                })();
            }

            var button = $(".menu .menu1").find("input");
            for(var i = 0; i < button.length; i++){
                if(i === 0){
                    var value = $(button[0]).attr("name");
                    $("#menuId").val(value);
                    $(button[i]).addClass("on");
                }else{
                    $(button[i]).addClass("off");
                }
            }
            for(var i = 0; i < button.length; i++){
                (function(){
                    var index = i;
                    $(button[index]).click(function(event) {
                        if($(button[index]).hasClass("off")){
                            $(button[index]).removeClass("off");
                            $(button[index]).addClass("on");
                            var val = $(button[index]).attr("name");
                            $("#menuId").val(val);
                            for(var j = 0; j < button.length; j++){
                                if(j !== index){
                                    $(button[j]).removeClass("on");
                                    $(button[j]).addClass("off");
                                }
                            }
                        }
                    });
                })();
            }
        </script>

    <script type="text/javascript">
        var testEditor;
        $(function() {
            testEditor = editormd("test-editormd", {
                width   : "90%",
                height  : 640,
                syncScrolling : "single",
                placeholder:"请开始你的表演...",
                path    : "/static/editormd/lib/",
                imageUpload : true,
                toolbarAutoFixed: false, //固定工具栏
                htmlDecode: true,
                fontSize: "16px",
                imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL : "./php/upload.php",
                toolbarIcons : function() {
                    return ["h1","h2","h3","h4","h5", "|","bold", "hr","|","image","code",
                        "|","table", "datetime","fullscreen","||", "watch", "preview"]
                },
                saveHTMLToTextarea : true
            });
        });

    </script>

    <script>
        layui.use('form', function(){
            var form = layui.form;
            form.render(); //不加这行代码 layui 可能导致复选框无法显示出来

        });
    </script>


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
</div>

<script type="text/javascript" src="/static/admin/js/layui.all.js"></script>
</body>
</html>