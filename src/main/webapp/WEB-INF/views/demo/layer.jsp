<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../layout/resources.jsp"%>
</head>
<body>
    <div class="container">
        <button type="button" onclick="openWin();" class="btn btn-default">弹窗</button>
    </div>
</body>
<script>
    function openWin() {
        framework.openWindow('/user/list','85%','85%','用户列表');
    }
</script>
</html>
