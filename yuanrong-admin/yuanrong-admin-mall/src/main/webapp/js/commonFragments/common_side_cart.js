
//点击侧边栏购物侧
var sideCartIconDom = $('#js-index-cart-side'),//侧边栏购物车按钮
    sideCartWrapDom = $('#js-side-cart-wrap'),//侧边栏购物车box
    sideCartWrapWidth = sideCartWrapDom.width(),
    sideHeiNum = 300,
    sideCartClearAllBtnDom = $('#js-cart-side-clear-all'),
    sideCartFullscreenBtnDom = $('#js-cart-side-fullscreen'),
    sideCartTabWrapDom = $('#js-cart-side-tab-wrap'),//选购车tab切换
    sideAuthorWrapDom = $('#js-cart-side-author-wrap'),
    sideArticleWrapDom = $('#js-cart-side-article-wrap'),
    sideDistriWrapDom = $('#js-cart-side-distribution-wrap'),
    sideCartBlueBtnDom = $('#js-cart-side-btn-blue');
//点击显示购物车
sideCartIconDom.on('click', function(){
    var $this = $(this);
    var sideBox = $this.parent().siblings();
    var sideType = sideCartTabWrapDom.find('.active').data('id');
    var getPageName = commonFun.getUrlPageName();
    var getMatchName = commonFun.pageNameReg(getPageName);
    if(sideBox.hasClass('hide-side-cart-wrap')){
        sideBox.removeClass('hide-side-cart-wrap');
        $('.cart-side-bottom-wrap').removeClass('cart-hide');
        // $('li',sideCartTabWrapDom).eq(0).click();
        $this.addClass('side-cart-hover');
        if(getMatchName || /author_detail/.test(getPageName) || /article_detail/.test(getPageName) ){
            if(getMatchName == 'author_images' || /author_detail/.test(getPageName)){//创作者
                $('li',sideCartTabWrapDom).eq(0).click();
            } else if(getMatchName == 'original_work' || /article_detail/.test(getPageName) || getMatchName == 'enterDetailPage'){//原创作品
                $('li',sideCartTabWrapDom).eq(1).click();
            } else if(getMatchName == 'ip_trade' || getMatchName == 'ip_trade_distribution'){//营销账号
                $('li',sideCartTabWrapDom).eq(2).click();
            } else {
                if(sideType == 0){
                    $('li',sideCartTabWrapDom).eq(0).click();
                } else if(sideType == 1){
                    $('li',sideCartTabWrapDom).eq(1).click();
                } else if(sideType == 2){
                    $('li',sideCartTabWrapDom).eq(2).click();
                }
            }
        } else {
            if(sideType == 0){
                $('li',sideCartTabWrapDom).eq(0).click();
            } else if(sideType == 1){
                $('li',sideCartTabWrapDom).eq(1).click();
            } else if(sideType == 2){
                $('li',sideCartTabWrapDom).eq(2).click();
            }
        }
        
    } else {
        $this.removeClass('side-cart-hover');
        sideBox.addClass('hide-side-cart-wrap');
        $('.cart-side-bottom-wrap').addClass('cart-hide');
    }
})
//选购车tab切换
$('li',sideCartTabWrapDom).off('click').on('click', function(){
    var $this = $(this),
    dataID = $this.data('id');
    $this.addClass('active').siblings().removeClass('active');
    if(dataID == 0){
        $('.side-author-display').show();
        $('.side-article-display').hide();
        $('.side-dis-display').hide();
        sideCartBlueBtnDom.text('立即预约');
        getSideListData(tAuthor);
    } else if(dataID == 1){
        $('.side-author-display').hide();
        $('.side-article-display').show();
        $('.side-dis-display').hide();
        sideCartBlueBtnDom.text('立即购买');
        getSideListData(tArticle);
    } else if(dataID == 2){
        $('.side-author-display').hide();
        $('.side-article-display').hide();
        $('.side-dis-display').show();
        sideCartBlueBtnDom.text('立即推广');
        getSideListData(tDistri);
    } 
})
//侧边栏-预约，购买，推广
// sideCartBlueBtnDom.off('click').on('click', function(){
//     var $this = $(this);
//     var dataID = $this.parents('#js-side-cart-wrap').siblings('#js-cart-side-tab-wrap').find('li.active').data('id');
//     if(dataID == 0){
        
