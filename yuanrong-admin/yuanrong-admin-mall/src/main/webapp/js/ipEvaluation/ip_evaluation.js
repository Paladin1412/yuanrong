//排行tab切换
var rankingTabWrapDom = $('#js-ranking-tab');//排行榜ul
$('li', rankingTabWrapDom).mouseenter(function(){
    var $this = $(this);
    $('li', rankingTabWrapDom).removeClass('active').find('.banner-opa-li').removeClass('banner-opa-li-active');
    $this.addClass('active').find('.banner-opa-li').addClass('banner-opa-li-active');
}).mouseleave(function(){
    var $this = $(this);
    $this.removeClass('active').find('.banner-opa-li').removeClass('banner-opa-li-active');
})
