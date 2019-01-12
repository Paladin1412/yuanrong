<#include "../commonFragments/basic/meta.ftl"/>
	<title>《${authorInfo.authorNickname !}》创作者简介_内容创作者_内容生产创作_《${authorInfo.authorNickname !}》作品列表_自媒体卖作品网络赚钱报价购买_圆融</title>
	<meta name="keywords" content='${authorInfo.authorNickname !}内容创作者简介,${authorInfo.authorNickname !}作品报价,${authorInfo.authorNickname !}作品购买,${authorInfo.authorNickname !}自媒体卖作品网络赚钱报价购买' />
	<meta name="description" content="${authorInfo.introduction !},《${authorInfo.authorNickname !} 》创作者简介_内容创作者_内容生产创作_${authorInfo.authorNickname !}作品列表_自媒体卖作品网络赚钱报价购买_圆融内容银行交易服务中心" />
	<meta http-equiv="keywords" content='${authorInfo.authorNickname !}内容创作者简介,${authorInfo.authorNickname !}作品报价,${authorInfo.authorNickname !}作品购买,${authorInfo.authorNickname !}自媒体卖作品网络赚钱报价购买' />
	<meta http-equiv="description" content="《${authorInfo.introduction !},《${authorInfo.authorNickname !} 》创作者简介_内容创作者_内容生产创作_${authorInfo.authorNickname !}作品列表_自媒体卖作品网络赚钱报价购买_圆融内容银行交易服务中心,圆融内容交易服务中心由中国传媒大学,内容银行重点实验室成立,目的是构建内容生产、传播、变现、资产化的一站式交易平台,面向内容创作者、广告主、代理公司、投融资机构等产业各方,提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务,打造内容产业新生态.圆融内容银行交易服务中心,内容交易,圆融内容交易,圆融平台,圆融内容交易服务平台,圆融买家卖家后台登录,内容版权交易平台,内容知识产权,授权转载平台,创作者,自媒体,广告主,内容创作,内容生产,写作赚钱,自媒体征稿,投稿赚钱,创作者创作赚钱,内容交易平台,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易:短视频广告营销(秒拍,美拍,快手,抖音),微信变现,微博赚钱,类似：克劳瑞,一道自媒体,稿稿,乐观号,微播易/微博易,,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业）" />

	<#include "../commonFragments/basic/css_link.ftl"/>
 	<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/iconfont/iconfont.css?${mdc_version}">
 	<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/contentBank/tab.css?${mdc_version}">
	<link rel="stylesheet" href="${request.contextPath}/css/contentBank/author_detail.css?${mdc_version}">
