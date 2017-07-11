<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3 align="center">编辑客户</h3>
<!-- enctype =" multipart/form-data  表示该页面可以是可以上传图片的"-->
<form action="<c:url value='/customer/updateCustomer.action'/>" method="post" enctype="multipart/form-data">
    <!-- 隐藏域-->
    <input type="hidden" name="cid" value="${customer.cid}"/>
    <table align="center">

        <tr>
            <td width="100px">客户名称</td>
            <td width="40%">
                <input type="text" name="name" value="${customer.name}"/>
            </td>
            <td align="left">
                <label id="nameError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>客户性别</td>
            <td>
                <input type="radio" name="sex" value="male" id="male" <c:if test="${customer.sex eq 'male'}"/>checked="checked"/>
                <label for="male">男</label>
                <input type="radio" name="sex" value="female" id="female" <c:if test="${customer.sex eq 'female'}"/> checked="checked"/>
                <label for="female">女</label>
            </td>
            <td>
                <label id="genderError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>手机</td>
            <td>
                <input type="text" name="phone" id="phone" value="${customer.phone}"/>
            </td>
            <td>
                <label id="phoneError" class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td>
                <input type="text" name="email" id="email" value="${customer.email}"/>
            </td>
            <td>
                <label id="emailError"class="error">&nbsp;</label>
            </td>
        </tr>
        <tr>
            <td>描述</td>
            <td>
                <textarea rows="5" cols="30" name="memo">${customer.memo}</textarea>
            </td>
            <td>
                <label id="discriptionError" class="error">&nbsp;</label>
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td>上传客户图片</td>--%>
            <%--<td>--%>
                <%--<c:if test=" ${customer.pic != null} ">--%>
                    <%--<img src="/pic/${customer.pic}" width="100px" height="100px"/>--%>
                <%--</c:if>--%>
            <%--</td>--%>
            <%--<td><input type="file" name="pictureFile"/></td>--%>
        <%--</tr>--%>
        <tr>
            <td></td>
            <td>
                <input type="submit" name="submit" value="编辑客户"/>
                <input type="reset" name="reset"/>
            </td>
        </tr>
    </table>
</form>


</body>
</html>
