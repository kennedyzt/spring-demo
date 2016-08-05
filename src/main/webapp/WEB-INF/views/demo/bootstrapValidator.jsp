<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../layout/resources.jsp"%>
</head>
<body>
    <div class="container">
        <form id="validatorForm" class="form-horizontal" role="form">
            <div class="form-group">
                <div class="col-sm-4 col-form-group">
                    <label for="firstname" class="col-sm-4 control-label">用户名</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="firstname" name="userName" placeholder="请输入用户名">
                    </div>
                </div>
                <div class="col-sm-4 col-form-group">
                    <label for="firstname" class="col-sm-4 control-label">密码</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="pwd" name="pwd" placeholder="请输入密码">
                    </div>
                </div>
                <div class="col-sm-4 col-form-group">
                    <label for="firstname" class="col-sm-4 control-label">重复密码</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="_pwd" id="firstname" placeholder="重复密码">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-4 col-form-group">
                    <label for="" class="col-sm-4 control-label">选择语言</label>
                    <div class="checkbox control-label">
                        <label> <input type="checkbox" name="languages" value="net" /> .Net
                        </label> <label> <input type="checkbox" name="languages" value="android" /> Android
                        </label> <label> <input type="checkbox" name="languages" value="php" /> PHP
                        </label> <label> <input type="checkbox" name="languages" value="java" /> Java
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group col-form-group">
                <div class="col-sm-4">
                    <label for="" class="col-sm-4 control-label">描述</label>
                    <div class="col-sm-8">
                        <textarea style="width: 100%" name="remark" class="form-control" rows="3"></textarea>
                    </div>
                </div>
            </div>
            <div class="form-group text-center">
                <button type="button" onclick="validator();" value="submit" class="btn btn-default">提交</button>
            </div>
    </div>
    </form>
    </div>
</body>
<script>
    $('#validatorForm').bootstrapValidator({
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove'
        },
        submitButtons : 'button[value="submit"]',
        message : 'This value is not valid',
        group : '.col-form-group',
        fields : {
            userName : {
                validators : {
                    notEmpty : {
                        message : '用户名不能为空'
                    }
                }
            },
            pwd : {
                validators : {
                    notEmpty : {
                        message : '密码不能为空'
                    },
                    stringLength : {
                        message : '密码不能少于6位不超过12位',
                        min : 6,
                        max : 12
                    }
                }
            },
            _pwd : {
                validators : {
                    callback : {
                        message : '两次输入密码不一致',
                        callback : function(value, validator) {
                            var pwd = $('#pwd').val();
                            return pwd == value;
                        }
                    }
                }
            },
            languages : {
                validators : {
                    choice : {
                        min : 1,
                        message : '最少选择一门语言'
                    }
                }
            },
            remark : {
                validators : {
                    notEmpty : {
                        message : '描述不能为空'
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        add();
    });

    function validator() {
        $('#validatorForm').data('bootstrapValidator').validate();
    }

    function add() {
        alert('验证成功');
    }
</script>
</html>
