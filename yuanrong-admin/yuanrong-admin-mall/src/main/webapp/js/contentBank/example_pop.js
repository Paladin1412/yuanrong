

//买断价弹出框

function a(i){
$('.btn_'+ i ).addClass('hide');
$('.btn_'+ (i+1) ).removeClass('hide');
$('.pop-main .nextContent').eq(i-2).addClass('hide');
if(i==4){
    $('.btn_'+ (i-2) ).removeClass('hide');
    $('.pop-main .nextContent').eq(i-4).removeClass('hide');
}else {
    $('.pop-main .nextContent').eq(i-1).removeClass('hide');
}
}

$('.close').click(function(){
$('.pop-main').addClass('hide');
})

// 屏幕高度
var pmHeight = document.documentElement.clientHeight || document.body.clientHeight;
$('#js-example-main-wrap').css({'max-Height':pmHeight*(2/3)+'px'});