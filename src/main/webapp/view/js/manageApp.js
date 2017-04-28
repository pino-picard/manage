/**
 * Created by caoxiao on 2017/4/6.
 */
var app = angular.module("manageApp", ['ngRoute']);

app.config(['$routeProvider',function ($routeProvider) {
    $routeProvider
        .when(
            '/attendance-leave',
            {
                templateUrl:'view/views/attendance/leave.html'
            }
        )
        .when(
            '/attendance-leaveApply',
            {
                templateUrl:'view/views/attendance/leaveApply.html'
            }
        )
        .when(
            '/attendance-leaveApproval',
            {
                templateUrl:'view/views/attendance/leaveApproval.html'
            }
        )
        .when(
            '/attendance-myLeave',
            {
                templateUrl:'view/views/attendance/myLeave.html'
            }
        )
        .when(
            '/office-changeApproval',
            {
                templateUrl:'view/views/office/changeApproval.html'
            }
        )
        .when(
            '/office-leaveApproval',
            {
                templateUrl:'view/views/office/leaveApproval.html'
            }
        )
        .when(
            '/office-myRecruitment',
            {
                templateUrl:'view/views/office/myRecruitment.html'
            }
        )
        .when(
            '/office-recruitmentApproval',
            {
                templateUrl:'view/views/office/recruitmentApproval.html'
            }
        )
        .when(
            '/personal-changeApply',
            {
                templateUrl:'view/views/personal/changeApply.html'
            }
        )
        .when(
            '/personal-changeApproval',
            {
                templateUrl:'view/views/personal/changeApproval.html'
            }
        )
        .when(
            '/personal-employeeChange',
            {
                templateUrl:'view/views/personal/employeeChange.html'
            }
        )
        .when(
            '/personal-employeeManage',
            {
                templateUrl:'view/views/personal/employeeManage.html'
            }
        )
        .when(
            '/personal-myChange',
            {
                templateUrl:'view/views/personal/myChange.html'
            }
        )
        .when(
            '/recruitment-allRecruitment',
            {
                templateUrl:'view/views/recruitment/allRecruitment.html'
            }
        )
        .when(
            '/recruitment-myRecruitment',
            {
                templateUrl:'view/views/recruitment/myRecruitment.html'
            }
        )
        .when(
            '/recruitment-recruitmentApply',
            {
                templateUrl:'view/views/recruitment/recruitmentApply.html'
            }
        )
        .when(
            '/recruitment-recruitmentApproval',
            {
                templateUrl:'view/views/recruitment/recruitmentApproval.html'
            }
        )
        .when(
            '/system-modelManage',
            {
                templateUrl:'view/views/system/modelManage.html'
            }
        )
        .when(
            '/system-roleManage',
            {
                templateUrl:'view/views/system/roleManage.html'
            }
        )
        .when(
            '/system-systemLog',
            {
                templateUrl:'view/views/system/systemLog.html'
            }
        )
        .when(
            '/system-userManage',
            {
                templateUrl:'view/views/system/userManage.html'
            }
        )
        .otherwise(
            {
                templateUrl:'view/component/screen.html'
            }
        );
}]);

app.directive("companyTree",function () {
    return {
        restrict : "EA",
        replace : true,
        templateUrl : "view/template/companyTree.html",
        scope : {
            treeData : "="
        },
        controller : "companyTreeController"
    };
});

app.controller("companyTreeController", function ($scope) {
    $scope.treeData = {
        name : "Root部门",
        hasChildDepart : true,
        hasPerson : true,
        department : [
            {
                name: "测试部门",
                hasChildDepart : false,
                hasPerson : true,
                department: {

                },
                person : [
                    {
                        name : "赵必伟"
                    },
                    {
                        name : "周竞帆"
                    },
                    {
                        name : "小叔叔"
                    }
                ]
            },
            {
                name: "中心研发部",
                hasChildDepart : true,
                hasPerson : true,
                department: [
                    {
                        name: "基础研发部",
                        hasChildDepart : false,
                        hasPerson : true,
                        department: [],
                        person : [
                            {
                                name : "张三"
                            },
                            {
                                name : "李四"
                            },
                            {
                                name : "王五"
                            }
                        ]
                    }
                ],
                person : [
                    {
                        name : "AA"
                    },
                    {
                        name : "BB"
                    },
                    {
                        name : "CC"
                    }
                ]
            }
        ],
        person : [
            {
                name : "萨瓦迪卡"
            },
            {
                name : "萨瓦兰奇"
            },
            {
                name : "萨顶顶"
            }
        ]
    }
});