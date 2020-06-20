package com.ecommy.demo.Common.DataObject;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name="receiver")
@DynamicUpdate
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

    //创建时间
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date createTime;

    //更新时间
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
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
