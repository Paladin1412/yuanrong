$(document).ready(function(){
    var tabIpTradeWrapDom = $('#js-tab-ip-trade-wrap');//IP交易tab
    var tabConAgencyWrapDom = $('#js-tab-agency-con-wrap');//代理权交易
    var tabConAdsWrapDom = $('#js-tab-ads-wrap');//广告交易
    var paginationWrapDom = $('.pagination-wrap');//分页
    var listBotNoteDom = $('#js-list-bot-note');//提示
    var tabAdsSortWrapDom = $('#js-tab-ads-sort-wrap');//广告交易分类
    var tabSortWrapDom = $('.ip-trade-sort-con-wrap');//广告交易资源分类
    var platformWrapDom = $('#js-platform-wrap');//平台
    var platformLiDom = $('li', platformWrapDom);//平台li
    var categoryWrapDom = $('#js-category-wrap');//行业分类
    var categoryLiDom = $('li', categoryWrapDom);//行业分类li
    var fansNumWrapDom = $('#js-fans-num-wrap');//粉丝数
    var fansNumLiDom = $('li', fansNumWrapDom);//粉丝数li
    var quotationWrapDom = $('#js-quotation-wrap');//参考报价
    var quotationLiDom = $('li', quotationWrapDom);//参考报价li
    var fansReferenceWrapDom = $('#js-fans-reference-wrap');//参考粉丝数
    var fansReferenceLiDom = $('li', fansReferenceWrapDom);//参考粉丝数li
    var listShortVideoWrapDom = $('#js-list-short-video-wrap');//短视频wrap
    var listWechatWrapDom = $('#js-list-wechat-wrap');//微信wrap
    var listWeiboWrapDom = $('#js-list-weibo-wrap');//微博wrap
    var categoryMoreBtnDom = $('#js-category-more-btn');//行业分类更多
    var listSvItemConWrapDom = $('#js-list-sv-item-con-wrap');//短视频列表wrap
    var listWcItemConWrapDom = $('#js-list-wc-item-con-wrap');//微信列表wrap
    var listWbItemConWrapDom = $('#js-list-wb-item-con-wrap');//微博列表wrap
    var listSvTitWrapDom = $('#js-list-sv-tit-wrap');//短视频表格tit wrap
    var ipTradeSortWrapDom = $('#js-ip-trade-sort-wrap');//子分类wrap
    var svNum = $('#js-sv-num');//短视频数量
    var wcNum = $('#js-wc-num');//微信数量
    var wbNum = $('#js-wb-num');//微博数量
    var tenThousandNum = 10000;//列表展示数字基数
    var hundredThousandNum = 100000;//列表数字大于十万
    var listNum = 0;//列表数量
    var listNoteNum = $('#js-list-note-num');//列表数量提示：搜索20条，无搜索60条
    var listNoDataWrapDom = $('#js-no-data-wrap');//暂无数据
    var inCartClass = 'btn-border-disabled';
    var searchInputDom = $('#js-search-text');
    //代理权交易/广告交易切换
    $('li', tabIpTradeWrapDom).eq(2).addClass('active').siblings().removeClass('active');
    $('#js-tab-ip-trade-wrap').css('margin-bottom', '0');
    getDataByParams();
function getDataByParams() {
    var getUrlParams = commonFun.getUrlParam();
    if(getUrlParams){
        if(getUrlParams.keyword){
            $('#js-tab-wx-a').attr('href', domain + '/ipTrade/ip_trade_distribution.html?adType=0&keyword='+getUrlParams.keyword);
            $('#js-tab-wb-a').attr('href', domain + '/ipTrade/ip_trade_distribution.html?adType=1&keyword='+getUrlParams.keyword);
            $('#js-tab-sv-a').attr('href', domain + '/ipTrade/ip_trade_distribution.html?adType=2&keyword='+getUrlParams.keyword);
        }
        if(getUrlParams.adType){
            var type =getUrlParams.adType;
            var pUrlPlatform = '/shortVideoPlatformInfo/allInfo';
            var postData = {};
            postData.cp = '1';
            postData.rows = '20';
            postData.search = $.trim(searchInputDom.val());
            postData = commonFun.delEmptyData(postData);
            //行业分类
            categoryMoreBtnDom.removeClass('category-more-btn-active');
            categoryMoreBtnDom.find('img').attr('src', domain + '/images/commonFragments/arr_small_down.png');
            categoryWrapDom.css('height', '30px');
            //排行
            clearSortActive();
            $('li',tabAdsSortWrapDom).eq(type).addClass('active').siblings().removeClass('active');
            $('li', categoryWrapDom).eq(0).addClass('active');
            $('li', fansNumWrapDom).eq(0).addClass('active');
            $('li', quotationWrapDom).eq(0).addClass('active');
            if(getUrlParams.adType == 2){//短视频
                var pUrlCategory = '/shortVideoCategoryInfo/getShortVideooCategory';
                listShortVideoWrapDom.show();//短视频wrap
                listWechatWrapDom.hide();//微信wrap
                listWeiboWrapDom.hide();//微博wrap
                $('li', platformWrapDom).eq(0).addClass('active');
                getCategoryData(pUrlCategory, '', categoryWrapDom, 'category', 'secShortVideo', postData);//获取行业分类信息
                categoryWrapDom.parent('.ip-trade-sort-con-wrap').show();
                getCategoryData(pUrlPlatform, '', platformWrapDom, 'platform');//获取平台信息
                platformWrapDom.parent('.ip-trade-sort-con-wrap').show();
                getNumbersData(fansNumWrapDom, 'fansNums');//获取粉丝数信息
                fansNumWrapDom.parent('.ip-trade-sort-con-wrap').show();
                getNumbersData(quotationWrapDom, 'quotation');//获取参考报价数信息
                quotationWrapDom.parent('.ip-trade-sort-con-wrap').show();
            } else if(getUrlParams.adType == 1){//微博
                var pUrlCategory = '/shortVideoCategoryInfo/getWeiboyiWeiBoCategory';
                listShortVideoWrapDom.hide();//短视频wrap
                listWechatWrapDom.hide();//微信wrap
                listWeiboWrapDom.show();//微博wrap
                fansNumWrapDom.parent('.ip-trade-sort-con-wrap').hide();//粉丝数
                platformWrapDom.parent('.ip-trade-sort-con-wrap').hide();//平台信息
                getCategoryData(pUrlCategory, '', categoryWrapDom, 'categoryWb', 'secWeibo', postData);//获取行业分类信息
                categoryWrapDom.parent('.ip-trade-sort-con-wrap').show();
                getNumbersData(quotationWrapDom, 'quotation');//获取参考报价数信息
                quotationWrapDom.parent('.ip-trade-sort-con-wrap').show();
                getNumbersData(fansNumWrapDom, 'fansNums');//获取粉丝数信息
                fansNumWrapDom.parent('.ip-trade-sort-con-wrap').show();
            } else {//默认微信
                getWcListFun();
            }
        } else {
            getWcListFun();
        }
    }
}
    //获取微信列表
    function getWcListFun(){
        var type = 'secWeChat';
        var postData = {};
        var pUrlCategory = '/shortVideoCategoryInfo/getWeiboyiWeiXinCategory';
        postData.cp = '1';
        postData.rows = '20';
        postData.search = $.trim(searchInputDom.val());
        postData = commonFun.delEmptyData(postData);
        //默认显示微信
        $('li',tabAdsSortWrapDom).eq(0).addClass('active').siblings().removeClass('active');
        //行业分类
        categoryMoreBtnDom.removeClass('category-more-btn-active');
        categoryMoreBtnDom.find('img').attr('src', domain + '/images/commonFragments/arr_small_down.png');
        categoryWrapDom.css('height', '30px');
        //排行
        clearSortActive();
        $('li', categoryWrapDom).eq(0).addClass('active');
        $('li', fansNumWrapDom).eq(0).addClass('active');
        $('li', quotationWrapDom).eq(0).addClass('active');
        listWechatWrapDom.show();//微信wrap
        listShortVideoWrapDom.hide();//短视频wrap
        listWeiboWrapDom.hide();//微博wrap
        platformWrapDom.parent('.ip-trade-sort-con-wrap').hide();//平台信息
        getCategoryData(pUrlCategory, '', categoryWrapDom, 'category', 'secWeChat', postData);//获取行业分类信息
        categoryWrapDom.parent('.ip-trade-sort-con-wrap').show();
        getNumbersData(fansNumWrapDom, 'fansNums');//获取粉丝数信息
        fansNumWrapDom.parent('.ip-trade-sort-con-wrap').show();
        getNumbersData(quotationWrapDom, 'quotation');//获取参考报价数信息
        quotationWrapDom.parent('.ip-trade-sort-con-wrap').show();
    }
    //列表数字显示处理
    function listNumberFun(dataNumArr, type){
        if(dataNumArr != [] && dataNumArr.length > 0){
            if(type == 'read'){//阅读数
                for(var m = 0, mLen = dataNumArr.length; m < mLen; m++){//hundredThousandNum
                    if(dataNumArr[m] < tenThousandNum){//小于1万
                        dataNumArr[m] =  commonFun.formatNumber((dataNumArr[m]), 0, '');
                    } else if(dataNumArr[m] > tenThousandNum && dataNumArr[m] < hundredThousandNum){//da于1万且小于10万
                        dataNumArr[m] = commonFun.formatNumber((dataNumArr[m] / tenThousandNum), 1, '') + '万';
                    } else if(dataNumArr[m] > hundredThousandNum){//阅读数大于10万
                        dataNumArr[m] = '10万+';
                    }
                }
            } else {
                for(var m = 0, mLen = dataNumArr.length; m < mLen; m++){//hundredThousandNum
                    if(dataNumArr[m] < tenThousandNum){//小于1万
                        dataNumArr[m] =  commonFun.formatNumber((dataNumArr[m]), 0, '');
                    } else if(dataNumArr[m] >= tenThousandNum){//da于1万
                        dataNumArr[m] = commonFun.formatNumber((dataNumArr[m] / tenThousandNum), 1, '') + '万';
                    }
                }
            }
            return dataNumArr;
        }
    }
    //获取短视频
    function getShortVideoData(pData){
        var postUrl = newDomain + '/ip/getIPLists';
        var pData = commonFun.delEmptyData(pData);
        listNum = 0;
        listSvItemConWrapDom.html('');
        
        var getLoginFun = function (isLogin) {
            if(isLogin == 'Y'){
                var isLogin = 'Y';
                var hideItem = '';
                var tableReplace = '';
                listBotNoteDom.hide();//底部文字提示
            } else {
                var isLogin = 'N';
                var hideItem = 'hide';
                var curUrl = window.location.href;
                var tableReplace = '<a href="'+centerUrl+'login/?callback='+curUrl+'" target="_blank" style="cursor: pointer; font-size: 12px;" class="color-blue">'+loginAvailable+'</a>';
                listBotNoteDom.show();//底部文字提示
            }
            commonFun.commonAjax(postUrl, 'post', pData, function(res){
                if(res.status == 1){
                    var sucData = res.data, sucDataList = sucData.data, listTotle = sucData.total, shortVideoList = '', platformDisplay = '',
                        fansNum = '',//粉丝数
                        playNum = '',//播放次数 
                        likeNum = '',//点赞数
                        commentNum = '',//评论数
                        areaName = '',//地区
                        gender = '',//性别
                        indexNum = '';//指数
                    svNum.text(listTotle); 
                    listNum = listTotle;
    
                    //短视频列表
                    if(sucDataList && sucDataList.length){
                        sucDataList.forEach(function(item){
                            item.newPrice = formatAccountPrice(item.platFormPriceArray)
                        });
                        console.log(sucDataList)
                        for(var i = 0, listLen = sucDataList.length; i < listLen; i++){
                            var forData = sucDataList[i],
                                subForData = forData.newPrice,////平台价格小循环
                                subPlatformNamePriceList = '',//平台列表
                                nonePriceSign = 0,//没有平台价格标示
                                platformIcon = '',//平台icon
                                platformName = '',//平台名称
                                platformInfo = '',//平台信息
                                detailUrl = '',//详情url
                                platAreaBorderLt = '',//平台地址分割线
                                addrSign = '<i class="iconfont">&#xe63f;</i>';//地址图标
    
                            if(subForData && subForData.length){
                                var subPlatformInfoList = '';
                                for(var j = 0, jLen = subForData.length; j < jLen; j++){//平台信息
                                    var thirdPosPriList = '';//位置，价格列表循环
                                    if(subForData[j].name == '视频发布' || subForData[j].name == '活动现场直播'){
                                        
                                        if(subForData[j].price && subForData[j].price.length){//平台价格列表
                                            var thirdForData = subForData[j].price; //平台价格
                                            for(var k = 0, kLen = thirdForData.length; k < kLen; k++){//平台价格
                                                if(k < 2){
    
                                                    var signPos = thirdForData[k].indexOf('_'),
                                                        isOriginal = thirdForData[k].charAt(thirdForData[k].length - 1),
                                                        getPrice = thirdForData[k].slice(0, signPos);
                                                    if(isOriginal == 1){
                                                        var isHideY = '';
                                                    } else {
                                                        var isHideY = 'dom-display-none';
                                                    }
                                                    var yHtml = '<span class="account-y-txt-wrap trans-all-2"><span class="account-y trans-all-2 '+isHideY+'"><img class="origin-img" src="'+domain+'/images/commonFragments/origin.png" alt=""></span><span class="account-y-note  trans-all-2"><span class="account-y-note-txt">报价含原创+发布服务</span><i class="account-tri"></i></span></span> '
                                                    if(getPrice == 0){
                                                        var formatterPrice = '--';
                                                    } else {
                                                        formatterPrice = commonFun.formatNumber((getPrice), 2, '￥');
                                                    }
                                                    // console.log(isOriginal)
                                                    thirdPosPriList += '<td>'+formatterPrice+'</td>';
                                                }
                                            }
                                        }
                                        subPlatformInfoList += '<tr><td>'+subForData[j].name+'</td>'+thirdPosPriList+'</tr>';
                                    }
                                    if(!subPlatformInfoList){
                                        subPlatformInfoList += '<tr><td>--</td><td>--</td><td>--</td></tr>';
                                    }
                                    
                                }
                            }
                            if(forData.Gender == 0){//female
                                gender = '<img class="gender-img" src="'+domain+'/images/ipTrade/g_female.png" alt="">';
                            } else if(forData.Gender == 1){
                                gender = '<img class="gender-img" src="'+domain+'/images/ipTrade/g_male.png" alt="">'
                            } else {
                                gender = '';
                            }
                            forData.platformIcoUrl && forData.platformIcoUrl != '' ? platformIcon = '<img class="platform-img" src="'+forData.platformIcoUrl+'" alt="">' : platformIcon = '';
                            forData.platform && forData.platform != '' ? platformName = forData.platform : platformName = '';
                            platformIcon != '' || platformName != '' ? platformInfo = '<span class="platform-img-tit-wrap fixedClear">'+platformIcon + platformName+'</span>' : platformInfo = '<span class="platform-img-tit-wrap fixedClear"></span>';
                            forData.DetailUrl ? detailUrl = 'target="_blank" href="'+forData.DetailUrl+'"' : detailUrl = 'href="javascript:;"';
                            forData.Followers_count && forData.Followers_count != '' ? fansNum = forData.Followers_count : fansNum = '0';
                            forData.Average_play_num && forData.Average_play_num != '' ? playNum = forData.Average_play_num : playNum = '0';
                            forData.Average_like_num && forData.Average_like_num != '' ? likeNum = forData.Average_like_num : likeNum = '0';
                            forData.Average_posts_num && forData.Average_posts_num != '' ? commentNum = forData.Average_posts_num : commentNum = '0';
                            forData.IndexValue && forData.IndexValue != '' ? indexNum = forData.IndexValue : indexNum = '0';
                            if(platformName !== '' && forData.Area_name && forData.Area_name != ''){
                                platAreaBorderLt = 'plat-area-border';
                            } else {
                                platAreaBorderLt = '';
                            }
                            if(forData.isAddCart == 1){
                                var isInCartFlag = inCartClass;
                                var isInCartTxt = '已加入选购车';
                            } else {
                                var isInCartFlag = '';
                                var isInCartTxt = '加入选购车';
                            }
                            forData.Area_name && forData.Area_name != '' ? areaName = '<span class="" style="color: #ccc;">|</span><span class="platform-area-tit-wrap fixedClear '+platAreaBorderLt+'">'+ addrSign + forData.Area_name+'</span>' : areaName = '<span class="platform-area-tit-wrap fixedClear"></span>';
                            var dataNumArr = [fansNum, playNum, likeNum, commentNum];//粉丝数，播放次数，点赞数，评论数
                            var funDataNumArr = listNumberFun(dataNumArr);
                            fansNum = funDataNumArr[0];
                            playNum = funDataNumArr[1];
                            likeNum = funDataNumArr[2];
                            commentNum = funDataNumArr[3];
                            shortVideoList += '<div class="list-item-wrap list-sv-item-wrap fixedClear" data-id="'+forData.accountOnlyId+'">'
                                +'<div class="list-item list-item-sv-tit fixedClear"><a class="fixedClear" style="display: block;" '+detailUrl+'>'
                                    +'<div class="list-item-img-wrap"><img class="avatar-img" src="'+forData.Account_avatar_url+'" alt=""></div>'
                                    +'<div class="list-item-tit-wrap list-item-sv-tit-wrap fixedClear"><div class="list-item-wb-an-tit-wrap fixedClear"><div class="list-item-wb-an-tit"><p title="'+forData.Pack_name+'">'+forData.Pack_name+'</p></div>'+gender+'</div><div class="plat-area-con-wrap fixedClear">'+platformInfo+areaName+'</div></div>'
                                +'</a></div>'
                                +'<div class="list-item list-item-sv-3" ><p>'+fansNum+'</p></div>'
                                +'<div class="list-item list-item-sv" ><p>'+playNum+'</p></div>'
                                +'<div class="list-item list-item-sv-3" ><p>'+likeNum+'</p></div>'
                                +'<div class="list-item list-item-sv-3" ><p>'+commentNum+'</p></div>'
                                +'<div class="list-item zs-tit"><p>'+indexNum+'</p></div>'
                                // +'<div class="list-item list-item-double-wrap ">'+subPlatformInfoList+'</div>'
                                +'<div class="list-item list-item-double-wrap "><table class="dis-table-wrap '+hideItem+'"><thead><th width="100">价格项</th><th width="120">账号直供价</th><th width="120">微播易</th></thead><tbody>'+subPlatformInfoList+'</tbody></table>'+tableReplace+'</div>'
                                // +'<div class="list-item list-item-sv"><p>'+forData.YuanrongIndex+'</p></div>'
                                +'<div class="list-item list-item-ope"><p class="list-item-btn list-item-btn-add '+isInCartFlag+'" data-id="'+forData.accountOnlyId+'">'+isInCartTxt+'</p><p class="list-item-btn list-item-btn-yy qq">立即推广</p></div>'
                            +'</div>'; 
                        }
                        listSvItemConWrapDom.append(shortVideoList);
                        paginationWrapDom.show();//分页
                        listNoDataWrapDom.hide();//暂无数据
                        paginationFun(pData, 'secShortVideo');
    
                        // if(pData.search && listTotle > 0){
                        //     listBotNoteDom.show();//底部文字提示
                        //     listNoteNum.text('20');
                        // } else if(listTotle > 0){
                        //     listBotNoteDom.show();//底部文字提示
                        //     listNoteNum.text('60');
                        // }
    
                        //图片展示处理
                        // $('.avatar-img').error(function(){
                        //     $('.avatar-img').attr('src', domain + '/images/avatar.png');
                        // })
                        
                        //点击平台名称展示参考价和位置
                        handleSource();
                        
                        var item = $('.list-sv-item-wrap');
                        listAddCartBtnFun(item);
                        spreadBtnFun(item);
                        // qdService('.list-item-btn-yy');
                    } else {
                        $('.pagination-note-wrap').hide();
                        listNum = 0;
                        svNum.text(0);
                        listSvItemConWrapDom.html('');
                        listNoDataWrapDom.show();//暂无数据
                        paginationWrapDom.hide();//分页
                        listBotNoteDom.hide();//底部文字提示
                    }
                } else {
                    listNum = 0;
                    svNum.text(0);
                    $('.pagination-note-wrap').hide();
                    listSvItemConWrapDom.html('');
                    listNoDataWrapDom.show();//暂无数据
                    paginationWrapDom.hide();//分页
                    listBotNoteDom.hide();//底部文字提示
                }
            })
                
        };
        getUserLoginInfo(getLoginFun);
    }   
    //获取微信列表    
    function getWechatData(pData){
        var postUrl = newDomain + '/ip/getWeixinUserList',
            pData = commonFun.delEmptyData(pData);
        listNum = 0;
        listWcItemConWrapDom.html('');
        var getLoginFun = function (isLogin) {
            if(isLogin == 'Y'){
                var isLogin = 'Y';
                var hideItem = '';
                var tableReplace = '';
                listBotNoteDom.hide();//底部文字提示
            } else {
                var isLogin = 'N';
                var hideItem = 'hide';
                var curUrl = window.location.href;
                var tableReplace = '<a href="'+centerUrl+'login/?callback='+curUrl+'" target="_blank" style="cursor: pointer; font-size: 12px;" class="color-blue">'+loginAvailable+'</a>';
                listBotNoteDom.show();//底部文字提示
            }
            
            commonFun.commonAjax(postUrl, 'post', pData, function(res){
                if(res.status == 1){
                    var sucData = res.data,
                        listTotle = sucData.total,
                        industryType = '',
                        qrCodeDisplay = '',
                        wechatList = '';
                    wcNum.text(listTotle); 
                    listNum = listTotle;
                    if(sucData.data && sucData.data.length){
                        var sucDataList = sucData.data;
                        sucDataList.forEach(function(item){
                            item.newPrice = formatAccountPrice(item.platformInfos)
                        });
                        
                        // console.log(sucDataList)
                        for(var i = 0, listLen = sucDataList.length; i < listLen; i++){
                            var forData = sucDataList[i], readNumFormat = 0, fansNumFormat = 0, likeNumFormat = 0;////阅读数 粉丝数
                            if(forData.newPrice && forData.newPrice.length){//平台名称，价格名称，金额
                                
                                var subForData = forData.newPrice, subPlatformInfoList = '';//价格为0个数
                                for(var j = 0, jLen = subForData.length; j < jLen; j++){//平台信息
                                    // console.log(subForData[j])
                                    if(subForData[j].price && subForData[j].price.length){//平台价格列表
                                        var thirdForData = subForData[j].price; //平台价格
                                        var thirdPosPriList = '';
                                        if(subForData[j].price.length > 1){
                                            var triImgHtml = '<img class="list-platform-tri" src="'+domain+'/images/commonFragments/tri_arr_down.png" alt="">';
                                        } else {
                                            var triImgHtml = '';
                                        }
                                        for(var k = 0, kLen = thirdForData.length; k < kLen; k++){//平台价格
                                            // console.log(thirdForData[k])
                                            var signPos = thirdForData[k].indexOf('_'),
                                                isOriginal = thirdForData[k].charAt(thirdForData[k].length - 1),
                                                getPrice = thirdForData[k].slice(0, signPos);
                                            if(isOriginal == 1){
                                                var isHideY = '';
                                            } else {
                                                var isHideY = 'dom-display-none';
                                            }
                                            var yHtml = '<span class="account-y-txt-wrap trans-all-2"><span class="account-y trans-all-2 '+isHideY+'"><img class="origin-img" src="'+domain+'/images/commonFragments/origin.png" alt=""></span><span class="account-y-note  trans-all-2"><span class="account-y-note-txt">报价含原创+发布服务</span><i class="account-tri"></i></span></span> '
                                            if(getPrice == 0){
                                                var formatterPrice = '--';
                                            } else {
                                                formatterPrice = commonFun.formatNumber((getPrice), 2, '￥');
                                            }
                                            // console.log(isOriginal)
                                            thirdPosPriList += '<td>'+formatterPrice+'</td>';
                                                    
                                        }
                                    }
                                    if(subForData[j].name == '头条发布' || subForData[j].name == '次条发布'){

                                        subPlatformInfoList += '<tr><td>'+subForData[j].name+'</td>'+thirdPosPriList+'</tr>';
                                    }
                                    
                                    if(!subPlatformInfoList){
                                        subPlatformInfoList += '<tr><td>--</td><td>--</td><td>--</td><td>--</td></tr>';
                                    }
                                }
                            }
                            forData.QRCode && forData.QRCode != 'Null' ? qrCodeDisplay = 'inline-block' : qrCodeDisplay = 'none';
                            forData.Type && forData.Type != '' ? industryType = forData.Type : industryType = '--';
                            forData.Fans && forData.Fans != '' ? fansNumFormat = forData.Fans : fansNumFormat = 0;
                            forData.AvgReadNum && forData.AvgReadNum != '' ? readNumFormat = forData.AvgReadNum : readNumFormat = 0;
                            forData.AvgRetweetLikeNum && forData.AvgRetweetLikeNum != '' ? likeNumFormat = forData.AvgRetweetLikeNum : likeNumFormat = 0;
                            
                            var dataNumArr = [fansNumFormat, readNumFormat, likeNumFormat];//粉丝数，平均阅读，平均点赞数
                            var funDataNumArr = listNumberFun(dataNumArr);
                            fansNumFormat = funDataNumArr[0];
                            readNumFormat = funDataNumArr[1];
                            likeNumFormat = funDataNumArr[2];

                            if(forData.isAddCart == 1){
                                var isInCartFlag = inCartClass;
                                var isInCartTxt = '已加入选购车';
                            } else {
                                var isInCartFlag = '';
                                var isInCartTxt = '加入选购车';
                            }
                            var thirdPosPriList = '';//位置，价格列表循环
                            
                            wechatList += '<div class="list-item-wrap list-wc-item-wrap fixedClear" data-id="'+forData.accountOnlyId+'">'
                                +'<div class="list-item list-item-wx-tit list-item-qrcon-wrap fixedClear">'
                                    +'<div class="list-item-img-wrap"><img class="avatar-img" src="'+forData.HeadImgLocal+'" alt=""></div>'
                                    +'<div class="list-item-tit-wrap"><p style="font-size: 14px;" title="'+forData.WeiXinAccount+'">'+forData.WeiXinAccount+'</p><p title="'+forData.WeiXinAccount_Id+'">'+forData.WeiXinAccount_Id+'</p></div>'
                                    +'<div class="list-item-qr-code-wrap"><img src="'+forData.QRCode+'" alt=""></div>'
                                    +'<img class="list-item-qr-sign-img" style="display:'+qrCodeDisplay+';" src="'+domain+'/images/ipTrade/qr_sign.png" alt="">'
                                +'</div>'
                                +'<div class="list-item list-item-wx list-item-wx-s"><p>'+industryType+'</p></div>'
                                +'<div class="list-item list-item-wx"><p>'+fansNumFormat+'</p></div>'
                                +'<div class="list-item list-item-wx"><p>'+readNumFormat+'</p></div>'
                                +'<div class="list-item list-item-wx"><p>'+likeNumFormat+'</p></div>'
                                +'<div class="list-item list-item-wx"><p>'+forData.YuanrongIndex+'</p></div>'
                                // +'<div class="list-item list-item-wx wc-list-sec-wrap">'+subPlatformInfoList+'</div>'
                                // +'<div class="list-item list-item-wx wc-list-sec-wrap"><table class="dis-table-wrap"><thead><th>价格项</th><th>账号直供价</th><th>微播易</th><th>新榜</th></thead><tbody>'+thirdPosPriList+'</tbody></table></div>'
                                +'<div class="list-item list-item-wx wc-list-sec-wrap"><table class="dis-table-wrap '+hideItem+'"><thead><th width="70">价格项</th><th width="120">账号直供价</th><th width="120">微播易</th><th width="120">新榜</th></thead><tbody>'+subPlatformInfoList+'</tbody></table>'+tableReplace+'</div>'
                                +'<div class="list-item list-item-wx list-item-wx-s2"><p class="list-item-btn list-item-btn-add '+isInCartFlag+'" data-id="'+forData.accountOnlyId+'">'+isInCartTxt+'</p><p class="list-item-btn list-item-btn-yy qq">立即推广</p></div>'
                            +'</div>'; 
                        }
                        listWcItemConWrapDom.append(wechatList);
                        paginationWrapDom.show();//分页  
                        listNoDataWrapDom.hide();//暂无数据
                        paginationFun(pData, 'secWeChat');

                        // if(pData.search && listTotle > 0){
                        //     listBotNoteDom.show();//底部文字提示
                        //     listNoteNum.text('20');
                        // } else if(listTotle > 0){
                        //     listBotNoteDom.show();//底部文字提示
                        //     listNoteNum.text('60');
                        // }

                        //图片展示处理
                        // $('.avatar-img').error(function(){
                        //     $('.avatar-img').attr('src', domain + '/images/avatar.png');
                        // })
                        //展示二维码
                        $('.list-item-qr-sign-img').mouseenter(function(){
                            var $this = $(this),
                                sibDom = $this.siblings('.list-item-qr-code-wrap'),
                                imgSrc = sibDom.find('img').attr('src');
                            imgSrc != '' && imgSrc != 'Null' ? sibDom.show() : sibDom.hide();
                        }).mouseleave(function(){
                            var $this = $(this),
                                sibDom = $this.siblings('.list-item-qr-code-wrap'),
                                imgSrc = sibDom.find('img').attr('src');
                            imgSrc != '' && imgSrc != 'Null' ? sibDom.hide() : sibDom.hide();
                        })
                        //点击平台名称展示参考价和位置
                        handleSource();
                        // onclick="disAddCartFun($(this), '+forData.accountOnlyId+')"
                        var item = $('.list-wc-item-wrap');
                        listAddCartBtnFun(item);
                        spreadBtnFun(item);
                        // qdService('.list-item-btn-yy');
                    } else {
                        
                        $('.pagination-note-wrap').hide();
                        listNum = 0;
                        wcNum.text(0);
                        listWcItemConWrapDom.html('');
                        listNoDataWrapDom.show();//暂无数据
                        paginationWrapDom.hide();
                        listBotNoteDom.hide();//底部文字提示
                    }
                } else {
                    $('.pagination-note-wrap').hide();
                    listNum = 0;
                    wcNum.text(0);
                    listWcItemConWrapDom.html('');
                    listNoDataWrapDom.show();//暂无数据
                    paginationWrapDom.hide();
                    listBotNoteDom.hide();//底部文字提示
                }
            })
        }
        
        getUserLoginInfo(getLoginFun);
    }
    //获取微博列表 
    function getWeibotData(pData){
        var postUrl = newDomain + '/ip/getWeiBoUserLists',
            pData = commonFun.delEmptyData(pData);
        listNum = 0;
        listWbItemConWrapDom.html('');
        var getLoginFun = function (isLogin) {
            if(isLogin == 'Y'){
                var isLogin = 'Y';
                var hideItem = '';
                var tableReplace = '';
                listBotNoteDom.hide();//底部文字提示
            } else {
                var isLogin = 'N';
                var hideItem = 'hide';
                var curUrl = window.location.href;
                var tableReplace = '<a href="'+centerUrl+'login/?callback='+curUrl+'" target="_blank" style="cursor: pointer; font-size: 12px;" class="color-blue">'+loginAvailable+'</a>';
                listBotNoteDom.show();//底部文字提示
            }
            commonFun.commonAjax(postUrl, 'post', pData, function(res){
                if(res.status == 1){
                    var sucData = res.data,
                        sucDataList = sucData.data,
                        listTotle = sucData.total,
                        intro = '',//介绍
                        wbName = '',//微博name
                        indusType = '',//行业分类
                        gender = '',//性别
                        weboList = '';  
                    wbNum.text(listTotle); 
                    listNum = listTotle;
                    sucDataList.forEach(function(item){
                        item.newPrice = formatAccountPrice(item.platformInfos)
                    });
                    // console.log(sucDataList)
                    
                    //微博列表
                    if(sucDataList && sucDataList.length){
                        for(var i = 0, listLen = sucDataList.length; i < listLen; i++){
                            var forData = sucDataList[i],
                                fansNumFormat = 0, avgSendNumFormat = 0, avgPostNumFormat = 0, avgLikeNumFormat = 0;//数字展示处理
    
                            if(forData.newPrice && forData.newPrice.length){
                                var subForData = forData.newPrice, subPlatformInfoList = '', detailUrl = '';
                                for(var j = 0, jLen = subForData.length; j < jLen; j++){//平台信息
                                    var thirdPosPriList = '';//位置，价格列表循环
                                    if(subForData[j].name ==  '直发报价' || subForData[j].name == '转发报价'){
                                        
                                        if(subForData[j].price && subForData[j].price.length){//平台价格列表
                                            var thirdForData = subForData[j].price;//平台价格
                                            for(var k = 0, kLen = thirdForData.length; k < kLen; k++){//平台价格
                                                if(k < 2){
                                                    var signPos = thirdForData[k].indexOf('_'),
                                                        isOriginal = thirdForData[k].charAt(thirdForData[k].length - 1),
                                                        getPrice = thirdForData[k].slice(0, signPos);
                                                    if(isOriginal == 1){
                                                        var isHideY = '';
                                                    } else {
                                                        var isHideY = 'dom-display-none';
                                                    }
                                                    var yHtml = '<span class="account-y-txt-wrap trans-all-2"><span class="account-y trans-all-2 '+isHideY+'"><img class="origin-img" src="'+domain+'/images/commonFragments/origin.png" alt=""></span><span class="account-y-note  trans-all-2"><span class="account-y-note-txt">报价含原创+发布服务</span><i class="account-tri"></i></span></span> '
                                                    if(getPrice == 0){
                                                        var formatterPrice = '--';
                                                    } else {
                                                        formatterPrice = commonFun.formatNumber((getPrice), 2, '￥');
                                                    }
                                                    // console.log(isOriginal)
                                                    thirdPosPriList += '<td>'+formatterPrice+'</td>';
                                                }
                                            }
                                        }
                                        subPlatformInfoList += '<tr><td>'+subForData[j].name+'</td>'+thirdPosPriList+'</tr>';
                                        if(!subPlatformInfoList){
                                            subPlatformInfoList += '<tr><td>--</td><td>--</td><td>--</td></tr>';
                                        }
                                    }
                                    
                                }
                            }
                            forData.Introduction && forData.Introduction != '' ? intro = forData.Introduction : intro = '';
                            forData.WeiBoName && forData.WeiBoName != '' ? wbName = forData.WeiBoName : wbName = '--';
                            forData.Type && forData.Type != '' ? indusType = forData.Type : indusType = '--';
                            forData.Fans && forData.Fans != '' ? fansNumFormat = forData.Fans : fansNumFormat = 0;
                            if(forData.Gender == 0){//female
                                gender = '<img class="gender-img" src="'+domain+'/images/ipTrade/g_female.png" alt="">';
                            } else if(forData.Gender == 1){
                                gender = '<img class="gender-img" src="'+domain+'/images/ipTrade/g_male.png" alt="">'
                            } else {
                                gender = '';
                            }
                            forData.AvgRetweetSendNum && forData.AvgRetweetSendNum != '' ? avgSendNumFormat = forData.AvgRetweetSendNum : avgSendNumFormat = 0;
                            forData.AvgRetweetPostsNum && forData.AvgRetweetPostsNum != '' ? avgPostNumFormat = forData.AvgRetweetPostsNum : avgPostNumFormat = 0;
                            forData.AvgRetweetLikeNum && forData.AvgRetweetLikeNum != '' ? avgLikeNumFormat = forData.AvgRetweetLikeNum : avgLikeNumFormat = 0;
                            forData.DetailUrl ? detailUrl = 'target="_blank" href="'+forData.DetailUrl+'"' : detailUrl = 'href="javascript:;"';
                            var dataNumArr = [fansNumFormat, avgSendNumFormat, avgPostNumFormat, avgLikeNumFormat];//粉丝数，平均转发数，平均评论数，平均点赞数
                            var funDataNumArr = listNumberFun(dataNumArr);
                            fansNumFormat = funDataNumArr[0];
                            avgSendNumFormat = funDataNumArr[1];
                            avgPostNumFormat = funDataNumArr[2];
                            avgLikeNumFormat = funDataNumArr[3];
                            if(forData.isAddCart == 1){
                                var isInCartFlag = inCartClass;
                                var isInCartTxt = '已加入选购车';
                            } else {
                                var isInCartFlag = '';
                                var isInCartTxt = '加入选购车';
                            }
                            weboList += '<div class="list-item-wrap list-wb-item-wrap fixedClear" data-id="'+forData.accountOnlyId+'">'
                                +'<div class="list-item list-item-wb-an-wrap list-item-width257 fixedClear">'
                                    +'<a class="fixedClear" style="display: block;" '+detailUrl+'>'
                                    +'<div class="list-item-img-wrap"><img class="avatar-img" src="'+forData.HeadURLLocal+'" alt=""></div>'
                                    +'<div class="list-item-tit-wrap"><div class="list-item-wb-an-tit-wrap fixedClear"><div class="list-item-wb-an-tit"><p title="'+wbName+'">'+wbName+'</p></div>'+gender+'</div></div>'
                                    // <p title="'+intro+'">'+intro+'</p>
                                    +'</a>'
                                +'</div>'
                                +'<div class="list-item list-item-width80"><p>'+indusType+'</p></div>'
                                +'<div class="list-item list-item-width98"><p>'+fansNumFormat+'</p></div>'
                                +'<div class="list-item list-item-width98"><p>'+avgSendNumFormat+'</p></div>'
                                +'<div class="list-item list-item-width98"><p>'+avgPostNumFormat+'</p></div>'
                                +'<div class="list-item list-item-width98"><p>'+avgLikeNumFormat+'</p></div>'
                                +'<div class="list-item list-item-width98"><p>'+forData.YuanrongIndex+'</p></div>'
                                // +'<div class="list-item list-item-width392 list-item-p-i-p-list-wrap fixedClear">'+subPlatformInfoList+'</div>'
                                +'<div class="list-item list-item-width392 list-item-p-i-p-list-wrap fixedClear"><table class="dis-table-wrap '+hideItem+'"><thead><th width="80">价格项</th><th width="116">账号直供价</th><th width="116">微播易</th></thead><tbody>'+subPlatformInfoList+'</tbody></table>'+tableReplace+'</div>'
                                +'<div class="list-item list-item-width108"><p class="list-item-btn list-item-btn-add '+isInCartFlag+'" data-id="'+forData.accountOnlyId+'">'+isInCartTxt+'</p><p class="list-item-btn list-item-btn-yy qq">立即推广</p></div>'
                            +'</div>'; 
                        }
                        listWbItemConWrapDom.append(weboList);
                        paginationWrapDom.show();//分页
                        listNoDataWrapDom.hide();//暂无数据
                        paginationFun(pData, 'secWeibo');
                        //点击平台名称展示参考价和位置
                        handleSource();
                        var item = $('.list-wb-item-wrap');
                        listAddCartBtnFun(item);
                        spreadBtnFun(item);
                        // qdService('.list-item-btn-yy');
                        //图片展示处理
                        // $('.avatar-img').error(function(){
                        //     $('.avatar-img').attr('src', domain + '/images/avatar.png');
                        // })
    
                        // if(pData.search && listTotle > 0){
                        //     listBotNoteDom.show();//底部文字提示
                        //     listNoteNum.text('20');
                        // } else if(listTotle > 0){
                        //     listBotNoteDom.show();//底部文字提示
                        //     listNoteNum.text('60');
                        // }
    
                    } else {
                        
                        $('.pagination-note-wrap').hide();
                        listNum = 0;
                        wbNum.text(0)
                        listWbItemConWrapDom.html('');
                        listNoDataWrapDom.show();//暂无数据
                        paginationWrapDom.hide();//分页
                        listBotNoteDom.hide();//底部文字提示
                    }
                    
                } else {
                    $('.pagination-note-wrap').hide();
                    listNum = 0;
                    wbNum.text(0)
                    listWbItemConWrapDom.html('');
                    listNoDataWrapDom.show();//暂无数据
                    paginationWrapDom.hide();//分页
                    listBotNoteDom.hide();//底部文字提示
                }
            })
        }
        getUserLoginInfo(getLoginFun);
        
    }
    //列表-加入购物车-点击事件
    function listAddCartBtnFun(item) {
        
        item.each(function (i) {
            item.eq(i).find('.list-item-btn-add').off('click').on('click', function (e) {
                console.log(e);
                var $this = $(this);
                var imgSrc = $this.parent().siblings().find('.avatar-img').attr('src');
                var proID = $this.data('id');
                addCartData(tDistri, proID, imgSrc, e);
            })
        })
    }
    //
    //点击立即推广
    function spreadBtnFun(item) {
        item.each(function (i) {
            item.eq(i).find('.list-item-btn-yy').off('click').on('click', function (e) {
                
                var getLoginFun = function (isLogin) {
                    if(isLogin == 'Y'){
                        var postUrl = newDomain + '/cart/shoppingCart_addAccount';
                        var postData = {
                            productId: item.eq(i).data('id')
                        };
                        commonFun.commonAjax(postUrl, 'post', postData, function(res){
                            if(res.status == 1){
                                var shoppingCartID = res.data.shoppingCartId;
                                localStorage.setItem('shoppingCartId', shoppingCartID);
                                localStorage.setItem('isPubIDs', 1);
                                window.location.href = domain + '/demandHall/demand_publish.html';
                            } else {
                                layer.msg(res.msg, {time: 1500});
                            }
                        })
                    }
                }
                getUserLoginInfo(getLoginFun, 'showpop');
                
            })
        })

    }
    //点击报价来源
    function handleSource(){
        
        $('.list-platform-tit-wrap').off('click').on('click', function(){
            var $this = $(this),
                domSib = $this.siblings('.list-item-width-con-pos'),
                posLen = domSib.find('.list-item-width-con-pos-wrap').length;
            if(posLen > 1){
                if($this.hasClass('active')){
                    $this.removeClass('active');
                    $this.find('img').attr('src', domain + '/images/commonFragments/tri_arr_down.png');
                    domSib.css('height', '30px');
                } else {
                    $this.addClass('active');
                    $this.find('img').attr('src', domain + '/images/commonFragments/tri_arr_up.png');
                    domSib.css('height', 'auto');
                }
            }
        })
    }
    //获取分类等子选项数据
    function getCategoryData(pUrl,pData,pDom,pType, listType, pdatalist){//行业分类分为3个接口
        var postUrl = newDomain + pUrl,
            pData = commonFun.delEmptyData(pData);
        if(pType == 'platform'){
            var postUrl = domain + pUrl
        }
        pDom.html('<li data-id="" class="active">不限</li>');
        commonFun.commonAjax(postUrl, 'get', pData, function(res){
            if(res.status == 1){
                var sucData = res,
                    sucDataList = sucData.data,
                    i = 0;
                if(pType == 'category'){//行业分类
                    var categoryList = '';
                    for(var listLen = sucDataList.length; i < listLen; i++){
                        categoryList += '<li data-id="'+sucDataList[i].CategoryID+'">'+sucDataList[i].CategoryName+'</li>';
                    }
                    //行业分类展示更多
                    sucDataList.length > 13 ? categoryMoreBtnDom.show() : categoryMoreBtnDom.hide();
                } else if(pType == 'categoryWb'){//行业分类，微博的行业分类没有id，需要单独写
                    var categoryList = '';
                    for(var listLen = sucDataList.length; i < listLen; i++){
                        categoryList += '<li data-id="'+sucDataList[i].CategoryID+'">'+sucDataList[i].typeName+'</li>';
                    }
                    //行业分类展示更多
                    sucDataList.length > 13 ? categoryMoreBtnDom.show() : categoryMoreBtnDom.hide();
                } else if(pType == 'platform'){//平台
                    var categoryList = '';
                    for(var listLen = sucDataList.length; i < listLen; i++){
                        categoryList += '<li data-id="'+sucDataList[i].RecID+'">'+sucDataList[i].PlatformName+'</li>';
                    }
                    //行业分类展示更多
                    sucDataList.length > 13 ? categoryMoreBtnDom.show() : categoryMoreBtnDom.hide();
                }
                pDom.append(categoryList);
                //点击行业分类—更多
                categoryMoreBtnDom.off('click').on('click', function(){
                    var liLen = $('li',categoryWrapDom).length,
                        $this = $(this),
                        srcImg = '';
                    if($this.hasClass('category-more-btn-active')){
                        $this.removeClass('category-more-btn-active');
                        $this.find('img').attr('src', domain + '/images/commonFragments/arr_small_down.png');
                        categoryWrapDom.css('height', '30px');
                    } else {
                        $this.addClass('category-more-btn-active');
                        $this.find('img').attr('src', domain + '/images/commonFragments/arr_small_up.png');
                        categoryWrapDom.css('height', 'auto');
                    }
                })
                categoryClick();
            } else {
                categoryMoreBtnDom.hide()
            }
            if(res.status == 'success'){
                console.log(res.data.data, pType)
                if(pType == 'platform'){//平台
                    var categoryList = '';
                    for(var i = 0, listLen = res.data.data.length; i < listLen; i++){
                        categoryList += '<li data-id="'+res.data.data[i].RecID+'">'+res.data.data[i].PlatformName+'</li>';
                    }
                }
                pDom.append(categoryList);
            }
            if(listType == 'secWeChat'){
                //传参
                getWechatData(pdatalist);//请求微信列表
            } else if (listType == 'secShortVideo') {
                //传参
                getShortVideoData(pdatalist);//请求列表
            } else if (listType == 'secWeibo') {
                //传参
                getWeibotData(pdatalist);//请求列表
            }
        },function(error){
            console.log(error);
            if(listType == 'secWeChat'){
                //传参
                getWechatData(pdatalist);//请求微信列表
            } else if (listType == 'secShortVideo') {
                //传参
                getShortVideoData(pdatalist);//请求列表
            } else if (listType == 'secWeibo') {
                //传参
                getWeibotData(pdatalist);//请求列表
            }
        });
    }
    //粉丝数，参考报价，参考粉丝数
    function getNumbersData(pDom,pType){
        pDom.html('<li data-id="" class="active">不限</li>');
        var fansNumData = [{'name': '5W以下', 'id': '0-50000'},{'name': '5W-10W', 'id': '50000-100000'},{'name': '10W-20W', 'id': '100000-200000'},{'name': '20W-50W', 'id': '200000-500000'},{'name': '50W-100W', 'id': '500000-1000000'},{'name': '100W以上', 'id': '1000000'}],
            quotationPriceData = [{'name': '1000元-3000元', 'id': '1000-3000'},{'name': '3000元-10000元', 'id': '3000-10000'},{'name': '10000元-50000元', 'id': '10000-50000'},{'name': '50000元以上', 'id': '50000'}],
            i = 0;
        if(pType == 'fansNums'){//粉丝数
            var categoryList = '';
            for(var listLen = fansNumData.length; i < listLen; i++){
                categoryList += '<li data-id="'+fansNumData[i].id+'">'+fansNumData[i].name+'</li>';
            }
        } else if(pType == 'quotation'){//参考报价
            var categoryList = '';
            for(var listLen = quotationPriceData.length; i < listLen; i++){
                categoryList += '<li data-id="'+quotationPriceData[i].id+'">'+quotationPriceData[i].name+'</li>';
            }
        } else if(pType == 'fansReference'){//参考粉丝数
            var categoryList = '';
            for(var listLen = fansNumData.length; i < listLen; i++){
                categoryList += '<li data-id="'+fansNumData[i].id+'">'+fansNumData[i].name+'</li>';
            }
        }
        pDom.append(categoryList);
        categoryClick();
    }
    //清除排序
    function clearSortActive(){
        $('.list-item-sort', listWechatWrapDom).removeClass('active');//短视频排序
        $('.list-item-sort', listSvTitWrapDom).removeClass('active');//微信排序
        $('.list-item-sort', listWeiboWrapDom).removeClass('active');//短视频排序
    }
    //点击分类等子选项
    function categoryClick(){
        clearSortActive();
        $('li', platformWrapDom).off('click').on('click', function(){//平台，只有短视频
            var $this = $(this),
                id = $this.data('id'),
                tabSortType = $('.active',tabAdsSortWrapDom).data('type'),
                postData = {};
            $this.addClass('active').siblings().removeClass('active');
            if(tabSortType == 'secWeChat' || tabSortType == 'secShortVideo'){
                postData.categoryID = $('.active', categoryWrapDom).data('id');
            } else if(tabSortType == 'secWeibo'){
                // var typName = $('.active', categoryWrapDom).text();
                // typName == '不限' ? postData.typeName = '' : postData.typeName = typName;
                
                postData.categoryID = $('.active', categoryWrapDom).data('id');
            }
            postData.platFormId = $('.active', platformWrapDom).data('id');
            postData.fansNum = $('.active', fansNumWrapDom).data('id');
            postData.referQuo = $('.active', quotationWrapDom).data('id');
            postData.cp = '1';
            postData.rows = '20';
            postData.search = $.trim(searchInputDom.val());
            postData = commonFun.delEmptyData(postData);
            getShortVideoData(postData);
            clearSortActive();
        })
        $('li', categoryWrapDom).off('click').on('click', function(){//行业分类：短视频，微信，微博
            var $this = $(this),
                id = $this.data('id'),
                postData = {},
                tabSortType = $('.active',tabAdsSortWrapDom).data('type'),
                searchCon = $.trim(searchInputDom.val());
            $this.addClass('active').siblings().removeClass('active');
            clearSortActive();
            postData.fansNum = $('.active', fansNumWrapDom).data('id');
            postData.referQuo = $('.active', quotationWrapDom).data('id');
            postData.cp = '1';
            postData.rows = '20';
            postData.search = searchCon;
            if(tabSortType == 'secShortVideo'){
                postData.categoryID = $('.active', categoryWrapDom).data('id');
                postData.platFormId = $('.active', platformWrapDom).data('id');
                postData = commonFun.delEmptyData(postData);
                getShortVideoData(postData);
            } else if(tabSortType == 'secWeChat'){
                postData.categoryID = $('.active', categoryWrapDom).data('id');
                postData = commonFun.delEmptyData(postData);
                getWechatData(postData);
            } else if(tabSortType == 'secWeibo'){
                // var typName = $('.active', categoryWrapDom).text();
                // typName == '不限' ? postData.typeName = '' : postData.typeName = typName;
                postData.categoryID = $('.active', categoryWrapDom).data('id');
                postData = commonFun.delEmptyData(postData);
                getWeibotData(postData);
            }
        })
        $('li', fansNumWrapDom).off('click').on('click', function(){//粉丝数：短视频、微信、微博
            var $this = $(this),
                id = $this.data('id'),
                postData = {},
                tabSortType = $('.active',tabAdsSortWrapDom).data('type'),
                searchCon = $.trim(searchInputDom.val());
            $this.addClass('active').siblings().removeClass('active');
            clearSortActive();
            if(tabSortType == 'secWeChat' || tabSortType == 'secShortVideo'){
                postData.categoryID = $('.active', categoryWrapDom).data('id');
            } else if(tabSortType == 'secWeibo'){
                // var typName = $('.active', categoryWrapDom).text();
                // typName == '不限' ? postData.typeName = '' : postData.typeName = typName;
                
                postData.categoryID = $('.active', categoryWrapDom).data('id');
            }
            postData.cp = '1';
            postData.rows = '20';
            postData.search = searchCon;
            postData.categoryID = $('.active', categoryWrapDom).data('id');
            postData.fansNum = $('.active', fansNumWrapDom).data('id');
            postData.referQuo = $('.active', quotationWrapDom).data('id');
            postData = commonFun.delEmptyData(postData);
            if(tabSortType == 'secShortVideo'){
                postData.platFormId = $('.active', platformWrapDom).data('id');
                postData = commonFun.delEmptyData(postData);
                getShortVideoData(postData);
            } else if(tabSortType == 'secWeChat'){
                getWechatData(postData);
            } else if(tabSortType == 'secWeibo'){
                getWeibotData(postData);
            }
        })
        $('li', quotationWrapDom).off('click').on('click', function(){//参考报价：短视频、微信、微博
            var $this = $(this),
                id = $this.data('id'),
                postData = {},
                tabSortType = $('.active',tabAdsSortWrapDom).data('type'),
                searchCon = $.trim(searchInputDom.val());
            $this.addClass('active').siblings().removeClass('active');
            clearSortActive();
            
            $('#js-start-price').val('');
            $('#js-end-price').val('');
            postData.fansNum = $('.active', fansNumWrapDom).data('id');
            postData.referQuo = $('.active', quotationWrapDom).data('id');
            postData.cp = '1';
            postData.rows = '20';
            postData.search = searchCon;
            if(tabSortType == 'secShortVideo'){
                postData.categoryID = $('.active', categoryWrapDom).data('id');
                postData.platFormId = $('.active', platformWrapDom).data('id');
                postData = commonFun.delEmptyData(postData);
                getShortVideoData(postData);
            } else if(tabSortType == 'secWeChat'){
                postData.categoryID = $('.active', categoryWrapDom).data('id');
                postData = commonFun.delEmptyData(postData);
                getWechatData(postData);
            } else if(tabSortType == 'secWeibo'){
                postData.categoryID = $('.active', categoryWrapDom).data('id');
                // var typName = $('.active', categoryWrapDom).text();
                // typName == '不限' ? postData.typeName = '' : postData.typeName = typName;
                postData = commonFun.delEmptyData(postData);
                getWeibotData(postData);
            }
        })
    }
    //点击短视频列表粉丝数等排序
    $('.list-item-sort', listSvTitWrapDom).off('click').on('click', function(){
        var $this = $(this),
            dataType = $this.data('type'),
            dataSort = $this.data('sort'),
            postData = {};
        $this.addClass('active').siblings().removeClass('active');
        postData.platFormId = $('.active', platformWrapDom).data('id');
        postData.categoryID = $('.active', categoryWrapDom).data('id');
        postData.fansNum = $('.active', fansNumWrapDom).data('id');
        postData.referQuo = $('.active', quotationWrapDom).data('id');
        postData.cp = '1';
        postData.rows = '20';
        if(dataType == 'sortFansNum'){//粉丝数
            postData.sort_fans = dataSort;
        } else if(dataType == 'sortPlayNum'){//平均播放数
            postData.sort_avgPlayCount = dataSort;
        } else if(dataType == 'sortLikeNum'){//点赞数
            postData.sort_AvgLikeCount = dataSort;
        } else if(dataType == 'sortCommentNum'){//评论数
            postData.sort_AvgCommontCount = dataSort;
        } else if(dataType == 'sortIndexValNum'){//指数
            postData.sort_YrIndex = dataSort;
        }
        postData.search = $.trim(searchInputDom.val());
        postData = commonFun.delEmptyData(postData);
        getShortVideoData(postData);
    })
    //点击微信粉丝数，平均阅读数排序
    $('.list-item-sort', listWechatWrapDom).off('click').on('click', function(){
        var $this = $(this),
            dataType = $this.data('type'),
            dataSort = $this.data('sort'),
            postData = {};
        $this.addClass('active').siblings().removeClass('active');
        postData.categoryID = $('.active', categoryWrapDom).data('id');
        postData.fansNum = $('.active', fansNumWrapDom).data('id');
        postData.referQuo = $('.active', quotationWrapDom).data('id');
        postData.cp = '1';
        postData.rows = '20';
        if(dataType == 'sortFansNum'){//粉丝数
            postData.sort_fans = dataSort;
        } else if(dataType == 'sortReadNum'){//平均阅读数
            postData.sort_AvgReadNum = dataSort;
        } else if(dataType == 'sortLikeNum'){//平均点赞数
            postData.sort_AvgLikeCount = dataSort;
        } else if(dataType == 'sortIndexValNum'){//圆融指数
            postData.sort_YrIndex = dataSort;
        }
        postData.search = $.trim(searchInputDom.val());
        postData = commonFun.delEmptyData(postData);
        getWechatData(postData);
    })
    //点击微博粉丝数等排序
    $('.list-item-sort', listWeiboWrapDom).off('click').on('click', function(){
        var $this = $(this),
            dataType = $this.data('type'),
            dataSort = $this.data('sort'),
            postData = {};
        $this.addClass('active').siblings().removeClass('active');
        postData.categoryID = $('.active', categoryWrapDom).data('id');
        postData.fansNum = $('.active', fansNumWrapDom).data('id');
        postData.referQuo = $('.active', quotationWrapDom).data('id');
        postData.cp = '1';
        postData.rows = '20';
        if(dataType == 'sortFansNum'){//粉丝数
            postData.sort_fans = dataSort;
        } else if(dataType == 'sortTransmitNum'){//平均转发数
            postData.sort_AvgForwardCount = dataSort;
        } else if(dataType == 'sortCommentNum'){//平均评论数
            postData.sort_AvgCommontCount = dataSort;
        } else if(dataType == 'sortLikeNum'){//平均点赞数
            postData.sort_AvgLikeCount = dataSort;
        } else if(dataType == 'sortIndexValNum'){//圆融指数
            postData.sort_YrIndex = dataSort;
        }
        postData.search = $.trim(searchInputDom.val());
        postData = commonFun.delEmptyData(postData);
        getWeibotData(postData);
    })
    
    // 自定义价格查询
    $('#js-self-price-btn').on('click', function () {
        var sPrice = parseFloat($.trim($('#js-start-price').val())),
            ePrice = parseFloat($.trim($('#js-end-price').val())),
            postData = {};
        if(sPrice > 0 || ePrice > 0){
            if(sPrice > 0 && ePrice > 0){
                if(sPrice > ePrice){
                    postData.referQuo = ePrice +'-'+ sPrice;
                } else {
                    postData.referQuo = sPrice +'-'+ ePrice;
                }
            }
            if(sPrice > 0 && !ePrice){
                postData.referQuo = sPrice;
            }
            if(ePrice > 0 && !sPrice){
                // postData.referQuo = ePrice;
                postData.referQuo = 0 +'-'+ ePrice;
            }
        } 
        
        postData.cp = '1';
        postData.rows = '20',
        tabSortType = $('.active',tabAdsSortWrapDom).data('type'),
        searchCon = $.trim(searchInputDom.val());
        $('li', quotationWrapDom).eq(0).addClass('active').siblings().removeClass('active');
        clearSortActive();
        postData.fansNum = $('.active', fansNumWrapDom).data('id');
        postData.cp = '1';
        postData.rows = '20';
        postData.search = searchCon;
        if(tabSortType == 'secShortVideo'){
            postData.categoryID = $('.active', categoryWrapDom).data('id');
            postData.platFormId = $('.active', platformWrapDom).data('id');
            postData = commonFun.delEmptyData(postData);
            getShortVideoData(postData);
        } else if(tabSortType == 'secWeChat'){
            postData.categoryID = $('.active', categoryWrapDom).data('id');
            postData = commonFun.delEmptyData(postData);
            getWechatData(postData);
        } else if(tabSortType == 'secWeibo'){
            postData.categoryID = $('.active', categoryWrapDom).data('id');
            // var typName = $('.active', categoryWrapDom).text();
            // typName == '不限' ? postData.typeName = '' : postData.typeName = typName;
            postData = commonFun.delEmptyData(postData);
            getWeibotData(postData);
        }
    })
    //分页
    function paginationFun(pData, type){
        var totleListNum = listNum;
        // if(pData.search){
        //     listNum < 60 ? totleListNum = listNum : totleListNum = 20;
        // } else {
        //     listNum < 60 ? totleListNum = listNum : totleListNum = 60;
        // }
        $("#pagination").pagination(totleListNum,{//总条目数100
            maxentries: totleListNum,//总条目数	必选参数，整数
            current_page: pData.cp,//当前选中的页面	可选参数，默认是0，表示第1页
            num_edge_entries: 2,//两侧显示的首尾分页的条目数
            num_display_entries: 4,//连续分页主体部分显示的分页条目数
            items_per_page: 20,//每页显示数据条数
            prev_text: '上一页',//“前一页”分页按钮上显示的文字	字符串参数，可选，默认是"Prev"
            next_text: '下一页',//	“下一页”分页按钮上显示的文字	字符串参数，可选，默认是"Next"
            callback: function (currPage, jg) {//前一个表示您当前点击的那个分页的页数索引值，后一个参数表示装载容器
                pData.cp = currPage;
                if(currPage > 3){
                    var getLoginFun = function (isLogin) {
                        if(isLogin == 'Y'){
                            if(type == 'secShortVideo'){
                                getShortVideoData(pData);
                            } else if(type == 'secWeChat'){
                                getWechatData(pData);
                            } else if(type == 'secWeibo'){
                                getWeibotData(pData);
                            }
                        }
                    };
                    getUserLoginInfo(getLoginFun, 'showpop', 'pagination');
                } else {
                    if(type == 'secShortVideo'){
                        getShortVideoData(pData);
                    } else if(type == 'secWeChat'){
                        getWechatData(pData);
                    } else if(type == 'secWeibo'){
                        getWeibotData(pData);
                    }
                }
            }
        });
    }
    //指数问号显示隐藏提示
    $('.index-img').mouseenter(function(){
        var $this = $(this);
        $this.parent().siblings('.zs-down-column-wrap').show();
    }).mouseleave(function(){
        var $this = $(this);
        $this.parent().siblings('.zs-down-column-wrap').hide();
    })
    //点击首页登陆
    var loginPopWrapDom = $('#js-login-pop-wrap');//登陆弹框
    $('.agent-join').on('click', function(){
        var $this = $(this);
        var dataType = $this.data('type');
        loginPopWrapDom.show();
    })

})

