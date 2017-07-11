<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 他的作用是为本页面所有的表单和超链接指定显示内容的框架-->
    <base target="main">
    <title>My JSP 'top.jsp' starting page</title>
</head>
<body style="text-align: center;">
    <h1>客户关系管理系统</h1>
    <a href="${pageContext.request.contextPath}/add.jsp" target="main">添加客户</a>
    <a href="${pageContext.request.contextPath}/customer/list" target="main">全部客户</a>
    <a href="${pageContext.request.contextPath}/query.jsp">高级查询</a>

</body>
</html>
