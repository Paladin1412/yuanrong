$(function(){
    // 默认全部分类
    var cp = 1,rows = 8;
    var categoryId;
    var listNoDataWrapDom = $('#js-no-data-wrap');//暂无数据

    // 分类点击
    $('.seg-industry-classification ul').on('click','li',function(){
        var _this = $(this);
        cp = 1;
        _this.addClass('active-nav').siblings().removeClass('active-nav');
        categoryId = _this.attr('id');
        getCategoryList(categoryId);
    })

    // 请求分类数据
    var category  = $('.seg-industry-classification ul');
    var postUrl =  domain + '/account/getCategoryList';
	commonFun.commonAjax(postUrl,'post','',function(res){
        if (res.status == 1) {
            var categoryList = res.data.data;
            if (categoryList.length > 0) {
                categoryList.forEach(function (item, index) {
                    $(`<li id='${item.id}'>${item.name}</li>`).appendTo(category);
                })
            }
        }

    })

    // 请求列表数据
    var  segContentUl = $('.seg-content-ul');
    function getCategoryList(categoryId) {
        var postUrl = domain + '/account/getAgentList',
            postData = {
                cp: cp,// 当前页数
                rows: rows,// 当前页数显示数量
                categoryID:categoryId // 参数
            },
            searchTxt = $.trim($('#js-search-text').val()),
            getUrlParams = commonFun.getUrlParam();
            if(getUrlParams && getUrlParams.keyword){
                postData.search = getUrlParams.keyword
            }
            if(searchTxt.length > 0){
                console.log(searchTxt)
                postData.search = searchTxt
            } else {
                delete postData.search;
            }
            new AjaxRequest({
                type: "post",
                url: postUrl,
                param: postData,
                isShowLoading: true,
                callBack: function(res){
                    if(res.status == 1){
                        var categoryList = res.data.data;
                        var pageContent = res.data.total;
                        if(categoryList.length>0){
                            segContentUl.html('');
                            categoryList.forEach(function(item,index){
                                var fansNum = item.fans ? formatIndexNumber(item.fans) : 0;
                                var platformID,readNum;
                                // 判断平台类型
                                if(item.platformID==12){
                                    platformID = '平均阅读数'
                                    readNum = item.avgReadCount ? formatIndexNumber(item.avgReadCount) : 0;
                                }else if(item.platformID==13){
                                    platformID = '平均转发数'
                                    readNum = item.avgForwardCount ? formatIndexNumber(item.avgForwardCount) : 0;
                                }else if(item.platformID==1||item.platformID==2||item.platformID==3){
                                    platformID = '平均播放量'
                                    readNum = item.avgPlayCount ? formatIndexNumber(item.avgPlayCount) : 0;
                                }else if(item.platformID==10){
                                    platformID = '平均点赞数'
                                    readNum = item.avgLikeCount ? formatIndexNumber(item.avgLikeCount) : 0;
                                }else{
                                    platformID = '平均阅读数'
                                    readNum = item.avgReadCount ? formatIndexNumber(item.avgReadCount) : 0;
                                }
                                $(`<li class="seg-content-li">
                                    <div class="seg-content-li-header fixedClear" >
                                        <div class="bg fixedClear">
                                                <div class='bg1' style="background: url(${item.headImageUrlLocal}) no-repeat center bottom; background-size: 150% 150%;"></div>
                                                <div class="seg-li-header-top-wrap">
                                                    <div class="seg-content-li-headerTop fixedClear">
                                                    <span>${item.categoryName}</span>
                                                    </div>
                                                    <div class="seg-li-header-btn-wrap">
                                                        <p><img src="${item.icoUrl}" alt="" class="seg-icon" >${item.name}</p>
                                                        <a class="qq" target="_blank" href="http://q.url.cn/cdPnbU?_type=wpa&qidian=true">在线咨询</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <img class="seg-header-img" src="${item.headImageUrlLocal}" alt="">
                                    </div>
                                    <div class="agent-list-fans">
                                        <p><i>${fansNum}</i><span>粉丝数量</span></p>
                                        <p><i>${readNum}</i><span>${platformID}</span></p>
                                    </div>
                                    <div class="brief-introduction">
                                        <p class="first">${item.introduction}</p>
                                        <p class="second"><span>合作品牌：</span>${item.agentCoopBrand}</p>
                                    </div>
                                </li>`).appendTo(segContentUl);

                                if(!item.categoryName){
                                    $('.seg-content-li-headerTop span').eq(index).css({'visibility':'hidden'});
                                }

                                if(item.agentCoopBrand == null || item.agentCoopBrand == ''){
                                    $('.second').eq(index).addClass('hide');
                                }
                            })
                            
                            listNoDataWrapDom.hide();//暂无数据
                            // pagination分页
                            $('.pagination-wrap').pagination(pageContent,{
                                current_page: cp,//当前页
                                items_per_page: rows,
                                prev_text: prev_text,
                                next_text: next_text,
                                num_display_entries:5,
                                callback:function (currPageNmu){
                                    if(currPageNmu > 3){
                                        var getLoginFun = function (isLogin) {
                                            if(isLogin == 'Y'){
                                                cp = currPageNmu;  // 当前页数
                                                getCategoryList(categoryId);
                                                $("html,body").scrollTop(300, 0);
                                            }
                                        };
                                        getUserLoginInfo(getLoginFun, 'showpop', 'pagination');

                                    } else {
                                        cp = currPageNmu;  // 当前页数
                                        getCategoryList(categoryId);
                                        $("html,body").scrollTop(350);
                                    }
                                }
                            });
                        } else {
                            segContentUl.html('');
                            listNoDataWrapDom.show();//暂无数据
                            $('.pagination-note-wrap').hide();
                        }
                    }
                    else {
                        if(!res.msg){
                            layer.msg(JSON.parse(res).msg);
                        }else {
                            layer.msg(res.msg);
                        }
                        segContentUl.html('');
                        listNoDataWrapDom.show();//暂无数据
                        $('.pagination-note-wrap').hide();
                    }
                }
            })
    }

    // 默认显示全部数据
    getCategoryList(categoryId);

    $('.seg-content').on('click', function(e){
        var ary = e.target.className.split(' ')
        ary.forEach(function(item){
            if(item == 'qq'){
                $('#qq2').trigger('click');
            }
        });
    })


})