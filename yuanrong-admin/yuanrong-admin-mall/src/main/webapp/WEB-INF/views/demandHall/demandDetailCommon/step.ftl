<style>
    .container-left-title{
        height: 70px;
        line-height: 70px;
        padding: 0 30px;
        position: relative;
    }
    .container-left-step > ul{
        overflow: auto;
        display: flex;
        justify-content: center;
        background: #F5F7FA;
    }
    .container-left-title > div{
        position: absolute;
        top: 0;
        width: 60px;
        height: 22px;
        line-height: 22px;
        text-align: center;
        font-size: 12px;
        color: #fff;
        background: #89BC62;
        border-radius: 0 0 2px 2px;
    }
    .container-left-title > span:nth-child(2){
        display: inline-block;
        font-size: 12px;
        width: 56px;
        height: 20px;
        line-height: 18px;
        text-align: center;
        border: 1px solid rgba(137,188,98,0.50);
        border-radius: 2px;
        color: #89BC62;
        margin-right: 10px;
    }
    .container-left-title > span:nth-child(3){
        font-size: 20px;
        /*color: #64676A;*/
        color: #48494a;
    }
    .container-left-step{
        height: 70px;
        background: #F7F8FC;
    }
    .container-left-step li{
        float: left;
        padding-top: 21px;
        margin-right: -24px;
        height: 70px;
        width: 76px;
        text-align: center;
        position: relative;
        overflow: hidden;
        /*line-height: 70px;*/
    }
    .container-left-step li > div > span{
        display: inline-block;
        width: 20px;
        height: 20px;
        line-height: 20px;
        text-align: center;
        border-radius: 50%;
        background: #DBDDE0;
        margin-bottom: 4px;
        color: #a9adb0;
        position: relative;
    }
    .container-left-step li > span{
        display: inline-block;
        height: 17px;
        line-height: 17px;
        font-size: 12px;
        color: #82868a;
    }
    .container-left-step li:nth-child(2n){
        /*width: 165px;*/
        /*height: 31px;*/
        /*border-bottom: 1px solid #E9EBEC;*/
        margin-top: 31px;
        width: 0;
        height: 1px;
        border-left: 95px solid #E9EBEC;
        border-right: 70px solid #E9EBEC;
        border-top: none;
        padding-top: 0;
    }
    .step-active-1{
        background: #E8F2FC;
        z-index: 1;
    }
    .step-active-2{
        background: #4895E7 !important;
        color: #fff !important;
    }
    .step-active-3{
        color: #4895E7 !important;
    }
    .step-active-4{
        border-left: 95px solid #97C1EF !important;
    }
    .step-active-5{
        border-left: 95px solid #97C1EF !important;
        border-right: 70px solid #97C1EF !important;
    }
    .step-icon:after{
        box-sizing: content-box;
        content: "";
        border: 1px solid #fff;
        border-left: 0;
        border-top: 0;
        height: 8px;
        left: 8px;
        position: absolute;
        top: 5px;
        transform: rotate(45deg);
        width: 3px;
        transition: transform .15s ease-in .05s;
        transform-origin: center;
    }
</style>
<div class="container-left-title">
    <div></div>
    <span>原创征稿</span>
    <span></span>
</div>
<div class="container-left-step">
    <ul>
        <li class="step-active-1">
            <div style="height:24px;"><span class="step-active-2">1</span></div>
            <span class="step-active-3" >招募作品</span>
        </li>
        <li class="step-active-4"></li>
        <li style="width:90px;">
            <div style="height:24px;"><span>2</span></div>
            <span>卖家提交作品</span>
        </li>
        <li></li>
        <li>
            <div><span>3</span></div>
            <span>买家购买作品</span>
        </li>
        <li></li>
        <li>
            <div><span>4</span></div>
            <span>平台付款</span>
        </li>
    </ul>
</div>