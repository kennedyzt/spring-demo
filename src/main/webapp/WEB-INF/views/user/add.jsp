<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加</title>
<link href="//cdn.bootcss.com/bootstrap/4.0.0-alpha.3/css/bootstrap.css"
	rel="stylesheet">
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js "></script>
<script src="//cdn.bootcss.com/bootstrap/4.0.0-alpha.3/js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" method="POST">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="usernaem"
						name="username" placeholder="用户名">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="inputPassword3"
						name="password" placeholder="密码">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">登录</button>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" id="sendWebSocketBtn1"
						onclick="sendWebSocketMsg();" class="btn btn-default">发送WebSocket消息</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		initEvent();
	});
	function initEvent() {
		$("#sendWebSocketBtn").on("click", sendWebSocketMsg());
	}
	function sendWebSocketMsg() {
		var url = "ws://" + "localhost:8080/springdemo/marco";
		sock = new WebSocket(url); //打开websocket
		sock.onmessage = function(e) { // 处理信息
			alert("Received message:" + e.data);
		}
		sock.send("Hello:" + $("#usernaem").val());
	}
</script>
</html>
