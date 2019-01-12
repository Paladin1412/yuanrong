<#include "../commonFragments/basic/meta.ftl"/>
	<title>需求详情_圆融内容交易服务中心</title>
	<meta http-equiv="keywords" content='广告主发布需求,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业' />
	<meta http-equiv="description" content="★广告主发布需求☆圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业,圆融由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化、证券化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />
<#include "../commonFragments/basic/css_link.ftl"/>
	<link rel="stylesheet" href="${request.contextPath}/css/contentBank/tab.css?${mdc_version}">
    <link rel="stylesheet" href="${request.contextPath}/css/demandHall/demand_work1.css?${mdc_version}">
    <link rel="stylesheet" href="${request.contextPath}/css/iconfont/iconfont.css?${mdc_version}">
</head>
<body>
<input type="hidden" id="recId" value="${recid}">
	<#--<#include "../commonFragments/basic/top.ftl"/>-->
    <#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
    <#--预览-->
    <div class="container-demand-preview hide">
        <img src="" alt="">
    </div>
<div class="wrap">
    <div class="demand-detail-wrap fixedClear">
        <div class="container-left">
            <#include "./demandDetailCommon/step.ftl"/>
            <div class="container-demand-info">
                <ul>
                    <li>
                        <div></div>
                        <span><i style="font-size:14px;" class="icon-yingbi iconfont"></i>招募预算<span class="num-unit"</span></span>
                    </li>
                    <li class="line"></li>
                    <li>
                        <div></div>
                        <span><i style="font-size:18px;" class="icon-icon--copy iconfont"></i>征稿数量<span class="num-unit">&nbsp;(篇)</span></span>
                    </li>
                    <li class="line"></li>
                    <li>
                        <div></div>
                        <span><i style="font-size:18px;" class="icon-icon--copy iconfont"></i>已投稿数<span class="num-unit">&nbsp;(篇)</span></span>
                    </li>
                    <li class="line"></li>
                    <li>
                        <div></div>
                        <span><i class="icon-shijian iconfont"></i>剩余时间</span>
                    </li>
                </ul>
            </div>
            <div class="container-demand-detail">
                <div class="container-demand-detail-title">
                    <span>需求详情</span>
                </div>
                <div class="container-demand-detail-list">
                    <ul>
                    </ul>
                </div>
            </div>


        </div>
        <#include "./demandDetailCommon/rightSlider.ftl"/>
        <div class="container-bottom">
            <div><span>投稿须知</span></div>
            <div>
                <ul>
                    <li>1. 所投作品必须为原创，绝对没有抄袭、照搬、洗文等任何违背原创精神的行为。</li>
                    <li>2. 作品没有在其他平台上发布过（含个人平台在内的一切平台和媒体端）</li>
                    <li>3. 作品中所含图片必须无水印、无版权纠纷。</li>
                    <li>4. 作品一经录用，购买方拥有该稿件版权的著作权，包括但不仅限于独家使用权、改编权等。</li>
                </ul>
                <div class="submit-pay">
                    <div></div>
                    <a href="javascript:void(0);"><span>提交作品</span></a>
                </div>
            </div>
        </div>
    </div>
</div>
	<#include "../commonFragments/basic/footer.ftl"/>
<!-- 热线电话 -->
	<#include "../commonFragments/hotline.ftl"/>
    
<div id="qq-media"></div>
</body>
<#include "../commonFragments/basic/js_script.ftl"/>
<script src="${request.contextPath}/js/demandHall/demand_work1.js?${mdc_version}"></script>
</html>
