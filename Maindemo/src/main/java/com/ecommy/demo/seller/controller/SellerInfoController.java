package com.ecommy.demo.seller.controller;

import com.ecommy.demo.Common.DataObject.SellerInfo;
import com.ecommy.demo.Common.Enums.ResultEnum;
import com.ecommy.demo.Common.Enums.ResultVOEnum;
import com.ecommy.demo.Common.VO.ResultVO;
import com.ecommy.demo.seller.service.SellerInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/sellerinfo")
@Api("商家管理员信息管理")
public class SellerInfoController {

    @Autowired
    private SellerInfoService sellerInfoService;

    @PostMapping("/create")
    @ApiOperation(value = "注册商家账号", notes = "注册商家账号", produces = "application/json;charset=UTF-8")
    public ResultVO Create(@Valid SellerInfo sellerInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOEnum.error(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        }
        //List<SellerInfo> sellerInfos;
        //sellerInfos = sellerInfoService.findAll(sellerInfo.getSellerAccount());
        if(sellerInfoService.findOne(sellerInfo.getSellerAccount()) == null){
            sellerInfoService.save(sellerInfo);
            return ResultVOEnum.success();}
        else{
            return ResultVOEnum.error(1,"账号已存在，请重新输入。");}
    }

    @GetMapping("/display")
    @ApiOperation(value = "查看商家信息", notes = "使用商家账号查看", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<List<SellerInfo>> getAll(@ApiParam("商家账号") @RequestParam("selleraccount")String selleraccount) {
        List<SellerInfo> sellerInfos;
        sellerInfos = sellerInfoService.findAll(selleraccount);
        return ResultVOEnum.success(sellerInfos);
    }


    @GetMapping("/login")
    @ApiOperation(value = "登录", notes = "根据账号密码查询账号是否存在", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<List<SellerInfo>> check(@ApiParam("账号") @RequestParam("selleraccount") String sellerAccount,
                                            @ApiParam("密码") @RequestParam("sellerpassword") String sellerPassword) {
        //SellerInfo sellerInfo;
        //sellerInfo = sellerInfoService.findOne(sellerAccount);
        if(sellerInfoService.findOne(sellerAccount) == null){
            return ResultVOEnum.error(1,"账号不存在，请重新输入。");
        }
        else if(sellerInfoService.findOne(sellerAccount).getSellerPassword().equals(sellerPassword)){
            return ResultVOEnum.success(0, "成功登陆：）");
        }
        else
            return ResultVOEnum.error(1,"账号密码错误，请重新输入。");
    }

    @PostMapping("/modify")
    @ApiOperation(value = "修改商家信息", notes = "修改信息", produces = "application/json;charset=UTF-8")
    public ResultVO Modify(@Valid SellerInfo sellerInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOEnum.error(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        }
        //List<SellerInfo> sellerInfos;
        //sellerInfos = sellerInfoService.findAll(sellerInfo.getSellerAccount());
        if(sellerInfoService.findOne(sellerInfo.getSellerAccount()) != null){
            sellerInfoService.save(sellerInfo);
            return ResultVOEnum.success();}
        else{
            return ResultVOEnum.error(1,"账号不存在");}
    }
}
