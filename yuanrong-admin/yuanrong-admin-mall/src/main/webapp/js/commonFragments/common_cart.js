
var tAuthor = 'author',
    tArticle = 'article',
    tDistri = 'distri',
    sideAuthor = 'sAuthor',
    sideArticle = 'sArticle',
    sideDis = 'sDis',
    popNoteWarningHTML = '<span class="pop-note-icon">!</span> ',
    popNoteWrapDom = $('#js-pop-note-wrap'),
    popValidWrapDom = $('#js-pop-valid-wrap'),
    popDelWrapDom = $('#js-pop-del-wrap'),
    popClearWrapDom = $('#js-pop-clear-wrap'),
    popOverNoteWrapDom = $('#js-pop-cartnum-wrap'),
    cartTabWrapDom = $('#js-cart-tab-wrap'),
    cartType = cartTabWrapDom.find('active').data('id'),
    idsArr = [],
    pubIDsArr = [],//发布需求传productID
    authorTableDom = $('#js-cart-author-table'),
    authorListWrapDom = $('#js-cart-author-list-con-wrap'),
    articleTableDom = $('#js-cart-article-table'),
    articleListWrapDom = $('#js-cart-article-list-con-wrap'),
    diseTableDom = $('#js-cart-dis-table'),
    disListWrapDom = $('#js-cart-dis-list-con-wrap'),
    popOrderInfoWrapDom =  $('#js-pop-order-info-wrap'),
    popOrderSubmitBtnDom = $('#js-pop-order-commit-btn'),
    cartValidArrLen = 0,
    cartValidArr = [],//失效数组
    cartExportDataBtnDom = $('#js-export-cart-data'),
    cartBuyBtnDom = $('.cart-buy-btn'),
    delSelectedBtnDom = $('#js-del-selected-btn'),
    btnNoBorderDdBtnClass = 'btn-border-disabled',
    btnBorderDisabledClass = 'btn-no-border-disabled',
    cartSideNumDom = $('#js-cart-side-num'),
    siteCartNumDom = $('#js-site-cart-num'),
    cartNum100 = 100,
    pageNameObj = {},
    artTotleNums = 0,
    tableData = {};//导出数据
//计算选购车总计数量
//选购车创作者，作品以及营销分发数量
getCartDataNums('cart');
//计算选购车数量
function getCartDataNums(type) {
    if(type == 'cart'){
        var postUrl = newDomain + '/cart/num';//选购车分类
    } else if(type == tDistri){
        var postUrl = newDomain + '/cart/accountTypeNum';//账号分类
    }
    commonFun.commonAjax(postUrl, 'post', {}, function(res){
        // console.log(res);
        if(res.status == 1){
            var sucData = res.data;
            if(type == 'cart'){
                if(sucData.length){
                    for(var i = 0, len = sucData.length; i < len; i ++){
                        var nums = sucData[i];
                        if(i == 0){
                            var cartAuthorNums = nums.num ? nums.num : 0;
                            $('#js-cart-author-totle').text('['+sucData[i].num+']');
                            $('.side-author-totle').text(sucData[i].num);
                        } else if(i == 1){
                            var cartArticleNums = nums.num ? nums.num : 0;
                            artTotleNums = cartArticleNums;
                            $('#js-cart-article-totle').text('['+sucData[i].num+']');
                            $('.side-article-totle').text(sucData[i].num);
                        } else if(i == 2){
                            var cartDisNums = nums.num ? nums.num : 0;
                            $('#js-cart-dis-totle').text('['+sucData[i].num+']');
                            $('.side-dis-totle').text(sucData[i].num);
                        }
                    }
                    var cartTotle = cartAuthorNums + cartArticleNums + cartDisNums;
                    if(cartTotle > 0){
                        cartSideNumDom.text(cartTotle);
                        siteCartNumDom.text(cartTotle);
                    } else {
                        cartSideNumDom.text(0);
                        siteCartNumDom.text(0);
                    }
                    
                }
                
            } else if(type == tDistri){
                $('#js-cart-wx-num').text(sucData.weixinNum);
                $('#js-cart-wb-num').text(sucData.weiBoNum);
                $('#js-cart-sv-num').text(sucData.shortVideoNum);

                // for(var i = 0, len = sucData.length; i < len; i ++){
                //     if(i == 0){
                //         $('#js-cart-author-totle').text('['+sucData[i].num+']');
                //     } else if(i == 1){
                //         $('#js-cart-article-totle').text('['+sucData[i].num+']');
                //     } else if(i == 2){
                //         $('#js-cart-dis-totle').text('['+sucData[i].num+']');
                //     }
                // }
            }
        }
    })
}
//选购车无数据
function cartNoDataFun(type) {
    var cartNoDataHTML = '';
    if(type == tAuthor){
        var noDataType = '创作者';
        var goUrl = domain + '/contentBank/author_images.html';
    } else if(type == tArticle){
        var noDataType = '作品';
        var goUrl = domain + '/contentBank/original_work.html';
    } else if(type == tDistri){
        var noDataType = '账号';
        var goUrl = domain + '/ipTrade/ip_trade_distribution.html?adType=0';
    }
    cartNoDataHTML = '<div class="side-cart-no-data"><img src="../images/cart/cart.png" alt=""><p class="no-data-p1">选购车空空的，赶快去挑选心仪的'+noDataType+'吧</p><p class="no-data-p2"><a href="'+goUrl+'">去看看</a></p></div>';
    return cartNoDataHTML;
}

//选购车content的高度
// var wrapMinHeight = $('.wrap').height();
// var cartCommonNav = $('.cart-tab-con-wrap') ? $('.cart-tab-con-wrap').height() : 0;
// var cartCommonTab = $('.cart-wrap .list-head-tit-wrap').height() ? $('.cart-wrap .list-head-tit-wrap').height() : 0;
// var cartConMinHeight = wrapMinHeight - cartCommonNav - cartCommonTab;
// $('.list-con-wrap').css('min-height', cartConMinHeight + 'px');\

