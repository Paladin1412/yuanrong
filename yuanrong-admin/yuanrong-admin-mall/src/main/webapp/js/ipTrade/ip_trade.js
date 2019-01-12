$(document).ready(function(){
    //代理权交易添加tab class
    $('#js-tab-ip-trade-wrap').find('li').eq(0).addClass('active');
    $('#js-tab-ip-trade-wrap').css('margin-bottom', '20px');
    // 控制代理权交易中微信号的出现
    $('.author-weixin').mouseenter(function(){
        $('.author-weixin>img').removeClass('hide');
    }).mouseleave(function(){
        $('.author-weixin>img').addClass('hide');
    })
    //点击首页登陆
    var loginPopWrapDom = $('#js-login-pop-wrap');//登陆弹框
    $('.agent-join').on('click', function(){
        var $this = $(this);
        var dataType = $this.data('type');
        loginPopWrapDom.show();
    })



    // 代理权交易点击切换样式
    // var flag = true;
    // var flag1 = true;
    // $('.left').click(function(e){
    //     e.stopPropagation();//阻止事件冒泡即可  
    //     if(flag) {
    //         $('.left .author-information-details').css({'display':'block'});
    //         $('.left').css({'height':'568px'});
    //         $('.left .author-zhankai img').attr('src',domain + '/images/commonFragments/arr_small_up.png');
    //         flag = false;
    //     } else {
    //         $('.left .author-information-details').css({'display':'none'});
    //         $('.left').css({'height':'310px'});
    //         $('.left .author-zhankai img').attr('src',domain + '/images/commonFragments/arr_small_down.png');
    //         flag = true;
    //     }
    // }).mouseleave(function(){
    //     $('.left .author-information-details').css({'display':'none'});
    //     $('.left').css({'height':'310px'});
    //     $('.left .author-zhankai img').attr('src',domain + '/images/commonFragments/arr_small_down.png');
    //     flag = true;
    // })
    
    // $('.right').click(function(e){
    //     e.stopPropagation();//阻止事件冒泡即可 
    //     if(flag1) {
    //         $('.right .author-information-details').css({'display':'block'});
    //         $('.right').css({'height':'490px'});
    //         $('.right .author-zhankai img').attr('src',domain + '/images/commonFragments/arr_small_up.png');
    //         flag1 = false;  
    //     } else {
    //         $('.right .author-information-details').css({'display':'none'});
    //         $('.right .author-zhankai img').attr('src',domain + '/images/commonFragments/arr_small_down.png');
    //         $('.right').css({'height':'310px'});
    //         flag1 = true;
    //     }
    // }).mouseleave(function(){
    //      $('.right .author-information-details').css({'display':'none'});
    //      $('.right .author-zhankai img').attr('src',domain + '/images/commonFragments/arr_small_down.png');
    //      $('.right').css({'height':'310px'});
    //      flag1 = true;
    // })


    // 代理权交易点击切换样式
    function clickPlan(tagName) {
        var flag = true;
        var details = $('.' + tagName + ' .author-information-details');
        var thisTag = $('.' + tagName);
        var openImg = $('.' + tagName + ' .author-zhankai img');
        function hidePlan() {
            details.addClass('hide');
            thisTag.css({'height': '310px'});
            openImg.attr('src',domain + '/images/commonFragments/arr_small_down.png');
            flag = true;
        }
        $('.' + tagName).click (function () {
            if(flag) {
                details.removeClass('hide');
                var height = details.height();
                thisTag.css({'height': height + 310 + 'px'});
                openImg.attr('src',domain + '/images/commonFragments/arr_small_up.png');
                flag = false;
            } else {
                hidePlan();
            }
        }).mouseleave (function () {
            hidePlan();
        })
    }
    clickPlan('left');
    clickPlan('right');

   

    // 代理权交易lihover效果
    $('.agent-list ul li').hover(function(){
        $(this).siblings().find('.agent-list-communication').addClass('hide');
        $(this).find('.agent-list-communication').removeClass('hide');
    },function () {
        $(this).find('.agent-list-communication').addClass('hide');
    })

     //QQ客服
    $('#content').on('click', function(e){
        var ary = e.target.className.split(' ')
        ary.forEach(function(item){
            if(item == 'qq'){
                $('#qq2').trigger('click');
            }
        });
    })

    // 切换点击QQ客服
    $('.author-communication').click(function(){
        // e.stopPropagation();//阻止事件冒泡即可 
        $('#div_imqq1').trigger('click');
    })
})
