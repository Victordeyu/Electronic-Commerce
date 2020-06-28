package com.ecommy.demo.Product.Service;

import com.ecommy.demo.Common.DataObject.ComputerProduct;
import com.ecommy.demo.Common.DataObject.PhoneProduct;
import com.ecommy.demo.Common.DataObject.Product;

import java.util.List;

public interface PhoneService {
    /**
     * 查找单品
     */
    PhoneProduct findOne(String SingleId);

    List<PhoneProduct> findBySellerId(String sellerId);

    /**
     * 新增
     */
    void Save(PhoneProduct newSingle);


    List<PhoneProduct> findByProductId(String productId);

    /**
     * 设置销量
     */
    void setSales(String singleId,int sales);

    void increaseSales(String singleId,int change);


    /**
     * 删除
     */
    void delete(String singleId);

    void deleteByProductId(String productId);

    /**
     * 库存操作
     */
    void increaseInventory(String singleId,Integer num);
}
