package com.ecommy.demo.Buyer.service;

import com.ecommy.demo.Common.DTO.OrderDTO;

public interface BuyerService {

    OrderDTO findOrderOne(String openid, String orderId);

    OrderDTO cancelOrder(String openid, String orderId);

}
