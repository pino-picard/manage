/**
 * Created by caoxiao on 2017/5/31.
 */
app.controller("companyTreeController", ['$scope', '$location', "$http", "$uibModal", "$route", "$rootScope", function($scope, $location, $http, $uibModal, $route, $rootScope, Session) {
    $scope.companyList = [];
    $scope.companySelectList = [];
    $scope.currentCompany = {};
    var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/getEmployeeTree";
    $http.get(url).then(
        function successCallback(response) {
            if (response.data.success){
                $scope.companyList = response.data.data;
                $scope.companyList.forEach(function (company) {
                    company.select = false;
                    company.active = '';
                });
                $scope.companyList[0].select = true;
                $scope.companyList[0].active = 'active';
                $scope.currentCompany = $scope.companyList[0];
            }
        },
        function errorCallback(response) {

        }
    );

    $scope.selectCompany = function (index) {
        $scope.companyList.forEach(function (company) {
            company.select = false;
            company.active = '';
        });
        $scope.companyList[index].select = true;
        $scope.companyList[index].active = 'active';
        $scope.currentCompany = $scope.companyList[index];
    };

    $scope.isSelect = function (index) {
        return $scope.companyList[index].active;
    }
}]);