
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
    <div class="container-fluid">
        <form id="goods_form" class="form-horizontal" role="form">
            <div class="form-group">
                <label for="goodsName" class="col-sm-2 control-label">商品名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="goodsName" value="忍者皮肤" readonly="readonly">
                </div>
            </div>
            <div class="form-group">
                <label for="remark" class="col-sm-2 control-label">商品描述</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="remark" value="英雄联盟瞎子皮肤" readonly="readonly">
                </div>
            </div>
            <div class="form-group">
                <label for="remark" class="col-sm-2 control-label">价格</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="price">
                </div>
            </div>
            <div id="code" class="form-group"></div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" onclick="pay()" class="btn btn-default">购买</button>
                </div>
            </div>
        </form>
    </div>
</body>
<script type="text/javascript">
    function pay() {
        $.ajax({
            url : createUrl("/pay/pay"),
            type : "post",
            data : $("#goods_form").serialize(),
            success : function(result) {
                $('#code').qrcode(result);
            }
        });
    }
</script>
</html>