package com.ecommy.demo.Product.Repository;


import com.ecommy.demo.Common.DataObject.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

//    List<Product> findByProductStatus(Integer productStatus);
        List<Product> findByProductStatus(Integer p);

        List<Product> findBySellerId(String sellerId);

        List<Product> findByCateGory(int cateGory);

        List<Product> findBySellerIdAndProductStatus(String sellerId ,Integer status);

        List<Product> findByCateGoryAndProductStatus(int cateGory,Integer productStatus );

        @Query(value = "select t from Product t where t.productName like %?1% or t.productDesc like %?1%" )
        List<Product> findByProductDescOrProductNameLike( String Keyword);
}
