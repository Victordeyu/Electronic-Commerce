package com.ecommy.demo.Common.Convert;

import com.google.gson.Gson;
import com.ecommy.demo.Common.DTO.OrderDTO;
import com.ecommy.demo.Common.DataObject.OrderDetail;
import com.ecommy.demo.Common.Form.OrderForm;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson=new Gson();
        OrderDTO orderDTO=new OrderDTO();
        List<OrderDetail>orderDetailList;

        BeanUtils.copyProperties(orderForm, orderDTO);

        orderDetailList = gson.fromJson(orderForm.getItems(),
                new TypeToken<List<OrderDetail>>() {
                }.getType());

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

}
