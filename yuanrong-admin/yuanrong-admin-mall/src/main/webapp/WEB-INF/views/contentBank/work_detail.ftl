<#include "../commonFragments/basic/meta.ftl"/>
<title>《${yRProduction.title}》原创作品售卖中_作品价格_作品详情_圆融原创作品内容交易服务中心</title>
<meta http-equiv="keywords" content='《${yRProduction.title}》,作品价格,原创作品,作品交易' />
<meta http-equiv="description" content="《${yRProduction.title}》,原创作品售卖中,作品价格,原创作品,作品交易,《${yRProduction.title}》,作品详情,圆融内容交易服务中心由中国传媒大学,内容银行重点实验室成立,目的是构建内容生产、传播、变现、资产化的一站式交易平台,面向内容创作者、广告主、代理公司、投融资机构等产业各方,提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务,打造内容产业新生态.圆融内容银行交易服务中心,内容交易,圆融内容交易,圆融平台,圆融内容交易服务平台,圆融买家卖家后台登录,内容版权交易平台,内容知识产权,授权转载平台,创作者,自媒体,广告主,内容创作,内容生产,写作赚钱,自媒体征稿,投稿赚钱,创作者创作赚钱,内容交易平台,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易:短视频广告营销(秒拍,美拍,快手,抖音),微信变现,微博赚钱,类似：克劳瑞,一道自媒体,稿稿,乐观号,微播易/微博易,,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业）" />
<meta name="keywords" content='《${yRProduction.title}》,作品价格,原创作品,作品交易' />
<meta name="description" content="《${yRProduction.title}》,原创作品售卖中,作品价格,原创作品,作品交易,《${yRProduction.title}》,作品详情,圆融内容交易服务中心由中国传媒大学,内容银行重点实验室成立,目的是构建内容生产、传播、变现、资产化的一站式交易平台,面向内容创作者、广告主、代理公司、投融资机构等产业各方,提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务,打造内容产业新生态.圆融内容银行交易服务中心,内容交易,圆融内容交易,圆融平台,圆融内容交易服务平台,圆融买家卖家后台登录,内容版权交易平台,内容知识产权,授权转载平台,创作者,自媒体,广告主,内容创作,内容生产,写作赚钱,自媒体征稿,投稿赚钱,创作者创作赚钱,内容交易平台,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易:短视频广告营销(秒拍,美拍,快手,抖音),微信变现,微博赚钱,类似：克劳瑞,一道自媒体,稿稿,乐观号,微播易/微博易,,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业）" />


<#include "../commonFragments/basic/css_link.ftl"/>
<link rel="stylesheet" href="${request.contextPath}/css/contentBank/tab.css?${mdc_version}">
<link rel="stylesheet" href="${request.contextPath}/css/iconfont/iconfont.css?${mdc_version}">
<link rel="stylesheet" href="${request.contextPath}/css/contentBank/original_work.css?${mdc_version}">
<link rel="stylesheet" href="${request.contextPath}/css/contentBank/work_detail.css?${mdc_version}">
</head>
<body style="margin: 0;">
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
<div class="wrap wrap-has-bread" style="font-size:14px;">
    <div style="background:#fbf0e9;">
        <div class="private-work" style="width:1170px;margin:-20px auto 20px ;font-size:12px;color:#ae9f9c;height:30px;line-height:30px;display:none;">此页仅供创作者自己预览,其他人不可见</div>
    </div>

    <#--<input id="local-content" value="${yRProduction.localcontent}">-->
    <div id="content" style="">
        <!--导航-->
        <div class="nav-author">
            <a href="/contentBank/original_work.html">买作品</a>
            <span>&gt;</span>
            <span class="nav-author-details">作品详情</span>
            <div style="float: right;" class="back">
                <#--<span >&lt;</span>-->
                <#--<a href="/contentBank/original_work.html">返回</a>-->
            </div>
        </div>

        <!--主题-->
        <div style="position: relative; height: 100%;">
            <div class="article-detail">

                <div class="article-title">
                    <div class="title-desc">${yRProduction.title}</div>
                    <div class="title-detail">
                        <span class="title-detail-category">分类: <span style="color: #64676a;"></span></span>
                        <span class="title-detail-index original-score">原创度: <span></span></span>
                        <span class="title-detail-info">信息量: <span style="color: #64676a;">${yRProduction.wordNum}字</span></span>
                            <#--<i class="iconfont icon-eye1"></i>-->
                        <#--<span>浏览量: <span style="color: #64676a;">${accessNum}</span></span>-->
                            <#--<i class="iconfont icon-shijian"></i>-->
                        <#--<span>时间: <span style="color: #64676a;">${(yRProduction.upTime?string("yyyy-MM-dd HH:mm:ss"))!""}</span></span>-->
                    </div>
                </div>

                <div class="article-detail-slice">
                    ${yRProduction.localcontent}
                </div>
                <div style="display:none;" class="not-publish">
                    <p style="color:#82868a;font-size:12px;">为了保证版权安全,仅显示50%内容,购买后可查看全文且拥有该作品的著作权</p>
                    <button class="btn" style="cursor:pointer;">立即购买</button>

                    <ul style="position:relative;">
                        <div class="publish-hot" style="position:absolute;top:-30px;"><span>高频词: </span></div>
                        <i class="iconfont icon-bangzhu" style="position:absolute;top:25px;left:40px;"></i>
                        <li><span style="position: relative;top: -3px;font-size: 24px;display: inline-block;margin-right: 3px;">.</span>买断指买断著作权</li>
                        <li><span style="position: relative;top: -3px;font-size: 24px;display: inline-block;margin-right: 3px;">.</span>购买成功后,买家拥有该作品的著作权,包括但不限于作品的复制权、发行权、改编权、摄制权等</li>
                        <li><span style="position: relative;top: -3px;font-size: 24px;display: inline-block;margin-right: 3px;">.</span>作品售卖成功后,原作者不再享有该作品著作权中的任何权益</li>
                    </ul>
                </div>
             </div>

            <div class="publish" style="display:none;">
                <ul style="position:relative;">
                    <div class="publish-hot" style="position:absolute;top:-46px;"><span>高频词: </span></div>
                    <i class="iconfont icon-bangzhu" style="position:absolute;top:10px;left:-15px;"></i>
                    <li><span style="position: relative;top: -3px;font-size: 24px;display: inline-block;margin-right: 3px;">.</span>转载使用时, 只能在不影响原文大意及结构的情况下, 修改标题、内文及版式</li>
                    <li><span style="position: relative;top: -3px;font-size: 24px;display: inline-block;margin-right: 3px;">.</span>转载方不得对转载的内容添加原创标签</li>
                    <li><span style="position: relative;top: -3px;font-size: 24px;display: inline-block;margin-right: 3px;">.</span>转载时需标注署名 注: 此文章转载于微信公众号/头条号 作者XXX</li>
                    <li><span style="position: relative;top: -3px;font-size: 24px;display: inline-block;margin-right: 3px;">.</span>在微信公众号中发表过的原创作品, 暂不支持授权转载到微信公众号</li>
                </ul>
            </div>

            <!--作者信息-右-->
            <div class="author-info">
                <div class="author-desc">
                </div>
                <div class="author-contact">
                    <ul>
                    </ul>
                </div>
            </div>
        </div>

        <!--图文热词-->
        <div class="hot" style="display:none;">
            <h3 style="color:#64676a;font-size:16px;">图文热词</h3>
            <div id="hot-word" style="width:600px;height:200px;margin:0 auto;cursor:none;">

            </div>
        </div>

        <!--相似作品推荐-->
        <div class="similar">
            <h3 style="color:#64676a;font-size:16px;">
                <span>相似作品推荐</span>
                <#include "common/change.ftl"/>
            </h3>
            <div class="similar_list">
                <ul>
                </ul>
            </div>
            <div class="no-data" style="padding: 20px 0 40px 0;display:none;">
                <img src="../../../images/commonFragments/no_data.png" alt="">
                <p style="color:#64676a;font-size:16px;">暂无数据</p>
            </div>
        </div>
    </div>
