var navWrapDom = $('#js-nav-wrap'),//nav导航
    topWrapDom = $('#js-top-wrap'),//导航wrap
    windowHei = $(window).height(),//获取窗口的高度
    windowHeiHalf = (windowHei / 2),//获取窗口的高度的一半
    loginPopNoteWrapDom = $('#js-pop-login-wrap'),
    paginationPopNoteWrapDom = $('#js-pop-pagination-wrap'),
    sideCartNoLoginWrapDom = $('#js-cart-side-nologin-wrap'),
    headerNavWrapDom = $('#js-header-nav-wrap'),
    searchTypeListWrapDom = $('#js-search-type-list'),//搜索类型
    searchTypeTxt = $('#js-search-type-tit'),//选中搜索类型
    searchDataType = 0,
    headerSearchBtnDom = $('#js-header-search-btn'),//搜索
    searchText = $('#js-search-text'),
    prev_text = '<i class="iconfont">&#xe608;</i>',
    next_text = '<i class="iconfont">&#xe640;</i>',    
    siteLoginInfoDom = $('#js-site-login-info'),
    siteNoLiginDom = $('#js-site-no-login'),
    siteLoginInfoDownmenu = $('#js-site-info-bd-wrap'),
    salesServiceUrl = 'http://q.url.cn/cdPnbU?_type=wpa&qidian=true';
    pageNameArr = ['content_bank', 'author_images', 'publish_demand', 'graphics_writer', 'video_auther', 'ip_trade', 'ip_trade_distribution', 'ip_evaluation', 'about_us'];

