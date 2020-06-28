package com.ecommy.demo.seller.service;

import com.ecommy.demo.Common.DataObject.SellerInfo;
import com.ecommy.demo.seller.repository.SellerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerInfoService extends ISellerInfoService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public void save(SellerInfo sellerInfo) {
        sellerInfoRepository.save(sellerInfo);
    }

   /* @Override
    public SellerInfo Modify(String shopId, String openId) {
        return sellerInfoRepository.findById(openId).orElse(null);
    }*/

    @Override//gai
    public List<SellerInfo> findAll(String sellerAccount) {
        return sellerInfoRepository.findAllBySellerAccount(sellerAccount);
    }

    @Override
    public SellerInfo findOne(String sellerAccount) {
        return sellerInfoRepository.findById(sellerAccount).orElse(null);
    }
}
