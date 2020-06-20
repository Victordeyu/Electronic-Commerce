package com.ecommy.demo.Account.service;


import com.ecommy.demo.Account.respository.ReceiverInfoRepository;
import com.ecommy.demo.Common.DataObject.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiverInfoService extends IReceiverInfoService {
    @Autowired
    private ReceiverInfoRepository receiverInfoRepository;

    @Override
    public void save(Receiver receiverInfo) {
        receiverInfoRepository.save(receiverInfo);
    }


    @Override
    public List<Receiver> findAll(String openid) {
        return receiverInfoRepository.findAllByOpenid(openid);
    }

    @Override
    public void delete(String recId) {
        receiverInfoRepository.deleteById(recId);
    }

    @Override
    public Receiver findOne(String recId) {
        return receiverInfoRepository.findById(recId).orElse(null);
    }
}
