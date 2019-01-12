<#include "../commonFragments/basic/meta.ftl"/>
	<title>创作者写作赚钱_内容创作者网络赚钱_内容生产创作_圆融内容银行交易服务中心</title>
<meta http-equiv="keywords" content='创作者写作赚钱,内容创作者网络赚钱,内容生产创作赚钱' />
<meta http-equiv="description" content="创作者写作赚钱,内容创作者网络赚钱,内容生产创作赚钱,圆融内容交易服务中心由中国传媒大学,内容银行重点实验室成立,目的是构建内容生产、传播、变现、资产化的一站式交易平台,面向内容创作者、广告主、代理公司、投融资机构等产业各方,提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务,打造内容产业新生态.圆融内容银行交易服务中心,内容交易,圆融内容交易,圆融平台,圆融内容交易服务平台,圆融买家卖家后台登录,内容版权交易平台,内容知识产权,授权转载平台,创作者,自媒体,广告主,内容创作,内容生产,写作赚钱,自媒体征稿,投稿赚钱,创作者创作赚钱,内容交易平台,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易:短视频广告营销(秒拍,美拍,快手,抖音),微信变现,微博赚钱,类似：克劳瑞,一道自媒体,稿稿,乐观号,微播易/微博易,,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业）" />
<meta name="keywords" content='创作者写作赚钱,内容创作者网络赚钱,内容生产创作赚钱' />
<meta name="description" content="创作者写作赚钱,内容创作者网络赚钱,内容生产创作赚钱,圆融内容交易服务中心由中国传媒大学,内容银行重点实验室成立,目的是构建内容生产、传播、变现、资产化的一站式交易平台,面向内容创作者、广告主、代理公司、投融资机构等产业各方,提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务,打造内容产业新生态.圆融内容银行交易服务中心,内容交易,圆融内容交易,圆融平台,圆融内容交易服务平台,圆融买家卖家后台登录,内容版权交易平台,内容知识产权,授权转载平台,创作者,自媒体,广告主,内容创作,内容生产,写作赚钱,自媒体征稿,投稿赚钱,创作者创作赚钱,内容交易平台,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易:短视频广告营销(秒拍,美拍,快手,抖音),微信变现,微博赚钱,类似：克劳瑞,一道自媒体,稿稿,乐观号,微播易/微博易,,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业）" />


<#include "../commonFragments/basic/css_link.ftl"/>
 	<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/contentBank/tab.css?${mdc_version}">
	<link rel="stylesheet" href="${request.contextPath}/css/contentBank/creator.css?${mdc_version}">
	<link rel="stylesheet" href="${request.contextPath}/css/contentBank/original_work.css?${mdc_version}">
</head>
<body>
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
	<!-- 创作者 导航 -->
