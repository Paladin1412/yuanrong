//二级导航切换
$('.content-bank-original').css({'background':'#fff','border-top':'2px solid #6daaec'});
$('.content-bank-author ').css({'background':'#e3e6ec','border-top':0});

//添加顶部的样式
$('#js-top-wrap').addClass('top-wrap-no-banner');

/*
 **	列表所需的参数
  	keyWord 关键词
  	publishStatus   	发布状态（0.未公开；1.已公开）
  	contentFormID      内容形式
  	yrCategoryId       内容分类
  	marketingSceneId    营销场景
  	priceStart         转载价格（开始）
  	pirceEnd          转载价格（结束）
  	sortScore         综合排序 （desc.降序；asc.升序 ）
  	sortPrice         价格排序 （desc.降序；asc.升序 ）
  	latest            按照最新排序（desc.降序；asc.升序 ）
*/
var publishStatus;
var searchPra = window.location.search;
var keyWord,contentFormID,yrCategoryId,marketingSceneId,priceStart,pirceEnd,sortScore='desc',sortPrice,latest;
var lowPrice = 10, highPrice = 500;
var getUrlParams = commonFun.getUrlParam();
if(getUrlParams && getUrlParams.keyword){
	keyWord = getUrlParams.keyword;
}
if(searchPra.indexOf('publishStatus')!=-1){
	//找到
	publishStatus = commonFun.getUrlParam().publishStatus*1;
	if(publishStatus){
		//已发布
		lowPrice = 10;
		$('#useScenes').children().eq(0).removeClass('active-nav');
		$('#useScenes').children().eq(1).addClass('active-nav');
		$('.price-pub-label').text('转载价格');
		$('.transhipment-price').css('display','block');
		$('.buy-out').css('display','none');
	}else{
		//未发布
		lowPrice = 15;
		$('#useScenes').children().eq(1).removeClass('active-nav');
		$('#useScenes').children().eq(0).addClass('active-nav');
		$('.price-pub-label').text('买断价');
		$('.transhipment-price').css('display','none');
		$('.buy-out').css('display','block');
	}
}else{
	//找不到
	publishStatus = 0;
	lowPrice = 15;
    $('.price-pub-label').text('买断价');
    $('.transhipment-price').css('display','none');
    $('.buy-out').css('display','block');
}
function getAuthorDic(){
	var str = "<li></li>";

	var contentFormEle = $('#contentForm');//内容形式
	var contentTypeEle = $('#contentType');//内容分类
	var createUseTime = $('#create-use-time');//营销场景
	/*
		获取内容形式和内容分类的数据
	*/
	commonFun.commonAjax(newDomain+'/dictInfo/getAuthorDic','post','',function(result){

		if(result.status == 1){
			var contentForm = result.data.contentForm;//内容形式
			var contentType = result.data.contentType;//内容分类

			for(var i=0;i<contentForm.length;i++){
				$(contentFormEle).append(str);
			};
			for(var i=0;i<contentType.length;i++){
				$(contentTypeEle).append(str);
			};

			var contentFormEleChild = $(contentFormEle).children().not('.no-limit');
			var contentTypeEleChild = $(contentTypeEle).children().not('.no-limit');
				
			
			if(contentFormEleChild.length<=15){
				$(contentFormEleChild).eq(0).parent().next().css('display','none');
			}else{
				$(contentFormEleChild).eq(0).parent().next().prop('flagbtn',false).css('display','block');
				// $(contentTypeEleChild).eq(0).parent().height(24);
			}	

			if(contentTypeEleChild.length<=15){
				$(contentTypeEleChild).eq(0).parent().next().css('display','none');
			}else{
				$(contentTypeEleChild).eq(0).parent().next().prop('flagbtn',false).css('display','block');
				// $(contentTypeEleChild).eq(0).parent().height(24);
			}	

			
			
			$(contentFormEleChild).each(function(index,item){
				$(item).text(contentForm[index].CategoryName);
				$(item).attr({id:contentForm[index].CategoryID,flag:contentForm[index].flag});
				if($(item).attr('flag')==0){	
					$(item).css({'color':'#ccc','cursor':'auto'});
				}
			});
			
			$(contentTypeEleChild).each(function(index,item){
				$(item).text(contentType[index].CategoryName);
				$(item).attr({id:contentType[index].CategoryID,flag:contentType[index].flag});
				if($(item).attr('flag')==0){
					$(item).css({'color':'#ccc','cursor':'auto'});
				}
			});	


			var contentFormList = $('#contentForm').children();
			var contentTypeList = $('#contentType').children();
			$(contentFormList).click(updateList);
			$(contentTypeList).click(updateList);
		}


	});	

	/*
		获取营销场景的数据
	*/
	// commonFun.commonAjax(newDomain+'/author/getMarketingScene','post','',function(result){
	// 	if(result.status == 1){
	// 		var marketingScene = result.data;
		
	// 		for(var i=0;i<marketingScene.length;i++){
	// 			$(createUseTime).append(str);
	// 		};

	// 		var createUseTimeChild = $(createUseTime).children().not('.no-limit');
	
	// 		if(createUseTimeChild.length<=15){
	// 			$(createUseTimeChild).eq(0).parent().next().css('display','none');
	// 		}else{
	// 			$(createUseTimeChild).eq(0).parent().next().prop('flagbtn',false).css('display','block');
	// 			$(createUseTimeChild).eq(0).parent().height(24);
	// 		}	

	// 		$(createUseTimeChild).each(function(index,item){
	// 			$(item).text(marketingScene[index].name);
	// 			$(item).attr({id:marketingScene[index].id,flag:marketingScene[index].isShow});//这里的flag暂时没慎用，主要来判断有没有创作者的
	// 			if($(item).attr('flag')==0){	
	// 				$(item).css({'color':'#ccc','cursor':'auto'});
	// 			}
	// 		});
	// 		var contentFormList = $('#create-use-time').children();
	// 		$(contentFormList).click(updateList);
	// 		getWorkList();
	// 	}
	// })

	getWorkList();
};

