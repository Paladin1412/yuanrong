<#include "../commonFragments/basic/meta.ftl"/>
<title>支付成功_圆融内容银行交易服务中心</title>
<meta http-equiv="keywords" content='创作者文章详情,圆融,内容银行,内容创作者,内容创业' />
<meta http-equiv="description" content="★创作者文章详情☆圆融,内容银行,内容创作者,内容创业,圆融由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化、证券化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />
<#include "../commonFragments/basic/css_link.ftl"/>
<link rel="stylesheet" href="${request.contextPath}/css/contentBank/tab.css?${mdc_version}">
<!--<link rel="stylesheet" href="${request.contextPath}/css/contentBank/article_detail.css?${mdc_version}">-->


<style>
    #content .pay-content {
        padding: 30px 30px 35px 30px;
    }
    #content .pay-order {
        background: #f4f6f9;
        padding: 30px 20px 30px 40px;
        text-align: center;
        color: #303132;
        font-size: 20px;
        background: #effee4;
    }
    #content .pay-code {
        border: 5px solid #e7f5dc;
        padding: 50px 20px 65px 25px;
    }
    #content .pay-code ul li {
        padding-bottom: 15px;
    }
    #pay-roll1:after {
        content:"";
        width:0;
        height:0;
        position:absolute;
        top:0;
        right:-10px;
        border-top:solid 15px transparent;
        border-left:solid 10px #eaf4e5;
        border-bottom:solid 15px transparent;
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
    #pay-roll3:after {
        content:"";
        width:0;
        height:0;
        position:absolute;
        right:-10px;
        top:0;
        border-top:solid 15px transparent;
        border-left:solid 10px #eaf4e5;
        border-bottom:solid 15px transparent;
    }

    #content .success-icon {
        display: inline-block;
        width: 25px;
        height: 25px;
        border-radius: 50%;
        background: #79bc53;
        vertical-align: middle;
        position: relative;
    }
    #content .success-icon:after {
        box-sizing: content-box;
        content: "";
        border: 3px solid #fff;
        border-left: 0;
        border-top: 0;
        height: 10px;
        left: 9px;
        position: absolute;
        top: 5px;
        transform: rotate(45deg);
        width: 4px;
        transition: transform .15s ease-in .05s;
        transform-origin: center;
    }
    #pay-roll2{
        background:#7cbc5a;color:#fff;position:relative;
    }
    #pay-roll1{
        background:#f4f6f9;color:#eaf4e5;position:relative;
    }
    #pay-roll0{
        background:#eaf4e5;color:#899883;position:relative;
    }
    
</style>
</head>
<body style="position:relative;">
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
<div class="wrap">
    <div id="content" style="overflow: hidden;">
        <!--导航-->
        <#include "common/top.ftl"/>
        <div class="pay-content">
            <div class="pay-order">
                <span class="success-icon"></span>
                <span>
                    恭喜您, 支付成功!
                </span>
            </div>
            <div class="pay-code">
                <ul style="color:#82868a;margin-left: 450px;">
                    <li>
                        <span>支付流水号 : </span>
                        <span style="color:#4895e7;font-size:16px;">${payWater.outTradeNo}</span>
                    </li>
                    <#--<li>
                        <span>支付账号 : </span>
                        <span style="color:#303132;">138888888888</span>
                    </li>-->
                    <li>
                        <span>支付渠道 : </span>
                        <span style="color:#303132;">快捷支付—微信支付</span>
                    </li>
                    <li>
                        <span>支付金额 : </span>
                        <span style="color:#fd8226">￥${payWater.totalFee}</span>
                    </li>
                    <li>
                        <span>交易时间 : </span>
                        <span style="color:#303132;">${payWater.createdTime?string('yyyy-MM-dd HH:mm:ss')}</span>
                    </li>
                    <li>
                        <span>交易状态 : </span>
                        <span style="color:#303132;">支付成功</span>
                    </li>
                    <li>
                        <span class="look-order" style="color: #599de9;background: #f5faff;border-radius: 2px;border: 1px solid #ddeaf9;display:inline-block;width:80px;height:34px;line-height:34px;text-align:center;cursor:pointer;margin-top:30px;">查看订单</span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<#include "../commonFragments/basic/footer.ftl"/>
<!-- 热线电话 -->
<#--<#include "../commonFragments/hotline.ftl"/>-->
</body>
<#include "../commonFragments/basic/js_script.ftl"/>

<script>
    $(function(){
        $('.look-order').on('click', function(){
            window.location.href = centerUrl + 'buyer/order/orderList';
            //window.open(centerUrl + 'buyer/order/orderList');
        })
        //导航栏
        $('#js-nav-wrap').hide();
        $('.top-wrap').addClass('top-wrap-no-banner');
    })
</script>
</html>





