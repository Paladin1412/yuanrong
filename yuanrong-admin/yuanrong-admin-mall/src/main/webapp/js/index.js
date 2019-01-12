
var isLoginFlag = false;
var getLoginStatus = function (isLogin) {
    if(isLogin == 'Y'){
        isLoginFlag = true;
        $('#js-index-side-login').hide();

        $.each($('.num-shelter-btn'), function(i, e){
            if(i == 0){//买家中心首页
                var url = centerUrl + 'buyer/index';
                $(e).find('a').attr('href', url);
            } else if(i == 1){//添加作品
                var url = centerUrl+'seller/sourceManage/article/articleEdit?add=add';
                $(e).find('a').attr('href', url);
            } else if(i == 2){//添加创作者
                var url = centerUrl+'seller/sourceManage/author/authorEdit/0';
                $(e).find('a').attr('href', url);
            } else if(i == 3){//添加账号
                var url = centerUrl+'seller/sourceManage/account/accountAdd';
                $(e).find('a').attr('href', url);
            }
        })
    } else {
        $('#js-index-side-login').show();
        isLoginFlag = false;
        
        $.each($('.num-shelter-btn'), function(i, e){
            if(i == 0){
                var url = centerUrl +'register/?callback=/#/buyer/index';
                $(e).find('a').attr('href', url);
            } else if(i == 1){
                var url = centerUrl +'login/?callback=/#/seller/sourceManage/article/articleEdit?add=add';
                $(e).find('a').attr('href', url);
            } else if(i == 2){
                var url = centerUrl +'login/?callback=/#/seller/sourceManage/author/authorEdit/0';
                $(e).find('a').attr('href', url);
            } else if(i == 3){
                var url = centerUrl +'login/?callback=/#/seller/sourceManage/account/accountAdd';
                $(e).find('a').attr('href', url);
            }
        })
    }
};
console.log(isLoginFlag, '--isLoginFlag-index')
getUserLoginInfo(getLoginStatus);


$(document).ready(function(){
    var loginPopWrapDom = $('#js-login-pop-wrap');//登陆弹框
    var loginDoneWrap = $('#js-login-done-wrap');//已登录弹框
    var closeLoginPopBtnDom = $('.close-login-pop-btn');//登陆弹框关闭按钮
    var loginPopBtnDom = $('#js-login-btn');//登陆弹框登陆按钮
    var loginWrapDom = $('#js-login-wrap');//登陆—媒体主，广告主
    var dataType;
    var source;
    var joinBtn1 = $('#js-join-btn1');//广告主入驻
    var joinBtn2 = $('#js-join-btn2');//创作者入驻
    var timer = null;//倒计时
    var countdown = 60;

    var numSectionWrapDom = $('#js-num-section-wrap'),//导航底部数量
        topScrollUlWrapDom = $('#js-scroll-ul');
 
    //banner下方的nav
    $('li', numSectionWrapDom).mouseenter(function(){
        // $('#qidian_wpa_2852157214_29').click();
        var $this = $(this);
        $this.find('.num-section-shelter-wrap').addClass('num-shelter-hover').siblings().find('.num-section-shelter-wrap').removeClass('num-shelter-hover');
    }).mouseleave(function(){
        var $this = $(this);
        $this.find('.num-section-shelter-wrap').removeClass('num-shelter-hover');
    })
    // // 创作者模块
    // $.each($('.item-li-author'),function (i, e) {
    //     console.log(i);
        
    //     $('.item-li-author').eq(i).mouseover(function(e){
    //         if(isMouseEnter == true){
    //             return;
    //         } else {
    //             var $this = $(this);
    //             e.stopPropagation();
    //             if($this.find('.section-bottom-wrap').hasClass('hide-item')){
    //                 $this.find('.section-bottom-wrap').removeClass('hide-item');
    //                 $this.siblings().eq(i+2).find('.section-bottom-wrap').addClass('hide-item');
    //             }
    //             isMouseEnter = true;
    //         }
    //     }).mouseleave(function(){
    //         if(isMouseEnter == false){
    //             return;
    //         } else {
    //             var $this = $(this);
    //             $('.item-li-author:lt(3)').find('.section-bottom-wrap').removeClass('hide-item');
    //             $('.item-li-author:gt(2)').find('.section-bottom-wrap').addClass('hide-item');
    //             isMouseEnter = false;
    //         }
    //         isMouseEnter = false;
    //     })

    // })

    
    //点击首页登陆
    $('span', loginWrapDom).off('click').on('click', function(){
        var $this = $(this);
        dataType = $this.data('type');
        $('#js-nav-wrap').children().each(function(){
            if ($(this).hasClass('active')){
                source = $(this).find('a').text();
            }
        });
        goLoginPage(dataType);
    })

    //点击“我要入驻”
    // joinBtn1.off('click').on('click', function(){
    //     var $this = $(this),
    //         type = $this.data('type'),
    //         id = $this.data('id');
    //         goLoginPage(id);
    // })


    joinBtn2.off('click').on('click', function(){
        var getLoginFun = function (isLogin) {
            $('.pop-wrap').hide();
            if(isLogin == 'Y'){
                var toRUl = centerUrl + 'seller/index';
            } else {
                var toRUl = centerUrl +'register';
            }
            // window.open(toRUl, '_blank');
            window.location.href = toRUl;
        }
        getUserLoginInfo(getLoginFun);
    })

    // //首页文字滚动
    // $.fn.extend({
	// 	"slideUp":function(value){
			
	// 		var _this = this;
	// 		//默认参数
	// 		value = $.extend({
	// 			 "liHei":"30",
	// 			 "time":2000,
	// 			 "moveTime":1000
	// 		},value)
			
	// 		//向上滑动动画
	// 		function animateAuto(){
	// 			$("li:first",_this).animate({"margin-top":-value.liHei}, value.moveTime, function(){
	// 				$(this).css("margin-top", 0).appendTo("#js-scroll-ul");
	// 			})
	// 		}
			
	// 		//自动间隔时间向上滑动
	// 		var animateFun = setInterval(animateAuto,value.time);
			
	// 		//悬停时停止滑动，离开时继续执行
	// 		$(_this).children("li").hover(function(){
	// 			clearInterval(animateFun);			//清除自动滑动动画
	// 		},function(){
	// 			animateFun = setInterval(animateAuto,value.time);	//继续执行动画
	// 		})
	// 	}	
	// })
	// topScrollUlWrapDom.slideUp();	
    
});

    