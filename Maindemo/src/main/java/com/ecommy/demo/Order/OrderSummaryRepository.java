package com.ecommy.demo.Order;

import com.ecommy.demo.Common.DataObject.OrderSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderSummaryRepository extends JpaRepository <OrderSummary,String>{
    List<OrderSummary> findByBuyerOpenId(String BuyerOenpId);

    OrderSummary findByOrderId(String orderId);

    List<OrderSummary> findBySellerAccount(String sellerAccount);

    Page<OrderSummary> findByOrOrderStatus(@Param("orderStatus") Integer orderStatus, Pageable pageable);

    Page<OrderSummary> findByOrderStatusNot(@Param("orderStatus") Integer orderStatus, Pageable pageable);

    List<OrderSummary> findBySellerAccountAndOrderStatus(String sellerAccount,Integer orderStatus);
}
