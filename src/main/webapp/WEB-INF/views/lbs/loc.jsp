<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>混合定位接口 基于临近基站和WIFI热点的高精度混合基站定位</title>
<jsp:include flush="true" page="../layout/resources.jsp"></jsp:include>
</head>
<body>
    <div class="container-fluid">
        <h1>基站查询接口 查询全国移动联通电信2G/3G/4G基站位置数据</h1>
        <form id="locForm">
            <div class="form-group">
                <label for="exampleInputEmail1">国家代码</label> <select class="form-control" name="mcc"><option value="460">中国
                    <option></select>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">网络类型</label> <select class="form-control" name="mnc"><option value="0">移动</option>
                    <option value="1">联通</option></select>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">lac(电信对应nid)</label> <input type="text" placeholder="4301" class="form-control" name="lac">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">cellid(电信对应bid)</label> <input type="text" placeholder="20986" class="form-control" name="ci">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">坐标类型</label> <select class="form-control" name="coord"><option value="wgs84">wgs84</option>
                    <option value="gcj02">gcj02</option>
                    <option value="bd09">bd09</option></select>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">返回格式</label> <select class="form-control" name="output"><option value="json">json</option>
                    <option value="xml">xml</option>
                    <option value="csv">csv</option></select>
            </div>
            <button type="button" onclick="search();" class="btn btn-default">查询</button>
        </form>
    </div>
    <label>查询结果</label>
    <textarea id="resultMsg" rows="5" cols="55"></textarea>
</body>
<script type="text/javascript">
    function search() {
        $.ajax({
            url : createUrl('/lbs/loc'),
            type : 'get',
            data : $("#locForm").serialize(),
            success : function(result) {
                $("#resultMsg").val(result);
            }
        });
    }
</script>
</html>