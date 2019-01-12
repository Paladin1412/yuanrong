<div class="login-pop-wrap" id="js-login-pop-wrap">
    <div class="login-pop-opa"></div>
    <div class="login-pop-con-wrap">
        <div class="login-pop-tit-wrap">
            <div>登陆</div>
            <span id="js-close-login-pop-btn">X</span>
        </div>
        <div class="login-input-wrap">
            <input type="text" class="tel" id="tel" placeholder="请输入手机号码" onkeyup="this.value=this.value.replace(/[^0-9]/ig,'')" maxlength="11">
        </div>
        <div class="login-input-wrap">
            <input type="text" class="code" id="code" placeholder="请输入验证码" onkeyup="this.value=this.value.replace(/[^0-9]/ig,'')" maxlength="4">
            <span id="js-code-btn">获取验证码</span>
        </div>
        <div class="login-input-wrap">
            <div class="login-pop-btn" id="js-login-btn">登陆</div>
        </div>
    </div>
</div>

<#--  未登录弹框   -->
<div class="pop-wrap" id="js-pop-login-wrap" style="display:none">
    <div class="pop-opa"></div>
    <div class="pop-con-wrap">
        <div class="pop-tit-wrap">
            <span  class="pop-tit">提示</span>
            <span class="pop-close-btn"><i class="iconfont">&#xe624;</i></span>
        </div>
        <div class="pop-main-wrap">
            <div class="pop-main" style="text-align: center; padding-top: 30px;">您尚未登录，请登录！</div>
        </div>
        <div class="pop-btn-wrap">
            <div class="pop-btn pop-btn-blue" data-id="" data-type="" style="margin-left: 0px;" onclick="goLoginPage()">登录</div>
        </div>
    </div>
</div>
<#--  点击分页提示只能看第一页信息   -->
<div class="pop-wrap" id="js-pop-pagination-wrap" style="display:none">
    <div class="pop-opa"></div>
    <div class="pop-con-wrap">
        <div class="pop-tit-wrap">
            <span  class="pop-tit">提示</span>
            <span class="pop-close-btn"><i class="iconfont">&#xe624;</i></span>
        </div>
        <div class="pop-main-wrap">
            <div class="pop-main" style="text-align: center; padding-top: 30px;">当前仅显示3页内容，登录可查看更多！</div>
        </div>
        <div class="pop-btn-wrap">
            <div class="pop-btn pop-btn-blue" data-id="" data-type="" style="margin-left: 0px;" onclick="goLoginPage()">登录</div>
        </div>
    </div>
</div>