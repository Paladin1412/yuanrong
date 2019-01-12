<#include "../commonFragments/basic/meta.ftl"/>
    <title>创作者_文章详情_圆融内容银行交易服务中心</title>
    <meta http-equiv="keywords" content='创作者文章详情,圆融,内容银行,内容创作者,内容创业' />
    <meta http-equiv="description" content="★创作者文章详情☆圆融,内容银行,内容创作者,内容创业,圆融由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化、证券化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />

    <#include "../commonFragments/basic/css_link.ftl"/>
    <link rel="stylesheet" href="${request.contextPath}/css/contentBank/tab.css?${mdc_version}">
    <link rel="stylesheet" href="${request.contextPath}/css/contentBank/article_detail.css?${mdc_version}">
    
</head>
<body style="margin: 0; font-size: 14px;">
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
<div class="wrap wrap-has-bread">
    <input type="hidden" id="recid" value="${recid}">
    <div id="content" style="overflow: hidden;">
        <div class="nav-author">
            <a href="/contentBank/content_bank.html">内容交易</a>
            <span>&gt;</span>
            <a href="/contentBank/author_images.html">创作者</a>
            <span>&gt;</span>
            <span class="nav-author-details" style="color: 48494a;">文章详情</span>
            <div style="float: right;">
                <span>&lt;</span>
                <a href="/contentBank/author_images.html" style='font-size:12px;'>返回</a>
            </div>
        </div>

        <div style="position: relative; height: 100%;">
            <div class="article-detail">
                <iframe id="frame_content" name="frame_content" style="width: 100%; height: 100%;" src="" frameborder="0"></iframe>
            </div>
            <div class="author-info">
                <div class="author-desc">
                    <!--<img src="" alt="">-->
                    <!--<div class="author-avatar"></div>-->
                    <!--<div class="author-name">冷兔</div>-->
                    <!--<div style="text-align: center;">-->
                        <!--<input class="author-order" type="button" value="立即预约">-->
                    <!--</div>-->
                </div>
                <div class="author-contact">
                    <!--<div>-->
                        <!--<span>约稿价格：</span>-->
                        <!--<span>￥2,000</span>-->
                    <!--</div>-->
                    <!--<div>-->
                        <!--<span>创作形式：</span>-->
                        <!--<span class="value-color">文章</span>-->
                    <!--</div>-->
                    <!--<div>-->
                        <!--<span>擅长领域：</span>-->
                        <!--<span class="value-color">软广植入、科技、时尚</span>-->
                    <!--</div>-->
                    <!--<div>-->
                        <!--<span>创作用时：</span>-->
                        <!--<span class="value-color">3天</span>-->
                    </div>
                    <ul class="name-list" style="padding: 0 15px 0 25px;">
                        <!--<li>世俗</li>-->
                        <!--<li>表情包</li>-->
                        <!--<li>明快</li>-->
                        <!--<li>大白话</li>-->
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
    <#include "../commonFragments/basic/footer.ftl"/>
    <!-- 热线电话 -->
    <#include "../commonFragments/hotline.ftl"/>
</body>
    <#include "../commonFragments/basic/js_script.ftl"/>
<script src="${request.contextPath}/js/contentBank/article_detail.js?${mdc_version}"></script>

<style lang="">        
    .wrap ol, .wrap ul{
        width: auto;
    }
    ol,ul{
        width: auto;
        padding: 0;
    }
    li{
        clear: none;
    }
    p{
        margin: 0;
    }
    body{
        background: #eceff4;
        height: auto;
        margin: 0 !important;
        font-size: 14px;
    }
    .article-detail img{
        width: 100%;
    }
</style>
<script>
    var winHei = $(window).height() * 2 / 3,
        winHeiHalf = winHei + (winHei / 2);
    // $('.article-detail').css('min-height', '500px');
    $('#frame_content').css('height', winHeiHalf + 'px');
    var wrapHei = $('.wrap').height();
    $('#content').css('min-height', '500px');
    // $.ajaxPrefilter( function (options) {
    //     if (options.crossDomain && jQuery.support.cors) {
    //         var http = (window.location.protocol === 'http:' ? 'http:' : 'https:');
    //         options.url = http + '//cors-anywhere.herokuapp.com/' + options.url;
    //         //options.url = "http://cors.corsproxy.io/url=" + options.url;
    //     }
    // });
    // var srcIframe = "http://mp.weixin.qq.com/s?__biz=MTM3NTEzNTkyMQ==&mid=2649809577&idx=2&sn=7427bca936cbdc43f43dcc19b5bf0119&chksm=61cd5d7756bad461c9f61de07090d9fc8217229a247461e58be006bdad0ef05cc76628002559&scene=27#wechat_redirect";//微信文章地址
    // $.get(srcIframe, function (response) {
    //     var html = response;
    //     html=html.replace(/data-src/g, "src").replace(/<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/g, '').replace(/https/g,'http');
    //     var html_src = 'data:text/html;charset=utf-8,' + html;
    //     $("#frame_content").attr("src" , html_src);
    // });
</script>
</html>