//点击关闭弹框
$('.pop-close-btn,.pop-btn-cancle').off('click').on('click', function () {
    $('.pop-wrap').hide();
})

//根据属性计算数量
function calNumByAttrFun(attr, dom) {
    if(attr){
        var attrArr = [],
            attrDataDom = dom,
            attrDataDomLen = attrDataDom.length;
        if(attr == 'valid'){//是否失效
            for(var i = 0; i < attrDataDomLen; i++){
                if(attrDataDom.eq(i).data(attr) == 0){//0==失效
                    attrArr.push(attrDataDom.eq(i));
                }
            }
        } else if(attr == 'publish'){//发布、未发布
            for(var i = 0; i < attrDataDomLen; i++){
                if(attrDataDom.eq(i).data(attr) == 1){//1==已发布
                    attrArr.push(attrDataDom.eq(i));
                }
            }
        }  
        return attrArr;
    }
}
//点击加入选购车
function addCartData(type, id, imgSrc, e) {//type类型——author，article，distri；id——商品id；imgSrc——商品图片src；e——点击对象
    //判断用户是否登陆
    var getLoginFun = function (isLogin) {
        if(isLogin == 'Y'){
            var postUrlNum = newDomain + '/cart/num';//选购车分类
            commonFun.commonAjax(postUrlNum, 'post', {}, function(res){
                // console.log(res);
                if(res.status == 1){
                    var sucData = res.data;
                    if(sucData.length){
                        for(var i = 0, len = sucData.length; i < len; i ++){
                            var nums = sucData[i];
                            if(i == 0){
                                var cartAuthorNums = nums.num ? nums.num : 0;
                                $('#js-cart-author-totle').text('['+sucData[i].num+']');
                                $('.side-author-totle').text(sucData[i].num);
                                
                                
                            } else if(i == 1){
                                var cartArticleNums = nums.num ? nums.num : 0;
                                $('#js-cart-article-totle').text('['+sucData[i].num+']');
                                $('.side-article-totle').text(sucData[i].num);
                            } else if(i == 2){
                                var cartDisNums = nums.num ? nums.num : 0;
                                $('#js-cart-dis-totle').text('['+sucData[i].num+']');
                                $('.side-dis-totle').text(sucData[i].num);
                            }

                        }

                        var cartTotle = cartAuthorNums + cartArticleNums + cartDisNums;
                        var btnTxt = '去看看';
                        if(cartTotle > 0){
                            cartSideNumDom.text(cartTotle);
                            siteCartNumDom.text(cartTotle);
                        } else {
                            cartSideNumDom.text(0);
                            siteCartNumDom.text(0);
                        }

                        if(type == tAuthor){
                            var postUrl = newDomain + '/cart/shoppingCart_addAuthor';
                            if(cartAuthorNums > cartNum100){
                                var popTxtNote = '选购车中心仪的创作者太多啦，快去看看吧！';
                                setPopInfoByCondition(0, '', '', popTxtNote, btnTxt,popOverNoteWrapDom,'.pop-btn-blue');
                            } else {
                                addCartDataPost(type, id, imgSrc, e, postUrl)
                            }
                            
                        } else if(type == tArticle){
                            var postUrl = newDomain + '/cart/shoppingCart_addProduction';

                            if(cartArticleNums > cartNum100){
                                var popTxtNote = '选购车中心仪的作品太多啦，快去看看吧！';
                                setPopInfoByCondition(1, '', '', popTxtNote, btnTxt,popOverNoteWrapDom,'.pop-btn-blue');
                            } else {
                                addCartDataPost(type, id, imgSrc, e, postUrl)
                            }
                        } else if(type == tDistri){
                            var postUrl = newDomain + '/cart/shoppingCart_addAccount';
                            if(cartDisNums > cartNum100){
                                var popTxtNote = '选购车中心仪的账号太多啦，快去看看吧！';
                                setPopInfoByCondition(2, '', '', popTxtNote, btnTxt,popOverNoteWrapDom,'.pop-btn-blue');
                            } else {
                                addCartDataPost(type, id, imgSrc, e, postUrl)
                            }
                        }
                    }
                }
            })
            
        }
    };
    getUserLoginInfo(getLoginFun, 'showpop');
}
//加入到选购车post
function addCartDataPost(type, id, imgSrc, e, postUrl) {
    var postData = {
        productId: id
    };
    commonFun.commonAjax(postUrl, 'post', postData, function(res){
        if(res.status == 1){
            var scrollTop =  document.documentElement.scrollTop || document.body.scrollTop || 0;
            var cartOffset = cartSideNumDom.offset();
            var flyer = $('<img class="u-flyer" style="width: 50px; border-radius: 50%;" src="'+imgSrc+'">');
            flyer.fly({
                start: {
                    left: e.pageX,
                    top: e.pageY - scrollTop
                },
                end: {
                    left: cartOffset.left + 10,
                    top: cartOffset.top - scrollTop + 10,
                    width: 0,
                    height: 0
                },
                onEnd: function(){
                    layer.msg('已成功加入选购车！', {time: 1500});
                    // $("#js-add-cart-suc-note").text('已成功加入选购车！').fadeIn().animate({width: '160px'}, 200).fadeOut(1000);
                    // e.currentTarget.classList = 'list-item-btn list-item-btn-add btn-border-disabled';
                    e.currentTarget.classList.add(btnNoBorderDdBtnClass);
                    e.currentTarget.innerText = '已加入选购车';
                    
                    var getPageName = commonFun.getUrlPageName();
                    if(/author_detail/.test(getPageName)){
                        $('.car-btn').addClass(btnNoBorderDdBtnClass);
                    }
                    if(/article_detail/.test(getPageName)){
                        $('.add-buy').addClass(btnNoBorderDdBtnClass);
                    }
                    
                    if(type == tAuthor){
                        getSideListData(tAuthor);
                    } else if(type == tArticle){
                        getSideListData(tArticle);
                    } else if(type == tDistri){
                        getSideListData(tDistri);
                    }
                    this.destory();
                }
            });
        } else {
            layer.msg(res.msg, {time: 1500});
        }

    })
}
//获取选购车列表数据
function getCartListData(type, dataType) {
    var documentHeight = $(document).height();
    if(type == tAuthor){
        var postUrl = newDomain + '/cart/shoppingCart_authorList';
    } else if(type == tArticle){
        var postUrl = newDomain + '/cart/shoppingCart_productList';
        if(dataType == 2){//已发布
            var postData = {
                publishStatus: 1
            };
            var articleType = '已发布';
        } else if(dataType == 3){//未发布
            var postData = {
                publishStatus: 0
            };
            var articleType = '未发布';
        } else {//全部
            var postData = {};
            var articleType = '全部';
        }
    } else if(type == tDistri){
        var postUrl = newDomain + '/cart/shoppingCart_accountList';
        if(dataType == 1){//微信
            var postData = {
                platformIndex: 1
            };
        } else if(dataType == 2){//微博
            var postData = {
                platformIndex: 2
            };
        } else if(dataType == 3){//短视频
            var postData = {
                platformIndex: 3
            };
        }
    }
    commonFun.commonAjax(postUrl, 'post', postData, function(res){
        if(res.status == 1){
            var sucData = res.data;
            tableData = sucData;
            if(type == tArticle){//作品
                if(sucData.content.length){
                    var articleHTML = cartListModule(sucData.content, type);
                    articleListWrapDom.html('').append(articleHTML);
                    var artDataLen = $('.list-con-main-wrap',articleListWrapDom).length;
                    var rTotle = artTotleNums;
                    rTotle ? rTotle = rTotle : rTotle = 0;
                    if(articleType == '未发布'){
                        var pubNum = rTotle - artDataLen;
                    } else if(articleType == '已发布' || articleType == '全部'){
                        var pubNum = calNumByAttrFun('publish', $('.list-con-main-wrap')) ? calNumByAttrFun('publish', $('.list-con-main-wrap')).length : 0;
                    }
                    var unPubNum = rTotle ? rTotle - pubNum : 0;
                    
                    // $('.side-article-totle').text(sucData[i].num);
                    $('#js-article-pub-num').text(pubNum);
                    $('#js-article-un-num').text(unPubNum);
                    handleCheckAll($('.list-check-all'), $('#js-cart-article-list-con-wrap'));
                } else {
                    var articleHTML = cartNoDataFun(tArticle);
                    articleListWrapDom.html('').append(articleHTML);
                    // cartNoDataSet(type);
                }
            } else {
                if(sucData.length){
                    var html = cartListModule(sucData, type);
                    idsArr = [];
                    pubIDsArr = [];
                    if(type == tAuthor){
                        authorListWrapDom.html('').append(html);
                        setTimeout(() => {
                            handleCheckAll($('.list-check-all'), $('#js-cart-author-list-con-wrap'));
                        }, 100);
                        // var aTotle = $('.list-con-main-wrap',authorListWrapDom).length;
                        // $('#js-cart-author-totle').text('['+aTotle+']');
                    } else if(type == tDistri){
                        disListWrapDom.html('').append(html);
                        setTimeout(() => {
                            handleCheckAll($('.list-check-all'), $('#js-cart-dis-list-con-wrap'));
                        }, 100);
                        // var dTotle = $('.list-con-main-wrap',disListWrapDom).length;
                        // $('#js-cart-dis-totle').text('['+dTotle+']');
                    }
                } else {
                    cartNoDataSet(type);               
                }
                getCartDataNums(tDistri);
            }
            // getCartDataNums('cart');
            // 失效数据计算
            cartValidArr = calNumByAttrFun('valid', $('.list-con-main-wrap')) ? calNumByAttrFun('valid', $('.list-con-main-wrap')) : [];
            cartValidArr ? cartValidArrLen = cartValidArr.length : 0;
            // list-check-all is-checked-all
            removeDisabledClass(idsArr.length);
            
        } else if(res.status == 401){//未登录
            window.location.href = centerUrl + 'login';
        } else {
            cartNoDataSet(type);
        }
        
        if(cartValidArrLen){
            $('#js-clear-invalid-data').removeClass(btnNoBorderDdBtnClass);
        } else {
            $('#js-clear-invalid-data').addClass(btnNoBorderDdBtnClass);
        }
    })
}
//列表模板
function cartListModule(sucData, type) {
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
            var invalidCheckDis = '';
            var validDomClass = 'valid-dom';
            if(type == tAuthor){
                var detailUrl = '/contentBank/author_detail_'+data.productId+'.html';
            } else if(type == tArticle){
                var detailUrl = '/contentBank/article_detail_'+data.productId+'.html';
            }
        } else {
            var isInvalid = '';
            var invalidColor = 'valid-color';
            var invalidCheckDis = 'list-check-disabled';
            var validDomClass = '';
            var detailUrl = '';
        }
        if(data.coverLocalUrl){//头像
            var coverLocalUrl = data.coverLocalUrl;
        } else {
            var coverLocalUrl = '${request.contextPath}/images/avatar.png';
        }
        if(data.quotedPrice){//1.未失效，0.失效
            // var quotedPrice = '￥' + commonFun.formatNumber((data.quotedPrice), 0, '');
            var quotedPrice = commonFun.formatNumber((data.quotedPrice), 2, '￥');
        } else {
            var quotedPrice = '';
        }
        if(data.headImageUrl){//头像
            var headImageUrl = data.headImageUrl;
        } else {
            var headImageUrl = '${request.contextPath}/images/avatar.png';
        }
        // if(data.publishStatusIndex){//发布状态
        //     if(data.publishStatusIndex == 1){//已发布
        //         var publishStatus = '';
        //     } else {//未发布
        //         var publishStatus = '<div class="side-avatar-article-wrap" title="'+data.publishStatusName+'"><i class="iconfont icon-eye-slash"></i></div>';
        //     }
        // }
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
            html += '<div class="list-con-main-wrap '+validDomClass+' fixedClear" data-valid="'+data.isInvalid+'" data-proid="'+data.productId+'" data-cartid="'+data.shoppingCartId+'">' 
                +'<ul class="fixedClear '+invalidColor+'">'
                    +'<li class="li-check '+invalidCheckDis+'" onclick="handleCheckSingle($(this).find(\'.list-check-span\'), 1)">'
                        +'<span class="list-check-span '+invalidCheckDis+'" data-proid="'+data.productId+'" data-cartid="'+data.shoppingCartId+'"  ></span>'
                    +'</li>'
                    +'<li class="li-tit-wrap fixedClear">'
                        +'<a href="'+detailUrl+'" target="_blank">'
                            +'<div class="li-avatar-wrap">'
                                +'<img class="li-avatar-img" src="'+avatarImgUrl+'" alt="">'
                            +'</div>'
                            +'<div class="li-name-wrap li-author-name">'
                                +'<p class="">'+data.authorNickname+'</p>'
                            +'</div>'
                        +'</a>'
                    +'</li>'
                    +'<li class="li-intro" style="text-align: left;" title="'+data.introduction+'">'+data.introduction+'</li>'
                    +'<li class="color-blue">'+createdPrice+'</li>'
                    +'<li class="li-del" onclick="removeItem(1, '+data.shoppingCartId+')">删除</li>'
                +'</ul>'
                +'<div class="invalid-wrap '+isInvalid+'"><div class="invalid-tit-wrap">失效</div></div>'
            +'</div>';
        } else if(type == tArticle){
            html += '<div class="list-con-main-wrap '+validDomClass+' fixedClear" data-valid="'+data.isInvalid+'" data-publish="'+data.publishStatusIndex+'" data-cartid="'+data.shoppingCartId+'">' 
                +'<ul class="fixedClear '+invalidColor+'">'
                    +'<li class="li-check '+invalidCheckDis+'" onclick="handleCheckSingle($(this).find(\'.list-check-span\'), 2)">'
                        +'<span class="list-check-span '+invalidCheckDis+'"  data-proid="'+data.productId+'"  data-cartid="'+data.shoppingCartId+'"  ></span>'
                    +'</li>'
                    +'<li class="li-article-tit-wrap fixedClear">'
                        +'<a href="'+detailUrl+'" target="_blank">'
                            +'<div class="li-avatar-wrap li-article-list-img-wrap fixedClear">'
                                +'<img class="li-avatar-img" src="'+coverLocalUrl+'" alt="">'
                                +'<div class="side-avatar-article-wrap '+publishSign+'" title="'+data.publishStatusName+'"><i class="iconfont icon-eye-slash"></i></div>'
                            +'</div>'
                            +'<div class="li-name-wrap li-article-name">'
                                +'<p class="" title="'+data.title+'">'+data.title+'</p>'
                            +'</div>'
                        +'</a>'
                    +'</li>'
                    +'<li class="li-article-author">'+data.authorNickname+'</li>'
                    +'<li class="c-yellow goods-price-wrap">'+quotedPrice+'<input type="hidden" class="art-price" value="'+data.quotedPrice+'"></li>'
                    +'<li class="li-del" onclick="removeItem(2, '+data.shoppingCartId+')">删除</li>'
                +'</ul>'
                +'<div class="invalid-wrap '+isInvalid+'"><div class="invalid-tit-wrap">失效</div></div>'
            +'</div>';
        } else if(type == tDistri){
            var subHtml = '';
            if(data.priceInfo){
                var subSucData = data.priceInfo;
                for(var k = 0, kLen = subSucData.length; k < kLen; k++){
                    if(subSucData[k].isOriginal == 1){
                        var isHideY = '';
                    } else {
                        var isHideY = 'account-y-hide';
                    }
                    if(subSucData[k].price){
                        // var price = '￥' + commonFun.formatNumber((subSucData[k].price), 2, '￥');
                        var price = commonFun.formatNumber((subSucData[k].price), 2, '￥');
                    } else {
                        var price = 0;
                    }
                    
                    subHtml += '<div class="div-account-price-wrap"><div class="li-account-detail">'
                        +'<p class="li-account-detail-one" style="">'
                            +'<span >'+subSucData[k].priceName+'</span>'
                            +'<span class="account-y-wrap">'+price+'</span>'
                            // +'<span class="account-y-note"><span class="account-y-note-txt">报价含原创+发布服务</span><i class="account-tri"></i></span>'
                            
                            
                            // +'<span class="account-y-txt-wrap trans-all-2"><span class="account-y trans-all-2 '+isHideY+'"><img class="origin-img" src="'+domain+'/images/commonFragments/origin.png" alt=""></span><span class="account-y-note  trans-all-2"><span class="account-y-note-txt">报价含原创+发布服务</span><i class="account-tri"></i></span></span> '
                        +'</p>'
                        // +'<p class="li-account-detail-two" style="">'
                        //     +'<span >原创内容</span>'
                        //     +'<span style="float: right;">￥1.10</span></p>'
                        // +'<p class="li-account-detail-three" style="">'
                        //     +'<span >次条发布</span>'
                        //     +'<span style="float: right;">--</span>'
                        // +'</p>'
                        // +'<p class=""><span >有效期至:</span> <span style="float: right;">2018-07-25</span></p>'
                    +'</div></div>';
                }
            }
            html += '<div class="list-con-main-wrap '+validDomClass+' fixedClear" data-valid="'+data.isInvalid+'" data-cartid="'+data.shoppingCartId+'">'
                +'<ul class="fixedClear '+invalidColor+'">'
                    +'<li class="li-check '+invalidCheckDis+'" onclick="handleCheckSingle($(this).find(\'.list-check-span\'), 3)">'
                        +'<span class="list-check-span '+invalidCheckDis+'"  data-proid="'+data.productId+'" data-cartid="'+data.shoppingCartId+'"  ></span>'
                    +'</li>'
                    +'<li class="li-account li-account-avatar-pos fixedClear">'
                        +'<div class=" div-account-avatar-pos fixedClear">'
                            +'<div class="li-avatar-wrap">'
                                +'<img class="li-avatar-img" src="'+headImageUrl+'" alt="">'
                            +'</div>'
                            +'<img class="li-platform-icon" src="'+data.icoUrl+'" alt="">'
                            +'<div class="li-name-wrap li-accont-name">'
                                +'<p class="">'+data.accountName+'</p>'
                                +'<p class="">'+data.accountId+'</p>'
                            +'</div></div>'
                    +'</li>'
                    +'<li class="li-account">'+fansNum+'</li>'
                    +'<li class="li-account li-account-price-wrap">'
                        +subHtml
                    +'</li>'
                    +'<li class="li-del" onclick="removeItem(3, '+data.shoppingCartId+')">删除</li>'
                +'</ul>'
                +'<div class="invalid-wrap '+isInvalid+'"><div class="invalid-tit-wrap">失效</div></div>'
            +'</div>';
        }
    }
    return html;
}
//选购车无数据
function cartNoDataSet(type) {
    if(type == tAuthor){
        var authorHTML = cartNoDataFun(tAuthor);
        authorListWrapDom.html('').append(authorHTML);
    } else if(type == tArticle){
        var articleHTML = cartNoDataFun(tArticle);
        articleListWrapDom.html('').append(articleHTML);
        $('#js-article-pub-num').text(0);
        $('#js-article-un-num').text(0);
    } else if(type == tDistri){
        var disHTML = cartNoDataFun(tDistri);
        disListWrapDom.html('').append(disHTML);
    }
    idsArr = [];
    pubIDsArr = [];
}
//点击弹框中确认清空失效数据或删除
function popConfirmClearData(type, id, opeType) {
    if (opeType == 'clear') {
        var postUrl = newDomain + '/cart/removeFailureProduct',
            postData = {
                cartTypeIndex: id
            };
    } else if(opeType == 'del'){
        var postUrl = newDomain + '/cart/removeShoppingCartProduct',
            postData = {
                shoppingCartIds: idsArr.join(',')
            };
    } else if(opeType == 'sideclear'){
        var postUrl = newDomain + '/cart/removeAll',
            postData = {};
    }    

    // var getPageName = commonFun.getUrlPageName();
    // var getMatchName = commonFun.pageNameReg(getPageName);
    // var getFunByName = function (isDelSuc) {
    //     if(isDelSuc == 'Y'){
    //         //获取页面名称，调用相应的列表接口
    //         if(getMatchName == 'author_images'){//创作者列表
    //             var listurl = newDomain+'/author/getAuthorList';
    //             getAuthorList(listurl,'post');
    //         } else if(/author_detail/.test(getPageName)){//创作者详情
    //             $('.car-btn').removeClass(btnNoBorderDdBtnClass);
    //         } else if(getMatchName == 'original_work'){//作品列表
    //             getWorkList();
    //         } else if(getMatchName == 'ip_trade_distribution'){//ip交易-营销分发
    //             var tType = $('#js-tab-ads-sort-wrap').find('li.active').data('type');
    //             var postData = {
    //                 cp: '1',
    //                 rows: '20'
    //             };
    //             if(tType == 'secWeChat'){
    //                 getWechatData(postData);
    //             } else if(tType == 'secWeibo'){
    //                 getWeibotData(postData);
    //             } else if(tType == 'secShortVideo'){
    //                 getShortVideoData(postData);
    //             }
    //         }
    //     }
    // };
    // delCartItemData(type, id, getMatchName, getFunByName);


    commonFun.commonAjax(postUrl, 'post', postData, function(res){
        if(res.status == 1){
            idsArr = [];
            pubIDsArr = [];
            delSelectedBtnDom.addClass(btnNoBorderDdBtnClass);
            
            if(type == tAuthor){
                getCartListData(tAuthor);
            } else if(type == tArticle){
                getCartListData(tArticle);
            } else if(type == tDistri){
                getCartListData(tDistri);
            } else {
                window.location.reload();
            }

            if(opeType == 'sideclear'){
                var authorHTML = cartNoDataFun(tAuthor);
                var articleHTML = cartNoDataFun(tArticle);
                var disHTML = cartNoDataFun(tDistri);
                $('#js-cart-side-author-wrap').html('').append(authorHTML);
                $('.side-author-totle').text(0);
                $('.side-article-totle').text(0);
                $('.side-dis-totle').text(0);
                $('#js-cart-side-article-wrap').html('').append(articleHTML);
                $('#js-publish-totle').text(0);
                $('#js-unpublish-totle').text(0);
                $('#js-cart-side-distribution-wrap').html('').append(disHTML);
            }    
            getCartDataNums(tDistri);
            getCartDataNums('cart');
        }
        $('.pop-wrap').hide();
        popClearWrapDom.find('.pop-btn-red').data('id', '').data('type', '').data('data-opetype', '');
    })
}

