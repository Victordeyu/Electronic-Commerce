package com.ecommy.demo.Account.service;


import com.ecommy.demo.Common.DataObject.Receiver;

import java.util.List;

public abstract class IReceiverInfoService {
    public abstract void save(Receiver receiverInfo);//保存或修改地址

    //public abstract void DefaultAddr(String recId, String openid);//设置默认地址

    public abstract List<Receiver> findAll(String openid);

    public abstract void delete(String recId);

    public abstract Receiver findOne(String recId);
}
