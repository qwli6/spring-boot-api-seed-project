<!DOCTYPE html>
<html lang="en" style="background-image: url('/static/image/bg.png'); background-size: cover">
<head>
    <title>用户登录</title>
    <#include "meta.ftl"/>
    <style type="text/css">
        .panel-shadow {
            -moz-box-shadow: 0 0 10px 0 rgba(39, 49, 65, 0.1);
            -webkit-box-shadow: 0 0 10px 0 rgba(39, 49, 65, 0.1);
            box-shadow: 0 0 10px 0 rgba(39, 49, 65, 0.1);
        }
        .bg-overlay {
            -moz-border-radius: 6px 6px 0 0;
            -webkit-border-radius: 6px 6px 0 0;
            background-color: rgba(47, 51, 62, 0.3);
            border-radius: 6px 6px 0 0;
            height: 100%;
            left: 0;
            position: absolute;
            top: 0;
            width: 100%;
        }
        .input-border {b1.png
            font-size: 14px;
            width: 100%;
            height: 40px;
            border-radius: 0;
            border: none;
            border-bottom: 1px solid #dadada;
        }

        .bg-img > h3 {
            text-shadow: 0 2px 3px #555;
            color: #cac9c8;
        }
    </style>

</head>
<body style="background-image: url('/static/image/bg.png'); background-size: cover">
<div style="margin: 0 auto; padding-bottom: 0; padding-top: 10%; width: 380px;">
    <div class="panel panel-color panel-danger panel-pages panel-shadow">
        <div class="panel-heading bg-img">
            <div class="bg-overlay"></div>
            <h3 class="text-center m-t-10"> XBlog 后台管理</h3>
        </div>
        <div class="panel-body">
            <!-- ajax 提交表单，提交之前验证表单信息是否完整-->
            <form class="form-horizontal m-t-20" method="post" id="loginForm" onsubmit="return checkForm()">
                <div class="form-group">
                    <div class="col-xs-12">
                        <input class=" input-lg input-border" name="username" type="text" required=""
                               placeholder="账号"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-12">
                        <input class=" input-lg input-border" name="password" type="password" required=""
                               placeholder="密码"/>
                    </div>
                </div>
                <#--<div class="form-group">-->
                    <#--<div class="col-xs-12">-->
                        <#--<div class="checkbox checkbox-danger">-->
                            <#--<input id="checkbox-signup" name="remeber_me" type="checkbox"/>-->
                            <#--<label for="checkbox-signup">记住我</label>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
                <div class="form-group text-center m-t-40">
                    <div class="col-xs-12">
                        <button class="btn btn-danger btn-lg btn-rounded btn-block w-lg waves-effect waves-light"
                                style="box-shadow: 0 0 4px #868282;" type="submit">登&nbsp;录
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/limonte-sweetalert2/6.4.1/sweetalert2.min.js"></script>
<script src="/static/admin/js/common.js"></script>
<script type="text/javascript">
    var blog = new $.blog();
    function checkForm() {
        //post 方式提交表单
        blog.post({
            url: '/login',
            data: $("#loginForm").serialize(),
            success:function(result){
                if(result && result.success){
                    //登录成功，重定向到首页
                    window.location.href='/admin/main'
                }else{
                    blog.alertError(result.msg || "登录失败");
                }
            }
        });
        return false;
    }
</script>
</body>
</html>
