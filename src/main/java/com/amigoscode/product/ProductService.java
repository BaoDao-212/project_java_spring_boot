package com.amigoscode.product;


import java.security.Principal;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.amigoscode.product.request.ProductRequest;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao repository;
    private final ProductDao productRepository;
   
    // methods change password
    public String createProduct( ProductRequest request)  {
        var product = productRepository.findProductByName(request.getName());
        if(product!=null){
            throw new IllegalStateException("Product is existed");
        }
        var data= repository.createProduct(Product.builder().product_name(request.getName()).numberProducts(request.getQuanlity()).build());
        return "Created product successfully!";
    }
    // method update product
    public String updateProduct(ProductRequest request,Long id) {
        var product =productRepository.findProductById(id);
        if(product==null){
            throw new IllegalStateException("Product not found");
        }
        repository.updateProduct(product);
        return "Updated product successfully";
    }
    // method delete product
    public String deleteProduct(Long id){
        var product= productRepository.findProductById(id);
        if(product==null){
            throw new IllegalStateException("Product not found");
        }
        repository.deleteProduct(id);
        return "Updated product successfully";
    }
    //method get all product
    public List<Product> getAllProduct(){
        return repository.findAllProducts();
    }
    //method get product
    public Product getProductById(Long id){
        return repository.findProductById(id);
    }

}
