/*
* author: wangZhu;
* version: 1.4;
* */

$(function(){
    var recId = window.location.href.split('detail_')[1].split('.')[0];
    var curPage = 1;
    var similarFlag = true;
    var publishStatus;
    var similarData;

    //作品详情数据及作者数据绑定;
    $.ajax({
        url: domain + '/author/getProductInfo',
        type: 'post',
        data: { ypRecId: recId },
        dataType: 'json',
        crossDomain: true,
        success: function(res){
            if(res.status === 1){
                publishStatus = res.data.publishStatusIndex;
                var data = res.data;
                var src = '/contentBank/original_work.html?publishStatus=' + publishStatus;

                $('<span >&lt;</span>' +
                    '<a href="'+ src +'" style="font-size: 12px;">返回</a>'
                ).appendTo($('.back'));

                data['yrCategoryName'] ? $('.title-detail-category').find('span').text(data['yrCategoryName']) : $('.title-detail-category').hide();
                // data['contentEvaluationScore'] ? $('.title-detail-index').find('span').text(data['contentEvaluationScore']) : $('.title-detail-index').hide();
                data['originalScore'] ? $('.title-detail-index').find('span').text(data['originalScore']) : $('.title-detail-index').hide();
                data['imgNum'] ? $('<span>'+ data['imgNum'] +'图片</span>').appendTo($('.title-detail-info span')) : null;

                if(data['tags']){
                    var tagsFormat = data['tags'].replace(/、/g, '<span class="work-label"> | </span>');

                    $('<span style="color:#303132;">'+ tagsFormat +'</span>')
                        .appendTo($('.publish-hot span'));
                }else {
                    $('.publish-hot').hide();
                }


                if(data['isAuthor'] === 1){
                    $('.private-work').show();
                    $('.not-publish .btn').hide();
                }

                if(data.publishStatusIndex === '0'){
                    $('.not-publish').show();
                    (data['hotWords'] && data['hotWords'] !== '') ?  $('.hot').show() : null;
                    $('.article-detail-slice').addClass('slice-hidden');
                    $('.work-price span').eq(0).text('买断价：');
                }else {
                    $('.publish').show();
                    $('.work-price span').eq(0).text('转载使用: ');
                }

                //判断购买者是否查看过;
                if(data.isBuy){
                    $('.not-publish > p').hide();
                    $('#js-cart-fix-bottom-wrap').hide();
                    $('.article-detail-slice').removeClass('slice-hidden');
                    if(!data.isRead){

                        $('.not-publish .btn').text('查看全部')
                        $('.not-publish .btn').unbind("click").on('click', function(){
                            console.log('事件绑定')
                            $.ajax({
                                url: domain + '/author/getCompleteContent',
                                type: 'post',
                                data: { recId: data.recId },
                                dataType: 'json',
                                crossDomain: true,
                                success: function(res){
                                    if(res.status === 1){
                                        $('.not-publish > p').hide();
                                        $('.not-publish .btn').hide();

                                        $('.article-detail-slice').html(res.data);
                                    }
                                }
                            })
                        });
                    }else{

                        $('.not-publish > p').hide();
                        $('.not-publish .btn').hide();
                    }
                }else{
                    $('.not-publish .btn').on('click', function(){
                        buyProductFun(data.recId, 'singleBuy');
                    });
                }




                upDown();
                if(data['hotWords']){
                    hotWordRender(data['hotWords']);
                }
                authorRender(data);
                bottomBuy(data);
            }else{

            }
        }
    });

    //文章的展开与收起;
    function upDown(){
        var toggleFlag = false;

        if($('.article-detail-slice').height() >= 790){
            $('.article-detail-slice').height(790);
            $('.article-detail-slice').css('overflow', 'hidden');
            $('<div>' +
                '<div class="show-more">展开更多' +
                '<i class="iconfont icon-open" style="color:#8c8e91;"></i>' +
                '</div>' +
                '</div>'
            ).appendTo($('.article-detail'));
        }
        $('.show-more').on('click', function(){
            if(!toggleFlag){
                $('.article-detail-slice').height('auto');
                toggleFlag = true;
                $('.show-more').html('<span>收起<i class="iconfont icon-shouqi" style="color:#8c8e91;"></i></span>');
            }else {
                $('.article-detail-slice').height(790);
                $('.show-more').html('<span>展开更多<i class="iconfont icon-open" style="color:#8c8e91;"></i></span>');
                toggleFlag = false;
            }
        });
    }

    //作者信息数据绑定;
    function authorRender(data){
        var curStyle = data['authorStatus'] == 2 ? "cursor:pointer;" : "";

        $('<img class="author-img" src="'+ data.authorImg +'" alt="" style="'+curStyle+' background:url(../../images/commonFragments/default_avart.png) no-repeat center">' +
            '<div class="author-name" style="'+curStyle+'">'+ data.authorNickname +
            '</div>' +
            '<div class="author-article">' +
            '<dl><dt>'+ data['productionNum'] +'</dt><dd>文章</dd></dl>' +
            '<dl><dt>'+ data['authorAccessNum'] +'</dt><dd>人气</dd></dl>' +
            '</div>').appendTo($('.author-desc'));

        //作者最近发布文章数据绑定;
        if(data['productionList']){
            data['productionList'].forEach(function(item, index){
                $('<li>' +
                    '<span style="position: relative;top: -8px;font-size: 26px;margin-right: 3px;float: left;">.</span>' +
                    '<a style="display: inline-block;width: 95%;" href="/contentBank/article_detail_'+ item.recId +'.html">'+ item.title +'</a>' +
                    '</li>'
                ).appendTo($('.author-contact ul'));
                item.title === undefined ? $('.author-contact ul li').eq(index).hide() : null;
            });
        }else {
            $('.author-contact').hide();
        }

        //创作者上架, 点击头像或名称进入作者详情页;
        if(data['authorStatus'] == 2){
            $('.author-img').on('click', function(){
                window.open('/contentBank/author_detail_'+ data.authorId +'.html');

            });
            $('.author-name').on('click', function(){
                window.open('/contentBank/author_detail_'+ data.authorId +'.html');
            });
        }
        else {
            $('.author-img').on('click', function(){
                layer.tips('创作者暂不接受预约',
                    '.author-img',
                    {area: '150px',time: 2000, tips: [3, '#aaa']}
                )
            });
            $('.author-name').on('click', function(){
                layer.tips('创作者暂不接受预约',
                    '.author-img',
                    {area: '150px',time: 2000, tips: [3, '#aaa']}
                )
            });
        }
    }

    //图文热词数据绑定;
    function hotWordRender(data){
        function createRandomItemStyle() {
            return {
                normal: {
                    color: 'rgb(' + [
                        Math.round(Math.random() * 200),
                        Math.round(Math.random() * 200),
                        Math.round(Math.random() * 200)
                    ].join(',') + ')'
                }
            }
        }
        var myChart = echarts.init(document.getElementById('hot-word'));
        var chartData = data.split('、');
        var chartDataAry = [];

        chartData.forEach(function(item, index){
            var obj = {};
            obj.name = item;
            obj.value = 10000 - index * 500;
            obj.itemStyle = createRandomItemStyle();
            chartDataAry.push(obj);
        });
        var option = {
            series: [{
                clickable: false,
                name: 'Google Trends',
                type: 'wordCloud',
                size: ['80%', '80%'],
                textRotation : [0],
                textPadding: 5,
                autoSize: {
                    enable: true,
                    minSize: 14
                },
                data: chartDataAry
            }]

        };
        myChart.setOption(option);
    }

    //底部购买数据绑定;
    function bottomBuy(data){
        //底部购买数据绑定;
        $('.bottom-work-title').text(data['title']);
        $('.work-price span').eq(1).text('￥'+ thousandCharacter(data.productQuotedPrice) +'');

        var cartFlag = location.href.split('=')[1];
        if(data['isAddCart'] == 1){
            $('.add-buy').text('已加入选购车');
            $('.add-buy').css({ 'cursor': 'auto', 'background': 'rgb(248, 249, 251)', 'color': 'rgb(211, 213, 217)' })
        }else{
            $('.add-buy').on('click', function(e){
                addCartData('article', recId, data.authorImg, e)
            });
        }

        // $('.btn').on('click', function(){
        //     buyProductFun(recId, 'singleBuy');
        // });
        $('.work-buy').on('click', function(){
            buyProductFun(recId, 'singleBuy');
        })
    }

    //获取相似作品数据;
    $.ajax({
        url: domain + '/author/getSimilarProducts',
        type: 'post',
        data: { ypRecId: recId },
        dataType: 'json',
        crossDomain: true,
        success: function(res){
            if(res.status === 1){
                //若无相似作品,则取作品列表数据绑定;
                if(res.data.length === 0){
                    similarFlag = false;
                    getWorkList(curPage);
                }
                else {
                    similarData = res.data;
                    similarListRender(similarData.slice(0,5));
                    if(similarData.length < 5){
                        $('.change-page').hide();
                    }
                    $('.change-page').on('click', function(){
                        curPage += 1;
                        curPage = curPage % 3 === 0 ? 3 : curPage % 3;
                        similarListRender(similarData.slice(5*(curPage-1),5*curPage));
                    });
                }
            }
        }
    });


    //相似作品数据绑定;
    function similarListRender(data){
        if(data.length === 0){
            return ;
        }
        $('.similar_list ul li').remove();
        data.forEach(function(item, index){
            var formatContent = $.trim(item.localContent);

            if(item.originalScore){
                var originalScore = item.originalScore, isShowOriginalScore = '';
            } else {
                var originalScore = 0, isShowOriginalScore = 'hide';
            }
            if(item.coverLocalUrl){
                var coverLocalUrl = item.coverLocalUrl;
            } else {
                if(item.coverLocalUrl == 'None'){
                    var coverLocalUrl = (domain+'/static/images/article/bg_image_big.jpg');
                }
                var coverLocalUrl = (domain+'/static/images/article/bg_image_big.jpg');
            }
            $('<li>' +
                '<a href="/contentBank/article_detail_'+ item.recId +'.html">' +
                    '<img src="'+ coverLocalUrl +'" style="height:130px;width:230px;background:url(../../static/images/article/bg_image_big.jpg) center center no-repeat;" alt="">' +
                '</a>' +
                '<div class="similar_article_list">' +
                '<a href="/contentBank/article_detail_'+ item.recId +'.html">' +
                    '<h3 style="position:relative;height:35px;" class="work-title">' +
                        '<span style="overflow:hidden;display:inline-block;white-space:nowrap;text-overflow:ellipsis">'+ item.productionTitle.slice(0,16) +'</span>' +
                        '<span style="color:#333333;font-size:14px;font-weight:400;position:absolute;top:0;">('+ item.wordNum +'字'+ item.imgNum +'图片)' +
                        '</span>' +
                    '</h3>' +
                '</a>' +
                '<input type="hidden" class="author-status" value='+ item.yrAuthorStatus +'>' +

                '<div class="publish_time" style="color:#64676a;font-size:12px;position:relative;">' +
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
                            "<a href='"+domain+"/contentBank/author_detail_"+item.yrAuthorId+".html' target='_blank' class='look-detail'>查看详情" +
                            "</a>"+
                        "</p>"+
                    "</div>"+

                    '作者: <span style="color:#474849;">'+ item.authorNickname +'</span></span>' +
                    // '<span style="color:#64676a;font-size:12px;">'+ item.publishTime +'' +
                    // '</span>' +
                    '<span style="float:right;color:#64676a;font-size:12px;">' +
                        '<span class="buy-type">转载使用：</span>' +
                        '<i>￥'+ thousandCharacter(item.productQuotedPrice) +'</i>' +
                    '</span>' +
                    '</div>' +
                    '<article style="height:44px;margin-top:-5px;">'+ formatContent +'' +
                    '</article>' +
                    '<div class="tags">' +
                        '<span class="category">分类：' +
                            '<i>'+ item.yrAuthorCategory +'</i>' +
                        '</span>' +
                        '<span class="screen">营销场景：' +
                            '<i>'+ item.crawTitle +'</i>' +
                        '</span>' +
                        '<span class="high-word">高频词：' +
                            '<i>'+ item.tags +'</i>' +
                        '</span>' +
                        // '<span class="access-num">浏览量：' +
                        // '<i>'+ item.accessNum +'</i>' +
                        // '</span>' +
                        // '<span class="content-index">圆融指数：' + '<i>'+ item.contentScore +'</i>' + '</span>' +
                        '<span class="content-index original-score ' + isShowOriginalScore+'">原创度：' + '<i>'+ originalScore +'</i>' + '</span>' +
                    '</div>' +
                '</div>' +
                '</li>').appendTo('.similar_list ul');

            item['yrAuthorCategory'] ? null : $('.category').hide();
            item['crawTitle'] ? null : $('.screen').hide();
            item['accessNum'] ? null : $('.access-num').hide();
            item['tags'] == (undefined || '') ? $('.high-word').hide() : $('.high-word i').eq(index).html(item.tags.replace(/、/g, '<span class="work-label"> | </span>'));

            $('.similar_list ul li').each(function(){
                // $(this).find('.content-index i').val() == 0 ? $(this).find('.content-index').hide() : null;
                $(this).find('.work-author').hover(function(){
                    if($(this).parent().parent().find('.author-status').val() ==2 ){
                        $(this).find('.work-author').css({ 'cursor': 'default' });
                        $(this).find('.float-auhtor').show();
                    }

                }, function(){
                    $(this).find('.float-auhtor').hide();

                })
            });
            if(publishStatus == 0){
                $('.buy-type').text('买断价：');
            }
        });
    }


    //若相似作品无数据, 获取作品列表页数据;
    function getWorkList(curPage){
        $.ajax({
            url: domain + '/author/yrProduction_list',
            type: 'post',
            data: {publishStatus: publishStatus, cp: curPage, rows: 5  },
            dataType: 'json',
            crossDomain: true,
            success: function(res) {
                if(res.status === 1){
                    $('.similar_list ul').empty();
                    res.data.data.forEach(function(item, index){
                        if(item.recId != recId) {
                            
                            if(item.originalScore){
                                var originalScore = item.originalScore, isShowOriginalScore = '';
                            } else {
                                var originalScore = 0, isShowOriginalScore = 'hide';
                            }
                            if(item.coverLocalUrl){
                                var coverLocalUrl = item.coverLocalUrl;
                            } else {
                                if(item.coverLocalUrl == 'None'){
                                    var coverLocalUrl = (domain+'/static/images/article/bg_image_big.jpg');
                                }
                                var coverLocalUrl = (domain+'/static/images/article/bg_image_big.jpg');
                            }
                            $('<li class="fixedClear">' +
                                '<a href="/contentBank/article_detail_'+ item.recId +'.html"><img src="'+ coverLocalUrl +'" style="height:130px;width:230px;background:url(../../static/images/article/bg_image_big.jpg) center center no-repeat;" alt=""></a>' +
                                '<div class="similar_article_list">' +
                                '<a href="/contentBank/article_detail_'+ item.recId +'.html"><h3 style="position:relative" class="work-title">' +
                                ''+ item.title +'<span style="color:#333333;font-size:14px;font-weight:400;">('+ item.wordNum +'字'+ item.imgNum +'图片)</span></h3></a>' +

                                '<span style="display:none;" class="author-status">'+ item.authorInfo.yrAuthorStatus +'</span>' +
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
                                "<a href='"+domain+"/contentBank/author_detail_"+item.authorInfo.yrAuthorId+".html' target='_blank' class='look-detail'>查看详情</a>"+
                                "</p>"+
                                "</div>"+


                                '作者: '+ item.authorInfo.authorNickname +'</span>' +
                                // '<span style="color:#64676a;font-size:12px;">'+ item.createdTime +'</span>' +
                                '<span style="float:right;color:#64676a;font-size:12px;"><span class="buy-type">转载使用：</span><i>￥'+ thousandCharacter(item.price) +'</i></span>' +
                                '</div>' +

                                '<article style="height:44px;margin-top:-5px;">'+ item.content +'</article>' +
                                '<div class="tags">' +
                                '<span class="category">分类：<i>'+ item.yrCategory +'</i></span>' +
                                '<span class="screen">营销场景：<i>'+ item.crawTitle +'</i></span>' +
                                '<span class="high-word">高频词：<i>'+ item.tags +'</i></span>' +
                                // '<span class="content-index">圆融指数：<i>'+ item.contentEvaluationScore +'</i></span>' +
                                '<span class="content-index original-score '+isShowOriginalScore+'">原创度：<i>'+ originalScore +'</i></span>' +
                                '</div>' +
                                '</div>' +
                                '</li>').appendTo('.similar_list ul');

                            item.title ? null : $('.work-title').css({ 'visibility': 'hidden' });
                            item.crawTitle ? null :  $('.screen').hide();
                            item.yrCategory ? null : $('.category').hide();
                            // item.tags == (null || '') ? $('.high-word').hide() : $('.high-word i').eq(index).html(item.tags.replace(/、/g, '<span class="work-label"> | </span>'));
                            if(item['tags']){
                                console.log($('.similar_list .high-word i').eq(index))
                                $('.similar_list .high-word i').eq(index).html(item.tags.replace(/、/g, '<span class="work-label"> | </span>'));
                            }else{
                                $('.high-word').hide();
                            }

                            $('.similar_list ul li').each(function(i, index){
                                // $(this).find('.content-index i').text() == 0 ? $(this).find('.content-index').hide() : null;
                                $(this).find('.work-author').hover(function(){
                                    if($(this).parent().parent().find('.author-status').text() ==2){
                                        $(this).find('.work-author').css({ 'cursor': 'pointer' });
                                        $(this).find('.float-auhtor').show();

                                    }
                                    // $(this).parent().parent().find('.author-status').text() ==2 ?  $(this).find('.float-auhtor').show() : null;
                                }, function(){
                                    $(this).find('.float-auhtor').hide();
                                })
                            });
                            if(publishStatus == 0){
                                $('.buy-type').text('买断价：');
                            }
                        }
                    });
                }
            }
        })
    }

    //点击换一批, 重新渲染作品列表数据;
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

});
