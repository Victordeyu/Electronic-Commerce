package com.ecommy.demo.Common.Convert;

import com.ecommy.demo.Common.DTO.OrderDTO;
import com.ecommy.demo.Common.Form.OrderForm;
import org.springframework.beans.BeanUtils;

public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm){
        OrderDTO orderDTO=new OrderDTO();

        BeanUtils.copyProperties(orderForm, orderDTO);
        return orderDTO;
    }

}
