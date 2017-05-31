/**
 * Created by caoxiao on 2017/5/23.
 */
app.controller("userController", ['$scope', '$location', "$http", "$uibModal", "$route", "$rootScope", function($scope, $location, $http, $uibModal, $route, $rootScope, Session) {
    $scope.searchList = ["按用户名进行搜索","按角色进行搜索"];
    $scope.selectedSearch = "";
    $scope.searchValue = "";
    $scope.username = "";

    $scope.rolename = "";

    $scope.userList = {};
    var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/getUserList?" +
        "userName="+ $scope.username + "&roleName" + $scope.rolename;
    var data = {
        params : {
            userName : $scope.username,
            roleName : $scope.rolename
        }
    };
    $http.get(url).then(
        function successCallback(response) {
            $scope.userList = response.data.data;
        },
        function errorCallback(response) {

        }
    );

    $scope.search = function () {
        if ($scope.selectedSearch == $scope.searchList[0]) {
            $scope.username = $scope.searchValue;
        } else if ($scope.selectedSearch == $scope.searchList[1]) {
            $scope.rolename = $scope.searchValue;
        }
        var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/getUserList?" +
            "userName="+ $scope.username + "&roleName=" + $scope.rolename;
        var data = {
            params : {
                userName : $scope.username,
                roleName : $scope.rolename
            }
        };
        $http.get(url).then(
            function successCallback(response) {
                $scope.userList = response.data.data;
            },
            function errorCallback(response) {

            }
        );
    };

    $scope.createUser = function () {
        var modalInstance = $uibModal.open({
            templateUrl : 'userCreate.html',
            controller : 'createUserControl',
            animation : true,
            size: 'md',
            resolve: {

            }
        });

        modalInstance.result.then(function (selectedItem) {
            $route.reload();
        }, function () {
            $route.reload();
        });
    };

    $scope.deleteUser = function (index) {

        var modalInstance = $uibModal.open({
            templateUrl : 'deleteUser.html',
            controller : 'deleteUserControl',
            animation : true,
            size: 'md',
            resolve: {
                user : function () {
                    return $scope.userList[index];
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $route.reload();
        }, function () {
            $route.reload();
        });
    };

    $scope.modifyUser = function () {
        var modalInstance = $uibModal.open({
            templateUrl : 'userCreate.html',
            controller : 'createUserControl',
            animation : true,
            size: 'md',
            resolve: {

            }
        });

        modalInstance.result.then(function (selectedItem) {
            $scope.reload();
        }, function () {

        });
    };

    $scope.reload = function () {
        $route.reload();
    };
    $scope.userList = [
        {
            "user_id" : "1",
            "user_name" : "pino",
            "create_time" : "2017-5-20",
            "display_name" : "pino",
            "email" : "pino@hotmail.com",
            "mobile" : "15210948806",
            "role_id" : "1",
            "role_name" : "管理员"
        },
        {
            "user_id" : "2",
            "user_name" : "pickup",
            "create_time" : "2017-5-20",
            "display_name" : "pickup",
            "email" : "pino@hotmail.com",
            "mobile" : "15210948806",
            "role_id" : "1",
            "role_name" : "管理员"
        },
        {
            "user_id" : "3",
            "user_name" : "caoxiao",
            "create_time" : "2017-5-20",
            "display_name" : "caoxiao",
            "email" : "pino@hotmail.com",
            "mobile" : "15210948806",
            "role_id" : "1",
            "role_name" : "管理员"
        },
        {
            "user_id" : "4",
            "user_name" : "shall",
            "create_time" : "2017-5-20",
            "display_name" : "shall",
            "email" : "pino@hotmail.com",
            "mobile" : "15210948806",
            "role_id" : "2",
            "role_name" : "员工"
        },
        {
            "user_id" : "5",
            "user_name" : "xxx",
            "create_time" : "2017-5-20",
            "display_name" : "xxx",
            "email" : "pino@hotmail.com",
            "mobile" : "15210948806",
            "role_id" : "2",
            "role_name" : "员工"
        }
    ]
}]);

app.controller("createUserControl", ['$scope', '$location', "$http", "$uibModalInstance", "$rootScope", function($scope, $location, $http, $uibModalInstance, $rootScope, Session) {
    $scope.newUser = {};
    $scope.selectedRole = {};
    // $scope.isCreate = isCreate;

    var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/getRoleList";
    $http.get(url).then(
        function successCallback(response) {
            $scope.roleList = response.data.data;
        },
        function errorCallback(response) {

        }
    );

    $scope.close = function () {
        $uibModalInstance.dismiss('cancel');
    };

    $scope.selectRole = function (role) {
        $scope.roleSelect = role;
    };

    $scope.enable = function () {
        if ($scope.newUser.username != null && $scope.newUser.email != null
            && $scope.newUser.tel != null && $scope.selectedRole != null) {
            if ($scope.newUser.password == $scope.newUser.repeatPass) {
                return true;
            }
            return false;
        }
        return false;
    };

    $scope.create = function () {


        var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/createUser";

        var data = {
            params : {
                userName : $scope.newUser.username,
                displayName : $scope.newUser.username,
                email : $scope.newUser.email,
                mobile : $scope.newUser.tel,
                password : $scope.newUser.password,
                roleId : $scope.selectedRole.roleId
            }
        };

        $http.post(url,data).then(
            function successCallback(response) {
                $scope.close();
            },
            function errorCallback(response) {

            }
        );
    };

}]);

app.controller("deleteUserControl", function($scope, $location, $http, $uibModalInstance, user, $rootScope, Session) {

    $scope.delete = function () {
        var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/deleteUser";

        var data = {
            params : {
                userName: user.user_name
            }
        };

        $http.post(url,data).then(
            function successCallback(response) {
                $scope.close();
            },
            function errorCallback(response) {

            }
        );
    };

    $scope.close = function () {
        $uibModalInstance.dismiss('cancel');
    };

});