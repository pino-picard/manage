/**
 * Created by caoxiao on 2017/4/13.
 */
App.directives("companyTree",function () {
    return {
        replace : true,
        templateUrl : "view/template/companyTree.html",
        scope : {
            dataTree : "="
        },
        controller : "companyTreeController"
    }
});

App.controller("companyTreeController", function ($scope) {

});