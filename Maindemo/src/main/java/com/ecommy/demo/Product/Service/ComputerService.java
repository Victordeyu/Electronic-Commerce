package com.ecommy.demo.Product.Service;

import com.ecommy.demo.Common.DataObject.ComputerProduct;

import java.util.List;

public interface ComputerService {

    /**
     * 查找单品
     */
    ComputerProduct findOne(String SingleId);

    List<ComputerProduct> findBySellerId(String sellerId);

    /**
     * 更改销量
     */
    void setSales(String singleId,int sales);

    void increaseSales(String singleId,int change);

    /**
     * 新增
     */
    void save(ComputerProduct newSingle);

    List<ComputerProduct> findByProductId(String productId);

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
