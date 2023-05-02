package com.practice.onlineShop.service;

import com.practice.onlineShop.entities.Product;
import com.practice.onlineShop.exceptions.InvalidCodeException;
import com.practice.onlineShop.exceptions.productNotFoundException;
import com.practice.onlineShop.mappers.ProductMapper;
import com.practice.onlineShop.repositories.ProductRepository;
import com.practice.onlineShop.vos.ProductVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final  ProductMapper productMapper;
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public  List<ProductVO> getAllProducts() {
        List<ProductVO>allProductsInDb=new ArrayList<>();
        Product product;
        Iterable<Product>allProducts=productRepository.findAll();
        for (Product allProduct : allProducts) {
            product = allProduct;
            ProductVO productVO = productMapper.toVO(product);

            allProductsInDb.add(productVO);
        }
        return allProductsInDb;
    }


    public void addProduct(ProductVO productVO,Long customerId){
        System.out.println("customer id in service"+customerId);
    Product product=productMapper.toEntity(productVO);
    productRepository.save(product);
    }

    public ProductVO getProduct(String productCode) throws productNotFoundException {
        Product product = getProduct1(productCode);
        return  productMapper.toVO(product);
    }





    public void updateProduct(ProductVO productVO, Long customerId) throws InvalidCodeException, productNotFoundException {
        System.out.println("customer id in service"+customerId);
        if(productVO.getCode()==null){
            throw new InvalidCodeException();
        }
      Product product=getProduct1(productVO.getCode());
        product.setCurrency(productVO.getCurrency());
        product.setValid(productVO.isValid());
        product.setStock(productVO.getStock());
        product.setDescription(productVO.getDescription());
        product.setCurrency(productVO.getCurrency());
       product.setCode(productVO.getCode());
       productRepository.save(product);
    }

    private Product getProduct1(String productCode) throws productNotFoundException {
        Optional<Product>productOptional=productRepository.findByCode(productCode);
        if(productOptional.isEmpty()){
            throw new productNotFoundException();
        }

        return productOptional.get();
}

    public void deleteProduct(String productCode, Long customerId) throws productNotFoundException, InvalidCodeException {
        System.out.println("userul cu id-ul"+customerId+"sterge"+"produsul"+productCode);
        if(productCode==null){
            throw new InvalidCodeException();
        }
       Product product= getProduct1(productCode);
       productRepository.delete(product);
    }

}
