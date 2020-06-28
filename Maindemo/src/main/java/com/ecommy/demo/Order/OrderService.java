package com.ecommy.demo.Order;

import com.ecommy.demo.Common.DTO.OrderDTO;
import com.ecommy.demo.Common.DataObject.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author： Qiuyun
 * 2020-6-14
 */


public interface OrderService {

    /**
     * 创建订单
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询某个订单
     */
    OrderDTO findOne(String orderId);

    List<OrderDTO> findUnfinished(String sellerAccount);

    /**
     * 查询某个用户的订单列表
     */
    List<OrderDTO> orderList(String openid);

    //查询某个卖家的订单列表
    List<OrderDTO> sellerOrderList(String sellerAccount);

    /**
     * 取消订单
     */
    OrderDTO cancel(OrderDTO orderDTO);

    void cancelOrderDetail(String orderId,String singleId);
    /**
     * 订单发货
     */
    OrderDTO send(OrderDTO orderDTO);


    /**
     * 订单支付
     */
    OrderDTO pay(OrderDTO orderDTO);

    /**
     * 完成订单
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 查询未完成订单
     * @param pageable
     * @return
     */
    Page<OrderDTO> findUnfinished(Pageable pageable);

    /**
     * 查询历史订单
     * @param pageable
     * @return
     */
    Page<OrderDTO> findHistory(Pageable pageable);

}
