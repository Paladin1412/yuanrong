<%--
  Created by IntelliJ IDEA.
  User: zhonghang
  Date: 2018/7/12
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支付</title>
</head>
<body>

<img src="/pay/wx/createScanPayQrcodeMode?sideLength=200">
</body>
<script src="/js/jquery.min.js"></script>
<script type="application/javascript">
    var t;
    $(function(){
        t = window.setInterval("payStatus()",3000);
    });
    function payStatus() {
        $.ajax({
           url : '/pay/queryOrder',
            type :"post",
            data :{"outTradeNo":"171102417707720713"},
            success : function(data){
                var result = JSON.parse(data);
                console.log(result);
               if(result.data.tradeState == 'SUCCESS'){
                   clearInterval(t);
                   alert("支付成功");
               }
            }
        });
    }
</script>
</html>