//点击清空失效数据按钮或者删除多条数据
function clearCartData(type, id, opeType) {//type-类型；id-清除失效数据时，给后台传的id，需要传递的id；opeType-清空失效 或者删除
    if(type == tAuthor){
        var noteTxt = '创作者';
    } else if(type == tArticle){
        var noteTxt = '作品';
    } else if(type == tDistri){
        var noteTxt = '账号';
    }
    if(opeType == 'clear'){//清空失效
        if(cartValidArrLen > 0){
            var popTxtNote = popNoteWarningHTML + ' 确定要清空 ' +cartValidArrLen + ' 个失效' + noteTxt + '吗？',
                btnTxt = '确定清除';
            setPopInfoByCondition(type, id, opeType, popTxtNote, btnTxt,popClearWrapDom,'.pop-btn-red');
        }
        
    } else if(opeType == 'singledel'){//选购车删除单个
        var popTxtNote = popNoteWarningHTML + ' 确定要删除该'+noteTxt+'吗？',
            btnTxt = '确定删除';
        setPopInfoByCondition(type, id, opeType, popTxtNote, btnTxt,popClearWrapDom,'.pop-btn-red');
    } else if(opeType == 'sideclear'){//侧边栏一键清空
        var getCartTotle = cartSideNumDom.text();
        if(getCartTotle > 0){
            var popTxtNote = popNoteWarningHTML + ' 确定要清空选购车的全部数据吗？',
            btnTxt = '确定清空';
            setPopInfoByCondition(type, '', opeType, popTxtNote, btnTxt,popClearWrapDom,'.pop-btn-red');
        } else {
            layer.msg('没有可清除的数据！', {time: 1500});
        }
    } else if(opeType == 'sidedel'){//侧边栏删除单个
        delCartItemData(type, id);
    } else if(opeType == 'del'){//删除
        if(idsArr.length > 0){
            var popTxtNote = popNoteWarningHTML + ' 确定要删除选中的 '+idsArr.length + ' 个' + noteTxt + '吗？',
                btnTxt = '确定删除';
            // clearCartDataPopInfo(type, id, opeType, popTxtNote, btnTxt);
            setPopInfoByCondition(type, '', opeType, popTxtNote, btnTxt,popClearWrapDom,'.pop-btn-red');
        }
    }

}
//清空或删除弹框信息设置
function setPopInfoByCondition(type, id, opeType, noteTxt, btnTxt, popDom, popBtn) { 
    popDom.find('.pop-main').html(noteTxt);
    popDom.find(popBtn).text(btnTxt);
    popDom.find(popBtn).data('id', id).data('type', type).data('opetype', opeType);
    popDom.show();
}
//从选购车中移除
function removeItem(type, id) {
    //选购车中删除，需要弹框确认
    if(type == 1 || type == 2 || type == 3){
        if(type == 1){
            var noteTxt = '创作者';
        } else if(type == 2){
            var noteTxt = '作品';
        } else if(type == 3){
            var noteTxt = '账号';
        }
        popDelWrapDom.find('.pop-main').html('<span class="pop-note-icon">!</span> 确定要删除该'+noteTxt+'吗？');
        popDelWrapDom.find('.pop-btn-red').data('id', id).data('type', type);
        popDelWrapDom.show();
    } else {//侧边栏直接删除
        var getPageName = commonFun.getUrlPageName();
        var getMatchName = commonFun.pageNameReg(getPageName);
        var getFunByName = function (isDelSuc) {
            if(isDelSuc == 'Y'){
                //获取页面名称，调用相应的列表接口
                if(getMatchName == 'author_images'){//创作者列表
                    var listurl = newDomain+'/author/getAuthorList';
                    getAuthorList(listurl,'post');
                } else if(/author_detail/.test(getPageName)){//创作者详情
                    $('.car-btn').removeClass(btnNoBorderDdBtnClass);
                } else if(getMatchName == 'original_work'){//作品列表
                    getWorkList();
                } else if(getMatchName == 'ip_trade_distribution'){//ip交易-营销分发
                    var tType = $('#js-tab-ads-sort-wrap').find('li.active').data('type');
                    var postData = {
                        cp: '1',
                        rows: '20'
                    };
                    if(tType == 'secWeChat'){
                        getWechatData(postData);
                    } else if(tType == 'secWeibo'){
                        getWeibotData(postData);
                    } else if(tType == 'secShortVideo'){
                        getShortVideoData(postData);
                    }
                }
            }
        };
        delCartItemData(type, id, getMatchName, getFunByName);
    }
}

