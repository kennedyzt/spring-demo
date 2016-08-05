
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include flush="true" page="../layout/resources.jsp"></jsp:include>
</head>
<body>
    <div id="toolbar">
        <button type="button" id="add" class="btn btn-primary">添加</button>
    </div>
    <table id="table"></table>
</body>
<script type="text/javascript" src="<%=path%>/resources/js/framework/user/list.js/"></script>
</html>