package com.ecommy.demo.Common.VO;

import lombok.Data;

@Data
public class PhoneVO implements Comparable<PhoneVO>{

    private String singleId;

    private String productId;

    private String brand;

    private String color;

    private String disk;

    private String memory;

    private String cpu;

    private int price;

    private String sellerId;

    private String url;

    private int inventory;

    private int num;

    private int cateGory=2;

    private boolean checked=true;

    private int sales;

    @Override
    public int compareTo(PhoneVO phoneVO) {
        return phoneVO.getSales()-this.sales;
    }
}
