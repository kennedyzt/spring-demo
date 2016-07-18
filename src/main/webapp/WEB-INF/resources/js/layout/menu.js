$(function() {
    $.ajax({
        type : "GET",
        url : "/menu/getlist",
        dataType : "json",
        success : function(data) {
            $('#tree').treeview({
                data : data,
                levels : 1,
                emptyIcon : "glyphicon glyphicon-stop",
                enableLinks : false,
                onNodeSelected : function(event, data) {
                    framework.tabs(data);
                }
            });
        }
    });
});