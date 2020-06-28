package com.ecommy.demo.Product.Repository;

import com.ecommy.demo.Common.DataObject.ComputerProduct;
import com.ecommy.demo.Common.DataObject.PhoneProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComputerRepository extends JpaRepository<ComputerProduct,String> {

    ComputerProduct findBySingleId(String singleId);

    void deleteBySingleId(String singleId);

    void deleteByProductId(String productId);

    List<ComputerProduct> findByProductId(String productId);

    List<ComputerProduct> findBySellerId(String sellerId);
}
