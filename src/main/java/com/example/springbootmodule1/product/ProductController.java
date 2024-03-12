package com.example.springbootmodule1.product;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(path="{productId}")
    public Product getOneProduct(@PathVariable("productId") Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping
    public void registerNewProduct(@RequestBody Product product) { productService.addNewProduct(product); }

    @DeleteMapping(path="{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
    }

    @PutMapping(path="{productId}")
    public void updateProduct(@PathVariable("productId") Long customerId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) BigDecimal price) {
        productService.updateProduct(customerId, name, price);
    }

}