//点击加入选购车
function disAddCartFun($this, id) {
    var imgSrc = $this.parent().siblings().find('.avatar-img').attr('src');
        addCartData(tDistri, id, imgSrc, e);
    //判断用户是否登陆
    // var getLoginFun = function (isLogin) {
    //     if(isLogin == 'Y'){
    //         var imgSrc = $this.parent().siblings().find('.avatar-img').attr('src');
    //         addCartData(tDistri, id, imgSrc, e);
    //     }
    // };
    // getUserLoginInfo(getLoginFun);
}

    //报价信息处理
    function getArrayByJson(jsonStr){
        // var jonsArray = JSON.parse(jsonStr);
        var jonsArray = jsonStr;
        var name = new Array(jonsArray.length);
        var priceName = new Array();
        //处理名字，报价信息
        for(var i=0;i<jonsArray.length;i++){
            name[i] = jonsArray[i].platformName;
            var priceInfo = jonsArray[i].positionPrice;
            for(var j=0;j<priceInfo.length ; j ++){
                if(!isInArray(priceName,priceInfo[j].position)){
                    priceName.push(priceInfo[j].position);
                }
            }
        }

        //处理二位数组报价
        var prices = new Array();
        for(var i = 0 ; i< name.length; i++){
            prices[i] = new Array(priceName.length);
        }

        for(var i = 0 ; i< prices.length; i++){
            for(var j = 0 ; j <prices[i].length ; j ++){
                prices[i][j] = {Price:0};
            }
        }

        for(var i = 0 ; i< jonsArray.length; i++){
            var priceInfo = jonsArray[i].positionPrice;
            for(var j=0;j<priceInfo.length ; j ++){
                var index = getNameIndex(priceName, priceInfo[j].position);
                // console.log(i + "\t"+index + "\t" + priceInfo[j]);
                prices[i][index] = priceInfo[j];
            }
        }
        return prices;
    }

    function getNameIndex(nameArray,name) {
        for(var i = 0; i < nameArray.length; i++){
            if(name === nameArray[i]){
                return i;
            }
        }
    }

    function isInArray(arr,value){
        for(var i = 0; i < arr.length; i++){
            if(value === arr[i]){
                return true;
            }
        }
        return false;
    }




