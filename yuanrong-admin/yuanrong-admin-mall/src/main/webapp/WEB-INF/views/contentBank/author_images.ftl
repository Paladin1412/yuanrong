<#include "../commonFragments/basic/meta.ftl"/>
	<title>创作者写作赚钱_内容创作者网络赚钱_内容生产创作赚钱_圆融内容银行交易服务中心</title>
<meta http-equiv="keywords" content='创作者写作赚钱,内容创作者网络赚钱,内容生产创作赚钱' />
<meta http-equiv="description" content="创作者写作赚钱,内容创作者网络赚钱,内容生产创作赚钱,圆融内容交易服务中心由中国传媒大学,内容银行重点实验室成立,目的是构建内容生产、传播、变现、资产化的一站式交易平台,面向内容创作者、广告主、代理公司、投融资机构等产业各方,提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务,打造内容产业新生态.圆融内容银行交易服务中心,内容交易,圆融内容交易,圆融平台,圆融内容交易服务平台,圆融买家卖家后台登录,内容版权交易平台,内容知识产权,授权转载平台,创作者,自媒体,广告主,内容创作,内容生产,写作赚钱,自媒体征稿,投稿赚钱,创作者创作赚钱,内容交易平台,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易:短视频广告营销(秒拍,美拍,快手,抖音),微信变现,微博赚钱,类似：克劳瑞,一道自媒体,稿稿,乐观号,微播易/微博易,,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业）" />
<meta name="keywords" content='创作者写作赚钱,内容创作者网络赚钱,内容生产创作赚钱' />
<meta name="description" content="创作者写作赚钱,内容创作者网络赚钱,内容生产创作赚钱,圆融内容交易服务中心由中国传媒大学,内容银行重点实验室成立,目的是构建内容生产、传播、变现、资产化的一站式交易平台,面向内容创作者、广告主、代理公司、投融资机构等产业各方,提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务,打造内容产业新生态.圆融内容银行交易服务中心,内容交易,圆融内容交易,圆融平台,圆融内容交易服务平台,圆融买家卖家后台登录,内容版权交易平台,内容知识产权,授权转载平台,创作者,自媒体,广告主,内容创作,内容生产,写作赚钱,自媒体征稿,投稿赚钱,创作者创作赚钱,内容交易平台,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易:短视频广告营销(秒拍,美拍,快手,抖音),微信变现,微博赚钱,类似：克劳瑞,一道自媒体,稿稿,乐观号,微播易/微博易,,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业）" />


<#include "../commonFragments/basic/css_link.ftl"/>
 	<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/contentBank/tab.css?${mdc_version}">
	<link rel="stylesheet" href="${request.contextPath}/css/contentBank/creator.css?${mdc_version}">
</head>
<body>
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
	<!-- 创作者 导航 -->
<div class="wrap">
	<div class="content_bank">
	<!-- 创作者和找内容公共导航 -->
		<#--  <#include "common/tab.ftl"/>   -->
		<div class='condition-wrap'> 
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
		  	<div  class='condition-nav'>
		  		<span class="tit">使用场景</span>
		  		<ul id='useScenes' num=1> 
		  			<li class='active-nav no-limit' id='' flag='1'>不限</li>
		  		</ul>
		  		<div class='more'><img src="${request.contextPath}/images/commonFragments/arr_small_down.png"></div>
		  	</div>
		  	<div  class='condition-nav'>
		  		<span class="tit">内容形式</span>
		  		<ul id='contentForm' num=2> 
		  			<li class='active-nav no-limit' id='' flag='1'>不限</li>
		  		</ul>
		  		<div class='more'><img src="${request.contextPath}/images/commonFragments/arr_small_down.png"></div>
		  	</div>
		  	<div  class='condition-nav'>
		  		<span class="tit">内容领域</span>
		  		<ul id='contentType' num=3> 
		  			<li class='active-nav no-limit' id='' flag='1'>不限</li>
		  		</ul> 
		  		<div class='more'><img src="${request.contextPath}/images/commonFragments/arr_small_down.png"></div>
		  	</div>
		  	<div  class='condition-nav'>
		  		<span class="tit">原创价格</span>
		  		<ul id='create-price' num=4> 
		  			<li class='active-nav no-limit' id=''>
		  				不限
		  			</li>
		  			<li>
		  				<span>5000</span>元以下
		  			</li>
		  			<li>
		  				<span>5000-6000</span>元
		  			</li>
		  			<li>
		  				<span>6000-10000</span>元
		  			</li>
		  			<li>
		  				<span>10000</span>元以上
		  			</li>
		  		</ul>
				<#include "../commonFragments/self_price.ftl"/>
		  	</div>
		  	<div  class='condition-nav '>
		  		<span class="tit">原创用时</span>
		  		<ul id='create-use-time' num=5> 
		  			<li class='active-nav no-limit' id=''>
		  				不限
		  			</li>
		  			<li>
		  				<span>1-7</span>天
		  			</li>
		  			<li>
		  				<span>7-14</span>天
		  			</li>
		  			<li>
		  				<span>14</span>天以上
		  			</li>
		  		</ul>
		  	</div>
		    <div  class='condition-nav last'>
		  		<span class="tit">创作风格</span>
		  		<ul id='createStyle' num=6> 
		  			<li class='active-nav no-limit' id='' flag='1'>不限</li>
		  		</ul> 
		  		<div class='more'><img src="${request.contextPath}/images/commonFragments/arr_small_down.png"></div>
		  	</div>
		</div>
		<div class='creator-content-list-wrap'>
			<ul id='creator-content-list'>
			</ul>
		</div>
		<div class='pagination-wrap'></div>
		<#include "../commonFragments/pagination_note.ftl"/>
	</div>
</div>
	<#include "../commonFragments/basic/footer.ftl"/> 	
	<#include "../commonFragments/basic/loading.ftl"/>
	<!-- 热线电话 -->
	<#include "../commonFragments/hotline.ftl"/>
</body>
<#include "../commonFragments/basic/js_script.ftl"/>
<script type="text/javascript" src="${request.contextPath}/plugins/jquery.pagination.js?${mdc_version}"></script>
<script type="text/javascript" src="${request.contextPath}/js/contentBank/creator.js"></script>
</html>
