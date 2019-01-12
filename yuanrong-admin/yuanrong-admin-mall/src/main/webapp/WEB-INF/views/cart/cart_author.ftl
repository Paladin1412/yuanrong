<#include "../commonFragments/basic/meta.ftl"/>
	<title>选购车_创作者_圆融内容交易服务中心</title>
	<meta http-equiv="keywords" content='IP评估,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业' />
	<meta http-equiv="description" content="★IP评估☆圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易,内容银行,内容创作者,内容创业,圆融由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化、证券化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />
	<#include "../commonFragments/basic/css_link.ftl"/> 
	<link rel="stylesheet" href="${request.contextPath}/css/cart/cart.css?${mdc_version}">
</head>
<body>
<form id="auto_form" style="display:none;" method="post" target="_blank">
	<input type="hidden" class="record" name="data[import_id]" value="">
	<input type="hidden" id="import_id" value="" />
</form>
	<!-- 头部 -->
	<#include "../commonFragments/basic/site.ftl"/>
	<#include "../commonFragments/basic/nav.ftl"/>
<div class="wrap">
	<div class="cart-wrap">
		<div class="cart-tab-con-wrap fixedClear">
			<#include "/cart/common/tab_cart.ftl"/>
			<div class="cart-tab-rt-wrap fixedClear">
				<#include "/cart/common/cart_author_common.ftl"/>
			</div>
		</div>

		<div class="cart-list-con-wrap">
			<div class="cart-author-wrap cart-author-display">
				<!-- 创作者 -->
				<#include "/cart/common/cart_author.ftl"/> 
			</div>
		</div>

	</div>
	
		<div class="cart-fix-bottom-wrap" id="js-cart-bottom-handle-wrap">
			<div class="cart-fix-bottom fixedClear">
				<div class="cart-fix-bot-lt-wrap fixedClear">
					<div class="cart-fix-bot-check-wrap fixedClear" onclick="handleCheckAll($(this).find('.list-check-all'), $('#js-cart-author-list-con-wrap'))">
						<span class="list-check-all " ></span><span>全选</span>
					</div>
					<div class="clear-invalid-data btn-border-disabled" id="js-del-selected-btn" onclick="clearCartData('author', '', 'del')">删除选中的创作者</div>
					<div class="clear-invalid-data btn-border-disabled" id="js-clear-invalid-data" onclick="clearCartData('author', 1, 'clear')">清空失效创作者</div>
					<div class="export-cart-data btn-border-disabled" id="js-export-cart-data" data-id="1">导出选中创作者</div>
				</div>
				<div class="cart-fix-bot-rt-wrap fixedClear">
					<#include "/cart/common/cart_author_common.ftl"/>
				</div>
			</div>
		</div>
		
</div>
<#include "/cart/common/cart_note_pop.ftl"/>
<#include "/cart/common/cart_export.ftl"/>
<!-- 底部 -->
<#include "../commonFragments/basic/footer.ftl"/> 
<!-- 热线电话 -->
<#--  <#include "../commonFragments/hotline.ftl"/>	  -->
</body>
<#include "../commonFragments/basic/js_script.ftl"/>
<script type="text/javascript" src="${request.contextPath}/plugins/jquery.pagination.js?${mdc_version}"></script>
<script src="${request.contextPath}/plugins/xlsx.full.min.js?${mdc_version}"></script>
<#--  <script src="${request.contextPath}/plugins/jquery.table2excel.js?${mdc_version}"></script>  -->
<script src="${request.contextPath}/js/cart/cart.js?${mdc_version}"></script>
<script>
    $('#js-cart-tab-wrap').find('li').eq(0).addClass('active');
	getCartListData('author');
</script>
</html>
