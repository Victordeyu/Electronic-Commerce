package com.ecommy.demo.Buyer.service;

import com.ecommy.demo.Buyer.respository.ShopCartRepository;
import com.ecommy.demo.Common.DataObject.ShopCart;
import com.ecommy.demo.Common.Form.ShopCartForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BuyerShopCartServicelm implements BuyerShopCartService{

    @Autowired
    ShopCartRepository shopCartRepository;

    @Override
    public ShopCart findOne(String shopCartId) {
        return shopCartRepository.findByShopCartId(shopCartId);
    }

    @Override
    public List<ShopCart> findByOpenId(String openId) {
        return shopCartRepository.findByOpenId(openId);
    }

    @Override
    public ShopCart findByOpenIdAndSingleId(String openId,String singleId) {
        return shopCartRepository.findByOpenIdAndSingleId(openId,singleId);
    }

    @Override
    public void save(ShopCart shopCart) {
        shopCartRepository.save(shopCart);
    }

    @Override
    public void deleteBySingleIdAndOpenId(String openId, String singleId) {
        shopCartRepository.deleteByOpenIdAndSingleId(openId,singleId);
    }

    @Override
    public void deleteByOpenId(String openId) {
        shopCartRepository.deleteByOpenId(openId);
    }
}
