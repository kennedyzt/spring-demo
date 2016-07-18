<!DOCTYPE html>
<html>
<head>
<title>kennedy</title>
<meta charset="UTF-8">
<jsp:include flush="true" page="resources.jsp"></jsp:include>
</head>
<body id="body">
    <div class="header" id="header">
        <jsp:include flush="true" page="header.jsp"></jsp:include>
    </div>
    <div class="main" id="main">
        <div class="left" id="left">
            <jsp:include flush="true" page="menu.jsp"></jsp:include>
        </div>
        <div class="right" id="right">
            <ul class="nav nav-tabs" style="height: 40px; width: 100%">
                <li data-id="" class="active"><a href="#home" data-toggle="tab">系统主页</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade in active" id="home" style="width: 100%; height: 100%">
                    <h1>主页面</h1>
                </div>
            </div>
        </div>
    </div>
    <div class="footer" id="footer">
        <jsp:include flush="true" page="footer.jsp"></jsp:include>
    </div>
    <script src="/resources/js/framework/layout/layout.js"></script>
</body>
</html>
