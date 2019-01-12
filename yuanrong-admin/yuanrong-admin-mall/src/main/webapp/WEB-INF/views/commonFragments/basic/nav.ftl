
<div class="header-nav-wrap trans-all-2" id="js-header-nav-wrap">
    <input type="hidden" value="${request.contextPath}" id="js-ajax-domain">
    <div class="inner-wrap header-con-wrap trans-all-2 fixedClear">
        <div class="header-logo-wrap fixedClear">
            <a href="/" style="display: block;" class="fixedClear">
                <span class="header-logo"><img src="${request.contextPath}/images/index_1.6/logo.png" alt=""></span>
                <span class="header-line"></span>
                <span class="header-txt">内容交易服务平台</span>
            </a>
        </div>
        <ul class="header-nav-list-wrap fixedClear">
            <li class="nav-index"><a class="" href="/">首页</a></li>
            <li class="nav-demand-hall"><a class="fixedClear" href="${request.contextPath}/demandHall/demand_hall.html">需求大厅<img class="nav-hot" src="${request.contextPath}/images/commonFragments/new.gif" alt=""></a></li>
            <li class="nav-works"><a class="fixedClear" href="${request.contextPath}/contentBank/original_work.html">买作品<img class="nav-hot" src="${request.contextPath}/images/commonFragments/hot.gif" alt=""></a></li>
            <li class="nav-authors"><a href="${request.contextPath}/contentBank/author_images.html">找作者</a></li>
            <li class="nav-accounts"><a href="${request.contextPath}/ipTrade/ip_trade_distribution.html?adType=0">做推广</a></li>
            <li class="nav-agency"><a href="${request.contextPath}/segmentAccount/segment_account.html">签账号</a></li>
            <li class="nav-rank"><a href="${request.contextPath}/ipEvaluation/ip_ranking_article.html">圆融榜单</a></li>
        </ul>
        <a class="header-publish-btn" onclick="localStorage.setItem('isPubIDs', 0)" href="${request.contextPath}/demandHall/demand_publish.html">发布需求</a>
        <div class="header-search-wrap fixedClear">
            <div class="header-search-type-wrap trans-all-2 fixedClear">
                <div class="header-search-tit-wrap trans-all-2"><span class="search-type-tit" id="js-search-type-tit">买作品</span><img class="down-img trans-all-2" src="${request.contextPath}/images/commonFragments/arr_down.png" alt=""><img class="up-img trans-all-2" src="${request.contextPath}/images/commonFragments/arr_up.png" alt=""></div>
                <ul class="header-search-type-list trans-all-2" id="js-search-type-list">
                    <li data-id="0" class="trans-all-2 active">买作品</li>
                    <li data-id="1" class="trans-all-2">找作者</li>
                    <li data-id="2" class="trans-all-2">做推广</li>
                    <li data-id="3" class="trans-all-2">签账号</li>
                </ul>
            </div>
            <div class="header-search-input-wrap">
                <input type="text" placeholder="请输入搜索关键字" id="js-search-text">
            </div>
            <div class="header-search-btn" id="js-header-search-btn">搜索</div>
        </div>
    </div>
</div>