/*
 * Copyright (c) 2025 - Felipe Desiderati
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

myApp.controller('myController', ['myService', '$scope', '$log', '$uibModal',
    function (myService, $scope, $log, $uibModal) {

        let $ctrl = this;
        $ctrl.searchContent = '';
        $ctrl.products = [];

        $ctrl.fetchAllProducts = function () {
            myService.fetchAllProducts().then(
                function (response) {
                    $ctrl.products = response || [];
                },
                function () {
                    $log.error('Error while fetching Products.');
                });
        };

        $ctrl.openModalProductNotSelected = function () {
            swal.fire({type: 'error', title: 'Nenhum produto foi selecionado!!!'});
        };

        $ctrl.newProduct = function () {
            if ($ctrl.selectedProduct == null && $ctrl.products.length !== 0) {
                $ctrl.openModalProductNotSelected();
            } else {
                $uibModal.open({
                    animation: true,
                    templateUrl: 'product-form.html',
                    size: 'lg',
                    controller: 'myModalController',
                    controllerAs: 'myModalController',
                    resolve: {
                        selectedProduct: function () {
                            return (!!$ctrl.selectedProduct ? $ctrl.selectedProduct.$modelValue : null);
                        },
                        isNewProduct: function () {
                            return true;
                        }
                    }
                }).result.then(function (newProduct) {
                    if (!$ctrl.selectedProduct) {
                        $ctrl.fetchAllProducts();
                    } else {
                        if (!$ctrl.selectedProduct.$modelValue.children) {
                            $ctrl.selectedProduct.$modelValue.children = [];
                        }
                        $ctrl.selectedProduct.$modelValue.children.push(newProduct);
                        $ctrl.selectedProduct.expand();
                    }
                }, function () {
                    $log.info('Operation cancelled at: ' + new Date());
                });
            }
        };

        $ctrl.editProduct = function () {
            if ($ctrl.selectedProduct == null) {
                $ctrl.openModalProductNotSelected();
            } else {
                $uibModal.open({
                    animation: true,
                    templateUrl: 'product-form.html',
                    size: 'lg',
                    controller: 'myModalController',
                    controllerAs: 'myModalController',
                    resolve: {
                        selectedProduct: function () {
                            return $ctrl.selectedProduct.$modelValue;
                        },
                        isNewProduct: function () {
                            return false;
                        }
                    }
                }).result.then(function (editedProduct) {
                    $ctrl.selectedProduct.$modelValue.description = editedProduct.description;
                    $ctrl.selectedProduct.$modelValue.note = editedProduct.note;
                }, function () {
                    $log.info('Operation cancelled at: ' + new Date());
                });
            }
        };

        $ctrl.removeProduct = function () {
            if ($ctrl.selectedProduct == null) {
                $ctrl.openModalProductNotSelected();
            } else {
                // noinspection JSUnusedLocalSymbols
                myService.deleteProduct($ctrl.selectedProduct.$modelValue.id).then(
                    function (response) {
                        $ctrl.selectedProduct.remove();
                        $ctrl.clearProductSelection();
                    },
                    function (errResponse) {
                        if (errResponse.status === 404) {
                            $ctrl.selectedProduct.remove();
                            $ctrl.clearProductSelection();
                        }
                        $log.error('Error while deleting Product.');
                    });
            }
        };

        $ctrl.collapseAll = function () {
            $ctrl.clearProductSelection();
            $scope.$broadcast('angular-ui-tree:collapse-all');
        };

        $ctrl.expandAll = function () {
            $scope.$broadcast('angular-ui-tree:expand-all');
        };

        $ctrl.searchProducts = function () {
            // Do nothing!!!
        };

        $ctrl.toggle = function (product) {
            product.toggle();
            if (product.collapsed && $ctrl.IsChildSelected(product.$modelValue, false)) {
                $ctrl.clearProductSelection();
            }
        };

        $ctrl.selectProduct = function (product) {
            if (!$ctrl.isSelected(product.$modelValue)) {
                $ctrl.selectedProduct = product;
            } else {
                $ctrl.clearProductSelection();
            }
        };

        $ctrl.isVisible = function (product) {
            let isVisible = null;
            if (!!$ctrl.searchContent) {
                if (!!product.children) {
                    for (let i = 0; i < product.children.length; i++) {
                        if ($ctrl.isVisible(product.children[i])) {
                            isVisible = true;
                            break;
                        }
                    }
                }
                if (isVisible == null) {
                    isVisible = product.description.includes($ctrl.searchContent);
                }
            } else {
                isVisible = true;
            }

            if ($ctrl.isSelected(product) && !isVisible) {
                $ctrl.clearProductSelection();
            }

            return isVisible;
        };

        $ctrl.isSelected = function (product) {
            return $ctrl.selectedProduct && $ctrl.selectedProduct.$modelValue
                && $ctrl.selectedProduct.$modelValue.id === product.id;
        };

        /**
         * @return {boolean}
         */
        $ctrl.IsChildSelected = function(product, isChild) {
            if (!!product.children) {
                for (let i = 0; i < product.children.length; i++) {
                    if ($ctrl.IsChildSelected(product.children[i], true)) {
                        return true;
                    }
                }
            }
            return isChild && $ctrl.isSelected(product);
        };

        $ctrl.clearProductSelection = function () {
            $ctrl.selectedProduct = null;
        };

        $ctrl.renderProductTooltip = function (product) {
            const noteTooltip = !!product.note ? ", Observação: " + product.note : "";
            return "Código: " + product.id + ", Descriçãao: " + product.description + noteTooltip;
        };

        $ctrl.clearProductSelection();
        $ctrl.fetchAllProducts();
    }]);