//选购车删除以及弹框确认删除
function delCartItemData(type, id, pageName, callback) {
    var postUrl = newDomain + '/cart/removeShoppingCartProduct',
        postData = {
            shoppingCartIds: id
        };
        // alert(id, type);
    commonFun.commonAjax(postUrl, 'post', postData, function(res){
        if(res.status == 1){
            var isDelSuc = 'Y';
            if(type == 4){
                getSideListData(tAuthor);
            } else if(type == 5){
                getSideListData(tArticle);
            } else if(type == 6){
                getSideListData(tDistri);
            } else if(type == 1){
                getCartListData(tAuthor);
            } else if(type == 2){
                getCartListData(tArticle);
            } else if(type == 3){
                getCartListData(tDistri);
            } 
            callback && callback(isDelSuc);
        } else {
            var isDelSuc = 'N';
            
            layer.msg(res.msg, {time: 1500});
        }
        $('.pop-wrap').hide();
        popDelWrapDom.find('.pop-btn-red').data('id', '').data('type', '');
        return isDelSuc;
    })
}
//作品——立即购买
cartBuyBtnDom.off('click').on('click', function () {
    buyProductFun(idsArr);
    // var postUrl = newDomain + '/order/orderInfoBuyer/placeTheProductOrder',
    //     postData = {
    //         shoppingCartIds: idsArr.join(',')
    //     };
    // commonFun.commonAjax(postUrl, 'post', postData, function(res){
    //     if(res.status == 1){
    //         popOrderSubmitBtnDom.data('id', '').data('type', 'type');
    //         popOrderInfoWrapDom.show();
    //     }
    // })
})
//立即购买
function buyProductFun(id, type) {//id：作品id   type: singleBuy,单个作品立即购买
    //判断用户是否登陆
    var getLoginFun = function (isLogin) {
        if(isLogin == 'Y'){
            if(type == 'singleBuy'){//立即购买
                var postUrl = newDomain + '/cart/confirmBuyPro',
                    orderType = 2,
                    postData = {
                        goodsId: id
                    };
                window.location.href = domain + '/pay/orderConfirm.html?type='+orderType+'&yrProductionId='+postData.goodsId;
            } else {//选购车购买
                var postUrl = newDomain + '/cart/confirmBuy', orderType = 3;
                if(typeof id == 'object'){//选购车多个
                    var postData = {
                        shoppingCartIds: id.join(',')
                    }
                } else {//选购车单个
                    var postData = {
                        shoppingCartIds: id
                    }
                }
                window.location.href = domain + '/pay/orderConfirm.html?type='+orderType+'&shoppingCarts='+postData.shoppingCartIds;
            }
            // commonFun.commonAjax(postUrl, 'post', postData, function(res){
            //     if(res.status == 1){
            //         var sucData = res.data;
            //         $('#js-pop-art-num').text(sucData.num);
            //         $('#js-pop-art-sum').text(sucData.costMoney);
            //         $('#js-pop-art-service-sum').text(sucData.invoiceFee);
            //         $('#js-pop-art-pay').text(sucData.totalMoney);
            //         popOrderSubmitBtnDom.data('id', id).data('type', type);
            //         popOrderInfoWrapDom.show();
            //     } else {
            //         layer.msg(res.msg, {time: 1500});
            //     }
            // })
        }
    }
    getUserLoginInfo(getLoginFun, 'showpop');
}
//点击弹框确认提交订单
popOrderSubmitBtnDom.off('click').on('click', function () {
    // window.location.href = domain + '/pay/unifiedOrder.html?orderSn=' + popOrderSubmitBtnDom.data('id');
    // /pay/unifiedOrder.html?orderSn=
        type = $(this).data('type');
    if(type == 'singleBuy'){
        var postUrl = newDomain + '/order/orderInfoBuyer/buyNow';
        var postData = {
            recId: $(this).data('id')
        };
    } else {
        var postUrl = newDomain + '/order/orderInfoBuyer/placeTheProductOrder';
        var postData = {
            shoppingCartIds: idsArr.join(',')
        };
    }
    commonFun.commonAjax(postUrl, 'post', postData, function(res){
        if(res.status == 1){
            idsArr = [];
            pubIDsArr = [];
            var oderID = res.data.orderSn;
        }
        popOrderInfoWrapDom.hide();
        window.location.href = domain + '/pay/unifiedOrder.html?orderSn='+oderID;
    })
})
//列表有选中数据，放开禁止按钮
function removeDisabledClass(len) {
    if(len > 0){
        delSelectedBtnDom.removeClass(btnNoBorderDdBtnClass);
        cartExportDataBtnDom.removeClass(btnNoBorderDdBtnClass);
        cartBuyBtnDom.removeClass(btnBorderDisabledClass);
    } else {
        delSelectedBtnDom.addClass(btnNoBorderDdBtnClass);
        cartBuyBtnDom.addClass(btnBorderDisabledClass);
        cartExportDataBtnDom.addClass(btnNoBorderDdBtnClass);
        $('.list-check-all').removeClass('is-checked-all');
        $('.select-num').text(0);
        $('.select-article-sum').text('￥0');
    }
}

