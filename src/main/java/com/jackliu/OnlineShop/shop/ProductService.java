package com.jackliu.OnlineShop.shop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Transactional
    public void removeOneStorageById(int id) {
        Optional<Product> theProduct = this.productRepository.findById(id);

        if (theProduct.isPresent()) {
            Product product = theProduct.get();
            product.setStorage(product.getStorage() - 1);
            this.productRepository.save(product);
        }

    }



}
