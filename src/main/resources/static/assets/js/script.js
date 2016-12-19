// Code goes here

angular.module('ycsb-app', ['ngTableToCsv'])
.controller('ExportCtrl',['$scope',function($scope){
  $scope.title = "ng-table-to-csv";
}]);