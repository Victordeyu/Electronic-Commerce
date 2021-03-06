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
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="order_detail")
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value="OrderDetail",description = "订单详情")
public class OrderDetail {

    @Id
    @ApiModelProperty("订单详情id")
    @Column(name="order_detail_id",length=32,nullable = false)//columnDefinition = "varchar(32) AUTO_INCREMENT comment '商品ID'")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderDetailId;

    @Column(name="order_id",length = 32)//columnDefinition = "varchar(32) comment '订单id'")
    @ApiModelProperty("订单id")
    private String orderId;

    @ApiModelProperty("单品id")
    @Column(name="sin_id",length = 32)//columnDefinition = "varchar(10) comment '单品id'")
    private String singleId;

    @ApiModelProperty("商品名")
    @Column(name="pro_name")//columnDefinition = "varchar(255) comment '商品名'")
    private String productName;

    @ApiModelProperty("数量")
    @Column(name="sin_num")//columnDefinition = "int comment '数量'")
    private Integer count;

    @ApiModelProperty("单价")
    @Column(name="sin_price")//columnDefinition = "int comment '单价'")
    private int productPrice;

    @ApiModelProperty("种类")
    @Column(name="sin_category")//,columnDefinition = "int comment '种类'")
    private int cateGory;

    @ApiModelProperty("小计价格")
    @Column(name="det_price")//,columnDefinition = "int comment '小计价格'")
    private int accountPrice;

    @ApiModelProperty("订单状态")
    @Column(name = "ord_status")//columnDefinition = "int comment '订单状态'")
    private Integer orderStatus= OrderStatusEnum.NORMAL.getCode();

    @CreatedDate
    @Column(name = "ord_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name="update_time")
    private Date updateTime;

}
