/**
 * Created by caoxiao on 2017/5/21.
 */
app.controller("loginController", ['$scope', '$location', "$http", "$window", "AuthService", "AUTH_EVENTS", "$rootScope",
    function($scope, $location, $http, $window, AuthService, AUTH_EVENTS, $rootScope, Session) {

    $scope.credentials = {
        username : '',
        password : ''
    };
    $scope.login = function() {
        // console.log('login', $scope.credentials);
        AuthService.login($scope.credentials);
    };

    $scope.errorInfo = "";
    
    // $scope.login = function () {
    //     if ("" != $scope.username && "" != $scope.password) {
    //         var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/login";
    //
    //         var params = {
    //             username : $scope.username,
    //             password : $scope.password
    //         };
    //         $http.post(url,params).then(
    //             function successCallback(response) {
    //                 if (response.result == "success") {
    //                     $window.location.href = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/view/views/index.html";
    //                 } else {
    //                     $scope.errorInfo = response.errorMsg;
    //                 }
    //             },
    //             function errorCallback(response) {
    //                 $scope.errorInfo = response.errorMsg;
    //             }
    //         );
    //     }
    // };
    
    $scope.register = function () {
        $window.location.href = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/view/views/userMgr/register.html";
    }
}]);

app.controller("registerController", ['$scope', '$location', "$http", "$window", "$rootScope", function($scope, $location, $http, $window, $rootScope, Session) {
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
        // $window.location.href = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/view/views/userMgr/login.html";
        if ($scope.checkParams()) {
            var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/register";

            var data = {
                params : {
                    username: $scope.username,
                    password: $scope.password,
                    email: $scope.email,
                    mobile: $scope.mobile,
                    nickname: $scope.nickname
                }
            };
            $http.post(url,data).then(
                function successCallback(response) {
                    if (response.success) {
                        $window.location.href = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/view/views/userMgr/login.html";
                    } else {
                        $scope.errorInfo = response.errorMsg;
                    }
                },
                function errorCallback(response) {
                    $scope.errorInfo = response.errorMsg;
                }
            );
        }
    }
}]);