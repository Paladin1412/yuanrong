/*
* author: wangZhu;
* version: 1.5;
* */

$(function(){

    $('.origin-item i').text('原创征稿');
    $('<li><span>1</span><span>投稿报名</span></li>' +
        '<li><span>2</span><span>提交作品</span></li>' +
        '<li><span>3</span><span>平台审核</span></li>' +
        '<li><span>4</span><span>买家挑选确认</span></li>' +
        '<li><span>5</span><span>买家支付</span></li>' +
        '<li><span>6</span><span>平台结算</span></li>').appendTo('.process-guide ul');


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
    $('.process-guide ul').on('click', function(){
        window.open(centerUrl + 'helpcenter/platformguide/guide/sellerguide#originalSolicitation');
    });

    var Imgs = [
        {type: 0, url: '/static/images/article/bg_image.png'},
        {type: 1, url: '/static/images/article/doc.png'},
        {type: 2, url: '/static/images/article/ppt.png'},
        {type: 3, url: '/static/images/article/excel.png'},
        {type: 4, url: '/static/images/article/txt.png'},
        {type: 5, url: '/static/images/article/pdf.png'},
        {type: 6, url: '/static/images/article/zip.png'}
    ];
    var sn = $('#recId').val();

    function type(item) {
        if (/\.(txt|html)/.test(item)) {
            return Imgs[4].url;
        } else if (/\.(doc|docx|docm)$/.test(item)) {
            return Imgs[1].url;
        } else if (/\.(jpg|jpeg|png|bmp|gif)$/.test(item)) {
            return Imgs[0].url;
        } else if (/\.(ppt|pptx|pptm|potm|ppsm)$/.test(item)) {
            return Imgs[2].url;
        } else if (/\.(xlsx|xls)$/.test(item)) {
            return Imgs[3].url;
        } else if (/\.(zip|rar)$/.test(item)) {
            return Imgs[6].url;
        } else if (/.pdf/.test(item)) {
            return Imgs[5].url;
        }
    }

    //绑定需求详情数据;
    commonFun.commonAjax('/demandHall/getByDemandSn', 'post', { demandSn: sn }, demandSuccess);

    //请求成功后的回调;
    function demandSuccess(res){
        if(res.status === 1){
            var data = res.data[0];

            demandRender(data);
            //在线咨询调用企点客服;
            // $('.online-service').on('click', function(e){
            //     $('#qq-media').trigger('click');
            // });

            if(data.attachment){
                data.attachment.split(',').forEach(function (item,index) {
                    $('<dl class="imgDl">' +
                        '<dt class="imgType"></dt>' +
                        '<dd class="imgHtml">'+ item.slice((item.lastIndexOf('/') + 1), item.length) +'</dd>' +
                        '<dd class="download">' +
                        '<div>' +
                        '<p class="hide thumbnail">预览</p>' +
                        '<a target="_blank" class="hide pdfTxt" href="'+ item +'">预览</a>' +
                        '<a class="hide down" href="'+ item +'">下载</a>' +
                        '</div>' +
                        '</dd>' +
                        '</dl>').appendTo($('.attachment'));

                    var preview = $('.container-demand-preview');
                    var img = $('<img>');
                    if(type(item)==Imgs[0].url){
                        img.attr('src',item+'?imageView2/1/w/90/h/67');
                        preview.children('img').attr('src',item+'?imageView2/1/w/90/h/67');
                    }else {
                        img.attr('src',type(item));
                    }
                    img.prependTo($('.imgType').eq(index));
                    // 根据类型展示预览或下载
                    if((/.pdf|.txt|.html/).test(item)){
                        $('.pdfTxt').eq(index).removeClass('hide').siblings().addClass('hide');
                    }else if(type(item)==Imgs[0].url) {
                        $('.thumbnail').eq(index).removeClass('hide').siblings().addClass('hide');
                    }else {
                        $('.down').eq(index).removeClass('hide').siblings().addClass('hide');
                    }
                    // 图片预览
                    var clientWidth = document.body.clientWidth*3/4;
                    $('.thumbnail').eq(index).click(function () {
                        $('.container-demand-preview img').attr('src',item)
                        $('.container-demand-preview img').css({'max-width':clientWidth+'px'})
                        preview.removeClass('hide');
                    });
                    preview.click(function() {
                        $(this).addClass('hide');
                    })
                })
            }
        }
    }

    //页面数据的渲染;
    function demandRender(data){
        //步骤条数据绑定;
        if(data.demandStatusIndex > 3){
            $('.container-left-title > div').text('已完成').css({'background': '#CDD1D5'});

            $('.container-left-step li').eq(0).css({'background': 'none'});
            $('.container-left-step li:odd').addClass('step-active-4').find('div > span').addClass('step-active-2');
            $('.container-left-step li:odd').css({ 'border-right': '95px solid #97C1EF' });
            $('.container-left-step li:even').find('div > span').addClass('step-active-2');
            $('.container-left-step li:even > span').addClass('step-active-3');
            $('.container-left-step li:last').css({'background': '#E8F2FC'}).find('div > span').text('').addClass('step-icon');

            $('.submit-pay span').css({'cursor': 'not-allowed', 'background': 'rgb(202, 205, 210)'});
        }else{
            $('.container-left-title > div').text('招募中');

            $('.submit-pay a').on('click', function(){
                var getLoginFun = function(isLogin){
                    if(isLogin === 'Y'){
                        var href = '/demandHall/demand_work2_'+ sn +'.html';
                        $('.submit-pay a').attr('href', href)
                    }else{
                        // $('<a href=""><span>提交作品</span></a>').appendTo($('.submit-pay'));
                    }
                };
                getUserLoginInfo(getLoginFun, 'showpop', '')
            });
        }

        //需求标题栏数据绑定;
        $('.container-left-title > span').eq(1).text(data.demandName.slice(0,30));
        $('.container-demand-info li').eq(0).find('div').html('<span>￥' + data.budgetMoney +'</span><span class="num-unit">&nbsp;/篇');
        $('.container-demand-info li').eq(2).find('div').text(data.expectNum);

        if(data.cnt_num){
            $('.container-demand-info li').eq(4).find('div').text(data.cnt_num);
        }else{
            $('.container-demand-info li').eq(4).find('div').text(0);
        }

        if(data.endTime){
            var ary = data.endTime.match(/(\d+)/g);
            $('<span>'+ ary[0] +'</span><span style="font-size:12px">天&nbsp;&nbsp;</span>'+ ary[1] +'<span style="font-size:12px">小时&nbsp;&nbsp;</span>'+ ary[2] +'<span style="font-size:12px">分钟</span>').appendTo($('.container-demand-info li').eq(6).find('div'));
        }

        //底部需求预算数据绑定;
        $('.submit-pay > div').html('<i>￥' + data.budgetMoney +'<span class="num-unit" style="color:#82868A">&nbsp;<span style="color:#a9adb0">/</span>篇</span></i>');

        // 字数要求
        var requireWordNum = data.requireWordNum?data.requireWordNum.replace('_','-'):'';

        //需求详情数据绑定;
        $('<li>' +
            '<span>表现形式 :</span>' +
            '<span>'+ data.contentForms +'</span>' +
            '</li>' +
            '<li class="list-item-word">' +
            '<span>字数要求 :</span>' +
            '<span>'+  requireWordNum +'字</span>' +
            '</li>' +
            '<li class="create-require">' +
            '<span>创作要求 :</span>' +
            '<span>'+ data.remark +'</span>' +
            '</li>' +
            '<li class="list-item-example">' +
            '<span>参考样稿 :</span>' +
            '<span><a target="_blank" style="color:#4895E7" href="'+ data.referURL +'">'+ data.referURL +'</a></span>' +
            '</li>' +
            '<li class="attachment" style="overflow: hidden;">' +
            '<span style="float:left;line-height:67px;">征稿素材 :</span>' +
            '</li>').appendTo($('.container-demand-detail-list ul'));

        // 字数要求
        if(data.contentForms == '文章'){
            if((!data.requireWordNum || data.requireWordNum == '_')){
                $('.list-item-word > span').eq(1).text('不限');
            }else{
                // $('.list-item-word').hide();
                var ary = data.requireWordNum.split('_');
                if(ary[1] == ''){
                    $('.list-item-word > span').eq(1).text('不少于'+ ary[0] +'字');
                }if(ary[0] == ''){
                    $('.list-item-word > span').eq(1).text('不多于'+ ary[1] +'字');
                }
            }
        }else{
            $('.list-item-word').hide();
        }


        //参考样稿;
        if(!data.referURL){
            $('.list-item-example').hide();
        }

        if(!data.attachment){
            $('.attachment').hide();
        }
    }
});


