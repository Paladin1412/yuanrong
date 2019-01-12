<#include "../commonFragments/basic/meta.ftl"/>
<title>确认订单_圆融内容银行交易服务中心</title>
<meta http-equiv="keywords" content='创作者文章详情,圆融,内容银行,内容创作者,内容创业' />
<meta http-equiv="description" content="★创作者文章详情☆圆融,内容银行,内容创作者,内容创业,圆融由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化、证券化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />
<#include "../commonFragments/basic/css_link.ftl"/>
<link rel="stylesheet" href="${request.contextPath}/css/pay/order_confirm.css?${mdc_version}">

</head>
<body style="position:relative;">
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
<div class="wrap">
    <div id="content">
        <!--导航-->
        <#include "common/top.ftl"/>
        <style>
            #content .pay-header .pay-step .pay-roll1-before{
                border-left-color: #7cbc5a;
            }
            #content .pay-header .pay-step .pay-roll2-before{
                border-left-color: #f4f6f9;
            }
        </style>
        <div class="order-wrap">
            <div >
                
                <div style="min-height: 240px;">    
                    <table style="">
                        <thead>
                            <tr>
                                <td><div class="cell">序号</div></td>
                                <td><div class="cell">订单类型</div></td>
                                <td><div class="cell">订单内容</div></td>
                                <td><div class="cell">税前金额</div></td>
                                <td><div class="cell">税额</div></td>
                                <td><div class="cell">订单金额</div></td>
                            </tr>
                        </thead>
                        <tbody id="js-order-list-wrap">
                            <#--  <tr>
                                <td><div class="cell">订单总计</div></td>
                                <td><div class="cell">订单总计</div></td>
                                <td><div class="cell cell-order-wrap">订单总计</div></td>
                                <td><div class="cell">订单总计</div></td>
                                <td><div class="cell">订单总计</div></td>
                                <td><div class="cell">订单总计</div></td>
                            </tr>
                            <tr>
                                <td><div class="cell">订单总计</div></td>
                                <td><div class="cell">订单总计</div></td>
                                <td>
                                    <div class="cell cell-order-wrap fixedClear">
                                        <div class="order-detail-lt-wrap fixedClear">
                                            <div class="order-img-wrap">
                                                <img src="" alt="">
                                            </div>
                                            <div class="order-person-info-wrap">
                                                <p>999</p>
                                                <p>0000</p>
                                            </div>
                                        </div>
                                        <div class="order-detail-rt-wrap">
                                            <p>999</p>
                                            <p>0000</p>
                                        </div>
                                    </div>
                                </td>
                                <td><div class="cell">订单总计</div></td>
                                <td><div class="cell">订单总计</div></td>
                                <td><div class="cell">订单总计</div></td>
                            </tr>  -->
                        </tbody>
                    </table>
                </div>
                <div class="order-price-wrap">
                    <div class="price-item-wrap price-item-wrap1 fixedClear">
                        <div class="price-item-div1">订单总金额：</div>
                        <div class="price-item-div2"><span id="js-order-sum"></span></div>
                    </div>
                    <div class="price-item-wrap price-item-wrap2 fixedClear">
                        <div class="price-item-div1">总税额：</div>
                        <div class="price-item-div2"><span id="js-tax-sum"></span></div>
                    </div>
                    <div class="price-totle-wrap fixedClear">
                        <div class="price-item-div1">实付款：</div>
                        <div class="price-item-div2"><span id="js-pay-sum"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="order-btn-wrap fixedClear">
                <div class="order-back-btn" id="js-order-back-btn" style="display: none;">
                    <a id="js-back-btn"><span><i class="iconfont">&#xe609;</i></span> 返回上一页</a>
                </div>
                <div class="order-confirm-btn" id="js-order-confirm-btn">提交订单</div>
            </div>
        </div>
    </div>

</div>

<#include "../commonFragments/basic/footer.ftl"/>
<#include "../commonFragments/basic/loading.ftl"/>
<!-- 热线电话 -->
<#include "../commonFragments/hotline.ftl"/>
</body>
 <#include "../commonFragments/basic/js_script.ftl"/>
<script src="${request.contextPath}/js/pay/order_confirm.js?${mdc_version}"></script>
<script type="text/javascript">
    $('#js-pay-title').text('确认订单');
</script>
</html>

