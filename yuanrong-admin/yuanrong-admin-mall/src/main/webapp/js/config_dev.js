
var getLocationUrl = window.location.href;
if(getLocationUrl.indexOf('localhost') != -1){//开发
    
    var newDomain = $('#js-ajax-domain').val();
    //http://v6mall.t.ifi6.com        'http://mall.ifi6.com:81';//接口地址     'http://mall.ifi6.com:81';//后台更改接口地址， 接口地址     http://zhongh.tech.xyauto.com:8084

    var domain = $('#js-ajax-domain').val();
    //http://v6mall.t.ifi6.com   $('#js-ajax-domain').val();//一期接口地址   页面跳转路由domain拼接   $('#js-ajax-domain').val()   'http://c.ifi6.com:81'  页面跳转路由domain拼接


    var domainCenter = 'http://localhost:9098';//买家中心接口地址

    var centerUrl = 'http://localhost:9098/#/';//卖家中心

    var toIndex = '/';//跳转首页，页面地址
    // console.log(newDomain)
}
if(getLocationUrl.indexOf('t.ifi6.com') != -1){//测试
    //后台更改接口地址， 接口地址
    var newDomain = $('#js-ajax-domain').val();// http://api.yuanrongbank.com
    
    //一期接口地址以及页面地址
    var domain = $('#js-ajax-domain').val();//
    
    //买家中心接口地址
    //退出登陆，用户信息接口
    var domainCenter = 'http://my.t.ifi6.com';
    
    //卖家中心
    //跳转卖家中心
    var centerUrl = 'http://my.t.ifi6.com/#/';
    
    //跳转首页，页面地址
    var toIndex = 'http://mall.t.ifi6.com';
    
}
if(getLocationUrl.indexOf('.ifi6.com:81') != -1){//测试 81
    //后台更改接口地址， 接口地址
    var newDomain = $('#js-ajax-domain').val();// http://api.yuanrongbank.com
    
    //一期接口地址以及页面地址
    var domain = $('#js-ajax-domain').val();//
    
    //买家中心接口地址
    //退出登陆，用户信息接口
    var domainCenter = 'http://localhost:9098'//'http://my.ifi6.com:81';
    
    //卖家中心
    //跳转卖家中心
    var centerUrl ='http://localhost:9098/#/' //'http://my.ifi6.com:81/#/';
    
    //跳转首页，页面地址
    var toIndex = 'http://mall.ifi6.com：81';
    
}