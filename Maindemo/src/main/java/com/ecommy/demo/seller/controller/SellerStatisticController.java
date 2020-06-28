package com.ecommy.demo.seller.controller;

import com.ecommy.demo.Common.Enums.ResultVOEnum;
import com.ecommy.demo.Common.VO.ProductVO;
import com.ecommy.demo.Common.VO.ResultVO;
import com.ecommy.demo.seller.service.SellerStatisticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
@Api("商家统计接口")
public class SellerStatisticController {

    @Autowired
    private SellerStatisticService sellerStatisticService;

    @GetMapping("/product")
    @ApiOperation(value = "查询某一商家的商品销量统计",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<ProductVO> productList(@RequestParam("sellerId") String seller_id){
        return ResultVOEnum.success(sellerStatisticService.productOrderBySale(seller_id));
    }

    @GetMapping("/phone")
    @ApiOperation(value = "查询某一商家的手机单品销量统计",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<ProductVO> phoneList(@RequestParam("sellerId") String seller_id){
        return ResultVOEnum.success(sellerStatisticService.phoneOrderBySale(seller_id));
    }

    @GetMapping("/computer")
    @ApiOperation(value = "查询某一商家的商品销量统计",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<ProductVO> computerList(@RequestParam("sellerId") String seller_id){
        return ResultVOEnum.success(sellerStatisticService.computerOrderBySale(seller_id));
    }
}
