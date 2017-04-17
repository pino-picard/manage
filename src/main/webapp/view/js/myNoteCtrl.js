/**
 * Created by caoxiao on 2017/4/6.
 */
app.controller("myNoteCtrl", ['$scope', '$location', "$http", function($scope, $location, $http) {
    $scope.hello = "empty";

    var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/test";
    $http.get(url).then(
        function successCallback(response) {
            $scope.hello = response.data;
        },
        function errorCallback(response) {
            $scope.hello = "error";
        }
    );
}]);