<%--
  Created by IntelliJ IDEA.
  User: zhonghang
  Date: 2018/6/8
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <jsp:include page="/common/inc.jsp"></jsp:include>
    <title>错误信息</title>
</head>
<blockquote class="layui-elem-quote"> 域名：${theBugInfo.domain}</blockquote>
<blockquote class="layui-elem-quote"> 请求路径：${theBugInfo.requestUrl}</blockquote>
<blockquote class="layui-elem-quote"> 用户IP：${theBugInfo.ip}</blockquote>
<blockquote class="layui-elem-quote">操作人：${theBugInfo.operUser}</blockquote>
<blockquote class="layui-elem-quote">错误标题：${theBugInfo.errorTitle}</blockquote>
<blockquote class="layui-elem-quote">错误详情：${theBugInfo.errorDescription}</blockquote>
<blockquote class="layui-elem-quote">请求参数：${theBugInfo.parameter}</blockquote>
<blockquote class="layui-elem-quote">创建时间：<fmt:formatDate value="${theBugInfo.createdTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></blockquote>
</body>
</html>
