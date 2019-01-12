var useScenesList,contentFormList,contentTypeList,createStyle;
var listurl = newDomain+'/author/getAuthorList';
var useScenesNum = '';
var contentFormNum ='';
var contentTypeNum = '';
var priceNum ='';
var useTimeNum = '';
var createStyleId = '';
var carBackColor ='#f8f9fb';
var carColor='#d3d5d9';

var getUrlParams = commonFun.getUrlParam();
if(getUrlParams && getUrlParams.keyword){
	var keyWords = getUrlParams.keyword;
}
//创作者搜索条件列表	
function getAuthorDic(){
	var str = "<li></li>";
	var useScenesEle = $('#useScenes');
	var contentFormEle = $('#contentForm');
	var contentTypeEle = $('#contentType');
	var createStyleEle = $('#createStyle');
	commonFun.commonAjax(newDomain+'/lable/authorLable','get','',function(result){
		if(result.status==1){
			var item = result.data.contentStyle;
			for(var i=0;i<item.length;i++){
				createStyleEle.append('<li flag='+item[i].flag+' id='+item[i].CategoryID+'>'+item[i].CategoryName+'</li>')	
			};
			var createStyleEleChild = $(createStyleEle).children();
			if(createStyleEleChild.length<=15){
				$(createStyleEleChild).eq(0).parent().next().css('display','none');
			}else{
				$(createStyleEleChild).eq(0).parent().next().prop('flagbtn',false).css('display','block');
				$(createStyleEleChild).eq(0).parent().height(24);
			};
			$(createStyleEleChild).each(function(index,item){
				if($(item).attr('flag')==0){	
					$(item).css({'color':'#ccc','cursor':'auto'});
				}
			});
			$(createStyleEleChild).click(updateList);
		}
	});

	commonFun.commonAjax(newDomain+'/dictInfo/getAuthorDic','post','',function(result){

		if(result.status == 1){
			var useScenes = result.data.useScenes;//使用场景
			var contentForm = result.data.contentForm;//内容形式
			var contentType = result.data.contentType;//目标领域

			for(var i=0;i<useScenes.length;i++){
				$(useScenesEle).append(str);
			};
			for(var i=0;i<contentForm.length;i++){
				$(contentFormEle).append(str);
			};
			for(var i=0;i<contentType.length;i++){
				$(contentTypeEle).append(str);
			};

			var useScenesEleChild = $(useScenesEle).children().not('.no-limit');
			var contentFormEleChild = $(contentFormEle).children().not('.no-limit');
			var contentTypeEleChild = $(contentTypeEle).children().not('.no-limit');
				
			if(useScenesEleChild.length<=10){
				$(useScenesEleChild).eq(0).parent().next().css('display','none');
			}else{
				$(useScenesEleChild).eq(0).parent().next().prop('flagbtn',false).css('display','block');
				$(contentTypeEleChild).eq(0).parent().height(24);
			}	
			
			if(contentFormEleChild.length<=15){
				$(contentFormEleChild).eq(0).parent().next().css('display','none');
			}else{
				$(contentFormEleChild).eq(0).parent().next().prop('flagbtn',false).css('display','block');
				$(contentTypeEleChild).eq(0).parent().height(24);
			}	

			if(contentTypeEleChild.length<=15){
				$(contentTypeEleChild).eq(0).parent().next().css('display','none');
			}else{
				$(contentTypeEleChild).eq(0).parent().next().prop('flagbtn',false).css('display','block');
				$(contentTypeEleChild).eq(0).parent().height(24);
			}	

			$(useScenesEleChild).each(function(index,item){
				$(item).text(useScenes[index].CategoryName);
				$(item).attr({id:useScenes[index].CategoryID,flag:useScenes[index].flag});

				if($(item).attr('flag')==0){
					$(item).css({'color':'#ccc','cursor':'auto'});
				}
			});	
			
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

			useScenesList = $('#useScenes').children();
			contentFormList = $('#contentForm').children();
			contentTypeList = $('#contentType').children();
			$(useScenesList).click(updateList);
			$(contentFormList).click(updateList);
			$(contentTypeList).click(updateList);

		}
	});	
};


