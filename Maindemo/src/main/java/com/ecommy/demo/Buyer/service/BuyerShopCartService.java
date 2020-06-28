package com.ecommy.demo.Buyer.service;

import com.ecommy.demo.Common.DataObject.ShopCart;
import com.ecommy.demo.Common.Form.ShopCartForm;

import java.util.List;


public interface BuyerShopCartService {

    ShopCart findOne(String shopCartId);

    List<ShopCart> findByOpenId(String openId);

    ShopCart findByOpenIdAndSingleId(String openId,String singleId);

    void save(ShopCart shopCart);

    void deleteBySingleIdAndOpenId(String openId,String singleId);

    void deleteByOpenId(String openId);
}
