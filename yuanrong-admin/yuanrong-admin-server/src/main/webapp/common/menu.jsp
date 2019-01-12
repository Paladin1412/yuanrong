<%--
  Created by IntelliJ IDEA.
  User: zhonghang
  Date: 2018/4/19
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false"  language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
</head>
<body>
<aside class="Hui-aside">
    <div class="menu_dropdown bk_2">
        <c:forEach items="${menus}" var="firstMenu">
             <c:if test="${ firstMenu.parentMenuId ==0 and firstMenu.typeIndex == 1}">
                 <c:forEach items="${menus}" var="secondMenu">
                     <dl>
                         <c:if test="${firstMenu.systemMenuId == secondMenu.parentMenuId and secondMenu.typeIndex == 1}">
                             <dt>
                                 <i class="Hui-iconfont">${secondMenu.menuIco}</i>
                                    ${secondMenu.menuName}
                                 <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
                             </dt>
                             <dd>
                                 <ul>
                                    <c:forEach items="${menus}" var="thirdMenu">
                                        <c:if test="${thirdMenu.parentMenuId ==secondMenu.systemMenuId and thirdMenu.typeIndex == 1 }">
                                            <li>
                                                <a data-href="${thirdMenu.menuPath}?first=t" data-title="${thirdMenu.menuName}" href="javascript:void(0)">${thirdMenu.menuName}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                 </ul>
                             </dd>
                        </c:if>
                     </dl>
                 </c:forEach>
             </c:if>
        </c:forEach>
    </div>
</aside>
</body>
</html>
