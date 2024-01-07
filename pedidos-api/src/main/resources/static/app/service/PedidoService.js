;(function() {
    angular
        .module('app')
        .service('PedidoService', ['$http', function($http) {
            return {
                get: function() {
                    return $http.get('/api/pedidos');
                },
                save: function(data) {
                    return $http.post('/api/pedidos', data);
                }
            };
        }]);
})();
