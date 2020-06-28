package com.ecommy.demo.Product.Service;

import com.ecommy.demo.Common.DataObject.ComputerProduct;
import com.ecommy.demo.Product.Repository.ComputerRepository;
import com.ecommy.demo.Product.Repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<ComputerProduct> findBySellerId(String sellerId) {
        return computerRepository.findBySellerId(sellerId);
    }

    @Override
    public void setSales(String singleId, int sales) {
        ComputerProduct computerProduct=computerRepository.findBySingleId(singleId);
        computerProduct.setSales(sales);
        save(computerProduct);
    }

    @Override
    public void increaseSales(String singleId, int change) {
        ComputerProduct computerProduct=findOne(singleId);
        setSales(singleId,change+computerProduct.getSales());
    }


    @Override
    public void save(ComputerProduct newComputer) {
        computerRepository.save(newComputer);
    }

    @Override
    public List<ComputerProduct> findByProductId(String productId) {
        return computerRepository.findByProductId(productId);
    }

    @Override
    public void delete(String singleId) {
                computerRepository.deleteBySingleId(singleId);
    }

    @Override
    public void deleteByProductId(String productId) {
        computerRepository.deleteByProductId(productId);
    }

    @Override
    public void increaseInventory(String singleId, Integer num) {
        ComputerProduct computerProduct=computerRepository.findBySingleId(singleId);
        computerProduct.setInventory(computerProduct.getInventory()+num);
        computerRepository.save(computerProduct);
    }


}