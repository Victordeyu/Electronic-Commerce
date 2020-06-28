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
@Table(name="phone_product")
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value="Computer",description = "手机")
public class PhoneProduct {

    @Id
//    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(length=32,nullable = false,
            name = "sin_id")//, columnDefinition = "varchar(10) AUTO_INCREMENT comment '手机单品的ID'")
    @ApiModelProperty("手机单品的ID")
    private String singleId;

    @Column(length=32,nullable = false,
            name = "pro_id")//columnDefinition = "varchar(8) comment '商品ID'")
    @ApiModelProperty("商品序号")
    private String productId;

    @ApiModelProperty("商品品牌")
    @Column(name = "pro_brand")//columnDefinition = "varchar(255) comment '商品品牌'")
    private String brand;

    @ApiModelProperty("单品颜色")
    @Column(name = "sin_color")//columnDefinition = "varchar(255) comment '商品颜色'")
    private String color;

    @ApiModelProperty("单品内存大小")
    @Column(name = "sin_disk")//columnDefinition = "varchar(255) comment '商品内存大小'")
    private String disk;

    @ApiModelProperty("单品运行内存大小")
    @Column(name = "sin_memory")//columnDefinition = "varchar(255) comment '商品运行内存大小'")
    private String memory;

    @ApiModelProperty("单品CPU型号")
    @Column(name = "sin_cpu")//columnDefinition = "varchar(255) comment '商品CPU型号'")
    private String cpu;

    @ApiModelProperty("单品库存")
    @Column(name = "sin_inventory")// columnDefinition = "int comment '商品库存'")
    private int inventory;

    @ApiModelProperty("单品价格")
    @Column(name = "sin_price")//columnDefinition = "int comment '商品价格'")
    private int price;

    @ApiModelProperty("商品所属商家")
    private String sellerId;

    @ApiModelProperty("图片URL")
    @Column(name = "sin_url")
    private String url;

    @ApiModelProperty("单品类别")
    @Column(name = "cateGory")
    private int cateGory=2;

    @ApiModelProperty("销量")
    @Column(name="sales")
    private int sales;

    @CreatedDate
    @Column(name = "ord_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name="update_time")
    private Date updateTime;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
//            optional=false)
//    @JoinColumn(name="Product_ProductID")
//    private Product product;
}
