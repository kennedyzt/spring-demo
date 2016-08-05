<html ng-app="myNoteApp">
<head>
<meta charset="utf-8">
<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>
</head>
<body>
    <div ng-controller="myNoteCtrl">
        <h2>我的笔记</h2>
        <p>
            <textarea ng-model="message" cols="40" rows="10"></textarea>
        </p>
        <p>
            <button ng-click="save()">保存</button>
            <button ng-click="clear()">清除</button>
        </p>
        <p>
            Number of characters left: <span ng-bind="left()"></span>
        </p>
    </div>
</body>
<script type="text/javascript">
    var app = angular.module("myNoteApp", []);
    app.controller("myNoteCtrl", function($scope) {
        $scope.message = "";
        $scope.left = function() {
            return 100 - $scope.message.length;
        };
        $scope.clear = function() {
            $scope.message = "";
        };
        $scope.save = function() {
            alert("Note Saved");
        };
    });
</script>
</html>