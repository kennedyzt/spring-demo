<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../layout/resources.jsp"%>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=C8qK88SCRyOZV9K5QbrOGdxcA1vAsrHD"></script>
</head>
<body>
    <div class="container">
        <div id="allmap" style="width: 100%; height: 500px;"></div>
    </div>
</body>
</html>
<script type="text/javascript">
    //百度地图API功能
    var map = new BMap.Map("allmap"); // 创建Map实例
    map.centerAndZoom(new BMap.Point(104.072653,30.664292), 11); // 初始化地图,设置中心点坐标和地图级别
    map.addControl(new BMap.MapTypeControl()); //添加地图类型控件
    map.setCurrentCity("北京"); // 设置地图显示的城市 此项是必须设置的
    map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
</script>