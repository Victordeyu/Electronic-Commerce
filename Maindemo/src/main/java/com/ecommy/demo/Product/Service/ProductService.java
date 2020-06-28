package com.ecommy.demo.Product.Service;

import com.ecommy.demo.Common.DataObject.Product;

import java.util.List;

public interface ProductService {

    //查找商品
    Product findOne(String ProductId);

    //查找某个商家的商品
    List<Product> findBySellerId(String sellerId);

    List<Product> findBySellerIdAndProductStatus(String sellerId ,Integer status);

    Product findBySingleId(String singleId);

    void setSales(String productId,int sale);

    void increaseSales(String productId,int change);

    //上架
    Product OnSale(String ProductId);

    //下架
    Product OffSale(String ProductId);

    //新增
    Product Save(Product Product);

    void Delete(String ProductId);

    List<Product> findUpAll();

    List<Product> findOffAll();

    List<Product> findByCategory(int cateGory);

    List<Product> findByCategoryAndStatus(int cateGory,Integer status);

    List<Product> findByProductDescOrProductNameLike(String Keyword);
}
