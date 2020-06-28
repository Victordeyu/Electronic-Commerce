package com.ecommy.demo.seller.repository;

import com.ecommy.demo.Common.DataObject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    List<SellerInfo> findAllByOpenId(String openid);

    List<SellerInfo> findAllBySellerAccount(String sellerAccount);
}