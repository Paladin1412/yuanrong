//添加顶部的样式
$('#js-top-wrap').addClass('top-wrap-no-banner');

function deleteAt(params){
	for(var i in params){
		if(params[i]===''||params[i]===undefined){
			delete params[i]
		}
	};
}
//获取作者信息和相似作者

var endNum = window.location.href.lastIndexOf('.');
var startNum = window.location.href.lastIndexOf('_');
//作者的ID
var recid  = window.location.href.slice(startNum+1,endNum)*1;
console.log(recid);
var likeAuthor,length,auImg,indexcontentForm=0;

var changeBatch = function(likeAuthor,start,end){
	var likeAuthorCur = likeAuthor.slice(start,end);
	var likeAuthorEle = $('#similar');
	//需要修改
	likeAuthorCur.forEach((item,index)=>{
		likeAuthorEle.append('<li class="fixedClear">'+
							'<a href="'+domain+'/contentBank/author_detail_'+item.recId+'.html" target="_blank"><img src="'+item.authorImg+'"></a>'+
							'<div>'+
								'<a href="'+domain+'/contentBank/author_detail_'+item.recId+'.html" target="_blank"><strong>'+item.authorNickname+'</strong></a>'+
								'<br>'+
								'<p>'+item.Introduction+'</p>'+
							'</div>'+
						'</li>')
	});		
}