function updateList(){
	var parentid = $(this).parent().attr('num');

	/*	
		 num 
		*  1 作品类型
		   2 内容形式
		   3 内容分类
		   4 转载价格
		   5 营销场景
	*/
	if(parentid==1){
		publishStatus = $(this).attr('id');
		priceStart = '';
		pirceEnd = '';
		var navList = $('#create-price').children();
		navList.removeClass('active-nav');
		navList.eq(0).addClass('active-nav');
		if(publishStatus*1||publishStatus===''){
			//
			lowPrice = 10;
			$('.price-pub-label').text('转载使用');
			$('.buy-out').css('display','none');
			$('.transhipment-price').css('display','block');
			
		}else{
			lowPrice = 15;
			$('.price-pub-label').text('买断价');
			$('.buy-out').css('display','block');
			$('.transhipment-price').css('display','none');
		}
	}
	else if(parentid==2){
		if(!Number($(this).attr('flag'))){
			return false;
		};
		contentFormID = $(this).attr('id');
	}
	else if(parentid==3){
		if(!Number($(this).attr('flag'))){
			return false;
		};
		yrCategoryId = $(this).attr('id');
	}
	else if(parentid==4){
		var priceId = $(this).children('span').text();
		// console.log(priceId);
		if(priceId=='不限'){
			priceStart = '';
			pirceEnd = '';
		}else{
			if(priceId.indexOf('-')!=-1){
				priceStart = priceId.split('-')[0]*1;
				pirceEnd = priceId.split('-')[1]*1;
			}else{
				if(priceId<=lowPrice){
					priceStart = 0;
					pirceEnd = lowPrice;
				};
				if(priceId>=highPrice){
					priceStart = highPrice;
					pirceEnd = '';	
				}
			}	
		}	
	}
	else if(parentid==5){
		if(!Number($(this).attr('flag'))){
			return false;
		};
		marketingSceneId = $(this).attr('id');
	};
	$(this).siblings().removeClass('active-nav');
	$(this).addClass('active-nav');
	
	$('#js-start-price').val('');
	$('#js-end-price').val('');
	getWorkList();
};


