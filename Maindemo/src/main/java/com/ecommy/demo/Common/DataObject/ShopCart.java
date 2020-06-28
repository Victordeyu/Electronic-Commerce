package com.ecommy.demo.Common.DataObject;

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
@Table(name="shop_cart")
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value="ShopCart",description = "购物车")
public class ShopCart {
    @Id
    @ApiModelProperty("购物车详情Id")
    @Column(name="shop_cart_id",length=32)
    private String shopCartId;

    @ApiModelProperty("用户Id")
    @Column(name="user_openid")
    private String openId;

    @ApiModelProperty("单品Id")
    @Column(name="single_id")
    private String singleId;

    @ApiModelProperty("种类")
    @Column(name="sin_category")//,columnDefinition = "int comment '种类'")
    private int cateGory;

    @ApiModelProperty("数量")
    @Column(name="sin_num")//columnDefinition = "int comment '数量'")
    private Integer count;

    @ApiModelProperty("检查")
    @Column(name="checked", columnDefinition = "bit(1)")//columnDefinition = "int comment '数量'")
    private boolean checked;

    @CreatedDate
    @Column(name = "ord_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name="update_time")
    private Date updateTime;

}
