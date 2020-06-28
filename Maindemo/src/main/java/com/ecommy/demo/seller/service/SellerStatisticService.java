package com.ecommy.demo.seller.service;

import com.ecommy.demo.Common.DataObject.ComputerProduct;
import com.ecommy.demo.Common.DataObject.PhoneProduct;
import com.ecommy.demo.Common.DataObject.Product;
import com.ecommy.demo.Common.VO.ComputerVO;
import com.ecommy.demo.Common.VO.PhoneVO;
import com.ecommy.demo.Common.VO.ProductVO;

import java.util.List;

public interface SellerStatisticService {
    List<ProductVO> productOrderBySale(String sellerAccount);

    List<PhoneVO> phoneOrderBySale(String sellerAccount);

    List<ComputerVO> computerOrderBySale(String sellerAccount);
}
