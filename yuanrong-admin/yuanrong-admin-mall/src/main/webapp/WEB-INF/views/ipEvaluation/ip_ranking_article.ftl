<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="ie=edge,chrome=1" />
	<meta name="renderer" content="webkit">
	<title>作品内容价值评估排行榜_圆融内容交易服务中心</title>
	<meta http-equiv="keywords" content='作品内容价值评估排行榜,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业' />
	<meta http-equiv="description" content="作品内容价值评估排行榜,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业,圆融由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化、的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />
	<#include "../commonFragments/basic/css_link.ftl"/> 
	<link rel="stylesheet" href="${request.contextPath}/css/ipEvaluation/ip_ranking.css?${mdc_version}">
	<script src="${request.contextPath}/plugins/layui/laydate/laydate.js?${mdc_version}"></script>
</head>
<body>
	<!-- 头部 -->
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>

<div class="wrap wrap-has-bread  fixedClear">
	<div class="content">
		<#--  <div class="ip_ranking_list_wrap">
			<a href="${request.contextPath}/ipEvaluation/ip_evaluation.ftl">IP评估</a>&nbsp;>&nbsp;<span>内容价值排行榜</span>
		</div>  -->
		<div class="rank-con-wrap fixedClear">
			<div class="rank-lt-wrap" id="js-rank-category-wrap">
				<#include "/ipEvaluation/common/ip_e_category.ftl"/>	
			</div>
			<div class="rank-rt-wrap ">
				<#include "/ipEvaluation/common/ip_e_tab.ftl"/>	
				<div class="ip_ranking_article">
					<div class="ip_ranking_article_list">
						<div class="ip_ranking_article_list_content">
							<div class="list_title">
								<ul>
									<li class="title" style="padding-left: 46px;">文章标题</li>
									<li>主题性</li>
									<li>创作性</li>
									<li>原创度</li>
									<li>综合指数</li>
								</ul>
							</div>
							<div class="list_content">
								<!-- <ul>
									<li class="title"><span>1</span><a href="">标题</a></li>
									<li>99</li>
									<li>99</li>
									<li>100</li>
									<li>100</li>
								</ul> -->
							</div>
						</div>
						<#--  <div class="more_btn" id="js-more-btn">显示更多&nbsp;<img src="${request.contextPath}/images/commonFragments/arr_small_down.png" alt=""></div>  -->
					<#include "../commonFragments/list_no_data.ftl"/> 
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 底部 -->
<#include "../commonFragments/basic/footer.ftl"/> 
<#include "../commonFragments/basic/loading.ftl"/>
<!-- 热线电话 -->
<#include "../commonFragments/hotline.ftl"/>	
</body>
<#include "../commonFragments/basic/js_script.ftl"/>
<script src="${request.contextPath}/js/ipEvaluation/ip_ranking_common.js?${mdc_version}"></script>
<script>
	$('.ip_ranking_article_tab li').eq(0).addClass('on').siblings().removeClass('on');
</script>
<!-- <script src="${request.contextPath}/js/ipEvaluation/ip_ranking.js?${mdc_version}"></script> -->
</html>
