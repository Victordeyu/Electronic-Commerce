package com.ecommy.demo.Buyer.controller;

import com.ecommy.demo.Common.DataObject.ComputerProduct;
import com.ecommy.demo.Common.DataObject.PhoneProduct;
import com.ecommy.demo.Common.DataObject.Product;
import com.ecommy.demo.Common.Enums.ProductStatusEnum;
import com.ecommy.demo.Common.Enums.ResultEnum;
import com.ecommy.demo.Common.Enums.ResultVOEnum;
import com.ecommy.demo.Common.VO.CateGoryVO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
@Api("买家商品接口")
@Slf4j
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private ComputerService computerService;

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

    @GetMapping("/one")
    @ApiOperation(value = "查询单一商品详情", notes = "By ProductId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<ProductVO> findOne(@RequestParam("productId")@Param("商品id") String productId){
        Product product=productService.findOne(productId);
        if(product.getProductStatus()== ProductStatusEnum.UP.getCode())
            return ResultVOEnum.success(product);
        else
            return ResultVOEnum.error(1, ResultEnum.PRODUCT_NOT_EXIST.getMessage());
    }

    @GetMapping("/singleOne")
    @ApiOperation(value = "查询单一单品详情",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO findSingle(@ApiParam("单品id") @RequestParam("singleId") String sin_id,
                               @ApiParam("单品类别") @RequestParam("cateGory") int cateGory){
        switch(cateGory){
            case 1:
                ComputerProduct computerProduct=computerService.findOne(sin_id);
                if(productService.findOne(computerProduct.getProductId()).getProductStatus()==ProductStatusEnum.UP.getCode())
                    return ResultVOEnum.success(computerProduct);
                else
                    return ResultVOEnum.error(1, ResultEnum.PRODUCT_NOT_EXIST.getMessage());
            case 2:
                PhoneProduct phoneProduct=phoneService.findOne(sin_id);
                if(productService.findOne(phoneProduct.getProductId()).getProductStatus()==ProductStatusEnum.UP.getCode())
                    return ResultVOEnum.success(phoneProduct);
                else
                    return ResultVOEnum.error(1, ResultEnum.PRODUCT_NOT_EXIST.getMessage());
        }
        return ResultVOEnum.error(1,"There is not this category");
    }

    @GetMapping("/cateGory")
    @ApiOperation(value="查询某一类的所有商品",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<ProductVO> cateGory(@ApiParam("类别") @RequestParam("cateGory")int cateGory){
        List<Product>productList;
        List<ProductVO>productVOList=new ArrayList();
        productList=productService.findByCategoryAndStatus(cateGory,ProductStatusEnum.UP.getCode());

        for (Product product : productList) {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            productVOList.add(productVO);
        }

        return ResultVOEnum.success(productVOList);

    }

    @GetMapping("/allByCateGory")
    @ApiOperation(value="查询所有商品",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO findAll(){
        List<Product>productList;
        List<CateGoryVO>CateGoryVOlist=new ArrayList<>();

        for(int i=1;i<3;i++) {
            List<ProductVO>productVOList=new ArrayList();
            productList = productService.findByCategoryAndStatus(i,ProductStatusEnum.UP.getCode());

            for (Product product : productList) {
                ProductVO productVO = new ProductVO();
                BeanUtils.copyProperties(product, productVO);
                productVOList.add(productVO);
            }
            CateGoryVO cateGoryVO = new CateGoryVO();
            cateGoryVO.setCateGory(i);
            cateGoryVO.setProductVOList(productVOList);
            CateGoryVOlist.add(cateGoryVO);
        }

        return ResultVOEnum.success(CateGoryVOlist);
    }

    @GetMapping("/singleAllByProduct")
    @ApiOperation(value="查询某一商品所有单品",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO findByProductId(@ApiParam("商品Id") @RequestParam("productId")String productId) {
        Product product = productService.findOne(productId);
        if (product.getProductStatus() == ProductStatusEnum.UP.getCode()) {
            int cate = product.getCateGory();
            switch (cate) {
                case 1:
                    List<ComputerProduct> computerProductList = new ArrayList();
                    computerProductList = computerService.findByProductId(productId);

                    return ResultVOEnum.success(computerProductList);
                case 2:
                    List<PhoneProduct> phoneProductList = new ArrayList();
                    phoneProductList = phoneService.findByProductId(productId);

                    return ResultVOEnum.success(phoneProductList);
            }
            return ResultVOEnum.error(1, "There is not this category");
        }
        else
        {
            return ResultVOEnum.error(1, ResultEnum.PRODUCT_NOT_EXIST.getMessage());
        }
    }


    @GetMapping("/findByNameLike")
    @ApiOperation(value="通过关键词查询商品",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<ProductVO> findByProductDescLike(String Keyword) {
        // 一定要加 "%"+参数名+"%"
        return ResultVOEnum.success(productService.findByProductDescOrProductNameLike(Keyword));
    }

}
