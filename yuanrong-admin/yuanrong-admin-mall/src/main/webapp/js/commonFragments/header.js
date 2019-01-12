

$('#js-my-register').attr('href', centerUrl + 'register');
siteNoLiginDom.attr('href', centerUrl + 'login');
//点击搜索类型
$('li', searchTypeListWrapDom).on('click', function () {
    console.log('test')
    var $this = $(this),
        txt = $this.text();
    searchDataType = $this.data('id');
    $this.addClass('active').siblings().removeClass('active');
    searchTypeTxt.text(txt);
    searchTypeListWrapDom.hide();
})
$('.header-search-tit-wrap').mouseenter(function (e) {
    e.stopPropagation();
    // searchTypeListWrapDom.show();
    searchTypeListWrapDom.slideDown("fast");
})
$('#js-search-type-list, .header-search-type-wrap').mouseleave(function () {
    searchTypeListWrapDom.hide();
})
$('.site-has-downmenu-wrap').each(function(index){
    $(this).on('mouseenter', function (e) {
        e.stopPropagation();
        $('.site-has-downmenu-wrap').eq(index).css({ 'background': '#fff' });
        $(this).find('.site-quick-downmenu-wrap').slideDown('normal');

    });
    $(this).on('mouseleave', function(){
         $(this).css({ 'background': '#F1F3F6' });
        $(this).find('.site-quick-downmenu-wrap').hide();
    })
});
// $('.site-has-downmenu-wrap').mouseenter(function (e) {
//     console.log('lallaleee')
//     e.stopPropagation();
//     $('.site-has-downmenu-wrap').css({ 'background': '#fff' });
//     $(this).find('.site-quick-downmenu-wrap').show();
// }).mouseleave(function () {
//     $(this).find('.site-quick-downmenu-wrap').hide();
// })

//点击搜索
headerSearchBtnDom.on('click', function () {
    var searchCon = $.trim(searchText.val()), urlParamsSearch = '';
    var getUrlParams = commonFun.getUrlParam();
    searchCon ? urlParamsSearch = '?keyword='+searchCon : urlParamsSearch = '';
    if(searchDataType == 0){//买作品
        var url = encodeURI(domain + '/contentBank/original_work.html'+urlParamsSearch);
    } else if(searchDataType == 1){//找作者
        var url = encodeURI(domain + '/contentBank/author_images.html'+urlParamsSearch);
    } else if(searchDataType == 2){//营销账号
        if(getUrlParams && getUrlParams.adType){
            if(getUrlParams.adType == 1){//微博
                var params = '';
                urlParamsSearch ? params = urlParamsSearch + '&adType=1' : params = '?adType=1';
                var url = encodeURI(domain + '/ipTrade/ip_trade_distribution.html'+params);
            } else if(getUrlParams.adType == 2){//短视频
                var params = '';
                urlParamsSearch ? params = urlParamsSearch + '&adType=2' : params = '?adType=2';
                var url = encodeURI(domain + '/ipTrade/ip_trade_distribution.html'+params);
            } else {//默认微信
                var params = '';
                urlParamsSearch ? params = urlParamsSearch + '&adType=0' : params = '?adType=0';
                var url = encodeURI(domain + '/ipTrade/ip_trade_distribution.html'+params);
            }
            
        } else {
            var params = '';
            urlParamsSearch ? params = urlParamsSearch + '&adType=0' : params = '?adType=0';
            var url = encodeURI(domain + '/ipTrade/ip_trade_distribution.html'+params);
        }
    } else if(searchDataType == 3){//签约账号
        var url = encodeURI(domain + '/segmentAccount/segment_account.html'+urlParamsSearch);
    }
    window.location.href = url;
})
//点击回车搜索
searchText.on('focus', function () {
    $(document).keyup(function(event){
        if(event.keyCode == 13){
            headerSearchBtnDom.click();
        }
    })
})

//登录显示登陆信息下拉
siteLoginInfoDom.mouseenter(function (e) {
    e.stopPropagation();
    if(siteNoLiginDom.css('display') == 'none'){
        $('.active-name').css({ 'background': '#fff' });
        // siteLoginInfoDownmenu.show();
        siteLoginInfoDownmenu.slideDown("fast");
        $(this).siblings('.down-img').hide();
        $(this).siblings('.up-img').show();
    } else {
        siteLoginInfoDownmenu.hide();
        // $(this).siblings('.up-img').hide();
        // $(this).siblings('.down-img').show();
    }
})
//隐藏下拉
siteLoginInfoDownmenu.mouseleave(function () {
    // $('.active-name').css({ 'background': '#F1F3F6' });
    siteLoginInfoDownmenu.hide();
    $('.site-login-info-wrap').find('.up-img').hide();
    $('.site-login-info-wrap').find('.down-img').show();
})
$('.site-login-info-wrap').mouseleave(function () {
    $('.active-name').css({ 'background': '#F1F3F6' });
    siteLoginInfoDownmenu.hide();
    $('.site-login-info-wrap').find('.up-img').hide();
    $('.site-login-info-wrap').find('.down-img').show();
})
$('.header-nav-wrap, .wrap').on('click', function () {
    siteLoginInfoDownmenu.hide();
    // $('.down-img').show();
    // $('.up-img').hide();
})
//点击登出;
$('#js-login-out').on('click', function(){
    // window.location.href = newDomain + '/registeredUserInfo/logout';//8084
    // window.location.href = 'http://mach.tech.xyauto.com:8084/';
    commonFun.commonAjax(domainCenter + '/userManagement/logout' , 'post', '', function(res){
        if(res.status == 1){
            isLoginFlag = false
            sessionStorage.removeItem('user');
            window.location.href = toIndex;
        } else {
            layer.msg('登出失败！', {time: 1500});
        }
    },function(error){
        console.log(error);
    });

});

//
function slideActive(){
    $(this).on('mouseover', function(){
        $(this).css({ 'background': '#F5F7FB' }).siblings().css({ 'background': '#fff' });
    })
    $(this).on('mouseleave', function(){
        $(this).css({ 'background': '#fff' });
    })
}
$('.site-quick-downmenu-wrap li').each(slideActive);

$('#js-site-info-bd-wrap li').each(slideActive);
