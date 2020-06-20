package com.ecommy.demo.Product.Service;

import com.ecommy.demo.Common.DataObject.ComputerProduct;
import com.ecommy.demo.Common.DataObject.PhoneProduct;
import com.ecommy.demo.Product.Repository.ComputerRepository;
import com.ecommy.demo.Product.Repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneServicelm implements PhoneService{

    @Autowired
    PhoneRepository phoneRepository;

    @Override
    public PhoneProduct findOne(String SingleId) {
        PhoneProduct phoneProduct=phoneRepository.findBySingleId(SingleId);
        return phoneProduct;
    }

    @Override
    public void Save(PhoneProduct newSingle) {
        phoneRepository.save(newSingle);
    }

    @Override
    public void delete(String singleId) {
        phoneRepository.deleteBySingleId(singleId);
    }

    @Override
    public void increaseInventory(String singleId, Integer num) {

    }


}