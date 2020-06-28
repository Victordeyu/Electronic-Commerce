package com.ecommy.demo.Buyer.controller;

import com.ecommy.demo.Buyer.service.BuyerShopCartService;
import com.ecommy.demo.Common.DataObject.ComputerProduct;
import com.ecommy.demo.Common.DataObject.PhoneProduct;
import com.ecommy.demo.Common.DataObject.Product;
import com.ecommy.demo.Common.DataObject.ShopCart;
import com.ecommy.demo.Common.Enums.ResultVOEnum;
import com.ecommy.demo.Common.Form.ShopCartForm;
import com.ecommy.demo.Common.Unit.Key;
import com.ecommy.demo.Common.VO.ComputerVO;
import com.ecommy.demo.Common.VO.PhoneVO;
import com.ecommy.demo.Common.VO.ProductVO;
import com.ecommy.demo.Common.VO.ResultVO;
import com.ecommy.demo.Product.Service.ComputerService;
import com.ecommy.demo.Product.Service.PhoneService;
import com.ecommy.demo.Product.Service.SingleProductService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer/shopcart")
@Api("买家购物车接口")
@Slf4j
public class BuyerShopCartController {

    @Autowired
    BuyerShopCartService buyerShopCartService;

    @Autowired
    ComputerService computerService;

    @Autowired
    PhoneService phoneService;

    @GetMapping("/one")
    @ApiOperation(value = "查询某一用户的购物车列表",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<ProductVO> list(@RequestParam("openId")@ApiParam("用户Id") String openId) {
        List<ShopCart>shopCartList=buyerShopCartService.findByOpenId(openId);
//        List<PhoneVO>phoneVOList=
//        List<ComputerVO>computerVOList =new List<ComputerVO>();
//        shopCartList=buyerShopCartService.findByOpenId(openId);
//
//        for(ShopCart shopCart:shopCartList)
//        {
//            switch (shopCart.getCateGory())
//            {
//                case 1:
//                    ComputerProduct computerProduct=computerService.findOne(shopCart.getSingleId());
//                    ComputerVO computerVO=new ComputerVO();
//                    BeanUtils.copyProperties(computerProduct,computerVO);
//                    computerVO.setNum(shopCart.getCount());
//                    computerVOList.add(computerVO);
//                    break;
//                case 2:
//                    PhoneProduct phoneProduct=phoneService.findOne(shopCart.getSingleId());
//                    PhoneVO phoneVO=new PhoneVO();
//                    BeanUtils.copyProperties(phoneProduct,phoneVO);
//                    phoneVO.setNum(shopCart.getCount());
//
//                    break;
//            }
//        }
        return ResultVOEnum.success(shopCartList);
    }

//    @GetMapping("/add")
//    @ApiOperation(value = "向某个用户购物车中增加单品",produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResultVO<ProductVO> add(@RequestParam("openId")@ApiParam("用户Id") String openId,
//                                    @RequestMapping("singleId")@ApiParam("单品Id") String singleId) {
    @PostMapping("/add")
    @ApiOperation(value = "向某个用户购物车中增加单品",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO add(@Valid ShopCartForm shopCartForm){
        ShopCart shopCart = new ShopCart();

        if (buyerShopCartService.findByOpenIdAndSingleId(shopCartForm.getOpenId(),shopCartForm.getSingleId())!=null) {
            shopCart = buyerShopCartService.findByOpenIdAndSingleId(shopCartForm.getOpenId(),shopCartForm.getSingleId());
            BeanUtils.copyProperties(shopCartForm,shopCart);
        } else {
            BeanUtils.copyProperties(shopCartForm,shopCart);
            shopCart.setShopCartId(Key.getKey());
        }
        buyerShopCartService.save(shopCart);
        return ResultVOEnum.success();
    }

    @GetMapping("/delete")
    @Transactional
    @ApiOperation(value="删除某单品",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO delete(@RequestParam("openId")@ApiParam("用户Id")String openId,
                           @RequestParam("singleId")@ApiParam("单品Id")String singleId){
        buyerShopCartService.deleteBySingleIdAndOpenId(openId,singleId);
        return ResultVOEnum.success();
    }

    @GetMapping("/deleteall")
    @Transactional
    @ApiOperation(value="清空购物车",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO deleteAll(@RequestParam("openId")@ApiParam("用户Id")String openId){
        buyerShopCartService.deleteByOpenId(openId);
        return ResultVOEnum.success();
    }
}
