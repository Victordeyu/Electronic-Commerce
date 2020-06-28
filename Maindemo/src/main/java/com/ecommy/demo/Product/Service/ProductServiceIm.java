package com.ecommy.demo.Product.Service;

import com.ecommy.demo.Common.DataObject.Product;
import com.ecommy.demo.Common.Enums.ProductStatusEnum;
import com.ecommy.demo.Product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductServiceIm implements ProductService {

    @Autowired
    private ProductRepository repository;


    @Override
    public Product findOne(String ProductId) {
        return repository.findById(ProductId).orElse(null);
    }

    @Override
    public List<Product> findBySellerId(String sellerId) {
        return repository.findBySellerId(sellerId);
    }

    @Override
    public List<Product> findBySellerIdAndProductStatus(String sellerId, Integer status) {
        return repository.findBySellerIdAndProductStatus(sellerId, status);
    }

    @Override
    public Product findBySingleId(String singleId) {
        return null;
    }

    @Override
    public void setSales(String productId, int sale) {
        Product product=findOne(productId);
        product.setSales(sale);
        repository.save(product);
    }

    @Override
    public void increaseSales(String productId, int change) {
        Product product=findOne(productId);
        setSales(productId,product.getSales()+change);
    }

    @Override
    public Product OnSale(String ProductId){
        Product product=repository.findById(ProductId).orElse(null);
        if(product==null) {
        }

        product.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(product);
    }

    @Override
    public Product OffSale(String ProductId){
        Product product=repository.findById(ProductId).orElse(null);
        if(product==null) {
        }

        product.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(product);
    }

    @Override
    public Product Save(Product product){
        return repository.save(product);
    }

    @Override
    public void Delete(String ProductID){
        repository.deleteById(ProductID);
    }

    @Override
    public List<Product> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<Product> findOffAll() {
        return repository.findByProductStatus(ProductStatusEnum.DOWN.getCode());
    }

    @Override
    public List<Product> findByCategory(int cateGory) {
        return repository.findByCateGory(cateGory);
    }

    @Override
    public List<Product> findByCategoryAndStatus(int cateGory, Integer status) {
        return repository.findByCateGoryAndProductStatus(cateGory,status);
    }

    @Override
    public List<Product> findByProductDescOrProductNameLike(String Keyword) {
        return repository.findByProductDescOrProductNameLike("%"+Keyword+"%");
    }
}
