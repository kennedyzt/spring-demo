$('#table').framework().bootstrapTable({
    url : '/user/getListByPage',
    height : parent.$(".layui-layer").height() - 100,
    columns : [ {
        title : '操作',
        formatter : operationFormatter
    }, {
        field : 'username',
        title : '用户名'
    }, {
        field : 'nickname',
        title : '昵称'
    }, {
        field : 'telephone',
        title : '电话'
    }, {
        field : 'email',
        title : '邮箱'
    }, {
        field : 'address',
        title : '地址'
    } ]
});

function operationFormatter(value, row, index) {
    return '<a><img src="/resources/imgs/edit.png"/></a>';
}