</div>
<div id="js-cart-fix-bottom-wrap" class="js-cart-fix-bottom-wrap" style="width:100%;">
    <div style="overflow: hidden;height:50px;line-height:50px;background:#fff;background:rgba(0,0,0,.65)" >
        <div class="bottom-work-title" style="color:#fff;"></div>
        <div style="float:right;margin-right:20%;">
            <span class="work-price" style="color:#fff;"><span>价格： </span><span style="color:#4895e7;font-size:18px; font-weight: 500;"></span></span>
            <span class="add-buy" style="font-size:14px;">加入选购车</span>
            <span class="work-buy" style="font-size:14px;">立即购买</span>
        </div>
    </div>
</div>
<span style="display: none;">
《${yRProduction.title}》原创作品售卖中_作品价格_作品详情_圆融原创作品内容交易服务中心
《${yRProduction.title}》,作品价格,原创作品,作品交易
《${yRProduction.title}》,原创作品售卖中,作品价格,原创作品,作品交易,《${yRProduction.title}》,作品详情,圆融内容交易服务中心由中国传媒大学,内容银行重点实验室成立,目的是构建内容生产、传播、变现、资产化的一站式交易平台,面向内容创作者、广告主、代理公司、投融资机构等产业各方,提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务,打造内容产业新生态.圆融内容银行交易服务中心,内容交易,圆融内容交易,圆融平台,圆融内容交易服务平台,圆融买家卖家后台登录,内容版权交易平台,内容知识产权,授权转载平台,创作者,自媒体,广告主,内容创作,内容生产,写作赚钱,自媒体征稿,投稿赚钱,创作者创作赚钱,内容交易平台,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易:短视频广告营销(秒拍,美拍,快手,抖音),微信变现,微博赚钱,类似：克劳瑞,一道自媒体,稿稿,乐观号,微播易/微博易,,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业）
    </span>
<#include "../commonFragments/basic/footer.ftl"/>
<!-- 热线电话 -->
<#include "../commonFragments/hotline.ftl"/>
</body>
<style>
    ul {
        width: 100%;
    }
    li{
        clear: none;
    }
    body{
        background: #eceff4;
        height: auto;
        margin: 0 !important;
        font-size: 14px;
    }
    p {
        margin: 0;
    }
</style>
<#include "../commonFragments/basic/js_script.ftl"/>
<script src="http://echarts.baidu.com/build/dist/echarts-all.js"></script>
<script src="${request.contextPath}/js/contentBank/work_detail.js?${mdc_version}"></script>
</html>