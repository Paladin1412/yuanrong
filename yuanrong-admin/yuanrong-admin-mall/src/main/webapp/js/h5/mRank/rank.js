
$(function(){
	var dataType = 'listArticle';
	var listArticleWrap = $('.list-article-wrap');
	var listConWrap = $('.list-con-wrap')
	var rangeTime,categoryName,rangeTimeTag = $('.rangeTime');
	var time = $('.shujuzq');
	var cp=1;
    var rows=50;

    // 排行榜123小图标
    var noOneImg = '<img src="/images/h5/rank/gold.png" alt="">',
    	noTwoImg = '<img src="/images/h5/rank/silver.png" alt="">',
    	noThreeImg = '<img src="/images/h5/rank/copper.png" alt="">';

	// tab切换
	$('.header>span').click(function(){
		var _this = $(this);
		var ipOrContent = _this.data('id');
		var index = _this.index();
		$('.inner-content').scrollLeft(0);
		_this.addClass('on').siblings().removeClass('on');
		$('.content>div').eq(index).removeClass('hide').siblings().addClass("hide");
		time.addClass('hide');
		$(window).scrollTop(0);
		$('.rangeTime').removeClass('colour');
		$('.time').attr('src','/images/h5/rank/time_1.png');
		$('.drop_down').attr('src','/images/h5/rank/drop_down.png');
		if(ipOrContent==1){
			dataType = 'listArticle';
		}else if(ipOrContent==2){
			dataType = 'listIPCreativity';
		}
		getTime(dataType);
	})

	// 默认点击事件
	$('.header>span').eq(0).click();

	// 图标事件
	$('.rangeTime').click(function(){
		if(time.hasClass('hide')){
			$('.rangeTime').addClass('colour');
			$('.time').attr('src','/images/h5/rank/time_2.png');
			$('.drop_down').attr('src','/images/h5/rank/drop_up.png');
			time.removeClass('hide');
		}else {
			time.addClass('hide');
			$('.rangeTime').removeClass('colour');
			$('.time').attr('src','/images/h5/rank/time_1.png');
			$('.drop_down').attr('src','/images/h5/rank/drop_down.png');

		}
	})

	$(document).scroll(function(){
		var scrollTop = $(window).scrollTop();
		if(scrollTop>=150){
			$('.header').css({'position':'fixed','top':'0'})
		}else if(scrollTop<150) {
			$('.header').css({'position':'absolute','top':''})
		}
	})
	
	// 时间
	function getTime(typeName){
		var postUrl = '/baseRank/selectRangeTime';
		commonFun.commonAjax(postUrl, 'post', {typeName}, function(res){
			if(res.status==1){
				var timeList = res.data;
				var shujuzq = $('.shujuzq');
	        	shujuzq.html('');
	        	if(timeList.length>0){
	        		timeList.forEach(function(item,index){
						$(`<li>${item}</li>`).appendTo(shujuzq);
					})
					$('.shujuzq li').eq(0).addClass('colour');
					for(var i = 0, len = shujuzq.length; i < len; i++){
						shujuzq.eq(i).off('click').on('click','li',function(){
							var _this = $(this);
							time.addClass('hide');
							_this.addClass('colour').siblings().removeClass('colour');
							$('.rangeTime').removeClass('colour');
							$('.time').attr('src','/images/h5/rank/time_1.png');
							$('.drop_down').attr('src','/images/h5/rank/drop_down.png');
							$(window).scrollTop(0);
							rangeTimeTag.html(_this.html());
							rangeTime = rangeTimeTag.html();
							if(typeName=="listArticle"){
								wenzhang(cp,rows,categoryName,rangeTime);
							}else if(typeName=="listIPCreativity"){
								ipList(cp,rows,categoryName,rangeTime)
							}
						})
					}
					rangeTimeTag.html($('.shujuzq li.colour').html());
					rangeTime = rangeTimeTag.html();
					
					fenlei(dataType,rangeTime)
	        	}
			} else {
				//暂无数据
			}
		})
	}

	// 分类数据
	function fenlei(typeName,rangeTime){
		var postUrl = '/dictInfo/getDictInfoByType';
		commonFun.commonAjax(postUrl, 'post', {type:13}, function(res){
			if(res.status==1){
				var fenleiList = res.data;
				var remenfl = $('.remenfl');
				remenfl.html('');
				if(fenleiList.length>0){
					fenleiList.forEach(function(item,index){
						$(`<li>${item.name}</li> `).appendTo(remenfl);
					})
					$('.remenfl li').eq(0).addClass('colour').siblings().removeClass('colour');
					for(var i = 0, len = remenfl.length; i < len; i++){
						remenfl.eq(i).off('click').on('click','li',function() {
							var _this = $(this);
							_this.addClass('colour').siblings().removeClass('colour');
							categoryName = _this.html();
							$(window).scrollTop(0);
							if(typeName=="listArticle"){
								wenzhang(cp,rows,categoryName,rangeTime);
							}else if(typeName=="listIPCreativity"){
								ipList(cp,rows,categoryName,rangeTime)
							}
						})
					}
					categoryName = $('.remenfl li.colour').html();
					if(typeName=="listArticle"){
						wenzhang(cp,rows,categoryName,rangeTime);
					}else if(typeName=="listIPCreativity"){
						ipList(cp,rows,categoryName,rangeTime)
					}
				}
			}
		})
	}
	
	// 内容价值
	function wenzhang(cp,rows,categoryName,rangeTime){
		var postUrl = '/rank/selectAllListArticle_M';
		commonFun.commonAjax(postUrl, 'post', {cp,rows,categoryName,rangeTime}, function(res){
			if(res.status==1){
				var wenzhangList = res.data.data;
				if(wenzhangList.length>0){
					listArticleWrap.html('');
					wenzhangList.forEach(function(item,index){
						var num = (index + 1 + (cp - 1) * rows);
					    var noImg = '';
					    if(num == 1) {
					        noImg = noOneImg;
					    } else if(num == 2) {
					        noImg = noTwoImg;
					    } else if(num == 3) {
					        noImg = noThreeImg;
					    } else {
					        noImg = num;
					    }
						$(`<ul class="fixedClear">
							<li class='list-article-title'><span class="li-width42">${noImg}</span><a href="${item.link}">${item.title}</a></li>
							<li class='list-article-index'>
								<div>
									<p class="ip-name-cn">${item.totalIndex}</p>
									<p class="ip-name-en">综合指数</p>
								</div>
							</li>
						</ul>`).appendTo(listArticleWrap);
					})
				}
			}
		})
	}

	// ip创造力
	function ipList(cp,rows,categoryName,rangeTime){
		var postUrl = '/rank/selectAllListIPCreativity_M';
		commonFun.commonAjax(postUrl, 'post', {cp,rows,categoryName,rangeTime}, function(res){
			if(res.status==1){
				var ipList = res.data.data;
				if(ipList.length>0){
					listConWrap.html('');
					ipList.forEach(function(item,index){
						var num = (index + 1 + (cp - 1) * rows);
					    var noImg = '';
					    if(num == 1) {
					        noImg = noOneImg;
					    } else if(num == 2) {
					        noImg = noTwoImg;
					    } else if(num == 3) {
					        noImg = noThreeImg;
					    } else {
					        noImg = num;
					    }
						$(`<ul class="ipList fixedClear">
							<li class="li-width42">${noImg}</li>
							<li class="fixedClear li-width203">
								<div class="ip-avatar-wrap" style="background: #eee url(${item.headImg}) center/500% no-repeat scroll;"></div>
								<div class="ip-name-wrap">
									<p class="ip-name-cn">${item.name}</p>
									<p class="ip-name-en">${item.weixinId}</p>
								</div>
							</li>
							<li class="list-article-index">
								<div>
									<p class="ip-name-cn">${item.totalIndex}</p>
									<p class="ip-name-en">综合指数</p>
								</div>
							</li>
						</ul>`).appendTo(listConWrap);
					})
				}
			}
		})
	}

})