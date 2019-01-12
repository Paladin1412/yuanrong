<#include "../commonFragments/basic/meta.ftl"/>
<title>订单支付_圆融内容银行交易服务中心</title>
<meta http-equiv="keywords" content='创作者文章详情,圆融,内容银行,内容创作者,内容创业' />
<meta http-equiv="description" content="★创作者文章详情☆圆融,内容银行,内容创作者,内容创业,圆融由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化、证券化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />
<#include "../commonFragments/basic/css_link.ftl"/>

<style>
    #content .pay-content {
        padding: 30px 30px 35px 30px;
    }
    #content .pay-order {
        background: #f4f6f9;
        padding: 30px 20px 30px 40px;
    }
    #content .pay-code {
        border: 5px solid #ddeaf9;
        padding: 20px 20px 20px 25px;
    }
    #pay-roll2:before {
        content:"";
        width:0;
        height:0;
        position:absolute;
        left:0;
        top:0;
        border-top:solid 15px transparent;
        border-left:solid 10px #fff;
        border-bottom:solid 15px transparent;
    }

    #content .nav-pay .pay-step .triangle {
        width:0;
        height:0;
        border-width:28px 0 28px 28px;;
        border-style:solid;
        border-color:black;/*透明 灰 透明 透明 */
        margin:40px auto;
        position:relative;
    }
    #pay-roll1 {
        background: #7cbc5a;
        color: #fff;
        position: relative;
    }
    #pay-roll2 {
        background: #f4f6f9;
        color: #64676a;
        position: relative;
    }
    .pay-order-number {
        color: #64676a;
        font-size: 18px;
    }
    .pay-order-number > span {
        color:#4895e7;
    }
    .pay-order-money {
        float: right;
        font-size: 14px;
        color: #64676a;
    }
    .pay-order-money > span {
        font-size: 30px;
        color: #fd8226;
    }
    .pay-order-tip {
        padding-top: 10px;
    }
    .pay-order-disc {
        color: #64676a;
        font-size: 14px;
    }
    .pay-order-disc > span {
        color: #4895e7;
    }
    .pay-code-item1 {
        padding-bottom:15px;
    }
    .pay-code-item1-triangle {
        width: 0;
        height: 0;
        border-top: 3px solid transparent;
        border-bottom: 3px solid transparent;
        border-right: 7px solid #fc6620;
        display: inline-block;
        vertical-align: middle;
    }
    .pay-code-item1-recommend {
        background: #fc6620;
        width: 40px;
        height: 20px;
        font-size: 12px;
        color: #fff;
        margin-left: -4px;
        display: inline-block;
        text-align: center;
        line-height: 20px;
        border-radius: 2px;
    }
    .pay-code-item2 {
        border: 10px solid #f4f6f9;
        width: 225px;
        margin: 0 auto;
    }
    .pay-code-item2 > div {
        padding: 14px 20px;
        background: #456084;
    }
    .pay-code-item-scan {
        float: left;
        margin-right: 10px;
    }
    .pay-code-item1-name {
        font-size: 18px;
        font-weight: 500;
    }
    .pay-code-item1-disc {
        color: #8b8f92;
        margin-left: 15px;
    }
    .pay-code-item2-disc {
        color: #fff;
        font-size: 12px;
    }
    .pay-step-item1 {
        background: #eaf4e5;
        color: #899883;
    }
    .musicMove{
        -webkit-animation:musicMove 5s linear 0s infinite;
        animation:musicMove 5s linear 0s infinite;
        -webkit-animation-fill-mode:both;
        animation-fill-mode:both;
    }
    @keyframes musicMove{
        0%{
            -webkit-transform:rotate(0deg);
            transform:rotate(0deg);
        }
        100%{
            -webkit-transform:rotate(360deg);
            transform:rotate(360deg);
        }
    }

