<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
</head>
<body>
    <h3 align="center">高级搜索</h3>
    <form action="<c:url value="/customer/search"/>" method="get">
        <table align="center" width="40%">
            <tr>
                <td width="100px">客户名称</td>
                <td width="40%">
                    <input type="text" name="customer.name">
                </td>
            </tr>
            <tr>
                <td>客户性别</td>
                <td>
                    <select name="customer.sex">
                        <option value="male">male</option>
                        <option value="female">female</option>
                    </select>
                </td>
            </tr>
            <tr>
            <td>手机</td>
            <td>
                <input type="text" name="customer.phone"/>
            </td>
            </tr>
            <tr>
                <td>邮箱</td>
                <td>
                    <input type="text" name="customer.email"/>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>
                    <input type="submit" value="搜索"/>
                    <input type="reset" value="重置"/>
                </td>
            </tr>

        </table>
    </form>

</body>
</html>
