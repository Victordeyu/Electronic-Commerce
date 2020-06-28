package com.ecommy.demo.Common.DataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="shopinfo")
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class ShopInfo {
    @Id
    @Column(name="shop_id",length=32)
    private String shopId;//primary

    //店铺名称
    @Column(name="shop_name")
    private String shopName;

    //openid
    @Column(name="open_id")
    private String openId;

    //shop-introduction
    @Column(name="shop_intro")
    private String intro;

    //simple-address
    @Column(name="shop_addr")
    private String shopAddress;

    //detail-address
    @Column(name="shop_daddr")
    private String shopAddressDetail;

    //open 1 close 0
    @Column(name="shop_status")
    private Integer shopStatus;

    @CreatedDate
    @Column(name = "ord_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name="update_time")
    private Date updateTime;
}

