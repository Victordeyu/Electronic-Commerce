package com.ecommy.demo.Product.Service;

import com.ecommy.demo.Common.DataObject.Product;

import java.util.List;

public interface ProductService {

    //查找商品
    Product findOne(String ProductId);

    //上架
    Product OnSale(String ProductId);

    //下架
    Product OffSale(String ProductId);

    //新增
    Product Save(Product Product);

    void Delete(String ProductId);

    List<Product> findUpAll();

    List<Product> findOffAll();
}
