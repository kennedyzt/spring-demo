<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
<link rel="stylesheet" href="resources/css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="resources/css/bootstrap/fileinput.css" />
<script src="resources/js/jquery/jquery-2.1.4.js"></script>
<script src="resources/js/bootstrap/bootstrap.js"></script>
<script src="resources/js/bootstrap/fileinput.js"></script>
<script src="resources/js/framework.js"></script>
</head>
<body>
    <div class="container">
        <form class="form-horizontal" enctype="multipart/form-data" method="POST">
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputEmail3" name="username" placeholder="用户名">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="inputPassword3" name="password" placeholder="密码">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">肖像</label>
                <div class="col-sm-10">
                    <input id="img" name="img" type="file" class="file-loading">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">提交</button>
                </div>
            </div>
        </form>
    </div>
</body>
<script type="text/javascript">
    $("#img").framework().fileInput();
</script>
</html>
