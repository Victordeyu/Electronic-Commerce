package com.ecommy.demo.Common.DTO;

import com.ecommy.demo.Common.DataObject.OrderDetail;
import com.ecommy.demo.Common.Enums.CodeEnum;
import com.ecommy.demo.Common.Enums.EnumUtil;
import com.ecommy.demo.Common.Enums.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value="OrderDTO",description = "订单")
public class OrderDTO {

    private String orderId;

    private String buyerOpenId;

    private String sellerAccount;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddressId;

    private String buyerAddress;

    private int orderAccount;

    private Integer orderStatus;

    private Date createTime;

    List<OrderDetail> OrderDetailList;

    public OrderDTO(){}

    @JsonIgnore
    public OrderStatusEnum GetOrderStatusEnum(){
        return EnumUtil.GetByCode(orderStatus, OrderStatusEnum.class);
    }


}
