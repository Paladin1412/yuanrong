<#--  失效弹框  -->
<div class="pop-wrap" id="js-pop-valid-wrap" style="display: none;">
    <div class="pop-opa"></div>
    <div class="pop-con-wrap">
        <div class="pop-tit-wrap">
            <span  class="pop-tit">提示</span>
            <span class="pop-close-btn"><i class="iconfont">&#xe624;</i></span>
        </div>
        <div class="pop-main-wrap">
            <div class="pop-note-tit-wrap">
                <span class="pop-note-icon">!</span>  以下 <span id="js-pop-invalid-num">2</span> 个账号失效, <br/> 是否删除无效账号继续发布需求？
            </div>
            <div class="pop-main ">
                <ul class="pop-main-list-wrap fixedClear" style="max-height: 98px;">
                    <li class="fixedClear">
                        <div class="pop-avatar-wrap">

                        </div>
                        <span class="pop-account-icon"><img src="" alt=""></span>
                        <div class="pop-list-tit-wrap">
                            <p>999</p>
                            <p>9999</p>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="pop-btn-wrap">
            <div class="pop-btn pop-btn-cancle">取消</div>
            <div class="pop-btn pop-btn-blue">继续发布需求</div>
        </div>
    </div>
</div>
<#-- 删除提示弹框  -->
<div class="pop-wrap" id="js-pop-del-wrap" style="display: none;">
    <div class="pop-opa"></div>
    <div class="pop-con-wrap">
        <div class="pop-tit-wrap">
            <span  class="pop-tit">提示</span>
            <span class="pop-close-btn"><i class="iconfont">&#xe624;</i></span>
        </div>
        <div class="pop-main-wrap">
            <div class="pop-main pop-main-no-author-wrap"></div>
        </div>
        <div class="pop-btn-wrap">
            <div class="pop-btn pop-btn-cancle">取消</div>
            <div class="pop-btn pop-btn-blue pop-btn-red" data-id="" data-type="" onclick="delCartItemData($(this).data('type'), $(this).data('id'))">确定删除</div>
        </div>
    </div>
</div>
<#-- 删除多个或者清除提示弹框  -->
<div class="pop-wrap" id="js-pop-clear-wrap" style="display: none;">
    <div class="pop-opa"></div>
    <div class="pop-con-wrap">
        <div class="pop-tit-wrap">
            <span  class="pop-tit">提示</span>
            <span class="pop-close-btn"><i class="iconfont">&#xe624;</i></span>
        </div>
        <div class="pop-main-wrap">
            <div class="pop-main pop-main-no-author-wrap"></div>
        </div>
        <div class="pop-btn-wrap">
            <div class="pop-btn pop-btn-cancle">取消</div>
            <div class="pop-btn pop-btn-blue pop-btn-red" data-id="" data-type="" data-opetype="" onclick="popConfirmClearData($(this).data('type'), $(this).data('id'), $(this).data('opetype'))"></div>
        </div>
    </div>
</div>
<#--  未选中发布需求   -->
<div class="pop-wrap" id="js-pop-note-wrap" style="display: none;">
    <div class="pop-opa"></div>
    <div class="pop-con-wrap">
        <div class="pop-tit-wrap">
            <span  class="pop-tit">提示</span>
            <span class="pop-close-btn"><i class="iconfont">&#xe624;</i></span>
        </div>
        <div class="pop-main-wrap">
            <div class="pop-main pop-main-no-author-wrap"></div>
        </div>
        <div class="pop-btn-wrap">
            <div class="pop-btn pop-btn-cancle">选择创作者</div>
            <div class="pop-btn pop-btn-blue" data-id="" data-type="" onclick="continuePublishNoData($(this).data('type'))">继续发布需求</div>
        </div>
    </div>
</div>

<!-- 提交订单弹框 -->
<div class="pop-wrap" id="js-pop-order-info-wrap" style="display: none;">
    <div class="pop-opa"></div>
    <div class="pop-con-wrap">
        <div class="pop-tit-wrap">
            <span  class="pop-tit">确认信息</span>
            <span class="pop-close-btn"><i class="iconfont">&#xe624;</i></span>
        </div>
        <div class="pop-main-wrap">
            <div class="pop-main">
                <ul class="pop-main-list-wrap pop-confirm-order-info fixedClear">
                    <li class="fixedClear">作品数量：</li>
                    <li class="fixedClear">
                        <span class="pop-art-num" id="js-pop-art-num"></span>个
                    </li>
                    <li class="fixedClear">作品金额：</li>
                    <li class="fixedClear">
                        ￥<span class="pop-art-sum" id="js-pop-art-sum"></span>
                    </li>
                    <li class="fixedClear">服务费：</li>
                    <li class="fixedClear">
                        ￥<span class="pop-art-service-sum" id="js-pop-art-service-sum"></span>
                    </li>
                    <li class="fixedClear">应付金额：</li>
                    <li class="fixedClear">
                        ￥<span class="pop-art-pay" id="js-pop-art-pay"></span>
                    </li>

                </ul>
            </div>
        </div>
        <div class="pop-btn-wrap">
            <div class="pop-btn pop-btn-blue" id="js-pop-order-commit-btn" data-id="" data-type="">提交订单</div>
        </div>
    </div>
</div>
<#-- 选购车数量大于100提示弹框  -->
<div class="pop-wrap" id="js-pop-cartnum-wrap" style="display: none;">
    <div class="pop-opa"></div>
    <div class="pop-con-wrap">
        <div class="pop-tit-wrap">
            <span  class="pop-tit">提示</span>
            <span class="pop-close-btn"><i class="iconfont">&#xe624;</i></span>
        </div>
        <div class="pop-main-wrap">
            <div class="pop-main pop-main-no-author-wrap"></div>
        </div>
        <div class="pop-btn-wrap">
            <#--  <div class="pop-btn pop-btn-cancle">取消</div>  -->
            <div class="pop-btn pop-btn-blue" data-id="" data-type="" onclick="goCartPage($(this).data('type'))">去看看</div>
        </div>
    </div>
</div>