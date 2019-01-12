<#include "../commonFragments/basic/meta.ftl"/>
	<title>创作品_作品详情_内容生产创作_圆融内容银行交易服务中心</title>

<meta http-equiv="keywords" content='作品价格,原创作品,作品交易' />
<meta http-equiv="description" content="原创作品售卖中,作品价格,原创作品,作品交易,作品详情,圆融内容交易服务中心由中国传媒大学,内容银行重点实验室成立,目的是构建内容生产、传播、变现、资产化的一站式交易平台,面向内容创作者、广告主、代理公司、投融资机构等产业各方,提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务,打造内容产业新生态.圆融内容银行交易服务中心,内容交易,圆融内容交易,圆融平台,圆融内容交易服务平台,圆融买家卖家后台登录,内容版权交易平台,内容知识产权,授权转载平台,创作者,自媒体,广告主,内容创作,内容生产,写作赚钱,自媒体征稿,投稿赚钱,创作者创作赚钱,内容交易平台,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易:短视频广告营销(秒拍,美拍,快手,抖音),微信变现,微博赚钱,类似：克劳瑞,一道自媒体,稿稿,乐观号,微播易/微博易,,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业）" />
<meta name="keywords" content='作品价格,原创作品,作品交易' />
<meta name="description" content="原创作品售卖中,作品价格,原创作品,作品交易,作品详情,圆融内容交易服务中心由中国传媒大学,内容银行重点实验室成立,目的是构建内容生产、传播、变现、资产化的一站式交易平台,面向内容创作者、广告主、代理公司、投融资机构等产业各方,提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务,打造内容产业新生态.圆融内容银行交易服务中心,内容交易,圆融内容交易,圆融平台,圆融内容交易服务平台,圆融买家卖家后台登录,内容版权交易平台,内容知识产权,授权转载平台,创作者,自媒体,广告主,内容创作,内容生产,写作赚钱,自媒体征稿,投稿赚钱,创作者创作赚钱,内容交易平台,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易:短视频广告营销(秒拍,美拍,快手,抖音),微信变现,微博赚钱,类似：克劳瑞,一道自媒体,稿稿,乐观号,微播易/微博易,,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业）" />

<#include "../commonFragments/basic/css_link.ftl"/>
 	<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/iconfont/iconfont.css?${mdc_version}">
 	<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/contentBank/tab.css?${mdc_version}">
 	<#--<link rel="stylesheet" href="${request.contextPath}/css/contentBank/creator.css?${mdc_version}">-->
    <link rel="stylesheet" href="${request.contextPath}/css/contentBank/original_work.css?${mdc_version}">
	<link rel="stylesheet" href="${request.contextPath}/css/contentBank/work_detail_abnormal.css?${mdc_version}">
</head>
<body>
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
<!-- 创作者 导航 -->
<div class="wrap wrap-has-bread">
    <div class="content_bank">
        <div class='author-content-wrap'>
            <div class='bread-crumbs-wrap'>
                <a href="/contentBank/original_work.html" style="color:#64676a">内容交易</a>
                <span>&gt;</span>
                <a href="/contentBank/original_work.html" style="color:#64676a">原创作品</a>
                <span>&gt;</span>
                <span style="color:#303132;">作品详情</span>
                <a href="/contentBank/original_work.html" class='back' ><span style="color: #bbbfc4">&lt;</span><span style="color:#64676a;font-size: 12px;">返回</span></a>
            </div>
        </div>
        <div id='abnormal-wrap'>
            <img src="${request.contextPath}/images/contentBank/no-work.png">
            <p style="color:#333333;font-size:14px;">作品不存在或已下架</p>
        </div>

        <div class='similar-author-wrap'>
            <#--<p>-->
                <#--<span>相似作品推荐</span>-->
                <#--<span class='chan-batch'>-->
					<#--<i></i>-->
					<#--换一批-->
				<#--</span>-->
            <#--</p>-->
            <div class='creator-content-list-wrap'>
                <!--相似作品推荐-->
                <div class="similar">
                    <h3 style="color:#64676a;font-size:16px;font-weight:normal;">相似作品推荐<#include "common/change.ftl"/></h3>
                    <div class="similar_list">
                        <ul>
                        </ul>
                    </div>
                    <div class="no-data" style="padding: 20px 0 40px 0;display:none;">
                        <img src="../../../images/commonFragments/no_data.png" alt="">
                        <p style="color:#64676a;font-size:16px;">暂无数据</p>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
	<#include "../commonFragments/basic/footer.ftl"/>
