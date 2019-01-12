
<style>
    
.self-price-wrap{
	float: left;
}
.self-price-wrap input, 
.self-price-wrap span{
	float: left;
	height: 24px;
	line-height: 24px;
	text-align: center;
}
.self-price-wrap span{
	min-width: auto !important;
}
.self-price-wrap input{
	width: 80px;
	line-height: inherit;
	background: #FFFFFF;
	border: 1px solid #E9EBEC;
	border-radius: 2px;
}
.self-price-wrap span.self-price-line{
	color: #E9EBEC;
	margin: 0 5px;
}
.self-price-wrap span.self-yuan{
	padding: 0 20px 0 4px;
}
.self-price-wrap span.self-price-line{
	padding: 0;
}
#js-self-price-btn{
	padding: 0 10px;
	cursor: pointer;
	border-radius: 2px;
    /*background: #4895E7;*/
	/*color: #fff;*/

    background: #f6faff;
    color: #4895e7;
    border: 1px solid #dfeaf8;
}
</style>

<div class='self-price-wrap fixedClear'>
    <input type="text" id="js-start-price" maxlength="8" onkeyup="this.value=this.value.replace(/[^0-9]/ig,'')" /><span class="self-price-line">—</span><input type="text" id="js-end-price"  maxlength="8" onkeyup="this.value=this.value.replace(/[^0-9]/ig,'')" /><span class="self-yuan" style="color:#727477;">元</span><span id="js-self-price-btn">确定</span>
</div>