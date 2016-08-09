<!DOCTYPE html>
<%
    String path = request.getContextPath();
%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>kennedy</title>
<meta charset="UTF-8">
<jsp:include flush="true" page="resources.jsp"></jsp:include>
</head>
<body id="body">
    <div class="header" id="header">
        <span>歡迎：<sec:authentication property="principal.username" /></span>
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
    <sec:authorize access="hasRole('ROLE_SYSTEM')">
        <div class="footer" id="footer">
            <jsp:include flush="true" page="footer.jsp"></jsp:include>
        </div>
    </sec:authorize>
    <script src="<%=path%>/resources/js/framework/layout/layout.js"></script>
</body>
</html>
