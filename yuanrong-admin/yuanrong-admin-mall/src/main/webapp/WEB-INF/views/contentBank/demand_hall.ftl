<#include "../commonFragments/basic/meta.ftl"/>
	<title>需求大厅_圆融内容交易服务中心</title>
	<meta http-equiv="keywords" content='广告主发布需求,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业' />
	<meta http-equiv="description" content="★广告主发布需求☆圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业,圆融由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化、证券化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />
	<#include "../commonFragments/basic/css_link.ftl"/> 
	<link rel="stylesheet" href="${request.contextPath}/css/contentBank/tab.css?${mdc_version}">
	<link rel="stylesheet" href="${request.contextPath}/css/contentBank/demand_hall.css?${mdc_version}">
</head>
<body>
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
<div class="wrap"> 
	<div class="demand-wrap">
		<!-- 这是内容银行发布需求的导航 -->
		<#include "common/tab.ftl"/> 
        <!-- 内容银行的发布需求 -->
        <div class="hall-con-wrap">
            <ul class="hall-list-ul-wrap fixedClear" id="js-hall-list-wrap">
				<!-- <li class="hall-list-li-wrap fixedClear">
					<div class="hall-list-lt-wrap">
						<img src="${request.contextPath}/images/contentBank/demand-pic.png" alt="">
						<div class="lt-type-wrap"><span class="span-1">图文</span>|<span class="span-2">视频</span></div>
					</div>
					<div class="hall-list-mid-wrap">
						<div class="mid-top fixedClear">
							<img src="${request.contextPath}/images/contentBank/demand-new.png" alt="">
							<h4>征集高质量母婴类内容创作者，能保持长期合作者优先作者</h4>
						</div>
						<div class="mid-steps-wrap fixedClear">
							<div class="mid-steps-con-wrap mid-first-step-wrap fixedClear">
								<div class="step-line-sign-wrap fixedClear">
									<div class="step-sign"><img src="${request.contextPath}/images/contentBank/demand-tick.png" alt=""></div>
									<div class="step-line"><img src="${request.contextPath}/images/contentBank/demand-line-ing.png" alt=""></div>
								</div>
								<div class="step-text">进行中</div>
							</div>
							<div class="mid-steps-con-wrap fixedClear">
								<div class="step-line-sign-wrap fixedClear">
									<div class="step-sign"><img src="${request.contextPath}/images/contentBank/demand-tick.png" alt=""></div>
									<div class="step-line"><img src="${request.contextPath}/images/contentBank/demand-line-ing.png" alt=""></div>
								</div>
								<div class="step-text">进行中</div>
							</div>
							<div class="mid-steps-con-wrap fixedClear">
								<div class="step-line-sign-wrap fixedClear">
									<div class="step-sign"><img src="${request.contextPath}/images/contentBank/demand-tick.png" alt=""></div>
									<div class="step-line"><img src="${request.contextPath}/images/contentBank/demand-line-ing.png" alt=""></div>
								</div>
								<div class="step-text">进行中</div>
							</div>
							<div class="mid-steps-con-wrap fixedClear" style="width: 30px">
								<div class="step-line-sign-wrap fixedClear" style="width: 30px">
									<div class="step-sign"><img src="${request.contextPath}/images/contentBank/demand-tick.png" alt=""></div>
									<div class="step-line" style="visibility: hidden;"><img src="${request.contextPath}/images/contentBank/demand-line-ing.png" alt=""></div>
								</div>
								<div class="step-text">进行中</div>
							</div>
						</div>
						<div class="mid-nums-wrap">
							<ul class="fixedClear">
								<li>
									<div class="mid-nums-num">$60,000</div>
									<div class="mid-nums-text">任务酬金</div>
								</li>
								<li class="mid-nums-li2">
									<div class="mid-nums-num">5天</div>
									<div class="mid-nums-text">剩余时间</div>
								</li>
								<li>
									<div class="mid-nums-num">35人</div>
									<div class="mid-nums-text">参与人数</div>
								</li>
							</ul>
						</div>
						<div class="mid-description-wrap">
							<p>
								当前中国年轻家庭市场规模已达到亿万量级，大部分人群对于家庭育儿与生活品质提升拥有着迫切的需求。宝宝树现征集母婴行业具有一定经验的内容创作者（文章、漫画、短视频等形态不限），希望从多个维度传
							</p>
						</div>
					</div>
					<div class="hall-list-rt-wrap">
						<ul class="hall-avatar-list fixedClear">
							<li><img src="${request.contextPath}/images/contentBank/demand-avatar.png" alt=""></li>
						</ul>
						<div class="hall-list-rt-mid-wrap">已有<span>66</span>人围观</div>
						<div class="hall-list-join-btn">我要参与</div>
					</div>
				</li> -->
			</ul>
			<div class="pagination-wrap fixedClear" style="display: none;">
				<div id="pagination" class="fixedClear"></div>
			</div>
			<#include "../commonFragments/list_no_data.ftl"/> 
        </div>
	</div>
