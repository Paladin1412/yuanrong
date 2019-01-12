<html>
<meta charset="UTF-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<!-- <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/> -->
<meta http-equiv="X-UA-Compatible" content="ie=edge,chrome=1" />
<meta name="renderer" content="webkit">
<meta name="robots" content="all" />

<meta name="googlebot" content="index,follow" />
<link rel="shortcut icon" href="/favicon.ico" />
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<meta http-equiv="Content-Language" content="zh-CN" />
<body>
<h2>Hello World! yuanrong-admin   server</h2>
</body>
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<form id= "uploadForm">
    <p >指定文件名： <input type="text" name="filename" value= ""/></p >
    <p >上传文件： <input type="file" name="file"/></p>
    <input type="button" value="上传" onclick="doUpload1()" />
</form>
<script type="application/javascript">
    function doUpload1() {
        var formData = new FormData($( "#uploadForm" )[0]);
        $.ajax({
            url: '/trading/exs_tradingRecord_vilidate' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                alert(returndata);
            },
            error: function (returndata) {
                alert(returndata);
            }
        });
    }

function doUpload() {
    var formData = new FormData($( "#uploadForm" )[0]);
    $.ajax({
        url: '/ip/ip_vilidateIp' ,
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
             alert(returndata);
        },
        error: function (returndata) {
            alert(returndata);
        }
    });
}

    function savePic(){
        var formData = new FormData($( "#uploadForm" )[0]);
        // var ajaxUrl = "/account/batchUpdateIPAccountPrice";
      //  var ajaxUrl = "/account/importIPAccount";
        var ajaxUrl = "/author/exs_vilidateProductPrice";
        //alert(ajaxUrl);
        //$('#uploadPic').serialize() 无法序列化二进制文件，这里采用formData上传
        //需要浏览器支持：Chrome 7+、Firefox 4+、IE 10+、Opera 12+、Safari 5+。
        $.ajax({
            type: "POST",
            //dataType: "text",
            url: ajaxUrl,
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                alert(data);
                console.log(data);
            },
            error: function(data) {
                alert("error:"+data.responseText);

            }
        });
        return false;
    }
    
    $(function () {
        $.ajax({
            type: "POST",
            url: "http://m.zhong.com:7003/shortVideoCategoryInfo/getShortVideooCategory",
            success: function (data) {
                console.log(data);
            },
            error: function(data) {
                console.log("error:"+data.responseText);
            }
        });
        $.ajax({
            type: "POST",
            url: "http://t.zhong.com:7003/shortVideoCategoryInfo/getShortVideooCategory",
            success: function (data) {
                console.log(data);
            },
            error: function(data) {
                console.log("error:"+data.responseText);
            }
        });
    })
    
    
</script>
</html>