function showMore(obj){
	$(obj).click(function(){
		if($(this).prop('flagbtn')){
			$(this).prev().height(24);
		}else{
			$(this).prev().height('auto');
		};
		$(this).prop('flagbtn',!($(this).prop('flagbtn')))
	})
};

function getWorkList (cp){
	var params = {
		keyWord,
		publishStatus,
		contentFormID,
		yrCategoryId,
		marketingSceneId,
		priceStart,
		pirceEnd,
		sortScore,
		sortPrice,
		latest,
		cp:cp||1,

	},
	searchTxt = $.trim($('#js-search-text').val());
	if(searchTxt.length > 0){
		params.keyWord = searchTxt
	} else {
		params.keyWord = '';
	}
	
	for(var i in params){
		if(params[i]===''||params[i]===undefined){
			delete params[i]
		}
	};

	new AjaxRequest({
		type: "post",
		url: newDomain+'/author/yrProduction_list',
		param: params,
		isShowLoading: true,
		callBack: function(res){
			var workListParent = $('#original-works-list-wrap');
			if(res.status == 1){
				workListParent.empty();
				var workList =res.data.data;//作品的数据列表
				if(!workList.length){
					var empty = "<div class='no-data-wrap' id='js-no-data-wrap'>"+
								"<img src='"+domain+"/images/commonFragments/no_data.png' >"+
								"<p>没找到作品！ 请<a class=' qq' href='"+salesServiceUrl+"' id='qq'>联系客服</a>或拨打电话400 819 8818 </p>"+
								"</div>";
					$(workListParent).html(empty);
					// $('#qq').click(function(){
					// 	qdService($(this));
					// });
					$('.pagination-wrap').css('display','none');
					$('.pagination-note-wrap').hide();
					return false
				}else{
					$('.pagination-wrap').css('display','block');
					var total = res.data.total;
					var page = res.data.pages;
					var cp1 = res.data.cp;
					var size = 10;
					workList.forEach((item,index)=>{
						var detailUrl = domain+"/contentBank/article_detail_"+item.recId+".html";
						if(item.price != '--'){
							var price = commonFun.formatNumber(item.price);
						} else {
							var price = '<a href="'+detailUrl+'" target="_blank" style="cursor: pointer; font-size: 12px;" class="color-blue">'+loginAvailable+'</a>';
						}
						if(item.coverLocalUrl){
							var coverLocalUrl = item.coverLocalUrl;
						} else {
							if(item.coverLocalUrl == 'None'){
								var coverLocalUrl = (domain+'/static/images/article/bg_image_big.jpg');
							}
							var coverLocalUrl = (domain+'/static/images/article/bg_image_big.jpg');
						}
						
						if(item.originalScore){
							var originalScore = item.originalScore, isShowOriginalScore = '';
						} else {
							var originalScore = 0, isShowOriginalScore = 'hide';
						}
						var originalScore = item.originalScore ? item.originalScore : 0;
						var formatContent = ($.trim(item.content.replace(/&nbsp;/g, '')) ||'  ');
						workListParent.append("<li class='fixedClear'>"+	
							"<div class='img-wrap'>"+
								"<a href='"+domain+"/contentBank/article_detail_"+item.recId+".html' target='_blank'><img src='"+coverLocalUrl+"'></a>"+
							"</div>"+
							"<div class='works-info-wrap'>"+
								"<div class='fixedClear works-info-tit-wrap'>"+
									"<a href='"+domain+"/contentBank/article_detail_"+item.recId+".html' target='_blank' style='float:left'><strong>"+item.title+"</strong></a>"+
									"<span class='word-num-wrap'>（"+item.wordNum+"字</span>"+
									"<i class='iconfont icon-weixin-copy1 weixin'><span class='tip'>在微信公众号发表过的原创作品，暂不支持授权转载到微信公众号</span></i>"+
								"</div>"+
								"<div class='works-time'>"+
									"<span class='work-author'><span class='work-label'>作者：</span>"+item.authorInfo.authorNickname+
										"<div class='float-auhtor'>"+
											"<p class='author-float-info'>"+
												"<img src='"+item.authorInfo.authorImg+"'>"+
												"<span>"+item.authorInfo.authorNickname+"</span>"+
											"</p>"+
											"<p>"+
												"创作形式：<span>"+item.authorInfo.contentform+"</span>"+	
											"</p>"+
											"<p>"+
												"擅长领域：<span>"+item.authorInfo.yrAuthorCategory+"</span>"+
											"</p>"+
											"<p>"+
												"作品数量：<span>"+item.authorInfo.prnum+"</span>"+
											"</p>"+
											"<p class='popularity'>"+
												"人<span style='visibility:hidden'>哈哈</span>气：<span>"+item.authorInfo.accessNum+"</span>"+
											"</p>"+
											" <p class='jump_detail'>"+
												"<a href='"+domain+"/contentBank/author_detail_"+item.authorInfo.yrAuthorId+".html' target='_blank'>查看详情</a>"+
											"</p>"+
										"</div>"+
									"</span>"+
									// "<span class='work-label'>"+
									// 	item.createdTime+
									// "</span>"+
									
								"</div>"+
								// "<p class='item-describe'>"+formatContent+"</p>"+
								"<p class='item-describe'><a style='color: #727477;' href='"+domain+"/contentBank/article_detail_"+item.recId+".html' target='_blank'>"+formatContent+"</a></p>"+
								"<p class='work-label-wrap'>"+
									"<span class='item-label item-label-category '>"+
										"<span class='work-label'>分类：</span>"+
										"<span classs='work-con'>"+item.yrCategory+"</span>"+
									"</span>"+
									"<span class='item-label item-label-dis'>"+
										"<span class='work-label'>营销场景：</span>"+
										"<span classs='work-con'>"+item.marketingScene+"</span>"+
									"</span>"+
									"<span class='item-label item-label-tags'>"+
										"<span class='work-label'>高频词：</span>"+
										"<span classs='work-con'>"+item.tags +"</span>"+
									"</span>"+
									// "<span class='item-label item-label-accessNum'>"+
									// "<span class='work-label'>浏览量：</span>"+
									// "<span classs='work-con'>"+item.accessNum +"</span>"+
									// "</span>"+
									"<span class='item-label original-score "+isShowOriginalScore+"'>"+
										"<span class='work-label original-score'>原创度：</span>"+
										"<span classs='work-con'>"+originalScore+"</span>"+
									"</span>"+
									//圆融指数:contentEvaluationScore
									// "<span class='item-label'>"+
									// 	"<span class='work-label'>交易次数:</span>"+
									// 	"<span classs='work-con'>"+item.sellCount+"</span>"+
									// "</span>"+
								"</p>"+
							"</div>"+
							"<div class='works-right-wrap'>"+
								"<p class='fixedClear'>"+
									"<i class='iconfont icon-bangzhu bangzhu'></i>"+
									"<span class='label price-pub'></span>"+
									"<span class='price'>"+price+"</span>"+
								"</p>"+
								"<p >"+
									"<button class='addCar'>加入选购车</button>"+
								"</p>"+
								"<p>"+
									"<button class='purchase'>立即购买</button>"+
								"</p>"+
							"</div>"+
						"</li>")
					})
				}
				

				var workListParentChild = workListParent.children();

				//价格标注  微信头像  帮助  多少字 多少图
				if(publishStatus*1||publishStatus===''){
					$('.price-pub').text('转载使用：');
					$('.bangzhu').css('display','none');
					// $('.word-num-wrap').css('display','none');
					$('.word-num-wrap').css('display','block');
				}else{
					$('.price-pub').text('买断价 : ')
					$('.weixin').css('display','none');
					$('.bangzhu').css('display','inline-block');
					$('.word-num-wrap').css('display','block');
				}


				workListParentChild.each(function(index,item){
					//发布平台是否是微信
					// console.log(typeof workList[index].tags)
					if(workList[index].publishPlatform=='微信'){
						$(item).find('.weixin').css('display','block');
					}
					if(workList[index].yrCategory===undefined||workList[index].yrCategory===''||workList[index].yrCategory==null||workList[index].yrCategory=='null'){
						$(item).find('.item-label-category').css('display','none');
					};
					if(workList[index].marketingSceney===undefined||workList[index].marketingSceney===''||workList[index].marketingSceney==null||workList[index].marketingSceney=='null'){
						$(item).find('.item-label-dis').css('display','none');
					};
					if(workList[index].tags===undefined||workList[index].tags===''||workList[index].tags==null||workList[index].tags=='null'){
						$(item).find('.item-label-tags').css('display','none');
					}else{
						var tagsFormat = workList[index].tags.replace(/、/g, '<span class="work-label"> | </span>');
						$(item).find('.item-label-tags').find('span').eq(1).html(tagsFormat);
					}
					if(workList[index].accessNum===undefined||workList[index].accessNum===0){
						$(item).find('.item-label-accessNum').css('display','none');
					};
					// if(workList[index].contentEvaluationScore ===undefined||workList[index].contentEvaluationScore ===0){
					// 	$(item).find('.item-label').eq(4).css('display','none');
					// };
					// if(workList[index].sellCount===undefined||workList[index].sellCount===''){
					// 	$(item).find('.item-label').eq(4).css('display','none');
					// };
					if(workList[index].price===undefined||workList[index].price===0){
						$(item).find('.price').parent().css('display','none')
					};


					//人气为undefined的暂时隐藏
					if(workList[index].authorInfo.accessNum===undefined){
						$(item).find('.popularity').css('display','none');
					};

					//加入选购车
					$(item).find('.addCar').click(function(e){
						var recid = workList[index].recId;
						var auImg = workList[index].coverLocalUrl;
						addCartData('article',recid,auImg,e);
					});
					$(item).find('.purchase').click(function(){
						var recid = workList[index].recId;
						buyProductFun(recid, 'singleBuy');
					});

					//作者信息如果是匿名不展示
					$(item).find('.work-author').hover(function(){
						if(workList[index].authorInfo.isAnonymous===0 && workList[index].authorInfo.yrAuthorStatus == 2){
							$(this).children('.float-auhtor').css({'display':'block'});
							$(this).css({'cursor': 'pointer'});
						}else{
							$(this).children('.float-auhtor').css({'display':'none'});
							$(this).css({'cursor': 'auto'});
						}
					},function(){
						$(this).children('.float-auhtor').css({'display':'none'});
					})


					//检验是否加入选购车
					if(workList[index].isAddCart){
						//加入
						$(item).find('.addCar').addClass('btn-border-disabled');
						$(item).find('.addCar').text('已加入选购车')
					}

					//微信显示hover
					$(item).find('.weixin').hover(function(){
						$(this).children('.tip').css('display','block');
					},function(){
						$(this).children('.tip').css('display','none');	
					});


					//帮助
					$(item).find('.bangzhu').click(function(){
						$('#js-pop-example-wrap').css('display','block');
					})

					//多少图片处理
					var wordNumEle = $(item).find('.word-num-wrap');
					if(workList[index].imgNum===undefined){
						wordNumEle.text(wordNumEle.html()+'）')
					}else{
						wordNumEle.text(wordNumEle.html()+''+workList[index].imgNum+'图片）')
					}

					//转载使用和买断价千隔分

				})

				$.each($('img'),function(i, e){
					e.onerror=function(){
						e.src = '/static/images/article/bg_image_big.jpg';
						// e.alt = '图片加载失败';
					}
				}); 
				$('.pagination-wrap').pagination(total,{
					current_page: cp1,//当前页
					items_per_page: size,
					prev_text: prev_text,
					next_text: next_text,
					num_display_entries:5,
					callback: function(newpage){
						if(newpage > 3){
							var getLoginFun = function (isLogin) {
								if(isLogin == 'Y'){
									getWorkList(newpage);
									$("html,body").scrollTop(0, 0);
								}
							};
							getUserLoginInfo(getLoginFun, 'showpop', 'pagination');
						} else {
							getWorkList(newpage);
							$("html,body").scrollTop(0, 0);
						}
					}
				});

			}
		}
	})

}
// var hotEleparent = $('#hot-word-wrap');
// commonFun.commonAjax(newDomain+'/author/yrProduction/hotSearch','post','',function(result){
// 	if(result.status==1){
// 		var hotEleList = result.data;
// 		hotEleList.forEach((item)=>{
// 			hotEleparent.append('<li>'+item.name+'</li>')
// 		})
// 	}
// })


