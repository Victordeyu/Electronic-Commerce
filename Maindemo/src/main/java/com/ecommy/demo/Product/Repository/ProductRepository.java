package com.ecommy.demo.Product.Repository;


import com.ecommy.demo.Common.DataObject.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

//    List<Product> findByProductStatus(Integer productStatus);
        List<Product> findByProductStatus(Integer p);
}
