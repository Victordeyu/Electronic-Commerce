package com.ecommy.demo.Common.Enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    DOWN(0,"下架"),
    UP(1,"上架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
