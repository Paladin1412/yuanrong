$(function () {
    // 需求类型，是内容定制，还是营销分发
    var param = $('#authorId').val().split('_');
    var demandType = param[0];
    // 订单号
    var demandSn = param[1].toString();

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
        $('.demandType3').removeClass('hide');
        $('.demandType1').addClass('hide')
    }
    $('.container-bottom').removeClass('hide');
    $('.container-left div .container-demand-detail1' ).removeClass('hide');

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



    // 调取函数，获取数据
    getByDemandSn();
    // 状态1
    function getByDemandSn() {
        var Imgs = [
            {type:0,url:'/static/images/article/bg_image.png'},
            {type:1,url:'/static/images/article/doc.png'},
            {type:2,url:'/static/images/article/ppt.png'},
            {type:3,url:'/static/images/article/excel.png'},
            {type:4,url:'/static/images/article/txt.png'},
            {type:5,url:'/static/images/article/pdf.png'},
            {type:6,url:'/static/images/article/zip.png'}
        ];

        // 判断attachment应该展示什么图片
        function type(item) {
            if(/\.(txt|html)$/.test(item)){
                return Imgs[4].url;
            }else if(/\.(doc|docx|docm)$/.test(item)){
                return Imgs[1].url;
            }else if(/\.(jpg|jpeg|png|bmp|gif)$/.test(item)){
                return Imgs[0].url;
            }else if(/\.(ppt|pptx|pptm|potm|ppsm)$/.test(item)){
                return Imgs[2].url;
            }else if(/\.(xlsx|xls)$/.test(item)){
                return Imgs[3].url;
            }else if(/\.(zip|rar)$/.test(item)){
                return Imgs[6].url;
            }else if(/.pdf/.test(item)){
                return Imgs[5].url;
            }
        }

        // 需求详情接口
        var postUrl = domain + '/demandHall/getByDemandSn';
        // 需求单号   ----参数
        $.ajax({
            url:postUrl,
            type:'post',
            data: {
                demandSn:demandSn
            },
            success:function(res){
                if(res.status==1){
                    var detailData = res.data[0];
                    var container_info = $('.container-demand-info');
                    var containerLeftTitle = $('.container-left-title');
                    containerLeftTitle.find('span').eq(1).html(detailData.demandName);
                    detailData.demandStatusIndex>3 ? containerLeftTitle.find('div').html('已完成').css({'background':'#CDD1D5'}): containerLeftTitle.find('div').html('招募中');

                    var ary = detailData.endTime.match(/(\d+)/g);
                    console

                    $(`<ul>
                        <li>
                            <div>￥${thousandCharacter(detailData.budgetMoney)}</div>
                            <span><i class="icon-yingbi iconfont"></i>招募预算</span>
                        </li>
                        <li class="line"></li>
                        <li>
                            <div>${detailData.expectNum}</div>
                            <span><i class="icon-renyuan iconfont"></i><span class="expectNum">期望作者数</span><span class="num-unit">&nbsp;(个)</span></span>
                        </li>
                        <li class="line"></li>
                        <li>
                            <div>${detailData.cnt_num?detailData.cnt_num:0}</div>
                            <span><i class="icon-renyuan iconfont"></i><span class="join-people">报名人数</span><span class="num-unit">&nbsp;(个)</span></span>
                        </li>
                        <li class="line"></li>
                        <li>
                            <div><span>${ary[0]}</span><span style="font-size:12px">天&nbsp;&nbsp;</span>${ary[1]}<span style="font-size:12px">小时&nbsp;&nbsp;</span>${ary[2]}<span style="font-size:12px">分钟</span></div>
                            <span><i class="icon-shijian iconfont"></i>剩余时间</span>
                        </li>
                    </ul>`).appendTo(container_info)
                    if(demandType==3){
                       $('.expectNum').html('期望账号数');
                       $('.join-people').html('报名账号数');
                    }

                    $('.submit-pay div').html('￥'+thousandCharacter(detailData.budgetMoney));

                    // 详情
                    var details_content;
                    if(demandType==1){
                        // 定制内容
                        details_content = $(`<ul>
                                                <li class="create-require">
                                                    <span>创作要求 :</span>
                                                    <span>使用场景：${detailData.scenes}<br/>内容形式：${detailData.contentForms}<br/>内容领域：${detailData.yrCategory}</span>
                                                </li>
                                                <li class="create-require demandMark">
                                                    <span>需求描述 :</span>
                                                    <span>${detailData.remark}</span>
                                                </li>
                                                <li class="attachment">
                                                    <span style="float: left;line-height: 67px;">征稿素材 :</span>
                                                </li>
                                            </ul>`)
                    }else if(demandType==3){
                        //营销分发
                        details_content = $(`<ul>
                                                <li class="create-require">
                                                    <span>平台 :</span>
                                                    <span>${detailData.platformName}</span>
                                                </li>
                                                <li class="create-require">
                                                    <span>粉丝数 :</span>
                                                    <span>${detailData.fans.replace('_','-')}万</span>
                                                </li>
                                                <li class="create-require">
                                                    <span>推广时间 :</span>
                                                    <span>${detailData.spreadTime}</span>
                                                </li>
                                                <li class="create-require demandMark">
                                                    <span>需求描述 :</span>
                                                    <span>${detailData.remark}</span>
                                                </li>
                                                <li class="attachment">
                                                    <span style="float: left;line-height: 67px;">征稿素材 :</span>
                                                </li>
                                            </ul>`)
                    }
                    if(details_content){
                        details_content.appendTo($('.container-demand-detail-list'))
                    }
                    // 需求描述
                    detailData.remark?detailData.remark:$('.demandMark').addClass('hide')
                    // 征稿素材
                    if(detailData.attachment){
                        detailData.attachment.split(',').forEach(function (item,index) {
                            $(`<dl class="imgDl">
                            <dt class="imgType"></dt>
                            <dd class="imgHtml">${item.slice(item.lastIndexOf('/')+1,item.length)}</dd>
                            <dd class="download">
                                <div>
                                    <p class="hide thumbnail">预览</p>
                                    <a class="hide pdfTxt" href="${item}" target="_blank">预览</a>
                                    <a class="hide down" href="${item}" target="_blank">下载</a>
                                </div>
                            </dd>
                        </dl>`).appendTo($('.attachment'))

                            // 创建img标签,如果类型是图片展示缩略图，其他类型展示本地图片
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
                            })
                            preview.click(function() {
                                $(this).addClass('hide')
                            })
                        })
                    }else {
                        $('.attachment').addClass('hide')
                    }


                    // 点击我要报名跳到状态2
                    if(detailData.demandStatusIndex>3){
                        $('.submit-pay > a > span').css({'cursor':'not-allowed','background':'#cacdd2'})
                        $('.submit-pay > a > span').css({'cursor':'not-allowed'})

                        // 已完成的进度条样式
                        $('.container-left-step li').eq(0).css({'background': 'none'});
                        $('.container-left-step li:odd').addClass('step-active-4').find('div > span').addClass('step-active-2');
                        $('.container-left-step li:odd').css({ 'border-right': '47px solid #97C1EF' });
                        $('.container-left-step li:even').find('div > span').addClass('step-active-2');
                        $('.container-left-step li:even > span').addClass('step-active-3');
                        $('.container-left-step li:last').css({'background': '#E8F2FC'}).find('div > span').text('').addClass('step-icon');
                    }
                    $('.submit-pay > a > span').click(function () {
                        if(detailData.demandStatusIndex>3){
                            layer.msg('报名已结束，请选其他需求报名！')
                        }else {
                            var getLoginFun = function(isLogin){
                                if(isLogin === 'Y'){
                                    $('.submit-pay a').attr('href','/demandHall/demand_author2_'+ demandType +'_'+ demandSn +'.html')
                                }
                            };
                            getUserLoginInfo(getLoginFun, 'showpop', '')
                        }
                    })

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