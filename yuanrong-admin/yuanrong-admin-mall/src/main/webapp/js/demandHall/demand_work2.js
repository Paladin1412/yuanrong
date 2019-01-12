/*
* author: wangZhu;
* version: 1.5;
* */
$(function(){
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

    $('.container-left-title > div').text('招募中');

    $('.origin-item i').text('原创征稿');
    $('<li><span>1</span><span>投稿报名</span></li>' +
        '<li><span>2</span><span>提交作品</span></li>' +
        '<li><span>3</span><span>平台审核</span></li>' +
        '<li><span>4</span><span>买家挑选确认</span></li>' +
        '<li><span>5</span><span>买家支付</span></li>' +
        '<li><span>6</span><span>平台结算</span></li>').appendTo('.process-guide ul');

    $('.project-adviser ul li').eq(0).on('click', function(){
        window.open(centerUrl + 'helpcenter/platformguide/guide/sellerguide#uploadingInformation');
    });
    $('.project-adviser ul li').eq(1).on('click', function(){
        window.open(centerUrl + 'helpcenter/platformguide/guide/sellerguide#makeMonery');
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

    var messageType = 0;

    //绑定需求详情数据;
    var sn = $('#recId').val(); //location.search.split('=')[1];
    commonFun.commonAjax('/demandHall/getByDemandSn', 'post', { demandSn: sn }, demandSuccess);

    //请求成功后的回调;
    function demandSuccess(res){
        if(res.status === 1){
            var data = res.data[0];

            $('.container-left-title > span').eq(1).text(data.demandName.slice(0, 30));
        }
    }

    //tab切换
    $('.container-demand-product ul li:lt(2)').each(function( index, item){
        $(this).on('click', function(){
            $(this).addClass('product-active').siblings().removeClass('product-active');
            $(this).parent().parent().find('.product-item').eq(index).addClass('show').siblings().removeClass('show');
        })
    });

    /*
    * 新增作品tab页的表单验证及提交
    * */

    //创作者下拉框数据绑定;
    var noAuthorFlag = false;
    commonFun.commonAjax(domainCenter + '/author/getMyYrAuthor', 'post', { }, authorSuccess);
    function authorSuccess(res){
        var data = res.data;

        if(res.status === 1){
            if(data.length === 0){
                noAuthorFlag = true;
                $('.create-product-author > span > span').hide();
                // commonFun.commonAjax(domainCenter + '/author/getMyYrAuthor', 'post', { }, authorSuccess);
            }else{
                data.forEach(function(item){
                    item.name = item.value;
                    item.value = item.id;
                });
                layui.formSelects.data('select-author', 'local', {
                    arr: res.data
                });
                layui.formSelects.value('select-author', [data[0].value]);
                layui.formSelects.on('select-author', function(id, vals, val, isAdd, isDisabled){
                    if(val){
                        handleAuthor(true);
                    }
                    return true;
                });
            }
        }
    }

    //radio标签选中切换;
    $('.create-product-form input').each(function(item){
        $(this).on('click', function() {
            if($(this).prop('checked')){
                $(this).prev().addClass('radio-inner-checked');
                $(this).parent().siblings().find('span').removeClass('radio-inner-checked');}
        })
    });

    //编辑器初始化;
    window.UEDITOR_HOME_URL = "http://up-z1.qiniup.com/"
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    var ue = UE.getEditor('product-container', {
        autoFloatEnabled: false,
        autoClearinitialContent:true,
        zIndex: 0,
        initialFrameHeight: 160,
        forecolor: '#606266'
    });
    ue.ready(function() {
        $.ajax({
            type: 'get',
            url: '/upload/uploadToken',
            async: false,
            success: function(res){
                // $('<input name="token" type="text" value="">').appendTo($('#edui1_iframeupload form'))
                UE.Editor.prototype.getActionUrl = function(action) {
                    if (action == 'uploadvideo') {
                        return 'http://up-z1.qiniup.com?token='+ JSON.parse(res).uptoken +'&key=';
                    }else{
                        return this._bkGetActionUrl.call(this, action);
                    }
                }
            }
        });
        ue.addListener("afterUpfile", function(res){
        });
        ue.blur(function(){
            handleContent();
        });
        $('#edui83_body').off().on('click', function(){
            $('#specharsTab').hide();
        })
    });
    ue.commands['simpleuploadimg'] = {
        execCommand: function() {
            messageType = 1;
            $('.new-product-message').show();
            return true;
        }
    };

    //导入word文档;
    // layui.use('upload', function(){
    //     var upload = layui.upload;
    //     upload.render({
    //         elem: '#insert-word'
    //         ,url: domainCenter + '/author/batchSaveWord'
    //         ,accept: 'file' //普通文件
    //         ,done: function(res){
    //             ue.setContent(res.msg, true);
    //         }
    //     });
    // });

    $('.insert-word').on('click', function(){
        $('#file').trigger('click');
    });

    $('#file').change(function(){
        $('.insert-word .load-icon').css({'display': 'inline-block'})

        var file = this.files[0];
        // name = file.name;
        // size = file.size;
        // type = file.type;

        if(!/doc|docx/.test(file.name)){
            layer.msg('请上传word文件!');
            return false;
        }
        var formData = new FormData($('form')[0]);
        $.ajax({
            url: domainCenter + '/author/batchSaveWord',
            type: 'POST',
            crossDomain: true,
            xhrFields: {
                withCredentials: true
            },
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function(res){
                $('.insert-word .load-icon').css({'display': 'none'});
                if(res.status === 1){
                    // var contentVal = ue.getContent();
                    ue.execCommand('cleardoc');
                    // contentVal = contentVal.replace(/(&nbsp; &nbsp; &nbsp; &nbsp;)/g,"")
                    // console.log(contentVal);
                    // ue.setContent(contentVal, true);
                    if(res.data.html){
                        ue.setContent(res.data.html, true);
                    }
                }
            },
            error: function(){
                $('.insert-word .load-icon').css({'display': 'none'});
            }
        });
    });

    //七牛上传;
    initQiNiu('uploadasCardPhoto','containerQiniuCardPhoto');
    function initQiNiu(browse, container, list, start){
        var Q2 = new QiniuJsSDK();
        var uploader2 = Q2.uploader({
            runtimes: 'html5,flash,html4',
            browse_button: browse,
            container: container,
            drop_element: container,
            max_file_size: '100mb',
            flash_swf_url: '/js/plupload/Moxie.swf',
            dragdrop: true,
            chunk_size: '4mb',
            uptoken_url: '/upload/uploadToken',
            domain: qnStorageDomain,
            filters: {
                mime_types: [
                ],
                max_file_size : '20mb'
            },
            auto_start: true,
            total: 2,
            init: {
                'FilesAdded': function(up, files) {
                },
                'BeforeUpload': function(up, file) {
                },
                'UploadProgress': function(up, file) {
                },
                'FileUploaded': function(up, file, info) {
                    var keys = qnStorageDomain + JSON.parse(info).key;
                    $('.message-body-local img').attr('src', keys);

                    commonFun.commonAjax(domainCenter + '/upload/saveImage', 'post', { imgUrl: keys }, saveImageSuccess);
                    function saveImageSuccess(res){

                    }
                },
                'UploadComplete': function() {
                },
                'Key': function(up, file) {
                    var timestamp = (new Date()).getTime() + '/';
                    var key = timestamp + file.name;
                    return key;
                }
            }
        });

        $(start).on('click', function(){
            uploader2.start();
        })
    };

    //新增作品表单验证;
    //创作者表单项验证;
    var authorFlag = false;
    function handleAuthor(bool){
        var bool = '' || bool;
        var authorVal = layui.formSelects.value('select-author', 'valStr') || bool;

        if(authorVal == '' || authorVal == undefined){
            $('.author-prompt').show();
            authorFlag = false;
        }else{
            $('.author-prompt').hide();
            authorFlag = true;
        }
    }

    //作品标题表单项验证;
    var titleFlag = false;
    var titleDom = $('.create-product-title input');
    function handleTitle(){
        var titleVal = $.trim(titleDom.val());

        if(titleVal == '' || titleVal == undefined){
            $('.title-prompt').show();
            titleFlag = false;
        }else{
            $('.title-prompt').hide();
            titleFlag = true;
        }
    }
    titleDom.on('blur', handleTitle);

    //作品内容表单项验证;
    var contentFlag = false;
    function handleContent(){
        var contentVal = ue.getContent();

        if(contentVal == '' || contentVal == undefined){
            $('.content-prompt').show();
            contentFlag = false;
        }else{
            $('.content-prompt').hide();
            contentFlag = true;
        }
    }

    //作品买断价格表单项验证;
    var priceFlag = false;
    var priceDom = $('.create-product-price input');
    function handlePrice(){
        var priceVal = $.trim(priceDom.val());

        if(priceVal == '' || priceVal == undefined){
            $('.price-prompt').show();
            priceFlag = false;
        }else{
            $('.price-prompt').hide();
            priceFlag = true;
        }
    }
    priceDom.on('blur', handlePrice);

    //新增作品表单提交;
    $('.product-submit').on('click', function(){
        if(!noAuthorFlag){
            handleAuthor();
        }
        handleTitle();
        handleContent();
        handlePrice();
        var validateFlag = (authorFlag || noAuthorFlag) && titleFlag && contentFlag && priceFlag;

        if(validateFlag){
            $('.product-submit').attr('disabled', true);
            $('.product-submit i').css({'display': 'inline-block'});

            var authorVal = layui.formSelects.value('select-author', 'val').join(',');
            var titleVal = $.trim($('.create-product-title input').val());
            var contentVal = ue.getAllHtml();
            var statusVal = $('input[name="show"]:checked').val();
            var publishVal = $('input[name="status"]:checked').val();
            var priceVal = $.trim($('.create-product-price input').val());
            var coverLocalUrlVal = $('.create-product-cover > img').attr('src');
            var postData = {
                'yrAuthor.recId': authorVal,
                title: titleVal,
                localcontent: contentVal,
                coverLocalUrl: coverLocalUrlVal,
                'contentForm.Id': statusVal,
                publishStatusIndex: publishVal,
                productQuotedPrice:priceVal,
                isRepresentative: 0
            };

            new AjaxRequest({
                type: 'post',
                url: domainCenter + '/author/yRProduction_save',
                isShowLoading: true,
                loadingType: 'upload',
                param: commonFun.delEmptyData(postData),
                callBack: function(res){
                    $('.product-submit i').css({'display': 'none'});
                    if(res.status === 1){
                        // $('.product-submit').attr('disabled', false);
                        var id= res.data.productId;

                        commonFun.commonAjax('/order/save', 'post', { demandSn: sn, referId: id, orderTypeValue: 4  }, saveOrderSuccess);
                        function saveOrderSuccess(res){
                            if(res.status === 1){
                                $('.container-left-step li').eq(2).find('.step-active-2').text('').addClass('step-icon');
                                $('.container-demand-product').hide();
                                $('.success-status').show();
                                $('<li>' +
                                    '<span>1</span>' +
                                    '<span>&nbsp;'+ res.data.title +'('+ res.data.wordNum +'字'+ res.data.imgNum +'图片)</span>' +
                                    '</li>').appendTo($('.submit-list'));
                            }
                        }
                    }else{
                        $('.product-submit').attr('disabled', false);
                    }
                }
            });

        }
    });

    //作品封面上传弹出层——显示;
    $('.picture-type div').eq(1).on('click', function(){
        messageType = 0;
        $('.new-product-message').show();
    });

    //作品封面弹出层Tab切换;
    $('.new-message-title li').each(function(index, item){
        $(this).on('click', function(){
            $(this).addClass('nav-active').siblings().removeClass('nav-active');
            $('.message-body > div').eq(index).css({ 'display': 'block' }).siblings().css({ 'display': 'none' });
        })
    });

    //Tab-图片素材库数据绑定;
    commonFun.commonAjax(domainCenter + '/resources/getMyImages', 'post', { }, getImageSuccess);
    function getImageSuccess(res){
        if(res.status === 1){
            res.data.data.forEach(function(item){
                $('<li>' +
                    '<img src="'+ item.imgUrl +'">' +
                    '<div class="select-icon">' +
                    '<i class="iconfont icon-chenggong" style="font-size:30px;line-height:97px;color:#71ca4e;"></i>' +
                    '</div>' +
                    '</li>').appendTo($('.new-product-message .message-body-online > ul'));
            });

            $('.new-product-message .message-body-online > ul > li').each(function(){
                $(this).on('click', function(){
                    $(this).find('div').css({ 'display': 'block' }).parent().siblings().find('div').css({ 'display': 'none' });
                })
            })
        }
    }

    //Tab-本地上传;
    $('.add-local-image').on('click', function(){
        $('#uploadasCardPhoto').trigger('click');
    });

    //Tab-本地上传或图片素材库-确定;
    $('.new-product-message .ensure-submit').on('click', function(){
        if(messageType === 0){
            if($('.new-message-title li').eq(1).hasClass('nav-active')){
                $('.create-product-cover img').attr('src', $('.message-body-local img').attr('src'));
                $('.new-product-message').hide();
            }
            if($('.new-product-message .new-message-title li').eq(0).hasClass('nav-active')){
                $('.new-product-message .message-body-online > ul > li > div').each(function(){
                    if($(this).css('display') == 'block'){
                        $('.create-product-cover img').attr('src', $(this).prev().attr('src'));
                        $('.new-product-message').hide();
                    }
                })
            }
        }else{
            if($('.new-message-title li').eq(1).hasClass('nav-active')){
                var src = $('.message-body-local img').attr('src');
                ue.setContent('<img src="'+ src +'"/>', true);
                $('.new-product-message').hide();
            }
            if($('.new-message-title li').eq(0).hasClass('nav-active')){
                $('.message-body-online > ul > li > div').each(function(){
                    if($(this).css('display') == 'block'){
                        var src = $(this).prev().attr('src');
                        ue.setContent('<img src="'+ src +'"/>', true);
                        $('.new-product-message').hide();
                    }
                });
            }
        }

    });

    //作品封面上传弹出层——遮罩层关闭;
    messageBoxHidden('new-message-box', 'new-product-message');

    //作品封面上传弹出层——取消关闭;
    $('.new-message-box .cancel-submit').on('click', function(){
        $('.new-product-message').hide();
    });
    $('.new-product-message .close-icon').on('click', function(){
        $('.new-product-message').hide();
    });

    //正文图片弹出层——显示;
    $('.picture-type div').eq(0).on('click', function(){
        $('.new-product-message-local').show();

        //正文图片数据绑定;
        var localContent= ue.getContent();
        var imgReg = /<img.*?(?:>|\/>)/gi;
        var imgAry = localContent.match(imgReg);

        $('.new-product-message-local .message-body-online > ul > li').remove();
        if(imgAry && imgAry.length > 0){
            $('.new-product-message-local .empty-data').hide();
            imgAry.forEach(function(item){
                $('<li style="width:130px;">'+ item +'<div class="select-icon" >' +
                    '<i class="iconfont icon-chenggong" style="font-size:30px;line-height:97px;color:#71ca4e;"></i>\'' +
                    '</div>' +
                    '</li>').appendTo('.new-product-message-local .message-body-online > ul');
            });

            //正文图片选择——确定;
            $('.new-product-message-local .message-body-online > ul > li').each(function(){
                $(this).on('click', function(){
                    $(this).find('div').css({ 'display': 'block' }).parent().siblings().find('div').css({ 'display': 'none' });
                })
            });
            $('.new-product-message-local .ensure-submit').on('click', function(){

                $('.new-product-message-local .message-body-online > ul > li > div').each(function(){
                    if($(this).css('display') == 'block'){
                        $('.create-product-cover img').attr('src', $(this).prev().attr('src'));
                        $('.new-product-message-local').hide();
                    }
                })


            });
        }else{
            $('.new-product-message-local .empty-data').show();
        }
    });





    //正文图片弹出层——取消关闭;
    $('.new-message-box .cancel-submit').on('click', function(){
        $('.new-product-message-local').hide();
    });
    $('.new-product-message-local .close-icon').on('click', function(){
        $('.new-product-message-local').hide();
    });


    /*
    * 选中已有作品tab页的数据渲染及交互
    * */

    var curPage = 1;
    var pageSize = 10;
    var totalPage;

    //列表数据渲染方法;
    function renderProductList(data){
        data.forEach(function(item, index){
            $('<li>' +
                '<label>' +
                '<span class="checkbox-inner"></span>' +
                '<input class="demand-checked-type" type="checkbox" name="product" value="'+ item.recId +'"/>' +
                '<span class="demand-checked-item"></span>' +
                '</label>' +
                '<span class="product-index">'+((curPage -1)*10 + (index + 1)) +'</span>' +
                '<a target="_blank" href="/contentBank/article_detail_'+ item.recId +'.html" style="color:#64676A;">' +
                '<span class="title" style="cursor:pointer;">&nbsp;'+ item.title +'('+ item.wordNum +'字'+ item.imgNum +'图片)</span>' +
                '</a>' +
                '</li>').appendTo($('.have-product-list'));
            if(item.isSign){
                $('.have-product-list li').eq(index).css('background', '#f5f7fa');
                $('.have-product-list li label input').eq(index).css('cursor', 'not-allowed');
                $('<span style="float:right;color:#89BC62;margin-right: 20px;">已报名</span>').appendTo($('.have-product-list li').eq(index));
                $('.have-product-list li label').eq(index).find('.checkbox-inner').addClass('checkbox-inner-checked check-disabled').css({'background': '#eaedf0', 'borderColor': '#dcdfe6'});
            }
        });

    }

    //列表中复选框选中及选中的个数;
    function checkProductList(){
        $('.have-product-list li input').each(function(item){
            $(this).on('click', function(){
                if($(this).prop('checked')){
                    $(this).prev().addClass('checkbox-inner-checked');
                    $('.select-total > span > span').text($('.have-product-list  .checkbox-inner-checked').not('.check-disabled').length);
                }else {

                    $(this).prev().removeClass('checkbox-inner-checked');
                    $('.select-total > span > span').text($('.have-product-list  .checkbox-inner-checked').not('.check-disabled').length);
                }
            })
        });
    }

    //分页数据处理;
    function handlePagination(total){
        //分页
        // totalPage = total;
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
                var searchVal = $.trim($('.product-search').val());
                var postData = { cp:curPage, rows: pageSize, demandSn: sn };
                if(searchVal){
                    postData.title = searchVal;
                }

                commonFun.commonAjax('/production/searchYRProductionByTitle', 'post', postData, productSuccess);
            }
        });
    }

    //已有作品列表数据获取及绑定;
    commonFun.commonAjax('/production/searchYRProductionByTitle', 'post', { cp:1, rows: 10, demandSn: sn  }, productSuccess);
    function productSuccess(res){
        if(res.status === 1){
            $('.have-product-list li').remove();
            $('.select-total > span > span').text(0);
            var data = res.data.data;

            if(data.length === 0){
                $('.container-demand-product ul li').eq(1).css({ 'display': 'none' });
                $('.container-demand-product ul li').eq(2).css({ 'width': '688px' });
                $('.have-product').hide();
            }else{
                renderProductList(data);
                checkProductList();
                totalPage = res.data.total;
                handlePagination();
            }
        }
    }

    //点击搜索按钮获取列表数据;
    $('.search-button').on('click', function(){
        var searchVal = $.trim($('.product-search').val());
        curPage = 1;

        commonFun.commonAjax('/production/searchYRProductionByTitle', 'post', { cp:curPage, rows: 10, title: searchVal, demandSn: sn }, searchSuccess);
        function searchSuccess(res){
            if(res.status === 1){
                var data = res.data.data;

                if(data.length > 0){
                    $('.have-product-list li').remove();
                    $('.empty-data').remove();
                    renderProductList(data);
                    checkProductList();
                    $('.no-data-wrap').hide();
                    $('#pagination').css({'display': 'block'});
                    $('.select-total').show();
                }else{
                    $('.have-product-list li').remove();
                    $('.no-data-wrap').show();
                    // $('<div class="empty-data">暂无数据!</div>').appendTo($('.have-product-list'));
                    $('.select-total span span').text(0);
                    $('.select-total').hide();
                    $('#pagination').css({'display': 'none'});
                }
                totalPage = res.data.total;
                handlePagination();
            }
        }
    });

    //弹出层显示;
    $('.have-product-submit-button').on('click', function(){

        var checkTotal = $('.have-product-list  .checkbox-inner-checked').not('.check-disabled');

        if(checkTotal.length > 0){
            checkTotal.each(function(index){
                var title = $(this).parent().parent().find('.title').text();

                $('<li>' +
                    '<span>'+ (index + 1) +'&nbsp;&nbsp;</span>' +
                    '<span>'+ title +'</span>' +
                    '</li>').appendTo($('.message-product-list'));
            });
            $('.submit-total').text(checkTotal.length);
            // $('.message-box').parent().addClass('message-box-wrapper');
            $('.message-box-wrapper').show();
        }
    });



    //弹出层取消操作;
    $('.cancel-submit').on('click', function(){
        $('.message-box-wrapper').hide();
        $('.message-product-list li').remove();
    });
    $('.have-product-message .close-icon').on('click', function(){
        $('.message-box-wrapper').hide();
        $('.message-product-list li').remove();
    });

    //点击遮罩层关闭弹层;
    function messageBoxHidden(ele, shade){
        $('.' + ele).parent().on('click', function(e){
            var success = true;
            $(e.target).parents().each(function(){
                if($(this).hasClass(ele)){
                    success = false;
                    return false;
                }
            });
            if(success){
                $('.' + ele).parent().hide();
                // $('.' + ele).hide();
                $('.message-product-list li').remove();
            }
        });
    }
    messageBoxHidden('message-box', 'message-box-wrapper');

    //弹出层确定提交操作;
    $('.ensure-submit').on('click', function(){
        var checkTotal = $('.have-product-list  .checkbox-inner-checked').not('.check-disabled');

        if(checkTotal.length > 0){
            var ary = [];
            // var sn = location.search.split('=')[1];

            checkTotal.each(function(item){
                ary.push(Number($(this).next().val()));
            });
            $('.ensure-submit').attr('disabled', true);
            commonFun.commonAjax('/order/saveMultProduct', 'post', { orderTypeValue: 4, demandSn: sn, referIds: ary.join(',') }, haveProductSuccess);
        }else{
            return false;
        }
    });

    //提交成功的回调;
    function  haveProductSuccess(res){
        $('.ensure-submit').attr('disabled', false);

        if(res.status === 1){
            $('.message-box').parent().removeClass('message-box-wrapper');
            $('.message-box').hide();
            $('.container-demand-product').hide();
            $('.container-left-step li').eq(2).find('.step-active-2').text('').addClass('step-icon');
            $('.have-product-list  .checkbox-inner-checked').not('.check-disabled').each(function(index){
                var title = $(this).parent().parent().find('.title').text();

                $('<li>' +
                    '<span>'+ (index + 1) +'</span>' +
                    '<span>'+ title +'</span>' +
                    '</li>').appendTo($('.submit-list'));
            });
            $('.success-status').show();
        }
    }

    //点击预览按钮;
    $('.product-options .product-preview').on('click', function(){
        var authorVal = layui.formSelects.value('select-author', 'valStr');
        var titleVal = $.trim($('.create-product-title input').val());
        var contentVal = ue.getContent();

        if(!authorVal){
            layer.msg('请选择创作者！');
            return false;
        }if(!titleVal){
            layer.msg('请输入作品标题！');
            return false;
        }if(!contentVal){
            layer.msg('请输入作品内容！');
            return false;
        }

        //显示预览弹出层;
        $('.product-preview-message').show();
        $('.preview-title').text(titleVal);
        $('.preview-author').text(authorVal);
        $('.preview-word').text(ue.getContentLength(true));
        $('.preview-content').html(contentVal);
    })

    //隐藏预览弹出层;
    $('.product-preview-message .close-icon').on('click', function(){
        $('.product-preview-message').hide();
    });
    $('.product-preview-message .cancel-submit').on('click', function(){
        $('.product-preview-message').hide();
    });

    $('.continue-sub').on('click', function(){
        location.href = location.href;
    })
});