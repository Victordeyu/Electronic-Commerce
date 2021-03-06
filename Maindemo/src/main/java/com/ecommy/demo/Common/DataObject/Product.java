package com.ecommy.demo.Common.DataObject;


import com.ecommy.demo.Common.Enums.ProductStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value="Product",description = "商品")
@Table(name="product")
public class Product {

    @Id
    @Column(name="pro_id",length=32)
//    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @ApiModelProperty("商品序号")
    private String productId;

    @ApiModelProperty("商品名字")
    @Column(name = "pro_name")
    private String productName;

    @ApiModelProperty("商品描述")
    @Column(name = "pro_des")
    private String productDesc;

    @ApiModelProperty("商品上下架")
    @Column(name = "pro_status")
    private Integer productStatus; //= ProductStatusEnum.UP.getCode();

    @ApiModelProperty("商品种类")
    @Column(name = "pro_category")
    private int cateGory;

    @ApiModelProperty("图片URL")
    @Column(name = "sin_url")
    private String url;

    @ApiModelProperty("所属商家的Id")
    @Column(name = "seller_id")
    private String sellerId;

    @ApiModelProperty("销量")
    @Column(name="sales")
    private int sales;

    @CreatedDate
    @Column(name = "ord_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name="update_time")
    private Date updateTime;

//    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
//    @JoinColumn(name="ProductID")
//    private List<PhoneProduct> PhoneList=new ArrayList<PhoneProduct>();
//
//    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
//    @JoinColumn(name="ProductID")
//    private List<ComputerProduct> computerList=new ArrayList<ComputerProduct>();



}
