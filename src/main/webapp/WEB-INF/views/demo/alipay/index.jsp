
<%
    String path = request.getContextPath();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include flush="true" page="../../layout/resources.jsp"></jsp:include>
<script src="<%=path%>/resources/js/jquery/jquery.qrcode.min.js"></script>
</head>
<body text=#000000 bgColor="#ffffff" leftMargin=0 topMargin=4>
    <header class="am-header"> </header>
    <div id="main">
        <button onclick="pay();">ss</button>
        <div id="code"></div>
    </div>
</body>
<script type="text/javascript">
    function pay() {
        $('#code')
                .qrcode(
                        "weixin://wxpay/bizpayurl?appid=wxf80f6cff0b60cc82&mch_id=1423332802&nonce_str=f6808210402125e30663234f94c87a8c&product_id=1&time_stamp=1415949957&sign=512F68131DD251DA4A45DA79CC7EFE9D");
    }
</script>
</html>