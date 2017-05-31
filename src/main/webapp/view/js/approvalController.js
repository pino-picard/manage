/**
 * Created by caoxiao on 2017/5/31.
 */
app.controller("approvalController", ['$scope', '$location', "$http", "$uibModal", "$route", "$rootScope", function($scope, $location, $http, $uibModal, $route, $rootScope, Session) {
    $scope.recruitList = [];
    $scope.searchList = ["按标题进行搜索","按申请人进行搜索"];
    var purl = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/getRecruitList?" +
        "status=2";
    $http.get(purl).then(
        function successCallback(response) {
            if (response.data.success) {
                $scope.recruitList = response.data.data;
            }
        },
        function errorCallback(response) {

        }
    );

    $scope.approval = function (index) {
        var modalInstance = $uibModal.open({
            templateUrl : 'approvalRec.html',
            controller : 'approvalRecControl',
            animation : true,
            size: 'md',
            resolve: {
                recruit : function () {
                    return $scope.recruitList[index];
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $route.reload();
        }, function () {
            $route.reload();
        });
    };

    $scope.deleteRec = function (index) {
        var modalInstance = $uibModal.open({
            templateUrl : 'deleteRec.html',
            controller : 'deleteRecControl',
            animation : true,
            size: 'md',
            resolve: {
                recruit : function () {
                    return $scope.recruitList[index];
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $route.reload();
        }, function () {
            $route.reload();
        });
    };

    $scope.reload = function () {
        $route.reload();
    };

    $scope.search = function () {
        if ($scope.selectedSearch == $scope.searchList[0]) {
            $scope.title = $scope.searchValue;
        } else if ($scope.selectedSearch == $scope.searchList[1]) {
            $scope.applyPerson = $scope.searchValue;
        }
        var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/getRecruitList?" +
            "title="+ $scope.title + "&applyPerson=" + $scope.applyPerson + "&status=2";
        $http.get(url).then(
            function successCallback(response) {
                $scope.recruitList = response.data.data;
            },
            function errorCallback(response) {

            }
        );
    };
}]);

app.controller("approvalRecControl", function($scope, $location, $http, $uibModalInstance, recruit, $rootScope, Session) {
    $scope.rec = recruit;
    $scope.isPass = "false";
    $scope.approvalResult = '';

    
    $scope.approval = function () {
        var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/approvalRecruit";
        var status = 2;
        if ($scope.isPass == "true") {
            status = 4;
        } else if ($scope.isPass == "false") {
            status = 3;
        }
        var data = {
            params : {
                recruitId : recruit.id,
                approvalStatus : status,
                approvalResult : $scope.approvalResult
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