//     } else if(dataID == 1){
        
//     } else if(dataID == 2){
        
//     } 
// })
//获取创作者
function getSideListData(type) {
    if(type == tAuthor){
        var postUrl = newDomain + '/cart/shoppingCart_authorList';
    } else if(type == tArticle){
        var postUrl = newDomain + '/cart/shoppingCart_productList';
    } else if(type == tDistri){
        var postUrl = newDomain + '/cart/shoppingCart_accountList';
    }
    commonFun.commonAjax(postUrl, 'post', {}, function(res){
        if(res.status == 1){
            var sucData = res.data;
            $('#js-cart-side-nologin-wrap').hide();
            if(type == tArticle){
                if(sucData.content.length){
                    var artSideHTML = sideCartListModule(sucData.content, type);
                    sideArticleWrapDom.html('').append(artSideHTML);
                    var rTotle = $('li',sideArticleWrapDom).length,
                        pubNum = calNumByAttrFun('publish', $('.side-cart-ul li')) ? calNumByAttrFun('publish', $('.side-cart-ul li')).length : 0,
                        unPubNum = rTotle ? rTotle - pubNum : 0,
                        aUnTotleSum = commonFun.formatNumber((sucData.unPublishMoney), 2, '￥'),
                        aPuTotleSum = commonFun.formatNumber((sucData.publishMoney), 2, '￥'),
                        aTotleSumFormat = parseFloat(sucData.publishMoney) + parseFloat(sucData.unPublishMoney);
                        aTotleSumFormat = commonFun.formatNumber((aTotleSumFormat), 2, '￥');
                    $('#js-publish-totle').text(pubNum);
                    $('#js-unpublish-totle').text(unPubNum);
                    $('#js-side-art-pub-sum').text(aPuTotleSum);
                    $('#js-side-art-unpub-sum').text(aUnTotleSum);
                    $('#js-side-article-sum').text(aTotleSumFormat);
                    sideCommonBtnRemoveDdClass();
                } else {
                    sideCartNoDataSet(type)
                }
            } else {
                if(sucData.length){
                    var html = sideCartListModule(sucData, type);
                    if(type == tAuthor){
                        sideAuthorWrapDom.html('').append(html);
                        var aTotle = $('li',sideAuthorWrapDom).length;
                    } else if(type == tDistri){
                        sideDistriWrapDom.html('').append(html);
                        var dTotle = $('li',sideDistriWrapDom).length;
                        getCartDataNums(tDistri);
                    }
                    sideCommonBtnRemoveDdClass();
                    
                } else {
                    
                    sideCartNoDataSet(type)
                    
                }
            }
            getCartDataNums('cart');
            handleSideCartDel();
        } else {
            sideCartNoDataSet(type)
        }
    })
}
//侧边栏无数据
function sideCartNoDataSet(type) {
    if(type == tAuthor){
        var authorHTML = cartNoDataFun(tAuthor);
        sideAuthorWrapDom.html('').append(authorHTML);
        $('.side-author-totle').text(0);
        sideCommonBtnAddDdClass();
    } else if(type == tArticle){
        var artSideHTML = cartNoDataFun(tArticle);
        sideArticleWrapDom.html('').append(artSideHTML);
        $('.side-article-totle').text(0);
        $('#js-publish-totle').text(0);
        $('#js-unpublish-totle').text(0);
        $('#js-side-article-sum').text(0);
        $('#js-side-art-pub-sum').text(0);
        $('#js-side-art-unpub-sum').text(0);
        sideCommonBtnAddDdClass();
    } else if(type == tDistri){
        var disHTML = cartNoDataFun(tDistri);
        sideDistriWrapDom.html('').append(disHTML);
        $('.side-dis-totle').text(0);
        $('#js-cart-wx-num').text(0);
        $('#js-cart-wb-num').text(0);
        $('#js-cart-sv-num').text(0);
        sideCommonBtnAddDdClass();
    }
}
function sideCommonBtnAddDdClass() {
    sideCartBlueBtnDom.addClass(btnBorderDisabledClass);
    sideCartClearAllBtnDom.addClass(btnBorderDisabledClass);
    sideCartFullscreenBtnDom.addClass(btnBorderDisabledClass);
}
function sideCommonBtnRemoveDdClass() {
    sideCartBlueBtnDom.removeClass(btnBorderDisabledClass);
    sideCartClearAllBtnDom.removeClass(btnBorderDisabledClass);
    sideCartFullscreenBtnDom.removeClass(btnBorderDisabledClass);
}
//创作者模板
function sideCartListModule(sucData, type) {
    var html = '';
    for(var i = 0, len = sucData.length; i < len; i ++){
        var data = sucData[i]
        
        if(data.authorImg){//头像
            var avatarImgUrl = data.authorImg;
        } else {
            var avatarImgUrl = '${request.contextPath}/images/avatar.png';
        }
        if(data.createdPrice){//创作者报价
            // var createdPrice = '￥' + commonFun.formatNumber((data.createdPrice), 0, '');
            var createdPrice = commonFun.formatNumber((data.createdPrice), 2, '￥');
        } else {
            var createdPrice = '按需定价';
        }
        if(data.isInvalid == 1){//1.未失效，0.失效
            var isInvalid = 'hide-side-invalid-wrap';
            var invalidColor = '';
        } else {
            var isInvalid = '';
            var invalidColor = 'valid-color';
        }
        if(data.coverLocalUrl){//头像
            var coverLocalUrl = data.coverLocalUrl;
        } else {
            var coverLocalUrl = '${request.contextPath}/images/avatar.png';
        }
        if(data.quotedPrice){//1.未失效，0.失效
            var quotedPrice = commonFun.formatNumber((data.quotedPrice), 2, '￥');
        } else {
            var quotedPrice = '';
        }
        if(data.headImageUrl){//头像
            var headImageUrl = data.headImageUrl;
        } else {
            var headImageUrl = '${request.contextPath}/images/avatar.png';
        }
        if(type == tArticle){
            if(data.publishStatusIndex == 1){//已发布
                var publishSign = 'article-sign';
            } else {//未发布
                // publishSign = '<div class="side-avatar-article-wrap" title="'+data.publishStatusName+'"><i class="iconfont icon-eye-slash"></i></div>';
                var publishSign = '';
            }
        }
        if(data.icoUrl){//头像
            var icoUrl = data.icoUrl;
        } else {
            var icoUrl = '';
        }
        if(data.fans){//粉丝
            if(data.fans < 10000){
                var fansNum = commonFun.formatNumber((data.fans), 0, '');
            } else {
                var fansNum = commonFun.formatNumber((data.fans / 10000), 1, '') + '万';
            }
        } else {
            var fansNum = 0;
        }
        if(type == tAuthor){
            html += ' <li class="fixedClear valid-dom '+invalidColor+'" data-valid="'+data.isInvalid+'" data-proID="'+data.productId+'" data-cartID="'+data.shoppingCartId+'">'
                +'<div class="side-avatar-wrap fixedClear">'
                    +'<img src="'+avatarImgUrl+'" alt="">'
                +'</div>'
                +'<div class="side-right-wrap">'
                    +'<div class="side-tit-wrap side-tit-author-wrap">'
                        +'<p>'+data.authorNickname+'</p>'
                    +'</div>'
                    +'<div class="side-price-wrap">'
                        +createdPrice
                    +'</div>'
                    +'<div class="side-del" onclick="removeItem(4,'+data.shoppingCartId+')">删除</div>'
                +'</div>'
                +'<div class="invalid-wrap '+isInvalid+'">'
                    +'<div class="side-invalid-tit-wrap">失效</div>'
                +'</div>'
            +'</li>';
        } else if(type == tArticle){
            html += '<li class="fixedClear valid-dom '+invalidColor+'"  data-publish="'+data.publishStatusIndex+'" data-valid="'+data.isInvalid+'" data-cartID="'+data.shoppingCartId+'">'
                +'<div class="side-avatar-wrap side-article-img-wrap">'
                    +'<img src="'+coverLocalUrl+'" alt="">'
                    +'<div class="side-avatar-article-wrap '+publishSign+'" title="'+data.publishStatusName+'"><i class="iconfont icon-eye-slash"></i></div>'
                +'</div>'
                +'<div class="side-right-wrap side-article-right-wrap">'
                    +'<div class="side-tit-wrap">'
                        // +'<p>'+data.authorNickname+'</p>'
                        +'<p>'+data.title+'</p>'
                    +'</div>'
                    +'<div class="side-price-wrap">'
                        +quotedPrice
                    +'</div>'
                    +'<div class="side-del" onclick="removeItem(5, '+data.shoppingCartId+')">删除</div>'
                +'</div>'
                +'<div class="invalid-wrap '+isInvalid+'">'
                    +'<div class="side-invalid-tit-wrap">失效</div>'
                +'</div>'
            +'</li>';
        } else if(type == tDistri){
            html += '<li class="fixedClear valid-dom '+invalidColor+'" data-valid="'+data.isInvalid+'" data-accountID="'+data.accountId+'" data-cartID="'+data.shoppingCartId+'">'
                +'<div class="side-avatar-wrap">'
                    +'<img src="'+headImageUrl+'" alt="">'
                +'</div>'
                +'<img class="side-account-icon" src="'+icoUrl+'" alt="">'
                +'<div class="side-right-wrap">'
                    +'<div class="side-tit-wrap">'
                        +'<p class="color-gray-303">'+data.accountName+'</p>'
                        +'<p class="color-gray-646">'+data.accountId+'</p>'
                    +'</div>'
                    +'<div class="side-price-wrap color-blue">'
                        + '粉丝数：' + fansNum
                    +'</div>'
                    +'<div class="side-del" onclick="removeItem(6, '+data.shoppingCartId+')">删除</div>'
                +'</div>'
                +'<div class="invalid-wrap '+isInvalid+'">'
                    +'<div class="side-invalid-tit-wrap">失效</div>'
                +'</div>'
            +'</li>';
        }
    }
    return html;
}

//悬停显示删除
function handleSideCartDel() {
    $('.cart-side-content-wrap ul li').mouseover(function(){
        var $this = $(this);
        // if($this.data('valid') == 1){
        //     $this.find('.side-del').show();
        // }
        $this.find('.side-del').show();
    }).mouseout(function(){
        var $this = $(this);
        $this.find('.side-del').hide();
    
    })
}

//点击侧边栏一键清空
function sideClearAllData() {
    var getCartTotle = $('#js-cart-side-num').text();
    if(getCartTotle > 0){
        $('#js-pop-clear-wrap').show();
    } else {
        layer.msg('没有可清除的数据！', {time: 1500});
    }
}

//侧边栏选购车-全屏显示
function goCartPage(type){
    var getLoginFun = function (isLogin) {
        if(isLogin == 'Y'){
            $('.pop-wrap').hide();
            if(type == 0){
                // console.log(isLogin, type)
                window.location.href = domain + '/cart/cart_author.html';
            } else if(type == 1){
                window.location.href = domain + '/cart/cart_article.html';
            } else if(type == 2){
                window.location.href = domain + '/cart/cart_distribution.html';
            }
        }
    }
    getUserLoginInfo(getLoginFun, 'showpop');
}