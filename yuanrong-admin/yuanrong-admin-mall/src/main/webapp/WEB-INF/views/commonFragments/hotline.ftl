<!-- 侧边栏 -->
<div class="side-wrap">
    <div class="side-con-wrap" id="js-side-con-wrap">
        <ul class="index-side" id="index-side">
            <li class="index-side-cart" id="js-index-cart-side">
                <i class="side-cart"></i>
                <div class="side cart-side-num-wrap" id="js-cart-side-num-wrap">
                    <div class="side-0-hover side-hover">

                    </div>
                </div>
                <div class="cart-side-num" id="js-cart-side-num"></div>
                <div id="js-add-cart-suc-note" class="add-cart-suc-note add-cart-suc-note-show"></div>
            </li>
            <li class="index-side-call">
                <i class="side-1"></i>
                <div class="side">
                    <div class="side-1-hover side-hover">
                        <p>合作</p>
                        <p>热线</p>
                    </div>
                </div>
                <div class="hot-line-empty fixedClear">
                    <div class="hot-line fl">
                        <p class="hot-line-service"><span class="side-4">客服热线</span></p>
                        <p class="call-number service-num">4008198818</p>
                        <!-- <p class="hot-line-cooperation"><span class=""><i class="iconfont">&#xe614;</i>市场合作</span></p>
                        <p class="call-number coope-num">13141231249</p> -->
                    </div>
                    <div class="hot-line-triangle fl"></div>
                </div>	
            </li>
            <li class="index-side-login" id="js-index-side-login" data-id="1">
                <i class="side-2"></i>
                <div class="side">
                    <div class="side-2-hover side-hover">
                        <p>登录</p>
                        <p>注册</p>
                    </div>
                </div>
            </li>
            <li class="index-side-qq">
                <i class="side-3"></i>
                <div class="side" id="side">
                    <div class="side-3-hover side-hover">
                        <p class="pp1"><a target="_blank" href="http://q.url.cn/cdPnbU?_type=wpa&qidian=true" style="color:#fff;">在线</a></p>
                        <p class="pp1"><a target="_blank" href="http://q.url.cn/cdPnbU?_type=wpa&qidian=true" style="color:#fff;">客服</a></p>
                    </div>
                </div>
            </li>
            <li class="index-side-code" >
                <i class="side-1"></i>
                <div class="side">
                    <div class="code-hover side-1-hover side-hover">
                        <p>二维码</p>
                    </div>
                </div>

                <div class="code-img fixedClear">
                    <div class="hot-line fl">
                        <img class="qr-img" src="../../../css/bgImg/commonFragments/shouxie_03.png" alt="">
                        <p style="height:30px;line-height:30px;font-size:14px;padding:0;text-align:center;">QQ手写群</p>
                        <#--border-bottom:1px solid #e9ebec-->
                        <p style="padding-bottom:9px;padding-left:0;text-align:center;">
                            <span style="font-size:12px;cursor:default;" id="js-qq-num">21112567</span>
                            <span style="display:inline-block;width:42px;height:24px;line-height:24px;text-align: center;background: #F6FAFF;
    border: 1px solid #DFEAF8;color: #4895E7;cursor: pointer;border-radius:2px;font-size:12px;" id="js-copy-qq" onclick="copyFun('#js-copy-qq', $('#js-qq-num').text())">复制</span>
                        </p>
                        <p style="width:108px;height:1px;background:#e9ebec;margin:0 auto;"></p>
                        <img class="qr-img" style="margin-top: 10px;" src="../../../css/bgImg/commonFragments/code-img_03.png" alt="">
                        <p style="padding: 6px 0 0; text-align: center; font-size: 14px;">关注圆融公众号</p>
                    </div>
                    <div class="code-img-triangle fl"></div>
                </div>
            </li>
            <#--  <li class="back-top">
                <div></div>
                <div class="side">
                    <div class="side-4-hover side-hover">
                        <p class="pp1">回到</p>
                        <p class="pp1">顶部</p>
                    </div>
                </div>
            </li>  -->
        </ul>
        <div class="back-top" id="js-back-top">
            <div></div>
            <div class="side">
                <div class="side-4-hover side-hover">
                    <p class="pp1">回到</p>
                    <p class="pp1">顶部</p>
                </div>
            </div>
        </div>
        <div class="side-cart-wrap hide-side-cart-wrap" id="js-side-cart-wrap">
            <#include "./cart_side.ftl"/>
        </div>
    </div>
</div>

<!--<div id="div_imqq1" class="" style="visibility: hidden">-->
    <!--<span class="btn-text" style="cursor: pointer;">客服</span>-->
<!--</div>-->

<!--企点客服DOM-->
<div id="qq2"></div>

<!-- WPA start -->
<#--  <script id="qd285215721426c8eea9f8628e77a18acb4ff47b0b7c" src="https://wp.qiye.qq.com/qidian/2852157214/26c8eea9f8628e77a18acb4ff47b0b7c" charset="utf-8" async defer></script>  -->
<!-- WPA end -->