<div class="wrap">
	<div class="content_bank">
		
	<!-- 创作者和找内容公共导航 -->
		<#--  <#include "/contentBank/common/tab.ftl"/>   -->
		<div class='condition-wrap fixedClear'> 
			<!-- 搜索内容 -->
			<#--  <div class='author-search-wrap'>
				<div class='search-icon-wrap'>
					<div>
					<i class='iconfont icon-sousuo1'></i>
					<input id='search-val' type="" name="" placeholder='请输入关键词'><button id='search-btn'>搜索</button>
					</div>
					<button id='release-btn'>发布需求</button>
				</div>
			</div>  -->
			<!-- <ul id='hot-word-wrap'> 
				<li>父亲节</li>
			</ul> -->
			
			<div class="work-down-column-wrap hide" id="js-note-work-type">
				<img class="work-down-column-img" src="${request.contextPath}/images/ipTrade/tri_up.png" alt="">
				<div class="work-down-column-con"></div>
			</div> 
		  	<div  class='condition-nav fixedClear condition-nav-type fixedClear'>
		  		<span class="tit">作品类型</span>
		  		<ul id='useScenes' class="fixedClear" num=1> 
					<li id='0' class='active-nav'>原创未发布 </li>
		  			<li id='1' >原创已发布</li>
		  		</ul> 
				  <i class="iconfont icon-wenhao icon-un" id="js-icon-un"></i> <i class="iconfont icon-wenhao  icon-pub" id="js-icon-pub"></i>
		  	</div>
		  	<div  class='condition-nav'>
		  		<span class="tit">内容形式</span>
		  		<ul id='contentForm' num=2> 
		  			<li class='active-nav no-limit' id='' flag='1'>不限</li>
		  		</ul>
		  		<div class='more'><img src="${request.contextPath}/images/commonFragments/arr_small_down.png"></div>
		  	</div>
		  	<div  class='condition-nav'>
		  		<span class="tit">内容分类</span>
		  		<ul id='contentType' num=3> 
		  			<li class='active-nav no-limit' id='' flag='1'>不限</li>
		  		</ul> 
		  		<div class='more'><img src="${request.contextPath}/images/commonFragments/arr_small_down.png"></div>
		  	</div>
		  	<div  class='condition-nav'>
		  		<span class="tit price-pub-label" style="display:inline-block;width:56px;">转载价格</span>
		  		<ul id='create-price' num=4> 
		  			<li class='active-nav no-limit' id='' flag='1'>
		  				<span>不限</span>
		  			</li>
		  			<!-- 买断价 -->
		  			<li class='buy-out'>
		  				<span>15</span>元以下
		  			</li>
		  			<li class='buy-out'>
		  				<span>15-50</span>元
		  			</li>
		  			<li class='buy-out'>
		  				<span>50-100</span>元
		  			</li>
		  			<li class='buy-out'>
		  				<span>100-300</span>元
		  			</li>
		  			<li class='buy-out'>
		  				<span>300-500</span>元
		  			</li>
		  			<li class='buy-out'>
		  				<span>500</span>元以上
		  			</li>

		  			<!-- 转载价格 -->
		  			<li class='transhipment-price'>
		  				<span>10</span>元以下
		  			</li>
		  			<li class='transhipment-price'>
		  				<span>10-30</span>元
		  			</li>
		  			<li class='transhipment-price'>
		  				<span>30-50</span>元
		  			</li>
		  		</ul>
				<#include "../commonFragments/self_price.ftl"/>
		  	</div>
		  	<!-- <div  class='condition-nav '>
		  		<span class="tit">营销场景</span>
		  		<ul id='create-use-time' num=5> 
		  			<li class='active-nav no-limit' id='' flag='1'>
		  				不限
		  			</li>
		  		</ul>
		  		<div class='more'><img src="${request.contextPath}/images/commonFragments/arr_small_down.png"></div>
		  	</div> -->
		    
		</div>
		<div class='original-content-list-wrap'>
			<div class='title-wrap'>
				<span>作品列表</span>
				<p>
					<span  id='synthesis' class='check-codition active-condition'>综合</span>
                    <span>|</span>
					<span id='new'  class='check-codition'>最新</span>
					<span>|</span>
					<span id='ascending'  class='check-codition'>价格从低到高 <i class="iconfont icon-jiantou1"></i></span>
					<span>|</span>
					<span id='descending'  class='check-codition'>价格从高到低 <i class="iconfont icon-jiantoushang-copy"></i></span>
				</p>
			</div>
			<ul id='original-works-list-wrap'>
				<!-- <li class='fixedClear'>	
					<div class='img-wrap'>
						<img src="${request.contextPath}/images/contentBank/author-video.jpg">
					</div>
					<div class='works-info-wrap'>
						<strong>为了混进男朋友的“世界杯”,我准备了一些新的装备</strong>
						<div class='works-time'>
							<span class='work-author'>作者:UOHIGIRL</span>
							<span>
								2018-02-25 10:01:16
							</span>
							<div class='float-auhtor'>
								 <p class='author-float-info'>
								 	<img src='${request.contextPath}/images/contentBank/author-video.jpg'>
								 	<span>谷大白话</span>
								 </p>
								 <p>
								 	创作形式：<span>漫画、文章</span>	
								 </p>
								 <p>
								 	擅长领域；<span>汽车、历史</span>
								 </p>
								 <p>
								 	作品数量：<span>80</span>
								 </p>
								 <p>
								 	人<span style='visibility:hidden;'>哈哈</span>气：<span>52343</span>
								 </p>
								 <p class='jump_detail'>
								 	<a href="">查看详情</a>
								 </p>
							</div>
						</div>
						<p class='item-describe'>
							据说，我们25岁的时候挣多少钱，5岁的视乎就的决定了，据说，我们25岁的时候挣多少钱，5岁的视乎就的决定了，据说，我们25岁的时候挣多少钱，5岁的视乎就的决定了，
						</p>
						<p class='work-label-wrap'>
							<span>
								<span class='work-label'>分类:</span>
								<span classs='work-con'>美妆时尚</span>
							</span>
							<span>
								<span class='work-label'>营销场景:</span>
								<span classs='work-con'>端午节</span>
							</span>
							<span>
								<span class='work-label'>高频词:</span>
								<span classs='work-con'>唇膏、欧莱雅、唇釉</span>
							</span>
							<span>
								<span class='work-label'>圆融指数:</span>
								<span classs='work-con'>93</span>
							</span>
							<span>
								<span class='work-label'>交易次数:</span>
								<span classs='work-con'>3</span>
							</span>
						</p>
					</div>
					<div class='works-right-wrap'>
						<p>	
							<span class='label'>转载使用:</span>
							<span class='price'>￥9.9</span>
						</p>
						<p >
							<button>加入购物车</button>
						</p>
						<p>
							<button>立即购买</button>
						</p>
					</div>
				</li> -->
			</ul>
		</div>
		<div class='pagination-wrap'></div>
		<#include "../commonFragments/pagination_note.ftl"/>
	</div>
</div>
	<#include "../contentBank/example_pop.ftl"/> 
	<#include "../commonFragments/basic/footer.ftl"/> 	
	<#include "../commonFragments/basic/loading.ftl"/>
	<!-- 热线电话 -->
	<#include "../commonFragments/hotline.ftl"/>
</body>
<#include "../commonFragments/basic/js_script.ftl"/>
<script type="text/javascript" src="${request.contextPath}/plugins/jquery.pagination.js?${mdc_version}"></script>
<script type="text/javascript" src="${request.contextPath}/js/contentBank/original_work.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/contentBank/example_pop.js"></script>
</html>
