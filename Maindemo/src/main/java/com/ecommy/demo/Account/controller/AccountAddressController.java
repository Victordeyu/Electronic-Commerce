package com.ecommy.demo.Account.controller;

import com.ecommy.demo.Account.service.IReceiverInfoService;
import com.ecommy.demo.Account.service.ReceiverInfoService;
import com.ecommy.demo.Common.DataObject.Receiver;
import com.ecommy.demo.Common.Enums.ResultVOEnum;
import com.ecommy.demo.Common.Enums.accountaddressEnum;
import com.ecommy.demo.Common.Unit.Key;
import com.ecommy.demo.Common.VO.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account/receiver")
@Api("用户收货地址管理")
public class AccountAddressController {
    @Autowired
    private ReceiverInfoService ReceiverService;

    @PostMapping("/save")
    @ApiOperation(value = "新增/修改", notes = "当有id时修改", produces = "application/json;charset=UTF-8")
    public ResultVO save(@Valid Receiver Receiver, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOEnum.error(accountaddressEnum.PARAM_ERROR.getCode(), accountaddressEnum.PARAM_ERROR.getMessage());
        }
        if (Receiver.getRecId() == null) {//create random number for address
            String recId = "rec" + Key.getKey();
            Receiver.setRecId(recId);
            Receiver.setStatus(0);
        }
        ReceiverService.save(Receiver);
        return ResultVOEnum.success();
    }

    // 设置默认地址
    @PutMapping("/defaultAddr")
    @ApiOperation(value = "设置默认地址", notes = "根据地址id，设置默认地址")
    public ResultVO<List<Receiver>> DefaultAddr(@ApiParam("地址recId") @RequestParam("recId") String recId,
                                                    @ApiParam("用户openid") @RequestParam("openid") String openid) {
        List<Receiver> Receivers;
        Receivers = ReceiverService.findAll(openid);
        int i = Receivers.size() - 1;
        while(i >= 0){
            Receivers.get(i).setStatus(0);
            ReceiverService.save(Receivers.get(i));
            i--;
        }
        Receiver Receiver = ReceiverService.findOne(recId);
        Receiver.setStatus(1);
        ReceiverService.save(Receiver);
        return ResultVOEnum.success(Receiver);
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询收货地址", notes = "根据openid查询收货地址", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<List<Receiver>> getAll(@ApiParam("用户openid") @RequestParam("用户openid")String openid) {
        List<Receiver> Receivers;
        Receivers = ReceiverService.findAll(openid);
        return ResultVOEnum.success(Receivers);
    }

    @GetMapping("/one")
    @ApiOperation(value = "查询单个收货地址", notes = "根据recId查询收货地址", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<List<Receiver>> getOne(@ApiParam("地址recId") @RequestParam("recId") String recId) {
        Receiver Receiver = ReceiverService.findOne(recId);
        return ResultVOEnum.success(Receiver);
    }

    @GetMapping("/del")
    @ApiOperation(value = "删除收货地址", notes = "根据openid和收货地址id删除", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResultVO<List<Receiver>> delete(@ApiParam("收货信息id") @RequestParam("recId") String recId){//, @ApiParam("用户openid") @RequestParam("openid") String openid) {
        ReceiverService.delete(recId);
//        List<Receiver> Receivers = ReceiverService.findAll(openid);
        return ResultVOEnum.success();//Receivers);
    }
}
