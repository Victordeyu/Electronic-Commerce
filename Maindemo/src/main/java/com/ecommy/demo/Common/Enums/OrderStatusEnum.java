package com.ecommy.demo.Common.Enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum {
    NORMAL(0,"正常状态"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }


}
