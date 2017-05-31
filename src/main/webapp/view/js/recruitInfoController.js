/**
 * Created by caoxiao on 2017/5/31.
 */
app.controller("recruitInfoController", ['$scope', '$location', "$http", "$uibModal", "$route", "$rootScope", function($scope, $location, $http, $uibModal, $route, $rootScope, Session) {
    $scope.recInfo = {};
    $scope.selectedApprover = {};
    $scope.approverList = [];

    var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/getUserList?roleName=4";
    $http.get(url).then(
        function successCallback(response) {
            if (response.data.success) {
                $scope.approverList = response.data.data;
            }
        },
        function errorCallback(response) {

        }
    );

    $scope.submit = function () {
        $scope.recInfo.deadline = document.getElementById("deadline").value;
        var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/createRecruit";
        var data = {
            title : $scope.recInfo.title,
            reason : $scope.recInfo.applyReason,
            description : $scope.recInfo.description,
            approver : $scope.selectedApprover.user_name,
            deadline : $scope.recInfo.deadline,
            applyPerson : ''
        };
        $http.post(url,data).then(
            function successCallback(response) {
                if (response.data.success) {
                    alert("申请招聘信息成功！");
                }
            },
            function errorCallback(response) {

            }
        );
    };
    $(".deadline").datetimepicker({
        linkFormat: "yyyy-mm-dd",
        pickerPosition: "bottom-left",
        linkField: "deadline",
        autoclose: true,
        startView: 4,
        minView: 2
    });
}]);