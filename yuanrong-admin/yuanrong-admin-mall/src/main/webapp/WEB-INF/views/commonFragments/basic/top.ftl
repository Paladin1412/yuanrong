<div class="nav-login-wrap fixedClear">
    <div class="top-wrap" id="js-top-wrap">
        <input type="hidden" value="${request.contextPath}" id="js-ajax-domain">
        <#if userInfoJSON??>
            <input type="hidden" value="${userInfoJSON.mobile}" id="user-phone">
        </#if>
        <div class="fixedClear banner-nav-top-con-wrap">
            <div class="top-lt-wrap">
                <div class="logo-img">
                    <a href="${request.contextPath}/">
                        <img title="圆融内容交易服务中心-传媒大学内容银行实验室"  src="${request.contextPath}/images/indexImg/logo.png"/>
                    </a>
                </div>
                <div class="logo-tit">
                    <a class="fixedClear" href="${request.contextPath}/">
                        <img title="圆融内容交易服务中心-传媒大学内容银行实验室"  src="${request.contextPath}/images/indexImg/logo_u.png"/>
                        
                        <div class="logo-tit-wrap">
                            <p class="logo-tit-p1">中国传媒大学</p>
                            <p class="logo-tit-p2">内容银行重点实验室</p>   
                        </div>
                    </a>
                </div>
            </div>
            <div class="top-rt-wrap fixedClear" id="js-login-wrap">
                <i class="iconfont icon-wode3"></i>
                <p class="phone"></p>
            
            <#--  <span class="">${userInfoJSON.mobile}</span>  -->
                <!--//media-->
                <span class="register-tit" data-type="0">我是买家</span>
                <span class="register-line"></span>
                <!--//ads-->
                <span class="login-tit" data-type="1" id="js-longin-tit">我是卖家</span>
                <!-- <img style="display: none;" src="${request.contextPath}/images/index/avatar.png" alt=""> -->
                <!-- <i class="login-out"><a href="javascript:void(0);">退出</a></i> -->
                <a href="javascript:void(0);" class="login-out">退出</a>
            </div>
            <div class="top-md-wrap">
                <ul class="fixedClear" id="js-nav-wrap">
                    <li class="nav-index trans-all-3">
                        <a href="${request.contextPath}/">首页</a>
                    </li>
                    <li class="nav-content-bank trans-all-3">
                        <a href="${request.contextPath}/contentBank/author_images.html">内容交易</a>
                    </li>
                    <li class="nav-ip-trade trans-all-3">
                        <a href="${request.contextPath}/ipTrade/ip_trade.html">IP交易</a>
                    </li>
                    <li class="nav-demand-hall trans-all-3">
                        <a href="${request.contextPath}/demandHall/demand_hall.html">需求大厅</a>
                    </li>
                    <li class="nav-ip-evaluation trans-all-3">
                        <a href="${request.contextPath}/ipEvaluation/ip_evaluation.html">IP评估</a>
                    </li>
                    <li class="nav-about-us trans-all-3">
                        <a href="${request.contextPath}/aboutUs/about_us.html">关于我们</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    
    <!-- 登陆弹框 -->
    <div class="login-pop-wrap" id="js-login-pop-wrap">
        <div class="login-pop-opa"></div>
        <div class="login-pop-con-wrap">
            <span id="" class="close-login-pop-btn"><i class="iconfont">&#xe624;</i></span>
            <div class="login-pop-tit-wrap">
                <div>登 录</div>
            </div>
            <div class="login-input-wrap">
                <input type="text" class="tel" id="tel" autocomplete='tel' placeholder="请输入手机号码" onkeyup="this.value=this.value.replace(/[^0-9]/ig,'')" maxlength="11">
            </div>
            <div class="login-input-wrap login-code-wrap fixedClear">

                <input type="text" class="code" id="code" placeholder="请输入图形验证码" onkeyup="this.value=this.value.replace(/[^A-Za-z0-9]/ig,'')" maxlength="4">
                <img id="img" alt="验证码" src="${request.contextPath}/registeredUserInfo/validateCode" onclick="changeImgVal()"/>
            </div>
            <div class="login-input-wrap login-code-wrap fixedClear">

                <input type="text" autocomplete='off' class="code" id="phone-code" placeholder="请输入短信验证码" onkeyup="this.value=this.value.replace(/[^0-9]/ig,'')" maxlength="4">
                <input class="code-btn" id="js-code-btn" type="button" value="获取验证码">
            </div>
            <div class="login-input-wrap">
                <div class="login-pop-btn" id="js-login-btn">登 录</div>
            </div>
        </div>
    </div>
    <!-- 用户已登录弹框 -->
    <div class="login-pop-wrap login-done-wrap" id="js-login-done-wrap">
        <div class="login-pop-opa"></div>
        <div class="login-pop-con-wrap">
            <span id="" class="close-login-pop-btn"><i class="iconfont">&#xe624;</i></span>
            <div class="login-done-con-wrap">
                <div class="login-done-con-top">
                    <h1><img src="${request.contextPath}/images/commonFragments/login_tick.png" alt="">登录成功</h1>
                    <p>更多功能，敬请期待...</p>
                </div>
            </div> 
            <div class="login-done-con-bot">
                <p class="p-busi">商务合作 : 17611113579</p>
                <p class="p-coope">市场合作 : 13141231249</p>
            </div>
            <div class="login-input-wrap">
                <div class="service">
                    <span class="qq">联系客服</span>
                </div>
            </div>
        </div>
    </div>
    <span id="cnzz_stat_icon_1255513477"></span>
</div>
<#include "../login_pop.ftl"/> 
<script type="text/javascript">
    // 刷新图片
    function changeImgVal() {
        var imgSrc = $("#img");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", changeUrl(src));
    }
    //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
    function changeUrl(url) {
        var timestamp = (new Date()).valueOf();
        var index = url.indexOf("?",url);
        if (index > 0) {
            url = url.substring(0, url.indexOf("?"));
        }
        url = url + "?timestamp=" + timestamp;
        return url;
    }
</script>
<script src="${request.contextPath}/plugins/layui/layui.all.js"></script>
<!--<script src="${request.contextPath}/js/common_domain.js"></script>-->