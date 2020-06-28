package com.ecommy.demo.Common.Enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),

    FAIL(1, "失败"),

    MINI_NOTEXIST(1, "小程序信息不存在"),

    NEED_LOGIN(1, "用户未登录"),

    PARAM_ERROR(1, "参数不正确"),

    SYSTEM_ERROR(1, "系统错误"),

    SESSION_EXPIRED(1, "请重新登陆"),

    LOGIN_PASSWORD_ERROR(1, "密码错误"),

    LOGIN_USER_NOT_EXIST(1, "用户不存在"),

    PRODUCT_NOT_EXIST(1, "商品不存在"),

    PRODUCT_STOCK_ERROR(1, "商品库存不正确"),

    ORDER_NOT_EXIST(1, "订单不存在"),

    ORDERDETAIL_NOT_EXIST(1, "订单详情不存在"),

    ORDER_STATUS_ERROR(1, "订单状态不正确"),

    ORDER_UPDATE_FAIL(1, "订单更新失败"),

    ORDER_DETAIL_EMPTY(1, "订单详情为空"),

    ORDER_PAY_STATUS_ERROR(1, "订单支付状态不正确"),

    CART_EMPTY(1, "购物车为空"),

    ORDER_OWNER_ERROR(1, "该订单不属于当前用户"),

    WECHAT_MP_ERROR(1, "微信公众账号方面错误"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(1, "微信支付异步通知金额校验不通过"),

    ORDER_CANCEL_SUCCESS(0, "订单取消成功"),

    ORDER_FINISH_SUCCESS(0, "订单完结成功"),

    PRODUCT_STATUS_ERROR(1, "商品状态不正确"),

    LOGIN_FAIL(1, "登录失败, 登录信息不正确"),

    LOGOUT_SUCCESS(0, "登出成功"),

    CATEOGRY_NOT_EXIST(1, "类别不存在"),

    USER_NOT_EXIST(1, "用户不存在"),

    USER_EXIST(1, "用户已存在"),

    IMAGE_NOT_EXIST(1, "请选择图片"),

    IMAGE_UPLOAD_FAILED(1, "上传失败"),

    ACTIVITY_NOT_EXIST(1, "商品活动不存在"),

    ADV_NOT_EXTIST(1, "广告不存在"),

    DEL_CATEGORY_ERROR(1, "该类目下有绑定商品，无法删除"),

    DEL_ACTIVITY_ERROR(1, "该活动下有绑定商品，无法删除")

    ;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
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
