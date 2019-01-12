<#include "../commonFragments/basic/meta.ftl"/>
	<title>需求详情_原创作者_圆融内容交易服务中心</title>
	<meta http-equiv="keywords" content='广告主发布需求,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业' />
	<meta http-equiv="description" content="★广告主发布需求☆圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业,圆融由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化、证券化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />
<#include "../commonFragments/basic/css_link.ftl"/>
	<link rel="stylesheet" href="${request.contextPath}/css/contentBank/tab.css?${mdc_version}">
    <link rel="stylesheet" href="${request.contextPath}/css/demandHall/demand_work1.css?${mdc_version}">
    <link rel="stylesheet" href="${request.contextPath}/css/demandHall/demand_author1.css?${mdc_version}">
    <style>
        .layer-open-bg {
            width: 100%;
            height: 100%;
            z-index: 1000;
            position: fixed;
            top: 0;
            left: 0;
            background: rgba(0, 0, 0, .3);
        }
        .layer-open {
            position: fixed;
            left: 50%;
            top: 50%;
            transform: translate(-60%, -50%);
        }
    </style>
</head>
<body>
<input type="hidden" id="authorId" value="${id}">
    <#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
	<#--<#include "../commonFragments/basic/top.ftl"/>-->
<#--预览-->
<div class="container-demand-preview hide">
    <img src="" alt="">
</div>
<div class="wrap">
    <div class="demand-detail-wrap fixedClear">
        <div class="container-left">
            <#include "./demandDetailCommon/stepAuthor.ftl"/>
            <div>
            <#--状态2--没有创作者的情况-->
                <div class="container-demand-detail2-no hide">
                    <div class="detail2-warn"><i></i><p>目前您的账户里还没有创作者</p></div>
                    <img src="/images/demandHall/detail_2.png" alt="">
                    <button class="detail2-btn" style="cursor: pointer">添加创作者》</button>
                </div>
            <#--状态2--有创作者的情况-->

                <div class="container-demand-detail2-have hide">
                    <p class="detail3-title">我要报名</p>
                    <div class="detail3-inputBox">
                        <i class="have-product-icon iconfont icon-sousuo1"></i>
                        <input type="text" class="product-search" placeholder="请输入文字关键词" value="">
                        <button class="detail3-btn">搜索</button>
                    </div>
                    <div class="detail3-list">
                        <ul class="detail3-list-header fixedClear">
                            <#--<li class="dt3-width2">创作者</li>-->
                            <#--<li class="dt3-width3" style="width: 40%">写稿报价（元）</li>-->
                            <#--<li class="dt3-width3" style="width: 20%">创作耗时（天）</li>-->
                            <#--<li class="dt3-width2">操作</li>-->
                        </ul>
                        <ul class="detail3-list-content fixedClear">

                        <#--<li class="fixedClear">-->
                        <#--<div class="dt3-headerImg"><img src="" alt=""><span>测试儿</span></div>-->
                        <#--<div class="dt3-price"><input type="text" value="1000"></div>-->
                        <#--<div class="dt3-getTime"><input type="text" value="7"></div>-->
                        <#--<div class="dt3-signup"><span class="signUp">报名</span></div>-->
                        <#--</li>-->
                        </ul>
                    <#--报名弹框-->
                        <div class="layer-open-bg hide">
                            <div class="layer-open">
                                <div class="layer-header"><span class="dt3-warn">温馨提示</span><span class="dt3-close">×</span></div>
                                <div class="layer-content">
                                    <div class="dt3-request"><img src="/images/demandHall/signUp1.png" alt=""><span>确认报名此需求吗？</span></div>
                                    <ul class="detail3-list-content1 fixedClear">
                                        <li class="fixedClear" style="justify-content: center;align-items: center;display: flex;border: 1px solid #EFF1F3;">
                                            <div class="dt3-headerImg" style="text-align: left;width:25%;"></div>
                                            <div class="getPrices fixedClear" style="width: 55%;"></div>
                                            <div class="authorTime1" style="width: 20%;text-align: center"></div>
                                        </li>
                                    </ul>
                                    <p class="confirm_info">买家确认使用平台会进行通知，之后才会进行需求细节沟通，创作者进行创作。</p>
                                    <div class="dt3-ccbtn"><span class="dt3-cancel">取消</span><span class="dt3-confirm">确认报名</span></div>
                                </div>
                            </div>
                        </div>
                    <#--pagination分页-->
                        <div class='pagination-wrap'></div>
                    </div>
                    <#--<div class="no-data hide">暂无数据</div>-->
                    <#include "../commonFragments/list_no_data.ftl"/>
                </div>
            <#--状态2--报名成功-->
                <div class="container-demand-detail2-success hide">
                    <div class="dt4-tips">
                        <img src="/images/demandHall/signUp.png" alt="">
                        <div class="dt4-right">
                            <p class="dt4-first">报名成功！</p>
                            <p class="dt4-second">录用结果请后续到卖家中心的订单列表中去查看呦</p>
                            <button class="dt4-btn"><a href="javascript:location.reload();">继续报名》</a></button>
                        </div>
                    </div>
                    <div class="dt4-content">
                        <ul class="detail3-list-content1 fixedClear">
                            <li class="fixedClear" style="justify-content: center;align-items: center;display: flex;background-color: #F7F8FC;border: 1px solid #E7EAED; ">
                                <div class="dt3-headerImg" style="text-align: left;width:30%;"></div>
                                <div class="getPrices fixedClear" style="width: 50%;"></div>
                                <div class="authorTime1" style="width: 15%;text-align: center"><span></span></div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <#include "./demandDetailCommon/rightSlider.ftl"/>
        <div class="container-bottom hide">
            <div><span>投稿须知</span></div>
            <div>
                <ul>
                    <li>1.报名后将由广告主确认是否选用。一旦确定选用才进行写稿。</li>
                    <li>2.稿件需要支持3次修改。</li>
                    <li>3.稿件的费用以报名时应约价格为准。</li>
                </ul>
                <div class="submit-pay">
                    <div>￥200</div>
                    <span>提交作品</span>
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
<script type="text/javascript" src="${request.contextPath}/plugins/jquery.pagination.js?${mdc_version}"></script>
<script src="${request.contextPath}/js/demandHall/demand_author2.js?${mdc_version}"></script>
</html>