//导出数据
function exportCartData(type, id) {
    var postData = {};
    if(typeof id == 'object'){
        postData.shoppingCartIds = id.join(',')
    } else {
        postData.shoppingCartIds = id
    }
    if(type == tAuthor){
        var postUrl = newDomain + '/cart/download/shopptingCartAuthor';
    } else if(type == tArticle){
        var postUrl = newDomain + '/cart/download/shopptingCartProduct';
    } else if(type == tDistri){
        var postUrl = newDomain + '/cart/download/shopptingCartAccount';
    }
    commonFun.commonAjax(postUrl, 'post', postData, function(res){
        if(res.status == 1){
            
        }
        console.log(res);
        $('#auto_form').attr('action',postUrl);
        $('#auto_form').submit();
        
    })
    
}
//点击导出
cartExportDataBtnDom.off('click').on('click', function () {
    var type = $(this).data('id'),
        time = new Date(),
        year = time.getFullYear(),
        month = time.getMonth() + 1,
        day = time.getDate(),
        hour = time.getHours(),
        min = time.getMinutes(),
        sec = time.getSeconds(),
        formatDate = '';
        month < 10 ? month = '0' + month : month = month;
        day < 10 ? day = '0' + day : day = day;
        hour < 10 ? hour = '0' + hour : hour = hour;
        min < 10 ? min = '0' + min : min = min;
        sec < 10 ? sec = '0' + sec : sec = sec;
        // formatDate = year + '-' + month + '-' + day + ' ' + hour + ':' + min + ':' + sec;
        formatDate = year + month + day + hour + min + sec;
        
     if(type == 1){
        download(tAuthor , '圆融创作者报价单_' + formatDate);
    } else if(type == 2){
        download(tArticle , '圆融作品报价单_' + formatDate);
    } else if(type == 3){
        download(tDistri , '圆融营销账号报价单_' + formatDate);
    }
})

