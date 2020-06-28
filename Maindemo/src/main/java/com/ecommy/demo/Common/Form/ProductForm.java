package com.ecommy.demo.Common.Form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value="Product",description = "商品")
public class ProductForm {

    @ApiModelProperty("商品序号")
    @NotNull
    private String productId;

    @ApiModelProperty("商品名字")
    @NotNull
    private String productName;

    @ApiModelProperty("商品描述")
    private String productDesc;

    @ApiModelProperty("商品上下架")
    private Integer productStatus; //= ProductStatusEnum.UP.getCode();

    @ApiModelProperty("商品种类")
    private int cateGory;

    @ApiModelProperty("商品所属商家")
    private String sellerId;

    @ApiModelProperty("URL")
    private String url;

}
