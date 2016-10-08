<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="../layout/resources.jsp" %>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-2"></div>
        <div class="col-xs-8">
            <form id="userForm" class="form-horizontal" enctype="multipart/form-data">
                <div></div>
                <div class="form-group text-center ">
                    <label for="username" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="username" id="username" placeholder="用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" name="password" id="password" placeholder="密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">上传文件</label>
                    <div class="col-sm-10">
                        <input id="doc" name="doc" type="file" multiple=true class="file-loading">
                    </div>
                </div>
                <div class="form-group text-center">
                    <input type="button" class="btn" id="submit" value="提交">
                </div>
            </form>
        </div>
        <div class="col-xs-2"></div>
    </div>

</div>
</body>
<script>
    $(function () {
        init();
    });
    function init() {
        $("#doc").framework().fileInput();
        bindEvent();
    }
    function bindEvent() {
        $("#submit").on("click", function () {
            $("#userForm").ajaxSubmit({
                type: 'post', // 提交方式 get/post
                url: createUrl('/demo/fileinput/adduser'), // 需要提交的 url
                data: {
                    id: 1
                },
                success: function (data) { // data 保存提交后返回的数据，一般为 json 数据
                    // 此处可对 data 作相关处理
                    alert('提交成功！');1
                }
            });
        });
        $('#doc').on('fileloaded', function (event, file, previewId, index, reader) {
            var files = $('#doc').fileinput('getFileStack');
            for (var i in files) {
                if (files[i].name == file.name) {
                    return;
                }
            }
        });
    }
</script>
</html>
