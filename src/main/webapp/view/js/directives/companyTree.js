/**
 * Created by caoxiao on 2017/4/13.
 */
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
        name : "1111111",
        hasChildDepart : true,
        hasPerson : true,
        department : [
            {
                name: "2222222222",
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
                name: "3333333",
                hasChildDepart : true,
                hasPerson : false,
                department: {
                    name: "444444444",
                    hasChildDepart : false,
                    hasPerson : false,
                    department: {

                    },
                    person : []
                },
                person : []
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