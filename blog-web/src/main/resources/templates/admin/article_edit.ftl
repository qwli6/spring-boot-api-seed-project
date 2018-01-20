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

        .layui-form p span{
            background-color: white;
            border-radius: 4px;
            padding: 4px 16px;
            color: white;
            display:inline-block;
            cursor:pointer;
        }

        .button1 {
            display: inline-block;
            padding: 15px 25px;
            font-size: 24px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            outline: none;
            color: #fff;
            background-color: #4CAF50;
            border: none;
            border-radius: 4px;
            box-shadow: 0 4px #999;
        }

        .button1:hover {background-color: #3e8e41}

        .button1:active {
            background-color: #3e8e41;
            box-shadow: 0 5px #666;
            transform: translateY(4px);
        }

        .layui-form p span.on{
            background-color: #01AAED;
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
                        <dd><a href="/admin/article/1">文章列表</a></dd>
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

    <div class="layui-body" style="margin-left: 8px; margin-right: 8px;">
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
                           placeholder="请输入标题，不要超过 60 个字符" style="width: 100%;
                    height: 36px; font-size: 18px; padding-left: 16px; border: 1px solid #DDDDDD; ">
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
                    <input type="text" name="tags" style="height: 36px; font-size: 18px; padding-left: 16px; border: 1px solid #DDDDDD; "/>
                </div>
            <#else>
                <div class="layui-form-item">
                    <input type="text" name="tags" style="height: 36px; font-size: 18px; padding-left: 16px; border: 1px solid #DDDDDD; "/>
                </div>
            </#if>


            <#if content??>
                <div class="layui-form-item">
                    <input type="text" name="urlName" autocomplete="off" class="layui-input" style="height: 36px; font-size: 18px; padding-left: 16px; border: 1px solid #DDDDDD;"/>
                </div>
                <#else>
                <div class="layui-form-item">
                    <input type="text" name="urlName" placeholder="友好的 URL 链接名称" style="height: 36px; font-size: 18px; padding-left: 16px; border: 1px solid #DDDDDD; "/>
                </div>
            </#if>




            <div class="layui-form-item">

                <input type="hidden" id="xx"/>
                <p id='model'>
                    <span><i class="layui-icon" style="font-size: 26px; color: darkgrey;">&#xe64c;</i></span>&nbsp;&nbsp;&nbsp;
                    <span><i class="layui-icon" style="font-size: 26px; color: darkgrey;">&#xe641;</i></span>
                </p>

                <#list menuList as menu>
                    <#if menu.title == "index">
                        <input type="button" name="menuId" title="${menu.title}" value="${menu.id}" checked>
                        <#else>
                        <input type="button" name="menuId" title="${menu.title}" value="${menu.id}">
                    </#if>
                </#list>
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
//        异步请求加载分类
       $(document).ready(function () {

       });
    </script>

    <script type="text/javascript">
        var mSpan = $("#model").find("span");
        $(mSpan[0]).addClass("on");
        $("#xx").val("0");
        mSpan[0].onclick = function () {
            if($(mSpan[0]).hasClass("on")){
                $(mSpan[0]).removeClass();
                $(mSpan[1]).addClass("on");
                $("#xx").val("1");
            }else{
                $(mSpan[0]).addClass("on");
                $(mSpan[1]).removeClass();
                $("#xx").val("0")

            }
        };

        mSpan[1].onclick = function () {
            if($(mSpan[1]).hasClass("on")){
                $(mSpan[1]).removeClass();
                $(mSpan[0]).addClass("on");
                $("#xx").val("0");

            }else{
                $(mSpan[1]).addClass("on");
                $(mSpan[0]).removeClass();
                $("#xx").val("1");
            }
        }


//        for(var i = 0; i < $(mSpan).length; i++){
//            mSpan[0].onclick = function () {
//                var siblings = this.parentNode.children;
//                for(var j=0; j < siblings.length; j++){
//                    siblings[j].removeClass();
//                }
//                this.addClass("on");
//            }
//        }
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