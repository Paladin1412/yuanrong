<div class="cart-side-box">
    <ul class="cart-side-tab-wrap fixedClear" id="js-cart-side-tab-wrap">
        <li class="active" data-id="0"><span>创作者(<i class=" side-author-totle" >0</i>)</span></li>
        <li data-id="1"><span>作品(<i class=" side-article-totle" >0</i>)</span></li>
        <li data-id="2"><span>营销账号(<i class=" side-dis-totle" >0</i>)</span></li>
    </ul>
    <div class="cart-side-panel-wrap">
        <div class="cart-side-author-wrap">
            <div class="cart-side-content-wrap fixedClear">
                <div class="cart-side-nologin-wrap " id="js-cart-side-nologin-wrap">
                    <span class="cart-side-tip-text">您还没有登录， </span><a onclick="goLoginPage()" href="javascript:;" class="cart-side-login-btn">登录</a>  后可看到更多内容
                    <#--  <span class="cart-side-tip-text">还没有登录，登录后商品将被保存</span><a onclick="goLoginPage()" href="javascript:;" class="cart-side-login-btn">登录</a>  -->
                </div>
                <ul class="side-author-list-wrap side-cart-ul side-author-display" id="js-cart-side-author-wrap">
                    <#--  <li class="fixedClear">
                        <div class="side-avatar-wrap">
                            <img src="${request.contextPath}/images/avatar.png" alt="">
                        </div>
                        <div class="side-right-wrap">
                            <div class="side-tit-wrap side-tit-author-wrap">
                                <p>99999</p>
                            </div>
                            <div class="side-price-wrap">
                                ￥999
                            </div>
                            <div class="side-del">删除</div>
                        </div>
                        <div class="side-invalid-wrap hide-side-invalid-wrap">
                            <div class="side-invalid-tit-wrap">
                                失效
                            </div>
                        </div>
                    </li>  -->
                </ul>
                <ul class="side-article-list-wrap side-cart-ul side-article-display" id="js-cart-side-article-wrap">
                    <#--  <li class="fixedClear">
                        <div class="side-avatar-wrap fixedClear">
                            <img src="${request.contextPath}/images/avatar.png" alt="">
                            <div class="side-avatar-article-wrap" title="未发布"><i class="iconfont icon-eye-slash"></i></div>
                        </div>
                        <div class="side-right-wrap">
                            <div class="side-tit-wrap">
                                <p>今天是星期二今天是星期二今天是星期二今天是星期二今天是星期二今天是星期二今天是星期二今天是星期二今天是星期二今天是星期二今天是星期二今天是星期二今天是星期二</p>
                            </div>
                            <div class="side-price-wrap">
                                ￥999
                            </div>
                            <div class="side-del">删除</div>
                        </div>
                        <div class="side-invalid-wrap hide-side-invalid-wrap"><div class="side-invalid-tit-wrap">
                                失效
                            </div></div>
                    </li>  -->
                </ul>
                <ul class="side-distribution-list-wrap side-cart-ul side-dis-display" id="js-cart-side-distribution-wrap">
                    <#--  <li class="fixedClear">
                        <div class="side-avatar-wrap side-avatar-dis-wrap">
                            <img class="side-account-avatar" src="${request.contextPath}/images/avatar.png" alt="">
                        </div>
                        <img class="side-account-icon" src="${request.contextPath}/images/avatar.png" alt="">
                        <div class="side-right-wrap">
                            <div class="side-tit-wrap">
                                <p class="color-gray-303">99999</p>
                                <p class="color-gray-646">99999</p>
                            </div>
                            <div class="side-price-wrap">
                                粉丝数：90909090
                            </div>
                            <div class="side-del">删除</div>
                        </div>
                        <div class="side-invalid-wrap hide-side-invalid-wrap"><div class="side-invalid-tit-wrap">
                                失效
                            </div></div>
                    </li>  -->
                </ul>
                <div class="cart-side-bottom-wrap cart-hide" id="js-cart-side-bottom-wrap">
                    <div class="cart-side-num-bot-wrap " id="js-cart-side-num-bot-wrap">
                        <div class="cart-side-num-author-wrap side-author-display">
                            已选创作者<span class="color-blue side-author-totle" >0</span>个
                        </div>
                        <div class="cart-side-num-article-wrap side-article-display">
                            <ul class="fixedClear">
                                <li>
                                    <div>已选作品<span class="color-blue side-article-totle">0</span>个</div>
                                    <div class="color-blue"><span style="font-size: 14px;" id="js-side-article-sum">0</span></div>
                                </li>
                                <li class="cart-side-eye-wrap">
                                    <div><i class="iconfont icon-eye1 "></i>已发布</div>
                                    <div><span id="js-publish-totle"></span>/<span class="color-blue"><span id="js-side-art-pub-sum">0</span></span></div>
                                </li>
                                <li class="cart-side-eye-slash-wrap">
                                    <div><i class="iconfont icon-eye-slash"></i>未发布</div>
                                    <div><span id="js-unpublish-totle"></span>/<span class="color-blue"><span id="js-side-art-unpub-sum">0</span></span></div>
                                </li>
                            </ul>
                        </div>
                        <div class="cart-side-num-dis-wrap side-dis-display fixedClear">
                            <div class="car-side-num-dis-lt">
                                <div>已选账号<span class="color-blue side-dis-totle" >0</span>个</div>
                            </div>
                            <ul class="car-side-num-dis-rt fixedClear">
                                <li>
                                    <div><i class="iconfont icon-weixin-copy"></i></div>
                                    <div id="js-cart-wx-num">0</div>
                                </li>
                                <li class="cart-side-eye-wrap">
                                    <div><i class="iconfont icon-weibo1"></i></div>
                                    <div id="js-cart-wb-num">0</div>
                                </li>
                                <li class="cart-side-eye-slash-wrap">
                                    <div><i class="iconfont icon-shipinbofang"></i></div>
                                    <div id="js-cart-sv-num">0</div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="cart-side-btn-wrap" id="js-cart-side-btn-wrap">
                        <span class="cart-side-btn-clear" id="js-cart-side-clear-all" onclick="clearCartData($('#js-cart-side-tab-wrap .active').data('id'), '', 'sideclear')">一键清空</span>
                        <span class="" id="js-cart-side-fullscreen"><a onclick="goCartPage($('#js-cart-side-tab-wrap .active').data('id'))" href="javascript:;">全屏显示</a></span>
                        <span class="cart-side-btn-blue" id="js-cart-side-btn-blue" onclick="goCartPage($('#js-cart-side-tab-wrap .active').data('id'))">立即预约</span>
                    </div>
                </div>
            </div>
        </div>
        <!-- <div class="cart-side-article-wrap" id="js-cart-side-article-wrap">
            <div class="cart-side-content-wrap">
                <ul class="side-article-list-wrap" id="js-cart-side-article-wrap">
                    <li>000</li>
                </ul>
            </div>
        </div>
        <div class="cart-side-distribution-wrap" id="js-cart-side-distribution-wrap">
            <div class="cart-side-content-wrap">
                <ul class="side-distribution-list-wrap" id="js-cart-side-distribution-wrap">
                    <li>000</li>
                </ul>
            </div>
        </div> -->
    </div>
</div>

<#include "/cart/common/cart_note_pop.ftl"/>