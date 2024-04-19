package com.amigoscode.product;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amigoscode.apiResponse.ApiResponse;
import com.amigoscode.product.request.ProductRequest;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // Get all products
    @GetMapping("/all")
    public ApiResponse<List<Product>> getAllProducts() {
        return new ApiResponse<List<Product>>(1000, null, productService.getAllProduct());
    }

    @PostMapping("/create")
    public ApiResponse<String> createProduct(@RequestBody
     ProductRequest product) {
        return new ApiResponse<String>(1000, null, productService.createProduct(product));
    }
    @PutMapping("/update/{id}")
    public ApiResponse<String> updateProduct(@RequestBody ProductRequest product, @PathVariable Long id) {
        return new ApiResponse<String>(1000, null, productService.updateProduct(product, id));
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteProduct(@PathVariable Long id) {
        return new ApiResponse<String>(1000, null, productService.deleteProduct(id));
    }
    @GetMapping("/{id}")
    public ApiResponse<Product> getProductById(@PathVariable Long id) {
        return new ApiResponse<Product>(1000, null, productService.getProductById(id));
    }
}