//获取作品列表的函数
var authorWorks  = function(authorId,publishStatusIndex,cp,rows,contentFormId){
	var cp = cp||1;
	var rows = rows||4;
	var worksOarentEle = $('#works-list-wrap');
	var data = {authorId,publishStatusIndex,cp,rows,contentFormId};
	deleteAt(data);
	commonFun.commonAjax(newDomain+'/author/getAuthorPro','post',data,function(result){
		if(result.status==1){

			var resultArr = result.data.data.authorProList;//作品的列表
			var notOpenNum = result.data.data.notOpenNum;//未发布个数
			var openNum = result.data.data.openNum;//发布个数
			var contentFormName = result.data.data.contentFormName ? result.data.data.contentFormName : '';//内容形式Name
			var contentFormId = result.data.data.contentFormId ? result.data.data.contentFormId : '';//内容形式id
			var createFromArr = contentFormName.split('、');
			var createFromIdArr = contentFormId.split('、');
			
			if(contentFormName){
				var createFromArr = contentFormName.split('、');
			} else {
				var createFromArr = [];
			}
			if(contentFormId){
				var createFromIdArr = contentFormId.split('、');
			} else {
				var createFromIdArr = [];
			}
			//这里是作品的分类，大于1 显示全部，1个不显示
			var catogary = $('#catogary');
			catogary.empty();
			if(createFromArr.length>1){
				catogary.append('<li id="">全部</li>')
				createFromArr.forEach((item,index)=>{
					catogary.append('<li id="'+createFromIdArr[index]+'">'+item+'</li>')
				});
			};

			catogary.children().eq(indexcontentForm).addClass('active')


			if(publishStatusIndex){
				if(openNum){
					worksOarentEle.empty();
				}
			}else{
				if(notOpenNum){
					worksOarentEle.empty();
				}
			}

			var total = result.data.total;
			var cp = result.data.cp;
			
			if(!notOpenNum){//未发布作品为0
				$('#no-work').css('display','none');
			} 
			if(!openNum){
				$('#publish-wrok').css('display','none');
			}
			if(!notOpenNum && !openNum){
				$('#no-work').show();
			}
			$('#publish-wrok').children('span').text('('+openNum+')');
			$('#no-work').children('span').text('('+notOpenNum+')');
			if(!resultArr.length){
				var empty = "<div class='no-data-wrap' id='js-no-data-wrap'>"+
						    "<img src='"+domain+"/images/commonFragments/no_data.png' >"+
						    "<p>暂无数据</p>"+
							"</div>";
				//请<a class=' qq' id='qq'>联系客服</a>或拨打电话400 819 8818 </a>
				if(publishStatusIndex){//已发布作品为空
					authorWorks(recid,0,1);
				} else {//未发布作品
					$(worksOarentEle).html(empty);
				}
                $('#qq').click(function(){
                    qdService($(this));
				});
				// $('#no-work').click();
				return false
			}else{
				resultArr.forEach((item,index)=>{
					var currentForm = item.contentFormName;//当前作品的类型
					// var coverLocalUrl = item.coverLocalUrl||domain+"/images/contentBank/online-price.png";
					var title = item.title;//当前的标题
					var createdTime = item.createdTime;//创作的时间
					var productQuotedPrice = item.productQuotedPrice//作品报价
					var localcontent = $.trim(item.localcontent) || '  ';//内容本地化
					var calendarName = item.calendarName;//营销场景
					var tags = item.tags;//高频词
					var contentScore =item.contentScore//内容指数
					var isRepresentative = item.isRepresentative//是否是代表作品
					var publishTime = item.publishTime;//发布时间
					var publishPlatform = item.publishPlatform;//发布平台
					var recId = item.recId;
					var originalScore = item.originalScore ? item.originalScore : 0;//原创度
					if(originalScore){
						var hideScore = '';
					} else {
						var hideScore = 'hide';
					}
					if(item.coverLocalUrl){
						var coverLocalUrl = item.coverLocalUrl;
					} else {
						if(item.coverLocalUrl == 'None'){
							var coverLocalUrl = (domain+'/static/images/article/bg_image_big.jpg');
						}
						var coverLocalUrl = (domain+'/static/images/article/bg_image_big.jpg');
					}
					worksOarentEle.append('<li class="fixedClear">'+
									'<div class="img-wrap">'+
										'<a href="'+domain+'/contentBank/article_detail_'+recId+'.html" target="_blank"><img src="'+coverLocalUrl+'"></a>'+
										'<div class="master-work">代表作</div>'+
										'<div class="work-type">'+currentForm+'</div>'+
									'</div>'+
									'<div class="works-info-wrap">'+
										'<p class="fixedClear"><a href="'+domain+'/contentBank/article_detail_'+recId+'.html" target="_blank"><strong class="title">'+title+'</strong></a><i class="iconfont icon-weixin-copy1 weixin"> <span class="tip">在微信公众号发表过的原创作品，暂不支持授权转载到微信公众号</span></i></p>'+
										'<p class="works-time">'+
											'<span>'+createdTime+'</span>'+
											'<span>'+
												'<i class="iconfont icon-bangzhu bangzhu"></i>'+
												'<span class="reprint">哈哈</span>'+
												'<span class="reship-use">'+'￥'+productQuotedPrice+'</span>'+
											'</span>'+
										'</p>'+
										'<p class="item-describe">'+
											''+localcontent+''+
										'</p>'+
										'<p class="work-label-wrap">'+
											'<span>'+
												'<span class="work-label">营销场景:</span>'+
												'<span class="work-con">'+calendarName+'</span>'+
											'</span>'+
											'<span>'+
												'<span class="work-label">高频词:</span>'+
												'<span class="work-con">'+tags+'</span>'+
											'</span>'+
											'<span class="'+hideScore+'">'+
												'<span class="work-label color-blue" style="color: #4895e7;">原创度:</span>'+
												'<span class="work-con color-blue" style="color: #4895e7;">'+originalScore+'</span>'+
											'</span>'+
										'</p>'+
									'</div>'+
								'</li>');

					var workList = worksOarentEle.children();
					//买断价
					workList.each((index,item)=>{
						publishStatusIndex?$(item).find('.reprint').text('转载使用：'):$(item).find('.reprint').text('买断价：');
						var weixin = $(item).find('.weixin');
						var tip = $(item).find('.tip');
						if(resultArr[index].publishPlatform=='微信'){
							weixin.css('display','block');
						}

						if(item.tags == '' || item.tags == undefined){
                            $('#works-list-wrap li').eq(index).find('.work-label-wrap > span').eq(1).hide();
						}

						//代表作
						var isRep = resultArr[index].isRepresentative;
						if(isRep){
                            $(item).find('.master-work').css('display','block')
						}else{
                            $(item).find('.master-work').css('display','none')
						}

						weixin.hover(()=>{
							tip.css('display','block');
						},()=>{
							tip.css('display','none');
						})

					});

				});
				if(!publishStatusIndex){
					$('.master-work').css('display','none');
					$('.bangzhu').css('visibility','visible');
				}
				$('#works-list-wrap').children().find('.work-con').each((index,item)=>{
					if($(item).text()==='undefined'){
						$(item).parent().css('display','none');
					}
				});

				$('.bangzhu').click(function(){
					$('#js-pop-example-wrap').css('display','block');
				})
	
			}

			//根据创作形式加载作品
			catogary.children().click(function(){
				indexcontentForm = $(this).index();
				var formId = $(this).attr('id');
				authorWorks(recid,publishStatusIndex,'','',formId);
			})
			
			$.each($('.img-wrap a img'),function(i, e){
				e.onerror=function(){
					e.src = '/static/images/article/bg_image_big.jpg';
					// e.alt = '图片加载失败';
				}
			}); 
			$('.pagination-wrap').pagination(total,{
				current_page: cp,//当前页
			    items_per_page: rows,
				prev_text: prev_text,
				next_text: next_text,
			    num_display_entries:2,
			    callback: function(newpage){
			    	console.log(cp);
			    	authorWorks(recid,publishStatusIndex,newpage);
			    }
			});

		};
	})
}



