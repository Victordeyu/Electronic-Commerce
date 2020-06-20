package com.ecommy.demo.Common.VO;

import com.ecommy.demo.Common.Enums.ProductStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Product",description = "商品")
public class ProductVO {

    @ApiModelProperty("商品序号")
    private String productID;

    @ApiModelProperty("商品名字")
    private String productName;

    @ApiModelProperty("商品描述")
    private String productDesc;

    @ApiModelProperty("商品上下架")
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    @ApiModelProperty("商品种类")
    private int cateGory;
}
