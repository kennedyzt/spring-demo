<!DOCTYPE html>
<html>
<head>
<title>个人信息</title>
</head>
<body>
    <h1>个人信息</h1>
    <table>
        <tr>
            <td>昵称：</td>
            <td>${userInfo.nickName }</td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>${userInfo.sex }</td>
        </tr>
        <tr>
            <td>城市：</td>
            <td>${userInfo.city }</td>
        </tr>
        <tr>
            <td>国家：</td>
            <td>${userInfo.country }</td>
        </tr>
        <tr>
            <td>头像地址：</td>
            <td>${userInfo.headImagUrl }</td>
        </tr>
        <tr>
            <td>组：</td>
            <td>${userInfo.groupId }</td>
        </tr>
        <tr>
            <td>特权信息：</td>
            <td>${userInfo.privilege }</td>
        </tr>
        <tr>
            <td>ID：</td>
            <td>${userInfo.unionid }</td>
        </tr>
        <tr>
            <td>公众号唯一ID：</td>
            <td>${userInfo.openId }</td>
        </tr>
    </table>
</body>
</html>
