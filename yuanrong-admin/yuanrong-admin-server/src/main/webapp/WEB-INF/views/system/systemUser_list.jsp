<%--
  Created by IntelliJ IDEA.
  User: zhonghang
  Date: 2018/4/21
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>系统用户列表</title>
    <jsp:include page="/common/inc.jsp"></jsp:include>
    <jsp:include page="/common/hui.jsp"></jsp:include>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 系统管理 <span class="c-gray en">&gt;</span>
    用户管理 <span class="c-gray en">&gt;</span> 用户列表
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>

<div class="page-container">

    <div class="text-c">
        用户名：<input type="text" id="userName" placeholder=" 用户名" style="width:150px" class="input-text">
        姓名：<input type="text" id="realName" placeholder=" 姓名" style="width:150px" class="input-text">
        状态：<select id="isLoginIndex" style="width:80px"class="input-text">
                    <option value="">[所有]</option>
                    <option value="1">正常</option>
                    <option value="0">停用</option>
              </select>
        <span class="btn btn-success" id="search_input" onclick="loadData();"><i class="Hui-iconfont">&#xe665;</i> 搜索</span>
    </div>
    <div id="tool">
        <div class="layui-btn-group">
            <button class="layui-btn layui-btn-sm"  title="新增用户" onclick="add()">
                <i class="layui-icon">&#xe654;</i>新增
            </button>
            <button class="layui-btn layui-btn-sm" title="导出搜索">
                <i class="layui-icon">&#xe601;</i>导出
            </button>
        </div>
        <hr>
    </div>
    <div class="mt-20">
        <table class="layui-table" lay-size="sm">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>用户名</th>
                    <th>姓名</th>
                    <th>是否可登录</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="tableData">

            </tbody>
        </table>
        <div><span id="info"></span><span id="paging"></span></div>

    </div>
</div>
</body>
<script src="/js/views/system/systemUser_list.js"></script>
</html>