$('#synthesis').click(function(){
	$(this).addClass('active-condition');
	$(this).siblings('.check-codition').removeClass('active-condition');
	sortScore = 'desc';
	sortPrice = ''
	latest = ''
	getWorkList()
});

$('#new').click(function(){
	$(this).addClass('active-condition');
	$(this).siblings('.check-codition').removeClass('active-condition');
	sortScore = '';
	sortPrice = ''
	latest = 'desc'
	getWorkList()
});
$('#ascending').click(function(){
	$(this).addClass('active-condition');
	$(this).siblings('.check-codition').removeClass('active-condition');
	sortScore = '';
	sortPrice = 'asc'
	latest = ''
	getWorkList()
});
$('#descending').click(function(){
	$(this).addClass('active-condition');
	$(this).siblings('.check-codition').removeClass('active-condition');
	sortScore = '';
	sortPrice = 'desc'
	latest = ''
	getWorkList()
});


$('#search-btn').click(()=>{
	keyWord = $.trim($('#search-val').val());

	getWorkList();
});

$('#search-val').keyup(function(e){
	if(e.keyCode==13){
		keyWord = $.trim($('#search-val').val());
		getWorkList();
	}
});

$('#release-btn').click(function(){
	//这里需要传值
	window.location.href = domain+'/demandHall/demand_publish.html'
});
//获取搜索条件
$('#useScenes').children().click(updateList);
$('#create-price').children().click(updateList);
getAuthorDic();

