// // rem 页面自适应
// (function (doc, win) {
//     var docEl = doc.documentElement,
//         resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
//         recalc = function () {
//             var clientWidth = docEl.clientWidth;
//             if (!clientWidth) return;
//             if(clientWidth>=640){
//                 docEl.style.fontSize = '100px';
//             }else{
//                 docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
//             }
//         };

//     if (!doc.addEventListener) return;
//     win.addEventListener(resizeEvt, recalc, false);
//     doc.addEventListener('DOMContentLoaded', recalc, false);
// })(document, window);

//首页整屏适配
//点击登录
$(document).ready(function(){
	// var domain = $('#domain').val();
	calcFirstDiv();
	function calcFirstDiv(){
		var phoneWidth = $(window).width();
		var phoneHeight = $(window).height();
		$('.banner').css({'width':phoneWidth,'height':phoneHeight});
		if(phoneWidth < 360){
			$('.head-r').css('width', '68px');
			$('.head-r p').eq(0).css('margin-bottom', '6px');
			$('.head-r span').hide();
		}
	}
	$(window).resize(function(){
		calcFirstDiv();
	})
	//点击登陆
	$('#login-btn').on('click',function(){
		var phone = $('#telep').val(),
			code = $('#code').val(),
			smsCode = $('#phone-code').val();

		if(phone == '') {
			layer.msg('请输入手机号码！', {time: 1500});
			return false;
		}
		if(code == '') {
			layer.msg('请输入验证码！', {time: 1500});
			return false;
		}
		if(smsCode == '') {
			layer.msg('请输入手机验证码', {time: 1500});
			return false;
		}
		$.ajax({
			//domain+
			url: '/registeredUserInfo/validateCodeVerification',
			type: 'post',
			dataType: 'json',
			cache: true,
            xhrFields: {
                withCredentials: true
            },
			data: {
				smsCode: smsCode,
				code: code
			},
			success:function(data) {
				console.log(data);
				if(data.status == 'fail') {
					console.log('手机验证码错误！');
					layer.msg('手机验证码错误！', {time: 1500});
					$('#img').trigger('click');
					return false;
				}else {
					layer.msg('入住成功！', {time: 1500});
					
					// 更改首页用户登录信息
					var name = data.data.data[0].UserName;
					console.log(name);
                    if(commonFun.telValid(name)){
                        var nameFilter = name.slice(0, 3) + '****' + name.slice(7, 11);
                        $('#phone').text('您好, '+ nameFilter +'!');
                    }else {
                        $('#phone').text('您好, '+ name +'!');
                    }
                    $('div.head-r :nth-child(3)').hide();
                    var $a = $('div.head-r :nth-child(2)');
                    console.log($a);
                   	window.location =  'http://localhost:8080/h5/index.html';
				}
			},
			error: function() {
				layer.msg('验证码错误!', {time: 1500});
			}
		})
	})
	//获取手机验证码并验证图形验证码
	$('#get-phone-code').on('click',function(){
		$('#phone-code')[0].value = null;
		var code = $('#code');
		var phone = $('#telep').val();
		if(phone == '') {
			layer.msg('请输入手机号码！', {time: 1500});
			return false;
		}
		if(commonFun.telValid($.trim(phone)) == false) {
			layer.msg('请输入正确的手机号！', {time: 1500});
            return false;
		}
		if(code.val() == '') {
			layer.msg('请输入图片验证码！', {time: 1500});
            return false;
		}
		$.ajax({
			url: 'http://localhost:8080/registeredUserInfo/getSMS',
			type: 'post',
			dataType: 'json',
			data: {
				phone: phone,
				code: code.val()			
			},
			success: function(data) {
				if(data.status == 'fail'){
					layer.msg('图形验证码错误！', {time: 1500});
					$('#img').trigger('click');
				}else {
					var codeButton = $('.get-txt');
					var countdown = 60;

					var time = setInterval(function() {
						if(countdown == 1) {
							codeButton.attr("disabled",false).css({'color':'#4074d4'}).val("获取验证码");
							countdown = 60;
							clearInterval(time);
							return false;
						} else {
							codeButton.attr("disabled", true).css({ 'color': '#707070' }).val( countdown + "秒后重新获取");
                            countdown--;
						}						
					},1000);
				}
			},
			error: function() {
				layer.msg('验证码错误！', {time: 1500});
			}
		});
	})
})	


