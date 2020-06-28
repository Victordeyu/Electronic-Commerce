package com.ecommy.demo.Product.Repository;

import com.ecommy.demo.Common.DataObject.PhoneProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneRepository extends JpaRepository<PhoneProduct,String> {

    PhoneProduct findBySingleId(String singleId);

    void deleteBySingleId(String singleId);

    void deleteByProductId(String productId);

    List<PhoneProduct> findBySellerId(String sellerId);

    List<PhoneProduct> findByProductId(String productId);
}
