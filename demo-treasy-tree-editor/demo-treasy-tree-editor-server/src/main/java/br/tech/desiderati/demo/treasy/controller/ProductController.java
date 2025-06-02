/*
 * Copyright (c) 2020 - Felipe Desiderati
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
package br.tech.desiderati.demo.treasy.controller;

import br.tech.desiderati.demo.treasy.controller.dto.ProductDTO;
import br.tech.desiderati.demo.treasy.domain.Product;
import br.tech.desiderati.demo.treasy.service.ProductService;
import dev.springbloom.web.rest.exception.ConflictRestApiException;
import dev.springbloom.web.rest.exception.NotFoundRestApiException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;

    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ProductDTO> fetchAllProducts() {
        List<Product> products = productService.findAllProducts();
        if (products.isEmpty()) {
            return Collections.emptyList();
        }

        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(modelMapper.map(product, ProductDTO.class));
        }
        return productDTOs;
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody @Valid ProductDTO productDTO) {
        log.info("Creating Product with id {}", productDTO.getId());

        // We must validate otherwise it will update the product.
        Product currentProduct = productService.findById(Long.valueOf(productDTO.getId()));
        if (currentProduct != null) {
            log.error("Unable to create. Product with id {} already exists", productDTO.getId());
            throw new ConflictRestApiException("product_already_exists_exception", productDTO.getId());
        }

        if (productDTO.getParentProduct() != null && productDTO.getParentProduct().getId() != null) {
            Product parentProduct = productService.findById(Long.valueOf(productDTO.getParentProduct().getId()));
            if (parentProduct == null) {
                log.error("Unable to update. Product with id {} not found", productDTO.getParentProduct().getId());
                throw new NotFoundRestApiException("product_not_found_exception", productDTO.getParentProduct().getId());
            }
        }

        Product newProduct = modelMapper.map(productDTO, Product.class);
        newProduct = productService.saveProduct(newProduct);
        return modelMapper.map(newProduct, ProductDTO.class);
    }

    @PutMapping(value = "/{id}")
    public ProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody @Valid ProductDTO productDTO) {
        log.info("Fetching & Updating Product with id {}", id);

        // We must validate otherwise it will create a new product.
        Product currentProduct = productService.findById(id);
        if (currentProduct == null) {
            log.error("Unable to update. Product with id {} not found", id);
            throw new NotFoundRestApiException("product_not_found_exception", id);
        }

        modelMapper.map(productDTO, currentProduct);
        currentProduct = productService.saveProduct(currentProduct);
        return modelMapper.map(currentProduct, ProductDTO.class);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Product with id {}", id);
        productService.deleteProductById(id);
    }
}
