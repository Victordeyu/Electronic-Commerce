package com.ecommy.demo.Buyer.controller;


import com.ecommy.demo.Buyer.service.BuyerService;
import com.ecommy.demo.Common.Convert.OrderForm2OrderDTOConverter;
import com.ecommy.demo.Common.DTO.OrderDTO;
import com.ecommy.demo.Common.Enums.ResultVOEnum;
import com.ecommy.demo.Common.Form.OrderForm;
import com.ecommy.demo.Common.VO.ResultVO;
import com.ecommy.demo.Order.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
@Api("买家订单接口")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    /**
     * 查询订单列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value="查询订单",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<OrderDTO> list(@ApiParam("页数") @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @ApiParam("容量") @RequestParam(value = "size", defaultValue = "15") Integer size) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        return ResultVOEnum.success(orderService.findUnfinished(pageRequest));
    }

    //创建订单
    @PostMapping("/create")
    @Transactional
    @ApiOperation(value = "创建订单", notes = "创建订单", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOEnum.success(map);
    }

    //支付订单
    @PostMapping("/pay")
    @ApiOperation(value = "支付订单", notes = "需要openid orderId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO pay(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        buyerService.payOrder(openid, orderId);
        return ResultVOEnum.success();
    }

    //取消订单
    @PostMapping("/cancel")
    @ApiOperation(value = "取消订单", notes = "需要openid orderId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ResultVOEnum.success();
    }


    //取消订单单个商品
    @PostMapping("/canceldetail")
    @ApiOperation(value = "取消订单", notes = "需要openid orderId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO cancelDetail(@RequestParam("orderId") String orderId,
                           @RequestParam("singleId") String singleId) {
        buyerService.cancelOrderDetail(orderId,singleId);
        return ResultVOEnum.success();
    }


    //修改
    @GetMapping("/detail")
    @ApiOperation(value = "查询用户所有订单", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<OrderDTO> orderDetail(@ApiParam("用户ID")@RequestParam("openId") String openId) {
        List<OrderDTO> orderDTOs = orderService.orderList(openId);
        return ResultVOEnum.success(orderDTOs);
    }

    @GetMapping("/special")
    @ApiOperation(value = "单个订单查询", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<OrderDTO> sporderDetail(@ApiParam("订单ID")@RequestParam("orderId") String orderId){
        OrderDTO orderDTO=orderService.findOne(orderId);
        return ResultVOEnum.success(orderDTO);
    }


}