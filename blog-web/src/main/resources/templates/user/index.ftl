<!DOCTYPE html>
<html>
<head>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">
        body{
            background-color: #F4F4F4;
        }
    </style>

</head>
<body>
<div class="container">
    <div class="row">
        <h1 style="padding-top: 32px;padding-bottom: 32px; padding-left: 16px;">码农全家桶</h1>
    </div>
    <nav class="navbar navbar-default" style="background-color: white">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#" style="font-size: 18px;">码农全家桶</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#" style="font-size: 16px;">关于不荒 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#" style="font-size: 16px;">好友</a></li>
                    <li><a href="#" style="font-size: 16px;">分享</a></li>
                    <li><a href="#" style="font-size: 16px;">推荐书单</a></li>
                    <li><a href="#" style="font-size: 16px;">碎语</a></li>
                    <li><a href="#" style="font-size: 16px;">日志列表</a></li>
                    <li><a href="#" style="font-size: 16px;">ISSUES</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#" style="font-size: 16px;">RSS</a></li>
                    <li><a href="#" style="font-size: 16px;">微博</a></li>
                    <li><a href="#" style="font-size: 16px;">知乎</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <div class="row">
        <div class="col-lg-9">
            <div class="panel panel-default">
                <div class="panel-body" style="font-size: 16px; line-height: 24px;color: #666666">
                    <span>Simple-Blog</span> 是一个利用 SpringBoot, SpringDataJpa, Maven 以及 Freemarker 开发的一个项目，所有新学的知识都会在这上面展开。
                    <span style="color: red">注意：这套系统本来是准备给自己用的，因为比较符合自己的习惯</span>！如果你们要使用的话，难免会出现
                    一些不可描述的 Bug，如果有兴趣的话，可以来我的 <a href="https://github.com/selfassu/simple-blog" target="_blank"> Github</a>上看看，另外如果对你的胃口的话，给我一个 Star 鼓励鼓励我可好？非常感谢！
                </div>
            </div>


            <#list contents as content>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h2><a href="#" style="text-decoration: none">${content.title}</a></h2>

                        <p class="text-left" style="font-family: Georgia,serif; font-size: 16px; color: gray">
                            ${(content.createDt*1000)?c?number?number_to_datetime}
                        </p>
                        <hr/>

                        <div style="font-size: 16px;line-height: 24px;font-family: STHeiti,serif">
                            ${content.content}
                        </div>
                    </div>
                </div>


            </#list>

            <div class="panel panel-default">
                <div class="panel-body text-center" style="font-size: 18px;">
                    <ul class="list-inline" style="font-family: Georgia, serif">
                        <li><a href="" style="text-decoration: none">上一页</a></li>
                        <li><a href="" style="text-decoration: none">2</a></li>
                        <li><a href="" style="text-decoration: none">下一页</a></li>
                        <li><a href="" style="text-decoration: none">尾页</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-lg-3">

            <div class="panel panel-default">
                <div class="panel-body" style="font-size: 16px;">
                    <p>不荒原名李启文，生于 <span style="font-family: Georgia, serif">1993-9-24，24</span>岁，英文 ID：selfassu。</p>
                    <p>● 于 <span style="font-family: Georgia,serif">16</span> 年 <span style="font-family: Georgia,serif">3</span> 月毕业于「湖北民族学院科技学院」。</p>
                    <p>● 现居住于<a href="">广东 ● 深圳 ● 南山区</a></p>
                    <p>● 自毕业后，一直在现公司担任「Android 开发工程师」，虽然是从事的是「Android 开发」，但是我对后台也有这浓厚的兴趣，因此在
                    公司也会做一些后台相关的项目。下一份工作期待能够成为一名真正的后端开发工程师。</p>
                    <p>● 在 <a href="https://github.com/selfassu" style="text-decoration: none;" target="_blank">Github</a> 上积极参与开源社区。</p>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-body">
                    <ul class="list-inline">
                        <li><a href="" style="font-size: 16px;">标签</a> </li>
                        <li><a href="" style="font-size: 16px;">标签2</a> </li>
                        <li><a href="" style="font-size: 16px;">标签3</a> </li>
                        <li><a href="" style="font-size: 16px;">标签4</a> </li>
                        <li><a href="" style="font-size: 16px;">标签5</a> </li>
                        <li><a href="" style="font-size: 16px;">标签6</a> </li>
                        <li><a href="" style="font-size: 16px;">标签7</a> </li>
                        <li><a href="" style="font-size: 16px;">标签8</a> </li>
                        <li><a href="" style="font-size: 16px;">标签9</a> </li>
                        <li><a href="" style="font-size: 16px;">标签10</a> </li>
                        <li><a href="" style="font-size: 16px;">标签11</a> </li>
                        <li><a href="" style="font-size: 16px;">标签12</a> </li>
                        <li><a href="" style="font-size: 16px;">标签13</a> </li>
                        <li><a href="" style="font-size: 16px;">标签14</a> </li>
                        <li><a href="" style="font-size: 16px;">标签15</a> </li>
                        <li><a href="" style="font-size: 16px;">标签16</a> </li>
                    </ul>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <ul class="list-unstyled" style="font-size: 16px;font-family: Georgia,serif; text-decoration: none">
                        <li><a href="">2017年1月</a> (20) </li>
                        <li><a href="">2017年1月</a> (20) </li>
                        <li><a href="">2017年1月</a> (20) </li>
                        <li><a href="">2017年1月</a> (20) </li>
                        <li><a href="">2017年1月</a> (20) </li>
                        <li><a href="">2017年1月</a> (20) </li>
                        <li><a href="">2017年1月</a> (20) </li>
                        <li><a href="">2017年1月</a> (20) </li>
                        <li><a href="">2017年1月</a> (20) </li>
                        <li><a href="">2017年1月</a> (20) </li>
                        <li><a href="">2017年1月</a> (20) </li>
                        <li><a href="">2017年1月</a> (20) </li>
                        <li><a href="">2017年1月</a> (20) </li>
                        <li><a href="">2017年1月</a> (20) </li>
                        <li><a href="">2017年1月</a> (20) </li>
                        <li><a href="">2017年1月</a> (20) </li>
                    </ul>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-body">
                    <ul class="list-unstyled" style="font-size: 16px;font-family: Georgia,serif; text-decoration: none">
                        <li><a href="">有用的连接1</a></li>
                        <li><a href="">有用的连接2</a></li>
                        <li><a href="">有用的连接3</a></li>
                        <li><a href="">有用的连接4</a></li>
                        <li><a href="">有用的连接5</a></li>
                        <li><a href="">有用的连接6</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>


</div>

</body>


</html>