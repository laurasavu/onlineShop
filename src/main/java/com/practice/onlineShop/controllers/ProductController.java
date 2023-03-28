package com.practice.onlineShop.controllers;

import com.practice.onlineShop.exceptions.productNotFoundException;
import com.practice.onlineShop.service.ProductService;
import com.practice.onlineShop.vos.ProductVO;
import org.springframework.web.bind.annotation.*;

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
        return   productService.getPrduct(productCode);

    }

}
