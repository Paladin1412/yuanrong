$(function(){
    //作者信息数据绑定;
    //var queryId = location.search.split('=')[1];
    // var recid = queryId == undefined ? 3 : queryId;
    var recid = localStorage.getItem('recid');

    $.ajax({
        url: domain + '/author/getAuthorArticleDetail',
        get: 'post',
        dataType: 'json',
        crossDomain: true,
        data: {
            recid: $("#recid").val()
        },
        success: function(res) {
            var authorInfo = $('.works-author');
            var similarAuthor = $('.recommend-works-list');
            var articleInfo = $('.works-details-list');
            var authorData = res.data.userInfo;
            var simData = res.data.likeArthors;
            var articleData = res.data.userArticleInfos;

            //面包屑;
            if(/author_image/.test(document.referrer) || /graphics_writer/.test(document.referrer)){
                $('<a href="'+ domain +'/contentBank/content_bank.html">内容银行</a>' +
                    '<span>&gt;</span>' +
                    '<a href="'+ domain +'/contentBank/author_images.html">创作者</a>' +
                    '<span>&gt;</span>' +
                    '<span class="nav-author-details">作者详情</span>').appendTo($('.nav-author'));
            }else {
                $('<a href="'+ domain +'/contentBank/content_bank.html">内容银行</a>' +
                    '<span>&gt;</span>' +
                    '<a href="'+ domain +'/contentBank/content_bank.html">原创内容</a>' +
                    '<span>&gt;</span>' +
                    '<span class="nav-author-details">作者详情</span>').appendTo($('.nav-author'));
            }

            //作者个人信息数据绑定;
            // authorData.HeadImage
            $('<dl class="fixedClear">' +
                    '<dt class="fl">' +
                        '<a href=""><img src="'+ authorData.HeadImage +'"  alt=""></a>' +
                    '</dt>' +
                    '<dd class="fl">' +
                        '<p class="works-author-rcp"><a href="">'+ authorData.NickName +'</a><span class="works-author-call">在线约稿</span></p>' +
                        '<p class="works-author-scly">擅长领域：<span>'+ authorData.TypeName +'</span></p>' +
                        '<p class="works-author-intro"><span style="float: left;">简介：</span>' +
                        '<span style="display: block; margin-left: 50px;">'+ authorData.Introduction +'</span></p>' +
                    '</dd>' +
                '</dl>').appendTo(authorInfo);

            //相似作者数据绑定;
            simData.forEach(function(item, index){
                if(item.RecID == recid){
                    simData.splice(index, 1);
                }
            });
            simData = simData.length > 5 ? simData.slice(0, 5): simData;

            simData.forEach(function(item){
                $('<li class="fixedClear" index="'+ item.RecID +'">' +
                    '<div class="recommend-works-list-pic fl">' +
                        '<a href="'+ domain +'/contentBank/graphics_writer_'+ item.RecID+'.html">' +
                            '<img src="'+ item.HeadImage +'" alt="">' +
                        '</a>' +
                    '</div>' +
                    '<div class="recommend-works-list-txt fl">' +
                        '<p><a href="'+ domain +'/contentBank/graphics_writer_'+ item.RecID+'.html">'+ item.NickName +'</a></p>' +
                        '<a href="'+ domain +'/contentBank/graphics_writer_'+ item.RecID+'.html">查看详情</a>' +
                    '</div>' +
                '</li>').appendTo(similarAuthor);
                });
            // similarAuthor.children().each(function(){
            //    $(this).on('click', function(){
            //        localStorage.setItem('recid', $(this).attr('index'));
            //    })
            // });

            //作者作品列表数据绑定:
            articleData.length == 0 ? $('.empty-div').css('display', 'block') : $('.empty-div').css('display', 'none');
            articleData = articleData.length > 5 ? articleData.slice(0, 5): articleData;
            articleData.forEach(function(item){
               $('<li class="fixedClear">' +
                   '<div class="works-details-list-pic fl"><a target="_blank" href="'+ item.Url +'"><img src="'+ item.HeadImgLocal +'" alt=""></a>' +
                   '</div>' +
                   '<div class="works-details-list-txt fl">' +
                        '<h2><a target="_blank" href="'+ item.Url +'">'+ item.Title +'</a></h2>' +
                        '<p>'+ item.Summary +'</p>' +
                        '<div class="works-details-list-txt-type fixedClear">' +
                            '<p>分类：<span>'+ item.typeName +'</span></p>' +
                            '<p>标签：<span>'+ item.Tag +'</span></p>' +
                            '<p>圆融指数：<span>'+ item.Score +'</span></p>' +
                        '</div>' +
                   '<img class="works-details-list-txt-call fixedClear">' +

                   '<div class="fixedClear">' +
                   // '<div class=" " style="float:left;">' +
                   // // '<i class="icon-wode1 iconfont qq" style="color: #888;"></i>' +
                   // // '<span class="btn-text qq" style="cursor: pointer;">&nbsp;在线议价</span>' +
                   //
                   //
                   // '</div>' +

                   //class="consult "
                   '<div>' +
                       '<img style="display:inline-block; width: 26px; height: 26px; border-radius: 50%;" src="'+ authorData.HeadImage +'"  >' +
                       '<span style="color: #5d5d5d;margin-left: 20px;">&nbsp;'+ authorData.NickName +'</span>' +
                       '<span class="btn-text qq" style="cursor: pointer;">&nbsp;&nbsp;|&nbsp;&nbsp;在线议价&nbsp;</span>' +
                       '<i class="icon-iconxiaoxi iconfont qq" style="color: #4f99e8; cursor: pointer"></i>' +
                   '</div>' +

                   '</div>' +

                   '</div></div></li>').appendTo(articleInfo);
            });
        }
    });


    //导航菜单高亮显示;
    $('.nav-content-bank').addClass('active').siblings().removeClass('active');

    //QQ客服
    $('.works-details-list').on('click', function(e){
        var ary = e.target.className.split(' ')
        ary.forEach(function(item){
            if(item == 'qq'){
                $('#div_imqq1').trigger('click');
            }
        });
    })

});