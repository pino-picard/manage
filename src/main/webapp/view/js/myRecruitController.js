/**
 * Created by caoxiao on 2017/5/31.
 */
app.controller("myRecruitController", ['$scope', '$location', "$http", "$uibModal", "$route", "$rootScope", function($scope, $location, $http, $uibModal, $route, $rootScope, Session) {
    $scope.recruitList = [];
    $scope.searchList = ["按标题进行搜索","按状态进行搜索"];
    var purl = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/getRecruitList?" +
        "applyPerson=pino" + "&status=1,3,4,5";
    $http.get(purl).then(
        function successCallback(response) {
            if (response.data.success) {
                $scope.recruitList = response.data.data;
            }
        },
        function errorCallback(response) {

        }
    );

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
            "title="+ $scope.title + "&status=1,3,4,5";
        $http.get(url).then(
            function successCallback(response) {
                $scope.recruitList = response.data.data;
            },
            function errorCallback(response) {

            }
        );
    };

    $scope.applyApproval = function (index) {
        var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/setRecruitStatus";
        var data = {
            params : {
                recruitId : $scope.recruitList[index].id,
                status : 2
            }
        };
        $http.post(url,'',data).then(
            function successCallback(response) {
                alert("提交申请成功");
                $route.reload();
            },
            function errorCallback(response) {

            }
        );
    }
}]);