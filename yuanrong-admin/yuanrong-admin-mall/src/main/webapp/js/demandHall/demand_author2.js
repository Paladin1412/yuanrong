$(function () {
    // 从地址栏中取参数
    // 需求类型，是内容定制，还是营销分发
    var param = $('#authorId').val().split('_');
    var demandType = param[0];
    // 订单号
    var demandSn = param[1].toString();

    var cp = 1,rows = 10;



    // 头部文字修改
    var containerTitle =  $('.container-left-title span').eq(0);
    // 流程文字修改
    var flowfont = $('.process-guide .origin-item i');
    if(demandType==1){
        containerTitle.html('内容定制');
        flowfont.html('内容定制');
        containerTitle.css({'border':'1px solid #4895E7 ','color':'#4895E7 '})
    }else if(demandType==3){
        containerTitle.html('营销分发');
        flowfont.html('营销分发');
        containerTitle.css({'border':'1px solid #8481DE ','color':'#8481DE '})
    }

    //步骤条样式修改
    $('.container-left-step li').eq(0).removeClass('step-active-1');
    $('.container-left-step li:eq(0) > span').removeClass('step-active-3');
    $('.container-left-step li').eq(0).find('.step-active-2').text('');
    $('.container-left-step li').eq(0).find('.step-active-2').addClass('step-icon');
    $('.container-left-step li').eq(1).addClass('step-active-5');
    $('.container-left-step li').eq(2).addClass('step-active-1');
    $('.container-left-step li').eq(2).find('div span').addClass('step-active-2');
    $('.container-left-step li:eq(2) > span').addClass('step-active-3');
    $('.container-left-step li').eq(3).addClass('step-active-4');


    $('.project-adviser ul li').eq(0).on('click', function(){
        window.open(centerUrl + 'helpcenter/platformguide/guide/sellerguide#uploadingInformation ');
    });
    $('.project-adviser ul li').eq(1).on('click', function(){
        window.open(centerUrl + 'helpcenter/platformguide/guide/sellerguide#makeMonery ');
    });
    $('.project-adviser ul li').eq(2).on('click', function(){
        window.open(centerUrl + 'helpcenter/paymentsettlement/paymentsettlement#seller');
    });
    $('.project-adviser ul li').eq(3).on('click', function(){
        window.open(centerUrl + 'helpcenter/paymentsettlement/paymentsettlement#chargeDescription');
    });




    // 流程
    if(demandType==1){
        $('<li><span>1</span><span>参与报名</span></li>' +
            '<li><span>2</span><span>提交信息</span></li>' +
            '<li><span>3</span><span>买家确认</span></li>' +
            '<li><span>4</span><span>内容创作</span></li>' +
            '<li><span>5</span><span>买家审核确认</span></li>' +
            '<li><span>6</span><span>平台结算</span></li>').appendTo('.process-guide ul');
        $('.origin-item i').text('内容定制');

        $('.process-guide ul').on('click', function(){
            window.open(centerUrl + 'helpcenter/platformguide/guide/sellerguide#customCreation');
        });
    }else if(demandType==3){
        $('<li><span>1</span><span>参与报名</span></li>' +
            '<li><span>2</span><span>提交信息</span></li>' +
            '<li><span>3</span><span>买家确认</span></li>' +
            '<li><span>4</span><span>账号发布</span></li>' +
            '<li><span>5</span><span>买家审核确认</span></li>' +
            '<li><span>6</span><span>平台结算</span></li>').appendTo('.process-guide ul');
        $('.origin-item i').text('营销分发');
        $('.process-guide ul').on('click', function(){
            window.open(centerUrl + 'helpcenter/platformguide/guide/sellerguide#marketingDistribution');
        });
    }

    // 调取函数，获取数据
    getMyYrAuthor_content();

    // 获取标题
    commonFun.commonAjax(domain+'/demandHall/getByDemandSn','get',{demandSn:demandSn},function(res){
        if(res.status==1){
            var detailData = res.data[0];
            $('.container-left-title span').eq(1).html(detailData.demandName);
        }
    })

    // 搜索点击
    $('.detail3-btn').click(function () {
        var authorNickname = $.trim($('.product-search').val());
        cp=1;
        getMyYrAuthor_content(authorNickname);
    })
    // 状态2有创作者的情况
    function getMyYrAuthor_content(name){
        var postUrl,data;
        if(demandType==1){
            // 定制内容的接口
            postUrl = domain + '/author/getMyYrAuthorContent';
            data = {demandSn:demandSn, cp:cp, rows:rows, authorNickname:name}
        }else if(demandType==3){
            // 营销分发的接口
            postUrl = domain + '/platForm/getPlatformIPAccountByNameLikeSearch';
            data = {demandSn:demandSn, cp:cp, rows:rows, name:name}
        }
        $.ajax({
            url:postUrl,
            type:'post',
            data:data,
            success:function (res) {
                if(res.status==1){
                    var pageContent = res.data.total;
                    var author_content = res.data.data;
                    var dt3ListContent = $('.detail3-list-content');
                    var list_header = $('.detail3-list-header');
                    var containerLeftTitle = $('.container-left-title');
                    author_content.demandStatusIndex>3 ? containerLeftTitle.find('div').html('已完成').css({'background':'#CDD1D5'}): containerLeftTitle.find('div').html('招募中');
                    if(author_content&&author_content.length>0){
                        $('.container-demand-detail2-have').removeClass('hide').siblings().addClass('hide');
                        $('.no-data-wrap').hide();
                        $('.detail3-list').removeClass('hide');
                        dt3ListContent.html('');
                        $('.dt3-price').html('');
                        list_header.html('')
                        if(demandType==1){
                            $(`<li class="dt3-width2">创作者</li>
                            <li class="dt3-width3" style="width: 40%">写稿报价（元）</li>
                            <li class="dt3-width3" style="width: 20%">创作耗时（天）</li>
                            <li class="dt3-width2">操作</li>`).appendTo(list_header)
                        }else if(demandType==3){
                            $(`<li style="width: 15%;">账号</li>
                                <li style="width: 15%;">粉丝数</li>
                                <li style="width: 35%">账号报价（元）</li>
                                <li style="width: 25%">可用排期（天）</li>
                                <li style="width: 10%;">操作</li>`).appendTo(list_header)
                            $('.product-search').attr('placeholder','请输入账号名')
                        }
                        author_content.forEach(function (item,index) {
                            var isSign = item.isSign? '<span class="signUp isSignUp" style="background:none;color:#89BC62;">已报名</span>': '<span class="signUp">报名</span>' ;
                            var prices_content = item.expectOffer;
                            var bgClass = item.isSign ? 'ready-back' : '';
                            var expectOffer = item.expectOffer.length>1?'line-height':'';
                            var inputKuang = item.expectOffer.length>1?'input-kuang':'';
                            // 定制内容
                            if(demandType==1){
                                $(`<li class="fixedClear ${bgClass}">
                                    <div class="dt3-headerImg"><img src="${item.headImg}" alt="" class="headImageUrlLocal"><span id="${item.id}">${item.nickname}</span></div>
                                    <div class="dt3-price" style="width: 40%;"></div>
                                    <div class="dt3-getTime" style="width: 20%;"></div>
                                    <div class="dt3-signup">${isSign}</div>
                                </li>`).appendTo(dt3ListContent)
                            }else if(demandType==3){
                                $(`<li class="fixedClear ${bgClass}">
                                    <div class="dt3-headerImg" style="width: 15%;"><img src="${item.headImageUrlLocal}" alt="" class="headImageUrlLocal"><img src="${item.icoUrl}" class="icoUrl" alt=""><span id="${item.id}">${item.name}</span></div>
                                    <div style="width: 15%;">${item.fans}</div>
                                    <div class="dt3-price" style="width: 35%;"></div>
                                    <div class="dt3-getTime" style="width: 25%;"><span class="joinInput ${expectOffer}" style="float: left;font-size: 20px;margin-left: 10px;cursor: pointer;">+</span></div>
                                    <div class="dt3-signup" style="width: 10%;">${isSign}</div>
                                </li>`).appendTo(dt3ListContent)
                            }

                            // 创作耗时
                            if(item.usableDate){
                                if(demandType==1){
                                    $('.dt3-getTime').eq(index).html(item.usableDate);
                                }else if(demandType==3){
                                    $('.dt3-getTime span.joinInput').eq(index).addClass('hide');
                                    var usableDate;
                                    if(item.usableDate.length==15){
                                        usableDate = item.usableDate.substr(0,10);
                                    }else {
                                        usableDate = item.usableDate.replace(/_-,-_/g,',');
                                    }
                                    $(`<span>${usableDate}</span>`).appendTo($('.dt3-getTime').eq(index))
                                }
                            }else {
                                if(demandType==1){
                                    $(`<input class="authorTime" style="text-align: center" type="text" value="">`).appendTo($('.dt3-getTime').eq(index));
                                }
                            }


                            // 价格项
                            if(prices_content){
                                prices_content.forEach(function (item1,index1) {
                                    if(item1.price){
                                        // ${expectOffer}
                                        $(`<div class="fixedClear lineHeight ${expectOffer}"><span style="float: left;max-width: 59%;margin-right:16px;text-align: left; white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">${item1.priceName}</span><span style="float: left;width: 85px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">${thousandCharacter(item1.price)}</span></div>`).appendTo($('.dt3-price').eq(index));
                                    }else {
                                        $(`<div class="fixedClear lineHeight ${expectOffer}"><span style="float: left;max-width: 59%;margin-right:16px;text-align: left; white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">${item1.priceName}</span><input class="authorPrices ${inputKuang}" type="text" value=""></div>`).appendTo($('.dt3-price').eq(index));
                                    }

                                    if(demandType==3){
                                        if(!item.usableDate){
                                            $(`<div style="float: left;padding-left: 40px;" class="${expectOffer}"><input class="authorTime laydate" style="background: url(../../images/contentBank/demand-time.png) no-repeat right center;" type="text" value=""></div>`).prependTo($('.dt3-getTime').eq(index))
                                        }
                                        // 绑定日期控件
                                        layui.use('laydate', function(){
                                            var laydate = layui.laydate;
                                            lay('.laydate').each(function(){
                                                laydate.render({
                                                    elem: this
                                                    ,theme:'#4895E7'
                                                    ,type: 'date'
                                                    ,min:1
                                                    ,format: 'yyyy-MM-dd'
                                                    ,trigger: 'click'
                                                });
                                            });
                                        });
                                    }
                                })
                            }


                            // +号表示新增一个排期，加上一个时间控件，并控制到6个
                            $('.dt3-getTime span.joinInput').eq(index).click(function(){
                                $(`<div style="float: left;padding-left: 40px;" class="${expectOffer}"><input class="authorTime laydate" style="background: url(../../images/contentBank/demand-time.png) no-repeat right center;" type="text" value=""></div>`).prependTo($('.dt3-getTime').eq(index))

                                var dataLength = $(this).siblings().length;
                                if(dataLength>=6){
                                    $(this).addClass('hide')
                                }
                                layui.use('laydate', function(){
                                    var laydate = layui.laydate;
                                    lay('.laydate').each(function(){
                                        laydate.render({
                                            elem: this
                                            ,theme:'#4895E7'
                                            ,type: 'date'
                                            ,min:1
                                            ,format: 'yyyy-MM-dd'
                                            ,trigger: 'click'
                                        });
                                    });
                                });
                            })
                        })

                        // 创作耗时判断
                        if(demandType==1){
                            $('.authorTime').each(function (index, item) {
                                $(this).on('blur', function(){
                                    var value = ($('.authorTime').eq(index))[0].value;
                                    if(value){
                                        if(/^-?\d+$/.test(value)){
                                            if(value>30||value<=0){
                                                layer.msg('请填写小于等于30的正整数');
                                                ($('.authorTime').eq(index))[0].value='';
                                            }
                                        }else{
                                            layer.msg('请填写小于等于30的正整数');
                                            ($('.authorTime').eq(index))[0].value='';
                                        }
                                    }
                                })
                            })
                        }

                        // 价格项判断
                        var pricesjudge = $('.dt3-price div .authorPrices');
                        pricesjudge.blur(function () {
                            var panduanPrice = $(this)[0].value;
                            if(panduanPrice){
                                if(/^-?\d+$/.test(panduanPrice)){
                                    if(panduanPrice>999999999||panduanPrice<1){
                                        $(this)[0].value = '';
                                        layer.msg('请填写1-999999999的正整数');
                                    }
                                }else {
                                    $(this)[0].value = '';
                                    layer.msg('请填写1-999999999的正整数');
                                }
                            }

                        })

                        // 弹框出现跟关闭控制
                        var sign_up = $('.dt3-signup .signUp');
                        sign_up.click(function () {
                            if($(this)[0].innerText=='报名'){
                                var li = $(this).parent().parent();
                                var authorTime,prices,ary;
                                if(demandType==1){
                                    authorTime = li.find('.authorTime')[0].value;
                                }else if(demandType==3){
                                    ary = new Array();
                                    Array.prototype.slice.call(li.find('.dt3-getTime div .authorTime')).forEach(function (item,index) {
                                        ary.push(item.value);
                                    })
                                    authorTime = ary.some(function (item) {  return item != (null || ''); })

                                    $('.confirm_info').html('买家确认使用平台会进行通知。之后才会进行需求细节沟通，账号进行发布。')
                                }
                                // 价格项判断是否为空
                                prices = li.find('.dt3-price div');
                                // 将html集合转变为数组
                                var isNullPrice = Array.prototype.slice.call(prices).every(function(item){
                                    return item.children[1].value != (null || '');
                                });
                                if(!authorTime && !isNullPrice){
                                    if(demandType==1){
                                        layer.msg('创作时间与价格项都不能为空');
                                    }else if(demandType==3){
                                        layer.msg('账号报价与可用排期都不能为空');
                                    }
                                }else if(!isNullPrice){
                                    layer.msg('价格项不为空');
                                }else if(!authorTime){
                                    if(demandType==1){
                                        layer.msg('创作时间不能为空');
                                    }else if(demandType==3){
                                        layer.msg('可用排期不能为空');
                                    }
                                }else {
                                    $('.layer-open-bg').removeClass('hide');
                                    // 获取数据
                                    // 创作者名称
                                    var headerImgs = $('.detail3-list-content1 .dt3-headerImg');
                                    var getPricess = $('.detail3-list-content1 .getPrices');

                                    headerImgs.html((li.find('.dt3-headerImg').children()).clone());
                                    getPricess.html('');
                                    // 弹窗价格项
                                    for(var i = 0;i < prices.length;i++){
                                        getPricess.append($(`<div style="line-height: 38px;margin-left: 5px;"><span>${prices[i].innerText}</span><span style="margin-left: 5px;">${prices[i].children[1].value}元</span></div>`));
                                    }
                                    // 创作时间
                                    var authorTime1 = $('.detail3-list-content1 .authorTime1');
                                    authorTime1.html('');
                                    if(demandType==1){
                                        headerImgs.css({'width':'30%'});
                                        authorTime1.append($(`<span>${authorTime +'天'}</span>`));
                                        authorTime1.css({'width':'15%'});
                                    }else if(demandType==3){
                                        for(var i = 0;i < ary.length;i++){
                                            authorTime1.append($(`<span style="line-height: 38px;display: block;">${ary[i]}</span>`));
                                        }
                                    }
                                }
                            }
                        })

                        // 弹框取消按钮
                        $('.dt3-cancel').click(function () {
                            $('.layer-open-bg').addClass('hide');
                            $('.authorTime').val('');
                            $('.authorPrices').val('');
                        })
                        // 弹框关闭按钮
                        $('.dt3-close').click(function () {
                            $('.layer-open-bg').addClass('hide');
                            $('.authorTime').val('');
                            $('.authorPrices').val('');
                        })
                        //确认投稿
                        $('.dt3-confirm').off().click(function () {
                            var postUrl ;
                            var parent = $(this).parent().parent();
                            if(demandType==1){
                                postUrl = domain + '/order/saveContent';
                                // 创作耗时
                                var usableDate1 = parent.find('.authorTime1').text();
                                var usableDate = usableDate1.match(/(\d+)/g)[0];
                            }else if(demandType==3){
                                postUrl = domain + '/order/saveMarker';
                                var usableDate1 = parent.find('.authorTime1').children();
                                var paiqiArray = Array.prototype.slice.call(usableDate1);
                                var paiqistring = '';
                                paiqiArray.forEach(function (item,index) {
                                    if(item.innerHTML){
                                        var paiqi = item.innerHTML;
                                        paiqistring += paiqi.substring(0,paiqi.length)+',';
                                    }
                                })
                                var usableDate = paiqistring.slice(0,paiqistring.length-1)
                            }
                            //recid
                            var referId = parent.find('.dt3-headerImg span')[0].id;

                            // 报价数组
                            var offerProject1 = parent.find('.getPrices div');
                            var getprices = Array.prototype.slice.call(offerProject1);
                            var string = '';
                            getprices.forEach(function (item,index) {
                                var str = item.children[1].innerHTML;
                                string += str.substring(0,str.length-1)+'_-,-_';
                            })
                            var offerProject = string.slice(0,string.length-5);
                            $.ajax({
                                url:postUrl,
                                type:'post',
                                data:{
                                    usableDate:usableDate,
                                    demandSn:demandSn,
                                    offerProject:offerProject,
                                    referId:referId
                                },
                                success:function(res){
                                    if(res.status==1){
                                        $('.container-demand-detail2-success').removeClass('hide').siblings().addClass('hide');
                                    }else {
                                        layer.msg(res.msg)
                                        $('.layer-open-bg').addClass('hide')
                                    }
                                }
                            })
                        })

                        // pagination分页
                        $('.pagination-wrap').pagination(pageContent,{
                            current_page: cp,//当前页
                            items_per_page: rows,
                            prev_text: prev_text,
                            next_text: next_text,
                            num_display_entries:5,
                            callback:function (currPageNmu){
                                cp = currPageNmu;  // 当前页数
                                getMyYrAuthor_content(name);
                            }
                        });

                    }else if(author_content.length==0&&!name){
                        if(centerUrl.indexOf(document.referrer) > -1){
                            // layer.msg('创作者添加成功, 正在等待审核...');
                            layer.open({
                                content: '创作者添加成功, 正在等待审核...'
                                ,title: '提示'
                                ,btn: [ '查看创作者','需求列表']
                                ,btnAlign: 'c'
                                ,yes: function(index, layero){
                                    location.href=centerUrl+'seller/sourceManage/author/authorList'
                                }
                                ,btn2: function(index, layero){
                                    location.href='/demandHall/demand_hall.html'
                                }
                            });
                        }


                        // 没有创作者的情况
                        $('.container-demand-detail2-no').removeClass('hide').siblings().addClass('hide');
                        if(demandType==1){
                            $('.detail2-btn').click(function () {
                                // window.open(centerUrl + 'seller/sourceManage/author/authorEdit/0');
                                location.href=centerUrl + 'seller/sourceManage/author/authorEdit/0'

                            })
                        }else if(demandType==3){
                            $('.container-demand-detail2-no .detail2-warn p').html('目前您的账户里还没有账号')
                            $('.detail2-btn').html('添加账号》');
                            $('.detail2-btn').click(function () {
                                // window.open(centerUrl + 'seller/sourceManage/account/accountAdd');
                                location.href = centerUrl + 'seller/sourceManage/account/accountAdd';
                            })
                        }
                    }else if(author_content.length==0&&name){
                        $('.detail3-list').addClass('hide');
                        $('.no-data-wrap').show();
                    }
                }else{
                    res.msg?layer.msg(res.msg):layer.msg(JSON.parse(res).msg);
                }
            }
        })
    }


    //千分符
    function thousandCharacter(val){
        var reg=/(\d{1,3})(?=(\d{3})+(?:$|\.))/g;
        return val ? val.toString().replace(reg, '$1,') : '--';
    }

})