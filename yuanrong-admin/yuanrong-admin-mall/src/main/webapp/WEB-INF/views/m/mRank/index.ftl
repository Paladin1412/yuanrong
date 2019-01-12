<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no,email=no">
	<meta name="robots" content="all" />
	<meta name="googlebot" content="index,follow" />
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<meta http-equiv="Content-Language" content="zh-CN" />
	<title>圆融排行榜</title>
	<meta http-equiv="keywords" content='圆融,圆融内容交易服务中心,内容交易平台,圆融内容银行,圆融内容交易,内容银行,微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业' />
	<meta http-equiv="description" content="★圆融内容交易服务中心手机版☆由中国传媒大学,内容银行重点实验室成立,圆融内容银行交易服务中心,内容交易平台,圆融,圆融内容交易服务中心,圆融内容银行,圆融内容交易:短视频广告营销(秒拍，美拍，快手，抖音)，微信变现，微博赚钱,类似：微播易,新榜,Robin8,短视频营销,内容生产创作,微信营销,微博营销,自媒体变现,内容营销,KOL自媒体,内容创业），目的是构建内容生产、传播、变现、资产化的一站式交易平台，面向内容创作者、广告主、代理公司、投融资机构等产业各方，提供内容价值评估、需求对接、资产孵化、交易保障、版权保护等服务，打造内容产业新生态。" />
	<link rel="shortcut icon" href="/favicon.ico" />
	<link rel="stylesheet" href="${request.contextPath}/css/h5/common/common.css?${mdc_version}">
    <style>
    	.wrapBg {
    		background-color: #fff;
    	}
    	.headerBg {
    		height: 5.413333rem;
    		background:url(/images/h5/rank/header-bg.jpg) no-repeat;
    		position: relative;
    		background-size: 100% 63%;
    	}
    	.explain {
    		position: absolute;
    		top: 1.6rem;
    		right: 0.4rem;
    	} 
    	.explain a {
    		color: #fff;
    		font-size: 0.32rem;
    	}
    	.data_info {
    		width: 0.32rem;
    		height: 0.32rem;
    		margin-right: 0.133333rem;
    	}
    	.hide {
    		display: none;
    	}
    	.colour {
    		color: #4895e7;
    	}
    	.header span.on {
            /*background: rgba(6,76,160,1);*/
            background: #1D67BB;
    	}
    	.header span.on i {
    		width: 0;
			height: 0;
			border-width: 0.106667rem;
			border-style: solid;
			border-color: transparent transparent #DAEAFA transparent;
            position: absolute;
			bottom: -0.013333rem;
    		left: 50%;
    	}
		.header {
            background: rgba(6,76,160,.6);
            position: absolute;
            top: 2.266667rem;
    		width: 100%;
    		z-index: 5;
		}
    	.header>span {
    		width: 50%;
    		display: inline-block;
			text-align: center;
			line-height: 1.066667rem;
			font-size: 0.373333rem;
			height: 1.066667rem;
			color: #fff;
            position: relative;
            background: #2A79CE;
    	}
    	.canChoose {
		    height: 1.066667rem;
		    line-height: 1.066667rem;
		    background: #E4EFFC;
		    font-size: 0.346667rem;
        	position: relative;
    		overflow: hidden;
    	}
    	.canChoose ul li {
    		height: 1.066667rem;
    		float: left;
    		margin-left: 0.666667rem;
    	}
    	.remenfl {
    		width: 16.666667rem;
    		height: 1.066667rem;
    	}
    	.remenfl li:nth-child(1){
    		margin-left: 0.466667rem;
    	}
    	.inner-content {
	        position: absolute;
		    left: 0;
		    overflow-y: hidden;
		    overflow-x: scroll;
		    width: 100%;
		    height: 1.066667rem;
		}
		.inner-content::-webkit-scrollbar {
		    display: none;
		}
		.titleCommon {
			width:70%;
			display:inline-block;
		    padding-left: 20%;
		}
		.content {
			width: 100%;
    		font-size: 0.346667rem;
		}
		.exponent {
			width:30%;
			display:inline-block;
			text-align: center;
		}

		.shujuzq {
			width: 100%;
			line-height: 1.013333rem;
    		font-size: 0.346667rem;
			text-align: center;
			background-color: #fff;
			position: relative;
		}
		.shujuzq li {
			border-bottom: 0.013333rem solid #DBDDE0;
		}
		.data_time {
			width: 100%;
    		height: 1.013333rem;
    		line-height: 1.013333rem;
    		font-size: 0.346667rem;
    		text-align:center;
    		background-color: #fff;
    		border-bottom: 0.013333rem solid #DBDDE0;
		}
		.time {
			width: 0.4rem;
			height: 0.4rem;
			margin-right: 0.133333rem;
		}
		.drop_down {
			width: 0.4rem;
			height: 0.4rem;
			margin-left: 0.133333rem;
		}


		.list-con-wrap ,.list-article-wrap{
			overflow-x: hidden;
			overflow-y: auto;
		}
		.list-con-wrap ul,.list-article-wrap ul {
			height: 1.333333rem;
			display: flex;
			align-items: center;
		}
		.list-con-wrap ul li.li-width203 {
			width: 65%;
			text-align: left;
		}
		.list-con-wrap ul li.li-width42 {
			width: 15%;
		}
		.li-width42 {
			width: 15%;
    		text-align: center;
    		float: left;
    		line-height: 1.333333rem;
		}
		.li-width42 img {
			width: 0.533333rem;
			height: 0.666667rem;
		}
        .list-con-wrap ul li {
        	width: 15%;
		    float: left;
		    text-align: center;
        }
        .list-con-wrap ul:nth-child(even),.list-article-wrap ul:nth-child(even) {
			background: #f3f5fa;
		}
        .ip-avatar-wrap{
            display: inline-block;
            width: 0.8rem;
            height: 0.8rem;
            overflow: hidden;
            margin-top: 0.146667rem;
            border-radius: 50%;
            background: #eee;
        }

        .ip-avatar-wrap img{
            width: 100%;
            vertical-align: top;
        }
        .ip-name-wrap{
            display: inline-block;
            text-align: left;
        }
        .ip-name-cn,.ip-name-en{
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 1;
            -webkit-box-orient: vertical;
        }
		.ip-name-cn{
			font-size: 0.346667rem;
            line-height: 0.44rem;
    		width: 100%;
        }
        .ip-name-en{
            color: #64676a;
    		font-size: 0.32rem;
        }
        .list-article-title {
			width: 80%;
			float: left;
	        display: flex;
    		align-items: center;
        }
        .list-article-title a {
			width: 90%;
			line-height: 0.533333rem;
			display: -webkit-box;
			-webkit-box-orient: vertical;
			-webkit-line-clamp: 1;
			overflow: hidden;
		}
        .list-article-index {
        	width: 20%;
			float: right;
			text-align: center;
        }
        .list-article-index .ip-name-cn {
        	color: #4895e7;
        }
    </style>
