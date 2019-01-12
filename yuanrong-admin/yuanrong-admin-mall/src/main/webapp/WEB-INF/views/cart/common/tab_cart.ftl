<#--  <div class="cart-tab-wrap fixedClear">
    <div class="car-tab-lt-wrap">
        <img src="" alt="">
        选购车
    </div>
    <ul>
        <li class="cart-tab-author">
            <a href='${request.contextPath}/cart/cart_author.html'>创作者</a>
        </li>
        <li class="cart-tab-article">
            <a href='${request.contextPath}/cart/cart_article.html'>作品</a>
        </li>
        <li class="cart-tab-dis">
            <a href='${request.contextPath}/cart/cart_distribution.html'>营销账号</a>
        </li>
    </ul>
</div>  -->
<div class="cart-tab-lt-wrap">
        <img class="cart-tab-img" src="../images/cart/cart.png" alt="">
        选购车
    </div>
    <ul class="cart-tab-wrap fixedClear" id="js-cart-tab-wrap">
        <li class="" data-id="0">
            <a href='${request.contextPath}/cart/cart_author.html'><span>创作者<i id="js-cart-author-totle">[0]</i></span></a>
        </li>
        <li data-id="1">
            <a href='${request.contextPath}/cart/cart_article.html'><span>作品<i id="js-cart-article-totle">[0]</i></span></a>
        </li>
        <li data-id="2">
            <a href='${request.contextPath}/cart/cart_distribution.html'><span>营销账号<i id="js-cart-dis-totle">[0]</i></span></a>
        </li>
    </ul>