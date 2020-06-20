package com.ecommy.demo.Common.Enums;

import lombok.Getter;

@Getter
public enum accountaddressEnum {
    PARAM_ERROR(1, "参数不正确");

    private Integer code;

    private String message;

    accountaddressEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
