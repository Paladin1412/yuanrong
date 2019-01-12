<%--
  Created by IntelliJ IDEA.
  User: zhonghang
  Date: 2018/4/24
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>新增用户列表</title>
    <jsp:include page="/common/inc.jsp"></jsp:include>
    <jsp:include page="/common/hui.jsp"></jsp:include>
</head>
<body>
<div class="page-container">
    <form class="layui-form  layui-form-pane" style="align-content: center" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">真实姓名：</label>
            <div class="layui-input-inline">
                <input type="text" name="realName"  lay-verify="required" save="true" autocomplete="off" class="layui-input" >
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">用户名：</label>
                <div class="layui-input-inline">
                    <input type="text" name="userName" lay-verify="required" save="true" autocomplete="off" class="layui-input" >
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">密码：</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" lay-verify="required" save="true"  autocomplete="off" class="layui-input" >
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">重输密码：</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" lay-verify="required" autocomplete="off" class="layui-input" >
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

    </form>
</div>
<script type="application/javascript" src="/js/views/system/systemUser_save.js"></script>
</body>
</html>
