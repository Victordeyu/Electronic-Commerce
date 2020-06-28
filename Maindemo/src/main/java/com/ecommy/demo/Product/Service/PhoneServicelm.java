package com.ecommy.demo.Product.Service;

import com.ecommy.demo.Common.DataObject.ComputerProduct;
import com.ecommy.demo.Common.DataObject.PhoneProduct;
import com.ecommy.demo.Product.Repository.ComputerRepository;
import com.ecommy.demo.Product.Repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<PhoneProduct> findBySellerId(String sellerId) {
        return phoneRepository.findBySellerId(sellerId);
    }

    @Override
    public void Save(PhoneProduct newSingle) {
        phoneRepository.save(newSingle);
    }

    @Override
    public List<PhoneProduct> findByProductId(String productId) {
        return phoneRepository.findByProductId(productId);
    }

    @Override
    public void setSales(String singleId, int sales) {
        PhoneProduct phoneProduct=phoneRepository.findBySingleId(singleId);
        phoneProduct.setSales(sales);
        Save(phoneProduct);
    }

    @Override
    public void increaseSales(String singleId, int change) {
        PhoneProduct phoneProduct=findOne(singleId);
        setSales(singleId,change+phoneProduct.getSales());
    }

    @Override
    public void delete(String singleId) {
        phoneRepository.deleteBySingleId(singleId);
    }

    @Override
    public void deleteByProductId(String productId) {
        phoneRepository.deleteByProductId(productId);
    }

    @Override
    public void increaseInventory(String singleId, Integer num) {
        PhoneProduct phoneProduct=phoneRepository.findBySingleId(singleId);
        int newInventory=phoneProduct.getInventory()+num;
        phoneProduct.setInventory(newInventory);
//        phoneRepository.deleteBySingleId(singleId);
        phoneRepository.save(phoneProduct);
    }


}