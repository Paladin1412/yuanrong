<html>
<head>
    <meta charset="UTF-8">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
    <!-- <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/> -->
    <meta http-equiv="X-UA-Compatible" content="ie=edge,chrome=1" />
    <meta name="renderer" content="webkit">
    <meta name="robots" content="all" />

    <meta name="googlebot" content="index,follow" />
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="Content-Language" content="zh-CN" />
</head>
<body>
<h2>Hello World! yuanrong-admin-web</h2>
<h3 id="content"></h3>
<script src="/js/jquery.min.js"></script>
<script>
    $(function () {
        request('/order/orderInfoSeller_list');
        request('/userManagement/loginInfo');
        request('/order/orderInfoSeller/orderStatusInfo');
        request('/order/orderInfoSeller_list');
    });

    function request(url) {
        $.ajax({
            url : url ,
            resultType : 'json',
            type : 'post',
            success : function (data) {
                console.log(data);
                // $("#content").html(data);
            }
        })
    }

    function logout() {
        $.ajax({
            url : '/registeredUserInfo/logout',
            resultType : 'json',
            success : function (data) {
                $("#content").html(data);
            }
        })
    }
</script>
</body>
</html>
