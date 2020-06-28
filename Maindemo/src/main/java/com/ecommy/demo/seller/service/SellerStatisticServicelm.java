package com.ecommy.demo.seller.service;

import com.ecommy.demo.Common.DataObject.ComputerProduct;
import com.ecommy.demo.Common.DataObject.PhoneProduct;
import com.ecommy.demo.Common.DataObject.Product;
import com.ecommy.demo.Common.VO.ComputerVO;
import com.ecommy.demo.Common.VO.PhoneVO;
import com.ecommy.demo.Common.VO.ProductVO;
import com.ecommy.demo.Product.Service.ComputerService;
import com.ecommy.demo.Product.Service.PhoneService;
import com.ecommy.demo.Product.Service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class SellerStatisticServicelm implements SellerStatisticService{

    @Autowired
    private ProductService productService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private ComputerService computerService;

    @Override
    public List<ProductVO> productOrderBySale(String sellerAccount) {
        List<Product> productList= productService.findBySellerId(sellerAccount);
        List<ProductVO>productVOList = new ArrayList<>();
        for(Product product:productList){
            ProductVO productVO=new ProductVO();
            BeanUtils.copyProperties(product,productVO);
            productVOList.add(productVO);
        }
        Collections.sort(productVOList);
        return productVOList;
    }

    @Override
    public List<PhoneVO> phoneOrderBySale(String sellerAccount) {
        List<PhoneProduct> phoneList=phoneService.findBySellerId(sellerAccount);
        List<PhoneVO> phoneVOList=new ArrayList<>();
        for(PhoneProduct phoneProduct:phoneList){
            PhoneVO phoneVO=new PhoneVO();
            BeanUtils.copyProperties(phoneProduct,phoneVO);
            phoneVOList.add(phoneVO);
        }
        Collections.sort(phoneVOList);
        return phoneVOList;
    }

    @Override
    public List<ComputerVO> computerOrderBySale(String sellerAccount) {
        List<ComputerProduct> computerList=computerService.findBySellerId(sellerAccount);
        List<ComputerVO> computerVOList=new ArrayList<>();
        for(ComputerProduct computerProduct:computerList){
            ComputerVO computerVO=new ComputerVO();
            BeanUtils.copyProperties(computerProduct,computerVO);
            computerVOList.add(computerVO);
        }
        Collections.sort(computerVOList);
        return computerVOList;
    }
}
