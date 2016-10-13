!(function($) {
    $.fn.framework = function(options) {
        var that = this;
        return {
            bootstrapTable : function(options) {
                var DEFAULTS = {
                    height : 550,
                    undefinedText : '-',
                    method : 'get',
                    pagination : true,
                    sidePagination : 'server',
                    pageSize : 10,
                    pageList : [ 10, 25, 50, 100, 'ALL' ],
                    minimumCountColumns : 2,
                    search : true,
                    uniqueId : 'id',
                    toolbar : '#toolbar',
                    responseHandler : function(res) {
                        return {
                            total : res.totalCount,
                            rows : res.records
                        };
                    }
                };
                if (options.url) {
                    options.url = createUrl(options.url);
                }
                $.extend(DEFAULTS, options);
                that.bootstrapTable(DEFAULTS);
            },
            fileInput : function(options) {
                var DEFAULTS = {
                    language : 'zh', // 设置语言
                    maxFileCount : 9, // 表示允许同时上传的最大文件个数
                    allowedFileExtensions : [ 'jpeg', 'jpg', 'png', 'gif', 'bmp', 'tif','zip' ],// 接收的文件后缀
                    showPreview : true, // 是否显示预览
                    uploadUrl : "/",
                    showUpload : false, // 是否显示上传按钮
                    showCaption : true, // 是否显示标题
                    showClose : false, // 关闭窗口
                    showUploadedThumbs : false,
                    dropZoneEnabled : false, // 是否允许拖拽
                    initialPreviewShowDelete : true, // 修改初始化时显示删除按钮
                    overwriteInitial : false, // 修改时不覆盖原有文件
                    maxImageWidth : 1500,
                    maxImageHeight : 1500,
                    maxFileSize : 14024,
                    previewSettings : {
                        image : {
                            width : "176px",
                        },
                        html : {
                            width : "176px"
                        },
                        text : {
                            width : "176px"
                        },
                        video : {
                            width : "176px"
                        },
                        audio : {
                            width : "176px"
                        },
                        flash : {
                            width : "176px"
                        },
                        object : {
                            width : "176px"
                        },
                        other : {
                            width : "176px"
                        }
                    },
                    browseClass : "btn btn-primary", // 按钮样式
                    defaultPreviewContent : '',
                    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>"
                };
                $.extend(DEFAULTS, options);
                that.fileinput(DEFAULTS);
            }
        };
    }
})(jQuery);
function createUrl(url) {
    var pathName = window.document.location.pathname;
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1) + "/";
    return projectName + url;
}
var framework = {
    openWindow : function(url, width, height, title, allowScroll) {
        var index = layer.open({
            type : 2, // layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
            title : title,
            content : [ createUrl(url), allowScroll || 'yes' ], // 不出现滚动条no
            area : [ width, height ],
            shadeClose : false, // 点击阴影是否关闭
            closeBtn : 1,
            shift : 1, // 目前shift可支持的动画类型有0-6
            maxmin : false
        });
        return index;
    },
    tips : function(msg, obj) {
        if (typeof obj == 'object') {
            layer.tips(msg, obj, {
                tips : [ 3, '#34A7FF' ],
                time : 1000
            })
        } else {
            layer.tips(msg, $("#" + obj), {
                tips : [ 1, '#34A7FF' ]
            });
        }
    },
    tabs : tabs
}
/**
 * nodeData{nodeId,href}
 * 
 * @param nodeData
 */
function tabs(nodeData) {
    var DEFAULT = {
        $li : $('<li><a data-toggle="tab"></a><span class="glyphicon glyphicon-remove" style="position: absolute;right:5px;"></span></li>'),
        $tab : $('<div class="tab-pane fade in active" style="width:100%;height:100%"><iframe width="100%" height="100%" style="overflow-x: hidden; display: inline;" allowtransparency="true" frameborder="0" width="100%" height="100%" </iframe></div>')
    }
    // 若没有href 不做任何处理
    if (nodeData.href) {
        // 已存在该节点则不追加节点，高亮显示当前节点
        var flag = false;
        $.each($('.nav.nav-tabs').children(), function() {
            if ($(this).attr('data-id') === String(nodeData.nodeId)) {
                $(this).find("a").tab('show');
                flag = true;
                return;
            }
        });
        if (!flag) {
            init(nodeData);
        }
        function init(nodeData) {
            initTab(nodeData);
            addEvent();
            showTab();
        }
        function initTab(nodeData) {
            DEFAULT.$li.attr('data-id', nodeData.nodeId);
            DEFAULT.$li.find('a').attr('href', '#page_' + nodeData.nodeId);
            DEFAULT.$li.find('a').text(nodeData.text);
            DEFAULT.$tab.attr('id', 'page_' + nodeData.nodeId);
            DEFAULT.$tab.find('iframe').attr('src', createUrl(nodeData.href));
        }
        function addEvent() {
            // 添加右击事件
            DEFAULT.$li.mousedown(function(e) {
                if (e.which === 3) {
                    alert("右击事件待完成");
                }
            }).hover(function() {
                $(this).find('.glyphicon-remove').show();
            }, function() {
                $(this).find('.glyphicon-remove').hide();
            });
            // 绑定删除事件
            DEFAULT.$li.find('.glyphicon-remove').on('click', function() {
                $('.tab-content div[id="' + $(this).parent().find("a").attr("href").substring(1) + '"]').remove();
                $(this).parent().prev().find("a").tab('show');
                $(this).parent().remove();
            }).hide();
        }
        function showTab() {
            $(".nav.nav-tabs").append(DEFAULT.$li);
            $(".tab-content").append(DEFAULT.$tab);
            DEFAULT.$li.find("a").tab('show');
        }
    }
}
