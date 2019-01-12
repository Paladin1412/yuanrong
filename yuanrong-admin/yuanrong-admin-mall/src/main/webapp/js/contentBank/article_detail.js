$(function(){
    var postData = {
        recid: $("#recid").val()
    };
    commonFun.commonAjax(newDomain + '/author/getAuthorInfo', 'post', postData, function(res){
        
        if(res.status == 1){

            var data = res.data;
            if(/mp.weixin.qq.com/.test(data[0].productUrl)){
                var html = data[0].content;
                if(html){
                    $('#frame_content').remove();
                    $(data[0].content).appendTo($('.article-detail'));
                    $('.article-detail img').css({'width': '100%'});

                }else{
                    $.ajaxPrefilter( function (options) {
                        if (options.crossDomain && jQuery.support.cors) {
                            var http = (window.location.protocol === 'http:' ? 'http:' : 'https:');
                            options.url = http + '//cors-anywhere.herokuapp.com/' + options.url;
                            //options.url = "http://cors.corsproxy.io/url=" + options.url;
                        }
                    });
                    var srcIframe = data[0].productUrl;
                    $.get(srcIframe, function (response) {
                        var html = response;
                        html=html.replace(/data-src/g, "src").replace(/<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/g, '').replace(/https/g,'http');
                        var html_src = 'data:text/html;charset=utf-8,' + html;
                        $("#frame_content").attr("src" , html_src);
                    });
                }

            } else {
                var conHtml = data[0].content;
                // console.log(conHtml)
                if(conHtml != ''){
                    $(".article-detail").html(conHtml)
                } else {
                    var alertComfirm = window.confirm('当前文章未发布不能浏览，更多内容，请联系我们！');
                    window.close();
                }
                
            }
            // else{
            //     $("#frame_content").attr("src" , data[0].productUrl);
            // }

            var time = data[0].authorCreationTime == 0 ? 5 : data[0].authorCreationTime;
            var pric = data[0].createdPrice == 0 ? '按需定价' : '￥' + data[0].createdPrice.toString().replace(/(\d{1,3})(?=(\d{3})+$)/g,'$1,');
            $('<img class="author-avatar" src="'+ data[0].authorImg +'" alt="">' +
                '<div class="author-name">'+ data[0].authorNickname +'</div>' +
                '<div style="text-align: center;">' +
                '<a href="/contentBank/publish_demand.html"><input class="author-order" type="button" value="立即预约"></a>' +
                '</div>').appendTo($('.author-desc'));

            $('<div>' +
                '<span>约稿价格：</span>' +
                //+ data[0].createdPrice.toString().replace(/(\d{1,3})(?=(\d{3})+$)/g,'$1,') +
                '<span>'+ pric +'</span>' +
                '</div>' +
                '<div>' +
                '<span>创作形式：</span>' +
                '<span class="value-color">'+ data[0].contentFormName +'</span>' +
                '</div>' +
                '<div style="overflow: hidden;">' +
                '<span style="float: left;">使用场景：</span>' +
                '<span style="float: left; width: 162px;" class="value-color">'+ data[0].scenesName.replace(/;/g, '、') +'</span>' +
                '</div>' +
                '<div style="overflow: hidden;">' +
                '<span style="float: left;">擅长领域：</span>' +
                '<span style="float: left; width: 162px;" class="value-color">'+ data[0].CategoryName.replace(/;/g, '、') +'</span>' +
                '</div>' +
                '<div>' +
                '<span>创作用时：</span>' +
                '<span class="value-color">'+ time +'天</span>' +
                '</div>').appendTo($('.author-contact'));

            var name = data[0].lableName;
            if(name){
                if(/\./.test(name)){
                    name.split('.').forEach(function(item){
                        $('<li class="list-item">'+ item +'</li>').appendTo($('.name-list'));
                    })
                }else {
                    $('<li class="list-item">'+ name +'</li>').appendTo($('.name-list'));
                }
            }

            $('.author-order').on('click', function(){
                sessionStorage.setItem('name', data[0].authorNickname);
            });
        }
    })
    // $.ajax({
    //     url: newDomain + '/author/getAuthorInfo',
    //     type: 'post',
    //     dataType: 'json',
    //     data: {
    //         recid: $("#recid").val()
    //     },
    //     success: function(res){
    //        // setTimeout(function(){
    //        //     var _iframe = document.getElementById('frame_content').contentWindow;
    //        //     var div =_iframe.document.getElementsByClassName('rich_media_title');
    //        //     console.log($(div));
    //        //     $(div).css({'padding': '40px'})
    //        //     console.log(div);
    //        // }, 5000);
    //         // for(var i = 0; i < divs.length; i++){
    //         //     if(divs[i].getAttribute('node-type') == 'outer'){
    //         //         divs[i].remove();
    //         //     }
    //         // }
    //     }
    // });

});