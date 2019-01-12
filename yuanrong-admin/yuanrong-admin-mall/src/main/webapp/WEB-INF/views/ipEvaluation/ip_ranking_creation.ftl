<#include "../commonFragments/basic/meta.ftl"/>
	<title>IP创作力排行榜_圆融内容交易服务中心</title>
	<meta http-equiv="keywords" content='IP创作力排行榜,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业' />
	<meta http-equiv="description" content="IP创作力排行榜,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业,圆融由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化、证券化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />
	<#include "../commonFragments/basic/css_link.ftl"/> 
	<link rel="stylesheet" href="${request.contextPath}/css/ipEvaluation/ip_ranking.css?${mdc_version}">
	<script src="${request.contextPath}/plugins/layui/laydate/laydate.js?${mdc_version}"></script>
</head>
<body>
	<!-- 头部 -->
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
<div class="wrap wrap-has-bread">
    <div class="content">
		<#--  <div class="bread-crumb">
			<a href="${request.contextPath}/ipEvaluation/ip_evaluation.ftl">IP评估&nbsp;&nbsp;></a><span>IP创作力排行榜</span>
		</div>  -->
		
	<div class="rank-con-wrap fixedClear">
			<div class="rank-lt-wrap" id="js-rank-category-wrap">
				<#include "common/ip_e_category.ftl"/>	
			</div>
			<div class="rank-rt-wrap ">
				<#include "common/ip_e_tab.ftl"/>	
				<div class="creation-list-wrap">
					<ul class="list-tit-wrap fixedClear">
						<li class="li-width42"></li>
						<li class="li-width203" style="text-align: left;padding-left: 48px;">IP</li>
						<li>内容质量</li>
						<li>持续力</li>
						<li>传播力</li>
						<li>原创力</li>
						<li>综合指数</li>
					</ul>
					<div class="list-con-wrap" id="js-list-con-wrap">
						<!-- <ul class="fixedClear">
							<li class="li-width42"><img src="${request.contextPath}/images/ipEvaluation/ipRanking/no_1.png" alt=""></li>
							<li class="fixedClear li-width203">
								<div class="ip-avatar-wrap">
									<img class="ip-avatar-img" src="${request.contextPath}/images/ipEvaluation/ipRanking/ip_avatar.png" alt="">
								</div>
								<div class="ip-name-wrap">
									<p class="ip-name-cn">人民日报</p>
									<p class="ip-name-en">renminribao</p>
								</div>
							</li>
							<li>稳定性</li>
							<li>内容质量</li>
							<li>原创比例</li>
							<li>传播性</li>
							<li>综合指数</li>
						</ul> -->
					</div>	
					<#--  <div class="more_btn" id="js-more-btn">显示更多<i class="iconfont">&#xe733;</i></div>  -->
					<#include "../commonFragments/list_no_data.ftl"/> 
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
	$('.ip_ranking_article_tab li').eq(1).addClass('on').siblings().removeClass('on');
	$(document).ready(function(){
		// var moreBtnDom = $('#js-more-btn'),//点击加载更多
		// 	moreFlag = 1,
		// 	isPage = 1,//是否有下一页
		// 	size = 50,
		// 	listConWrapDom = $('#js-list-con-wrap');//ip创造力排行
		// $('.ip_ranking_article_tab li').eq(1).addClass('on').siblings().removeClass('on');
		//ip创造力排行
		// listConWrapDom.ftl('');
		// getCreationList(moreFlag);
		// function getCreationList(page){
		// 	var postUrl = domain + '/listIPCreativity/selectAllListIPCreativity',
		// 		noDataWrapDom = $('#js-no-data-wrap'),//暂无数据
		// 		noOneImg = '<img src="${request.contextPath}/images/ipEvaluation/ipRanking/no_1.png" alt="">',
		// 		noTwoImg = '<img src="${request.contextPath}/images/ipEvaluation/ipRanking/no_2.png" alt="">',
		// 		noThreeImg = '<img src="${request.contextPath}/images/ipEvaluation/ipRanking/no_3.png" alt="">',
		// 		pData = {
		// 			page: page,
		// 			size: size
		// 		};
		// 	commonFun.commonAjax(postUrl, 'post', pData, function(data){
		// 		if(data.status =='success'){
		// 			var sucData = data.data,
		// 				isPage = sucData.pages;//是否有下一页
		// 			isPage && isPage == moreFlag ? $('.more-wrap').hide() : $('.more-wrap').show();
		// 			if(sucData.data && sucData.data.length){
		// 				var sucDataList = sucData.data,
		// 					sucDataListLen = sucDataList.length;
		// 				for(var j = 0; j < sucDataListLen; j++ ){
		// 					var noImg = '',
		// 						id = (j + 1) + (page - 1) * size;
		// 					if(id == 1){
		// 						noImg = noOneImg;
		// 					} else if(id == 2){
		// 						noImg = noTwoImg;
		// 					} else if(id == 3){
		// 						noImg = noThreeImg;
		// 					} else {
		// 						noImg = id;
		// 					}
		// 					//'+sucDataList[j].HeadImg+'
		// 					var html = '<ul class="fixedClear">'
		// 						+'<li class="li-width42">'+noImg+'</li>'
		// 						+'<li class="fixedClear li-width203"><div class="ip-avatar-wrap"><img class="ip-avatar-img" src="'+sucDataList[j].HeadImg+'" alt=""></div>'
		// 							+'<div class="ip-name-wrap">'
		// 								+'<p class="ip-name-cn">'+sucDataList[j].Name+'</p>'
		// 								+'<p class="ip-name-en">'+sucDataList[j].weixinId+'</p>'
		// 							+'</div></li>'
		// 						+'<li>'+sucDataList[j].ContentQuality+'</li>'
		// 						+'<li>'+sucDataList[j].Stability+'</li>'
		// 						+'<li>'+sucDataList[j].Transmissible+'</li>'
		// 						+'<li>'+sucDataList[j].OriginalProportion+'</li>'
		// 						+'<li>'+sucDataList[j].TotalIndex+'</li>'
		// 					+'</ul>';
		// 					listConWrapDom.append(html);
		// 					var listUlDom = listConWrapDom.find('ul'),
		// 						listUlDOmLen = listUlDom.length;
		// 					if(listUlDOmLen){
		// 						for(var i = 0; i < listUlDOmLen; i++){
		// 							listConWrapDom.find('ul:odd').addClass('ul-gray');
		// 						}
		// 					}
		// 				}
		// 			}
		// 		} else {
		// 			noDataWrapDom.show();
		// 			$('.more-wrap').hide();
		// 		}
		// 	})
		// }
		// //点击加载更多
		// moreBtnDom.off('click').on('click', function(){
		// 	moreFlag++;
		// 	getCreationList(moreFlag);
		// })
		// //计算content的高度
		// calcHeight();
		// function calcHeight(){
		// 	var footerHei = $('.footer-wrap').height(),
		// 		winHei = $(window).height(),
		// 		contentHei = winHei - footerHei - 158;
		// 	$('.content').css('min-height', contentHei + 'px');
		// }
		// $(window).resize(function(){
		// 	calcHeight();
		// })
	})
</script>
</html>
