package com.ecommy.demo.Common.DataObject;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.DateTimeException;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="seller_info")
@ApiModel(value="sellerInfo",description = "商家信息")
@DynamicUpdate
public class SellerInfo {

    //openid
    @Column(name="open_Id",length = 32)
    @ApiModelProperty("商家openid")
    private String openId;

    //account number
    @Id
    @Column(name="seller_account",length = 32)
    @ApiModelProperty("商家账号")
    private String sellerAccount;

    //account password
    @Column(name="seller_password")
    @ApiModelProperty("商家密码")
    private String sellerPassword;


    //name
    @Column(name="seller_name")
    @ApiModelProperty("商家名称")
    private String sellerName;

    //phone-number of owner
    @Column(name="seller_phone")
    @ApiModelProperty("商家手机号")
    private String sellerMobile;

    @CreatedDate
    @Column(name = "ord_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name="update_time")
    private Date updateTime;

}
