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
    <div>
        <table style="border: solid 1px gray;" align="center" width="80%">
            <thead>
            <tr>
                <th>文章标题</th>
                <th>状态</th>
                <th>发布时间</th>
                <th>评论数</th>
                <th>点击量</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#list page as content>
            <tr>
                <td>${content.title}(${(content.createDt*1000)?c?number?number_to_datetime})</td>
                <td>${content.state}</td>
                <td>fdd</td>
                <td>dd</td>
                <td>dd</td>
                <td>dd</td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
</body>

</html>