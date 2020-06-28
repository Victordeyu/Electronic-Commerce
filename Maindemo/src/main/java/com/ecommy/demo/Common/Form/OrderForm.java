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
    @ApiModelProperty("卖家的账号")
    private String sellerAccount;

    @ApiModelProperty("买家的openid")
    private String buyerOpenId;

//    @ApiModelProperty("单品id")
//    @Column(name = "sin_id", columnDefinition = "bigint comment '单品id'")
//    private String singleId;

    @ApiModelProperty("姓名")
    private String buyerName;

    @ApiModelProperty("手机号")
    private String buyerPhone;

    @ApiModelProperty("地址id")
    private String buyerAddressId;

    @ApiModelProperty("地址信息")
    private String buyerAddress;

    @ApiModelProperty("订单状态")
    private Integer orderStatus= OrderStatusEnum.NORMAL.getCode();

    @ApiModelProperty("订单价格")
    private int orderAccount;

    @ApiModelProperty("单品列表")
    private String items;

    private Date createTime;

}
