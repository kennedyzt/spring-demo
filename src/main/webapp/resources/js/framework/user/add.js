$(function() {
    initEvent();
});
function initEvent() {
    $("#submitBtn").on("click", function() {
        add();
    });
}
function add() {
    $.ajax({
        type : "post",
        url : "/user/add",
        data : $("#userForm").serialize(),
        success : function() {
            alert("添加成功");
        }
    });
}