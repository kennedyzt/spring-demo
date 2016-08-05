
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include flush="true" page="../layout/resources.jsp"></jsp:include>
</head>
<body>
    <div class="container">
        <form method="post" action="<%=path%>/websocket/send">
            <div class="form-group">
                <label for="username">发件人</label> <input type="text" class="form-control" name="username" id="username" placeholder="用户名">
            </div>
            <div class="message">
                <label for="message">消息</label> <input type="text" class="form-control" name="message" id="message" placeholder="消息">
            </div>
            <button type="submit" class="btn btn-default">发送</button>
        </form>
    </div>
</body>
</html>
