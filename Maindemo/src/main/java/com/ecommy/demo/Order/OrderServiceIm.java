package com.ecommy.demo.Order;

import com.ecommy.demo.Buyer.service.BuyerService;
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
                    orderDetail.setOrderDetailId(Key.getKey());
                    orderDetail.setOrderId(orderId);
                    amount+=computerProduct.getPrice()*orderDetail.getCount();
                    computerService.increaseInventory(orderDetail.getSingleId(),-orderDetail.getCount());
                    computerService.increaseSales(orderDetail.getSingleId(),orderDetail.getCount());
                    productService.increaseSales(computerProduct.getProductId(),orderDetail.getCount());
                    orderDetailRepository.save(orderDetail);
                    break;
                case 2:
                    PhoneProduct phoneProduct= phoneService.<PhoneProduct>
                            findOne(orderDetail.getSingleId());
                    if(phoneProduct==null){
                        //抛出异常
                    }
                    orderDetail.setOrderDetailId(Key.getKey());
                    orderDetail.setOrderId(orderId);
                    orderDetail.setOrderStatus(OrderStatusEnum.NORMAL.getCode());
                    amount+=phoneProduct.getPrice()*orderDetail.getCount();
                    phoneService.increaseInventory(orderDetail.getSingleId(),-orderDetail.getCount());
                    phoneService.increaseSales(orderDetail.getSingleId(),orderDetail.getCount());
                    productService.increaseSales(phoneProduct.getProductId(),orderDetail.getCount());
                    orderDetailRepository.save(orderDetail);
                    break;
            }
        }

        //写入数据库
        //
        OrderSummary orderSummary=new OrderSummary();
        BeanUtils.copyProperties(orderDTO,orderSummary);
        orderSummary.setOrderId(orderId);
        orderSummary.setOrderAccount(amount);
        orderSummary.setOrderStatus(OrderStatusEnum.NORMAL.getCode());
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderAccount(amount);
        orderDTO.setOrderStatus(OrderStatusEnum.NORMAL.getCode());

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
    public List<OrderDTO> findUnfinished(String sellerAccount) {
        List<OrderSummary> orderSummaryList = orderSummaryRepository.findBySellerAccountAndOrderStatus(sellerAccount,OrderStatusEnum.NORMAL.getCode());
        List<OrderSummary> orderSummaryList1 =  orderSummaryRepository.findBySellerAccountAndOrderStatus(sellerAccount,OrderStatusEnum.PAID.getCode());
        int i = orderSummaryList1.size() - 1;
        while(i >= 0){
            orderSummaryList.add(orderSummaryList1.get(i));
            i--;
        }
        List<OrderDTO> orderDTOS = OrderSummary2OrderDTOConverter.convert(orderSummaryList);
        return orderDTOS;
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
        //减掉销量
        List<OrderDetail>orderDetailList=orderDetailRepository.findByOrderId(orderSummary.getOrderId());
        for(OrderDetail orderDetail:orderDetailList){
            //将每一个单品的状态都设置成取消
            orderDetail.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
            orderDetailRepository.save(orderDetail);
            switch(orderDetail.getCateGory()){
                case 1:
                    ComputerProduct computerProduct=computerService.findOne(orderDetail.getSingleId());
                    computerService.increaseSales(orderDetail.getSingleId(),-orderDetail.getCount());
                    computerService.increaseInventory(orderDetail.getSingleId(),orderDetail.getCount());
                    productService.increaseSales(computerProduct.getProductId(),-orderDetail.getCount());
                    break;
                case 2:
                    PhoneProduct phoneProduct=phoneService.findOne(orderDetail.getSingleId());
                    phoneService.increaseSales(orderDetail.getSingleId(),-orderDetail.getCount());
                    phoneService.increaseInventory(orderDetail.getSingleId(),orderDetail.getCount());
                    productService.increaseSales(phoneProduct.getProductId(),-orderDetail.getCount());
                    break;
            }
        }
        return orderDTO;
    }

    @Override
    public void cancelOrderDetail(String orderId, String singleId) {
        OrderDetail orderDetail=orderDetailRepository.findByOrderIdAndSingleId(orderId,singleId);
        orderDetail.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderDetailRepository.save(orderDetail);

        switch (orderDetail.getCateGory())
        {
            case 1:
                ComputerProduct computerProduct=computerService.findOne(orderDetail.getSingleId());
                computerService.increaseSales(orderDetail.getSingleId(),-orderDetail.getCount());
                computerService.increaseInventory(orderDetail.getSingleId(),orderDetail.getCount());
                productService.increaseSales(computerProduct.getProductId(),-orderDetail.getCount());
                break;
            case 2:
                PhoneProduct phoneProduct=phoneService.findOne(orderDetail.getSingleId());
                phoneService.increaseSales(orderDetail.getSingleId(),-orderDetail.getCount());
                phoneService.increaseInventory(orderDetail.getSingleId(),orderDetail.getCount());
                productService.increaseSales(phoneProduct.getProductId(),-orderDetail.getCount());
                break;
        }

        List<OrderDetail> orderDetailList=orderDetailRepository.findByOrderId(orderId);
        for(OrderDetail orderDetail1:orderDetailList){
            if(orderDetail1.getOrderStatus()!=OrderStatusEnum.CANCEL.getCode())
                return;
        }
        OrderSummary orderSummary=orderSummaryRepository.findByOrderId(orderId);
        orderSummary.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderSummaryRepository.save(orderSummary);
        return;
    }

    @Override
    public OrderDTO send(OrderDTO orderDTO) {

        if(orderDTO.getOrderStatus().equals(OrderStatusEnum.NORMAL.getCode())){
            //抛出异常，订单状态不正常
        }
        OrderSummary orderSummary=new OrderSummary();
        orderDTO.setOrderStatus(OrderStatusEnum.SENT.getCode());
        BeanUtils.copyProperties(orderDTO,orderSummary);
        orderSummaryRepository.save(orderSummary);

        List<OrderDetail>orderDetailList=orderDetailRepository.findByOrderId(orderDTO.getOrderId());
        for(OrderDetail orderDetail:orderDetailList){
            orderDetail.setOrderStatus(OrderStatusEnum.SENT.getCode());
            orderDetailRepository.save(orderDetail);
        }

        return orderDTO;
    }

    @Override
    public OrderDTO pay(OrderDTO orderDTO) {
        if(orderDTO.getOrderStatus().equals(OrderStatusEnum.NORMAL.getCode())){
            //抛出异常，订单状态不正常
        }
        OrderSummary orderSummary=new OrderSummary();
        orderDTO.setOrderStatus(OrderStatusEnum.PAID.getCode());
        BeanUtils.copyProperties(orderDTO,orderSummary);
        orderSummaryRepository.save(orderSummary);

        List<OrderDetail>orderDetailList=orderDetailRepository.findByOrderId(orderDTO.getOrderId());
        for(OrderDetail orderDetail:orderDetailList){
            orderDetail.setOrderStatus(OrderStatusEnum.PAID.getCode());
            orderDetailRepository.save(orderDetail);
        }

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

        List<OrderDetail>orderDetailList=orderDetailRepository.findByOrderId(orderDTO.getOrderId());
        for(OrderDetail orderDetail:orderDetailList){
            orderDetail.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
            orderDetailRepository.save(orderDetail);
        }

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

    @Override
    public List<OrderDTO> sellerOrderList(String sellerAccount){
        List<OrderSummary>orderList=orderSummaryRepository.findBySellerAccount(sellerAccount);
        List<OrderDTO> orderDTOList= OrderSummary2OrderDTOConverter.convert(orderList);
        orderDTOList.forEach(orderDTO ->
                orderDTO.setOrderDetailList(orderDetailRepository.findByOrderId(orderDTO.getOrderId()))
        );
        return orderDTOList;
    }
}
