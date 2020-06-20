package com.ecommy.demo.Common.Form;

import com.ecommy.demo.Common.Enums.OrderStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
@ApiModel(value = "OrderForm", description = "订单")
public class OrderForm {
    @ApiModelProperty("订单id")
    private String orderId;

    /**
     * 缺买家和卖家的openid
     */
    @ApiModelProperty("卖家的openid")
    private String sellerOpenId;

    @ApiModelProperty("买家的openid")
    private String buyerOpenId;

//    @ApiModelProperty("单品id")
//    @Column(name = "sin_id", columnDefinition = "bigint comment '单品id'")
//    private String singleId;

    @ApiModelProperty("订单状态")
    private Integer orderStatus= OrderStatusEnum.NORMAL.getCode();

    @ApiModelProperty("订单价格")
    private Integer orderAccount;

    private Date createTime;

}
