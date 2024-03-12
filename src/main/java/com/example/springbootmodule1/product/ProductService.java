package com.example.springbootmodule1.product;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.math.BigDecimal;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() { return productRepository.findAll(); }

    public Product getProductById(Long productId) { return productRepository.getReferenceById(productId); }

    public void addNewProduct(Product product) { productRepository.save(product); }

    public void deleteProduct(Long productId) {
        boolean exists = productRepository.existsById(productId);
        if (!exists) {
            throw new IllegalStateException("Product with an id of (" + productId + ") does not exist!");
        }
        productRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(Long productId, String newName, BigDecimal newPrice) {
        Product product = productRepository.getReferenceById(productId);
        if (newName != null && !newName.isEmpty() && !Objects.equals(productId, product.getId())) {
            product.setName(newName);
        }
        if (newPrice != null && !Objects.equals(newPrice, product.getPrice())) {
            product.setPrice(newPrice);
        }
    }

}
