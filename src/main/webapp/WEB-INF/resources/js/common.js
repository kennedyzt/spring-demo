function eventBinding(dom) {
    $(dom).each(function() {
        if ($(this).attr("src").indexOf("edit") != -1) {
            $(this).mouseover(function() {
                framework.tips('编辑', this);
            });
            return;
        }
        if ($(this).attr("src").indexOf("delete") != -1) {
            $(this).mouseover(function() {
                framework.tips('删除', $(this).parent());
            });
            return;
        }
        if ($(this).attr("src").indexOf("detail") != -1) {
            $(this).mouseover(function() {
                framework.tips('详情', $(this).parent());
            });
            return;
        }
        if ($(this).attr("src").indexOf("download") != -1) {
            $(this).mouseover(function() {
                framework.tips('下载', $(this).parent());
            });
            return;
        }
        if ($(this).attr("src").indexOf("next") != -1) {
            $(this).mouseover(function() {
                framework.tips('添加子节点', $(this).parent());
            });
            return;
        }
    });
}