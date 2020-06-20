package com.ecommy.demo.Product.Service;

import com.ecommy.demo.Common.DataObject.CateGory;

public interface SingleProductService {

    /**
     * 查找单品
     */
    <T> T findone(String SingleId);


    /**
     * 新增
     */
    void save(Object newSingle);



    /**
     * 删除
     */
    void delete(String singleId);

    /**
     * 库存操作
     */
    void increaseInventory(String singleId,Integer num);
}