//重组导出数据
function formatExportData(type, expData) {
    var eLen = expData.length;
    var formatData = [];
    if(eLen){
        for(var i = 0; i < eLen; i ++){
            var expName = expData[i];
            
        if(type == tAuthor){
            formatData.push({'序号': i + 1,'创作者': expName.authorNickname, '简介': expName.introduction,'原创价格': '￥' + expName.createdPrice})
        } else if(type == tArticle){
            formatData.push({'序号': i + 1,'作品名称': expName.authorNickname, '作者': expName.introduction,'作品价格': '￥' + expName.createdPrice})
        } else if(type == tDistri){
            formatData.push({'序号': i + 1,'创作者': expName.authorNickname, '简介': expName.introduction,'原创价格': '￥' + expName.createdPrice})
        }
            console.log(expName)
            
        }
        console.log(formatData,'--formatData')
        return formatData;
    }
}
// xlxs导出
//如果使用 FileSaver.js 就不要同时使用以下函数
function saveAs(obj, fileName) {//当然可以自定义简单的下载文件实现方式 
    var tmpa = document.createElement("a");
    tmpa.download = fileName || "下载";
    tmpa.href = URL.createObjectURL(obj); //绑定a标签
    tmpa.click(); //模拟点击实现下载
    setTimeout(function () { //延时释放
        URL.revokeObjectURL(obj); //用URL.revokeObjectURL()来释放这个object URL
    }, 100);
}
const wopts = { bookType: 'xlsx', bookSST: false, type: 'binary' };//这里的数据是用来定义导出的格式类型
// const wopts = { bookType: 'csv', bookSST: false, type: 'binary' };//ods格式
// const wopts = { bookType: 'ods', bookSST: false, type: 'binary' };//ods格式
// const wopts = { bookType: 'xlsb', bookSST: false, type: 'binary' };//xlsb格式
// const wopts = { bookType: 'fods', bookSST: false, type: 'binary' };//fods格式
// const wopts = { bookType: 'biff2', bookSST: false, type: 'binary' };//xls格式

