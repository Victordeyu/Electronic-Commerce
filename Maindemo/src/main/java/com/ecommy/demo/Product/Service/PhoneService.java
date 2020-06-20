package com.ecommy.demo.Product.Service;

import com.ecommy.demo.Common.DataObject.ComputerProduct;
import com.ecommy.demo.Common.DataObject.PhoneProduct;

public interface PhoneService {
    /**
     * 查找单品
     */
    PhoneProduct findOne(String SingleId);


    /**
     * 新增
     */
    void Save(PhoneProduct newSingle);



    /**
     * 删除
     */
    void delete(String singleId);

    /**
     * 库存操作
     */
    void increaseInventory(String singleId,Integer num);
}
