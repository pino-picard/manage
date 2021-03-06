/**
 * Created by caoxiao on 2017/4/6.
 */
app.controller("myNoteCtrl", ['$scope', '$location', "$http", function($scope, $location, $http) {
    $scope.hello = "empty";

    $scope.my_data = [{
        label: 'Languages',
        children: ['Jade','Less','Coffeescript']
    }];

    $scope.my_tree = {};

    var url = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/manage/test";
    $http.get(url).then(
        function successCallback(response) {

        },
        function errorCallback(response) {

        }
    );

    $scope.treeData = [{
        label : "Root部门",
        children : [
            {
                label: "测试部门",
                children : [
                    {
                        label : "赵一"
                    },
                    {
                        label : "杨二"
                    },
                    {
                        label : "Alice"
                    }
                ]
            },
            {
                label: "中心研发部",
                children: [
                    {
                        label: "基础研发部",
                        children : [
                            {
                                label : "张三"
                            },
                            {
                                label : "李四"
                            },
                            {
                                label : "王五"
                            }
                        ]
                    },
                    {
                        label: "产品研发部",
                        children: [
                            {
                                label: "ET"
                            },
                            {
                                label: "钱七"
                            },
                            {
                                label: "当归"
                            }
                        ]
                    }
                ]
                // children : [
                //     {
                //         label : "AA"
                //     },
                //     {
                //         label : "BB"
                //     },
                //     {
                //         label : "CC"
                //     }
                // ]
            }
        ],
        // children : [
        //     {
        //         label : "萨瓦迪卡"
        //     },
        //     {
        //         label : "萨瓦兰奇"
        //     },
        //     {
        //         label : "萨顶顶"
        //     }
        // ]
    }]
}]);