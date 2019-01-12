//选购车选中数量
$('.select-num').text(idsArr.length);
//点击单选
function handleCheckSingle($this, type) {
    var $this = $this,
        dataID = $this.data('cartid'),
        proID = $this.data('proid');
    if(type == 1){
        var tableDom = $('#js-cart-author-list-con-wrap');
    } else if(type == 2){
        var tableDom = $('#js-cart-article-list-con-wrap');
    } else if(type == 3){
        var tableDom = $('#js-cart-dis-list-con-wrap');
    }
    
    var tableList = tableDom.find('.list-con-main-wrap');
    var tableListLen = tableList.length;
    if($this.hasClass('is-checked-span')){
        $this.removeClass('is-checked-span');
        var idsArrLen = idsArr.length;
        for(var i = 0; i < idsArrLen; i ++ ){
            if(idsArr[i] == dataID){
                idsArr.splice(i, 1);
            }
        }
        var pubIDsArrLen = pubIDsArr.length;
        for(var i = 0; i < pubIDsArrLen; i ++ ){
            if(pubIDsArr[i] == proID){
                pubIDsArr.splice(i, 1);
            }
        }
        $('.list-check-all').removeClass('is-checked-all');
        
        if(type == 2){
            var getSum = calArticleSumFun($('.is-checked-span').parents('.list-con-main-wrap'));
            var formatGetSum = commonFun.formatNumber(getSum, 2, '￥');
            $('.select-article-sum').text(formatGetSum);
        }
    } else {
        $this.addClass('is-checked-span');
        idsArr.push(dataID);
        pubIDsArr.push(proID);
        if(tableListLen == idsArr.length){
            $('.list-check-all').addClass('is-checked-all');
        } else {
            $('.list-check-all').removeClass('is-checked-all');
        }
        if(type == 2){
            var getSum = calArticleSumFun($('.is-checked-span').parents('.list-con-main-wrap'));
            // $('.select-article-sum').text(getSum);
            var formatGetSum = commonFun.formatNumber(getSum, 2, '￥');
            $('.select-article-sum').text(formatGetSum);
        }
    }
    $('.select-num').text(idsArr.length);
    removeDisabledClass(idsArr.length);
}
function handleTableList(list) {
    var forList = list;
    var len = forList.length;
    if(len){
        for(var i = 0; i < len; i ++ ){
            forList[i].find('.list-check-span').data('id');
        }
    }
}
//点击全选
function handleCheckAll($this, tableDom) {
    var $this = $this;
    idsArr = [];
    pubIDsArr = [];
    if($this.hasClass('is-checked-all')){
        $this.removeClass('is-checked-all');
        $('.list-check-all').removeClass('is-checked-all');
        tableDom.find('.list-con-main-wrap').find('.list-check-span').removeClass('is-checked-span');
        $('.select-article-sum').text(0);
        idsArr = [];
        pubIDsArr = [];
    } else {
        $this.addClass('is-checked-all');
        $('.list-check-all').addClass('is-checked-all');
        for(var i = 0, len = tableDom.find('.list-con-main-wrap').length; i < len; i++){
            var forDom = tableDom.find('.list-con-main-wrap').eq(i);
            if(forDom.data('valid') == 1){//未失效
                forDom.find('.list-check-span').addClass('is-checked-span');
                var id = forDom.find('.is-checked-span').data('cartid');
                var proID = forDom.find('.is-checked-span').data('proid');
                idsArr.push(id);
                pubIDsArr.push(proID);
            }
        }
        var count = 0;
        $.each($('.valid-dom'), function (i, e) {
            var priceVal = parseFloat($(e).find('ul').find('li.goods-price-wrap').find('.art-price').val());
            count += priceVal;
        })
        
        var formatGetSum = commonFun.formatNumber(count, 2, '￥');
        $('.select-article-sum').text(formatGetSum);
    }
    $('.select-num').text(idsArr.length);
    removeDisabledClass(idsArr.length);
}
//单选全选金额计算
function calArticleSumFun(dom) {
    var count = 0;
    $.each(dom, function (i, e) {
        var val = $(e).find('.art-price').val();
        count += parseFloat(val);
    })
    return count;
}

/*
*
*选购车--创作者
*
*/ 
//创作者--发布需求
$('.cart-publish-btn').off('click').on('click', function () {
    var $this = $(this);
    if(cartTabWrapDom.find('.active').data('id') == 0){//创作者
        var type = tAuthor, noteTxt = '创作者';
    } else if(cartTabWrapDom.find('.active').data('id') == 2){//账号
        var type = tDistri, noteTxt = '账号';
    }
    if(idsArr.length == 0){//未选中创作者--发布需求弹框
        var popTxtNote =  '您没有选中'+noteTxt+'，确定要发布需求吗？', btnTxt = '';
        publishCartPopInfo(type, popTxtNote, btnTxt)
    } else {//选中创作者，是否失效弹框
        //判断创作者是否失效

        //选中的创作者全部失效

        //选中的创作者部分失效

        // popValidWrapDom.show();
        //账号：accountIDs  shoppingCartId  创作者： authorIDs
        localStorage.setItem('shoppingCartId', idsArr.join(','));
        localStorage.setItem('isPubIDs', 1);
        window.location.href = domain + '/demandHall/demand_publish.html';
        
    }
})
//点击弹框中的继续发布--未选择创作者
function continuePublishNoData(type) {
    if(type == tAuthor){
        localStorage.setItem('isPubIDs', 0);
        window.location.href = domain + '/demandHall/demand_publish.html';
    } else if(type == tDistri){
        localStorage.setItem('isPubIDs', 0);
        window.location.href = domain + '/demandHall/demand_publish.html';
    }
    $('.pop-wrap').hide();
}
//发布需求弹框
function publishCartPopInfo(type, noteTxt, btnTxt) { 
    console.log(type,'==type')
    popNoteWrapDom.find('.pop-main').html(noteTxt);
    popNoteWrapDom.find('.pop-btn-blue').data('type', type);
    popNoteWrapDom.show();
}
/*
*
*选购车--作品
*
*/ 
//已发布，未发布tab切换
$('#js-cart-article-tab-wrap li').off('click').on('click', function () {
    var $this = $(this),
    dataID = $this.data('id');
    idsArr = [];
    pubIDsArr = [];
    $this.addClass('active').siblings().removeClass('active');
    getCartListData(tArticle, dataID);
    removeDisabledClass(idsArr.length);
    getCartDataNums('cart');
})
/*
*
*选购车--账号
*
*/ 
//账号分类切换
$('#js-cart-dis-tab-wrap li').off('click').on('click', function () {
    var $this = $(this),
    dataID = $this.data('id');
    idsArr = [];
    pubIDsArr = [];
    $this.addClass('active').siblings().removeClass('active');
    getCartListData(tDistri, dataID);
    removeDisabledClass(idsArr.length);
})

//删除选中数据
function delSelectedListData(type) {
    if(idsArr.length > 0){
        if(type == tAuthor){
            var noteTxt = ' 创作者';
        } else if(type == tArticle){
            var noteTxt = ' 作品';
        } else if(type == tDistri){
            var noteTxt = ' 账号';
        }
        popClearWrapDom.find('.pop-main').html('<span class="pop-note-icon">!</span> 确定要清空 '+cartValidArrLen + ' 个失效' + noteTxt + '吗？');
        popClearWrapDom.find('.pop-btn-red').data('id', id).data('type', type);
        popClearWrapDom.show();
    } else {
        layer.msg('请选择需要删除的数据', {time: 1500});
    }
}


//导出
function exportCartDataFun(type, expData) {
    
}