<!-- 热线电话 -->
	<#include "../commonFragments/hotline.ftl"/>
</body>
<style>
    ul {
        width: 100%;
    }
    li{
        clear: none;
    }
    body{
        background: #eceff4;
        height: auto;
        margin: 0 !important;
        font-size: 14px;
    }
    p {
        margin: 0;
    }
</style>

<#include "../commonFragments/basic/js_script.ftl"/>
<script type="text/javascript" src="${request.contextPath}/plugins/jquery.pagination.js?${mdc_version}"></script>
<script type="text/javascript" src="${request.contextPath}/js/contentBank/work_detail_abnormal.js"></script>
<script>
    var recId = window.location.href.split('detail_')[1].split('.')[0];
    var curPage = 1;
    var similarFlag = true;
    var similarData;

    $.ajax({
        url: domain + '/author/getProductInfo',
        type: 'post',
        data: { ypRecId: recId },
        dataType: 'json',
        crossDomain: true,})

    //判断是否有相似作品;
    $.ajax({
        url: domain + '/author/getSimilarProducts',
        type: 'post',
        data: {
            recId:  recId
        },
        dataType: 'json',
        crossDomain: true,
        success: function(res){
            if(res.status == 1){
                if(res.data.length == 0){
                    similarFlag = false;
                    getWorkList(curPage);
                }else {
                    similarData = res.data;
                    console.log(similarData.slice(0,5));
                    getSimilarList(similarData.slice(0,5));
                    if(similarData.length < 5){
                        $('.change-page').hide();
                        return;
                    }
                    $('.change-page').on('click', function(){
                        curPage += 1;
                        curPage = curPage % 3 == 0 ? 3 : curPage % 3;
                        getSimilarList(similarData.slice(5*(curPage-1),5*curPage));
                    });
                }
            }else {
                similarFlag = false;
                getWorkList(curPage);
            }
        }
    });

    //相似作品数据绑定;
    function getSimilarList(data){
        if(data.length == 0){
            return ;
        }
        $('.similar_list ul').empty();
        data.forEach(function(item, index){
            
            if(item.originalScore){
                var originalScore = item.originalScore, isShowOriginalScore = '';
            } else {
                var originalScore = 0, isShowOriginalScore = 'hide';
            }
            $('<li>' +
                    '<a href="/contentBank/article_detail_'+ item.recId +'.html"><img src="'+ item.coverLocalUrl +'" style="height:130px;width:230px;background:url(../../static/images/article/bg_image_big.png) center center no-repeat;background-size:230px 130px;" alt=""></a>' +
                    '<div class="similar_article_list">' +
                    '<a href="/contentBank/article_detail_'+ item.recId +'.html"><h3 style="position:relative" class="work-title">' +
                    ''+ item.productionTitle +'<span style="color:#333333;font-size:14px;font-weight:400;">('+ item.wordNum +'字'+ item.imgNum +'图片)</span></h3></a>' +

                    '<input type="hidden" class="author-status" value='+ item.yrAuthorStatus +'>' +
                    // '<span style="display:none" class="author-status">'+ item.yrAuthorStatus +'</span>' +
                    '<div class="publish_time" style="cursor:pointer;color:#64676a;font-size:12px;position:relative;">' +
                    '<span style="display:inline-block;height:30px;width:240px;" class="work-author">' +
                    "<div class='float-auhtor' style='position:absolute;top:20px;'>"+
                    "<p class='author-float-info'>"+
                    "<img style='width:50px;height:50px;border-radius:50%;' src='"+item.authorImg+"'>"+
                    "<span>"+item.authorNickname+"</span>"+
                    "</p>"+
                    "<p>"+
                    "创作形式：<span>"+item.contentform+"</span>"+
                    "</p>"+
                    "<p>"+
                    "擅长领域：<span>"+item.yrAuthorCategory+"</span>"+
                    "</p>"+
                    "<p>"+
                    "作品数量：<span>"+item.prnum+"</span>"+
                    "</p>"+
                    "<p class='popularity'>"+
                    "人<span style='visibility:hidden'>哈哈</span>气：<span>"+item.accessNum+"</span>"+
                    "</p>"+
                    "<p class='jump_detail'>"+
                    "<a href='"+domain+"/contentBank/author_detail_"+item.yrAuthorId+".html' target='_blank' class='look-detail'>查看详情</a>"+
                    "</p>"+
                    "</div>"+


                    '作者: '+ item.authorNickname +'</span>' +
                    // '<span style="color:#64676a;font-size:12px;">'+ item.publishTime +'</span>' +
                    '<span style="float:right;color:#64676a;font-size:12px;" class="similar-type"><span class="buy-type">价格：</span><i>￥'+ thousandCharacter(item.productQuotedPrice) +'</i></span>' +
                    '</div>' +
                    '<article style="height:44px;margin-top:-5px;overflow:hidden">'+ item.localContent +'</article>' +
                    '<div class="tags">' +
                    '<span class="category">分类：<i>'+ item.yrAuthorCategory +'</i></span>' +
                    '<span class="screen">营销场景：<i>'+ item.crawTitle +'</i></span>' +
                    '<span class="similar-high-word">高频词：<i>'+ item.tags +'</i></span>' +
                    // '<span class="look-num" ><span style="color:#727477;">浏览量：</span><i>'+ item.accessNum +'</i></span>' +
                    '<span class="content-index original-score'+isShowOriginalScore+'">原创度：<i>'+ originalScore +'</i></span>' +
                    //'<span class="content-index">圆融指数：<i>'+ item.contentScore +'</i></span>' +
                    // '<span>交易次数：<i>'+ item. +'</i></span>' +
                    '</div>' +
                    '</div>' +
                    '</li>').appendTo('.similar_list ul');

            item.yrAuthorCategory == undefined ? $('.category').hide() : null;
            item.crawTitle == (undefined || '' || null) ? $('.screen').hide() : null;
            item.tags == (undefined || '') ? $('.similar-high-word').hide() : $('.similar-high-word i').eq(index).html(item.tags.replace(/、/g, '<span class="work-label"> | </span>'));

            $('.similar_list ul li').each(function(i,index){

                if(item.publishStatusIndex == 0){

                    $('.similar-type .buy-type').text('买断价：')
                }else {
                    $('.similar-type .buy-type').text('转载使用：')
                }
                //$(this).find('.content-index i').val() == 0 ? $(this).find('.content-index').hide() : null;
                $(this).find('.work-author').hover(function(){
                    // $(this).find('.float-auhtor').show()
                    $(this).parent().parent().find('.author-status').val() ==2 ?  $(this).find('.float-auhtor').show() : null;
                }, function(){
                    $(this).find('.float-auhtor').hide();
                })
            });
        });
    }

    //若相似作品无数据, 获取作品列表页数据;
    function getWorkList(curPage){
        $.ajax({
            url: domain + '/author/yrProduction_list',
            type: 'post',
            data: { cp: curPage, rows: 5, publishStatus: 0 },
            dataType: 'json',
            crossDomain: true,
            success: function(res) {
                if(res.status == 1){
                    $('.similar_list ul').empty();
                    res.data.data.forEach(function(item, index){
                        
                        if(item.originalScore){
                            var originalScore = item.originalScore, isShowOriginalScore = '';
                        } else {
                            var originalScore = 0, isShowOriginalScore = 'hide';
                        }
                        $('<li>' +
                                '<a href="/contentBank/article_detail_'+ item.recId +'.html"><img src="'+ item.coverLocalUrl +'" style="height:135px;width:230px;background:url(../../static/images/article/bg_image_big.png) center center no-repeat;background-size:230px 130px;" alt=""></a>' +
                                '<div class="similar_article_list">' +
                                '<a href="/contentBank/article_detail_'+ item.recId +'.html"><h3 class="work-title">'+ item.title +'<span style="color:#333333;font-size:14px;font-weight:400;">('+ item.wordNum +'字'+ item.imgNum +'图片)</span></h3></a>' +

                                // '<p class="publish_time">' +
                                // '<span>'+ item.createdTime +'</span>' +
                                // '<span>转载使用：<i>￥'+ item.price +'</i></span>' +
                                // '</p>' +


                                '<div class="publish_time" style="color:#64676a;font-size:12px;position:relative;">' +
                                '<span style="display:inline-block;height:30px;width:240px;" class="work-author">' +
                                "<div class='float-auhtor' style='position:absolute;top:20px;'>"+
                                "<p class='author-float-info'>"+
                                "<img style='width:50px;height:50px;border-radius:50%;' src='"+item.authorInfo.authorImg+"'>"+
                                "<span>"+item.authorInfo.authorNickname+"</span>"+
                                "</p>"+
                                "<p>"+
                                "创作形式：<span>"+item.authorInfo.contentform+"</span>"+
                                "</p>"+
                                "<p>"+
                                "擅长领域：<span>"+item.authorInfo.yrAuthorCategory+"</span>"+
                                "</p>"+
                                "<p>"+
                                "作品数量：<span>"+item.authorInfo.prnum+"</span>"+
                                "</p>"+
                                "<p class='popularity'>"+
                                "人<span style='visibility:hidden'>哈哈</span>气：<span>"+item.authorInfo.accessNum+"</span>"+
                                "</p>"+
                                "<p class='jump_detail'>"+
                                "<a href='"+domain+"/contentBank/author_detail_"+item.authorInfo.yrAuthorId+".html' target='_blank'>查看详情</a>"+
                                "</p>"+
                                "</div>"+


                                '作者: <span style="color:#333;">'+ item.authorInfo.authorNickname +'</span></span>' +
                                // '<span style="color:#64676a;font-size:12px;">'+ item.createdTime +'</span>' +
                                '<span style="float:right;color:#64676a;font-size:12px;"><span class="buy-type">买断价：</span><i>￥'+ thousandCharacter(item.price) +'</i></span>' +
                                '</div>' +


                                '<article style="height:44px;overflow:hidden;">'+ item.content +'</article>' +
                                '<div class="tags">' +
                                '<span class="category" ><span style="color:#727477;">分类：</span><i>'+ item.yrCategory +'</i></span>' +
                                '<span class="screen" ><span style="color:#727477;">营销场景：</span><i>'+ item.crawTitle +'</i></span>' +
                                '<span class="high-word" ><span style="color:#727477;">高频词：</span><i>'+ item.tags +'</i></span>' +
                                // '<span class="look-num" ><span style="color:#727477;">浏览量：</span><i>'+ item.accessNum +'</i></span>' +
                                '<span class="content-index original-score ' + isShowOriginalScore+'" ><span>原创度：</span><i>'+ originalScore +'</i></span>' +
                                //'<span class="content-index" ><span style="color:#727477;">圆融指数：</span><i>'+ item.contentScore +'</i></span>' +
                                // '<span>交易次数：<i>'+ item. +'</i></span>' +
                                '</div>' +
                                '</div>' +
                                '</li>').appendTo('.similar_list ul');


                        item.title == undefined ? $('.work-title').css({ 'visibility': 'hidden' }) : null;
                        item.crawTitle == undefined ? $('.screen').hide() : null;
                        item.tags == (undefined || '' || null) ? $('.high-word').hide() : $('.high-word i').eq(index).html(item.tags.replace(/、/g, '<span class="work-label"> | </span>'));
                        //item.contentScore == undefined ? $('.content-index').hide() : null;

                        $('.similar_list ul li').each(function(it, index){
                            $(this).find('.work-author').hover(function(){
                                if($(this).parent().parent().find('.author-status').val() ==2){
                                    $(this).find('.float-auhtor').show();
                                }
                            }, function(){
                                $(this).find('.float-auhtor').hide();
                            })
                        })


                    });
                }
            }
        })
    }

    //点击换一批, 重新渲染相似作品数据;
    $('.change-page').on('click', function(){
        if(!similarFlag){
            curPage += 1;
            curPage = curPage % 3 == 0 ? 3 : curPage % 3;
            getWorkList(curPage);
        }
    });

    //千分符
    function thousandCharacter(val){
        const reg=/(\d{1,3})(?=(\d{3})+(?:$|\.))/g;
        return val ? val.toString().replace(reg, '$1,') : '--';
    }
</script>
</html>


