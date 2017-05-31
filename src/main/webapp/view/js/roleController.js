/**
 * Created by caoxiao on 2017/5/30.
 */
app.controller("roleController", function($scope, $location, $http, $uibModal, $route, $rootScope, Session) {
    console.log('Session', Session);
    $scope.roleList = [];
    $scope.pageList = [];
    $scope.createList = [];
    $scope.isEditing = false;
    $scope.selectedRole = {};

    var getPageList = function () {
        $http.get("/manage/resources/page.json").success(function(data) {
            $scope.pageList = data.pageList;
            angular.forEach($scope.pageList, function(page){
                page.select = false;
            });
            $scope.createList = $scope.pageList;
        });
    };
    getPageList();
    var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/getRoleList";
    $http.get(url).then(
        function successCallback(response) {
            $scope.roleList = response.data.data;
            for (var index=0; index < $scope.roleList.length; index++) {
                $scope.roleList[index].select = false;
            }
            $scope.roleList[0].select = true;
            var purl = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/getPrivilegeInfo?privileges="+ $scope.roleList[0].privileges;
            $http.get(purl).then(
                function successCallback(response) {
                    $scope.currentList = response.data.data;
                    for (var index=0; index < $scope.currentList.length; index++) {
                        setPageSelect($scope.currentList[index]);
                    }
                },
                function errorCallback(response) {

                }
            );
        },
        function errorCallback(response) {

        }
    );

    var setPageSelect = function (page) {
        for (var index=0; index < $scope.pageList.length; index++) {
            if (page.pageNum == $scope.pageList[index].pageNum) {
                $scope.pageList[index].select = true;
            }
        }
    };

    $scope.selectPage = function (page) {
        if ($scope.isEditing) {
            page.select = !page.select;
        }
    };

    $scope.selectRole = function (role) {
        for (var index=0; index < $scope.roleList.length; index++) {
            $scope.roleList[index].select = false;
        }
        role.select = !role.select;
        $scope.selectedRole = role;
        var purl = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/getPrivilegeInfo?privileges="+ role.privileges;
        $http.get(purl).then(
            function successCallback(response) {
                angular.forEach($scope.pageList, function(page){
                    page.select = false;
                });
                $scope.currentList = response.data.data;
                for (var index=0; index < $scope.currentList.length; index++) {
                    setPageSelect($scope.currentList[index]);
                }
            },
            function errorCallback(response) {

            }
        );
    };

    $scope.isActive = function (role) {
        return role.select == true ? 'active' : '';
    };

    $scope.modify = function () {
        $scope.isEditing = true;
    };

    $scope.save = function () {
        $scope.isEditing = false;
        var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/modifyRole";

        var data = {
            params : {
                roleId : $scope.selectedRole.roleId,
                privilege : getSelectPages()
            }
        };

        $http.post(url,'',data).then(
            function successCallback(response) {
                $route.reload();
            },
            function errorCallback(response) {

            }
        );
    };

    $scope.create = function () {
        $scope.isEditing = false;
        var modalInstance = $uibModal.open({
            templateUrl : 'createRole.html',
            controller : 'createRoleControl',
            animation : true,
            size: 'md',
            resolve: {
                pages : function () {
                    return $scope.createList;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $scope.reload();
        }, function () {

        });
    };

    $scope.delete = function () {
        $scope.isEditing = false;
        var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/deleteRole";
        var data = {
            params : {
                roleId : $scope.selectedRole.roleId
            }
        };

        $http.post(url,'',data).then(
            function successCallback(response) {
                $route.reload();
            },
            function errorCallback(response) {

            }
        );
    };

    $scope.cancel = function () {
        $scope.isEditing = false;
        $route.reload();
    };

    var findRoleSelect = function () {
        $scope.roleList.forEach(function (role) {
            if (role.select == true) {
                return role;
            }
        });
    };

    var getSelectPages = function () {
        var target = '';
        var isfirst = true;
        for (var index=0; index < $scope.pageList.length; index++) {
            if ($scope.pageList[index].select == true) {
                if (isfirst) {
                    target += '' + $scope.pageList[index].pageNum;
                    isfirst = false;
                } else {
                    target += ',' + $scope.pageList[index].pageNum;
                }
            }

        }

        return target;
    }
});

app.controller("createRoleControl", function($scope, $location, $http, $uibModalInstance, pages, $rootScope, Session) {
    $scope.pages = pages;
    $scope.roleName = '';

    angular.forEach($scope.pages, function(page){
        page.select = false;
    });

    $scope.selectPages = function (page) {
        page.select = !page.select;
    };
    
    $scope.createRole = function () {
        var pageStr = getSelectPages();
        var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/createRole";

        var data = {
            params : {
                roleName : $scope.roleName,
                privilege : pageStr
            }
        };

        $http.post(url,data).then(
            function successCallback(response) {
                alert("创建成功");
                $scope.close();
            },
            function errorCallback(response) {

            }
        );

    };

    $scope.close = function () {
        $uibModalInstance.dismiss('cancel');
    };

    var getSelectPages = function () {
        var target = '';
        var isfirst = true;
        for (var index=0; index < $scope.pages.length; index++) {
            if ($scope.pages[index].select == true) {
                if (isfirst) {
                    target += '' + $scope.pages[index].pageNum;
                    isfirst = false;
                } else {
                    target += ',' + $scope.pages[index].pageNum;
                }
            }

        }

        return target;
    }
});