function downloadExl(data, type) {
    const wb = { SheetNames: ['Sheet1'], Sheets: {}, Props: {} };
    wb.Sheets['Sheet1'] = XLSX.utils.json_to_sheet(data);//通过json_to_sheet转成单页(Sheet)数据
    saveAs(new Blob([s2ab(XLSX.write(wb, wopts))], { type: "application/octet-stream" }), "这里是下载的文件名" + '.' + (wopts.bookType=="biff2"?"xls":wopts.bookType));
}
function s2ab(s) {
    if (typeof ArrayBuffer !== 'undefined') {
        var buf = new ArrayBuffer(s.length);
        var view = new Uint8Array(buf);
        for (var i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
        return buf;
    } else {
        var buf = new Array(s.length);
        for (var i = 0; i != s.length; ++i) buf[i] = s.charCodeAt(i) & 0xFF;
        return buf;
    }
}





var idTmr; 
 //获取当前浏览器类型 
  function getExplorer() { 
       var explorer = window.navigator.userAgent ; 
    
       if (explorer.indexOf("MSIE") >= 0) { 
            return 'ie'; 
       } else if (explorer.indexOf("Firefox") >= 0) { 
            return 'Firefox'; 
       } else if(explorer.indexOf("Chrome") >= 0){ 
            return 'Chrome'; 
       } else if(explorer.indexOf("Opera") >= 0){ 
            return 'Opera'; 
       } else if(explorer.indexOf("Safari") >= 0){ 
            return 'Safari'; 
       } 
  } 
    
 //获取到类型需要判断当前浏览器需要调用的方法，目前项目中火狐，谷歌，360没有问题 
  //win10自带的IE无法导出 
  function exportExcel(tableid) { 
       if( getExplorer() == 'ie' ) { 
        var curTbl = document.getElementById(tableid); 
        var oXL = new ActiveXObject("Excel.Application"); 
        var oWB = oXL.Workbooks.Add(); 
        var xlsheet = oWB.Worksheets(1); 
        var sel = document.body.createTextRange(); 
        sel.moveToElementText(curTbl); 
        sel.select(); 
        sel.execCommand("Copy"); 
        xlsheet.Paste(); 
        oXL.Visible = true; 
      
            try { 
                var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls"); 
            } catch (e) { 
                print("Nested catch caught " + e); 
            } finally { 
                 oWB.SaveAs(fname); 
                 oWB.Close(savechanges = false); 
                 oXL.Quit(); 
                 oXL = null; 
                 idTmr = window.setInterval("Cleanup();", 1); 
            } 
          
       } else { 
            tableToExcel(tableid) 
       } 
  } 
  function Cleanup() { 
       window.clearInterval(idTmr); 
       CollectGarbage(); 
  } 
    
 //判断浏览器后调用的方法，把table的id传入即可 
  var tableToExcel = (function() { 
       var uri = 'data:application/vnd.ms-excel;base64,', 
         template = '<html><head><meta charset="UTF-8"></head><body><table>{table}</table></body></html>', 
         base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }, 
         format = function(s, c) { 
          return s.replace(/{(\w+)}/g, 
            function(m, p) { return c[p]; }) } 
       return function(table, name) { 
        if (!table.nodeType) table = document.getElementById(table) 
        var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML} 
        window.location.href = uri + base64(format(template, ctx)) 
       } 
  })() 