//更多
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

function getAuthorList(url,type,page,size,scenes,contentForm,category,creationPrice,creationTime,createStyID){
	if(!url){
		return false
	}
	var url = url;
	var type = type || 'post';
	var page = page || 1;
	var size = size || 10;
	var creatorContentList = $('#creator-content-list');
	var str = "	<li>"+
					"<div class='author-image-wrap'>"+
						"<a href='' target='_blank'>"+
							"<img src='' width=100%><br>"+
							"<span></span>"+
						"</a>"+
					"</div>"+
					"<div class='author-info'>"+
						"<p>原创参考价：<span class='blue-48'></span></p>"+
						"<p>创作形式：<span></span></p>"+
						"<p class='good-wrap'><span class='good-labal'>使用场景：</span><span class='good-content'></span></p>"+
						"<p>擅长领域：<span></span></p>"+
						"<p>原创用时：<span></span></p>"+
						"<div class='tag-wrap'>"+
						 	"<span class='labal-name'>创作风格：</span>"+
						 	"<ul>"+
						 	"</ul>"+
						 "</div>"+
					"</div>"+
					"<div class='author-works-info'>"+
						"<div class='introduction'>"+
							"<span>作者简介：</span>"+
							"<p class='fixedClear1'><a href='' target='_blank'></a></p>"+
							/*"<div class='mask-describe'>qdwefwewr</div>"+*/
							"<div style='clear:both'></div>"+
						"</div>"+
						"<div class='master-works'>"+
							"<span>作者作品：</span>"+
							"<ul>"+
							"</ul>"+
						"</div>"+
					"</div>"+
					"<div class='order-wrap'>"+
						"<a href='' class='order-btn car-btn' >加入选购车</a>"+
						"<a href='"+domain+"/demandHall/demand_publish.html' class='order-btn order' >立即预约</a>"+
					"</div>"+
				"</li>";
	var data = {cp:page,rows:size,scenes:scenes,category:category,contentForm:contentForm,creationPrice:creationPrice,creationTime:creationTime,lableId:createStyID,likeName:keyWords},
	searchTxt = $.trim($('#js-search-text').val());
	if(searchTxt.length > 0){
		data.likeName = searchTxt
	} else {
		data.likeName = '';
	}

	for(var i in data){
		if(data[i]===''||data[i]===undefined){
			delete data[i]
		}
	};
	var empty = "<div class='no-data-wrap' id='js-no-data-wrap'>"+
    "<img src='"+domain+"/images/commonFragments/no_data.png' >"+
    "<p>没找到符合条件的创作者！ 请<a class=' qq' href='"+salesServiceUrl+"' id='qq'>联系客服</a>或拨打电话400 819 8818 </p>"+
	"</div>";
	new AjaxRequest({
		type: "post",
		url: url,
		param: data,
		isShowLoading: true,
		callBack: function(res){
			if(res.status == 1){
				if(!res.data.data.length){
					$(creatorContentList).html(empty);
					$('#qq').click(function(){
						qdService($(this));
					});
				   $('.pagination-wrap').css('display','none');
				   $('.pagination-note-wrap').hide();
				   return false
				}
			   $(creatorContentList).empty();
			   $('.pagination-wrap').css('display','block');
		   var total = res.data.total;
		   var currTotal = res.data.currTotal;
		   var dataList = res.data.data;
		   for(var i=0;i<dataList.length;i++){
			   $(creatorContentList).append(str);
		   };
   
		   var creatorContentListChild = $(creatorContentList).children();
		   //预约的line-height
		   
		   $(creatorContentListChild).each(function(index,item){
			   var recId = dataList[index].recId;//作者Id
			   var authorImg = dataList[index].authorImg;//作者头像
			   var authorNickname = dataList[index].authorNickname;//作者名称
			   var createdPrice = dataList[index].createdPrice//原创参考价
			   var contentFormName = dataList[index].contentFormName//创作形式
			   var scenesName = dataList[index].scenesName//使用场景
			   var CategoryName = dataList[index].CategoryName//擅长领域
			   var carFlag = dataList[index].flag;
   
			   var itemCarFlag = $(item).find('.order-wrap').children().eq(0);
			   itemCarFlag.attr('arrFlag',carFlag);
			   itemCarFlag.attr('id',recId);
			   if(itemCarFlag.attr('arrFlag')*1){
				   //存在选购车
				   itemCarFlag.text('已加入选购车');
				   //已加入选购车的样式
				   itemCarFlag.css({'background':carBackColor,'color':carColor,'cursor':"auto"});
			   }else if(!itemCarFlag.attr('arrFlag')*1){
				   //不存在选购车
				   itemCarFlag.text('加入选购车');
			   }else if(carFlag===undefined){
				   //未登录
				   itemCarFlag.text('加入选购车');
			   };
   
			   //加入选购车
			   itemCarFlag.click(function(e){
				   event.preventDefault();
				   if(!$(this).attr('arrflag')||$(this).attr('arrflag')==0){
					   var auImg = $(this).parent().parent().find('.author-image-wrap').find('img').attr('src');
					   addCartData('author',$(this).attr('id')*1,auImg,e);
				   }else{
					   return false 
				   }
			   })
   
			   if(!CategoryName){
				   $(item).find('.tag-wrap').css('display','none');
			   }
			   if(dataList[index].authorCreationTime===0){
				   dataList[index].authorCreationTime = 5;
			   }
			   var authorCreationTime = dataList[index].authorCreationTime//原创用时
			   var lableName = dataList[index].lableName//作者标签
			   var Introduction = dataList[index].Introduction//作者简介
			   var productionArray =  dataList[index].productionArray//作品列表
   
			   //标签处理
			   var lableNameParent = $(item).find('.tag-wrap').children('ul');
			   if(lableName){
				   var lableNameArray = lableName.split('.');
				   for(var j=0;j<lableNameArray.length;j++){
					   $(lableNameParent).append('<li></li>')
				   };
				   var lableList =  $(item).find('.tag-wrap').children('ul').children();
				   $(lableList).each(function(index2,item){
					   $(item).text(lableNameArray[index2]);
				   })
			   } else {
				   lableNameParent.siblings('.labal-name').hide()
			   }
   
			   //作者默认头像
			   if(!authorImg){
				   authorImg = domain+'/images/contentBank/author-video.jpg'
			   };
   
			   $(item).children('.author-image-wrap').find('img').attr('src',authorImg);//作者头像
			   $(item).children('.author-image-wrap').find('span').text(authorNickname);//作者名称
			   $(item).children('.author-image-wrap').children('a').attr('href', domain+"/contentBank/author_detail_"+recId+".html");
			   var authorInfo = $(item).children('.author-info').children();
   
			   
			   $(item).find('.order').click(function(e){
				   e.preventDefault();
				   getUserLoginInfo(function(isLogin){
					   if(isLogin== 'Y'){
						   localStorage.setItem('isPubIDs',1);
						   localStorage.setItem('authorIDs',recId);
						   var postUrl = newDomain + '/cart/shoppingCart_addAuthor';
						   commonFun.commonAjax(postUrl,'post',{productId:recId},function(result){
							   var shoppingCartId = result.data.shoppingCartId;
							   localStorage.setItem('shoppingCartId',shoppingCartId);
							   window.location.href= domain+"/demandHall/demand_publish.html";
						   })
					   }
				   },'order')
			   })
   
   
			   //默认的参考价
			   if(!createdPrice){
				   createdPrice = '按需定价'
			   }else{
				   if(createdPrice == '--'){
					   var detailUrl = domain+"/contentBank/author_detail_"+recId+".html";
					   createdPrice = '<a href="'+detailUrl+'" target="_blank" style="cursor: pointer;font-size: 14px;" class="color-blue">'+loginAvailable+'</a>';
				   } else {
					   createdPrice ='￥'+ createdPrice.toString().replace(/(\d{1,3})(?=(\d{3})+$)/g,'$1,');
				   }
			   }
			   $(authorInfo).eq(0).find('span').html(createdPrice);//参考价
			   $(authorInfo).eq(1).find('span').text(contentFormName);//创作形式
			   $(authorInfo).eq(2).find('.good-content').text(scenesName);//使用场景
			   $(authorInfo).eq(3).find('span').text(CategoryName);//擅长领域
			   $(authorInfo).eq(4).find('span').text(authorCreationTime+'天');//原创用时
					
			   //默认的描述
			   if(!Introduction){
				   Introduction = '无'
			   }
			   var introStr = $(item).find('.author-works-info').children('.introduction').children('p').children('a');
			   introStr.html(Introduction);//作者简介
			   introStr.attr('href',domain+"/contentBank/author_detail_"+recId+".html");
			   // $(item).find('.author-works-info').children('.introduction').children('.mask-describe').text(Introduction);
			   // $(item).find('.author-works-info').children('.introduction').mousemove(function(e){
			   // 	var x = e.pageX-$(this).offset().left;
			   // 	var y =e.pageY-$(this).offset().top+20;
			   // 	$(this).children('.mask-describe').css({'left':x+'px','top':y+'px'});
			   // });
			   /*$(item).find('.author-works-info').children('.introduction').hover(function(e){
				   var x = e.pageX-$(this).offset().left;
				   var y =e.pageY-$(this).offset().top+20;
				   $(this).children('.mask-describe').css({'left':x+'px','top':y+'px'});
			   })*/
   
			   //处理代表作
			   if(productionArray.length===0){
				   $(item).find('.master-works').css('display','none');
			   }else{
				   $(item).find('.master-works').css('display','block');
				   var worksStr;
				   var workflag = true;
				   var worksListParent = $(item).find('.master-works').children('ul');
				   var workNum =  productionArray.length<=3? productionArray.length:3;
				   for(var i=0;i<workNum;i++){
					   if(!(productionArray[i].productionImage)){
						   workflag = false;
					   };	
				   };
				   if(!workflag){
					   worksStr = "<li style='width:95%;float:none;height:33px;border-bottom:1px dotted #c1c1c1;line-height:33px;'><a href='' target='_blank' ><p class='no-float-describe'></p></a></li>";
					   $(item).find('.master-works').children('span').css('line-height','35px');
   
				   }else{
					   worksStr = "<li>"+
									   "<a href='' target='_blank'>"+
										   "<img src=''>"+
										   "<p class='float-describe'></p>"+
									   "</a>"+
								   "</li>";
					   $(item).find('.master-works').children('span').css('line-height','')
				   };
				   
				   for(var i=0;i<workNum;i++){
					   $(worksListParent).append(worksStr);
				   };
				   var worksList = $(item).find('.master-works').children('ul').children();
				   $(worksList).each(function(index1,item1){
					   if(workflag){
						   $(item1).find('img').attr('src',productionArray[index1].productionImage);
					   }
					   if(!(productionArray[index1].production)){
						   $(item1).find('.float-describe').css('display','none');
					   }
					   $(item1).find('p').html(productionArray[index1].production);
					   var production = productionArray[index1].production;
					   //跳转作品详情
				   /*	if(production.indexOf('[视频]')!=-1){
						   $(item1).find('a').attr('href',productionArray[index1].productUrl);
					   }else{*/
						   $(item1).find('a').attr('href',productionArray[index1].productionUrl+'.html');
					   // }
				   });
			   }
			   
			   //立即预约的id传入
			   $(item).find('.order-wrap').click(function(){
				   window.localStorage.setItem('useScenes',useScenesNum);
				   window.localStorage.setItem('contentForm',contentFormNum);
				   window.localStorage.setItem('contentType',contentTypeNum);
				   window.localStorage.setItem('recId',recId);
				   window.localStorage.setItem('authorNickname',authorNickname);
			   });
		   });
   
		   	$.each($('.author-image-wrap a img'),function(i, e){
				//    console.log(e)
				e.onerror=function(){
					e.src = '/static/images/user.png';
					// e.alt = '图片加载失败';
				}
			}); 

		   //分页
		   $('.pagination-wrap').pagination(total,{
			   current_page: page,//当前页
			   items_per_page: size,
			   prev_text: prev_text,
			   next_text: next_text,
			   num_display_entries:5,
			   callback: function(newpage){
				   if(newpage > 3){
					   var getLoginFun = function (isLogin) {
						   if(isLogin == 'Y'){
							   getAuthorList(listurl,'post',newpage,size,useScenesNum,contentFormNum,contentTypeNum,priceNum,useTimeNum);
							   window.scrollTo(0,0);
						   }
					   };
					   getUserLoginInfo(getLoginFun, 'showpop', 'pagination');
				   } else {
					   getAuthorList(listurl,'post',newpage,size,useScenesNum,contentFormNum,contentTypeNum,priceNum,useTimeNum);
					   window.scrollTo(0,0);
				   }
			   }
		   });
		   
		   };
		}
	})
}

