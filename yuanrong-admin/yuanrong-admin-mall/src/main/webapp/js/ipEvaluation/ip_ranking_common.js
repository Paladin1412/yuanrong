$(document).ready(function() {
    var ipCategoryListDom = $('#js-ip-category-list');
    var timeSelectDom = $('#js-time-range');
    var rankType = $('.on').data('id');
    var moreBtnDom = $('#js-more-btn');//点击加载更多
    var listName = '';
    var listConWrapDom = $('#js-list-con-wrap');//ip创造力排行
    var cp = 1;
    var rows = 50;

    if(rankType == 1){//内容排行
        listName = 'listArticle';
    } else if(rankType == 2){//创作力排行
        listName = 'listIPCreativity';
    }
    getTimeList(listName);//获取时间列表
        
    //获取分类
    function getCategoryList() {
        var postUrl = newDomain + '/dictInfo/getDictInfoByType';
        ipCategoryListDom.html('');
        commonFun.commonAjax(postUrl, 'post', {'type': 13}, function(res){
            var categoryArr = res.data
            if(res.status == 1){
                var categoryHtml = ''
                for(var i = 0, len = categoryArr.length; i < len; i ++){
                    categoryHtml += '<li data-id="'+categoryArr[i].id+'"><a>'+categoryArr[i].name+'</a></li>'
                }
                ipCategoryListDom.append(categoryHtml)
                ipCategoryListDom.find('li').eq(0).addClass('ca-active');
                //点击分类
                $('li',ipCategoryListDom).off('click').on('click', function () {
                    var $this = $(this)
                    console.log($this)
                    $this.addClass('ca-active').siblings().removeClass('ca-active')
                    getData(cp, $('.ca-active').find('a').text(), timeSelectDom.val(), rankType);
                })
            }
            getData(cp, $('.ca-active').find('a').text(), timeSelectDom.val(), rankType);//获取内容列表
        }, function (error) {
            getData(cp, $('.ca-active').find('a').text(), timeSelectDom.val(), rankType);//获取内容列表
            console.log(error)
        })
    }
    //获取时间
    // var timeHtml = ''
    // var timeArr = ['3333','444','666']
    // for(var i = 0, len = timeArr.length; i < len; i ++){
    //     timeHtml += '<option value="'+timeArr[i]+'">'+timeArr[i]+'</option>'
    // }
    // timeSelectDom.append(timeHtml)
    function getTimeList(listName) {
        var postUrl = newDomain + '/baseRank/selectRangeTime';
        timeSelectDom.html('');
        //typeName = listArticle 文章排行榜时间范围 ，当typeName = listIPCreativity 
        commonFun.commonAjax(postUrl, 'post', {typeName: listName}, function(res){
            // console.log(res)
            var timeArr = res.data
            if(res.status == 1){
                var timeHtml = ''
                for(var i = 0, len = timeArr.length; i < len; i ++){
                    timeHtml += '<option value="'+timeArr[i]+'">'+timeArr[i]+'</option>'
                }
                timeSelectDom.append(timeHtml)
            }
            getCategoryList();//获取分类列表
        }, function (error) {
            console.log(error)
            getCategoryList();//获取分类列表
        })
    }
    //切换时间
    timeSelectDom.on('change', function () {
        var $this = $(this);
        getData(cp, $('.ca-active').find('a').text(), timeSelectDom.val(), rankType);
    })
    //获取内容价值列表
	function getData(cp, name, time, type) {
        if(type == 1){//内容排行
            var postUrl = newDomain + '/listArticle/selectAllListArticle';
            $('.list_content').html('');
        } else if(type == 2){//创作力排行
            var postUrl = newDomain + '/listIPCreativity/selectAllListIPCreativity';
            listConWrapDom.html('');
        }
        var noDataWrapDom = $('#js-no-data-wrap'),//暂无数据
            noOneImg = '<img src="/images/ipEvaluation/ipRanking/no_1.png" alt="">',
            noTwoImg = '<img src="/images/ipEvaluation/ipRanking/no_2.png" alt="">',
            noThreeImg = '<img src="/images/ipEvaluation/ipRanking/no_3.png" alt="">',
            postData = {
				cp: cp,
                rows: rows,
                typeName: name,
                rangeTime: time
            };
            
            new AjaxRequest({
                type: "post",
                url: postUrl,
                param: postData,
                isShowLoading: true,
                callBack: function(res){
                    if(res.status == 1){
                        noDataWrapDom.hide();
                        var sucData = res.data;
                        sucData.total <= cp*rows ? $('.more_btn').hide() : $('.more_btn').show();
                        if(sucData.list && sucData.list.length){
                            var sucDataList = sucData.list,
                                sucDataListLen = sucDataList.length;
                            if(type == 1){//内容排行
                                var list_content = $('.list_content');
                                sucDataList.forEach(function(item,index){
                                    var num = (index + 1 + (cp - 1) * rows);
                                    var noImg = '';
                                    if(num == 1) {
                                        noImg = noOneImg;
                                    } else if(num == 2) {
                                        noImg = noTwoImg;
                                    } else if(num == 3) {
                                        noImg = noThreeImg;
                                    } else {
                                        noImg = num;
                                    }
                                    $('<ul>'+
                                        '<li class="title"><span>'+ noImg +'</span><a target="_blank" href="'+ item.Link + '">'+ item.Title  +'</a></li>'+
                                        '<li>'+ item.Theme +'</li>'+
                                        '<li>'+ item.Creativity +'</li>'+
                                        '<li>'+ item.Originality +'</li>'+
                                        '<li>'+ item.TotalIndex +'</li>'+
                                    '</ul>').appendTo(list_content);
                                })
                            } else if(type == 2){//创作力排行
                                for(var j = 0; j < sucDataListLen; j++ ){
                                    var noImg = '',
                                        id = (j + 1) + (cp - 1) * rows;
                                    if(id == 1){
                                        noImg = noOneImg;
                                    } else if(id == 2){
                                        noImg = noTwoImg;
                                    } else if(id == 3){
                                        noImg = noThreeImg;
                                    } else {
                                        noImg = id;
                                    }
                                    //'+sucDataList[j].HeadImg+'
                                    var html = '<ul class="fixedClear">'
                                        +'<li class="li-width42">'+noImg+'</li>'
                                        +'<li class="fixedClear li-width203"><div class="ip-avatar-wrap" style="background: #eee url('+sucDataList[j].HeadImg+') center/500% no-repeat scroll;"></div>'
                                        // https://open.weixin.qq.com/qr/code?username=rmrbwx
                                        // <img class="ip-avatar-img" src="'+sucDataList[j].HeadImg+'" alt="">
                                            +'<div class="ip-name-wrap">'
                                                +'<p class="ip-name-cn">'+sucDataList[j].Name+'</p>'
                                                +'<p class="ip-name-en">'+sucDataList[j].weixinId+'</p>'
                                            +'</div></li>'
                                        +'<li>'+sucDataList[j].ContentQuality+'</li>'
                                        +'<li>'+sucDataList[j].Stability+'</li>'
                                        +'<li>'+sucDataList[j].Transmissible+'</li>'
                                        +'<li>'+sucDataList[j].OriginalProportion+'</li>'
                                        +'<li>'+sucDataList[j].TotalIndex+'</li>'
                                    +'</ul>';
                                    listConWrapDom.append(html);
                                    var listUlDom = listConWrapDom.find('ul'),
                                        listUlDOmLen = listUlDom.length;
                                    if(listUlDOmLen){
                                        for(var i = 0; i < listUlDOmLen; i++){
                                            listConWrapDom.find('ul:odd').addClass('ul-gray');
                                        }
                                    }
                                }  
                            }
                        } else {
                            noDataWrapDom.show();
                            moreBtnDom.hide();
                        }
                        
                    } else {
                        noDataWrapDom.show();
                        moreBtnDom.hide();
                    }
                }
            })
    }
    
    //点击加载更多
    moreBtnDom.off('click').on('click', function(){
        cp ++;
        getData(cp, $('.ca-active').find('a').text(), timeSelectDom.val(), rankType);
    })
    //计算content的高度
    calcHeight();
    function calcHeight(){
        var footerHei = $('.footer-wrap').height(),
            winHei = $(window).height(),
            contentHei = winHei - footerHei - 158;
        $('.content').css('min-height', contentHei + 'px');
    }
    $(window).resize(function(){
        calcHeight();
    })
})