</div>
	<#include "../commonFragments/basic/footer.ftl"/> 	
	<!-- 热线电话 -->
	<#include "../commonFragments/hotline.ftl"/>	
</body>
<#include "../commonFragments/basic/js_script.ftl"/>
<script type="text/javascript" src="${request.contextPath}/plugins/jquery.pagination.js?${mdc_version}"></script>
<script type="text/javascript">
   	$(document).ready(function(){
		var hallListWrapDom = $('#js-hall-list-wrap');
		var listNoDataWrapDom = $('#js-no-data-wrap');//暂无数据s
    	var paginationWrapDom = $('.pagination-wrap');//分页
		getDemandList(1);
		function getDemandList(page){
			var postUrl = newDomain + '/advertiserReleaseRequirement/getDemandHall',
				noDataWrapDom = $('#js-no-data-wrap'),//暂无数据
				totalPage = 0,
				pageSize = 10,
				postData = {
					cp: page,
					rows: pageSize
				};
			hallListWrapDom.ftl('');
			commonFun.commonAjax(postUrl, 'post', postData, function(res){
				if(res.status == 1){
					var sucData = res.data;
					totalPage = sucData.total;
					<#--  console.log(sucData.list)  -->
					if(sucData.list && sucData.list.length){
						var sucDataList = sucData.list,
							sucDataListLen = sucDataList.length,
							htmlList = '';
						for(var i = 0; i < sucDataListLen; i++ ){
							var sucDataStatus = statusFun(sucDataList[i].demandStatus);
							var avatarHtml = '';//围观人员头像
							var timeLeft = sucDataList[i].EndTime
							var joinBtn = '';//是否可以参与
							var budgetNum = sucDataList[i].ProBudget;
							var budget = '';//金额
							var sucDataAvatar = sucDataList[i].onLookerImgUrlList;
							var padTop9 = '9px';
							var newImg = '';
							timeLeft == 0 ? joinBtn = 'no-click' : joinBtn = '';
							budgetNum && budgetNum != '' ? budget = '￥<span>'+budgetNum+'</span>' : budget = '可商议';
							budget == '可商议' ? padTop9 = '9px' : padTop9 = '0';
							for(var k = 0, kLen = sucDataAvatar.length; k < kLen; k++){
								avatarHtml += '<li><img src="'+sucDataAvatar[k].onlookerImgUrl+'" alt=""></li>';
							}   
							if(i < 5  && page == 1){
								newImg = '<img src="${request.contextPath}/images/contentBank/demand-new.png" alt="">';
							} else {
								newImg = '';
							}
							var contentFormName = sucDataList[i].contentFormName ? sucDataList[i].contentFormName : '--'
 							htmlList += '<li class="hall-list-li-wrap fixedClear">'
								+'<div class="hall-list-lt-wrap">'
									+'<img src="'+sucDataList[i].RequireImg+'" alt="">'
									+'<div class="lt-type-wrap">'+contentFormName+'</div>'
								+'</div>'
								+'<div class="hall-list-mid-wrap">'
									+'<div class="mid-top fixedClear">'
										+ newImg
									+'<h4>'+sucDataList[i].ProName+'</h4>'
								+'</div>'
								+'<div class="mid-steps-wrap fixedClear">'+sucDataStatus+'</div>'
								+'<div class="mid-nums-wrap">'
									+'<ul class="fixedClear">'
										+'<li style="padding-top: '+padTop9+'">'
											+'<div class="mid-nums-num">'+budget+'</div>'
											+'<div class="mid-nums-text">任务酬金</div>'
										+'</li>'
										+'<li class="mid-nums-li2">'
											+'<div class="mid-nums-num"><span>'+sucDataList[i].endDay+'</span>天</div>'
											+'<div class="mid-nums-text">剩余时间</div>'
										+'</li>'
										+'<li>'
											+'<div class="mid-nums-num"><span>'+sucDataList[i].AttendNum+'</span>人</div>'
											+'<div class="mid-nums-text">参与人数</div>'
										+'</li>'
									+'</ul>'
								+'</div>'
								+'<div class="mid-description-wrap">'
									+'<p>'+sucDataList[i].ProDescription+'</p>'
								+'</div>'
							+'</div>'
							+'<div class="hall-list-rt-wrap">'
								+'<ul class="hall-avatar-list fixedClear">'+avatarHtml+'</ul>'
								+'<div class="hall-list-rt-mid-wrap">已有<span>'+sucDataList[i].OnlookerNum+'</span>人围观</div>'
								+'<div class="hall-list-join-btn '+joinBtn+'">我要参与</div>'
							+'</div>'
						+'</li>';

						}
						hallListWrapDom.append(htmlList);
						listNoDataWrapDom.hide();
						//右侧头像
						var avatarWrapLi = $('.hall-list-li-wrap'),
							wrapLiLen = avatarWrapLi.length;
						for(var i = 0; i < wrapLiLen; i ++){
							var avatarLi = $('.hall-list-rt-wrap', avatarWrapLi[i]).find('.hall-avatar-list li'),
								avatarLiLen = avatarLi.length;
								for(var j = 0; j < avatarLiLen; j++){
									if(j == 0 || j == 5){
										avatarLi.eq(j).css({
											'margin-left': '0'
										})
									} else {
										avatarLi.eq(j).css({
											'margin-left': '-6px'
										})
									}
								}
						}
						//点击我要参与	
						hallListWrapDom.on('click', 'li .hall-list-join-btn', function(e){
							$('#qq2').trigger('click');
						})
						//分页
						if(totalPage > 10){
							paginationWrapDom.show();
							$('#pagination').pagination(totalPage, {
								current_page: postData.cp,//当前页
								items_per_page: pageSize,
								prev_text: '上一页',
								next_text: '下一页',
								num_display_entries:5,
								callback: function(currPage, jg){
									postData.cp = currPage;
									getDemandList(postData.cp);
								}
							});
						}	
					} else {
						listNoDataWrapDom.show();
						paginationWrapDom.hide();
					}
				}
			})
		}
		//状态html
		function statusFun(status){
			var statusLineImgBlue = '${request.contextPath}/images/contentBank/demand-line-ing.png',//进行中，line
				statusLineImgGray = '${request.contextPath}/images/contentBank/demand-line-gray.png',//gray
				statusTickImgBlue = '${request.contextPath}/images/contentBank/demand-tick.png',
				statusTickImgGray = '${request.contextPath}/images/contentBank/demand-gray.png',
				txtActive = 'step-text-acitve',
				statusHtml = '';
			//招募中
			var statusZM = '<div class="mid-steps-con-wrap mid-first-step-wrap fixedClear">'
				+'<div class="step-line-sign-wrap fixedClear">'
					+'<div class="step-sign"><img src="'+statusTickImgGray+'" alt=""></div>'
					+'<div class="step-line"><img src="'+statusLineImgGray+'" alt=""></div>'
				+'</div>'
				+'<div class="step-text ">招募中</div>'
			+'</div>';
			//创作中
			var statusCZ = '<div class="mid-steps-con-wrap fixedClear">'
				+'<div class="step-line-sign-wrap fixedClear">'
					+'<div class="step-sign"><img src="'+statusTickImgGray+'" alt=""></div>'
					+'<div class="step-line"><img src="'+statusLineImgGray+'" alt=""></div>'
				+'</div>'
				+'<div class="step-text">创作中</div>'
			+'</div>';
			//验收
			var statusYS = '<div class="mid-steps-con-wrap fixedClear">'
				+'<div class="step-line-sign-wrap fixedClear">'
					+'<div class="step-sign"><img src="'+statusTickImgGray+'" alt=""></div>'
					+'<div class="step-line"><img src="'+statusLineImgGray+'" alt=""></div>'
				+'</div>'
				+'<div class="step-text step-text-padlt">验收</div>'
			+'</div>';
			//评价
			var statusPJ = '<div class="mid-steps-con-wrap fixedClear" style="width: 30px">'
				+'<div class="step-line-sign-wrap fixedClear" style="width: 30px">'
					+'<div class="step-sign"><img src="'+statusTickImgGray+'" alt=""></div>'
					+'<div class="step-line" style="visibility: hidden;"><img src="'+statusLineImgGray+'" alt=""></div>'
				+'</div>'
				+'<div class="step-text step-text-padlt">评价</div>'
			+'</div>';
			var statusVar = '<div class="mid-steps-con-wrap mid-first-step-wrap fixedClear">'
				+'<div class="step-line-sign-wrap fixedClear">'
					+'<div class="step-sign"><img src="'+statusTickImgBlue+'" alt=""></div>'
					+'<div class="step-line"><img src="'+statusLineImgBlue+'" alt=""></div>'
				+'</div>'
				+'<div class="step-text '+txtActive+'">'+status+'</div>'
			+'</div>';
			if(status == '招募中'){
				//statusZM + statusCZ + statusYS + statusPJ
				statusHtml = statusVar + statusCZ + statusYS + statusPJ;
			} else if(status == '创作中'){
				statusHtml = statusZM + statusVar + statusYS + statusPJ;
			+'</div>';
			} else if(status == '验收'){
				statusHtml = statusZM + statusCZ + statusVar + statusPJ;
			} else if(status == '评价'){
				statusHtml = statusZM + statusCZ + statusYS + statusVar;
			}
			return statusHtml;
		}
	})
</script>
</html>
