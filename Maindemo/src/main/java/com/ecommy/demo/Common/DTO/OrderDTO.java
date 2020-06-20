package com.ecommy.demo.Common.DTO;

import com.ecommy.demo.Common.DataObject.OrderDetail;
import com.ecommy.demo.Common.Enums.CodeEnum;
import com.ecommy.demo.Common.Enums.EnumUtil;
import com.ecommy.demo.Common.Enums.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value="OrderDTO",description = "订单")
public class OrderDTO {

    private String OrderId;

    private String BuyerOpenId;

    private String BuyerAddress;

    private String BuyerNumber;

    private int OrderAccount;

    private Integer OrderStatus;

    private Date Time;

    List<OrderDetail> OrderDetailList;

    public OrderDTO(){}

    @JsonIgnore
    public OrderStatusEnum GetOrderStatusEnum(){
        return EnumUtil.GetByCode(OrderStatus, OrderStatusEnum.class);
    }


}
