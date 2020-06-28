package com.ecommy.demo.seller.controller;


import com.ecommy.demo.Common.DataObject.ComputerProduct;
import com.ecommy.demo.Common.DataObject.PhoneProduct;
import com.ecommy.demo.Common.DataObject.Product;
import com.ecommy.demo.Common.Enums.ProductStatusEnum;
import com.ecommy.demo.Common.Enums.ResultEnum;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
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
    public ResultVO<ProductVO> list(@ApiParam("商家id") @RequestParam("sellerId") String seller_id,
                                    @ApiParam("商品状态")@RequestParam(value = "type", defaultValue = "1") Integer status) {
        List<Product> productList;
        List<ProductVO> productVOList=new ArrayList();

        productList = productService.findBySellerIdAndProductStatus(seller_id, status);

        for (Product product : productList) {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            productVOList.add(productVO);
        }

        return ResultVOEnum.success(productVOList);
    }

    @GetMapping("/sellerid")
    @ApiOperation(value = "查询某一商家的商品",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<ProductVO> list(@RequestParam("sellerId") String seller_id){
        List<Product> productList;
        List<ProductVO> productVOList=new ArrayList();

        productList=productService.findBySellerId(seller_id);

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
    @Transactional
    @ApiOperation(value = "删除", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO delete(@ApiParam("商品id") @RequestParam("productId") String id) {
        Product product=productService.findOne(id);
        productService.Delete(id);
        switch (product.getCateGory()){
            case 1:
                computerService.deleteByProductId(id);
            case 2:
                phoneService.deleteByProductId(id);
        }

        return ResultVOEnum.success();
    }

    @GetMapping("/deleteSingle")
    @Transactional
    @ApiOperation(value = "删除", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO deleteSingle(@ApiParam("单品id") @RequestParam("singleId") String id,
                                 @ApiParam("种类") @RequestParam("cateGory") int cate) {
        switch(cate){
            case 1:
                computerService.delete(id);
                return ResultVOEnum.success();
            case 2:
                phoneService.delete(id);
                return ResultVOEnum.success();
        }
        return ResultVOEnum.error(1,"There is not this cateGory");
    }

    @PostMapping("/save")
    @ApiOperation(value="新增",notes="新增商品", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO save(@Valid @RequestBody ProductForm productForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        Product product = new Product();

        if (!StringUtils.isEmpty(productForm.getProductId())) {
            product = productService.findOne(productForm.getProductId());
        } else {
            productForm.setProductId(Key.getKey());
        }
//        productForm.setProductId(Key.getKey());
//        product.setProductID(productForm.getProductId());
//
        BeanUtils.copyProperties(productForm,product);
        product.setProductStatus(ProductStatusEnum.DOWN.getCode());
        product.setSales(0);
        productService.Save(product);
        return ResultVOEnum.success();
    }

    @PostMapping("/save/computer")
    @ApiOperation(value="新增电脑",notes="新增电脑", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO saveComputer(@Valid @RequestBody ComputerForm computerForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        ComputerProduct computerProduct=new ComputerProduct();

        if (computerForm.getSingleId()!=null) {
            computerProduct = computerService.findOne(computerForm.getSingleId());
        } else {
             computerForm.setProductId(computerForm.getProductId());
            computerForm.setSingleId(Key.getKey());
        }
//        computerForm.setSingleId(Key.getKey());
//        product.setProductID(productForm.getProductId());
//
        BeanUtils.copyProperties(computerForm,computerProduct);
        computerProduct.setSales(0);
        computerProduct.setPrice((int)computerProduct.getPrice()*100);
        computerService.save(computerProduct);
        return ResultVOEnum.success();
    }

    @PostMapping("/save/phone")
    @ApiOperation(value="新增手机",notes="新增手机", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO savePhone(@Valid @RequestBody PhoneForm phoneForm,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        PhoneProduct phoneProduct=new PhoneProduct();
        if (phoneForm.getSingleId()!=null) {
            phoneProduct = phoneService.findOne(phoneForm.getSingleId());
        } else {
            phoneForm.setProductId(phoneForm.getProductId());
            phoneForm.setSingleId(Key.getKey());
        }
//        phoneForm.setSingleId(Key.getKey());
//        product.setProductID(productForm.getProductId());
//
        BeanUtils.copyProperties(phoneForm,phoneProduct);
        phoneProduct.setSales(0);
        phoneProduct.setPrice(phoneProduct.getPrice()*100);
        phoneService.Save(phoneProduct);
        return ResultVOEnum.success();
    }

    @GetMapping("/one")
    @ApiOperation(value = "查询单一商品详情", notes = "By ProductId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<ProductVO> findOne(@RequestParam("productId")@Param("商品id") String productId) {
        Product product = productService.findOne(productId);
        return ResultVOEnum.success(product);
    }

    @GetMapping("/singleAllByProduct")
    @ApiOperation(value="查询某一商品所有单品",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO findByProductId(@ApiParam("商品Id") @RequestParam("productId")String productId){
        int cate = productService.findOne(productId).getCateGory();
        switch(cate){
            case 1:
                List<ComputerProduct> computerProductList=new ArrayList();
                computerProductList=computerService.findByProductId(productId);

                return ResultVOEnum.success(computerProductList);
            case 2:
                List<PhoneProduct> phoneProductList=new ArrayList();
                phoneProductList=phoneService.findByProductId(productId);

                return ResultVOEnum.success(phoneProductList);
        }
        return ResultVOEnum.error(1,"There is not this category");
    }

    @GetMapping("/singleOne")
    @ApiOperation(value = "查询单一单品详情",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO findSingle(@ApiParam("单品id") @RequestParam("singleId") String sin_id,
                               @ApiParam("单品类别") @RequestParam("cateGory") int cateGory){
        switch(cateGory){
            case 1:
                ComputerProduct computerProduct=computerService.findOne(sin_id);
                return ResultVOEnum.success(computerProduct);
            case 2:
                PhoneProduct phoneProduct=phoneService.findOne(sin_id);
                return ResultVOEnum.success(phoneProduct);
        }
        return ResultVOEnum.error(1,"There is not this category");
    }
}



