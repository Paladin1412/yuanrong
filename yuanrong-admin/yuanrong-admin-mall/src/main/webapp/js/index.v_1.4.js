
var isLoginFlag = false;
var getLoginStatus = function (isLogin) {
    if(isLogin == 'Y'){
        isLoginFlag = true;
        $('#js-index-side-login').hide();
    } else {
        $('#js-index-side-login').show();
        isLoginFlag = false;
    }
    console.log(isLoginFlag, '--isLoginFlag')
};
console.log(isLoginFlag, '--isLoginFlag-index')
getUserLoginInfo(getLoginStatus);


// commonFun.commonAjax(domainCenter +'/userManagement/loginInfo' , 'post', '', function(res){
//     var sucData = res.data;
//     if(res.status == 1){
//         isLoginFlag = true;
//         var userMobile = sucData.mobile;
//         var handleMoblie = userMobile.slice(0,3) + '****' + userMobile.slice(7, 11);
//         $('.phone').text('您好, '+ handleMoblie +'!').show();
//         $('.login-out').show();
//     } else {
//         $('.phone').text('').hide();
//         $('.login-out').hide();
//     }
// },function(error){
//     console.log(error);
// });
$(document).ready(function(){
    var loginPopWrapDom = $('#js-login-pop-wrap');//登陆弹框
    var loginDoneWrap = $('#js-login-done-wrap');//已登录弹框
    var loginTitDom = $('#js-longin-tit');//首页登陆
    var closeLoginPopBtnDom = $('.close-login-pop-btn');//登陆弹框关闭按钮
    var loginPopBtnDom = $('#js-login-btn');//登陆弹框登陆按钮
    var loginWrapDom = $('#js-login-wrap');//登陆—媒体主，广告主
    var bannerWrapDom = $('#js-banner-wrap');//banner wrap
    var swiperContainerWrapDom = $('#js-swiper-container-wrap');//轮播图imgwrap
    var bannerTxtWrapDom = $('#js-banner-li1-tit-wrap');//banner文字wrap
    var winWidth = $(window).width();//获取屏幕的宽度
    var bannerHei = (600 / 1920) * winWidth;//获取banner的height
    var bannerSubNavWrapDom = $('#js-banner-sub-nav-wrap');//导航下方的nav
    var secWrapDom = $('#js-sec-wrap');//全链条内容交易服务wrap
    var fourWrapDom = $('#js-four-wrap');//优质资源合作
    var fourWrapDomHei = (700 / 1920) * winWidth;//优质资源模块高度
    var dataType;
    var source;
    var thirdSortWrapUlDom = $('#js-third-sort-wrap');//权威价值ul
    var fiveWrapDommHei = (596 / 1920) * winWidth;//优质资源模块高度
    var fiveWrapDom = $('#js-five-wrap');//发现价值模块
    var joinBtn1 = $('#js-join-btn1');//广告主入驻
    var joinBtn2 = $('#js-join-btn2');//创作者入驻
    var timer = null;//倒计时
    var countdown = 60;
    //点击首页登陆
    $('span', loginWrapDom).off('click').on('click', function(){
        var $this = $(this);
        dataType = $this.data('type');
        $('#js-nav-wrap').children().each(function(){
           if ($(this).hasClass('active')){
               source = $(this).find('a').text();
           }
        });
        goLoginPage(dataType);
    })
    //全链条动态加载显示
    toAddClass();
    function toAddClass(){
        bannerTxtWrapDom.addClass('active');
        secWrapDom.find('.sec-slogan-wrap').addClass('active');
    }

    //点击弹框联系客服
    $('.login-input-wrap .service').on('click', function(){
        $('#div_imqq1').trigger('click');
        setTimeout(function(){
            loginDoneWrap.hide();
        }, 1500);
    })
    //判断是否登录;
    // getSessInfoFun();
    function getSessInfoFun(){
        var user = sessionStorage.getItem('user') || '';
        if(user){
            var phone = user.slice(0,3) + '****' + user.slice(7, 11);
            $('.phone').text('您好, '+ phone);
            $('.login-out').show();
            // $('#js-login-wrap').children().each(function(index){
            //     if(index <= 2){
            //         $(this).hide();
            //     }
            //     if(index >= 3 && index <= 6){
            //         $(this).show();
            //     }
            // });
        }
    }
    //根据是否登录显示弹框
    function sessionLoginFun(){
        var user = sessionStorage.getItem('user') || '';
        if(user){
            loginDoneWrap.show();
        } else {
            clearLoginPopFun();
            loginPopWrapDom.show();
        }
    }
    function clearLoginPopFun(){
        $('#tel').val('');
        $('#code').val('');
        $('#phone-code').val('');
        clearCountDownFun();
    }
    function clearCountDownFun(){
        $('#js-code-btn').attr("disabled",false).css({ 'color': '#4074d4' }).val("获取验证码");
        countdown = 60;
        clearTimeout(timer);
    }
    //点击登出;
    $('.login-out').on('click', function(){
        // window.location.href = newDomain + '/registeredUserInfo/logout';//8084
        // window.location.href = 'http://mach.tech.xyauto.com:8084/';
        commonFun.commonAjax(domainCenter + '/userManagement/logout' , 'post', '', function(res){
            if(res.status == 1){
                isLoginFlag = false
                sessionStorage.removeItem('user');
                window.location.href = toIndex;
            } else {
                layer.msg('登出失败！', {time: 1500});
            }
        },function(error){
            console.log(error);
        });
        // $.ajax({
        //     url: domainCenter + '/userManagement/logout',//8083
        //     type: 'get',
        //     dataType: 'json',
        //     success: function(data){
        //         if(data.status == 1){
        //             isLoginFlag = false
        //             sessionStorage.removeItem('user');
        //             // location.reload();
        //         }else{
        //             layer.msg('登出失败！', {time: 1500});
        //         }
        //     }
        // });

    });

    //banner下方的nav
    $('li', bannerSubNavWrapDom).mouseenter(function(){
        $('#qidian_wpa_2852157214_29').click();
        var $this = $(this);
        $('li', bannerSubNavWrapDom).removeClass('active').find('.banner-opa-li').removeClass('banner-opa-li-active');
        $this.addClass('active').find('.banner-opa-li').addClass('banner-opa-li-active');
    }).mouseleave(function(){
        var $this = $(this);
        $this.removeClass('active').find('.banner-opa-li').removeClass('banner-opa-li-active');
    })
    //全链条交易遮罩层
    secWrapDom.find('.sec-sort-wrap li').mouseenter(function(){
        var $this = $(this);
        $this.addClass('active');
        $this.find('.sec-cover-wrap').addClass('sec-cover-active-wrap');
        $this.find('.sec-txt-wrap').find('p').hide();
        $this.find('.sec-txt-wrap').find('.sec-learn-more').show();
    }).mouseleave(function(){
        var $this = $(this);
        $this.removeClass('active');
        $this.find('.sec-cover-wrap').removeClass('sec-cover-active-wrap');
        $this.find('.sec-txt-wrap').find('p').show();
        $this.find('.sec-txt-wrap').find('.sec-learn-more').hide();
    })
    //权威价值评估体系Ul悬停
    thirdSortWrapUlDom.find('.third-sort-txt-con-wrap').mouseenter(function(){
        var $this = $(this);
        $this.addClass('active');
        $this.find('.third-txt-bg-img').addClass('active');
    }).mouseleave(function(){
        var $this = $(this);
        $this.removeClass('active');
        $this.find('.third-txt-bg-img').removeClass('active');
    })
    //点击“我要入驻”
    joinBtn1.off('click').on('click', function(){
        var $this = $(this),
            type = $this.data('type'),
            id = $this.data('id');
            goLoginPage(id);
    })

    // 侧边栏在线客服登录
     $(".index-side-login").off('click').on('click', function(){
        var $this = $(this),
            type = $this.data('type'),
            id = $this.data('id');
        // sessionLoginFun();
        // goLoginPage(id);
        if(isLoginFlag){
            loginDoneWrap.show();
        } else {
            goLoginPage(-1);
        }
    })


    joinBtn2.off('click').on('click', function(){
        var $this = $(this),
            type = $this.data('type'),
            id = $this.data('id');
            goLoginPage(id);
    })
    //点击关闭登陆弹框
    closeLoginPopBtnDom.off('click').on('click', function(){
        loginPopWrapDom.hide();
        loginDoneWrap.hide();
    })
    //点击登陆
    loginPopBtnDom.off('click').on('click', function(){
        var phone = $.trim($('#tel').val()),
            code = $.trim($('#code').val()),
            smsCode = $.trim($('#phone-code').val());

            postUrl = domain + '/ip/getIPLists';
        if(phone == ''){
            layer.msg('请输入电话号码！', {time: 1500});
            return false;
        }
        if(commonFun.telValid($.trim(phone)) == false ){
            layer.msg('请填写正确的手机号！', {time: 1500});
            return false;
        }
        if(code == ''){
            layer.msg('请输入图形验证码！', {time: 1500});
            return false;
        }
        if(smsCode == ''){
            layer.msg('请输入验证码！', {time: 1500});
            return false;
        }
        if(phone.length == 11 && code.length == 4){
            commonFun.commonAjax(postUrl, 'get', '', function(data){
                console.log(data)
            },function(error){
                console.log(error);
            });
        }
        if(commonFun.telValid(phone) && code.length == 4 && smsCode.length  == 4){
            $.ajax({
                url: domain + '/registeredUserInfo/validateCodeVerification',
                type: 'post',
                dataType: 'json',
                cache: true,
                xhrFields: {
                    withCredentials: true
                },
                crossDomain: true,
                data: {
                    'smsCode': smsCode,
                    'code': code,
                    'source': source,
                    'role': dataType
                },
                async:false,
                success: function(data){
                    if (data.status == 'fail'){
                        layer.msg('手机验证码输入错误！', {time: 1500});
                        $('#img').trigger('click');
                        clearCountDownFun();
                    }else {
                        console.log('10101001')
                        $('.phone-number').addClass('input-hidden');
                        $('.graph-validate').addClass('input-hidden');
                        $('.phone-validate').addClass('input-hidden');

                        var name = data.data.data[0].UserName;
                        console.log(name);
                        if(commonFun.telValid(name)){
                            var nameFilter = name.slice(0, 3) + '****' + name.slice(7, 11);
                            $('.phone').text('您好, '+ nameFilter +'!');
                        }else {
                            $('.phone').text('您好, '+ name +'!');
                        }


                        // $('#js-login-wrap').children().each(function(index){
                        //     if(index <= 2){
                        //         $(this).hide();
                        //     }
                        //     if(index >= 3 && index <= 6){
                        //         $(this).show();
                        //     }
                        // });
                        sessionStorage.setItem('user', phone);
                        loginPopWrapDom.hide();
                        getSessInfoFun();
                    }
                },
                error: function(){
                    layer.msg('验证码输入错误！', {time: 1500});
                    $('#img').trigger('click');
                    clearCountDownFun();
                }

            })
        }
    })

    //获取手机验证码并验证图形验证码;
    $('#js-code-btn').prop("onclick",null).off('click').on('click', function(){
        $('#phone-code')[0].value = null;
        var $this = $(this);
        var code = $('.code');
        var codeVal = $.trim(code.val());
        var codeLen = codeVal.length;
        var phone = $.trim($('#tel').val());

        if(commonFun.telValid(phone) == false ){
            layer.msg('请填写正确的手机号！', {time: 1500});
            return false;
        }
        if(codeVal === ''){
            layer.msg('请输入验证码！', {time: 1500});
            return false;
        }
        if(codeLen != 4){
            layer.msg('请输入正确的验证码！', {time: 1500});
            return false;
        }
        if(commonFun.telValid(phone) && codeLen == 4){
            $this.attr("disabled", true).css({ 'color': '#707070' });
            $.ajax({
                url: domain + '/registeredUserInfo/getSMS',
                type: 'post',
                dataType: 'json',
                data: {
                    'phone': phone,
                    'code': codeVal,
                    'source': source
                },
                success: function(data){
                    if(data.status == 'fail'){
                        layer.msg('图形验证码输入错误！', {time: 1500});
                        $('#img').trigger('click');
                        clearCountDownFun();
                    } else {
                        countDown($this);
                    }
                },
                error: function() {
                    layer.msg('验证码输入错误！', {time: 1500});
                    $('#img').trigger('click');
                    clearCountDownFun();
                }
            })
        }
    })
    //倒计时
    function countDown(btn){
        countdown --;
        if(countdown > 0){
            btn.attr("disabled", true).css({ 'color': '#707070' }).val( countdown + "秒后重新获取");
            timer = setTimeout(function() {
                countDown(btn);
            }, 1000);
        } else {
            btn.attr("disabled",false).css({ 'color': '#4074d4' }).val("获取验证码");
            countdown = 60;
            clearTimeout(timer);
        }
    }
});