</head>
<body>
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
	<!-- 创作者 导航 -->
	<div class="wrap wrap-has-bread">
        <div style="background: #fbf0e9;"><p class='prompt'>创作者暂未上架,此页仅供创作者自已预览,其他人不可见</p></div>
		<div class="content_bank">
			<div class='author-content-wrap'>
			 	<div class='bread-crumbs-wrap nav-author'>
			 		<a href="${request.contextPath}/contentBank/author_images.html">找作者</a>
			 		<span>&gt;</span>
			 		<span style="color: #48494a">作者详情</span>
			 		<a href="${request.contextPath}/contentBank/author_images.html" class='back'><span class="small-icon-return">&lt;</span><span class="details-return-font">返回</span></a>
			 	</div>
			</div>
			<div class='author-info-wrap fixedClear'>
				<div class='author-left'>
					<div id='author-image'>
						<#--<img src='${request.contextPath}/images/contentBank/author-video.jpg'>-->
						<#if authorInfo.authorImg ?? && authorInfo.authorImg != "">
							<img src='${request.contextPath}${authorInfo.authorImg}'>
						<#else >
							<img src='${request.contextPath}/images/contentBank/author-video.jpg'>
						</#if>

					</div>
					<p>
						<#--<strong>谷大白话</strong>-->
                        <strong>${authorInfo.authorNickname !}</strong>

						<span>人气：<span style="color: #48494a;">${accessNum }</span></span>
						<span>作品数量：<span style="color: #48494a;">${authorInfo.proUpNum !}</span></span>
					</p>
				</div>
				<div class='author-right fixedClear'>
					<div class='info fixedClear'>
						<p><span class='label'>原创参考价：</span><span class='label-con price'><#if authorInfo.createdPrice ?? && authorInfo.createdPrice !=0>${authorInfo.createdPrice }<#else > 按需定价</#if></span></p>
						<p><span class='label'>创作用时：</span><span class='label-con create-time'>${authorInfo.authorCreationTime !}</span></p>
						<p><span class='label'>创作形式：</span><span class='label-con create-form '>${authorInfo.contentFormName !}</span></p>
						<p><span class='label'>擅长领域：</span><span class='label-con be-good-domain'>${authorInfo.categoryName !}</span></p>
                        <p class="long-width"><span class='label '>使用场景：</span><span class='label-con use-sence'>${authorInfo.scenesName !}</span></p>
					</div>
					<div class='describe fixedClear'>
						<span>作者简介：</span>
						<p>${authorInfo.introduction !}</p>
					</div>
					<ul class='label-style'>
                        <#if authorInfo.lableName ?? && authorInfo.lableName != "">
                            <#list authorInfo.lableName ? split(".") as name>
                                <li>${name}</li>
                            </#list>
                        </#if>
						<!-- <li>世俗</li>
						<li>猎奇</li> -->
					</ul>
				</div>
				<div class='btn-wrap'>
					<button class='car-btn'></button>
					<button class='order-btn' style="border:none;">立即预约</button>
				</div>
			</div>
			<div class='work-and-author-wrap fixedClear'>
				<div class='works-wrap'>
					<ul class='tab'>
						<li id='publish-wrok'>
							原创已发布
							<span></span>
						</li>
						<li  id='no-work'>
							原创未发布
							<span></span>
						</li>
					</ul>
					<ul id='catogary' class='fixedClear'>
						<!-- <li>全部</li>
						<li>文章</li>
						<li>视频</li>
						<li>漫画</li> -->
					</ul>
					<ul id='works-list-wrap'>
						<!-- <li class='fixedClear'>
							<div class='img-wrap'>
								<img src="${request.contextPath}/images/contentBank/author-video.jpg">
								<div class='master-work'>代表作</div>
								<div class='work-type'>视频</div>
							</div>
							<div class='works-info-wrap'>
								<strong>为了混进男朋友的“世界杯”,我准备了一些新的装备</strong>
								<p class='works-time'>
									<span>2018-04-25 10:01:16</span>
									<span>
										<span>转载使用：</span>
										<span class='reship-use'>￥9.9</span>
									</span>
								</p>
								<p class='item-describe'>
									据说,我们25岁的时候挣多少钱,5岁的视乎就的决定了,据说,我们25岁的时候挣多少钱,5岁的视乎就的决定了,据说,我们25岁的时候挣多少钱,5岁的视乎就的决定了,
								</p>
								<p class='work-label-wrap'>
									<span>
										<span class='work-label'>营销场景:</span>
										<span class='work-con'>端午节</span>
									</span>
									<span>
										<span class='work-label'>高频词:</span>
										<span class='work-con'>唇膏、欧莱雅、唇釉</span>
									</span>
									<span>
										<span class='work-label'>圆融指数:</span>
										<span class='work-con'>93</span>
									</span>
								</p>
							</div>
						</li> -->
					</ul>
					<div class='pagination-wrap'></div>
				</div>
				<div class='auhtor-wrap'>
					<p class='tab'>相似作者推荐   <span id='change-batch-btn'><i class='iconfont icon-forabatch changeBta'></i>换一批</span></p>
					<ul id='similar'>
						<!-- <li class='fixedClear'>
							<img src="${request.contextPath}/images/contentBank/author-video.jpg">
							<div>
								<strong>行尸走肥肉</strong>
								<br>
								<p>银河行业美女基金尽力管理有限基金</p>
							</div>
						</li> -->
					</ul>
				</div>	
			</div>
			<div id='js-cart-fix-bottom-wrap' class='js-cart-fix-bottom-wrap'>
				<div>
					<div class='left'>
						<#if authorInfo.authorImg ?? && authorInfo.authorImg != "">
							<img src='${request.contextPath}${authorInfo.authorImg}'>
						<#else >
							<img src='${request.contextPath}/images/contentBank/author-video.jpg'>
						</#if>
						<span>${authorInfo.authorNickname !}</span>
					</div>
					<div class='right'>
						<p>原创参考价： <strong class='price'>
								<#if authorInfo.createdPrice ?? && authorInfo.createdPrice !=0>${authorInfo.createdPrice }
								<#else > 按需定价</#if>
							</strong>
						</p>
						<button  class='car-btn'>加入选购车</button>
						<button class='order-btn'>立即预约</button>
					</div>
				</div>
			</div>
		</div>
		<#include "../contentBank/example_pop.ftl"/> 
	</div>
<span style="display: none;">
${authorInfo.authorNickname !}_创作者简介_内容创作者_内容生产创作_${authorInfo.authorNickname !}作品列表_自媒体卖作品网络赚钱报价购买_圆融
${authorInfo.authorNickname !}内容创作者简介,${authorInfo.authorNickname !}作品报价,${authorInfo.authorNickname !}作品购买,${authorInfo.authorNickname !}自媒体卖作品网络赚钱报价购买
${authorInfo.introduction !},${authorInfo.authorNickname !}_创作者简介_内容创作者_内容生产创作_${authorInfo.authorNickname !}作品列表_自媒体卖作品网络赚钱报价购买_圆融内容银行交易服务中心,圆融内容交易服务中心由中国传媒大学,内容银行重点实验室成立,目的是构建内容生产、传播、变现、资产化的一站式交易平台,面向内容创作者、广告主、代理公司、投融资机构等产业各方,提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务,打造内容产业新生态.圆融内容银行交易服务中心,内容交易,圆融内容交易,圆融平台,圆融内容交易服务平台,圆融买家卖家后台登录,内容版权交易平台,内容知识产权,授权转载平台,创作者,自媒体,广告主,内容创作,内容生产,写作赚钱,自媒体征稿,投稿赚钱,创作者创作赚钱,内容交易平台,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易:短视频广告营销(秒拍,美拍,快手,抖音),微信变现,微博赚钱,类似：克劳瑞,一道自媒体,稿稿,乐观号,微播易/微博易,,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业）
    </span>
	<#include "../commonFragments/basic/footer.ftl"/> 	
	<!-- 热线电话 -->
	<#include "../commonFragments/hotline.ftl"/>
</body>
<#include "../commonFragments/basic/js_script.ftl"/>
<script type="text/javascript" src="${request.contextPath}/plugins/jquery.pagination.js?${mdc_version}"></script>
<script type="text/javascript" src="${request.contextPath}/js/contentBank/author_detail.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/contentBank/example_pop.js"></script>
</html>
