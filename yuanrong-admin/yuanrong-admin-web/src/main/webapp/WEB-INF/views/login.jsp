<%--
  Created by IntelliJ IDEA.
  User: zhonghang
  Date: 2018/5/19
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>登录</title>
</head>
<body>
<form action="/login" method="post">
    用户名：<input type="text" name="username" id="username">
    密码：<input type="password" name="password" id="password">
    <input type="submit" value="登录">
</form>
</body>
</html>