var tenThousandNum = 10000;//列表展示数字基数

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
        // navWrapDom.show();
		if(getMatchName == 'original_work' || /article_detail/.test(getPageName)){//找作品：列表，详情
            $('.nav-works').find('a').addClass('active');
            $('.nav-works').siblings().find('a').removeClass('active');
            searchTypeListFormat(0);
            headerNavWrapDom.addClass('top-wrap-no-banner');
            searchDataType = 0;
        } else if(getMatchName == 'author_images' || /author_detail/.test(getPageName)){//找作者
            $('.nav-authors').find('a').addClass('active');
            $('.nav-authors').siblings().find('a').removeClass('active');
            searchTypeListFormat(1);
            headerNavWrapDom.addClass('top-wrap-no-banner');
            searchDataType = 1;
        } else if(getMatchName == 'ip_trade_distribution'){//营销账号
            $('.nav-accounts').find('a').addClass('active');
            $('.nav-accounts').siblings().find('a').removeClass('active');
            searchTypeListFormat(2);
            headerNavWrapDom.addClass('top-wrap-no-banner');
            searchDataType = 2;
        } else if(getMatchName == 'segment_account'){//包段账号
            $('.nav-agency').find('a').addClass('active');
            $('.nav-agency').siblings().find('a').removeClass('active');
            searchTypeListFormat(3);
            headerNavWrapDom.addClass('top-wrap-no-banner');
            searchDataType = 3;
        } else if(getMatchName == 'demand_hall' || /demand_work|demand_author/.test(location.href)){//需求大厅
            $('.nav-demand-hall').find('a').addClass('active');
            $('.nav-demand-hall').siblings().find('a').removeClass('active');
            headerNavWrapDom.addClass('top-wrap-no-banner');
        } else if( getMatchName == 'demand_publish'){//发布需求
            // $('.nav-demand-hall').addClass('active').siblings().removeClass('active');
            headerNavWrapDom.addClass('top-wrap-no-banner');
            
            if(/segment_account/.test(document.referrer)){
                $('.nav-agency').find('a').addClass('active');
                $('.nav-agency').siblings().find('a').removeClass('active');
            } else if(/ip_trade_distribution/.test(document.referrer)){
                $('.nav-accounts').find('a').addClass('active');
                $('.nav-accounts').siblings().find('a').removeClass('active');
            } else if(/cart_author/.test(document.referrer) || /cart_distribution/.test(document.referrer)){
                $('.nav-demand-hall').find('a').addClass('active');
                $('.nav-demand-hall').siblings().find('a').removeClass('active');
            } else if(/original_work/.test(document.referrer)){
                $('.nav-works').find('a').addClass('active');
                $('.nav-works').siblings().find('a').removeClass('active');
            } else if(/author_image/.test(document.referrer)){
                $('.nav-authors').find('a').addClass('active');
                $('.nav-authors').siblings().find('a').removeClass('active');
            } else {
                $('.nav-demand-hall').find('a').addClass('active');
                $('.nav-demand-hall').siblings().find('a').removeClass('active');
            }
        } else if(getMatchName == 'ip_ranking_article' || getMatchName == 'ip_ranking_creation' || getMatchName == 'ip_data_description'){//IP排行榜
            $('.nav-rank').find('a').addClass('active');
            $('.nav-rank').siblings().find('a').removeClass('active');
            headerNavWrapDom.addClass('top-wrap-no-banner');
        } else if(getMatchName == 'about_us' || getMatchName == 'play_around'){//关于我们，玩转圆融
            $('.header-nav-list-wrap li a').removeClass('active');
            handleNav(winScrollTop, windowHei);
        } else if(getMatchName == 'cart_distribution' || getMatchName == 'cart_article' || getMatchName == 'cart_author' || getMatchName == 'unifiedOrder' || getMatchName == 'unifiedorder' || getMatchName == 'paySuccess' || getMatchName == 'paysuccess'){//选购车
            $('.header-nav-list-wrap li a').removeClass('active');
            headerNavWrapDom.addClass('top-wrap-no-banner');
        }
        
    } else {
        $('.nav-index').find('a').addClass('active');
        $('.nav-index').siblings().find('a').removeClass('active');
        // navWrapDom.show();
        handleNav(winScrollTop, windowHei);
        headerNavWrapDom.removeClass('top-wrap-no-banner');
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
//导航鼠标悬停样式
$('li', navWrapDom).mouseenter(function(){
    var $this = $(this);
    $this.addClass('active').siblings().removeClass('active');
}).mouseleave(function(){
    getNavStatus();
})

//格式化列表数字
function formatIndexNumber(num){
    if(!num){
        var formatNum = 0;
    } 
    if(num < tenThousandNum){//小于1万
       var formatNum =  commonFun.formatNumber((num), 0, '');
    } else if(num > tenThousandNum){//da于1万
        var formatNum = commonFun.formatNumber((num / tenThousandNum), 1, '') + '万';
    }
    return formatNum;
}
//获取url搜索参数
function searchTypeListFormat(searchDataType) {
    var i = searchDataType,
        getUrlParams = commonFun.getUrlParam();
    $('li', searchTypeListWrapDom).eq(i).addClass('active').siblings().removeClass('active');
    searchTypeTxt.text($('li', searchTypeListWrapDom).eq(i).text());
    if(getUrlParams && getUrlParams.keyword){
        var getUrlKeyword = getUrlParams.keyword;
        // console.log(getUrlKeyword)
        searchText.val(getUrlKeyword);
    }
}
$(document).ready(function(){
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
    // var cartFixBottomNormalTop = $('#js-cart-bottom-handle-wrap').offset().top;
    var winWidth = $(window).width(),
        winHei = $(window).height(),
        docHeight = $(document).height(),
        winDistance = (parseFloat(winWidth) - 1170) / 2;

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
        if(winScrollTop > 120){
            topWrapDom.addClass('top-wrap-no-banner');
            topWrapDom.addClass('top-wrap-fixed');
            headerNavWrapDom.addClass('header-nav-fixed');
            $('.list-wc-tit-wrap,.list-wb-tit-wrap,.list-sv-tit-wrap').removeClass('table-head-fixed-0');
            $('.list-wc-tit-wrap,.list-wb-tit-wrap,.list-sv-tit-wrap').addClass('table-head-fixed-70');
            $('#js-rank-category-wrap').addClass('content-left-fixed');
            // $('.content-right-fixed').css('right', winDistance + 'px');
            // $('.content-left-fixed').css('left', winDistance + 'px');
        } else if(winScrollTop < 120){
            topWrapDom.removeClass('top-wrap-fixed');
            headerNavWrapDom.removeClass('header-nav-fixed');
            $('#js-rank-category-wrap').removeClass('content-left-fixed');
            // $('#js-demand-help,.container-right').removeClass('content-right-fixed');
            getNavStatus();
        }
        
        // console.log(docHeight, winHei,docHeight - winHei)
        
        //当页面滚动到权威价值评估体系时，该模块加载
        if(winScrollTop > 600 && winScrollTop < windowHei){//固定ip交易表头
            $('.list-wc-tit-wrap,.list-wb-tit-wrap,.list-sv-tit-wrap').addClass('table-head-fixed-0');
        } else if(winScrollTop < 600){
            $('.list-wc-tit-wrap,.list-wb-tit-wrap,.list-sv-tit-wrap').removeClass('table-head-fixed-0');
            $('.list-wc-tit-wrap,.list-wb-tit-wrap,.list-sv-tit-wrap').removeClass('table-head-fixed-70');
        }
        
        //侧边栏返回顶部，滚动距离大于窗口的2倍时显示
        if(winScrollTop >= windowHei){
            $(".back-top").show();//热线电话
        } else if(winScrollTop < windowHei){
            $(".back-top").hide();
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
        
        
        // console.log(winScrollTop, cartFixBottomNormalTop)
        // if(winScrollTop >= cartFixBottomNormalTop && winScrollTop > windowHei){
        //     $('.cart-fix-bottom-handle').hide();
        // } else {
        //     $('.cart-fix-bottom-handle').show();
        // }


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
function goLoginPage(type, pageUrl) {//type：卖家，买家；pageUrl指定页面路径
    var getLoginFun = function (isLogin) {
        $('.pop-wrap').hide();
        if(isLogin == 'Y'){
            if(type == 0){//买家
                var toRUl = centerUrl + 'buyer/index';
            } else if(type == 1){//卖家
                var toRUl = centerUrl + 'seller/index';
            }
        } else {
            if(pageUrl){
                var currentUrl = pageUrl;
            } else {
                if(type == 0){//买家
                    var currentUrl = centerUrl + 'buyer/index';
                } else if(type == 1){//卖家
                    var currentUrl = centerUrl + 'seller/index';
                } else {
                    var currentUrl = window.location.href;
                }
            }
            var toRUl = centerUrl +'login/?callback=' + currentUrl;
        }
        // window.open(toRUl, '_blank');
        window.location.href = toRUl;
    }
    getUserLoginInfo(getLoginFun);
}
//判断用户是否登陆
/**
* function:        		ajax请求
* @param callback    	回调函数
* @param isShowPop    	未登录，是否显示登陆弹框
* @param pagination    	未登录，列表只能查看3页数据
* @returns{string}  	
*/	
function getUserLoginInfo(callback, isShowPop, pagination) {
    var isLogin = '', getCookieUK = commonFun.getCookie('uk') || '',//用户是否登陆
        getCookieMobile = commonFun.getCookie('u') || '';//获取手机号码
    console.log(getCookieUK)
    if(getCookieUK){
        isLogin = 'Y';
        var  handleMobile = getCookieMobile.slice(0,3) + '****' + getCookieMobile.slice(7, 11);
        siteLoginInfoDom.text(handleMobile).show();
        siteNoLiginDom.hide();
        $('#js-site-login-name').text(handleMobile);
        $('#js-site-register').hide();
        $('.site-login-span0').hide();
        sideCartNoLoginWrapDom.hide();
        loginPopNoteWrapDom.hide();
        $('.site-login-span1').show();
        $('.pagination-note-wrap').hide();
        var getLoginFlag = callback(isLogin);
        sessionStorage.setItem('mobile', getCookieMobile);

        $('#js-center-order-list').attr('href', centerUrl + 'buyer/order/orderList');
        $('#js-center-buy-list').attr('href', centerUrl + 'buyer/order/orderbuyList');
        $('#js-change-pwd').attr('href', centerUrl + 'sellerInfo/modifyPwd');
        $('#js-buyer').attr('href', centerUrl + 'buyer/index');
        $('#js-my-request').attr('href', centerUrl + 'buyerDemand/buyerDemandArgument');
        $('#js-seller').attr('href', centerUrl + 'seller/index');
        $('#js-my-resource').attr('href', centerUrl + 'seller/sourceManage/author/authorList');
        $('#js-my-order').attr('href', centerUrl + 'seller/order/orderList');
        $('#js-my-register').attr('href', centerUrl + 'register');
        $('#js-my-account').attr('href', centerUrl + 'seller/sourceManage/account/accountList');
        $('#js-my-work').attr('href', centerUrl + 'seller/sourceManage/article/articleList');
        $('#js-my-author').attr('href', centerUrl + 'seller/sourceManage/author/authorList');
    } else {
        var winCurUrl = window.location.href;
        siteLoginInfoDom.text('').hide();
        siteNoLiginDom.show();
        $('.site-login-span1').hide();
        $('.site-login-span0').show();
        $('#js-site-register').show();
        $('.pagination-note-wrap').show();
        $('#js-pagination-note').attr('href', centerUrl + 'login?callback='+winCurUrl);

        $('#js-center-order-list').attr('href', centerUrl + 'login/?callback=/#/buyer/order/orderList');
        $('#js-center-buy-list').attr('href', centerUrl + 'login/?callback=/#/buyer/order/orderbuyList');
        $('#js-change-pwd').attr('href', centerUrl + 'login/?callback=/#/sellerInfo/modifyPwd');
        $('#js-buyer').attr('href', centerUrl + 'login/?callback=/#/buyer/index');
        $('#js-my-request').attr('href', centerUrl + 'login/?callback=/#/buyerDemand/buyerDemandArgument');
        $('#js-seller').attr('href', centerUrl + 'login/?callback=/#/seller/index');
        $('#js-my-resource').attr('href', centerUrl + 'login/?callback=/#/seller/sourceManage/author/authorList');
        $('#js-my-order').attr('href', centerUrl + 'login/?callback=/#/seller/order/orderList');
        $('#js-my-account').attr('href', centerUrl + 'login/?callback=/#/seller/sourceManage/account/accountList');
        $('#js-my-work').attr('href', centerUrl + 'login/?callback=/#/seller/sourceManage/article/articleList');
        $('#js-my-author').attr('href', centerUrl + 'login/?callback=/#/seller/sourceManage/author/authorList');

        isLogin = 'N';
        sideCartNoLoginWrapDom.show();
        if(isShowPop){
            if(pagination && pagination == 'pagination'){
                paginationPopNoteWrapDom.show();
            } else {
                loginPopNoteWrapDom.show();
            }
        }
        var getLoginFlag = callback(isLogin);
    }
    
    $('#js-site-info-lt-wrap').show();
    $('#js-help-center').attr('href', centerUrl + 'helpcenter/platform/platform');
    return getLoginFlag;
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
$('.wrap, .index-wrap,#js-footer-wrap,.top-wrap,.has-banner-wrap,.pop-wrap,.toubu,.site-nav-wrap').off('click').on('click', function(e) {
    $('#js-side-cart-wrap').addClass('hide-side-cart-wrap');
    $('.cart-side-bottom-wrap').addClass('cart-hide');
    $('#js-index-cart-side').removeClass('side-cart-hover');
});

// 侧边栏在线客服登录
$(".index-side-login").off('click').on('click', function(){
    goLoginPage(-1);
})

//点击弹框联系客服
$('.login-input-wrap .service').on('click', function(){
    $('#div_imqq1').trigger('click');
    setTimeout(function(){
        loginDoneWrap.hide();
    }, 1500);
})

//点击复制
function copyFun(dom, copyContent) {
    var clipboard = new Clipboard(dom, {
        text: function () {
            return copyContent;
        }
    });
    clipboard.on('success', function (e) {
        layer.msg('复制成功!', {'time': 2000});
        e.clearSelection();
    });
    clipboard.on('error', function (e) {
        console.error('Action:', e.action);
        console.error('Trigger:', e.trigger);
    }); 
}
