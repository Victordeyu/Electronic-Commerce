package com.ecommy.demo.Common.Form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

@Data
@ApiModel(value="Phone",description = "手机")
public class PhoneForm {
    @ApiModelProperty("手机单品的ID")
    private String singleId;

    @ApiModelProperty("商品序号")
    private String productId;

    @ApiModelProperty("商品品牌")
    private String brand;

    @ApiModelProperty("单品颜色")
    private String color;

    @ApiModelProperty("单品内存大小")
    private String disk;

    @ApiModelProperty("单品运行内存大小")
    private String memory;

    @ApiModelProperty("单品CPU型号")
    private String cpu;

    @ApiModelProperty("单品库存")
    private int inventory;

    @ApiModelProperty("单品价格")
    private int price;

    @ApiModelProperty("图片URL")
    private String url;

}
