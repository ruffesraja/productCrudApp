package com.example.productApp.service;

import com.example.productApp.model.Product;
import com.example.productApp.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;
    public List<Product> getAllProducts() {
        List<Product> result = productDao.findAll();
        //System.out.println(result.get(0).getProductName());
        return result;
    }

    public List<Product> getProduct(String name) {
        return productDao.findByProductName(name);
    }

    public String addProduct(Product product) {
        productDao.save(product);
        return "product added successfully";
    }

    public String deleteProduct(Integer productId) {
        System.out.println("service");
        productDao.deleteById(productId);
        return "product deleted successfully";
    }

    public String updateProduct(Integer id,Product product) {
        Product old = productDao.findById(id) .orElseThrow(() -> new RuntimeException("Employee not found with id"));
        old.setProductName(product.getProductName());
        old.setProductPrice(product.getProductPrice());
        productDao.save(product);
        return "product updated";
    }
}