function formatAccountPrice(data){
    var newDataList= [];
    if(data.length == 1){
      if(data[0].platformName != '圆融') {
          var obj ={};
          obj.positionPrice = [];
          data.unshift(obj);
      }
        if(data[0].platformName != '微播易') {
            var obj ={};
            obj.positionPrice = [];
            data.splice(1,0,obj);
        }
        if(data[0].platformName != '新榜') {
            var obj ={};
            obj.positionPrice = [];
            data.push(obj);
        }
    }
    if(data.length  == 2){
        data.forEach(function(item, index){
            if(index == 0 && item.platformName != '圆融'){
                    var obj ={};
                    obj.positionPrice = [];
                    data.unshift(obj);
            }
            if(index == 1 && item.platformName != '微播易'){
                    var obj ={};
                    obj.positionPrice = [];
                    data.splice(1,0,obj);
            }



            // if(item.platformName != '圆融' && (item.platformName == '新榜' || item.platformName == '微播易')){
            //     var obj ={};
            //     obj.positionPrice = [];
            //     data.unshift(obj);
            // }
            // if(item.platformName != '新榜' && (item.platformName == '圆融' || item.platformName == '微播易')){
            //     var obj ={};
            //     obj.positionPrice = [];
            //     data.splice(1,0,obj);
            // }
            // if(item.platformName != '微播易' && (item.platformName == '圆融' || item.platformName == '新榜')){
            //     var obj ={};
            //     obj.positionPrice = [];
            //     data.push(obj);
            // }
        })
        if(data.length == 2){
            var obj ={};
            obj.positionPrice = [];
            data.push(obj);
        }
    }
    data.forEach(function(item){
        item.positionPrice.forEach(function(it){
                var obj = {}
                obj.name = it.position;
                obj.price = it.Price;
                newDataList.push(obj);
        })
    });

    var nameList = []

    newDataList.forEach(function(item){
        nameList.push(item.name);
    });
    var formatNameList = nameList.filter(function(ele, index, self){
        return self.indexOf(ele) === index;
    });

    var resData = JSON.parse(JSON.stringify(data));

    resData.forEach(function(item){
        if(item.positionPrice.length < formatNameList.length){
            var ary = [];
            var name = formatNameList.slice(0);
            item.positionPrice.forEach(function(it){
                ary.push(it.position);
            })

            arrDiff(name, ary).forEach(function(ite){
                var obj = {};
                obj.position = ite;
                obj.Price = 0;
                item.positionPrice.push(obj);
            })

        }
    });

    var newDataList1= [];
    resData.forEach(function(item){
        if(item.positionPrice){
            item.positionPrice.forEach(function(it){
                var obj = {}
                obj.name = it.position;
                it.isOriginal = it.isOriginal ? it.isOriginal : 0;
                obj.price = it.Price + '_' + it.isOriginal;
                newDataList1.push(obj);

            })
        }

    });


    var dataAry = [];
    formatNameList.forEach(function(item){
        var obj = { name: item};
        var ary = [];
        newDataList1.forEach(function(it){
            if(it.name == item){
                ary.push(it.price);
            }
        });
        obj.price = ary;
        dataAry.push(obj);
    });
    return dataAry;

}

function arrDiff(array,array2){
    var arr3 =[];
    for (key in array) {
        var stra = array[key];
        var count = 0;
        for(var j= 0; j < array2.length; j++){
            var strb = array2[j];
            if(stra == strb) {
                count++;
            }
        }
        if(count===0) {//表示数组1的这个值没有重复的，放到arr3列表中
            arr3.push(stra);
        }
    }
    return arr3;
}