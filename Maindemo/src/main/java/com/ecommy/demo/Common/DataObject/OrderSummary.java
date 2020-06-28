package com.ecommy.demo.Common.DataObject;


import com.ecommy.demo.Common.Enums.OrderStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="order_summary")
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value="OrderSummary",description = "订单摘要")
public class OrderSummary {

    @Id
    @ApiModelProperty("订单id")
    @Column(name="order_id",length=32,nullable = false)//     columnDefinition = "varchar(32) AUTO_INCREMENT comment '商品ID'")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderId;


    @ApiModelProperty("卖家的账号")
    @Column(name = "seller_id")//columnDefinition = "varchar(255) comment '卖家的openid'")
    private String sellerAccount;

    @ApiModelProperty("姓名")
    @Column(length = 32)
    private String buyerName;

    @ApiModelProperty("手机号")
    @Column(name="buyer_phone")
    private String buyerPhone;

    @ApiModelProperty("地址id")
    @Column(name="address_id")
    private String buyerAddressId;

    @ApiModelProperty("地址信息")
    @Column(name="address")
    private String buyerAddress;

    @ApiModelProperty("买家的openid")
    @Column(name = "buyer_id")//columnDefinition = "varchar(255) comment '买家的openid'")
    private String buyerOpenId;

//    @ApiModelProperty("单品id")
//    @Column(name = "sin_id", columnDefinition = "bigint comment '单品id'")
//    private String singleId;

    @ApiModelProperty("订单状态")
    @Column(name = "ord_status")//columnDefinition = "int comment '订单状态'")
    private Integer orderStatus= OrderStatusEnum.NORMAL.getCode();

    @ApiModelProperty("订单价格")
    @Column(name = "ord_account")// columnDefinition = "int comment '订单价格'")
    private Integer orderAccount;

    @CreatedDate
    @Column(name = "ord_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name="update_time")
    private Date updateTime;

}
