<#include "../commonFragments/basic/meta.ftl"/>
	<title>提交作品_圆融内容交易服务中心</title>
	<meta http-equiv="keywords" content='广告主发布需求,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业' />
	<meta http-equiv="description" content="★广告主发布需求☆圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业,圆融由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化、证券化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />
<#include "../commonFragments/basic/css_link.ftl"/>
	<link rel="stylesheet" href="${request.contextPath}/css/contentBank/tab.css?${mdc_version}">
 	<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/iconfont/iconfont.css?${mdc_version}">
    <link rel="stylesheet" href="${request.contextPath}/css/demandHall/demand_work2.css?${mdc_version}">
    <link rel="stylesheet" href="${request.contextPath}/js/formSelects/formSelects-v4.css?${mdc_version}">
</head>
<body>
<input type="hidden" id="recId" value="${recid}">
	<#--<#include "../commonFragments/basic/top.ftl"/>-->
    <#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
<div class="wrap">
    <div class="demand-detail-step2-wrap fixedClear">
        <div class="container-left">
            <#include "./demandDetailCommon/step.ftl"/>
            <div class="container-demand-product">
                <div>
                    <ul>
                        <li class="product-active">提交作品</li>
                        <li>选用已有作品</li>
                        <li></li>
                    </ul>
                    <div class="show create-product product-item">
                        <ul>
                            <li class="create-product-author">
                                <span style="float:left;">
                                    <span>*</span>创作者
                                </span>
                                <select name="author" xm-select-skin="normal" lay-verify="required" xm-select="select-author" xm-select-radio xm-select-direction="down" style="width:225px;" >
                                    <option value=""></option>
                                </select>
                                <div class="author-prompt">
                                    <span>请选择创作者</span>
                                </div>
                            </li>
                            <li class="create-product-title">
                                <span>
                                    <span>*</span>作品标题
                                </span>
                                <input type="text" placeholder="请输入作品标题">
                                <div class="title-prompt">
                                    <span>请输入作品标题</span>
                                </div>
                            </li>
                            <li class="create-product-content">
                                <span>
                                    <span>*</span>作品内容
                                </span>

                                <span class="insert-word" style="background:#66b1ff;color:#fff;">
                                    <i style="display:none;height:32px;" class="iconfont icon-loading2 load-icon"></i>
                                    <i class="iconfont icon-daoru" style="margin-right: 5px;"></i>导入word文档
                                </span>
                                <span>如果电脑上有已创作好的文稿, 可以直接导入</span>
                                <form action="" style="display:none;">
                                    <input type="file" id="file" name="file" size="10"/>
                                </form>

                                <#--<input id="uploadbutton" type="button" value="Upload"/>-->

                                <p id="insert-word" style="display:none;"></p>
                                <script id="product-container" name="content" type="text/plain">
                                </script>
                                <div class="content-prompt">
                                    <span>请输入作品内容</span>
                                </div>
                            </li>
                            <li class="create-product-cover">
                                <span>
                                    <span></span>作品封面
                                </span>

                                <img src="" alt="" >
                                <div class="picture-type">
                                    <div>
                                        +从正文选择
                                    </div>
                                    <div>
                                        +从图库选择
                                    </div>
                                </div>
                                <div class="upload-image">
                                    <div id="containerQiniuCardPhoto">
                                        <button type="button" id="uploadasCardPhoto"></button>
                                    </div>
                                </div>
                            </li>
                            <li class="create-product-form">
                                <span>
                                    <span>*</span>作品形式
                                </span>
                                <div>
                                    <label id="type-1">
                                        <span class="radio-inner radio-inner-checked"></span>
                                        <input class="demand-type-radio" name="show" type="radio" checked value="1" id="type-1"  />
                                        <span class="demand-type-item">文章</span>
                                    </label>
                                    <label id="type-2">
                                        <span class="radio-inner"></span>
                                        <input class="demand-type-radio" name="show" type="radio" value="2" id="type-2"/>
                                        <span class="demand-type-item">视频</span>
                                    </label>
                                    <label id="type-3">
                                        <span class="radio-inner"></span>
                                        <input class="demand-type-radio" name="show" type="radio" value="4" id="type-3"/>
                                        <span class="demand-type-item">漫画</span>
                                    </label>
                                    <label id="type-4">
                                        <span class="radio-inner"></span>
                                        <input class="demand-type-radio" name="show" type="radio" value="6" id="type-4" />
                                        <span class="demand-type-item">其他</span>
                                    </label>
                                </div>
                            </li>
                            <li class="create-product-status">
                                  <span>
                                    <span>*</span>发布情况
                                </span>
                                <div>
                                    <label>
                                        <span class="radio-inner radio-inner-checked"></span>
                                        <input class="demand-type-radio" name="status" type="radio" checked value="0"  />
                                        <span class="demand-type-item">未发布</span>
                                    </label>
                                </div>
                            </li>
                            <li class="create-product-price">
                                <span>
                                    <span>*</span>买断价格
                                </span>
                                <input type="text" placeholder="请输入买断价格"><span style="color: #A9ADB0;">&nbsp;元</span>
                                <span>若作品未被录用, 可以在平台以买断价格继续售卖</span>
                                <div class="price-prompt">
                                    <span>请输入买断价格</span>
                                </div>
                            </li>
                        </ul>
                        <div class="product-options">
                            <span class="product-preview">预览</span>
                            <#--<span class="product-submit">提交</span>-->
                            <button class="product-submit">
                                <i style="position:relative;top:1px;left:-3px;display:none;height:37px;" class="iconfont icon-loading2 load-icon"></i>
                                提交
                            </button>
                        </div>
                        <#--<div class="success-status">-->
                            <#--<div class="submit-success">-->
                                <#--<div>-->
                                    <#--<img src="../../../images/demandHall/product-icon.png" alt="">-->
                                <#--</div>-->
                                <#--<div class="submit-success-desc">-->
                                    <#--<div>投稿成功~！</div>-->
                                    <#--<div>录用结果请后续到<span><a style="color:#4895E7;" href="'+ centerUrl +'seller/order/orderList">卖家中心</a></span>的订单列表中去查看呦</div>-->
                                    <#--<div><a href="javascript:location.reload();" style="color:#4895E7;">继续投稿》</a></div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<ul class="submit-list">-->
                            <#--</ul>-->
                        <#--</div>-->
                    </div>
                    <div class="have-product product-item">
                        <i class="have-product-icon iconfont icon-sousuo1"></i>
                        <input placeholder="请输入文章关键字" class="product-search"/>
                        <span class="search-button">搜索</span>
                        <ul class="have-product-list" style="margin-top:10px;">
                        </ul>
                         <#include "../commonFragments/list_no_data.ftl"/>
                        <div class="select-total">
                            <span>已选<span>0</span>个作品</span>
                            <div class="have-product-submit" style="float:right;position:relative;top:-1px;">
                                <span class="have-product-submit-button">提交</span>
                            </div>
                        </div>
                        <div class="pagination-wrap fixedClear" style="width:770px;">
                            <div id="pagination" class="fixedClear"></div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="new-product-message">
                <div class="new-message-box">
                    <ul class="new-message-title">
                        <li class="nav-active">图片素材库</li>
                        <li>本地上传</li>
                        <i class="iconfont icon-guanbi close-icon"></i>
                    </ul>
                    <div class="message-body">
                        <div class="message-body-online">
                            <ul>
                            </ul>
                        </div>
                        <div class="message-body-local" style="display:none;">
                            <div>
                                <span>上传图片:</span>
                                <span class="add-local-image">选取文件</span>
                            </div>
                            <div style="width:110px;height:110px;overflow:hidden;">
                                <img style="height:100%;" src="../../../static/images/article/bg_image_big.jpg" alt="">
                            </div>

                        </div>
                    </div>
                    <div class="message-footer">
                        <span class="cancel-submit">取消</span>
                        <input type="button" class="ensure-submit"  value="确认">
                    </div>
                </div>
            </div>

            <div class="new-product-message-local">
                <div class="new-message-box">
                    <ul class="new-message-title">
                        <li class="nav-active">正文图片</li>
                        <i class="iconfont icon-guanbi close-icon"></i>
                    </ul>
                    <div class="message-body">
                        <div class="message-body-online">
                            <div class="empty-data">
                                暂无数据
                            </div>
                            <ul>
                            </ul>
                        </div>
                    </div>
                    <div class="message-footer">
                        <span class="cancel-submit">取消</span>
                        <input type="button" class="ensure-submit"  value="确认">
                    </div>
                </div>
            </div>

            <div class="have-product-message message-box-wrapper">
                <div class="message-box">
                    <div class="message-title">
                        <span>温馨提示</span>
                        <i class="iconfont icon-guanbi close-icon"></i>
                    </div>
                    <div class="message-body">
                        <div class="message-body-desc">
                            <img style="background:#ecf4fc" src="../../../images/demandHall/product-message-icon.png" alt="">
                            <span>您选择了<span class="submit-total"></span>件作品</span>
                        </div>
                        <ul class="message-product-list">
                        </ul>
                        <div class="message-warning">
                            尊敬的用户，圆融平台要求您的稿件是<span style="color:#4895E7">原创且未发布作品</span>，同时要求您是稿件无争议的创作者。您的投稿被购买后，买方将拥有一切著作权，进行编辑使用，
                            因版权瑕疵给买方造成的一切法律和经济风险需要由您承担，请谨慎投稿。
                        </div>
                    </div>
                    <div class="message-footer">
                        <span class="cancel-submit">取消</span>
                        <input type="button" class="ensure-submit"  value="确认投稿">
                    </div>
                </div>
            </div>

            <div class="product-preview-message">
                <div class="new-message-box">
                    <ul class="new-message-title">
                        <li class="nav-active">预览</li>
                        <i class="iconfont icon-guanbi close-icon"></i>
                    </ul>
                    <div class="message-body">
                        <div class="message-body-online">
                            <p class="preview-title"></p>
                            <div class="preview-info">
                                <span>作者: <span class="preview-author"></span></span>
                                <span>字数: <span class="preview-word"></span></span>
                            </div>
                            <div class="preview-content">

                            </div>
                        </div>
                    </div>
                    <div class="message-footer">
                        <span class="cancel-submit">取消</span>
                        <#--<input type="button" class="ensure-submit"  value="确认">-->
                    </div>
                </div>
            </div>

            <div class="success-status">
                <div class="submit-success">
                    <div>
                        <img src="../../../images/demandHall/product-icon.png" alt="">
                    </div>
                    <div class="submit-success-desc">
                        <div>投稿成功~！</div>
                        <div>录用结果请后续到<span><a style="color:#4895E7;" href="'+ centerUrl +'seller/order/orderList">卖家中心</a></span>的订单列表中去查看呦</div>
                        <div class="continue-sub"><a href="javascript:void(0);" style="color:#4895E7;">继续投稿》</a></div>
                    </div>
                </div>
                <ul class="submit-list">
                </ul>
            </div>

        </div>
        <#include "./demandDetailCommon/rightSlider.ftl"/>
    </div>
</div>
	<#include "../commonFragments/basic/footer.ftl"/>
<!-- 热线电话 -->
	<#include "../commonFragments/hotline.ftl"/>

</body>
<#include "../commonFragments/basic/js_script.ftl"/>
<script src="${request.contextPath}/js/ueditor/ueditor.config.js"></script>
<script src="${request.contextPath}/js/ueditor/ueditor.all.min.js"></script>
<script src="${request.contextPath}/js/ueditor/ueditor.all.js"></script>
<script src="${request.contextPath}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="${request.contextPath}/js/ueditor/ueditor.parse.min.js"></script>
<script type="text/javascript" src="/js/formSelects/formSelects-v4.js" charset="utf-8"></script>
<script type="text/javascript" src="/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="/js/plupload/i18n/zh_CN.js"></script>
<script src="/js/qiniu/qiniu.js"></script>
<script type="text/javascript" src="${request.contextPath}/plugins/jquery.pagination.js?${mdc_version}"></script>
<script src="${request.contextPath}/js/demandHall/demand_work2.js?${mdc_version}"></script>
</html>
