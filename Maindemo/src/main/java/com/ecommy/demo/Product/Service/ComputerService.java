package com.ecommy.demo.Product.Service;

import com.ecommy.demo.Common.DataObject.ComputerProduct;

public interface ComputerService {

    /**
     * 查找单品
     */
    ComputerProduct findOne(String SingleId);


    /**
     * 新增
     */
    void save(ComputerProduct newSingle);



    /**
     * 删除
     */
    void delete(String singleId);

    /**
     * 库存操作
     */
    void increaseInventory(String singleId,Integer num);

}
