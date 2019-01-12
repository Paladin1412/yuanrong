<#include "../commonFragments/basic/meta.ftl"/>
	<title>需求详情_原创作者_圆融内容交易服务中心</title>
	<meta http-equiv="keywords" content='广告主发布需求,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业' />
	<meta http-equiv="description" content="★广告主发布需求☆圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业,圆融由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化、证券化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />
<#include "../commonFragments/basic/css_link.ftl"/>
	<link rel="stylesheet" href="${request.contextPath}/css/contentBank/tab.css?${mdc_version}">
    <link rel="stylesheet" href="${request.contextPath}/css/demandHall/demand_work1.css?${mdc_version}">
    <link rel="stylesheet" href="${request.contextPath}/css/demandHall/demand_author1.css?${mdc_version}">
    <style>
        .layer-open {
            position: fixed;
            left: 50%;
            top: 50%;
            margin-left: -391px;
            margin-top: -170px;
        }
    </style>
</head>
<body>
<input type="hidden" id="authorId" value="${id}">

    <#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
	<#--<#include "../commonFragments/basic/top.ftl"/>-->
<#--预览-->
<div class="container-demand-preview hide">
    <img src="" alt="">
</div>
<div class="wrap">
    <div class="demand-detail-wrap fixedClear">
        <div class="container-left">
            <#include "./demandDetailCommon/stepAuthor.ftl"/>
            <div>
                <#--状态1-->
                <div class="container-demand-detail1 hide">
                    <div class="container-demand-info">
                        <#--<ul>-->
                            <#--<li>-->
                                <#--<div>￥20000</div>-->
                                <#--<span>招募预算<span class="num-unit">&nbsp;(元)</span></span>-->
                            <#--</li>-->
                            <#--<li class="line"></li>-->
                            <#--<li>-->
                                <#--<div>10</div>-->
                                <#--<span>征稿数量<span class="num-unit">&nbsp;(件)</span></span>-->
                            <#--</li>-->
                            <#--<li class="line"></li>-->
                            <#--<li>-->
                                <#--<div>16</div>-->
                                <#--<span>已投稿数<span class="num-unit">&nbsp;(件)</span></span>-->
                            <#--</li>-->
                            <#--<li class="line"></li>-->
                            <#--<li>-->
                                <#--<div>8天6时30分</div>-->
                                <#--<span>剩余时间</span>-->
                            <#--</li>-->
                        <#--</ul>-->
                    </div>
                    <div class="container-demand-detail">
                        <div class="container-demand-detail-title">
                            <span>需求详情</span>
                        </div>
                        <div class="container-demand-detail-list fixedClear">
                            <#--<ul>-->
                                <#--<li>-->
                                    <#--<span>表现形式 :</span>-->
                                    <#--<span>不限</span>-->
                                <#--</li>-->
                                <#--<li>-->
                                    <#--<span>字数要求 :</span>-->
                                    <#--<span>3000-5000字</span>-->
                                <#--</li>-->
                                <#--<li>-->
                                    <#--<span>时长要求 :</span>-->
                                    <#--<span>15秒-60秒</span>-->
                                <#--</li>-->
                                <#--<li class="create-require">-->
                                    <#--<span>创作要求 :</span>-->
                                    <#--<span>主要征收都市两性社会现实类短篇小说，包括婚姻恋爱、家庭伦理、职场社会、都市悬疑等题材的小说。公众号受众主体为都市女性，在行文过程请注意契合女性读者的特征文章分为上下篇发布，总字数5000-12000字，请布置好故事的悬念，在上篇吸引读者的好奇心，结局最好有反转，故事以情节取胜</span>-->
                                <#--</li>-->
                                <#--<li>-->
                                    <#--<span>参考样稿 :</span>-->
                                    <#--<span>https://mp.weixin.qq.com/s/FQFGQV9DmFyZl2kakJrWdQ</span>-->
                                <#--</li>-->
                                <#--<li>-->
                                    <#--<span style="float: left;line-height: 67px;">征稿素材 :</span>-->
                                    <#--<dl class="imgDl">-->
                                        <#--<dt class="imgType"></dt>-->
                                        <#--<dd class="imgHtml">.txt</dd>-->
                                    <#--</dl>-->
                                    <#--<dl class="imgDl">-->
                                        <#--<dt class="imgType"></dt>-->
                                        <#--<dd class="imgHtml">.doc</dd>-->
                                    <#--</dl>-->
                                <#--</li>-->
                            <#--</ul>-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <#include "./demandDetailCommon/rightSlider.ftl"/>
        <div class="container-bottom hide">
            <div><span>报名须知</span></div>
            <div>
                <ul class="demandType1">
                    <li>1.报名后将由广告主确认是否选用。一旦确定选用才进行写稿。</li>
                    <li>2.稿件需要支持3次修改。</li>
                    <li>3.稿件的费用以报名时应约价格为准。</li>
                </ul>
                <ul class="demandType3 hide">
                    <li>1.报名后将由广告主确认是否选用。一旦确定选用才沟通执行。</li>
                    <li>2.原创内容的需要支持3次修改。</li>
                    <li>3.发布费用以报名时应约价格为准。</li>
                </ul>
                <div class="submit-pay">
                    <div>￥200</div>
                    <a href="javascript:void(0);"><span>我要报名</span></a>
                </div>
            </div>
        </div>
    </div>
</div>
	<#include "../commonFragments/basic/footer.ftl"/>
<!-- 热线电话 -->
	<#include "../commonFragments/hotline.ftl"/>
</body>
<#include "../commonFragments/basic/js_script.ftl"/>
<script type="text/javascript" src="${request.contextPath}/plugins/jquery.pagination.js?${mdc_version}"></script>
<script src="${request.contextPath}/js/demandHall/demand_author1.js?${mdc_version}"></script>
</html>