function updateList(){
	var parentid = $(this).parent().attr('num');
	
	if(parentid==1){
		if(!Number($(this).attr('flag'))){
			return false;
		};
		var useSceneId = $(this).attr('id');
		useScenesNum = useSceneId;
	}
	else if(parentid==2){
		if(!Number($(this).attr('flag'))){
			return false;
		};
		var contentFormId = $(this).attr('id');
		contentFormNum = contentFormId;
	}
	else if(parentid==3){
		if(!Number($(this).attr('flag'))){
			return false;
		};
		var contentTypeId = $(this).attr('id');
		contentTypeNum = contentTypeId;
	}
	else if(parentid==4){
		var priceId = $(this).children('span').text();
		if(priceId==1000){
			priceNum = '0-1000'
		} else if(priceId == 5000){
			priceNum = '0-5000'
		} else {
			priceNum = priceId;
		}
	}
	else if(parentid==5){
		var useTimeId = $(this).children('span').text();
		useTimeNum = useTimeId;
	}
	else if(parentid==6){
		if(!Number($(this).attr('flag'))){
			return false;
		};
		createStyleId = $(this).attr('id');
	}
	$(this).siblings().removeClass('active-nav');
	$(this).addClass('active-nav');
	
	$('#js-start-price').val('');
	$('#js-end-price').val('');
	getAuthorList(listurl,'post',1,'',useScenesNum,contentFormNum,contentTypeNum,priceNum,useTimeNum,createStyleId);
};


