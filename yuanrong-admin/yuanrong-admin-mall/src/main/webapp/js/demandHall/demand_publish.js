/*
* author: wangZhu;
* version: 1.5;
* */

$(function(){
    //检测是否登录
    var login ;
    var showInvalidate = function(isLogin){
        if(isLogin == 'Y'){
            login = 1;
            $('.graph-validate').hide();
            $('.phone-validate').hide();
            $('.demand-call').val(sessionStorage.getItem('mobile'));
            $('.demand-call').css({ 'border-color': '#67c23a' });
        }
    };
    getUserLoginInfo(showInvalidate);

    //生成面包屑
    function renderBreadCrumb(path, name){
        var breadLeft = '<a href="'+ path +'">'+ name +'</a>' +
                            '<span>&nbsp;&gt;&nbsp;</span>' +
                        '<span class="nav-author-details">发布需求</span>';
        var breadRight = '<a onclick="history.go(-1);" style="font-size:12px;">&lt;返回</a>';

        $(breadLeft).appendTo($('.nav-author'));
        $(breadRight).appendTo($('.nav-author > div'));
    }

    //需求类型的radio标签切换;
    $('.demand-type input').each(function(){
        $(this).on('click', function(){
            if($(this).prop('checked')){
                $(this).prev().addClass('radio-inner-checked');
                $(this).parent().siblings().find('span').removeClass('radio-inner-checked');

                if($(this).val() == 1){
                    $('.made-type').show();
                    $('.IP-type').hide();
                    $('.marketing-type').hide();
                    $('.origin-contribution').hide();
                    $('.origin-contribution2').hide();
                    $('.made-tip').show();
                    $('.market-tip').hide();
                    $('.budget').show();
                    $('.description').show();
                    $('.demand-total-name').show();
                }
                if($(this).val() == 2){
                    $('.made-type').hide();
                    $('.IP-type').show();
                    $('.marketing-type').hide();
                    $('.origin-contribution').hide();
                    $('.origin-contribution2').hide();
                    $('.text-tip span').not('.origin-tip').hide();
                    $('.budget').show();
                    $('.description').show();
                    $('.demand-total-name').show();
                }
                if($(this).val() == 3){
                    $('.made-type').hide();
                    $('.IP-type').hide();
                    $('.origin-contribution').hide();
                    $('.origin-contribution2').hide();
                    $('.marketing-type').show();
                    $('.made-tip').hide();
                    $('.market-tip').show();
                    $('.budget').show();
                    $('.description').show();
                    $('.demand-total-name').show();
                }
                if($(this).val() == 4){
                    $('.made-type').hide();
                    $('.IP-type').hide();
                    $('.marketing-type').hide();
                    $('.origin-contribution').show();
                    $('.origin-contribution2').show();
                    $('.budget').hide();
                    $('.description').hide();
                    // $('.demand-total-name').hide();
                }
                if($(this).val() == 1 &&(/author_images/.test(document.referrer) || /author_detail/.test(document.referrer) || /cart_author/.test(document.referrer)) && localStorage.getItem('isPubIDs') == 1 ){
                    $('.detail-list').show();
                }else{
                    $('.detail-list').hide();
                }

                if($(this).val() == 3 &&(/ip_trade_distribution/.test(document.referrer) || /cart_distribution/.test(document.referrer)) && localStorage.getItem('isPubIDs') == 1 ){
                    $('.detail-account-list').show();
                }else{
                    $('.detail-account-list').hide();
                }

                //重置表单
                formReset();
                if(login){
                    $('.demand-call').val(sessionStorage.getItem('mobile'));
                }
            }

        })
    });

    //默认选中不同的需求类型;
    function selectDemandType(type){
        var type = type || 0;
        console.log(type);
        $('.demand-type input').eq(type).prop('checked', true)
            .prev().addClass('radio-inner-checked')
            .parent().siblings().find('span').removeClass('radio-inner-checked');
    }

    //从导航栏点击发布需求按钮进入或从选购车点击发布需求进入;
    if((/author_image/.test(document.referrer) || /original_work/.test(document.referrer) || /ip_trade_distribution/.test(document.referrer) || /segment_account/.test(document.referrer) || /cart_author/.test(document.referrer) || /cart_distribution/.test(document.referrer)) && localStorage.getItem('isPubIDs') != 1){
        if(/author_image/.test(document.referrer)){
            renderBreadCrumb('/demandHall/demand_hall.html', '需求大厅');
            selectDemandType();
        }
        if(/original_work/.test(document.referrer)){
            renderBreadCrumb('/demandHall/demand_hall.html', '需求大厅');
            selectDemandType(1);
            $('.demand-type input').eq(1).trigger('click');
        }
        if(/segment_account/.test(document.referrer)){
            renderBreadCrumb('/demandHall/demand_hall.html', '需求大厅');
            selectDemandType(2);
        }
        if(/ip_trade_distribution/.test(document.referrer)){
            renderBreadCrumb('/demandHall/demand_hall.html', '需求大厅');
            selectDemandType(3);
        }
        if(/cart_author/.test(document.referrer)){
            renderBreadCrumb('javascript:history.go(-1)', '选购车');
            selectDemandType();
        }
        if(/cart_distribution/.test(document.referrer)){
            renderBreadCrumb('javascript:history.go(-1)', '选购车');
            selectDemandType(3);
        }
    }else if((localStorage.getItem('isPubIDs') != 1)){
        renderBreadCrumb('/demandHall/demand_hall.html', '需求大厅');
    }

    //从创作者列表页或作者详情页点击立即预约按钮或选购车点击发布需求进入;
    if((/author_images/.test(document.referrer) || /author_detail/.test(document.referrer) || /cart_author/.test(document.referrer)) && localStorage.getItem('isPubIDs') == 1 ){
        $('.demand-title').text('发布需求 - 内容定制');
        $('.made-type .made-author').hide();
        // $('.made-type .made-price').hide();
        $('.made-type .made-show').hide();
        // $('.demand-details > li').eq(1).hide();
        $('.fast-demand-tip').hide();

        if(/author_images/.test(document.referrer)){
            renderBreadCrumb('/contentBank/author_images.html', '找作者');
            selectDemandType();
        }
        if(/author_detail/.test(document.referrer)){
            renderBreadCrumb('javascript:window.history.go(-1)', '作者详情');
            selectDemandType();
        }
        if(/cart_author/.test(document.referrer)){
            renderBreadCrumb('javascript:window.history.go(-1)', '选购车');
            selectDemandType();
        }

        $.ajax({
            url: domain + '/demand/list_yrAuthorByIds',
            type: 'post',
            data: { shopCartIds: localStorage.getItem('shoppingCartId'), rows: localStorage.getItem('shoppingCartId').split(',').length},
            dataType: 'json',
            crossDomain: true,
            success: function(res){
                if(res.status == 1){
                    $('.detail-list').show();
                    $('.list-total').text(res.data.total);

                    var list = res.data.data;
                    list.forEach(function(item,index){
                        item.createdPrice = item.createdPrice == 0 ? '按需定价' : '￥' + item.createdPrice;
                        $('<li style="margin-top:0;">' +
                            '<div style="text-align:center;width:10%;"><span style="display:none;">'+ item.shoppingCartId +'</span><span>'+ (index + 1) +'</span></div>' +
                            '<div style="text-align:left;width:25%;"><img src="'+ item.authorImg +'" style="width:50px;height:50px;border-radius:50%;margin-right:8px;" alt="">'+ item.authorNickname +'</div>' +
                            '<div style="width:25%;overflow: hidden;height:50px;line-height:25px;margin-top:10px;">'+ item.introduction +'</div>' +
                            '<div style="width:25%;color:#4895e7;text-align:center;w">'+ thousandCharacter(item.createdPrice) +'</div>' +
                            '<div class="dele" style="width:15%;cursor:pointer;text-align:center;">删除</div>').appendTo($('.detail-list .select-list'));
                    });
                    $('.dele').on('click', function(e){
                        var curShopIndex = $(this).parent().find('div').first().find('span').eq(0).text();
                        var ary = localStorage.getItem('shoppingCartId').split(',');
                        ary.forEach(function(item, index){
                           if(item == curShopIndex) {
                               ary.splice(index, 1);
                           }
                        });
                        localStorage.setItem('shoppingCartId', ary.join(','));
                        $(this).parent().remove();
                        $('.detail-list .select-list > li').each(function(index,item){
                            if(index > 0) {
                                $(this).children().eq(0).find('span').eq(1).text(index);
                            }
                        });
                        $('.detail-list .list-total').text($('.detail-list .select-list > li').length -1);
                    });
                }
            }
        })
    }

    //从账号列表页点击立即推广进入或从选购车点击发布需求进入;
    if((/ip_trade_distribution/.test(document.referrer) || /cart_distribution/.test(document.referrer)) && localStorage.getItem('isPubIDs') == 1){
        if(/ip_trade_distribution/.test(document.referrer)){
            renderBreadCrumb('javascript:window.history.go(-1)', '做推广');
            selectDemandType(3);
        }
        if(/cart_distribution/.test(document.referrer)){
            renderBreadCrumb('javascript:window.history.go(-1)', '选购车');
            selectDemandType(3);
        }

        $('.demand-title').text('发布需求 - 营销分发');
        $('.marketing-type .marketing-account').hide();
        // $('.marketing-type .marketing-price').hide();
        $('.marketing-type .marketing-show').hide();

        // $('.demand-details > li').eq(1).hide();
        $('.fast-demand-tip').hide();

        $.ajax({
            url: domain + '/demand/list_PlatformIPAccountByShopCartIds',
            type: 'post',
            data: { shoppingCartIDS: localStorage.getItem('shoppingCartId'), rows: localStorage.getItem('shoppingCartId').split(',').length },
            dataType: 'json',
            crossDomain: true,
            success: function(res){
                if(res.status == 1){
                    $('.detail-account-list').show();
                    $('.list-total').text(res.data.total);
                    res.data.data.forEach(function(item,index){
                        $('<li style="overflow:hidden;height:80px;line-height:80px;margin-top:0">' +
                            '<div style="width:10%;text-align:center;"><span style="display:none;">'+ item.shoppingCartId +'</span><span>'+ (index + 1) +'</span></div>' +
                            '<div style="width:30%;">' +
                            '<div style="width:30%;float:left;position:relative;"><img src="'+ item.headImageUrlLocal +'" style="width:50px;height:50px;border-radius:50%;margin-right:8px;" alt=""><img style="position:absolute;right:0;bottom:15px;width:14px;height:14px;" src="'+ item.icoUrl +'" alt=""></div><div style="float:left;line-height:20px;padding-top:20px;"><p>'+ item.name +'</p><p>'+ item.accountId +'</p></div></div>' +
                            '<div style="width:20%;text-align:center;">'+ unitFormat(item.fans) +'</div>' +
                            '<div class="item-price" style="width:25%;"></div>' +
                            '<div class="dele" style="width:10%;margin-left:13px;cursor:pointer;text-align:center;">删除</div></li>').appendTo($('.detail-account-list .select-list'));

                        item.priceInfo.forEach(function(i){
                            $('<li style="height:20px;line-height:20px;margin-top:5px;border-bottom:1px dashed rgb(233, 235, 236)"><span>'+ i.referenceItem +'</span><span style="margin-left:20px;float:right;"><span class="origin-icon" style="border:1px solid #4895e7;color: #4895e7;border-radius: 2px;">原</span>￥'+ thousandCharacter(parseInt(i.referencePrice)) +'</span></li>').appendTo($('.item-price').eq(index));
                            i.isOriginal == 1 ? $('.origin-icon').show(): $('.origin-icon').hide();
                        });
                        $('.item-price').find('li').last().css({ 'border': 'none' });
                    });

                    $('.dele').on('click', function(e){
                        var curShopIndex = $(this).parent().find('div').first().find('span').eq(0).text();
                        var ary = localStorage.getItem('shoppingCartId').split(',');
                        ary.forEach(function(item, index){
                            if(item == curShopIndex) {
                                ary.splice(index, 1);

                            }
                        });
                        localStorage.setItem('shoppingCartId', ary.join(','));

                        $(this).parent().remove();
                        $('.detail-account-list .select-list > li').each(function(index,item){
                            if(index > 0) {
                                $(this).children().eq(0).find('span').eq(1).text(index);
                            }
                        });
                        $('.detail-account-list .list-total').text($('.detail-account-list .select-list > li').length -1);
                    });

                }
            }
        })
    }

    //select多选下拉列表数据绑定;
    //获取所有多选下拉框
    var formSelects = layui.formSelects;

    //使用场景下拉框列表数据绑定;
    $.ajax({
        url: domain + '/dictInfo/getAuthorDic',
        type: 'post',
        dataType: 'json',
        crossDomain: true,
        success: function(res){
            if(res.status == 1){
                var useSceneData = res.data.useScenes;
                var contentFormData = res.data.contentForm;
                var contentTypeData = res.data.contentType;

                //使用场景多选下拉框数据
                useSceneData.forEach(function(item){
                    item.name = item.CategoryName;
                    item.value = item.CategoryID;
                });
                formSelects.data('select-id1', 'local', {
                    arr: useSceneData
                });
                formSelects.on('select-id1', function(id, vals, val, isAdd, isDisabled){
                    if(val){
                        handleScreen(true);
                    }
                    return true;
                });

                //内容形式多选下拉框数据
                var formFilter = contentFormData.filter(function(item){
                    return item.CategoryName != '其它';
                });

                formFilter.forEach(function(item, index){
                    item.name = item.CategoryName;
                    item.value = item.CategoryID;
                });
                formSelects.data('select-id2', 'local', {
                    arr: formFilter
                });
                formSelects.on('select-id2', function(id, vals, val, isAdd, isDisabled){
                    if(val){
                        handleForm(true);
                    }
                    return true;
                });

                //内容领域多选下拉框数据
                contentTypeData.forEach(function(item){
                    item.name = item.CategoryName;
                    item.value = item.CategoryID;
                });
                formSelects.data('select-id3', 'local', {
                    arr: contentTypeData
                });
                formSelects.on('select-id3', function(id, vals, val, isAdd, isDisabled){
                    if(val){
                        handleField(true);
                    }
                    return true;
                });
            }

        }
    });
    //所属行业下拉框列表数据绑定;
    $.ajax({
        url: domain + '/dictInfo/getDictInfoByType',
        type: 'post',
        data: { type: 15 },
        dataType: 'json',
        crossDomain: true,
        success: function(res){
            //获取所有多选下拉框
            // var formSelects = layui.formSelects;
            if(res.status == 1){
                res.data.forEach(function(item){
                    item.value = item.id;
                });
                formSelects.data('select-id7', 'local', {
                    arr: res.data
                });
    
                formSelects.on('select-id7', function(id, vals, val, isAdd, isDisabled){
                    if(val){
                        handleIndustry(true);
                    }
                    return true;
                });
    
                formSelects.data('select-id8', 'local', {
                    arr: res.data
                });
                formSelects.on('select-id8', function(id, vals, val, isAdd, isDisabled){
                    if(val){
                        handleMarketIndustry(true);
                    }
                    return true;
                });
    
                formSelects.data('select-id9', 'local', {
                    arr: res.data
                });
                formSelects.on('select-id9', function(id, vals, val, isAdd, isDisabled){
                    if(val){
                        handleIPIndustry(true);
                    }
                    return true;
                });

                formSelects.data('select-id10', 'local', {
                    arr: res.data
                });
                formSelects.on('select-id10', function(id, vals, val, isAdd, isDisabled){
                    if(val){
                        handleOriginIndustry(true);
                    }
                    return true;
                });
            }
            
        }
    });

    //账号分类下拉框列表数据绑定;
    $.ajax({
        url: domain + '/dictInfo/getDictInfoByType',
        type: 'post',
        data: { type: 14 },
        dataType: 'json',
        crossDomain: true,
        success: function(res){
            //获取所有多选下拉框
            if(res.status == 1){
                var formSelects = layui.formSelects;

                res.data.forEach(function(item){
                    item.value = item.id;
                });
                formSelects.data('select-id6', 'local', {
                    arr: res.data
                });
                formSelects.on('select-id6', function(id, vals, val, isAdd, isDisabled){
                    if(val){
                        handleMarketPlatform(true);
                    }
                    return true;
                });
            }
        }
    });

    //获取手机短信验证码;
    $('#img1').trigger('click');
    var phoneInvalidateFlag = false;
    $('.demand-pull').on('click', function(){
        var that = $(this);
        var countdown =60;

        $.ajax({
            url: domain + '/verificationCode/sendMessage',
            type: 'post',
            data: {
                mobile: $.trim($('.demand-call').val()),
                imgCode: $.trim($('.demand-graph').val())
            },
            dataType: 'json',
            crossDomain: true,
            success: function(res){
                if(res.status == 1){
                    phoneInvalidateFlag = true;
                    var codeTimer = setInterval(function() {
                        if (countdown == 1) {
                            that.attr('disabled', false).css({ 'color': '#346bd1' }).val("获取验证码");
                            countdown = 60;
                            clearInterval(codeTimer);
                            return false;
                        } else {
                            that.attr("disabled", true).css({ 'color': '#707070' }).val(countdown + "s重新发送");
                            countdown--;
                        }
                    },1000);
                }else {
                    phoneInvalidateFlag = false;
                    $('.img-prompt span').last().addClass('show').removeClass('hidden').siblings().addClass('hidden');
                }
            }
        });
    });

    /*
    普通需求表单验证提示信息;
     */
    //需求名称验证
    var name = $('.demand-name');
    var namePrompt = $('.name-prompt');
    var nameFlag;
    function handleName(){
        var nameVal= $.trim(name.val());
        if(nameVal == '' || nameVal == null){
            namePrompt.parent().find('input').css({ 'border-color': '#f56c6c' });
            namePrompt.children().each(function(index, item){
                index == 0 ? $(this).addClass('show').removeClass('hidden'): $(this).removeClass('show').addClass('hidden');
            });
            nameFlag = false;
        }
        else if(nameVal.length < 4 && nameVal.length > 0){
            namePrompt.parent().find('input').css({ 'border-color': '#f56c6c' });
            namePrompt.children().each(function(index, item){
                index == 1 ? $(this).addClass('show').removeClass('hidden'): $(this).removeClass('show').addClass('hidden');
            });
            nameFlag = false;
        }
        else if(nameVal.length > 50){
            namePrompt.parent().find('input').css({ 'border-color': '#f56c6c' });
            namePrompt.children().each(function(index, item){
                index == 2 ? $(this).addClass('show').removeClass('hidden'): $(this).removeClass('show').addClass('hidden');
            });
            nameFlag = false;
        }
        else{
            namePrompt.parent().find('input').css({ 'border-color': '#67c23a' });
            namePrompt.children().each(function(){
                $(this).removeClass('show').addClass('hidden');
            });
            nameFlag = true;
        }
    }
    name.on('blur', handleName);

    //定制内容—创作要求—使用场景验证
    var screen = $('input[name="screen"]');
    var screenFlag = false;
    function handleScreen(bool) {
        var bool = '' || bool;
        var ScreenVal = screen.val() || bool;

        if (ScreenVal == '' || ScreenVal == undefined) {
            $('.made-type .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#f56c6c' });
            $('.made-screen span').addClass('show').removeClass('hidden');
            screenFlag = false;
        }
        else {
            $('.made-type .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#67c23a' });
            $('.made-screen span').addClass('hidden').removeClass('show');
            screenFlag = true;
        }
    }
    // screen.on('blur', handleScreen);

    //定制内容—创作要求验证—内容形式验证
    var formFlag = false;
    function handleForm(bool) {
        var bool = '' || bool;
        var formVal = formSelects.value('select-id2', 'nameStr') || bool;
        if (formVal == '' || formVal == undefined) {
            $('.made-form .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#f56c6c' });
            $('.made-form span').addClass('show').removeClass('hidden');
            formFlag = false
        }
        else {
            $('.made-form .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#67c23a' });
            $('.made-form span').addClass('hidden').removeClass('show');
            formFlag = true;
        }
    }
    // form.on('change', handleForm);

    //定制内容—创作要求验证—内容领域验证
    var fieldFlag = false;
    function handleField(bool) {
        var bool = '' || bool;
        var fieldVal = formSelects.value('select-id3', 'nameStr') || bool;
        if (fieldVal == '' || fieldVal == undefined) {
            $('.made-field .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#f56c6c' });
            $('.made-field span').addClass('show').removeClass('hidden');
            fieldFlag = false;
        }
        else {
            $('.made-field .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#67c23a' });
            $('.made-field span').addClass('hidden').removeClass('show');
            fieldFlag = true;
        }
    }
    // field.on('change', handleField);

    //定制内容—期望作者数验证;
    var expectedAuthorFlag = false;
    var expectedAuthorDom = $('.expected-authors input');
    function handleExpectedAuthor(){
        var expectedAuthorVal = $.trim(expectedAuthorDom.val());
        if(expectedAuthorVal == '' || expectedAuthorVal == undefined){
            expectedAuthorDom.css({ 'border-color': '#f56c6c' });
            $('.authors-prompt span').addClass('show').removeClass('hidden');
        }
        else{
            expectedAuthorDom.css({ 'border-color': '#67c23a' });
            $('.authors-prompt span').addClass('hidden').removeClass('show');
            expectedAuthorFlag = true;
        }
    }
    expectedAuthorDom.on('blur', handleExpectedAuthor);

    //定制内容—期望获得报价验证;
    var expectedPriceFlag = false;

    var  handleExpectedPrice = function(){
        var expectedPriceDom = $('.made-type .expected-price input').eq(0);
        var expectedPriceVal = $.trim(expectedPriceDom.val());
        if(expectedPriceVal == '' || expectedPriceVal == undefined){
            expectedPriceDom.css({ 'border-color': '#f56c6c' });
            $('.made-type .expected-price-prompt span').addClass('show').removeClass('hidden');
        }else{
            expectedPriceDom.css({ 'border-color': '#67c23a' });
            $('.made-type .expected-price-prompt span').addClass('hidden').removeClass('show');
            expectedPriceFlag = true;
        }
    };
    $('.made-type .expected-price input').on('blur', handleExpectedPrice);

   //营销分发—期望获得报价验证;
    var marketingPriceFlag = false;

    var handleMarketingPrice = function(){
        var expectedPriceDom = $('.marketing-type .expected-price input');
        var expectedPriceVal = $.trim(expectedPriceDom.val());
        if(expectedPriceVal == '' || expectedPriceVal == undefined){
            expectedPriceDom.css({ 'border-color': '#f56c6c' });
            $('.marketing-type .expected-price-prompt span').addClass('show').removeClass('hidden');
        }else{
            expectedPriceDom.css({ 'border-color': '#67c23a' });
            $('.marketing-type .expected-price-prompt span').addClass('hidden').removeClass('show');
            marketingPriceFlag = true;
        }
    };
    $('.marketing-type .expected-price input').on('blur', handleMarketingPrice);

    //完成时间验证
    var dayFlag = false;
    function handleDay() {
        var dayVal = $.trim($('#test1').val());
        if (dayVal == '' || dayVal == null) {
            $('#test1').css({ 'border-color': '#f56c6c' });
            $('.expected-time .day-prompt span').addClass('show').removeClass('hidden');
            dayFlag = false;
        }else {
            $('#test1').css({ 'border-color': '#67c23a' });
            $('.expected-time .day-prompt span').addClass('hidden').removeClass('show');
            dayFlag = true;
        }
    }
    // day.on('blur', handleDay);

    //需求预算验证
    var budget = $('.demand-price');
    var budgetFlag;
    function handleBudget() {
        var budgetVal = $.trim(budget.val());
        if (budgetVal == '' || budgetVal == null) {
            budget.css({ 'border-color': '#f56c6c' });
            $('.budget-prompt span').eq(0).addClass('show').removeClass('hidden').siblings().addClass('hidden');
            budgetFlag = false;
        }
        else if(budgetVal < 10) {
            budget.css({ 'border-color': '#f56c6c' });
            $('.budget-prompt span').eq(1).addClass('show').removeClass('hidden').siblings().addClass('hidden');
            budgetFlag = false;
        }
        else {
            budget.css({ 'border-color': '#67c23a' });
            $('.budget-prompt span').addClass('hidden').removeClass('show');
            budgetFlag = true;
        }
    }
    budget.on('blur', handleBudget);

    //需求描述验证(非必填项);
    $('.demand-text textarea').on('input', function(){
        var wordNum = $(this).val().length;
        var wordNumDom = $('.demand-text-word span');
        wordNumDom.text(wordNum);
        if(wordNum > 1000){
            wordNumDom.css({'color': 'rgb(245, 108, 108)'});
            $('.demand-text textarea').css({'border-color': 'rgb(245, 108, 108)'});
        }else{
            wordNumDom.css({'color': '#409eff'});
            $('.demand-text textarea').css({'border-color': '#409eff'});
        }
    });

    $('.demand-text textarea').on('blur', function(){
        var wordNum = $.trim($(this).val()).length;
        if(wordNum < 1000 && wordNum > 0){
            $('.demand-text-word span').css({'color': 'rgb(103, 194, 58)'});
            $('.demand-text textarea').css({'border-color': 'rgb(103, 194, 58)'});
        }
    });

    //原创征稿—征稿要求验证;

    //定制内容—所属行业验证
    var industryFlag = false;
    function handleIndustry(bool) {
        var bool = '' || bool;
        var industryVal = formSelects.value('select-id7', 'nameStr') || bool;

        if (industryVal == '' || industryVal == undefined) {
            $('.made-industry .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#f56c6c' });
            $('.industry-prompt span').addClass('show').removeClass('hidden');
            industryFlag = false;
        }
        else {
            $('.made-industry .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#67c23a' });
            $('.industry-prompt span').addClass('hidden').removeClass('show');
            industryFlag = true;
        }
    }

    //图形验证码验证
    var img = $('.demand-graph');
    var imgFlag;
    function handleImg() {
        var imgVal = $.trim(img.val());
        if (imgVal == '' || imgVal == undefined) {
            $('.img-prompt span').eq(0).addClass('show').removeClass('hidden').siblings().addClass('hidden');
            imgFlag = false;
        }else {
            $('.img-prompt span').addClass('hidden').removeClass('show');
            imgFlag = true;
        }
    }
    img.on('blur', handleImg);

    //手机号验证
    var call = $('.demand-call');
    var callPrompt = $('.call-prompt');
    var callFlag;
    function handleCall() {
        if($.trim(call.val()) == '' || call.val() == null){
            call.css({ 'border-color': '#f56c6c' });
            callPrompt.children().each(function(index, item){
                index == 0 ? $(this).addClass('show').removeClass('hidden'): $(this).removeClass('show').addClass('hidden');
            });
            callFlag = false;
        }
        else if(commonFun.telValid($.trim(call.val())) == false){
            call.css({ 'border-color': '#f56c6c' });
            callPrompt.children().each(function(index, item){
                index == 1 ? $(this).addClass('show').removeClass('hidden'): $(this).removeClass('show').addClass('hidden');
            });
            callFlag = false;
        }
        else{
            call.css({ 'border-color': '#67c23a' });
            callPrompt.children().each(function(){
                $(this).removeClass('show').addClass('hidden');
                callFlag = true;
            })
        }
    }
    call.on('blur', handleCall);

    //手机验证码验证
    var pull = $('.demand-push');
    var pullPrompt = $('.pull-prompt span');
    var pullFlag;
    function handlePull() {
        if($.trim(pull.val()) == '' || pull.val() == undefined){
            $('.pull-prompt span').eq(0).addClass('show').removeClass('hidden').siblings().addClass('hidden');
            pullFlag = false;
        }
        else{
            pullPrompt.removeClass('show').addClass('hidden');
            pullFlag = true;
        }
    }
    pull.on('blur', handlePull);

    //IP代理权—IP形式验证
    var IPForm = false;
    function handleIPForm() {
        var ary = [];
        $('.IP-type .demand-checked input').each(function(item){
            if($(this).prop('checked')){
                ary.push($(this).val());
            }
        });
        if(ary.length == 0){
            $('.IP-type-prompt span').addClass('show').removeClass('hidden');
            IPForm = false;
        }else {
            $('.IP-type-prompt span').removeClass('show').addClass('hidden');
            IPForm = true;
        }
    }

    $('.IP-type .demand-checked input').each(function(item){
        $(this).on('click', function(){
            $('.IP-type-prompt span').removeClass('show').addClass('hidden');
            IPForm = true;
        })
        // if($(this).prop('checked')){
        //     ary.push($(this).val());
        // }
    });

    //IP代理权—IP行业验证
    var IPIndustryFlag = false;
    function handleIPIndustry(bool){
        var bool = '' || bool;
        var industryVal = formSelects.value('select-id9', 'nameStr') || bool;
        if (industryVal == '' || industryVal == undefined) {
            $('.IP-type .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#f56c6c' });
            $('.IP-industry-prompt span').addClass('show').removeClass('hidden');
        }
        else {
            $('.IP-type .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#67c23a' });
            $('.IP-industry-prompt span').addClass('hidden').removeClass('show');
            IPIndustryFlag = true;
        }
    }

    //营销分发—发布平台验证
    var platformFlag = false;
    function handlePlatform() {
        var ary = [];
        $('.marketing-type .demand-checked input').each(function(item){
            if($(this).prop('checked')){
                ary.push($(this).val());
            }
        });
        if(ary.length == 0){
            $('.platform-prompt span').addClass('show').removeClass('hidden');
            platformFlag = false;
        }else {
            $('.platform-prompt span').removeClass('show').addClass('hidden');
            platformFlag = true;
        }
    }
    $('.marketing-type .demand-checked input').each(function(item){
        $(this).on('click', function(){
            $('.platform-prompt span').removeClass('show').addClass('hidden');
            platformFlag = true;
        })
    });

    //营销分发—推广时间验证
    var marketDayFlag = false;
    function handleMarketDay() {
        var day = $('.market-day');
        var dayVal = $.trim(day.val());
        if (dayVal == '' || dayVal == null) {
            $('#test2').css({ 'border-color': '#f56c6c' });
            $('.market-expected-time .day-prompt span').addClass('show').removeClass('hidden');
        }else {
            $('#test2').css({ 'border-color': '#67c23a' });
            $('.market-expected-time .day-prompt span').addClass('hidden').removeClass('show');
            marketDayFlag = true;
        }
    }

    //营销分发—账号分类验证
    var marketPlatformFlag = false;
    function handleMarketPlatform(bool) {
        var bool = '' || bool;
        var fieldVal = formSelects.value('select-id6', 'nameStr') || bool;

        if (fieldVal == '' || fieldVal == undefined) {
            $('.marketing-type-cate .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#f56c6c' });
            $('.account-prompt span').addClass('show').removeClass('hidden');
            marketPlatformFlag = false;
        }
        else {
            $('.marketing-type-cate .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#67c23a' });
            $('.account-prompt span').addClass('hidden').removeClass('show');
            marketPlatformFlag = true;
        }
    }

    //营销分发—所属行业验证
    var marketIndustryFlag = false;
    function handleMarketIndustry(bool) {
        var bool = '' || bool;
        var fieldVal = formSelects.value('select-id8', 'nameStr') || bool;

        if (fieldVal == '' || fieldVal == undefined) {
            $('.marketing-type .demand-industry .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#f56c6c' });
            $('.market-industry-prompt span').addClass('show').removeClass('hidden');
        }
        else {
            $('.marketing-type .demand-industry .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#67c23a' });
            $('.market-industry-prompt span').addClass('hidden').removeClass('show');
            marketIndustryFlag = true;
        }
    }

    //营销分发—粉丝数量验证
    var fansFlag = false;
    function handleFans() {
        var fans1 = $.trim($('.demand-fans1').val());
        var fans2 = $.trim($('.demand-fans2').val());
        if((fans1 == '' || fans1 == undefined) || (fans2 == '' || fans2 == undefined)){
            $('.demand-fans1').css({ 'border-color': '#f56c6c' });
            $('.demand-fans2').css({ 'border-color': '#f56c6c' });
            $('.fans-prompt span').eq(0).addClass('show').removeClass('hidden').siblings().addClass('hidden');
            fansFlag = false;
        }
        else if(parseInt(fans1) > parseInt(fans2)){
            $('.demand-fans1').css({ 'border-color': '#f56c6c' });
            $('.demand-fans2').css({ 'border-color': '#f56c6c' });
            $('.fans-prompt span').eq(1).addClass('show').removeClass('hidden').siblings().addClass('hidden');
            fansFlag = false;
        }
        else{
            $('.demand-fans1').css({ 'border-color': '#67c23a' });
            $('.demand-fans2').css({ 'border-color': '#67c23a' });
            $('.fans-prompt span').removeClass('show').addClass('hidden');
            fansFlag = true;
        }
    }
    $('.demand-fans1').on('blur', handleFans);
    $('.demand-fans2').on('blur', handleFans);

    //营销分发—期望账号数验证;
    var expectAccountFlag = false;
    var expectAccountDom = $('.expected-account input');
    function handleExpectAccount(){
        var expectAccountVal = $.trim(expectAccountDom.val());
        if(expectAccountVal == '' || expectAccountVal == undefined){
            expectAccountDom.css({ 'border-color': '#f56c6c' });
            $('.expected-account-prompt span').addClass('show').removeClass('hidden');
            expectAccountFlag = false;
        }else{
            $('.expected-account-prompt span').addClass('hidden').removeClass('show');
            expectAccountDom.css({ 'border-color': '#67c23a' });
            expectAccountFlag = true;
        }
    }
    expectAccountDom.on('blur', handleExpectAccount);

    //原创征稿—表现形式验证;
    var performanceFlag = false;
    function handlePerformance(){
        var ary = [];
        $('.origin-contribution .demand-checked input').each(function(item){
            if($(this).prop('checked')){
                ary.push($(this).val());
            }
        });
        if(ary.length == 0){
            $('.contribution-type-prompt span').addClass('show').removeClass('hidden');
            performanceFlag = false;
        }else {
            $('.contribution-type-prompt span').removeClass('show').addClass('hidden');
            performanceFlag = true;
        }
    }
    $('.origin-contribution .demand-checked input').each(function(item) {
        $(this).on('click', function () {
            $('.contribution-type-prompt span').removeClass('show').addClass('hidden');
            performanceFlag = true;
        })
    });

    //若表现形式为‘文章’, 则显示‘字数要求’这一项;
    $('input:radio[name="origin-form"]').on('change', function(){
        if($('input:radio[name="origin-form"]:checked').val() != '文章'){
            $('.origin-contribution-num').hide();
        }else{
            $('.origin-contribution-num').show();
        }
    });

    //原创征稿—字数要求验证;
    var wordNumFlag = false;
    var wordNumDom1 = $('.word-num .num1');
    var wordNumDom2 = $('.word-num .num2');
    function handleWordNum(){
        var num1Val = $.trim(wordNumDom1.val());
        var num2Val = $.trim(wordNumDom2.val());
        if((num1Val == '' || num1Val == undefined) || (num2Val == '' || num2Val == undefined)){
            $('.word-num-prompt span').addClass('show').removeClass('hidden');
            if(num1Val == '' || num1Val == undefined){
                wordNumDom1.css({ 'border-color': '#f56c6c' });
            }if(num2Val == '' || num2Val == undefined){
                wordNumDom2.css({ 'border-color': '#f56c6c' });
            }
        }else{
            wordNumDom1.css({ 'border-color': '#67c23a' });
            wordNumDom2.css({ 'border-color': '#67c23a' });
            $('.word-num-prompt span').addClass('hidden').removeClass('show');
            wordNumFlag = true;
        }
    }
    // wordNumDom1.on('blur', handleWordNum);
    // wordNumDom2.on('blur', handleWordNum);


    //原创征稿—稿件费用验证;
    var workPriceFlag = false;
    var workPriceDom = $('.work-price input');
    function handleWorkPrice(){
        var workPriceVal = $.trim(workPriceDom.val());
        if(workPriceVal == '' || workPriceVal == undefined){
            workPriceDom.css({ 'border-color': '#f56c6c' });
            $('.work-price-prompt span').addClass('show').removeClass('hidden');
        }else{
            workPriceDom.css({ 'border-color': '#67c23a' });
            $('.work-price-prompt span').addClass('hidden').removeClass('show');
            workPriceFlag = true;
        }
    }
    workPriceDom.on('blur', handleWorkPrice);

    //原创征稿—征集数量验证;
    var workNumFlag = false;
    var workNumDom = $('.work-num input');
    function handleWorkNum(){
        var workNumVal = $.trim(workNumDom.val());
        if(workNumVal == '' || workNumVal == undefined){
            workNumDom.css({ 'border-color': '#f56c6c' });
            $('.work-num-prompt span').addClass('show').removeClass('hidden');
            workNumFlag = false;
        }else{
            workNumDom.css({ 'border-color': '#67c23a' });
            $('.work-num-prompt span').addClass('hidden').removeClass('show');
            workNumFlag = true;
        }
    }
    workNumDom.on('blur', handleWorkNum);

    //原创征稿—截止时间验证;
    var endFlag = false;
    function handleEnd(){
        var dayVal = $.trim($('#test3').val());
        if (dayVal == '' || dayVal == null) {
            $('#test3').css({ 'border-color': '#f56c6c' });
            $('.origin-contribution2 .day-prompt span').addClass('show').removeClass('hidden');
            endFlag = false;
        }else {
            $('#test3').css({ 'border-color': '#67c23a' });
            $('.origin-contribution2 .day-prompt span').addClass('hidden').removeClass('show');
            endFlag = true;
        }
    }

    //原创征稿—所属行业验证;
    var originIndustryFlag = false;
    function handleOriginIndustry(bool) {
        var bool = '' || bool;
        var fieldVal = formSelects.value('select-id10', 'nameStr') || bool;

        if (fieldVal == '' || fieldVal == undefined) {
            $('.origin-contribution .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#f56c6c' });
            $('.origin-industry-prompt span').addClass('show').removeClass('hidden');
            originIndustryFlag = false;
        }
        else {
            $('.origin-contribution .xm-select-parent .xm-input').eq(0).css({ 'border-color': '#67c23a' });
            $('.origin-industry-prompt span').addClass('hidden').removeClass('show');
            originIndustryFlag = true;
        }
    }

    //普通需求登录后修改了默认手机号,显示手机验证码,图形验证码;
    $('.phone-number .demand-call').on('change', function(){
        $('.graph-validate').show();
        $('.phone-validate').show();
        login = false;
    });

    var keys = [];
    //普通需求表单提交
    $('.submit').on('click', function(){
        handleName();
        handleScreen();
        handleForm();
        handleField();
        handleBudget();
        handleDay();
        handleImg();
        handleCall();
        handlePull();
        handleIndustry();
        handleIPForm();
        handleIPIndustry();
        handlePlatform();
        handleMarketDay();
        handleMarketIndustry();
        handleMarketPlatform();
        handleFans();
        handleExpectedPrice();
        handleExpectedAuthor();
        handlePerformance();
        handleExpectAccount();
        // handleWordNum();
        handleWorkPrice();
        handleWorkNum();
        handleEnd();
        handleMarketingPrice();
        handleOriginIndustry();

        var contentForms = [];
        $('.demand-checked input').each(function(){
            if($(this).prop('checked')){
                contentForms.push($(this).val());
            }
        });
        if($('.demand-type input').first().prop('checked')){
            var madeValidate = nameFlag && dayFlag && budgetFlag && callFlag && screenFlag && formFlag && fieldFlag && industryFlag && expectedPriceFlag;

            if(madeValidate && ((imgFlag && pullFlag) || login) && (localStorage.getItem('isPubIDs') == 1 || (expectedAuthorFlag))){
                if(!phoneInvalidateFlag && !login){
                    $('.pull-prompt span').eq(1).addClass('show').removeClass('hidden').siblings().addClass('hidden');
                    return false;
                }
                $('.submit i').css({'display': 'inline-block'});
                $('.submit').attr('disabled',"true");
                $('#start').trigger('click');
            }
        }
        if($('.demand-type input').eq(1).prop('checked')){
            var originValidate = nameFlag && workPriceFlag && callFlag && workNumFlag && endFlag && originIndustryFlag;

            if(originValidate && ((imgFlag && pullFlag) || login)){
                if(!phoneInvalidateFlag && !login){
                    $('.pull-prompt span').eq(1).addClass('show').removeClass('hidden').siblings().addClass('hidden');
                    return false;
                }
                $('#start2').trigger('click');
                $('.submit').attr('disabled',"true");
                $('.submit i').css({'display': 'inline-block'});
            }
        }
        if($('.demand-type input').eq(2).prop('checked')){
            var IPValidate = nameFlag && budgetFlag && callFlag && IPForm && IPIndustryFlag;

            if(IPValidate && ((imgFlag && pullFlag) || login)){
                if(!phoneInvalidateFlag && !login){
                    $('.pull-prompt span').eq(1).addClass('show').removeClass('hidden').siblings().addClass('hidden');
                    return false;
                }
                $('.submit').attr('disabled',"true");
                $('.submit i').css({'display': 'inline-block'});
                var data = {
                    mobile: $.trim($('.demand-call').val()),
                    smsCode: $.trim($('.demand-push').val()),
                    demandName: $.trim($('.demand-name').val()),
                    demandTypeIndex: $('input[name="type"]:checked ').val().toString(),
                    budgetMoney: $.trim($('.demand-price').val()),
                    remark: $.trim($('.demand-text textarea').val()),
                    contentForms: contentForms.join(','),
                    yrCategory: formSelects.value('select-id9', 'nameStr')
                };

                commonFun.commonAjax('/demand/demand_save', 'post', data, iPTypeSuccess);
                function iPTypeSuccess(res){
                    $('.submit').removeAttr("disabled");
                    $('.submit i').css({'display': 'none'});
                    if(res.status == 1){
                        if(res.status == 1){
                            if(login){
                                location.href = centerUrl +  'buyerDemand/buyerDemandArgument';
                            }else {
                                layer.open({
                                    title: '提示',
                                    content: '恭喜你, 提交成功!',
                                    shade: 0
                                });
                                window.setTimeout(function(){
                                    location.reload();
                                }, 2000);
                            }
                        }
                    }else{
                        layer.msg(res.msg);
                    }
                }
            }
        }
        if($('.demand-type input').eq(3).prop('checked')){
            var marketingValidate = nameFlag && budgetFlag && callFlag && marketDayFlag  && marketIndustryFlag && platformFlag && marketPlatformFlag  && fansFlag && marketingPriceFlag ;

            if(marketingValidate && ((imgFlag && pullFlag) || login) && (localStorage.getItem('isPubIDs') == 1 || (expectAccountFlag))){
                if(!phoneInvalidateFlag && !login){
                    $('.pull-prompt span').eq(1).addClass('show').removeClass('hidden').siblings().addClass('hidden');
                    return false;
                }
                $('#start1').trigger('click');
                $('.submit').attr('disabled',"true");
                $('.submit i').css({'display': 'inline-block'});
            }
        }
    });

    //定制内容tab表单提交--在附件上传后提交
    function madeTypeSubmit(){
        var expectOfferData;
        var ary = [];
        $('.made-type .expected-price input').each(function(){
            if($.trim($(this).val()) != ''){
                ary.push($(this).val());
            }
        });
        expectOfferData = ary.join('_-,-_');

        var data = {
            mobile: $.trim($('.demand-call').val()),
            smsCode: $.trim($('.demand-push').val()),
            demandName: $.trim($('.demand-name').val()),
            demandTypeIndex: $('input[name="type"]:checked ').val().toString(),
            budgetMoney: $.trim($('.demand-price').val()),
            remark: $.trim($('.demand-text textarea').val()),
            tradeId: Number(formSelects.value('select-id7', 'valStr')),
            attachment: keys.join(','),
            contentForms: formSelects.value('select-id2', 'nameStr'),
            yrCategory: formSelects.value('select-id3', 'nameStr'),
            scenes: formSelects.value('select-id1', 'nameStr'),
            expectedTime: $('#test1').val(),
            isShow: $('.made-type input[name="made-show"]:checked').val(),
            expectNum: $('.expected-authors input').val(),
            expectOffer: expectOfferData
        };
        if((/author_images/.test(document.referrer) || /author_detail/.test(document.referrer) || /cart_author/.test(document.referrer)) && localStorage.getItem('isPubIDs') == 1){
            data.shopCartIds = localStorage.getItem('shoppingCartId');
            data.isShow = 0;
        }

        commonFun.commonAjax('/demand/demand_save', 'post', data, madeTypeSuccess);
        function madeTypeSuccess(res){
            $('.submit').removeAttr("disabled");
            $('.submit i').css({'display': 'none'});
            if(res.status === 1){
                if(login){
                    location.href = centerUrl + 'buyerDemand/buyerDemandArgument';
                }
                else {
                    layer.open({
                        title: '提示'
                        ,content: '恭喜你, 提交成功!',
                        shade: 0
                    });
                    window.setTimeout(function(){
                        location.reload();
                    }, 2000);
                }
            }else {
                layer.msg(res.msg);
                // $('.pull-prompt span').eq(1).addClass('show').removeClass('hidden').siblings().addClass('hidden');
            }
        }
    }

    //原创征稿tab表单提交--在附件上传后提交
    function originProductSubmit(){
        var contentForms = [];
        $('.demand-checked input').each(function(item){
            if($(this).prop('checked')){
                contentForms.push($(this).val());
            }
        });

        var num1Val = $.trim($('.num1').val()), num2Val = $.trim($('.num2').val());
        var wordNumVal = '';
        if(num1Val){
            wordNumVal =  num1Val +'_'
        }
        if(num2Val){
            wordNumVal =  '_'+ num2Val;
        }
        if(num1Val && num2Val){
            wordNumVal =  num1Val +'_'+ num2Val;
        }
        var data = {
            mobile: $.trim($('.demand-call').val()),
            smsCode: $.trim($('.demand-push').val()),
            demandName: $.trim($('.demand-name').val()),
            demandTypeIndex: $('input[name="type"]:checked ').val().toString(),
            remark: $.trim($('.origin-contribution .demand-text textarea').val()),
            attachment: keys.join(','),
            contentForms: $('input[name="origin-form"]:checked ').val(),
            requireWordNum: wordNumVal,
            budgetMoney: $.trim($('.work-price .origin-input-item').val()),
            expectNum: $.trim($('.work-num .origin-input-item').val()),
            expectedTime: $('#test3').val(),
            isShow: $('input[name="origin-show"]:checked').val(),
            referURL: $.trim($('.consult-example input').val()),
            tradeId: Number(formSelects.value('select-id10', 'valStr'))
        };

        commonFun.commonAjax('/demand/demand_save', 'post', data, originProductSuccess);
        function originProductSuccess(res){
            $('.submit').removeAttr("disabled");
            $('.submit i').css({'display': 'none'});
            if(res.status == 1){
                if(res.status == 1){
                    if(login){
                        location.href = centerUrl +  'buyerDemand/buyerDemandArgument';
                    }else {
                        layer.open({
                            title: '提示',
                            content: '恭喜你, 提交成功!',
                            shade: 0
                        });
                        window.setTimeout(function(){
                            location.reload();
                        }, 2000);
                    }
                }
            }
            else {
                layer.msg(res.msg);
                // $('.pull-prompt span').eq(1).addClass('show').removeClass('hidden').siblings().addClass('hidden');
            }
        }
    }

    //营销分发tab表单提交--在附件上传后提交
    function marketingTypeSubmit(){
        var contentForms = [];
        $('.demand-checked input').each(function(item){
            if($(this).prop('checked')){
                contentForms.push($(this).val());
            }
        });

        var expectOfferData = [];
        $('.marketing-type .expected-price input').each(function(item){
            if($.trim($(this).val())){
                expectOfferData.push($(this).val());
            }
        });

        var data = {
            mobile: $.trim($('.demand-call').val()),
            smsCode: $.trim($('.demand-push').val()),
            demandName: $.trim($('.demand-name').val()),
            demandTypeIndex: $('input[name="type"]:checked ').val().toString(),
            budgetMoney: $.trim($('.demand-price').val()),
            remark: $.trim($('.demand-text textarea').val()),
            attachment: keys.join(','),
            platformName: contentForms.join(','),
            spreadTime: $('#test2').val(),
            yrCategory: formSelects.value('select-id6', 'nameStr'),
            fans: $.trim($('.demand-fans1').val()) +'_'+$.trim($('.demand-fans2').val()),
            tradeId: Number(formSelects.value('select-id8', 'valStr')),
            isShow: $('.marketing-type input[name="show"]:checked').val(),
            expectNum: $('.expected-account input').val(),
            expectOffer: expectOfferData.join('_-,-_')
        };
        if((/ip_trade_distribution/.test(document.referrer) || /cart_distribution/.test(document.referrer)) && localStorage.getItem('isPubIDs') == 1){
            data.shopCartIds = localStorage.getItem('shoppingCartId');
            data.isShow = 0;
        }

        commonFun.commonAjax('/demand/demand_save', 'post', data, marketingTypeSuccess);
        function marketingTypeSuccess(res){
            $('.submit').removeAttr("disabled");
            $('.submit i').css({'display': 'none'});

            if(res.status == 1){
                if(login){
                    location.href = centerUrl +  'buyerDemand/buyerDemandArgument';
                }else {
                    layer.open({
                        title: '提示'

                        ,content: '恭喜你, 提交成功!',
                        shade: 0
                    });
                    window.setTimeout(function(){
                        location.reload();
                    }, 2000);
                }
            }else {
                layer.msg(res.msg);
                // $('.pull-prompt span').eq(1).addClass('show').removeClass('hidden').siblings().addClass('hidden');
            }
        }
    }

    //QQ客服;
    $('.demand-details').on('click', function(e){
        var ary = e.target.className.split(' ');
        ary.forEach(function(item){
            if(item == 'qq'){
                $('#qq2').trigger('click');
            }
        });
    });

    $('.demand-type input').each(function(){
        if($(this).prop('checked')){
                $(this).prev().addClass('radio-inner-checked');
                $(this).parent().siblings().find('span').removeClass('radio-inner-checked');

                if($(this).val() == 1){
                    $('.made-type').show();
                    $('.IP-type').hide();
                    $('.marketing-type').hide();
                    $('.made-tip').show();
                    $('.market-tip').hide();
                }
                if($(this).val() == 2){
                    $('.made-type').hide();
                    $('.IP-type').show();
                    $('.marketing-type').hide();
                    // $('.text-tip span').hide();
                }
                if($(this).val() == 3){
                    $('.made-type').hide();
                    $('.IP-type').hide();
                    $('.marketing-type').show();
                    $('.made-tip').hide();
                    $('.market-tip').show();
                }

            }
        });

    //需求大厅是否展示radio切换
    $('.demand-show input').each(function(item){
        $(this).on('click', function() {
            if($(this).prop('checked')){
                $(this).prev().addClass('radio-inner-checked');
                $(this).parent().siblings().find('span').removeClass('radio-inner-checked');}
        })
    });



    //重置表单
    function formReset() {
        $('.demand-details input').not('input[type=radio],input[type=button],input[type=checkbox],input[privateAttr=name]').val('');
        $('.demand-details input').css({'border-color': '#ebeef2'});
        $('.demand-details .xm-select-parent .xm-input').css({'border-color': '#ebeef2'});

        $('.demand-details textarea').val('');
        $('.demand-details span').each(function(){
            if($(this).hasClass('show')){
                $(this).addClass('hidden');
            }
        });
    }

    //checkbox选中
    $('.demand-checked input').each(function(){
        $(this).on('click', function(){
            if($(this).prop('checked')){
                $(this).prev().addClass('checkbox-inner-checked');
            }else {
                $(this).prev().removeClass('checkbox-inner-checked');
            }
        })
    });

    //征稿要求的tip提示框(原创征稿)
    $('.origin-tip').on('click', function(){
        layer.open({
            type: 0,
            content:
            '<ul style="font-size: 12px;color:#64676a;">' +
            '<li style="overflow: hidden;"><div style="float:left;margin-right: 15px;color:#82868a;">1.</div><div style="float:left">家居类，跟软装、硬装等家居类相关的文章；</div></li>' +
            '<li style="overflow: hidden;"><div style="float:left;margin-right: 15px;color:#82868a;">2.</div><div style="float:left;width:360px;">文章要求言之有物，价值观正确，无任何反动言论；</div></li>' +
            '<p><span style="margin-right: 15px;color:#82868a;">3.</span>最好结合热点或热门家居风格；</p>' +
            '<li style="overflow: hidden;"><div style="float:left;margin-right: 15px;color:#82868a;">4.</div><div style="float:left;width:360px;">2000字左右，必须原创，未发布在任何平台或公众号，拒绝洗稿拼凑盗版；</div></li>' +
            '<p><span style="margin-right: 15px;color:#82868a;">5.</span>配图至少3张，要求高清无水印无版权纠纷，图片精美</p>' +
            '</ul>',
            area: ['430px', '280px'],
            title: '原创征稿样例',
        })
    });

    //需求描述的tip提示框(定制内容)
    $('.made-tip').on('click', function(){
        layer.open({
            type: 0,
            content:
            '<ul style="font-size: 12px;color:#64676a;">' +
            '<li style="overflow: hidden;"><div style="float:left;margin-right: 15px;color:#82868a;">产品名称:</div><div style="float:left">腾讯手游魂斗罗</div></li>' +
            '<li style="overflow: hidden;"><div style="float:left;margin-right: 15px;color:#82868a;">需求背景:</div><div style="float:left;width:360px;">新版本更新，可以参考官网或者游戏试玩http://hdl.qq.com/index.shtml?ADTAG=media.innerenter.qqcom.index_navigation</div></li>' +
            '<li style="overflow: hidden;"><div style="float:left;margin-right: 15px;color:#82868a;">创作重点:</div><div style="float:left;width:360px;">主打像素乱入，魂斗罗英雄变成像素风格，另外一点仍然是红蓝CP，卖点是IP元素和新关卡（赛博朋克）</div></li>' +
            '<p><span style="margin-right: 15px;color:#82868a;">推广目的:</span> 拉IP用户回流</p>' +
            '<p><span style="margin-right: 15px;color:#82868a;">受众人群:</span> 男性 年龄层25-40 魂斗罗IP用户，没有地域限制</p>' +
            '<p><span style="margin-right: 15px;color:#82868a;">创作要求:</span> 呈现魂斗罗新版本中的角色，视频画面均以像素风格来呈现</p>' +
            '</ul>',
            area: ['500px', '320px'],
            title: '需求描述',
        })
    });

    //需求描述的tip提示框(营销分发)
    $('.market-tip').on('click', function(){
        layer.open({
            type: 0,
            content:
            '<ul style="font-size: 12px;color:#64676a;">' +
            '<li style="overflow: hidden;"><div style="float:left;margin-right: 15px;color:#82868a;">需求背景:</div><div style="float:left;width:360px;">本次主题旨在向追求品质生活的目标人群，塑造以下效果： 购买东风标致的目标人群年轻、时尚、小资，对生活充满热情，并且追求一切美好事物。用这种生活态度带出东风标致车型，让目标人群对品牌好感度提升，达成情感共鸣</div></li>' +
            '<li style="overflow: hidden;"><div style="float:left;margin-right: 15px;color:#82868a;">目标人群:</div><div style="float:left;width:360px;">#享受生活#、#先觉体验#、#休闲娱乐#、#小资生活#主张自由的生活、对美好的事物有执着追求，目标人群25-35岁</div></li>' +
            '<li style="overflow: hidden;"><div style="float:left;margin-right: 15px;color:#82868a;">传播方向:</div><div style="float:left;width:360px;">从TA的享受生活方式入手或自媒体本身风格入手</div></li>' +
            '<li style="overflow: hidden;"><div style="float:left;margin-right: 15px;color:#82868a;">产品信息:</div><div style="float:left;width:360px;">1. 东风标致2008：T+STT高效动力、全景天幕玻璃顶、星际宇航驾驶空间、触控智能系统2. 金融政策：X008 SUV盛会 “0”息“0”税“0”元保养</div></li>' +
            '</ul>',
            area: ['500px', '370px'],
            title: '需求描述',
        })
    });

    /*
    快速需求的弹层表单
     */
    $('.demand-title .help').on('click', function(){

        layer.open({
            type: 1,
            title: '快速需求',
            area: ['300px', '380px'],
            fixed: true,
            success: function(layero, index){
                $('#img-fast').trigger('click');
            },
            // '</form>' +'<form>' +
            content:
            '<ul class="help-message">\n' +
            '<li class="fast-name">' +
            '<input type="text" placeholder="您的称呼(2-20字)" class="fast-name-input">' +
            '<span class="hidden">请输入称呼</span>' +
            '</li>\n' +
            '<li class="fast-type">' +
            '<select name="fast-type-select" class="fast-type-select" lay-verify="required">' +
            '<option value="">您需要什么</option>' +
            '<option value="1">找作者</option>' +
            '<option value="2">找代理权</option>' +
            '<option value="3">营销分发</option>' +
            '<option value="4">征稿</option>' +
            '</select>' +
            '<span class="hidden">请选择需求类型</span>' +
            '</li>' +
            '<li class="fast-phone">' +
            '<input type="text" placeholder="您的手机" onchange="handleFastPhone" class="fast-phone-input" maxlength="11">' +
            '<span class="hidden phone-null">请输入手机号</span>' +
            '<span class="hidden phone-error">请输入正确格式的手机号</span>' +
            '</li>\n' +
            '<li class="fast-img">' +
            '<input class="fast-img-input" style="width:160px;" type="text" placeholder="图形验证码" maxlength="4">' +
            '<img id="img-fast" style="width:81px;margin-left:9px;" alt="验证码" src="/verificationCode/getValidateImg" />' +
            '<span class="hidden">请输入图形验证码</span>' +
            '<span class="hidden">图形验证码错误</span>' +
            '</li>\n' +
            '<li class="fast-code">' +
            '<input class="fast-code-input" style="width:160px;" type="text" placeholder="手机验证码" maxlength="4">' +
            '<input class="fast-code-button" type="button" style="width:90px;margin-left:9px;" value="获取验证码">' +
            '<span class="hidden">请输入手机验证码</span>' +
            '<span class="hidden">短信验证码错误</span>' +
            '</li>\n' +
            '</ul>\n' +

            '<p class="help-submit">提交,让客服联系我</p>'
        });

        //若登录则隐藏图形验证码，手机验证码
        if(login){
            $('.fast-img-input').hide();
            $('.fast-code').hide();
            $('#img-fast').hide();
            $('.fast-phone-input').val(sessionStorage.getItem('mobile'));
        }

        //若登陆改变了默认手机号,则显示图形验证码, 手机验证码
        $('.fast-phone-input').on('change', function(){
            $('.fast-img-input').show();
            $('.fast-code').show();
            $('#img-fast').show();
        });

        //名称验证
        var fastName = $('.fast-name-input');
        var fastNameFlag;
        function handleFastName() {
            if ($.trim(fastName.val()) == '' || $.trim(fastName.val()) == undefined) {
                $('.fast-name span').addClass('show').removeClass('hidden');
            }else {
                $('.fast-name span').addClass('hidden').removeClass('show');
                fastNameFlag = true;
            }
        }
        fastName.on('blur', handleFastName);

        //类型验证
        var fastType = $('.fast-type-select');
        var fastTypeFlag;
        function handleFastType() {
            var fastTypeVal = fastType.val();

            if (fastTypeVal == '' || fastTypeVal == undefined) {
                $('.fast-type span').addClass('show').removeClass('hidden');
            }
            else {
                $('.fast-type span').addClass('hidden').removeClass('show');
                fastTypeFlag = true;
            }
        }
        fastType.on('change', handleFastType);

        //手机号验证
        var fastPhone = $('.fast-phone-input');
        var fastPhoneFlag;
        function handleFastPhone() {
            var fastPhoneVal = $.trim(fastPhone.val());
            var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(15[0-3]{1})|(15[4-9]{1})|(18[0-9]{1})|(199)|(166))+\d{8})$/;

            if (fastPhoneVal == '' || fastPhoneVal == undefined) {
                $('.fast-phone .phone-null').addClass('show').removeClass('hidden');
                return;
            }
            if(!reg.test(fastPhoneVal)){
                $('.fast-phone span').last().addClass('show').removeClass('hidden');
                $('.fast-phone .phone-null').addClass('hidden');
            }
            else {
                $('.fast-phone span').addClass('hidden').removeClass('show');
                fastPhoneFlag = true;
            }
        }
        fastPhone.on('blur', handleFastPhone);

        //图形码验证
        var fastImg = $('.fast-img-input');
        var fastImgFlag;

        function handleFastImg() {
            var fastImgVal = $.trim(fastImg.val());

            if (fastImgVal == '' || fastImgVal == undefined) {
                $('.fast-img span').first().addClass('show').removeClass('hidden');
            }else {
                $('.fast-img span').addClass('hidden').removeClass('show');
                fastImgFlag = true;
            }
        }
        fastImg.on('blur', handleFastImg);

        //手机验证码验证
        var fastCode = $('.fast-code-input');
        var fastCodeFlag;

        function handleFastCode() {
            var fastCodeVal = $.trim(fastCode.val());

            if (fastCodeVal == '' || fastCodeVal == undefined) {
                $('.fast-code span').first().addClass('show').removeClass('hidden');
            }
            else {
                $('.fast-code span').addClass('hidden').removeClass('show');
                fastCodeFlag = true;
            }
        }
        fastCode.on('blur', handleFastCode);

        //图片验证码切换
        $('#img-fast').on('click', function(){
            var imgSrc = $("#img-fast");
            var src = imgSrc.attr("src");
            imgSrc.attr("src", changeUrl1(src));
        });
        //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
        function changeUrl1(url) {
            var timestamp = (new Date()).valueOf();
            var index = url.indexOf("?",url);
            if (index > 0) {
                url = url.substring(0, url.indexOf("?"));
            }
            url = url + "?timestamp=" + timestamp;
            return url;
        }

        //手机验证码获取
        $('.fast-code-button').on('click', function(){
            var countdown =60;
            var that = $(this);

            if(fastImgFlag && fastPhoneFlag){
                $.ajax({
                    url: domain + '/verificationCode/sendMessage',
                    type: 'post',
                    data: {
                        mobile: $.trim(fastPhone.val()),
                        imgCode: $.trim(fastImg.val())
                    },
                    dataType: 'json',
                    crossDomain: true,
                    success: function(res){
                        if(res.status == 1){
                            var codeTimer = setInterval(function() {
                                if (countdown == 1) {
                                    that.attr('disabled', false).css({ 'color': '#346bd1' }).val("获取验证码");
                                    countdown = 60;
                                    clearInterval(codeTimer);
                                    return false;
                                } else {
                                    that.attr("disabled", true).css({ 'color': '#707070' }).val(countdown + "s重新发送");
                                    countdown--;
                                }
                            },1000);
                        }else {
                            $('.fast-img span').last().addClass('show').removeClass('hidden');
                        }
                    }
                });
            }
        });

        //表单提交
        $('.help-submit').on('click', function(){
            handleFastName();
            handleFastPhone();
            handleFastType();
            if(!login){
                handleFastImg();
                handleFastCode();
            }
            if(fastNameFlag && fastTypeFlag && fastPhoneFlag && ((fastImgFlag && fastCodeFlag) || login) ){
                $.ajax({
                    url: domain + '/demandFast/demandFast_save',
                    type: 'post',
                    data: {
                        nickname: $.trim(fastName.val()),
                        demandTypeIndex: fastType.val(),
                        mobile: $.trim(fastPhone.val()),
                        smsCode: $.trim(fastCode.val())
                    },
                    dataType: 'json',
                    crossDomain: true,
                    success: function(res){
                        if(res.status == 1){
                            layer.close(layer.index);
                            layer.open({
                                title: '提示'
                                ,content: '恭喜你, 提交成功!',
                                shade: 0,
                                time: 3000
                            });
                        }else {
                            $('.fast-code span').last().addClass('show').removeClass('hidden');
                        }
                    }
                });
            }

        })

    });

    /*
    需求附件上传
    */
    initQiNiu('uploadasCardPhoto','containerQiniuCardPhoto', '#qiniuList', '#start');
    initQiNiu('uploadasCardPhoto1','containerQiniuCardPhoto1', '#qiniuList1', '#start1');
    initQiNiu('uploadasCardPhoto2','containerQiniuCardPhoto2', '#qiniuList2', '#start2');
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
            auto_start: false,
            total: 2,
            init: {
                'FilesAdded': function(up, files) {
                    plupload.each(files, function (file) {
                        // 文件添加进队列后,处理相关的事情
                       if(up.files.length <= 5) {
                           var tr = $(['<tr style="text-align: center;">'
                               ,'<td>'+ file.name +'</td>'
                               ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                               // ,'<td>等待上传</td>'
                               ,'<td>'
                               ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
                               ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete" style="font-size:12px;">删除</button>'
                               ,'</td>'
                               ,'</tr>'].join(''));
                           $(list).append(tr);
                       }
                    });
                    $(list + ' .demo-delete').each(function(index, item){
                        $(this).on('click', function(){
                            $(this).parent().parent().remove();
                            up.splice(index, 1);
                        })
                    })
                },
                'BeforeUpload': function(up, file) {
                },
                'UploadProgress': function(up, file) {
                },
                'FileUploaded': function(up, file, info) {
                    keys.push(qnStorageDomain + JSON.parse(info).key);
                },
                'UploadComplete': function() {
                    if(start === '#start'){
                        madeTypeSubmit();
                    }if(start === '#start2'){
                        originProductSubmit();
                    }if(start === '#start1'){
                        marketingTypeSubmit();
                    }
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
    }

    //时间控件
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        laydate.render({
            elem: '#test1', //指定元素
            theme: '#3c94ef',
            type: 'date',
            min: 1,
            done: function(value, date, endDate){
                dayVal = value;
                // handleDay();
                if(value){
                    handleDay()
                }
                $('#test1').val(value);
              //得到日期生成的值，如：2017-08-18
            }
        });
        laydate.render({
            elem: '#test2', //指定元素
            theme: '#3c94ef',
            type: 'date',
            min: 1,
            done: function(value){
                dayVal = value;
                if(value){
                    handleMarketDay();
                }
                $('#test2').val(value);
            }
        });
        laydate.render({
            elem: '#test3', //指定元素
            theme: '#3c94ef',
            type: 'date',
            min: 1,
            done: function(value){
                dayVal = value;
                if(value){
                    handleEnd();
                }
                $('#test3').val(value);
            }
        });
    });

    // //form
    // layui.use('form', function(){
    //     var form = layui.form
    // });
    //千分符
    function thousandCharacter(val){
        const reg=/(\d{1,3})(?=(\d{3})+(?:$|\.))/g;
        return val ? val.toString().replace(reg, '$1,') : '--';
    }

    //粉丝数大于万的以万为单位;
    function unitFormat(val) {
        return parseInt(val) > 10000 ? thousandCharacter(val / 10000) + '万' : thousandCharacter(val);
    }

    //增加期望报价项;
    function deletePrice(ele){
        $(ele + '.expected-price .add-price-icon').on('click', function(){
            if($(ele + '.dele-price-icon').length >= 2){
                return ;
            }
            $('<div class="new-price-item">' +
                '<input class="demand-price fl" placeholder="请输入" type="text" maxlength="50" value=""><span class="dele-price-icon">&nbsp;删除</span>' +
                '</div>').appendTo($(ele + '.expected-price'));
            //将删除写在里面;
            $(ele + '.dele-price-icon').on('click', function(){
                $(this).parent().remove();
            });
        });
    }
    deletePrice('.made-type ');
    deletePrice('.marketing-type ');

    //侧边栏跳转
    $('.problem-info ul li').eq(0).on('click', function(){
        window.open(centerUrl + 'helpcenter/platformguide/guide/buyerguide');
    });
    $('.problem-info ul li').eq(1).on('click', function(){
        window.open(centerUrl + 'helpcenter/paymentsettlement/paymentsettlement#buyer');
    });
    $('.problem-info ul li').eq(2).on('click', function(){
        window.open(centerUrl + 'helpcenter/paymentsettlement/paymentsettlement#refundAndAfterSale');
    });
    $('.problem-info ul li').eq(3).on('click', function(){
        window.open(centerUrl + 'helpcenter/paymentsettlement/paymentsettlement#copyRight');
    });

    $('.right-bottom ul').on('click', function(){
        window.open(centerUrl + 'helpcenter/platformguide/guide/sellerguide');
    })
});
    
    
