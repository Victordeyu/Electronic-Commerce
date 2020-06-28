package com.ecommy.demo.Common.VO;

import lombok.Data;

@Data
public class OrderCartVO<T> {

    private T data;

    private Integer num;
}