//作者基本信息和相似作者基本信息s
commonFun.commonAjax(newDomain+'/author/getAuthorInfo','post',{recid},
	function(result){
	
	$('.prompt').css('display',result.data.identify==1?'block':'none');
	if(result.data.identify==1){
		$('.top-wrap-no-banner').css({'boxShadow':'none'});
	}
	/*var authorImage = result.data.authorInfo.authorImg||domain+'/images/contentBank/author-video.jpg';//作者头像
	auImg=authorImage
	var authorNickname = result.data.authorInfo.authorNickname||'——'//作者昵称*/
	var flag = result.data.authorInfo.flag;//选购车的标志
	if(flag){
		$('.car-btn').css({'cursor':'auto','background':'rgb(248, 249, 251)','color':'rgb(211, 213, 217)'});
		$('.car-btn').text('已加入选购车')
	}else{
		$('.car-btn').css({'cursor':'pointer','background':'#f6faff','color':'#4895e7'});
		$('.car-btn').text('加入选购车')
	}
	
	$('.car-btn').attr('flag',flag);
	
	//相似作者推荐
	likeAuthor = result.data.likeAuthor;//返回的相似作者数据
	if(likeAuthor.length<=5){
		$('#change-batch-btn').css('display','none');
		if(likeAuthor.length==0){
			// 如果相似作者为空的话，调用列表创作者
			var authorUrl = newDomain+'/author/getAuthorList';
			$('#change-batch-btn').css('display','block');
			commonFun.commonAjax(authorUrl,'post',{rows:15},function(result){
				likeAuthor = result.data.data;
				likeAuthor.forEach((item,index)=>{
					if(recid==item.recId){
						likeAuthor.splice(item,1);
					}
				})
				length = Math.ceil(likeAuthor.length/5);
				changeBatch(likeAuthor,0,5);
			});
		}
	}

	length = Math.ceil(likeAuthor.length/5);
	changeBatch(likeAuthor,0,5);
	authorWorks(recid,1,1);

});


var peiceNum = $('.price').html();
if(peiceNum.indexOf('按需定价')==-1){
	$('.price').html(commonFun.formatNumber(peiceNum));
}

var num = 1;

$('#change-batch-btn').click(function(){
	if(num>length-1){
		num = 0;
	}
	$('#similar').empty();
	changeBatch(likeAuthor,num*5,(num+1)*5);
	num++;
});


$('#no-work').click(function(){
	indexcontentForm = 0;//记录内容形式 加class
	$('#works-list-wrap').empty();
	$(this).css('border-bottom','2px solid #4895e7');
	$('#publish-wrok').css('border',0)
	authorWorks(recid,0,1);
});

$('#publish-wrok').click(function(){
	indexcontentForm = 0;
	$('#works-list-wrap').empty();
	$(this).css('border-bottom','2px solid #4895e7');
	$('#no-work').css('border',0)
	authorWorks(recid,1,1);
})

$('.order-btn').click(function(){
	// recid
	localStorage.setItem('authorIDs',recid);
	localStorage.setItem('isPubIDs',1);
	var postUrl = newDomain + '/cart/shoppingCart_addAuthor';
	commonFun.commonAjax(postUrl,'post',{productId:recid},function(result){
		if(result.status==1){
			var shoppingCartId = result.data.shoppingCartId;
			localStorage.setItem('shoppingCartId',shoppingCartId);
			window.location.href= domain+"/demandHall/demand_publish.html";
		}else {
			layer.msg(result.msg, {time: 1500});
		}
		
	})
})

$('.car-btn').click(function(e){
	if(!Number($(this).attr('flag'))){
		addCartData('author',recid,auImg,e);
	}else{
		return false 
	}	
})

$('.back').click(function(e){
	e.preventDefault();
	window.location.href=document.referrer;
})