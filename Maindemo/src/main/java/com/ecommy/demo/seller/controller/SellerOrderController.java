package com.ecommy.demo.seller.controller;

import com.ecommy.demo.Common.DTO.OrderDTO;
import com.ecommy.demo.Common.Enums.ResultVOEnum;
import com.ecommy.demo.Common.VO.ResultVO;
import com.ecommy.demo.Order.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seller/order")
@Slf4j
@Api
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 查询订单列表
     * @param page
     * @param size
     * @return
     */
//    @GetMapping("/list")
//    @ApiOperation(value="查询未完成订单",produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResultVO<OrderDTO> list(@ApiParam("页数") @RequestParam(value = "page", defaultValue = "1") Integer page,
//                                   @ApiParam("容量") @RequestParam(value = "size", defaultValue = "15") Integer size) {
//        PageRequest pageRequest = new PageRequest(page - 1, size);
//        return ResultVOEnum.success(orderService.findUnfinished(pageRequest));
//    }
    @GetMapping("/list")
    @ApiOperation(value="查询未完成订单",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<OrderDTO> list(@ApiParam("商家账号")@RequestParam("sellerAccount") String sellerAccount) {
        List<OrderDTO> unOrderList = orderService.findUnfinished(sellerAccount);
        return ResultVOEnum.success(unOrderList);
    }



    /**
     * 根据ID取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/Cancel")
    @ApiOperation(value="取消订单",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO cancel(@ApiParam("订单ID")@RequestParam("orderID") String orderId){
        OrderDTO orderDTO=orderService.findOne(orderId);
        orderService.cancel(orderDTO);
        return ResultVOEnum.success();
    }

    @GetMapping("/detail")
    @ApiOperation(value = "订单查询", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<OrderDTO> orderDetail(@ApiParam("订单ID")@RequestParam("orderId") String orderId){
        OrderDTO orderDTO=orderService.findOne(orderId);
        return ResultVOEnum.success(orderDTO);
    }

    @GetMapping("/send")
    @ApiOperation(value="订单发货",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO send(@ApiParam("订单ID")@RequestParam("orderID") String orderId){
        OrderDTO orderDTO=orderService.findOne(orderId);
        orderService.send(orderDTO);
        return ResultVOEnum.success();
    }

    //查询seller的所有订单
    @GetMapping("/sellerlist")
    @ApiOperation(value="查询商家所有订单",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<OrderDTO> orderlist(@ApiParam("卖家account")@RequestParam("sellerAccount") String sellerAccount) {
        List<OrderDTO> orderDTOs = orderService.sellerOrderList(sellerAccount);
        return ResultVOEnum.success(orderDTOs);
    }

}

