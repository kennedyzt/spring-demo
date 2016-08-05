<div id="message" class="glyphicon glyphicon-bell glyphicon "></div>
<script type="text/javascript">
    var url = "ws://" + "localhost:8080/springdemo/marco";
    sock = new WebSocket(url); //打开websocket
    sock.onmessage = function(e) { // 处理信息
        alert(e.data);
    }
</script>
