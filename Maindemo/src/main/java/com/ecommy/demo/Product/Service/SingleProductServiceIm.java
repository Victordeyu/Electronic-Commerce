//package com.ecommy.demo.Product.Service;
//
//import com.ecommy.demo.Common.DataObject.ComputerProduct;
//import com.ecommy.demo.Common.DataObject.PhoneProduct;
//import com.ecommy.demo.Product.Repository.ComputerRepository;
//import com.ecommy.demo.Product.Repository.PhoneRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SingleProductServiceIm implements SingleProductService{
//
//    ComputerRepository computerRepository;
//    PhoneRepository phoneRepository;
//
//    @Override
//    public <T> T Findone(String SingleId, Integer CateGoryId) {
//
//        switch(CateGoryId)
//        {
//            case 1:
//                T c =(T)computerRepository.findBySingleId(SingleId);
//                return c;
//            case 2:
//                T p =(T)phoneRepository.findBySingleId(SingleId);
//                return p;
//        }
//        return null;
//    }
//
//    @Override
//    public <T> void save(T newSingle) {
//
//    }
//
//    @Override
//    public void delete(String singleId, Integer CateGoryId) {
//        switch(CateGoryId)
//        {
//            case 1:
//                computerRepository.deleteBySingleId(singleId);
//            case 2:
//                phoneRepository.deleteBySingleId(singleId);
//        }
//    }
//
//    @Override
//    public void increaseInventory(String singleId, Integer CateGoryId, Integer num) {
//
//    }
//
//
//}
