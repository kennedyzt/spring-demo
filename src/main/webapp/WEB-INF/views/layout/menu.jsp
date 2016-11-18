
<%
    String path = request.getContextPath();
%>
<div class="contextMenu" id="myMenu1">
    <ul>
        <li id="closeall">关闭所有</li>
        <li id="refresh">刷新</li>
        <li id="close">关闭</li>
    </ul>
</div>
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
                    emptyIcon : "icon-circle",
                    enableLinks : false,
                    backColor : "#2f4050",
                    color : "#a7b1c2",
                    showBorder : false,
                    selectedBackColor : "#00A0E6",
                    expandIcon : 'glyphicon glyphicon-chevron-right',
                    collapseIcon : 'glyphicon glyphicon-chevron-down',
                    onNodeSelected : function(event, data) {
                        framework.tabs(data);
                    }
                });
            }
        });
    });
</script>
