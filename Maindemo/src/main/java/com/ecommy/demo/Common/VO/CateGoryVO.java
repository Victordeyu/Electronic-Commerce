package com.ecommy.demo.Common.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value="ProductList")
public class CateGoryVO {

    @ApiModelProperty("种类序号")
    private int cateGory;

    @ApiModelProperty("商品列表")
    private List<ProductVO> productVOList;
}
