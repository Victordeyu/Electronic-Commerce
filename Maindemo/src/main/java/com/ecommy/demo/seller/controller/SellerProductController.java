package com.ecommy.demo.seller.controller;


import com.ecommy.demo.Common.DataObject.ComputerProduct;
import com.ecommy.demo.Common.DataObject.PhoneProduct;
import com.ecommy.demo.Common.DataObject.Product;
import com.ecommy.demo.Common.Enums.ProductStatusEnum;
import com.ecommy.demo.Common.Enums.ResultVOEnum;
import com.ecommy.demo.Common.Exception.SellException;
import com.ecommy.demo.Common.Form.ComputerForm;
import com.ecommy.demo.Common.Form.PhoneForm;
import com.ecommy.demo.Common.Form.ProductForm;
import com.ecommy.demo.Common.Unit.Key;
import com.ecommy.demo.Common.VO.ProductVO;
import com.ecommy.demo.Common.VO.ResultVO;
import com.ecommy.demo.Product.Service.ComputerService;
import com.ecommy.demo.Product.Service.PhoneService;
import com.ecommy.demo.Product.Service.ProductService;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/seller/product")
@Api("卖家商品接口")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ComputerService computerService;

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/list")
    @ApiOperation(value = "查询某一状态的产品", notes = "1:上架 0:下架", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<ProductVO> list(@RequestParam(value = "type", defaultValue = "1") Integer status) {
        List<Product> productList;
        List<ProductVO> productVOList=new ArrayList();

        if (status == 0) {
            productList = productService.findOffAll();
        } else {
            productList = productService.findUpAll();
        }

        for (Product product : productList) {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            productVOList.add(productVO);
        }

        return ResultVOEnum.success(productVOList);
    }

    @GetMapping("/onsale")
    @ApiOperation(value = "上架", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO onSale(@RequestParam("productId") String productId) {
        Product product = productService.OnSale(productId);
        return ResultVOEnum.success(product);
    }

    @GetMapping("/offsale")
    @ApiOperation(value = "下架", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO offSale(@RequestParam("productId") String productId) {
        Product product = productService.OffSale(productId);
        return ResultVOEnum.success(product);
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO delete(@ApiParam("商品id") @RequestParam("productId") String id) {
        productService.Delete(id);
        return ResultVOEnum.success();
    }

    @PostMapping("/save")
    @ApiOperation(value="新增",notes="新增商品", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO save(@Valid @RequestBody ProductForm productForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            //throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        Product product = new Product();

        if (!StringUtils.isEmpty(productForm.getProductId())) {
            //product = productService.findOne(productForm.getProductId());
        } else {
            productForm.setProductId(Key.getKey());
        }
        productForm.setProductId(Key.getKey());
//        product.setProductID(productForm.getProductId());
//
        BeanUtils.copyProperties(productForm,product);
        product.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productService.Save(product);
        return ResultVOEnum.success();
    }

    @PostMapping("/save/computer")
    @ApiOperation(value="新增电脑",notes="新增电脑", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO saveComputer(@Valid @RequestBody ComputerForm computerForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            //throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        ComputerProduct computerProduct=new ComputerProduct();

        if (!StringUtils.isEmpty(computerForm.getProductId())) {
            //product = productService.findOne(productForm.getProductId());
        } else {
             computerForm.setProductId(Key.getKey());
        }
        computerForm.setSingleId(Key.getKey());
//        product.setProductID(productForm.getProductId());
//
        BeanUtils.copyProperties(computerForm,computerProduct);
        computerService.save(computerProduct);
        return ResultVOEnum.success();
    }

    @PostMapping("/save/phone")
    @ApiOperation(value="新增手机",notes="新增手机", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO savePhone(@Valid @RequestBody PhoneForm phoneForm,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            //throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        PhoneProduct phoneProduct=new PhoneProduct();
        if (!StringUtils.isEmpty(phoneForm.getProductId())) {
            //product = productService.findOne(productForm.getProductId());
        } else {
            phoneForm.setProductId(Key.getKey());
        }
        phoneForm.setSingleId(Key.getKey());
//        product.setProductID(productForm.getProductId());
//
        BeanUtils.copyProperties(phoneForm,phoneProduct);
        phoneService.Save(phoneProduct);
        return ResultVOEnum.success();
    }
}



