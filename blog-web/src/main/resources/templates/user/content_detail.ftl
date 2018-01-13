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
        <h1 style="padding-top: 32px;padding-bottom: 32px; padding-left: 16px;"><a href="/" style="color: black; text-decoration: none">码农全家桶</a></h1>
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
                <a class="navbar-brand" href="/" style="font-size: 18px;">码农全家桶</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="#" style="font-size: 16px;">关于不荒 <span class="sr-only">(current)</span></a></li>
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
            </div>
        </div>
    </nav>

    <div class="row">
        <div class="col-lg-12">

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
        </div>
    </div>


</div>

</body>


</html>