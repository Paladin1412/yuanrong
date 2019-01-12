
<style>
#content {
    width: 1170px;
    margin: 0 auto;
    height: 100%;
    background: #fff;
    border-radius: 2px;
    overflow: hidden;
}
    #content .nav-pay {
        height: 50px;
        line-height: 50px;
        padding: 0 20px;
        border-bottom: 1px solid #ddeaf9;
    }
    #content .nav-pay .pay-title {
        font-size: 18px;
        color: #64676a;
    }
    #content .nav-pay .pay-step {
        float: right;
    }
    #content .nav-pay .pay-step > span {
        font-size: 12px;
        display: inline-block;
        width: 140px;
        height: 30px;
        line-height: 30px;
        text-align: center;
    }
    #pay-roll1:before {
        content:"";
        width:0;
        height:0;
        position:absolute;
        left:0;
        top:0;
        border-top:solid 15px transparent;
        border-left:solid 10px #fff;
        border-bottom:solid 15px transparent;
    }
    
    #content .pay-header .pay-step .pay-roll1-before {
        width: 0;
        height: 0;
        border-top: 15px solid transparent;
        border-bottom: 15px solid transparent;
        border-left:10px solid #eaf4e5;
        display: inline-block;
        vertical-align: middle;
        margin-left: -5px;
    }
    #content .pay-header .pay-step .pay-roll2-before {
        width: 0;
        height: 0;
        border-top: 15px solid transparent;
        border-bottom: 15px solid transparent;
        border-left: 10px solid #7cbc5a;
        display: inline-block;
        vertical-align: middle;
        margin-left: -4px;
    }
</style>
<!--导航-->
<div class="nav-pay">
    <div class="pay-header">
        <span class="pay-title" id="js-pay-title">订单支付</span>
        <div class="pay-step fixedClear">
            <span id="pay-roll0" class="pay-step-item1">提交订单信息</span>
            <span class="pay-roll1-before"></span>
            <span id="pay-roll1">支付订单费用</span>
            <span class="pay-roll2-before"></span>
            <span id="pay-roll2">交易成功</span>
        </div>
    </div>
</div>