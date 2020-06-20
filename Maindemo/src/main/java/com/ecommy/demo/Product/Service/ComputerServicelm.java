package com.ecommy.demo.Product.Service;

import com.ecommy.demo.Common.DataObject.ComputerProduct;
import com.ecommy.demo.Product.Repository.ComputerRepository;
import com.ecommy.demo.Product.Repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputerServicelm implements ComputerService{

    @Autowired
    ComputerRepository computerRepository;

    @Override
    public ComputerProduct findOne(String SingleId) {
        ComputerProduct computerProduct= (ComputerProduct) computerRepository.findBySingleId(SingleId);
        return computerProduct;
    }

    @Override
    public void save(ComputerProduct newComputer) {
        computerRepository.save(newComputer);
    }

    @Override
    public void delete(String singleId) {
                computerRepository.deleteBySingleId(singleId);
    }

    @Override
    public void increaseInventory(String singleId, Integer num) {

    }


}