$('#search-btn').click(function(){
	keyWords = $.trim($('#search-val').val());
	getAuthorList(listurl,'post',1,'',useScenesNum,contentFormNum,contentTypeNum,priceNum,useTimeNum,createStyleId);
});
$('#search-val').keydown(function(e){
	keyWords = $.trim($('#search-val').val());
	e.keyCode==13&&getAuthorList(listurl,'post',1,'',useScenesNum,contentFormNum,contentTypeNum,priceNum,useTimeNum,createStyleId);
});

$('#release-btn').click(function(){
	//这里需要传值
	localStorage.setItem('isPubIDs',0);
	window.location.href = domain+'/demandHall/demand_publish.html'
});


$('#create-price').children().click(updateList);
$('#create-use-time').children().click(updateList);

getAuthorDic();
getAuthorList(listurl,'post');
showMore($('#useScenes').next());
showMore($('#contentForm').next());
showMore($('#contentType').next());
showMore($('#createStyle').next());

// 自定义价格查询
$('#js-self-price-btn').on('click', function () {
	var sPrice = parseFloat($.trim($('#js-start-price').val())),
		ePrice = parseFloat($.trim($('#js-end-price').val()));
	if(sPrice > 0 || ePrice > 0){
		if(sPrice > ePrice){
			priceNum = ePrice +'-'+ sPrice;
		} else {
			priceNum = sPrice +'-'+ ePrice;
		}
		if(sPrice > 0 && !ePrice){
			priceNum = sPrice;
		}
		if(ePrice > 0 && !sPrice){
			priceNum = ePrice;
			priceNum = 0 +'-'+ ePrice;
		}
	}

	$('#create-price').find('li').eq(0).addClass('active-nav').siblings().removeClass('active-nav');
	getAuthorList(listurl,'post',1,'',useScenesNum,contentFormNum,contentTypeNum,priceNum,useTimeNum,createStyleId);
})