function download(type, filename) {
    var oReq = new XMLHttpRequest();
    var ids = 'shoppingCartIds='+idsArr.join(',');
    
    if(type == tAuthor){
        var postUrl = newDomain + '/cart/download/shopptingCartAuthor?shoppingCartIds='+idsArr.join(',');
    } else if(type == tArticle){
        var postUrl = newDomain + '/cart/download/shopptingCartProduct?shoppingCartIds='+idsArr.join(',');
    } else if(type == tDistri){
        var postUrl = newDomain + '/cart/download/shopptingCartAccount?shoppingCartIds='+idsArr.join(',');
    }
    oReq.open("GET", postUrl, true);
    oReq.responseType = "blob";
    oReq.onload = function (oEvent) {
        var content = oReq.response;

        var elink = document.createElement('a');
        elink.download = filename+'.xlsx';
        elink.style.display = 'none';

        var blob = new Blob([content]);
        elink.href = URL.createObjectURL(blob);

        document.body.appendChild(elink);
        elink.click();

        document.body.removeChild(elink);
    };
    oReq.send();
    setTimeout(() => {
        idsArr = [];
        pubIDsArr = [];
        $('.list-check-span').removeClass('is-checked-span');
        removeDisabledClass(idsArr.length);
    }, 1000);
}
