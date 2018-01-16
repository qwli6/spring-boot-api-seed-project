<!DOCTYPE html>
<html lang="en">
<head>
    <title>用户登录</title>
    <link rel="stylesheet" href="/static/admin/css/login.css"/>
</head>
<body>
<div class="login-dlg">
    <!-- ajax 提交表单，提交之前验证表单信息是否完整  return checkForm(); 阻止表单提交-->
    <form method="post" id="loginForm" autocomplete="off" onsubmit="return checkForm();">
        <input type="hidden" name="_csrf" value="1234567890"> <!-- 防止 csrf 攻击-->
        <input name="username" type="text" placeholder="username"/>
        <input name="password" type="password" placeholder="password"/>
        <button id="btn-submit">Login</button>
    </form>
    <#--显示错误消息-->
    <#--<div class="exception-msg">用户名密码错误</div>-->
</div>

<script src="/static/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    function checkForm() {
        //登录前检查，返回 true 才提交表单
        var val = $("input[username]").val();
        alert("val:" + val);
        if(val === null || val.trim("") === ""){
            alert("用户名不能为null");
            return false;
        }

        return false;


    }
//    var blog = new $.blog();
//    function checkForm() {
//        //post 方式提交表单
//        blog.post({
//            url: '/login',
//            data: $("#loginForm").serialize(),
//            success:function(result){
//                if(result && result.success){
//                    //登录成功，重定向到首页
//                    window.location.href='/admin/main'
//                }else{
//                    blog.alertError(result.msg || "登录失败");
//                }
//            }
//        });
//        return false;
//    }
</script>
</body>
</html>
