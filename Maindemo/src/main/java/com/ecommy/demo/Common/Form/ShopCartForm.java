package com.ecommy.demo.Common.Form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value="ShopCart",description = "购物车")
public class ShopCartForm {
    @ApiModelProperty("用户Id")
    @NotNull
    private String openId;

    @ApiModelProperty("单品Id")
    @NotNull
    private String singleId;

    @ApiModelProperty("种类")
    @NotNull
    private int cateGory;

    @ApiModelProperty("数量")
    @NotNull
    private Integer count;

    @ApiModelProperty("检查")
    private boolean checked=false;

}