myApp.controller('myModalController',
    ['myService', '$scope', '$log', '$uibModalInstance', 'selectedProduct', 'isNewProduct',
        function (myService, $scope, $log, $uibModalInstance, selectedProduct, isNewProduct) {

            const $ctrl = this;
            $ctrl.isNewProduct = isNewProduct;
            if ($ctrl.isNewProduct) {
                if (!!selectedProduct) {
                    $ctrl.product = {
                        id: "",
                        description: "",
                        note: "",
                        parentProduct: {
                            id: selectedProduct.id,
                            description: selectedProduct.description,
                            note: selectedProduct.note
                        }
                    }
                }
            } else {
                $ctrl.product = {
                    id: selectedProduct.id,
                    description: selectedProduct.description,
                    note: selectedProduct.note
                }
            }

            $ctrl.createProduct = function (product) {
                myService.createProduct(product).then(
                    function (response) {
                        $uibModalInstance.close(response);
                    },
                    /** @namespace errResponse.data.validationMessages */
                    function (errResponse) {
                        $log.error('Error while creating Product.');
                        if (typeof (errResponse.data.validationMessages) !== 'undefined') {
                            $ctrl.addValidationErrorMessages(errResponse.data.validationMessages);
                        } else {
                            $ctrl.addErrorMessage(errResponse.data.message);
                        }
                    });
            };

            $ctrl.updateProduct = function (product, id) {
                myService.updateProduct(product, id).then(
                    function (response) {
                        $uibModalInstance.close(response);
                    },
                    /** @namespace errResponse.data.validationMessages */
                    function (errResponse) {
                        $log.error('Error while updating Product.');
                        if (typeof (errResponse.data.validationMessages) !== 'undefined') {
                            $ctrl.addValidationErrorMessages(errResponse.data.validationMessages);
                        } else {
                            $ctrl.addErrorMessage(errResponse.data.message);
                        }
                    });
            };

            $ctrl.addErrorMessage = function (message) {
                $('#errorsContainer').show();
                $('<li/>').html(message).appendTo('#errors');
            };

            $ctrl.addValidationErrorMessages = function (messages) {
                $('#errorsContainer').show();
                for (let i = 0; i < messages.length; i++) {
                    const input = messages[i].split(':')[0];
                    const message = messages[i].split(':')[1];
                    $('<li/>')
                        .wrapInner(
                            $('<a/>')
                                .attr('href', 'javascript: void(0);')
                                .html(message)
                                .on('click', function () {
                                    // Focus on the invalid field
                                    $("input[name='" + input + "']").focus();
                                })
                        )
                        .appendTo('#errors');
                }
            };

            $ctrl.clearErrorMessages = function () {
                $('#errorsContainer').hide();
                $('#errors').html('');
            };

            $ctrl.submit = function () {
                $ctrl.clearErrorMessages();
                if ($ctrl.isNewProduct) {
                    $log.info('Saving new Product', $ctrl.product);
                    $ctrl.createProduct($ctrl.product);
                } else {
                    $ctrl.updateProduct($ctrl.product, $ctrl.product.id);
                    $log.info('Product updated with id: ', $ctrl.product.id);
                }
            };

            $ctrl.cancel = function () {
                $uibModalInstance.dismiss('Cancel');
            };
        }]);
