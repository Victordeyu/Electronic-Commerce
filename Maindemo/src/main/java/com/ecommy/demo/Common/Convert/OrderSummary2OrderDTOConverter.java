package com.ecommy.demo.Common.Convert;

import com.ecommy.demo.Common.DTO.OrderDTO;
import com.ecommy.demo.Common.DataObject.OrderSummary;
import com.ecommy.demo.Common.Enums.EnumUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderSummary2OrderDTOConverter {

    public static OrderDTO convert(OrderSummary orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderSummary> orderMasterList) {
        return orderMasterList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