showMore($('#useScenes').next());
showMore($('#contentType').next());
showMore($('#create-use-time').next());
$('#js-icon-un').mouseenter(function(){
	$('#js-note-work-type .work-down-column-con').text('未在任何网站、公众号、报纸、媒体以及其他平台发表过的原创作品');
	$('#js-note-work-type').css({'left': '144px'}).show();
}).mouseleave(function(){
    $('#js-note-work-type').hide();
})
$('#js-icon-pub').mouseenter(function(){
	$('#js-note-work-type .work-down-column-con').text('在任意网站、公众号、报纸、媒体以及其他平台发表过，包含发表或被删除的原创作品');
	$('#js-note-work-type').css({'left': '262px'}).show();
}).mouseleave(function(){
    $('#js-note-work-type').hide();
})
// 自定义价格查询
$('#js-self-price-btn').on('click', function () {
	var sPrice = parseFloat($.trim($('#js-start-price').val())),
		ePrice = parseFloat($.trim($('#js-end-price').val()));
		sPrice ? sPrice = sPrice : sPrice = 0;
		ePrice ? ePrice = ePrice : ePrice = 0;
	if(sPrice > 0 || ePrice > 0){
		if(sPrice > 0 && ePrice > 0){
			if(sPrice > ePrice){
				priceStart = ePrice;
				pirceEnd = sPrice;
			} else {
				priceStart = sPrice;
				pirceEnd = ePrice;
			}
		}
		if(sPrice > 0 && !ePrice){
			priceStart = sPrice;
			pirceEnd = '';
		}
		if(ePrice > 0 && !sPrice){
			priceStart = '';
			pirceEnd = ePrice;
		}
	} 
	
	$('#create-price').find('li').eq(0).addClass('active-nav').siblings().removeClass('active-nav');
	getWorkList();
})