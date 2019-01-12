var orderConfirmBtnDom = $('#js-order-confirm-btn'),
    orderBackBtnDom = $('#js-order-back-btn');
//返回上一页
$('#js-back-btn').on('click', function () {
    console.log(window.history,window.history.length,'--window.history');
    if(window.history.length > 1){
        window.history.go(-1);
    } else {
        window.location.href = '/contentBank/original_work.html';
    }
})

var getUrlParams = commonFun.getUrlParam(),
    postData = {};
    if(getUrlParams){
        if(getUrlParams.type){//type=1 流水单号、type=2立即下单、type=3购物车
            if(getUrlParams.type == 1 || getUrlParams.type == 4){
                postData.type = 1;
            } else {
                postData.type = getUrlParams.type;
            }
        }
        if(getUrlParams.orderSns){
            var ids = getUrlParams.orderSns;
            if(ids.indexOf(',') != -1){
                postData.orderSns = ids.split(',');
            } else {
                postData.orderSns = ids;
            }
            postData.orderSns = getUrlParams.orderSns
            
        }
        if(getUrlParams.yrProductionId){
            postData.yrProductionId = getUrlParams.yrProductionId;
        }
        if(getUrlParams.shoppingCarts){
            postData.shoppingCarts = getUrlParams.shoppingCarts;
        }

    }
new AjaxRequest({
    type: "post",
    url: domain+'/order/orderInfoBuyer/sureOrder',
    param: postData,
    isShowLoading: true,
    callBack: function(res){
        $('#js-order-list-wrap').html('');
        if(res.status == 1){
            var sucData = res.data, sucDataList = sucData.orderSureDetail, html = '';
            $('#js-order-sum').text(commonFun.formatNumber((sucData.totalOrderPrice), 2, '￥'));
            $('#js-tax-sum').text(commonFun.formatNumber((sucData.totalTaxPrice), 2, '￥'));
            $('#js-pay-sum').text(commonFun.formatNumber((sucData.receiptMoney), 2, '￥'));
            if(sucDataList.length){
                for(var i = 0, len = sucDataList.length; i < len; i++){
                    var sucFor = sucDataList[i], index = parseFloat(i) + 1, detailUrl = domain+"/contentBank/article_detail_"+sucFor.id+".html";
                    sucFor.id ? activeClass = "color-blue" : activeClass = '';
                    html += '<tr>'
                            +'<td><div class="cell">'+index+'</div></td>'
                            +'<td><div class="cell">'+sucFor.orderTypeName+'</div></td>'
                            +'<td><div class="cell cell-order-wrap"><a class="or-article-detail" href="'+detailUrl+'" target="_blank">'+sucFor.title+'</a></div></td>'
                            +'<td><div class="cell">'+commonFun.formatNumber((sucFor.orderPrice), 2, '￥')+'</div></td>'
                            +'<td><div class="cell">'+commonFun.formatNumber((sucFor.taxPrice), 2, '￥')+'</div></td>'
                            +'<td><div class="cell c-yellow">'+commonFun.formatNumber((sucFor.totalPrice), 2, '￥')+'</div></td>'
                        +'</tr>'; 
                }
                $('#js-order-list-wrap').append(html);
            } else {
                layer.msg(res.msg, {time: 1500});
            }
        }
    }
})

//提交订单
orderConfirmBtnDom.on('click', function () {
    var orderType = '';//type=1 流水单号、type=2立即下单、type=3购物车
    orderConfirmBtnDom.addClass('btn-no-border-disabled');
    getUrlParams && getUrlParams.type ? orderType = getUrlParams.type : orderType = '';
    if(orderType == 2){//立即购买
        var postUrl = newDomain + '/order/orderInfoBuyer/buyNow',
            oData = {
                recId: getUrlParams.yrProductionId
            };
    } else if(orderType == 3){//选购车购买多个     多个作品，生成多个订单号
        var postUrl = newDomain + '/order/orderInfoBuyer/placeTheProductOrder',
            oData = {
                shoppingCartIds: getUrlParams.shoppingCarts
            };
    } else if(orderType == 1){//需求订单-再次购买（单个）
        var postUrl = newDomain + '/order/orderInfoBuyer/rePlaceTheProductOrder',
            oData = {
                orderSn: getUrlParams.orderSns
            };
    } else if(orderType == 4){//需求-去下单（多个）    多个报名号，生成多个订单号
        var postUrl = newDomain + '/order/orderInfoBuyer/saveProductOrder',
            oData = {
                orderInfoSn: getUrlParams.orderSns
            };
    }
    commonFun.commonAjax(postUrl, 'post', oData, function(res){
        if(res.status == 1){
            var sucData = res.data;
            if(sucData && sucData.orderSn){//单个
                window.location.href = domain + '/pay/unifiedOrder.html?orderSns='+sucData.orderSn;
            } else {//多个
                if(sucData.length){
                    var idsStr = '';
                    for(var i = 0, len = sucData.length; i < len; i ++){
                        var forIDs = sucData[i];
                        idsStr += 'orderSns='+forIDs.orderSn+'&'
                    }
                    idsStr = idsStr.substring(0, idsStr.length - 1);
                    window.location.href = domain + '/pay/unifiedOrder.html?'+idsStr;
                }
            }

        } else if(res.status == 0){//作品未上架或者已售罄
            layer.msg(res.msg, {time: 1500});
            orderBackBtnDom.show();
        } else {
            // orderConfirmBtnDom.removeClass('btn-no-border-disabled');
        }
    })
})