
<div class="site-nav-wrap">
    <div class="inner-wrap site-con-wrap fixedClear">
        <div class="site-login-info-wrap">
            <ul class="site-info-lt-wrap fixedClear trans-all-2" id="js-site-info-lt-wrap">
                <li class="site-info-li1 fixedClear">
                
        <#if userInfoJSON??>
            <input type="hidden" value="${userInfoJSON.mobile}" id="user-phone">
        <#else>
        
        </#if>
                    <span class="site-login-span0 trans-all-2 color-blue"><a class="trans-all-2 color-blue" target="_blank" id="js-site-no-login"> Hi, 请登录！ </a></span>
                    <div class="site-login-span1 trans-all-2 color-blue" style="display:inline-block;">
                        您好,
                        <div style="display:inline-block;padding:0 2px 0 4px;width:104px; cursor: pointer;" class="active-name">
                            <i id="js-site-login-info"></i>
                            <img class="down-img trans-all-2" src="${request.contextPath}/images/commonFragments/arr_down.png" alt="">
                            <img class="up-img trans-all-2" src="${request.contextPath}/images/commonFragments/arr_up.png" alt="">
                        </div>
                    </div>
                    <ul class="site-info-bd-wrap"  id="js-site-info-bd-wrap">
                        <#--<li class=" trans-all-2" id="js-site-login-name"></li>-->
                        <li class=" trans-all-2" style="padding:0 5px;"><a id="js-change-pwd" target="_blank" >修改密码</a></li>
                        <li class=" trans-all-2" style="padding:0 5px;" id="js-login-out"><a href="javascript:;">退出登录</a></li>
                    </ul>
                </li>
                <li><span class="site-login-span2 trans-all-2" id="js-site-register"> <a id="js-my-register" target="_blank">免费注册</a> </span></li>
                <li><span class="site-login-span3 trans-all-2"> 欢迎来到圆融内容交易平台！</span></li>
            </ul>
        </div>
        <ul class="site-quick-menu-wrap fixedClear">
            <li><a href="${request.contextPath}/cart/cart_article.html" target="_blank">选购车<span clas="color-blue"> <span class="color-blue" id="js-site-cart-num" >0</span></span></a></li>
            <li class="site-separator">|</li>
            <li class="site-has-downmenu-wrap fixedClear">
                <a id="js-buyer" href="javascript:;" target="_blank">我是买家<img class="down-img trans-all-2" src="${request.contextPath}/images/commonFragments/arr_down.png" alt=""><img class="up-img trans-all-2" src="${request.contextPath}/images/commonFragments/arr_up.png" alt=""></a>
                <ul class="site-quick-downmenu-wrap trans-all-2" id="">
                    <li class=" trans-all-2"><a id="js-center-buy-list" target="_blank" >已购买作品</a></li>
                    <li class=" trans-all-2"><a id="js-center-order-list" target="_blank" >我的订单</a></li>
                    <li class=" trans-all-2"><a id="js-my-request" target="_blank">我的需求</a></li>
                </ul>
            </li>
            <li class="site-separator">|</li>
            <li class="site-has-downmenu-wrap fixedClear">
                <a id="js-seller" target="_blank">我是卖家<img class="down-img trans-all-2" src="${request.contextPath}/images/commonFragments/arr_down.png" alt=""><img class="up-img trans-all-2" src="${request.contextPath}/images/commonFragments/arr_up.png" alt=""></a>
                <ul class="site-quick-downmenu-wrap trans-all-2" id="">
                    <#--  <li class=" trans-all-2"><a id="js-my-resource" target="_blank">我的资源</a></li>
                    <li class=" trans-all-2"><a id="js-my-order" target="_blank">我的订单</a></li>  -->
                    <li class=" trans-all-2"><a id="js-my-author" target="_blank">创作者管理</a></li>
                    <li class=" trans-all-2"><a id="js-my-work" target="_blank">作品管理</a></li>
                    <li class=" trans-all-2"><a id="js-my-account" target="_blank">账号管理</a></li>
                    <li class=" trans-all-2"><a id="js-my-order" target="_blank">订单管理</a></li>
                </ul>
            
            </li>
            <li class="site-separator">|</li>
            <li><a href="${request.contextPath}/playAround/play_around.html" target="_blank">玩转圆融</a></li>
            <li class="site-separator">|</li>
            <li><a href="" id="js-help-center" target="_blank">帮助中心</a></li>
            <li class="site-separator">|</li>
            <li><a href="${request.contextPath}/aboutUs/about_us.html" target="_blank">关于我们</a></li>
        </ul>
    </div>
</div>