</style>
</head>
<body style="position:relative;">
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
<div class="wrap">
    <input type="hidden" id="info" value="${mergePay.orderRequest.outTradeNo}">
    <input type="hidden" id="nowTime" value="${systemNow}">

    <div id="content">
        <!--导航-->
        <#include "common/top.ftl"/>
        <div class="pay-content">
            <div class="pay-order">
                <div>
                    <span class="pay-order-number">订单编号: <span>${mergePay.orderRequest.outTradeNo}</span></span>
                    <span class="pay-order-money">支付金额: <span>${mergePay.totalMoney}</span> 元</span>
                    
                </div>
                <div style="padding-top: 10px;">
                    <span>订单个数：${mergePay.orderInfoBuyers?size}</span>
                </div>
                <#--  <div class="pay-order-tip">
                    <span class="pay-order-disc">您的订单已提交成功! 请在<span></span>完成支付, 逾期将取消订单!</span>
                </div>  -->
            </div>
            <div class="pay-code">
                <div class="pay-code-item1">
                    <img src="../../../images/pay/weixin_pay.png" alt="">
                    <span class="pay-code-item1-name">微信支付</span>
                    <div class="pay-code-item1-triangle"></div>
                    <span class="pay-code-item1-recommend">推荐</span>
                    <span class="pay-code-item1-disc">亿万用户的选择, 更快更安全</span>
                </div>
                <div class="pay-code-item2">
                    <img class="pay-codeUrl" src="/pay/wx/createScanPayQrcodeMode?codeUrl=${wxPayUnifiedOrderResult.codeURL}&sideLength=200" alt="">
                    <div>
                        <img class="pay-code-item-scan" src="../../../images/pay/weixin_scan.png" alt="">
                        <span class="pay-code-item2-disc">请使用微信扫描二维码以完成支付</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#--  <div class="pop-wrap loading" id="" style="display: none;">
    <div class="pop-opa"></div>
    <div class="pop-con-wrap" style="background: none; width: 100%; margin-left: -50%; color: #fff;">
        <div  style="text-align:center;">
            <div class=" musicMove"><i class="iconfont icon-loading" style="font-size:60px; opacity: .8"></i></div>
            <span style="font-size:30px; padding-top: 20px; ">支付成功，系统正在处理中...</span>
        </div>
    </div>
</div>  -->
<div class="pop-wrap loading" id="" style="display:none;">
    <div class="pop-opa"></div>
    <div class="loading-gif-wrap">

        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div style="font-size:30px; padding-top: 50px; z-index:1000; width:400px;background:none;margin-left:-200px; color: #fff;">支付成功，系统正在处理中...</div>
    </div>

</div>
<#--<div class="loading" style="width:100%;height:969px;z-index:10000;background:black;position:absolute;top:0;opacity: .4;">-->
   <#--<div  style="text-align:center;margin-top: 250px;">-->
       <#--<div class=" musicMove"><i class="iconfont icon-loading" style="font-size:80px; color:blue;"></i></div>-->
       <#--<span style="font-size:30px;opacity:1">支付成功，系统正在处理中...</span>-->
   <#--</div>-->
<#--</div>-->
<#include "../commonFragments/basic/footer.ftl"/>
<!-- 热线电话 -->
<#--<#include "../commonFragments/hotline.ftl"/>-->
</body>
<#include "../commonFragments/basic/js_script.ftl"/>

<script type="text/javascript">

    // var nowTime = (new Date($('#nowTime').val())).getTime();
    //var  startTime = (new Date($('#nowTime').val().substr(0,10) + "T" + $('#nowTime').val().substr(11,8))).getTime()  ;

    //function initTime(endTime){
       // var endTime = (new Date($('#invalideTime').val().substr(0,10) + "T" + $('#invalideTime').val().substr(11,8) )).getTime();
        //startTime = startTime + 1000;
        //var time = endTime - startTime;

        //if(time <= 0) {
            //window.clearInterval(countDown);
            //$('.pay-order-money').hide();
            //$('.pay-order-disc').html('<span>您的订单已失效, 请重新购买!</span>');
            //$('.pay-codeUrl').hide();
            //clearInterval(time);
           // return ' ';
            //订单结束，不显示时间
        //}else {
            //var mm = Math.floor((time/1000/60)%60);
           // var ss = Math.floor((time/1000)%60);
           // var str = mm+'分'+ss+'秒';
           // $('.pay-order-disc span').text(str);
        //}

   // }
    //var countDown =  window.setInterval(initTime, 1000);

    var time = window.setInterval(payStatus, 3000);
    var order = $('#info').val();
    function payStatus() {
        $.ajax({
            url: '/pay/queryOrder',
            type: "post",
            data: {"orderSn": order},
            success: function(res){
                if(res.data.tradeState == 'SUCCESS'){
                    $('.loading').show();
                    setTimeout(function(){
                        window.location.href = '/pay/paySuccess?orderSn='+ order +'';
                    }, 3000);
                    clearInterval(time);
                }else {

                }
            }
        });
    }
</script>
</html>





