<#include "../commonFragments/basic/meta.ftl"/>
	<title>征稿投稿_网络赚钱_需求大厅_圆融内容交易服务中心</title>
	<meta http-equiv="keywords" content='征稿投稿,网络赚钱,需求大厅,投稿赚钱' />
	<meta http-equiv="description" content="征稿投稿,网络赚钱,需求大厅,投稿赚钱,圆融内容交易服务中心由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心,内容交易,圆融内容交易,圆融平台,圆融内容交易服务平台,内容创作,内容生产,写作赚钱,自媒体征稿,投稿赚钱,创作者创作赚钱,内容交易平台,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：克劳瑞，一道自媒体，稿稿，乐观号，微播易/微博易,,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />
    <meta name="keywords" content='征稿投稿,网络赚钱,需求大厅,投稿赚钱' />
    <meta name="description" content="征稿投稿,网络赚钱,需求大厅,投稿赚钱,圆融内容交易服务中心由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心,内容交易,圆融内容交易,圆融平台,圆融内容交易服务平台,内容创作,内容生产,写作赚钱,自媒体征稿,投稿赚钱,创作者创作赚钱,内容交易平台,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：克劳瑞，一道自媒体，稿稿，乐观号，微播易/微博易,,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />

<#include "../commonFragments/basic/css_link.ftl"/>
	<link rel="stylesheet" href="${request.contextPath}/css/contentBank/tab.css?${mdc_version}">
	<link rel="stylesheet" href="${request.contextPath}/css/demandHall/demand_hall.css?${mdc_version}">
</head>
<body>
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
<div class="wrap">
    <div class="demand-wrap">
        <!-- 这是内容银行发布需求的导航 -->
		<#--<#include "common/tab.ftl"/>-->
        <!-- 内容银行的发布需求 -->
        <div class="hall-con-wrap">
            <div class="query-form">
                <div class="demand-query-type">
                    <span>类型:</span>
                    <span>
                        <span class="active">全部</span>
                    </span>

                    <span>
                        <span>原创征稿</span>
                    </span>
                    <span>
                        <span>内容定制</span>
                    </span>
                    <span>
                        <span>营销分发</span>
                    </span>
                </div>
                <#--<div class="demand-query-status">-->
                    <#--<span>状态:</span>-->
                    <#--<span>-->
                        <#--<span class="active">全部</span>-->
                    <#--</span>-->
                    <#--<span>-->
                        <#--<span>招募中</span>-->
                    <#--</span>-->
                    <#--<span>-->
                        <#--<span>已完成</span>-->
                    <#--</span>-->
                <#--</div>-->
            </div>
            <div class="demand-middle">
                <div class="demand-container">
                    <div style="width:870px;">
                        <#include "../commonFragments/list_no_data.ftl"/>
                    </div>

                    <ul class="demand-list">
                    </ul>
                </div>
                <div class="demand-help trans-all-2" id="js-demand-help">
                    <div>
                        <span>玩转圆融</span>
                        <span class="link-help" style="cursor:pointer;"></span>
                    </div>
                    <ul>
                        <li class="link-help">
                            <div>
                                <div>原创征稿</div>
                                <div>Original</div>
                                <div>works</div>
                            </div>
                            <div>我有稿件，怎样投稿赚钱？</div>
                        </li>
                        <li class="made-type link-help">
                            <div>
                                <div>内容定制</div>
                                <div>Content</div>
                                <div>customization</div>
                            </div>
                            <div>我文笔棒，怎样更好变现？</div>
                        </li>
                        <li class="marketing-type link-help">
                            <div>
                                <div>营销分发</div>
                                <div>Marketing</div>
                                <div>distribution</div>
                            </div>
                            <div>我有账号，如何有偿发广告？</div>
                        </li>
                        <#--<li class="IP-type link-help">-->
                            <#--<div>-->
                                <#--<div>IP代理权</div>-->
                                <#--<div>IP agency</div>-->
                                <#--<div>power</div>-->
                            <#--</div>-->
                            <#--<div>圆融IP代理权需求流程说明</div>-->
                        <#--</li>-->
                    </ul>
                </div>
            </div>


            <div class="pagination-wrap fixedClear" style="width:870px;">
                <div id="pagination" class="fixedClear"></div>
            </div>
			<#--<#include "../commonFragments/list_no_data.ftl"/>-->
        </div>
        <#--<div class="demand-fix-bottom-wrap" style="">-->
            <#--<div>发布需求，让卖家找您》<div class="demand-publish-btn" id="js-pub-btn">发布需求</div></div>-->
        <#--</div>-->
    </div>
</div>
<span style="display: none;">
征稿投稿_网络赚钱_需求大厅_圆融内容交易服务中心
征稿投稿,网络赚钱,需求大厅,投稿赚钱
征稿投稿,网络赚钱,需求大厅,投稿赚钱,圆融内容交易服务中心由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心,内容交易,圆融内容交易,圆融平台,圆融内容交易服务平台,内容创作,内容生产,写作赚钱,自媒体征稿,投稿赚钱,创作者创作赚钱,内容交易平台,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：克劳瑞，一道自媒体，稿稿，乐观号，微播易/微博易,,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态
    </span>

	<#include "../commonFragments/basic/footer.ftl"/>
<!-- 热线电话 -->
	<#include "../commonFragments/hotline.ftl"/>
</body>
<#include "../commonFragments/basic/js_script.ftl"/>
<script type="text/javascript" src="${request.contextPath}/plugins/jquery.pagination.js?${mdc_version}"></script>
<script src="${request.contextPath}/js/demandHall/demand_hall.js?${mdc_version}"></script>
</html>
