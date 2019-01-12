<#include "../commonFragments/basic/meta.ftl"/>
	<title>广告主发布需求_圆融内容交易服务中心</title>
	<meta http-equiv="keywords" content='广告主发布需求,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业' />
	<meta http-equiv="description" content="★广告主发布需求☆圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业,圆融由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化、证券化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />
	<#include "../commonFragments/basic/css_link.ftl"/> 
	<link rel="stylesheet" href="${request.contextPath}/css/contentBank/tab.css?${mdc_version}">
	<link rel="stylesheet" href="${request.contextPath}/css/demandHall/publish_demand.css?${mdc_version}">
</head>
<body>
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
<div class="wrap"> 
	<div class="publish-demand">
		<!-- 这是内容银行发布需求的导航 -->
		<#include "common/tab.ftl"/> 
		<!-- 内容银行的发布需求 -->
		<div class="demand">
			<div class="demand-empty">
				<ul class="demand-details">
                <!--<li>-->
                <!--<p style="margin-top: 0;"><span>*</span>初始身份：</p>-->
                <!--<div class="demand-type status-type fixedClear">-->
                <!--<div class="fl fixedClear">-->
                <!--<input id="buy" class="demand-pic fl" type="radio" name="pic-v" value="1" checked="checked">-->
                <!--<label for="buy" class="fl" style="margin-left: 5px;">买家</label>-->
                <!--</div>-->
                <!--<div class="fl fixedClear" style="margin-left: 10px;">-->
                <!--<input id="sale" class="demand-v fl" type="radio" name="pic-v" value="2" >-->
                <!--<label for="sale" class="fl" style="margin-left: 5px;">卖家</label>-->
                <!--</div>-->
                <!--</div>-->
                <!--</li>-->
                <li>
                    <p class="demand-details-type"><span>*</span>创作要求：</p>
                    <div class="demand-type fixedClear" style="width: 560px;">
                        <div class="fl fixedClear">
                            <!--<input class="demand-pic fl" type="radio" name="pic-v" value="1">-->
                            <!--<span class="fl">图文</span>-->
                            <select name="" id="use-scene" >
                                <option value="" >使用场景</option>
                            </select>
                        </div>
                        <div class="fl fixedClear">
                            <!--<input class="demand-v fl" type="radio" name="pic-v" value="2" checked="checked">-->
                            <!--<span class="fl">短视频</span>-->
                            <select name="" id="content-form" >
                                <option value="">内容形式</option>
                            </select>
                        </div>
                        <div class="fl fixedClear">
                            <!--<input class="demand-v fl" type="radio" name="pic-v" value="2" checked="checked">-->
                            <!--<span class="fl">短视频</span>-->
                            <select name="" id="content-type" >
                                <option value="饿哦">目标领域</option>
                            </select>
                        </div>
                        <div class="fl fixedClear">
                            <!--<input class="demand-v fl" type="radio" name="pic-v" value="2" checked="checked">-->
                            <!--<span class="fl">短视频</span>-->
                            <select name="" id="change-times" >
                                <option value="">修改次数</option>
                                <option value="3">3次</option>
                                <option value="5">5次</option>
                                <option value="6">5次以上</option>
                            </select>
                        </div>
                        <div class="fl fixedClear">
                            <!--<input class="demand-v fl" type="radio" name="pic-v" value="2" checked="checked">-->
                            <!--<span class="fl">短视频</span>-->
                            <select name="finish-day" >
                                <option value="">完成天数</option>
                                <option value="">3天内</option>
                                <option value="">3-5天</option>
                                <option value="">5天以上</option>
                            </select>
                        </div>
                        <div class="day-prompt">
                            <span class="color hidden">请选择每一个创作要求!</span>
                        </div>
                    </div>
                </li>
                <li>
                    <p>意向创作者：</p>
                    <div class="fixedClear">
                        <input class="demand-day fl" type="text" value="" placeholder="请输入意向创作者" maxlength="10">
                        <!--<div class="day-prompt">-->
                        <!--<span class="color hidden">请输入有限期限!</span>-->
                        <!--</div>-->
                    </div>
                </li>
                <li>
                    <p><span>*</span>需求名称：</p>
                    <div>
                        <input class="demand-name" type="text" value="" placeholder="4-50个字" maxlength="50">
                        <div class="name-prompt">
                            <span class="color hidden">请输入需求名称!</span>
                            <span class="color hidden">需求名称过短, 请重新输入!</span>
                            <span class="color hidden">需求名称过长, 请重新输入!</span>
                        </div>
                    </div>
                </li>
                <li>
                    <p><span>*</span>需求描述：</p>
                    <div class="demand-text">
                        <textarea class="" placeholder="请输入需求描述"></textarea>
                        <div class="text-prompt">
                            <span class="color hidden">请输入需求描述!</span>
                            <span class="color hidden">需求描述过长, 请重新输入!</span>
                        </div>
                    </div>
                </li>
                <li>
                    <p><span>*</span>需求预算：</p>
                    <div class="fixedClear">
                        <input class="demand-price fl" type="text" value="" placeholder="填写需求预算，可修改" onkeyup="
							if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/[^0-9]/ig,'')}" maxlength="8">
                        <span class="money fr">元</span>
                        <div class="budget-prompt">
                            <span class="color hidden">请输入需求预算!</span>
                        </div>
                    </div>
                </li>

                <li class="phone-number">
                    <p><span>*</span>手机号码：</p>
                    <div class="fixedClear">
                        <input class="demand-call" type="text" value="" placeholder="请输入手机号码" onkeyup="this.value=this.value.replace(/[^0-9]/ig,'')">
                        <div class="call-prompt">
                            <span class="color hidden">请输入手机号!</span>
                            <span class="color hidden">手机号格式不正确, 请重新输入!</span>
                        </div>
                        <!--<input class="demand-push fl" type="text" value="" placeholder="请输入验证码" onkeyup="this.value=this.value.replace(/[^0-9]/ig,'')">-->
                        <!--<input class="demand-pull fr" type="button" value="获取验证码">-->
                        <!--<div class="pull-prompt">-->
                        <!--<span class="color hidden">请输入手机验证码!</span>-->
                        <!--</div>-->
                    </div>
                </li>
                <li class="graph-validate">
                    <p><span>*</span>图形验证：</p>
                    <div class="fixedClear ">
                        <input type="text" class="demand-graph"  placeholder="请输入图形验证码" onkeyup="this.value=this.value.replace(/[^A-Za-z0-9]/ig,'')" maxlength="4">
                        <!--<span class="code-btn" id="">图形验证码</span>-->
                        <img id="img1" alt="验证码" src="${request.contextPath}/registeredUserInfo/validateCode" onclick="changeImgVal1()"/>
                    </div>
                    <div class="img-prompt">
                        <span class="color hidden">请输入图形验证码!</span>
                    </div>
                </li>
                <li class="phone-validate">
                    <p><span>*</span>手机验证：</p>
                    <div class="fixedClear ">
                        <input class="demand-push fl" type="text" value="" placeholder="请输入手机验证码" onkeyup="this.value=this.value.replace(/[^0-9]/ig,'')" maxlength="4">
                        <input class="demand-pull fr" type="button" value="获取验证码">
                    </div>

                    <div class="pull-prompt">
                        <span class="color hidden">请输入手机验证码!</span>
                    </div>
                </li>

                <li>
                    <div style="width: 460px;">
                        <!--<input class="service common qq" type="button" value="联系客服">-->
                        <div class="service common qq" style="">
                            <i class="iconfont icon-kefu qq"></i>
                            <span class="qq ">联系客服</span>
                        </div>
                        <input class="submit common" type="submit" value="立即发布">

                    </div>
                </li>
            </ul>
			</div>
		</div>
	</div>
</div>

    <!--企点客服DOM节点-->
	<div id="qq2"></div>
	<#include "../commonFragments/basic/footer.ftl"/> 	
	<!-- 热线电话 -->
	<#include "../commonFragments/hotline.ftl"/>	
</body>
<#include "../commonFragments/basic/js_script.ftl"/>

<script type="text/javascript">
    // 刷新图片
    function changeImgVal1() {
        var imgSrc = $("#img1");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", changeUrl1(src));
    }
    //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
    function changeUrl1(url) {
        var timestamp = (new Date()).valueOf();
        var index = url.indexOf("?",url);
        if (index > 0) {
            url = url.substring(0, url.indexOf("?"));
        }
        url = url + "?timestamp=" + timestamp;
        return url;
    }
</script>
<#--  <script id="qd285215721426c8eea9f8628e77a18acb4ff47b0b7c" src="https://wp.qiye.qq.com/qidian/2852157214/26c8eea9f8628e77a18acb4ff47b0b7c" charset="utf-8" async defer></script>  -->
<script src="${request.contextPath}/js/demandHall/publish_demand.js?${mdc_version}"></script>
</html>
