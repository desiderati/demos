/*
 * Copyright (c) 2019 - Felipe Desiderati
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
'use strict';

myApp.factory('myService', ['$http', '$q', '$log', '$environmentConfig',
    function ($http, $q, $log, $environmentConfig) {
        return {
            fetchAllProducts: function () {
                return $http.get($environmentConfig.apiUrl + '/api/v1/product').then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        $log.error('Error while fetching Products.');
                        return $q.reject(errResponse);
                    });
            },

            createProduct: function (product) {
                return $http.post($environmentConfig.apiUrl + '/api/v1/product', product).then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        $log.error('Error while creating Product.');
                        return $q.reject(errResponse);
                    });
            },

            updateProduct: function (product, id) {
                return $http.put($environmentConfig.apiUrl + '/api/v1/product/' + id, product).then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        $log.error('Error while updating Product.');
                        return $q.reject(errResponse);
                    });
            },

            deleteProduct: function (id) {
                return $http.delete($environmentConfig.apiUrl + '/api/v1/product/' + id).then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        $log.error('Error while deleting Product.');
                        return $q.reject(errResponse);
                    });
            }
        };
    }]);
