/**
 * Created by caoxiao on 2017/5/13.
 */
app.controller("employeeController", ['$scope', '$location', "$http", function($scope, $location, $http) {
    $scope.employee = {};

    $scope.email = {
        first : "",
        second : "",
        third : ""
    };

    $scope.changeEmail = function () {
        $scope.employee.email = $scope.email.first + "@" + $scope.email.second + "." + $scope.email.third;
    };

    $scope.submitForm = function () {
        setTime();

        var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/addEmployee";

        $http.post(url,$scope.employee).then(
            function successCallback(response) {

            },
            function errorCallback(response) {

            }
        );
    };

    var setTime = function () {
        $scope.employee.birthday = document.getElementById("birthday").value;
        $scope.employee.workStartTime = document.getElementById("work_startTime").value;
        $scope.employee.workEndTime = document.getElementById("work_endTime").value;
        $scope.employee.projectStartTime = document.getElementById("project_startTime").value;
        $scope.employee.projectEndTime = document.getElementById("project_endTime").value;
    };


    $(".birthday_datetime").datetimepicker({
        linkFormat: "yyyy-mm-dd",
        pickerPosition: "bottom-left",
        linkField: "birthday",
        autoclose: true,
        startView: 4,
        minView: 2
    });

    $(".work_startTime").datetimepicker({
        linkFormat: "yyyy-mm-dd",
        pickerPosition: "bottom-left",
        linkField: "work_startTime",
        autoclose: true,
        startView: 4,
        minView: 2
    });

    $(".work_endTime").datetimepicker({
        linkFormat: "yyyy-mm-dd",
        pickerPosition: "bottom-left",
        linkField: "work_endTime",
        autoclose: true,
        startView: 4,
        minView: 2
    });

    $(".project_startTime").datetimepicker({
        linkFormat: "yyyy-mm-dd",
        pickerPosition: "bottom-left",
        linkField: "project_startTime",
        autoclose: true,
        startView: 4,
        minView: 2
    });

    $(".project_endTime").datetimepicker({
        linkFormat: "yyyy-mm-dd",
        pickerPosition: "bottom-left",
        linkField: "project_endTime",
        autoclose: true,
        startView: 4,
        minView: 2
    });
}]);

app.controller("employeeList", ['$scope', '$location', "$http", function($scope, $location, $http) {
    $scope.employeeList = [];
    var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/getEmployeeList";

    $http.get(url).then(
        function successCallback(response) {
            $scope.employeeList = response.data.data;
        },
        function errorCallback(response) {

        }
    );
}]);
