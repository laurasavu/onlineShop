package com.practice.onlineShop.controllers;

import com.practice.onlineShop.exceptions.InvalidCodeException;
import com.practice.onlineShop.exceptions.productNotFoundException;
import com.practice.onlineShop.service.ProductService;
import com.practice.onlineShop.vos.ProductVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/{customerId}")
    public void addProduct(@RequestBody ProductVO productVO, @PathVariable Long customerId) {

        productService.addProduct(productVO, customerId);
    }
    @GetMapping("/{productCode}")
    public ProductVO getProduct(@PathVariable String productCode) throws productNotFoundException {
        return   productService.getProduct(productCode);

    }
    @GetMapping
    public List<ProductVO>getProducts(){
        return productService.getAllProducts();
    }
    @PutMapping("/{customerId}")
    public void updateProduct(@RequestBody ProductVO productVO, @PathVariable Long customerId) throws InvalidCodeException, productNotFoundException {
        productService.updateProduct(productVO,customerId);
    }
    @DeleteMapping("/{productCode}/{customerId}")
    public void deleteProduct(@PathVariable String productCode,@PathVariable Long customerId) throws InvalidCodeException, productNotFoundException {
        productService.deleteProduct(productCode,customerId);
    }
}
