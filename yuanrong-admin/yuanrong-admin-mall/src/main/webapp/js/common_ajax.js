/*
* type              请求的方式  默认为post
* url               发送请求的地址
* param             发送请求的参数
* isShowLoading      是否显示loading动画  默认为false
* loadingType       loading文字设置，默认“加载中...”
* dataType          返回JSON数据  默认为JSON格式数据
* callBack          请求的回调函数
*/
(function(){
    function AjaxRequest(opts){
        this.type = opts.type || "post";
        this.url = opts.url;
        this.param = opts.param || {};
        this.isShowLoading = opts.isShowLoading || false;
        this.dataType = opts.dataType || "json";
        this.loadingType = opts.loadingType || "default";
        this.callBack = opts.callBack;
        this.init();  
    }

    AjaxRequest.prototype = {
        //初始化
        init: function(){
            this.sendRequest();
        },
        //渲染loading
        showLoading: function(){
            if(this.isShowLoading){
                if(this.loadingType == 'default'){
                    var loadingTxt = '加载中...';
                } else if(this.loadingType == 'upload'){
                    var loadingTxt = '正在上传中...';
                } 
                $('#js-loading-txt').text(loadingTxt);
                $('#js-loading').show();
            }
        },
        //隐藏loading
        hideLoading: function(){
            if(this.isShowLoading){
               $('#js-loading-txt').text('加载中...');
               $('#js-loading').hide();
            }
        },
        //发送请求
        sendRequest: function(){
            var self = this;
            $.ajax({
                type: this.type,
                url: this.url,
                cache: true,
                crossDomain: true,
                data: this.param,
                dataType: this.dataType,
                xhrFields: {
                    withCredentials: true
                },
                headers: {
                    'X-Requested-With': 'XMLHttpRequest'
                },
                traditional: true,
                beforeSend: this.showLoading(),
                success: function(res){
                    self.hideLoading();
                    if (res != null && res != "") {
                        if(self.callBack){
                            if (Object.prototype.toString.call(self.callBack) === "[object Function]") {   //Object.prototype.toString.call方法--精确判断对象的类型
                                self.callBack(res);
                                // if(res.status == 401){

                                // }
                            } else {
                                console.log("callBack is not a function");
                            }
                        }

                        if(res.status == 0){
                            layer.msg(res.msg, {time: 1500});
                        }
                    }
                },
                error: function (error) {
                    console.log(error,'--ajax error');
                }
            });
        }
    };

    window.AjaxRequest = AjaxRequest;
})();