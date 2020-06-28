package com.ecommy.demo.Order;

import com.ecommy.demo.Common.DataObject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId(String orderId);

    OrderDetail findByOrderIdAndSingleId(String orderId,String singleId);
}
