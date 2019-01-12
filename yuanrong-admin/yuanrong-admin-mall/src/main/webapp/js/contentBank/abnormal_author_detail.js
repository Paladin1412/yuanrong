$('#js-top-wrap').addClass('top-wrap-no-banner');
var likeAuthor,length;
var carBackColor ='#f8f9fb';
var carColor='#d3d5d9'
//相似作者推荐
var similarAuthor = function(){
	var url = newDomain+'/author/getAuthorInfo';
	var authorUrl = newDomain+'/author/getAuthorList';
	var type = 'post';
	var endNum = window.location.href.lastIndexOf('.');
	var startNum = window.location.href.lastIndexOf('_');
	//作者的ID
	var recid  = window.location.href.slice(startNum+1,endNum)*1;
	var data = {
		recid
	}
	commonFun.commonAjax(url,type,data,function(result){
		var identify = result.data.identify;
		console.log(identify);
		if(!result.status){
			if(!identify){
				console.log('创作者不存在');
				//创作者不存在，重新调接口
				commonFun.commonAjax(authorUrl,'post',{pageSize:15},function(result){
					likeAuthor = result.data.data;
					likeAuthor.forEach(function(item){
                        item.createdPrice = item.createdPrice == 0 ? '按需定制': item.createdPrice;
					});
					length = Math.ceil(likeAuthor.length/5);
					changeBatch(likeAuthor,0,5);
				});
			}else{
				//相似作者
				 likeAuthor =result.data.likeAuthor;
				 length = Math.ceil(likeAuthor.length/5);
				 changeBatch(likeAuthor,0,5);
			}
		}
	})
}

var changeBatch = function(likeAuthor,start,end){
	var likeAuthorCur = likeAuthor.slice(start,end);
	var likeAuthorEle = $('#author-list');
	
	likeAuthorCur.forEach((item,index)=>{
		likeAuthorEle.append("<li>"+
					"<div class='author-image-wrap'>"+
						"<a href='"+domain+"/contentBank/author_detail_"+item.recId+".html' target='_blank'>"+
							"<img src='"+item.authorImg+"' width=100%><br>"+
							"<span>"+item.authorNickname+"</span>"+
						"</a>"+
					"</div>"+
					"<div class='author-info'>"+
						"<p>原创参考价：<span class='blue-48'>"+item.createdPrice+"</span></p>"+
						"<p>创作形式：<span>"+item.contentFormName+"</span></p>"+
						"<p class='good-wrap'><span class='good-labal'>使用场景：</span><span class='good-content'>"+item.scenesName+"</span></p>"+
						"<p>擅长领域：<span>"+item.CategoryName+"</span></p>"+
						"<p>原创用时：<span>"+item.authorCreationTime+"</span></p>"+
						"<div class='tag-wrap'>"+
						 	"<span class='labal-name'>创作风格：</span>"+
						 	"<ul>"+
						 	"</ul>"+
						 "</div>"+
					"</div>"+
					"<div class='author-works-info'>"+
						"<div class='introduction'>"+
							"<a href='"+domain+"/contentBank/author_detail_"+item.recId+".html' target='_blank' style='line-height:26px;'>"+
								"<span style='float:left;'>作者简介：</span>"+
								"<span class='fixedClear1 intro'>"+item.Introduction+"</span>"+
								// "<div class='mask-describe'>"+item.Introduction+"</div>"+
								"<div style='clear:both'></div>"+
							"</a>"+
						"</div>"+
						"<div class='master-works'>"+
							"<span>代表作品：</span>"+
							"<ul>"+
							"</ul>"+
						"</div>"+
					"</div>"+
					"<div class='order-wrap'>"+
						"<a href='' class='order-btn car-btn' >加入选购车</a>"+
						"<a href='"+domain+"/contentBank/publish_demand.html' class='order-btn order'>立即预约</a>"+
					"</div>"+
				"</li>");
	});	

	var likeAuthorEleChild = likeAuthorEle.children();
	likeAuthorEleChild.each((index,item)=>{
		//创作风格
		// console.log(likeAuthorCur[index].lableName);
		if(likeAuthorCur[index].lableName){
			var styleArr = likeAuthorCur[index].lableName.split('.');
			var styleEl = $(item).find('.tag-wrap').children('ul');
			styleArr.forEach((item1,index)=>{
				styleEl.append('<li>'+item1+'</li>')
			});	
		}
		
		// 作者简介mouseover
		// $(item).find('.author-works-info').children('.introduction').mousemove(function(e){
		// 	var x = e.pageX-$(this).offset().left;
		// 	var y =e.pageY-$(this).offset().top+20;
		// 	$(this).children('.mask-describe').css({'left':x+'px','top':y+'px'});
		// });
		// $(item).find('.author-works-info').children('.introduction').hover(function(e){
		// 	var x = e.pageX-$(this).offset().left;
		// 	var y =e.pageY-$(this).offset().top+20;
		// 	$(this).children('.mask-describe').css({'left':x+'px','top':y+'px'});
		// })


		//处理代表作
		var productionArray = likeAuthorCur[index].productionArray;
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
					// if(production.indexOf('[视频]')!=-1){
					// 	$(item1).find('a').attr('href',productionArray[index1].productUrl);
					// }else{
						$(item1).find('a').attr('href',productionArray[index1].productionUrl+'.html');
					// }
				});
			}


			var creatorContentListChild = $(likeAuthorEle).children();
			$(creatorContentListChild).each(function(index,item){

				var recId = likeAuthorCur[index].recId;
				var carFlag = likeAuthorCur[index].flag;
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
					var auImg = $(this).parent().parent().find('.author-image-wrap').find('img').attr('src');
					addCartData('author',$(this).attr('id')*1,auImg,e);
				})

				//立即预约
				$(item).find('.order').click(function(e){
					e.preventDefault();
					localStorage.setItem('isPubIDs',1);
					localStorage.setItem('authorIDs',recId);
					var postUrl = newDomain + '/cart/shoppingCart_addAuthor';
					commonFun.commonAjax(postUrl,'post',{productId:recId},function(result){
						var shoppingCartId = result.data.shoppingCartId;
						localStorage.setItem('shoppingCartId',shoppingCartId);
						window.location.href= domain+"/demandHall/demand_publish.html";
					})
				})

			})
			

		

	})

};


var num=1;
$('.chan-batch').click(()=>{

	if(num>length-1){
		num = 0;
	}
	$('#author-list').empty();
	changeBatch(likeAuthor,num*5,(num+1)*5);
	num++;
})

similarAuthor();
