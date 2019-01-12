$(function(){
   // 短视频作者数据绑定;
    var queryId = location.search.split('=')[1];
    // var recid = queryId == undefined ? 3 : queryId;
    var recid = localStorage.getItem('recid');
    $.ajax({
        url: domain + '/author/getAuthorVideoDetail',
        get: 'post',
        dataType: 'json',
        crossDomain: true,
        data: {
            recid: $("#recid").val()
        },
        success: function(res){
            //面包屑;
            if(/author_image/.test(document.referrer) || /graphics_writer/.test(document.referrer)){
                $('<a href="'+ domain +'/contentBank/content_bank.html">内容银行</a>' +
                    '<span> &gt;</span>' +
                    '<a href="'+ domain +'/contentBank/author_images.html">创作者</a>' +
                    '<span> &gt;</span>' +
                    '<span class="nav-author-details">作者详情</span>').appendTo($('.nav-author'));
            }else {
                $('<a href="'+ domain +'/contentBank/content_bank.html">内容银行</a>' +
                    '<span> &gt;</span>' +
                    '<a href="'+ domain +'/contentBank/content_bank.html">原创内容</a>' +
                    '<span> &gt;</span>' +
                    '<span class="nav-author-details">作者详情</span>').appendTo($('.nav-author'));
            }

            if (res.status == 0) {
                $('#fail').css({ 'display': 'block', 'margin-bottom': '50px' });
                $('.con-details').css('display', 'none');
            }else {
                var authorInfo = $('.video-author');
                var videoInfo = $('.works-video');
                var authorData = res.data.userInfo;
                var videoData = res.data.userVideoInfos;

                //短视频作者个人信息数据绑定;
                $('<dl class="fixedClear"><dt class="fl"> <a href=""><img src="'+ authorData.HeadImage +'" alt="">' +
                    '</a></dt><dd class="fl"><p class="video-author-rcp"><a href="">'+ authorData.NickName +'</a> <span class="video-author-call">在线约稿</span>' +
                    '</p><p class="video-author-scly">擅长领域： <span>'+ authorData.TypeName +'</span></p><p class="video-author-intro">' +
                    '<span style="float: left;">简介：</span>' +
                    '<span style="display: block; margin-left: 50px;">'+ authorData.Introduction +
                    '</span></p></dd> </dl>').appendTo(authorInfo);

                //短视频作者作品列表数据绑定:
                videoData.length == 0 ? $('.empty-div').css('display', 'block') : $('.empty-div').css('display', 'none');
                videoData = videoData.length > 8 ? videoData.slice(0, 8): videoData;
                videoData.forEach(function(item) {
                    $('<li><dl class="works-video-list"><dt><a target="_blank" href="'+ item.Url +'"><img src="'+ item.Imgurl +'" alt="" width="270" heigh="290">' +
                        '<div  class="video-ico"><span class="video-ico-triangle"></span></div></a></dt><dd class="works-video-type">分类：<span>'+ item.typeName +'</span></dd>' +
                        '<dd class="works-video-intro"><a style="font-size: 16px;" target="_blank" href="'+ item.Url +'">'+ item.Title +'</a></dd><dd class="works-video-call fixedClear">' +

                        // '<div id="" class="consult ">' +
                        // '<i class="icon-wode1 iconfont qq" style="color: #888;"></i>' +
                        // '<span class="btn-text qq" style="cursor: pointer;">&nbsp;在线议价</span>' +
                        // '</div>' +
                        //
                        // '<div id="" class="consult " style="border-left: 1px solid #e3e3e3">' +
                        // '<i class="icon-iconxiaoxi iconfont qq" style="color: #888;"></i>' +
                        // '<span class="btn-text qq" style="cursor: pointer;">&nbsp;咨询</span>' +
                        '<div>' +
                        '<img style="display:inline-block; width: 20px; height: 20px; border-radius: 50%;" src="'+ authorData.HeadImage +'"  >' +
                        '<span style="color: #333333;margin-left: 20px; font-size: 12px;">&nbsp;'+ authorData.NickName +'</span>' +
                        '<span class="btn-text qq" style="cursor: pointer; font-size: 12px; color: #333333">&nbsp;&nbsp;|&nbsp;&nbsp;在线议价&nbsp;</span>' +
                        '<i class="icon-iconxiaoxi iconfont qq" style=" cursor: pointer; font-size: 12px;"></i>' +
                        '</div>' +

                        '</div>' +

                        '</dd></dl></li>').appendTo(videoInfo);
                });

                $('#fail').css({ 'display': 'none' });
            }
        }
    });

    //导航菜单高亮显示;
    $('.nav-content-bank').addClass('active').siblings().removeClass('active');

    //QQ客服
    $('.works-video').on('click', function(e){
        console.log(e.target)
        var ary = e.target.className.split(' ')
        ary.forEach(function(item){
            if(item == 'qq'){
                console.log('iconfont')
                $('#div_imqq1').trigger('click');
            }
        });
    })
});