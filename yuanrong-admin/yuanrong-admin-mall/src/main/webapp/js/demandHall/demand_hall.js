/*
* author: wangZhu;
* version: 1.5;
* */

$(function(){
   //查询项切换active样式改变
    var typeIndex = 0;

    var curPage = 1;
    var pageSize = 10;
    var totalPage;

    function changeActive(ele){
        $(ele).each(function(index){
            $(this).on('click', function(){
                typeIndex = index;
                $(this).addClass('active').parent().siblings().find('span').removeClass('active');
                if(/demand-query-type/.test(ele.selector)){
                    curPage = 1;
                    if(typeIndex === 1){
                        typeIndex = 4;
                        getDemandList(4);
                    }else{
                        typeIndex = index;
                        getDemandList(typeIndex);
                    }
                }
            })
        })
    }
    changeActive($('.demand-query-type > span > span'));

    //判断各类型是否有数据;
    $.ajax({
        url: '/demandHall/demandTypeCnt',
        type: 'post',
        data: {},
        dataType: 'json',
        crossDomain: true,
        success: function(res){
            var data = res.data;
            data.forEach(function(item){
                if(item['demandTypeCnt'] == 0){
                    if(item.demandTypeIndex == 1){
                        // $('.demand-query-type > span').eq(2)
                    }
                }
            })
        }
    });

    //列表数据获取及分页;
    getDemandList();
    function getDemandList(){
        // typeIndex = typeIndex ? typeIndex : 0;
        $.ajax({
            url: '/demandHall/getDemandHall',
            type: 'post',
            data: {
                cp: curPage,
                rows: pageSize,
                demandTypeIndex: typeIndex
            },
            dataType: 'json',
            crossDomain: true,
            success: function(res){
                if(res.status === 1){
                    $('.demand-list').empty();
                    if(res.data.total == 0){
                        $('.no-data-wrap').show();
                        $('#pagination').css({'display': 'none'});
                    }else{
                        $('.no-data-wrap').hide();
                        $('#pagination').css({'display': 'block'});
                    }

                    res.data.data.forEach(function(item, index){
                        var demandType, typeInit;
                        if(item.demandTypeIndex === 1){
                            demandType = '期望作者数';
                            typeInit = '个';
                        }if(item.demandTypeIndex === 3){
                            demandType = '期望账号数';
                            typeInit = '个';
                        }if(item.demandTypeIndex === 4){
                            demandType = '征稿数量';
                            typeInit = '篇';
                        }

                        $('<li>' +
                            '<div class="item-status"></div>' +
                            '<div class="demand-list-item-title">' +
                            '<div class="demand-list-title-top">' +
                            '<span class="demand-title-type">'+ item.enumDemandType +'</span>' +
                            '<span class="demand-title-name">'+ item.demandName +'</span>' +
                            '<span><span>￥'+ thousandCharacter(item.budgetMoney) +'</span><span class="demand-list-unit">&nbsp;<span style="color:#a9adb0">/</span>篇</span></span>' +
                            '</div>' +
                            '<div class="demand-list-title-bottom">' +
                            '<span>'+ demandType +':<span class="title-bottom-num">'+ item.expectNum +'</span>'+ typeInit +'</span>' +
                            '<span class="title-bottom-time">报名剩余:' +
                            '</span>' +
                            '<span class="user-option"></span>' +
                            '</div>' +
                            '<div class="cut-off"></div>' +
                            '</div>' +
                            '<div class="demand-list-item-content">' +
                            '</div>' +
                            '<div class="demand-list-item-tag" style="position:relative;top:-6px;">' +
                            '</div>' +
                            '</li>').appendTo($('.demand-list'));

                            if(item.contentForms){
                                item.contentForms.split(',').forEach(function(item){
                                    $('<span>'+ item +'</span>').appendTo($('.demand-list-item-tag').eq(index));
                                })
                            }

                            if(item.demandStatusIndex > 3){
                                $('<span>已完成</span>').appendTo($('.item-status').eq(index));
                                $('.item-status').eq(index).css({'background': '#CDD1D5'});
                            }
                            else{
                                $('<span>招募中</span>').appendTo($('.item-status').eq(index))
                            }

                            if(item.endTime){
                                var ary = item.endTime.match(/(\d+)/g);
                                $('<span class="time-active">'+ ary[0] +'</span>天' +
                                    '<span class="time-active">'+ ary[1] +'</span>小时' +
                                    '<span class="time-active">'+ ary[2] +'</span><span>分钟</span>')
                                    .appendTo($('.title-bottom-time').eq(index));
                            }

                            if(item.demandTypeIndex === 1 || item.demandTypeIndex === 3){
                                var src = '/demandHall/demand_author1_' + item.demandTypeIndex +'_'+ item.demandSn + '.html';
                                var text = item.demandStatusIndex > 3 ? '查看详情' : '我要报名';

                                if(item.demandTypeIndex === 1){
                                    $('.demand-title-type').eq(index).css({'color': '#4895E7', 'borderColor': '#4895E7'});
                                }
                                if(item.demandTypeIndex === 3){
                                    $('.demand-title-type').eq(index).css({'color': '#9B76F8', 'borderColor': '#9B76F8'});
                                }

                                $('.demand-list-unit').eq(index).hide();
                                $('<a style="color:#fff;" target="_blank" href='+ src +'><span>'+ text +'</span></a>').appendTo($('.user-option').eq(index));
                                if(item.remark){
                                    var formatRemark = item.remark.slice(0, 80);
                                    $('<span>'+ formatRemark +'&nbsp;&nbsp;<a target="_blank" href='+ src +' class="look-more">' +
                                        '查看更多<i class="iconfont icon-fanhui" style="color:#4895E7;font-size: 12px;position: relative;top: -1px;left: -1px;"></i></a></span>')
                                        .appendTo($('.demand-list-item-content').eq(index));
                                }
                            }
                            else{
                                var src = '/demandHall/demand_work1_'+ item.demandSn + '.html';
                                var text = item.demandStatusIndex > 3 ? '查看详情' : '我要投稿';

                                $('<a style="color:#fff;" target="_blank" href='+ src +'><span>'+ text +'</span></a>')
                                    .appendTo($('.user-option').eq(index));
                                if(item.remark){
                                    var formatRemark = item.remark.slice(0, 80);
                                    $('<span>'+ formatRemark +'&nbsp;&nbsp;<a target="_blank" href='+ src +' class="look-more">' +
                                        '查看更多<i class="iconfont icon-fanhui" style="color:#4895E7;font-size: 12px;position: relative;top: -1px;left: -1px;"></i></a></span>')
                                        .appendTo($('.demand-list-item-content').eq(index));
                                }
                            }


                    });

                    //点击标题跳转详情页;
                    $('.demand-title-name').each(function(index, item){
                        $(item).on('click', function(){
                            $('.user-option a span').eq(index).trigger('click');
                        })
                    })
                    totalPage = res.data.total;

                    var paginationWrapDom = $('.pagination-wrap');
                    paginationWrapDom.show();
                    $('#pagination').pagination(totalPage, {
                        current_page: curPage,//当前页
                        items_per_page: pageSize,
                        prev_text: prev_text,
                        next_text: next_text,
                        num_display_entries: 5,
                        callback: function (currPage, jg) {
                            curPage = currPage;
                            $("HTML,body").scrollTop(0);
                            getDemandList();
                        }
                    });
                }
            }
        });
    }

    //跳转到买卖家中心的帮助;
    $('ul .link-help').eq(0).on('click', function(){
        window.open(centerUrl + 'helpcenter/platformguide/guide/sellerguide#originalSolicitation');
    });
    $('ul .link-help').eq(1).on('click', function(){
        window.open(centerUrl + 'helpcenter/platformguide/guide/sellerguide#customCreation');
    });
    $('ul .link-help').eq(2).on('click', function(){
        window.open(centerUrl + 'helpcenter/platformguide/guide/sellerguide#marketingDistribution');
    });

    //千分符
    function thousandCharacter(val){
        var reg=/(\d{1,3})(?=(\d{3})+(?:$|\.))/g;
        return val ? val.toString().replace(reg, '$1,') : '--';
    }
});