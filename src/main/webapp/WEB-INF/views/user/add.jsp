<!DOCTYPE html>
<%
    String path = request.getContextPath();
%>
<html>
<head>
<meta charset="UTF-8">
<title>添加</title>
<link href="<%=path%>/resources/css/bootstrap/bootstrap.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <form class="form-horizontal" method="POST">
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="usernaem" name="username" placeholder="用户名">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="inputPassword3" name="password" placeholder="密码">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">添加</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
