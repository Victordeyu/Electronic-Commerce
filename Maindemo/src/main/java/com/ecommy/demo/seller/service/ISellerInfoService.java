package com.ecommy.demo.seller.service;

import com.ecommy.demo.Common.DataObject.SellerInfo;

import java.util.List;

public abstract class ISellerInfoService {
    public abstract void save(SellerInfo receiverInfo);//保存或修改商家信息

    //public abstract SellerInfo Modify(String shopId, String openId);

    public abstract List<SellerInfo> findAll(String openid);

    public abstract SellerInfo findOne(String recId);
}

