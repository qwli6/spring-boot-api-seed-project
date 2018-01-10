<!DOCTYPE html>
<html lang="en">
<head>
    <title>我是主页</title>
    <#include "meta.ftl"/>
    <style>
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
    </style>
</head>
<body>
    <h1> 我是主页 body</h1>
</body>
<div>
    <button type="button" class="button1">这是按钮</button>
</div>
</html>