<#include "../commonFragments/basic/meta.ftl"/>
	<title>原创内容交易_内容创作者_内容生产创作_圆融内容交易服务中心</title>
	<meta http-equiv="keywords" content='原创内容交易,内容银行,内容生产创作,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业,原创内容' />
	<meta http-equiv="description" content="原创内容交易,内容银行,内容生产创作,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业,原创内容,圆融由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化、证券化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />

	<#include "../commonFragments/basic/css_link.ftl"/>
	
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/css/iconfont/iconfont.css?${mdc_version}">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/css/commonFragments/tab.css?${mdc_version}">
	<link rel="stylesheet" href="${request.contextPath}/css/contentBank/cb_common.css?${mdc_version}">
	<link rel="stylesheet" href="${request.contextPath}/css/contentBank/con_bank.css?${mdc_version}">
	
</head>
<body>
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
	<div  class="content_bank wrap">
		<#--  <#include "common/con_bank_common.ftl"/> 	  -->
		<!--找内容 短视频-->
		<div  id='con-short-control-box'>
			<!-- 图文的圆融指数排序 -->
			<!-- <div class='sort'>
				<div class='sort-icon'>
					<span class='icon-shangjiantou iconfont'></span>
					<span class='icon-xiajiantou iconfont'></span>
				</div>
				<div class='sort-txt'>圆融指数从高到低</div>
			</div> -->
			<!-- 找内容 - 短视频 -->
			<ul id='contet-video-box'>
				<div class='empty'>
					<#include '/commonFragments/list_no_data.ftl'/>
				</div>
			    <!-- 要渲染的DOM元素 -->
				<!-- <li>
					<div class='cv-img-box'>
						<a href="">
							<img src='${request.contextPath}/images/test.png'>
						</a>
						<div class='vide-btn'></div>
					</div>
					<div class='video-info'>
						<p>
							<span class='user'>
								<a href="">用户</a>
							</span>
							<span class='user-classify'>分类:
								<strong>健身</strong>
							</span>
						</p>
						<p class='describe-video'>
							<a href="">厉害了，我的三峡</a>
						</p>
					</div>
					<div  class='video-handle'>
							<p>在线议价</p>
							<p>咨询</p>
					</div>
				</li> -->

			</ul>
			<!-- 找内容 - 图文 -->
			<!-- <ul id='contet-txt-box'> -->
				<!-- <li>
					<div class='text-img-box'>
						<a href="">
							<img src="${request.contextPath}/images/logo.png">
						</a>
					</div>
					<div class='text-info-box'>
						<p class='text-title'>
							<a href="" target='_blank'> 
								<h1>满足日常同行需求 城市代步纯电动汽车哪家强</h1>
							</a>
						</p>
						<p class='text-describe'>
							随着消费者环保意识的增强以及相关政策的推动之下，纯电动汽车迎来了一个飞速发展时期。除了一些面向高端的车型之外，由于补贴后的价格更加实惠，一些用于日常通行代步的纯电动车更…
						</p>
						<p class='text-info'>
							<span>分类：
								<strong>汽车</strong>
							</span>
							<span>标签： 
								<strong>科技、长安、北京现代、北京现代，长安</strong>
							</span>
							<span>圆融指数:
								<strong>93</strong>
							</span>
						</p>
						<p class='text-user'>
							<a href="" class='text-user-image'>
								<img src="${request.contextPath}/images/logo.png">
							</a>
							<a href="" class='text-user-name'>
								<span>用户名</span>
							</a>
							
						</p>
					</div>
					<div class='icon-box'>
						 <div><img src=""></div>
						 <div><img src=""></div>
					</div>
				</li> -->
			<!-- </ul> -->
			<div class='pagination-wrap'>
			</div>
		</div>

		<!--<div id="div_imqq1" class="" style="visibility: hidden">-->
			<!--<span class="btn-text" style="cursor: pointer;">客服</span>-->
		<!--</div>-->
        <a class="btn" target=blank href="tencent://message/?Menu=yes&uin=11305802&Site=http://www.yuanrongbank.com/&Service=300&sigT=45a1e5847943b64c6ff3990f8a9e644d2b31356cb0b4ac6b24663a3c8dd0f8aa12a545b1714f9d45"><span id="div_imqq1"></span></a>
		<!--<a class="btn" target=blank href="tencent://message/?Menu=yes&uin=11305802&Site=80fans&Service=300&sigT=45a1e5847943b64c6ff3990f8a9e644d2b31356cb0b4ac6b24663a3c8dd0f8aa12a545b1714f9d45"><span id="div_imqq1"></span></a>-->
	</div>  
	<#include "../commonFragments/basic/footer.ftl"/> 
	<!-- 热线电话 -->
	<#include "../commonFragments/hotline.ftl"/>
</body>
<#include "../commonFragments/basic/js_script.ftl"/>
<script type="text/javascript" src="${request.contextPath}/plugins/jquery.pagination.js?${mdc_version}"></script>
<script type="text/javascript" src="${request.contextPath}/js/contentBank/cb_common/cb_common.js?${mdc_version}"></script>
<script type="text/javascript" src="${request.contextPath}/js/contentBank/con_bank.js?${mdc_version}"></script>
<#--  <script id="qd2852157214fd16a3d12b0859fec4f7d84f8468d8ec" src="https://wp.qiye.qq.com/qidian/2852157214/fd16a3d12b0859fec4f7d84f8468d8ec" charset="utf-8" async defer></script>
<script id="qd285215721493225a8bd25e764cc3182273d46e0b08" src="https://wp.qiye.qq.com/qidian/2852157214/93225a8bd25e764cc3182273d46e0b08" charset="utf-8" async defer></script>
<script id="qd2852157214dd472ddbd983dd8fdfbf9a53b2c654be" src="https://wp.qiye.qq.com/qidian/2852157214/dd472ddbd983dd8fdfbf9a53b2c654be" charset="utf-8" async defer></script>  -->
</html>