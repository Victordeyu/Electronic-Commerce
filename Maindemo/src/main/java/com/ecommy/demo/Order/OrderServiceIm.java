package com.ecommy.demo.Order;

import com.ecommy.demo.Common.Convert.OrderSummary2OrderDTOConverter;
import com.ecommy.demo.Common.DTO.OrderDTO;
import com.ecommy.demo.Common.DataObject.*;
import com.ecommy.demo.Common.Enums.OrderStatusEnum;
import com.ecommy.demo.Common.Unit.Key;
import com.ecommy.demo.Product.Service.ComputerService;
import com.ecommy.demo.Product.Service.PhoneService;
import com.ecommy.demo.Product.Service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceIm implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderSummaryRepository orderSummaryRepository;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private ComputerService computerService;

    @Override
    public OrderDTO create(OrderDTO orderDTO){

        //创建一个orderid
        String orderId= Key.getKey();

        Integer amount=0;
        //对订单列表中的每一个单品进行处理
        for(OrderDetail orderDetail : orderDTO.getOrderDetailList())
        {
            switch (orderDetail.getCateGory()){
                case 1:
                    ComputerProduct computerProduct= computerService.<ComputerProduct>
                            findOne(orderDetail.getSingleId());
                    if(computerProduct==null){
                        //抛出异常
                    }
                    amount+=computerProduct.getPrice();
                    orderDetailRepository.save(orderDetail);

                case 2:
                    PhoneProduct phoneProduct= phoneService.<PhoneProduct>
                            findOne(orderDetail.getSingleId());
                    if(phoneProduct==null){
                        //抛出异常
                    }
                    amount+=phoneProduct.getPrice();
                    orderDetailRepository.save(orderDetail);
            }
        }

        //写入数据库
        //
        OrderSummary orderSummary=new OrderSummary();
        orderSummary.setOrderId(orderId);
        orderSummary.setOrderAccount(amount);
        orderSummary.setOrderStatus(OrderStatusEnum.NORMAL.getCode());
        BeanUtils.copyProperties(orderDTO,orderSummary);

        orderSummaryRepository.save(orderSummary);

        return orderDTO;
    }


    @Override
    public OrderDTO findOne(String orderId){
        OrderSummary orderSummary=orderSummaryRepository.findById(orderId).orElse(null);
//        if(orderSummary==null)

            //未查到该订单，抛出异常


        List<OrderDetail> orderList=orderDetailRepository.findByOrderId(orderId);
//        if(CollectionUtils.isEmpty(orderList)

            //订单的详细内容为空，抛出异常


        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderSummary,orderDTO);
        orderDTO.setOrderDetailList(orderList);

        return orderDTO;
    }

    @Override
    public List<OrderDTO> orderList(String openid){
        List<OrderSummary>orderList=orderSummaryRepository.findByBuyerOpenId(openid);
        List<OrderDTO> orderDTOList= OrderSummary2OrderDTOConverter.convert(orderList);
        orderDTOList.forEach(orderDTO ->
                orderDTO.setOrderDetailList(orderDetailRepository.findByOrderId(orderDTO.getOrderId()))
        );
        return orderDTOList;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO){

        if(orderDTO.getOrderStatus().equals(OrderStatusEnum.NORMAL.getCode())){
            //抛出异常，订单状态不正常
        }
        OrderSummary orderSummary=new OrderSummary();
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderSummary);
        orderSummaryRepository.save(orderSummary);

        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO){
        if(orderDTO.getOrderStatus().equals(OrderStatusEnum.NORMAL.getCode())){
            //抛出异常，订单状态不正常
        }
        OrderSummary orderSummary=new OrderSummary();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO,orderSummary);
        orderSummaryRepository.save(orderSummary);

        return orderDTO;
    }


    @Override
    public Page<OrderDTO> findUnfinished(Pageable pageable) {
        Page<OrderSummary> orderMasterPage = orderSummaryRepository.findByOrOrderStatus(OrderStatusEnum.NORMAL.getCode(), pageable);
        List<OrderDTO> orderDTOS = OrderSummary2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<>(orderDTOS, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    public Page<OrderDTO> findHistory(Pageable pageable) {
        Page<OrderSummary> orderMasterPage = orderSummaryRepository.findByOrderStatusNot(OrderStatusEnum.NORMAL.getCode(), pageable);
        List<OrderDTO> orderDTOS = OrderSummary2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<>(orderDTOS, pageable, orderMasterPage.getTotalElements());
    }


}
