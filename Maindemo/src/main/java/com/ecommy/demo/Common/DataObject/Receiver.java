package com.ecommy.demo.Common.DataObject;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name="receiver")
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value="receiver",description = "收货地址")
public class Receiver {
    @Column(length = 32)
    @Id
    private String recId;

    //openID
    @Column(length = 64)
    @NotNull
    @NotEmpty
    private String openid;

    //phone-number
    @Column(length = 12)
    @NotNull
    @NotEmpty
    private String phone;

    //name
    @Column(length = 32)
    @NotNull
    @NotEmpty
    private String name;

    //address
    @NotNull
    @Column(length = 256)
    @NotEmpty
    private String address;

    //detailed-address
    @NotNull
    @Column(length = 256)
    @NotEmpty
    private String detail;

    //default-number 1 or 0
    @Column(length = 1)
    @NotNull
    private int status;

    @CreatedDate
    @Column(name = "ord_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name="update_time")
    private Date updateTime;

    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public void setRecId(String recId) {
        this.recId = recId;
    }

    public String getOpenid() {
        return openid;
    }

    public String getRecId() {
        return recId;
    }
}
