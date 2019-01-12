var navWrapDom = $('#js-nav-wrap'),//nav导航
    topWrapDom = $('#js-top-wrap'),//导航wrap
    windowHei = $(window).height(),//获取窗口的高度
    windowHeiHalf = (windowHei / 2),//获取窗口的高度的一半
    loginPopNoteWrapDom = $('#js-pop-login-wrap'),
    sideCartNoLoginWrapDom = $('#js-cart-side-nologin-wrap'),
    pageNameArr = ['content_bank', 'author_images', 'publish_demand', 'graphics_writer', 'video_auther', 'ip_trade', 'ip_trade_distribution', 'ip_evaluation', 'about_us'];

//导航tab切换
getNavStatus();
function getNavStatus(){
    var getPageName = commonFun.getUrlPageName();
    var winScrollTop = document.documentElement.scrollTop || document.body.scrollTop;
    var getIsPubIDs = localStorage.getItem('isPubIDs') || 0;
	if(getPageName){
        var getMatchName = commonFun.pageNameReg(getPageName);
        // console.log(getMatchName)
        // getMatchName == 'article_detail'
        navWrapDom.show();
		if(getMatchName == 'content_bank' || getMatchName == 'author_images' || getMatchName == 'publish_demand' || getMatchName == 'graphics_writer' || getMatchName == 'video_auther' || /article_detail/.test(getPageName) || /author_detail/.test(getPageName) || getMatchName == 'original_work' || getMatchName == 'enterDetailPage'){//内容交易
            // || getMatchName == 'demand_hall'
            $('.nav-content-bank').addClass('active').siblings().removeClass('active');
            $('.top-wrap').addClass('top-wrap-no-banner');
            if (getMatchName == 'author_images') {
                $('.content-bank-author').addClass('active').siblings().removeClass('active');
            } else if(getMatchName == 'publish_demand'){
                $('.content-bank-demand').addClass('active').siblings().removeClass('active');
            } else if(getMatchName == 'content_bank' || getMatchName == 'original_work'){
                $('.content-bank-content').addClass('active').siblings().removeClass('active');
            }
            // else if(getMatchName == 'demand_hall'){
            // 	$('.content-bank-hall').addClass('active').siblings().removeClass('active');
            // }
        } else if(getMatchName == 'ip_trade' || getMatchName == 'ip_trade_distribution'){//IP交易
            $('.top-wrap').addClass('top-wrap-no-banner');
            $('.nav-ip-trade').addClass('active').siblings().removeClass('active');
            if(getMatchName == 'ip_trade'){
                $('.tab-ip-trade-li1').addClass().addClass('active').siblings().removeClass('active');
            } else if(getMatchName == 'ip_trade_distribution'){
                $('.tab-ip-trade-li3').addClass().addClass('active').siblings().removeClass('active');
            }
        } else if(getMatchName == 'ip_evaluation'){//IP评估
            $('.nav-ip-evaluation').addClass('active').siblings().removeClass('active');
            handleNav(winScrollTop, windowHei)
        } else if(getMatchName == 'demand_hall'){//需求大厅
            $('.nav-demand-hall').addClass('active').siblings().removeClass('active');
            $('.top-wrap').addClass('top-wrap-no-banner');
        } else if( getMatchName == 'demand_publish'){//需求大厅
            // $('.nav-demand-hall').addClass('active').siblings().removeClass('active');
            $('.top-wrap').addClass('top-wrap-no-banner');
            
            if(/ip_trade/.test(document.referrer) || /ip_trade_distribution/.test(document.referrer)){
                $('.nav-ip-trade').addClass('active').siblings().removeClass('active');
            } else if(/cart_author/.test(document.referrer) || /cart_distribution/.test(document.referrer)){
                $('.nav-demand-hall').addClass('active').siblings().removeClass('active');
            } else if(/original_work/.test(document.referrer) || /author_image/.test(document.referrer)){
                $('.nav-content-bank').addClass('active').siblings().removeClass('active');
            } else {
                $('.nav-demand-hall').addClass('active').siblings().removeClass('active');
            }
        } else if(getMatchName == 'ip_ranking_article' || getMatchName == 'ip_ranking_creation' || getMatchName == 'ip_data_description'){//IP排行榜
            $('.nav-ip-evaluation').addClass('active').siblings().removeClass('active');
            $('.top-wrap').addClass('top-wrap-no-banner');
        } else if(getMatchName == 'about_us'){//关于我们
            $('.nav-about-us').addClass('active').siblings().removeClass('active');
            handleNav(winScrollTop, windowHei);
        } else if(getMatchName == 'cart_distribution' || getMatchName == 'cart_article' || getMatchName == 'cart_author' || getMatchName == 'unifiedOrder' || getMatchName == 'unifiedorder' || getMatchName == 'paySuccess' || getMatchName == 'paysuccess'){//选购车
            // $('.nav-ip-evaluation').addClass('active').siblings().removeClass('active');
            navWrapDom.hide();
            $('.top-wrap').addClass('top-wrap-no-banner');
        }
        
    } else {
        $('.nav-index').addClass('active').siblings().removeClass('active');
        navWrapDom.show();
        handleNav(winScrollTop, windowHei);
    }
}
//当滚动距离大于一屏幕的时候，导航栏固定
function handleNav(winScrollTop, windowHei) {
    if(winScrollTop > windowHei){
        $('.top-wrap').addClass('top-wrap-no-banner');
    } else {
        $('.top-wrap').removeClass('top-wrap-no-banner');
    }
}
//鼠标悬停样式
$('li', navWrapDom).mouseenter(function(){
    var $this = $(this);
    $this.addClass('active').siblings().removeClass('active');
}).mouseleave(function(){
    getNavStatus();
})
$(document).ready(function(){
    //计算权威模块距离顶部的高度
    var secondWrapDom = $('#js-sec-wrap');//链条内容交易服务
    var secondWrapDomHei = secondWrapDom.height();//链条内容交易服务
    var thirdWrapDom = $('#js-third-wrap');//权威价值评估体系
    var thirdWrapDomTop = thirdWrapDom ? getDomTopNum(thirdWrapDom,'.sec-tit-wrap') : 0;//距离顶部的高度
    var fourConWrapDom = $('#js-four-con-wrap');//优质资源合作
    var fourConWrapDomTop = fourConWrapDom ? getDomTopNum(fourConWrapDom,'.sec-tit-wrap') : 0;//距离顶部的高度
    var fiveConWrapDom = $('#js-five-con-wrap');//发现价值模块
    var fiveConWrapDomTop = fiveConWrapDom ? getDomTopNum(fiveConWrapDom,'.sec-tit-wrap') : 0;//距离顶部的高度
    var sixconWrapDom = $('#js-six-con-wrap');//合作伙伴
    var sixconWrapDomTop = sixconWrapDom ? getDomTopNum(sixconWrapDom,'.sec-tit-wrap') : 0;//距离顶部的高度
    var hotlineWrapDom = $('#index-side');//热线电话
    var getAuName = commonFun.getUserAgentFun.mobile;//是否为移动设备
    var eSecWrapDom = $('#js-e-sec-wrap');//ip评估—原创资源评估
    var eSecWrapDomTop = eSecWrapDom ? getDomTopNum(eSecWrapDom,'h1') : 0;
    var eThirdWrapDom = $('#js-e-third-wrap');//ip评估—营销资源评估
    var eThirdWrapDomTop = eThirdWrapDom ? getDomTopNum(eThirdWrapDom,'h1') : 0;
    var eForthWrapDom = $('#js-e-forth-wrap');//ip评估—IP资产评估
    var eForthWrapDomTop = eForthWrapDom ? getDomTopNum(eForthWrapDom,'h1') : 0;
    var aboutSecWrapDom = $('#js-about-sec-wrap');//关于我们-关于圆融
    var aboutSecWrapDomTop = aboutSecWrapDom ? getDomTopNum(aboutSecWrapDom,'h3') : 0;
    var aboutThirdWrapDom = $('#js-about-third-wrap');//关于我们-我们的愿景
    var aboutThirdWrapDomTop = aboutThirdWrapDom ? getDomTopNum(aboutThirdWrapDom,'h3') : 0;
    var footerWrapDom = $('#js-footer-wrap');
    var footerWrapDomTop = footerWrapDom ? getDomTopNum(footerWrapDom,'.index-footer') : 0;

    var headerNavWrapDom = $('#js-header-nav-wrap');

    //当前模块显示 = 上一个模块底到顶部的距离 + 当前模块
    //滚动显示模块
    scrollAnimationFun();
    //屏幕滚动超过一屏，显示导航
    window.onscroll = function(){
        scrollAnimationFun();
    }
    //滚动显示动态显示模块
    function scrollAnimationFun(){
        var winScrollTop = document.documentElement.scrollTop || document.body.scrollTop;
        var winScrollAddHei = winScrollTop + windowHei;
        
        //超过一屏，显示导航
        if(winScrollTop > windowHei){
            topWrapDom.addClass('top-wrap-no-banner');
            topWrapDom.addClass('top-wrap-fixed');
            headerNavWrapDom.addClass('header-nav-fixed');
            
            $('.list-wc-tit-wrap,.list-wb-tit-wrap,.list-sv-tit-wrap').removeClass('table-head-fixed-0');
            $('.list-wc-tit-wrap,.list-wb-tit-wrap,.list-sv-tit-wrap').addClass('table-head-fixed-70');
        } else if(winScrollTop < 600){
            topWrapDom.removeClass('top-wrap-fixed');
            headerNavWrapDom.removeClass('header-nav-fixed');
            getNavStatus();
        }
        //当页面滚动到权威价值评估体系时，该模块加载
        if(winScrollTop > 600 && winScrollTop < windowHei){//固定ip交易表头
            $('.list-wc-tit-wrap,.list-wb-tit-wrap,.list-sv-tit-wrap').addClass('table-head-fixed-0');
        } else if(winScrollTop < 600){
            $('.list-wc-tit-wrap,.list-wb-tit-wrap,.list-sv-tit-wrap').removeClass('table-head-fixed-0');
            $('.list-wc-tit-wrap,.list-wb-tit-wrap,.list-sv-tit-wrap').removeClass('table-head-fixed-70');
        }
        if(winScrollAddHei >= thirdWrapDomTop){//首页权威模块，第三个模块
            thirdWrapDom.find('.third-slogan-wrap').addClass('active');
            thirdWrapDom.find('.third-slogan-wrap').addClass('opa1');
        }
        //侧边栏返回顶部，滚动距离大于窗口的2倍时显示
        if(winScrollTop >= windowHei){
            $(".back-top").show();//热线电话
        } else if(winScrollTop < windowHei){
            $(".back-top").hide();
        }
        if(winScrollAddHei >= fourConWrapDomTop){//优质资源合作，第四个模块
            fourConWrapDom.addClass('active');
            fourConWrapDom.addClass('opa1');
        }
        if(winScrollAddHei >= fiveConWrapDomTop){//发现价值模块，第五个模块
            fiveConWrapDom.addClass('active');
            fiveConWrapDom.addClass('opa1');
        }
        if(winScrollAddHei >= sixconWrapDomTop){//合作伙伴模块，第六个模块
            sixconWrapDom.addClass('active');
            sixconWrapDom.addClass('opa1');
        }
        // 关于我们的js
        if(winScrollAddHei >= aboutSecWrapDomTop){
            $('.about-us-p1').removeClass('about-hide').addClass('about-show first-line-move');
            $('.about-us-p2').removeClass('about-hide').addClass('about-show second-line-move');
        }
        if(winScrollAddHei >= aboutThirdWrapDomTop){
            //关于我们——内容滚动显示
            $('.about-future-con').removeClass('about-hide').addClass('about-show');
            $('.about-future-con h3').addClass('first-line-move');
            $('.about-future-con span').addClass('second-line-move');
            $('.about-future-con p').addClass('third-line-move');
        }
        //侧边栏滚动到底部
        // var documentHeight = $(document).height();
        // // console.log(documentHeight, windowHei,winScrollAddHei,footerWrapDomTop)
        // // console.log(footerWrapDom.offset().top,windowHei - footerWrapDom.height())
        // // console.log(footerWrapDom.offset().top - (windowHei - footerWrapDom.height()))
        // console.log(winScrollTop, (footerWrapDom.offset().top - windowHei ));
        // console.log(footerWrapDom.offset().top);
        // if((footerWrapDom.offset().top - (windowHei - footerWrapDom.height())) - 500 <= winScrollTop){
        //     $('#js-cart-bottom-handle-wrap').addClass('cart-fix-bottom-0-wrap');
        // } else {
        //     $('#js-cart-bottom-handle-wrap').removeClass('cart-fix-bottom-0-wrap');
        // }
        
        // var documentHeight = $(document).height();
        // console.log((windowHei + winScrollTop), documentHeight,winScrollTop,windowHei)
        // if((windowHei + winScrollTop + 40) >= documentHeight){//滚动到底部
        //     // $('#js-cart-bottom-handle-wra').addClass('cart-fix-bottom-198-wrap');
        //     $('#js-cart-bottom-handle-wrap').removeClass('cart-fix-bottom-0-wrap');
        //     // console.log(footerWrapDomTop,'==footerWrapDomTop')
        // } else {
        //     if(documentHeight > (windowHei + 198)){
        //         $('#js-cart-bottom-handle-wrap').addClass('cart-fix-bottom-0-wrap');
        //     }
        // }
        //创作者详情，作品详情，常驻底部
        $('#js-cart-fix-bottom-wrap').addClass('cart-fix-bottom-0-wrap');
        // var documentHeight = $(document).height();
        // if(windowHei <= documentHeight) {
        //     $('#js-cart-fix-bottom-wrap').removeClass('cart-fix-bottom-198-wrap');
        //     $('#js-cart-fix-bottom-wrap').addClass('cart-fix-bottom-0-wrap');
        // }
        // if(winScrollTop < 10 || winScrollTop == 0){//顶部且div高度大于一屏
        //     $('#js-cart-fix-bottom-wrap').removeClass('cart-fix-bottom-198-wrap');
        //     $('#js-cart-fix-bottom-wrap').removeClass('cart-fix-bottom-0-wrap');
        // }
        
        // ip评估
        if(winScrollAddHei >= eSecWrapDomTop){
            $('.origin-bg').addClass('active');
            $('.origin-bg').addClass('opa');
        }
        if(winScrollAddHei >= eThirdWrapDomTop) {
            $('.marketing-bg').addClass('active');
            $('.marketing-bg').addClass('opa');
        }
        if(winScrollAddHei >= eForthWrapDomTop) {
            $('.evaluate-bg').addClass('active');
            $('.evaluate-bg').addClass('opa');
        }
    }
    //QQ客服
    $('#side').on('click', function(e){
        $('#qq2').trigger('click');
        // var ary = e.target.className.split(' ')
        // ary.forEach(function(item){
        //     if(item == 'pp1' || item == 'side-3'){

        //     }
        // });
    })
    //返回顶部
    $(".back-top").click(function(){
        $("html,body").animate({scrollTop:0}, 500);
    })
})
//获取元素距离顶部高度
function getDomTopNum (dom, domChild){
    var domChildHeiAddTop = 0;
    var domHeight = dom.height();
    if(domHeight){
        if(domChild){
            var domChild = dom.find(domChild);
            var domChildHei = domChild.height();
            var domChildTop = domChild.offset().top;
            domChildHeiAddTop = domChildHei + domChildTop;
            return domChildHeiAddTop;
        }
    } else {
        return 0;
    }
}
//点击跳转到登陆页面
function goLoginPage(type) {
    var currentUrl = window.location.href;
    if (isLoginFlag) {
        if(type == 0){//买家
            var toRUl = centerUrl + 'buyer/index';
        } else if(type == 1){//卖家
            var toRUl = centerUrl + 'seller/index';
        }
    } else {
        var toRUl = centerUrl +'login/?callback=' + currentUrl.toString();
    }
    $('.pop-wrap').hide();
    window.open(toRUl, '_blank');
    // window.location.href = toRUl;
}
//判断用户是否登陆
function getUserLoginInfo(callback, isShowPop) {//isShowPop：未登录，是否显示登陆弹框
    var isLogin = '';
    commonFun.commonAjax(domainCenter +'/userManagement/loginInfo' , 'post', {}, function(res){
        var sucData = res.data;
        if(res.status == 1){
            isLogin = 'Y';
            var userMobile = sucData.mobile;
            var handleMoblie = userMobile.slice(0,3) + '****' + userMobile.slice(7, 11);
            $('.phone').text('您好, '+ handleMoblie).show();
            $('.login-out').show();
            sideCartNoLoginWrapDom.hide();
            loginPopNoteWrapDom.hide();
            var getLoginFlag = callback(isLogin);
            sessionStorage.setItem('mobile', userMobile);
            return getLoginFlag;
        } else {
            $('.phone').text('').hide();
            $('.login-out').hide();
            isLogin = 'N';
            sideCartNoLoginWrapDom.show();
            if(isShowPop){
                loginPopNoteWrapDom.show();
            }

            var getLoginFlag = callback(isLogin);
            return getLoginFlag;
        }
    },function(error){
        console.log(error);
        isLogin = 'N';
        sideCartNoLoginWrapDom.show();
        if(isShowPop){
            loginPopNoteWrapDom.show();
        }
        var getLoginFlag = callback(isLogin);
        return getLoginFlag;
    });
}
//计算内容盒子的高度
calContentHeiFun();
function calContentHeiFun(){
    var winHei =  $(window).height();
    var footerHei = $('.footer-wrap').height();
    var navHei = $('#js-top-wrap').height();
    var conHeight = winHei - footerHei - navHei - 90;
    $('.wrap').css('min-height', conHeight + 'px');
}
$(window).resize(function(){
    calContentHeiFun();
})
//调用企点客服
function qdService(domClass) {
    //QQ客服
    $(domClass).on('click', function(e){
        var ary = e.target.className.split(' ')
        ary.forEach(function(item){
            if(item == 'qq'){
                $('#qq2').trigger('click');
            }
        });
    })
}
//点击隐藏侧边栏选购车
$('.wrap, .index-wrap,#js-footer-wrap,.top-wrap,.has-banner-wrap,.pop-wrap').off('click').on('click', function(e) {
    $('#js-side-cart-wrap').addClass('hide-side-cart-wrap');
    $('.cart-side-bottom-wrap').addClass('cart-hide');
    $('#js-index-cart-side').removeClass('side-cart-hover');
});