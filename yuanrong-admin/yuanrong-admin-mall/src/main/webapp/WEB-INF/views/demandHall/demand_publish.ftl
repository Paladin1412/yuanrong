<#include "../commonFragments/basic/meta.ftl"/>
<title>发布需求_圆融内容交易服务中心</title>
<meta http-equiv="keywords" content='广告主发布需求,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业' />
<meta http-equiv="description" content="★广告主发布需求☆圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业,圆融由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化、证券化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />
<#include "../commonFragments/basic/css_link.ftl"/>
<link rel="stylesheet" href="${request.contextPath}/css/contentBank/tab.css?${mdc_version}">
<link rel="stylesheet" href="${request.contextPath}/css/demandHall/demand_publish.css?${mdc_version}">
</head>
<body>
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
<div class="wrap wrap-has-bread">
    <div class="publish-demand">
        <div class="demand">
            <div class="demand-empty fixedClear">
                <div class="nav-author">
                    <div>
                        <#--<span class="small-icon-return">&lt;</span>-->

                    </div>
                </div>

                <div class="demand-container-left">
                    <p class="demand-title">发布需求
                        <span class="fast-demand-tip" style="float:right;padding-right:21px;">
                            <img src="../../../images/contentBank/demand-tip.png" alt="">
                            <span class="help">不知道怎么写, 留下信息, 坐等客服帮我梳理需求</span>
                        </span>
                    </p>
                    <form>
                        <ul class="demand-details">
                            <li>
                                <p><span>*</span>需求类型：</p>
                                <div class="demand-type">
                                    <label>
                                        <span class="radio-inner radio-inner-checked"></span>
                                        <input class="demand-type-radio" name="type" type="radio" value="1"  checked />
                                        <span class="demand-type-item">内容定制</span>
                                    </label>
                                    <label>
                                        <span class="radio-inner"></span>
                                        <input class="demand-type-radio" name="type" type="radio" value="4" />
                                        <span class="demand-type-item">原创征稿</span>
                                    </label>
                                    <label>
                                        <span class="radio-inner"></span>
                                        <input class="demand-type-radio" name="type" type="radio" value="2" />
                                        <span class="demand-type-item">IP代理权</span>
                                    </label>
                                    <label>
                                        <span class="radio-inner"></span>
                                        <input class="demand-type-radio" name="type" type="radio" value="3" />
                                        <span  class="demand-type-item">营销分发</span>
                                    </label>
                                </div>
                            </li>

                            <div class="made-type">
                                <li>
                                    <p class="demand-details-type">
                                        <span>*</span>创作要求：
                                    </p>
                                    <div class="fixedClear" >
                                        <div class="fl fixedClear made-screen">
                                            <select name="screen" lay-verify="required" xm-select="select-id1" id="selectId1" xm-select-max="3" >
                                                <option value="">使用场景</option>
                                            </select>
                                            <span class="tip-item hidden">请选择使用场景</span>
                                        </div>
                                        <div class="fl fixedClear made-form">
                                            <select name="form" lay-verify="required" xm-select="select-id2" id="select-id2" xm-select-max="3">
                                                <option value="">内容形式</option>
                                            </select>
                                            <span class="tip-item hidden">请选择内容形式</span>
                                        </div>
                                        <div class="fl fixedClear made-field">
                                            <select name="field" lay-verify="required" xm-select="select-id3" id="select-id3" xm-select-max="3">
                                                <option value="">内容领域</option>
                                            </select>
                                            <span class="tip-item hidden">请选择内容领域</span>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <p><span>*</span>期望完成时间：</p>
                                    <div class="fixedClear expected-time">
                                        <input class="demand-day fl" id="test1" type="text" placeholder="期望完成的时间">
                                        <div class="day-prompt">
                                            <span class="color hidden">请选择完成时间</span>
                                        </div>
                                    </div>
                                </li>
                                <li class="made-author">
                                    <p><span>*</span>期望作者数：</p>
                                    <div class="fixedClear expected-authors">
                                        <input class="fl"type="text" placeholder="请输入期望作者数" onkeyup="
							if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/[^0-9]/ig,'')}"><span class="authors-item">&nbsp;个</span>
                                        <div class="authors-prompt">
                                            <span class="color hidden">请输入期望作者数</span>
                                        </div>
                                    </div>
                                </li>
                            </div>

                            <div class="IP-type">
                                <li>
                                    <p class="demand-details-type">
                                        <span>*</span>IP形式：
                                    </p>
                                    <div class="demand-checked fixedClear">
                                        <div class="fl fixedClear">
                                            <label for="ip-type-1">
                                                <span class="checkbox-inner"></span>
                                                <input class="demand-checked-type" type="checkbox" id="ip-type-1" name="" value="图文"/>
                                                <span class="demand-checked-item">图文</span>
                                            </label>
                                            <label for="ip-type-2">
                                                <span class="checkbox-inner"></span>
                                                <input class="demand-checked-type" type="checkbox" id="ip-type-2"  name="" value="视频" />
                                                <span class="demand-checked-item">视频</span>
                                            </label>
                                            <label for="ip-type-3">
                                                <span class="checkbox-inner"></span>
                                                <input class="demand-checked-type" type="checkbox" id="ip-type-3" name="" value="漫画" />
                                                <span class="demand-checked-item">漫画</span>
                                            </label>
                                        </div>
                                        <div class="IP-type-prompt">
                                            <span class="color hidden">请选择IP形式</span>
                                        </div>
                                </li>
                                <li>
                                    <p class="demand-details-type"><span>*</span>IP行业：</p>
                                    <div class="fixedClear" style="width: 560px;">
                                        <div class="fl fixedClear">
                                            <select name="city" lay-verify="required" xm-select="select-id9" xm-select-max="3">
                                                <option value=""></option>
                                            </select>
                                        </div>
                                        <div class="IP-industry-prompt">
                                            <span class="color hidden">请选择IP行业</span>
                                        </div>
                                    </div>
                                </li>
                            </div>

                            <div class="marketing-type">
                                <li class="marketing-type-plat">
                                    <p class="demand-details-type">
                                        <span>*</span>发布平台：
                                    </p>
                                    <div class="demand-checked fixedClear">
                                        <div class="fl fixedClear">
                                            <label for="market-check1">
                                                <span class="checkbox-inner"></span>
                                                <input class="market-type-check" type="checkbox" id="market-check1" value="微信" />
                                                <span class="market-type-item">微信</span>
                                            </label>
                                            <label for="market-check2">
                                                <span class="checkbox-inner"></span>
                                                <input class="market-type-check" type="checkbox" id="market-check2" value="微博" />
                                                <span class="market-type-item">微博</span>
                                            </label>
                                            <label for="market-check3">
                                                <span class="checkbox-inner"></span>
                                                <input class="market-type-check" type="checkbox" id="market-check3" value="视频" />
                                                <span class="market-type-item">视频</span>
                                            </label>
                                        </div>
                                        <div class="platform-prompt">
                                            <span class="color hidden">请选择发布平台</span>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <p><span>*</span>推广时间：</p>
                                    <div class="fixedClear market-expected-time">
                                        <input class="market-day fl" id="test2" type="text" placeholder="请选择推广时间">
                                        <div class="day-prompt">
                                            <span class="color hidden">请选择推广时间</span>
                                        </div>
                                    </div>
                                </li>
                                <li class="marketing-type-cate">
                                    <p class="demand-details-type"><span>*</span>账号分类：</p>
                                    <div class="fixedClear" style="width: 560px;">
                                        <div class="fl fixedClear">
                                            <select name="city" lay-verify="required" xm-select="select-id6" xm-select-max="3">
                                                <option value=""></option>
                                            </select>
                                        </div>
                                        <div class="account-prompt">
                                            <span class="color hidden">请选择账号分类</span>
                                        </div>
                                    </div>
                                </li>
                                <li class="marketing-type-fans">
                                    <p><span>*</span>粉丝数量：</p>
                                    <div class="fixedClear">
                                        <input class="demand-fans1 fl" placeholder="请输入" style="width:80px;height:34px;" type="text" value="" onkeyup="
							if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/[^0-9]/ig,'')}" maxlength="8">&nbsp;至
                                        <input class="demand-fans2" placeholder="请输入" style="width:80px;height:34px;" type="text" value="" onkeyup="
							if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/[^0-9]/ig,'')}" maxlength="8">&nbsp;万人
                                        <div class="fans-prompt">
                                            <span class="color hidden">请输入粉丝数</span>
                                            <span class="color hidden">请输入正确的粉丝数区间</span>
                                        </div>
                                    </div>
                                </li>
                                <li class="marketing-account">
                                    <p><span>*</span>期望账号数：</p>
                                    <div class="fixedClear expected-account">
                                        <input class="fl"type="text" placeholder="请输入期望账号数" onkeyup="
							if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/[^0-9]/ig,'')}"><span style="line-height:36px;">&nbsp;个</span>
                                        <div class="expected-account-prompt">
                                            <span class="color hidden">请输入期望账号数</span>
                                        </div>
                                    </div>
                                </li>
                            </div>

                            <li class="budget">
                                <p><span>*</span>需求预算：</p>
                                <div class="fixedClear">
                                    <input class="demand-price fl" type="text" value="" placeholder="填写需求预算，可修改" onkeyup="
							if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/[^0-9]/ig,'')}" maxlength="8">
                                    <span class="money" style="float: left;">&nbsp;元</span>
                                    <div class="budget-prompt">
                                        <span class="color hidden">请输入需求预算</span>
                                        <span class="color hidden">请输入大于10的需求预算</span>
                                    </div>
                                </div>
                            </li>
                            <div class="origin-contribution2" style="display:none;">
                                <li>
                                    <p><span>*</span>表现形式：</p>
                                    <div class="demand-show">
                                        <label>
                                            <span class="radio-inner radio-inner-checked"></span>
                                            <input class="demand-type-radio" name="origin-form" type="radio" checked value="文章"  />
                                            <span class="demand-type-item">文章</span>
                                        </label>
                                        <label>
                                            <span class="radio-inner"></span>
                                            <input class="demand-type-radio" name="origin-form" type="radio" value="视频" />
                                            <span class="demand-type-item">视频</span>
                                        </label>
                                        <label>
                                            <span class="radio-inner"></span>
                                            <input class="demand-type-radio" name="origin-form" type="radio" value="漫画" />
                                            <span class="demand-type-item">漫画</span>
                                        </label>
                                    </div>
                                </li>
                                <li class="origin-contribution-num">
                                    <p>字数要求：</p>
                                    <div class="fixedClear word-num">
                                        <input class="origin-input-item num1" placeholder="" type="text" onkeyup="
							if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/[^0-9]/ig,'')}">
                                        <span>——</span>
                                        <input class="origin-input-item num2" placeholder="" type="text" onkeyup="
							if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/[^0-9]/ig,'')}"><span>&nbsp;字</span>
                                        <div class="word-num-prompt">
                                            <span class="color hidden">请输入字数要求</span>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <p><span>*</span>稿件费用：</p>
                                    <div class="fixedClear work-price">
                                        <input class="origin-input-item" placeholder="" type="text" onkeyup="
							if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/[^0-9]/ig,'')}"><span>&nbsp;元/篇</span>
                                        <div class="work-price-prompt">
                                            <span class="color hidden">请输入稿件费用</span>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <p><span>*</span>征集数量：</p>
                                    <div class="fixedClear work-num">
                                        <input class="origin-input-item" placeholder="" type="text" onkeyup="
							if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/[^0-9]/ig,'')}"><span>&nbsp;篇</span>
                                        <div class="work-num-prompt">
                                            <span class="color hidden">请输入征集数量</span>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <p><span>*</span>截止日期：</p>
                                    <div>
                                        <input class="demand-day fl" id="test3" type="text" placeholder="请选择截止日期">
                                    <#--<input class="origin-input-item" type="text">-->
                                        <div class="day-prompt">
                                            <span class="color hidden">请选择截止日期</span>
                                        </div>
                                    </div>
                                </li>
                            </div>
                            <li class="demand-total-name">
                                <p><span>*</span>需求名称：</p>
                                <div style="width:360px;">
                                    <input class="demand-name" privateAttr="name" type="text" value=""  placeholder="一句话描述您的需求(4-30个字)" maxlength="30" />
                                    <div class="name-prompt">
                                        <span class="color hidden">请输入需求名称</span>
                                        <span class="color hidden">需求名称过短, 请重新输入</span>
                                        <span class="color hidden">需求名称过长, 请重新输入</span>
                                    </div>
                                </div>
                            </li>
                            <li class="description">
                                <p>需求描述：</p>
                                <div class="demand-text">
                                    <div class="text-tip">
                                        <span class="color made-tip" style="display:none;"><img src="../../../images/contentBank/demand-edit.png" alt="">看看别人怎么写</span>
                                        <span class="color market-tip" style="display:none;"><img src="../../../images/contentBank/demand-edit.png" alt="">看看别人怎么写</span>
                                    </div>
                                    <textarea class="" placeholder="请输入需求描述(1000字以内)" maxlength="1000"></textarea>
                                    <p class="demand-text-word" style="text-align:right;"><span style="color:#409eff;">0</span>/1000字</p>
                                </div>
                            </li>

                            <div class="made-type" style="display:none;">
                                <li>
                                    <p>需求附件：</p>
                                    <div class="fixedClear attachment" id="containerQiniuCardPhoto">
                                        <button type="button" id="uploadasCardPhoto" class="layui-btn layui-btn-normal" style="color:#2899eb;background-color:#f5faff;border:1px solid #ddeaf9;font-size:12px;height: 36px;" >选择文件</button>
                                        <span style="font-size:12px; ">最多可添加5个附件, 单个文件大小不超过20M</span>
                                        <div class="layui-upload-list">
                                            <table class="layui-table">
                                                <tbody id="qiniuList"></tbody>
                                            </table>
                                            <span id="start" style="visibility:hidden;"></span>
                                            <span id="qiniuProgressCardPhoto"></span>
                                        </div>
                                </li>
                                <li class="made-price">
                                    <p><span>*</span>期望获得报价：<span class="expected-price-note-tit">（最多三个）</span></p>
                                    <div class="fixedClear  expected-price" style="width:600px;">
                                        <p class="expected-price-note">针对以上需求，填写您要获取的报价的名称，创作者报名后，您只能在获取的报价中选择一个进行交易</p>
                                        <input class="fl" type="text" value="" maxlength="50" placeholder="请输入你想获得的报价，如：原创报价"><span class="add-price-icon">&nbsp;+</span>
                                        <div class="expected-price-prompt">
                                            <span class="color hidden">请输入期望获得报价</span>
                                        </div>
                                    </div>
                                </li>
                                <li class="made-show">
                                    <p><span>*</span>需求大厅展示：</p>
                                    <div class="demand-show">
                                        <label>
                                            <span class="radio-inner radio-inner-checked"></span>
                                            <input class="demand-type-radio" name="made-show" type="radio" checked value="1"  />
                                            <span class="demand-type-item">是</span>
                                        </label>
                                        <label>
                                            <span class="radio-inner"></span>
                                            <input class="demand-type-radio" name="made-show" type="radio" value="0" />
                                            <span class="demand-type-item">否</span>
                                        </label>
                                    </div>
                                    <div class="demand-show-tip">
                                        如果选择是，在需求大厅展示，允许自主报名/投稿，也可平台推荐；如选择否，仅平台推荐&自选
                                    </div>
                                </li>

                                <li class="made-industry">
                                    <p class="demand-details-type"><span>*</span>所属行业：</p>
                                    <div class="demand-industry fixedClear" style="width: 560px;">
                                        <div class="fl fixedClear">
                                            <select name="city" lay-verify="required" xm-select="select-id7" xm-select-radio >
                                                <option value=""></option>
                                            </select>
                                        </div>
                                        <div class="industry-prompt">
                                            <span class="color hidden">请选择所属行业</span>
                                        </div>
                                    </div>
                                </li>
                            </div>
                            <div class="marketing-type">
                                <li>
                                    <p>需求附件：</p>
                                    <div class="fixedClear attachment" id="containerQiniuCardPhoto1">
                                        <button type="button" id="uploadasCardPhoto1" class="layui-btn layui-btn-normal" style="color:#2899eb;background-color:#f5faff;border:1px solid #ddeaf9;font-size:12px;height: 36px;" >选择文件</button>
                                        <span style="font-size:12px; ">最多可添加5个附件, 单个文件大小不超过20M</span>
                                        <div class="layui-upload-list">
                                            <table class="layui-table">
                                                <tbody id="qiniuList1"></tbody>
                                            </table>
                                            <span id="start1" style="visibility:hidden;"></span>
                                            <span id="qiniuProgressCardPhoto1"></span>
                                        </div>
                                </li>
                                <li class="marketing-price">
                                    <p><span>*</span>期望获得报价：<span class="expected-price-note-tit" >（最多三个）</span></p>
                                    <div class="fixedClear expected-price" style="width:600px">
                                        <p class="expected-price-note">针对以上需求，填写您要获取的报价的名称，账号应约后，您只能在获取的报价中选择一个进行交易</p>
                                        <input class="fl" type="text" value="" maxlength="50" placeholder="请输入你想获得的报价，如：出席活动+微信头条原创+发布"><span class="add-price-icon">&nbsp;+</span>
                                        <div class="expected-price-prompt">
                                            <span class="color hidden">请输入期望获得报价</span>
                                        </div>
                                    </div>
                                </li>
                                <li class="marketing-show">
                                    <p><span>*</span>需求大厅展示：</p>
                                    <div class="demand-show">
                                        <label>
                                            <span class="radio-inner radio-inner-checked"></span>

                                            <input class="demand-type-radio" name="show" type="radio" checked value="1"  />
                                            <span class="demand-type-item">是</span>
                                        </label>
                                        <label>
                                            <span class="radio-inner"></span>
                                            <input class="demand-type-radio" name="show" type="radio" value="0" />
                                            <span class="demand-type-item">否</span>
                                        </label>
                                    </div>
                                    <div class="demand-show-tip">
                                        如果选择是，在需求大厅展示，允许自主报名/投稿，也可平台推荐；如选择否，仅平台推荐&自选
                                    </div>
                                </li>

                                <li>
                                    <p class="demand-details-type"><span>*</span>所属行业：</p>
                                    <div class="demand-industry fixedClear" style="width: 560px;">
                                        <div class="fl fixedClear">
                                            <select name="city" lay-verify="required" xm-select="select-id8" xm-select-radio >
                                                <option value=""></option>
                                            </select>
                                        </div>
                                        <div class="market-industry-prompt">
                                            <span class="color hidden">请选择所属行业</span>
                                        </div>
                                    </div>
                                </li>
                            </div>
                            <div class="origin-contribution">
                                <#--<li>-->
                                    <#--<p><span>*</span>需求名称：</p>-->
                                    <#--<div style="width:360px;">-->
                                        <#--<input class="demand-name" privateAttr="name" type="text" value=""  placeholder="一句话描述您的需求(4-50个字)" maxlength="50" />-->
                                        <#--<div class="name-prompt">-->
                                            <#--<span class="color hidden">请输入需求名称</span>-->
                                            <#--<span class="color hidden">需求名称过短, 请重新输入</span>-->
                                            <#--<span class="color hidden">需求名称过长, 请重新输入</span>-->
                                        <#--</div>-->
                                    <#--</div>-->
                                <#--</li>-->
                                <li class="origin-description">
                                    <p><span>*</span>征稿要求：</p>
                                    <div class="demand-text">
                                        <div class="text-tip">
                                            <span class="color origin-tip"><img src="../../../images/contentBank/demand-edit.png" alt="">看看别人怎么写</span>
                                        </div>
                                        <textarea class="" placeholder="请详细填写您对投稿作品的要求" maxlength="1000"></textarea>
                                        <p class="demand-text-word" style="text-align:right;"><span style="color:#409eff;">0</span>/1000字</p>
                                    </div>
                                </li>
                                <li>
                                    <p>素材附件：</p>
                                    <div class="fixedClear attachment" id="containerQiniuCardPhoto2">
                                        <button type="button" id="uploadasCardPhoto2" class="layui-btn layui-btn-normal" style="color:#2899eb;background-color:#f5faff;border:1px solid #ddeaf9;font-size:12px;height: 36px;" >选择文件</button>
                                        <span style="font-size:12px; ">最多可添加5个附件, 单个文件大小不超过20M</span>
                                        <div class="layui-upload-list">
                                            <table class="layui-table">
                                                <tbody id="qiniuList2"></tbody>
                                            </table>
                                            <span id="start2" style="visibility:hidden;"></span>
                                            <span id="qiniuProgressCardPhoto2"></span>
                                        </div>
                                </li>
                                <li>
                                    <p><span>*</span>需求大厅展示：</p>
                                    <div class="demand-show">
                                        <label>
                                            <span class="radio-inner radio-inner-checked"></span>
                                            <input class="demand-type-radio" name="origin-show" type="radio" checked value="1"  />
                                            <span class="demand-type-item">是</span>
                                        </label>
                                        <label>
                                            <span class="radio-inner"></span>
                                            <input class="demand-type-radio" name="origin-show" type="radio" value="0" />
                                            <span class="demand-type-item">否</span>
                                        </label>
                                    </div>
                                    <div class="demand-show-tip">
                                        如果选择是，在需求大厅展示，允许自主报名/投稿，也可平台推荐；如选择否，仅平台推荐&自选
                                    </div>
                                </li>
                                <li class="origin-industry">
                                    <p class="demand-details-type"><span>*</span>所属行业：</p>
                                    <div class="demand-industry fixedClear" style="width: 560px;">
                                        <div class="fl fixedClear">
                                            <select name="city" lay-verify="required" xm-select="select-id10" xm-select-radio >
                                                <option value=""></option>
                                            </select>
                                        </div>
                                        <div class="origin-industry-prompt">
                                            <span class="color hidden">请选择所属行业</span>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <p>参考样稿：</p>
                                    <div class="consult-example">
                                        <input type="text" placeholder="http://">
                                    </div>
                                </li>
                            </div>

                            <li class="phone-number">
                                <p><span>*</span>联系电话：</p>
                                <div class="fixedClear">
                                    <input class="demand-call" type="text" value="" placeholder="请输入手机号码" maxlength="11" onkeyup="this.value=this.value.replace(/[^0-9]/ig,'')">
                                    <div class="call-prompt">
                                        <span class="color hidden">请输入手机号</span>
                                        <span class="color hidden">手机号格式不正确, 请重新输入</span>
                                    </div>
                                </div>
                            </li>
                            <li class="graph-validate">
                                <p><span>*</span>图形验证：</p>
                                <div class="fixedClear ">
                                    <input type="text" class="demand-graph"  placeholder="请输入图形验证码" onkeyup="this.value=this.value.replace(/[^A-Za-z0-9]/ig,'')" maxlength="4">
                                    <img id="img1" alt="验证码" src="${request.contextPath}/verificationCode/getValidateImg" onclick="changeImgVal1()"/>
                                </div>
                                <div class="img-prompt">
                                    <span class="color hidden">请输入图形验证码</span>
                                    <span class="color hidden">验证码输入错误</span>
                                </div>
                            </li>
                            <li class="phone-validate">
                                <p><span>*</span>手机验证：</p>
                                <div class="fixedClear ">
                                    <input class="demand-push fl" type="text" value="" placeholder="请输入手机验证码" onkeyup="this.value=this.value.replace(/[^0-9]/ig,'')" maxlength="4">
                                    <input class="demand-pull" type="button" value="获取验证码">
                                </div>
                                <div class="pull-prompt">
                                    <span class="color hidden">请输入手机验证码</span>
                                    <span class="color hidden">手机验证码输入错误</span>
                                </div>
                            </li>
                        <#--已选创作者或账号列表-->
                            <div class="detail-list" style="display:none;">
                                <div>
                                    <span style="font-size:16px;" class="detail-list-title">已选创作者</span>[<span class="list-total">2</span>]
                                </div>
                                <ul class="select-list">
                                    <li>
                                        <span style="width:10%">序号</span>
                                        <span style="width:25%;">创作者</span>
                                        <span style="width:25%;">简介</span>
                                        <span style="width:25%;">原创报价(元)</span>
                                        <span style="width:12%;">操作</span>
                                    </li>
                                </ul>
                            </div>
                            <div class="detail-account-list" style="display:none;">
                                <div>
                                    <span style="font-size:16px;" class="detail-list-title">已选营销分发账号</span>[<span class="list-total">2</span>]
                                </div>
                                <ul class="select-list">
                                    <li>
                                        <span style="width:10%;">序号</span>
                                        <span style="width:30%;">账号</span>
                                        <span style="width:20%;">粉丝数(位)</span>
                                        <span style="width:25%;">营销账号参考报价</span>
                                        <span style="width:10%">操作</span>
                                    </li>
                                </ul>
                            </div>
                        </ul>


                    </form>

                <#--提交按钮-->
                    <div style="width: 100%;">
                        <div style="width: 100%; text-align: center;">
                            <#--<input class="submit common" style="width:96px;height:40px;margin-bottom:50px;" type="submit" value="立即提交">-->
                                <#--<i style="position:relative;top:1px;left:-3px;display:inline-block" class="iconfont icon-loading2 load-icon"></i>-->
                            <button class="submit common" style="width:96px;height:40px;margin-bottom:50px;line-height:40px;" >
                                <i style="position:relative;top:1px;left:-3px;display:none;height:37px;" class="iconfont icon-loading2 load-icon"></i>
                                <span>立即提交</span>
                            </button>
                        </div>
                    </div>
                </div>

                <div class="right">
                    <div class="right-top">
                        <div class="project-adviser">
                            <div class="project-adviser-avatar">
                                <#--<img src="../../../static/images/user.png" alt="">-->
                                <img src="../../../static/images/article/header.jpg" alt="">
                            </div>
                            <div>
                                <span></span>
                            </div>
                            <div>
                                <a target="_blank" href="http://q.url.cn/ab3X7U?_type=wpa&qidian=true" style="color:#fff;">
                                    <span class="online-service">在线咨询</span>
                                </a>

                            </div>
                            <div style="text-align:center;">
                                <span>客服热线: </span>
                                <span class="online-phone">4008198818</span>
                            </div>
                        </div>
                        <div class="problem-info">
                            <div>热门问题:</div>
                            <ul>
                                <li>1. 怎么买作品/找作者写稿/找账号发广告？</li>
                                <li>2. 在圆融平台进行交易买家需要何时付款？</li>
                                <li>3. 如果与卖方有纠纷怎么退款/进行售后？</li>
                                <li>4. 买稿件/文章后拥有著作权是指什么权益？</li>
                            </ul>
                        </div>
                    </div>
                    <div class="right-bottom">
                        <div>流程指南</div>
                        <ul>
                            <li><span>1</span><span>发布需求</span></li>
                            <li><span>2</span><span>平台审核</span></li>
                            <li><span>3</span><span>买家支付预付款</span></li>
                            <li><span>4</span><span>需求通过发布</span></li>
                            <li><span>5</span><span>征集作者作品</span></li>
                            <li><span>6</span><span>买家确认</span></li>
                            <li><span>7</span><span>平台结算</span></li>
                        </ul>
                    </div>
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
<script type="text/javascript" src="/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="/js/plupload/i18n/zh_CN.js"></script>
<script src="/js/qiniu/qiniu.js"></script>
<script src="${request.contextPath}/js/demandHall/demand_publish.js?${mdc_version}"></script>
<script src="//sandbox.runjs.cn/uploads/rs/96/wiqefzh7/formSelects-v4.js" type="text/javascript" charset="utf-8"></script>
</html>

