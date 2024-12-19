package com.example.productApp.controller;

import com.example.productApp.model.Product;
import com.example.productApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("allProducts")
    public List<Product> getAllProducts(){
        //return "all products";
        return productService.getAllProducts();
    }
    @GetMapping("name/{name}")
    public List<Product> getProduct(@PathVariable String name){
        return productService.getProduct(name);
    }
    @PostMapping("add")
    public String addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
    @DeleteMapping("delete/{productId}")
    public String deleteProduct(@PathVariable Integer productId){
        System.out.println("controller");
        return productService.deleteProduct(productId);
    }
    @PutMapping("update/{id}")
    public String updateProduct(@PathVariable Integer id,@RequestBody Product product){
        return productService.updateProduct(id,product);
    }
}