</head>
<body>

	<div class="wrap wrapBg">
		<div class="headerBg">
			<div class="explain"><a href="/rank/description.html"><img src="/images/h5/rank/data_info.png" class="data_info" alt="">数据说明</a></div>
			<div class="header">
				<span class="ip on" data-id="1">内容价值排行榜<i></i></span><span class="price" data-id="2">IP创作力排行榜<i></i></span>
				<div class="canChoose">
					<div class="inner-content">
						<ul class="remenfl">
						</ul>
					</div>
				</div>
				<div class="data_time">
					<img class="time" src="/images/h5/rank/time_1.png" alt=""><span class="rangeTime"></span><img class="drop_down" src="/images/h5/rank/drop_down.png" alt="">
					<ul class="shujuzq hide">
					</ul>
				</div>

			</div>
		</div>
        
		<div>
			<!-- <div class="canChoose">
				<div class="inner-content">
					<ul class="remenfl">
					</ul>
				</div>
			</div>
			<div class="data_time">
				<img class="time" src="/images/h5/rank/time_1.png" alt=""><span class="rangeTime"></span><img class="drop_down" src="/images/h5/rank/drop_down.png" alt="">
				<ul class="shujuzq hide">
				</ul>
			</div> -->
			<div class="hide">
				<div>
					<span style="float:left;">数据周期:</span>
				</div>
			</div>
		</div>
		<div class="content">
			<div>
				<div class="list-article-wrap">
				</div>
			</div>
			<div class="hide">
				<div class="list-con-wrap" id="js-list-con-wrap">
					
				</div>	
			</div>
		</div>
	</div>
</body>
<script src="${request.contextPath}/plugins/zepto.min.js?${mdc_version}"></script>
<script src="${request.contextPath}/js/config.js?${mdc_version}"></script>
<script src="${request.contextPath}/js/common.js?${mdc_version}"></script>
<script src="${request.contextPath}/plugins/h5/flexible.js?${mdc_version}"></script>
<script src="${request.contextPath}/js/h5/mRank/rank.js?${mdc_version}"></script>
<script>
</script>
</body>
</html>