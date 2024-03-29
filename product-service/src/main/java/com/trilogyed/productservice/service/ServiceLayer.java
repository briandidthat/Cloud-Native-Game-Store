package com.trilogyed.productservice.service;

import com.trilogyed.productservice.dao.ProductDao;
import com.trilogyed.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {

    ProductDao dao;


    @Autowired
    public ServiceLayer(ProductDao dao){this.dao = dao;}


    public Product createProduct(Product product){
        return dao.createProduct(product);
    }

    public void updateProduct(Product product){
        dao.updateProduct(product);
    }

    public Product getProduct(int productId){
        return dao.getProduct(productId);
    }

    public Product getProductByName(String productName) {
        return dao.getProductByName(productName);
    }

    public List<Product> getAllProducts(){
        return dao.getAllProducts();
    }

    public void deleteProduct(int productId){
        dao.deleteProduct(productId);
    }

}
