package com.ecommy.demo.Buyer.respository;

import com.ecommy.demo.Common.DataObject.ShopCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopCartRepository extends JpaRepository<ShopCart,String> {

    ShopCart findByShopCartId(String shopCartId);

    ShopCart findByOpenIdAndSingleId(String openId,String SingleId);

    List<ShopCart> findByOpenId(String openId);

    void deleteByOpenIdAndSingleId(String openId,String singleId);

    void deleteByOpenId(String openId);
}
