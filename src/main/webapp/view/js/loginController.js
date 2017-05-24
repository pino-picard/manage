/**
 * Created by caoxiao on 2017/5/21.
 */
app.controller("loginController", ['$scope', '$location', "$http", "$window", function($scope, $location, $http, $window) {
    
    $scope.username = "";
    
    $scope.password = "";

    $scope.errorInfo = "";
    
    $scope.login = function () {
        $window.location.href = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/view/views/index.html";
        // if ("" != $scope.username && "" != $scope.password) {
        //     var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/login";
        //
        //     var params = {
        //         username : $scope.username,
        //         password : $scope.password
        //     };
        //     $http.post(url,params).then(
        //         function successCallback(response) {
        //             if (response.result == "success") {
        //                 $location.path("/index");
        //             } else {
        //                 $scope.errorInfo = response.errorMsg;
        //             }
        //         },
        //         function errorCallback(response) {
        //             $scope.errorInfo = response.errorMsg;
        //         }
        //     );
        // }
    };
    
    $scope.register = function () {
        $window.location.href = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/view/views/userMgr/register.html";
    }
}]);

app.controller("registerController", ['$scope', '$location', "$http", "$window", function($scope, $location, $http, $window) {
    $scope.username = "";
    $scope.password = "";
    $scope.repeatPassword = "";
    $scope.email = "";
    $scope.mobile = "";
    $scope.nickname = "";

    $scope.checkParams = function () {
        if ($scope.username != "" && $scope.password != "" && $scope.repeatPassword != "" && $scope.email != "" && $scope.mobile != "") {
            if ($scope.password == $scope.repeatPassword) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    };

    $scope.addUser = function () {
        $window.location.href = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/view/views/userMgr/login.html";
        // if ($scope.checkParams()) {
        //     var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/register";
        //
        //     var params = {
        //         username : $scope.username,
        //         password : $scope.password,
        //         email : $scope.email,
        //         mobile : $scope.mobile,
        //         nickname : $scope.nickname
        //     };
        //     $http.post(url,params).then(
        //         function successCallback(response) {
        //             if (response.result == "success") {
        //                 $location.path("/login");
        //             } else {
        //                 $scope.errorInfo = response.errorMsg;
        //             }
        //         },
        //         function errorCallback(response) {
        //             $scope.errorInfo = response.errorMsg;
        //         }
        //     );
        // }
    }
}]);