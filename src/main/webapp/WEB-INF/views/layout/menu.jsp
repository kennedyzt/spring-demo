
<%
    String path = request.getContextPath();
%>
<div id="tree"></div>
<script>
    $(function() {
        $.ajax({
            type : "GET",
            url : createUrl("/menu/getlist"),
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
</script>
