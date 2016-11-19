<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付宝手机网站支付接口</title>
</head>
<body>
    <form id="alipaysubmit" name="alipaysubmit" action="https://openapi.alipay.com/gateway.do" method="get">
        <input type="hidden" name="sign" value="8ed605bf6bfa38cbaf63a1fa09282064" /> <input type="hidden" name="_input_charset" value="utf-8" /><input type="hidden" name="subject" value="测试" /><input
            type="hidden" name="total_fee" value="0.01" /> <input type="hidden" name="sign_type" value="MD5" /> <input type="hidden" name="service" value="alipay.wap.create.direct.pay.by.user" /> <input
            type="hidden" name="notify_url" value="http://127.0.0.1:8080/alipay.wap.create.direct.pay.by.user-JAVA-UTF-8/notify_url.jsp" /><input type="hidden" name="partner" value="2088102168708784" /><input
            type="hidden" name="seller_id" value="2088102168708784" /><input type="hidden" name="out_trade_no" value="201611191252483" /><input type="hidden" name="payment_type" value="1" /><input
            type="hidden" name="return_url" value="http://127.0.0.1:8080/alipay.wap.create.direct.pay.by.user-JAVA-UTF-8/return_url.jsp" /><input type="submit" value="确认" style="display: none;">
    </form>
</body>
<script>
    document.forms['alipaysubmit'].submit();
</script>
</html>
