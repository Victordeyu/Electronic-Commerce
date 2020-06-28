package com.ecommy.demo.Common.VO;

import lombok.Data;

@Data
public class ComputerVO implements Comparable<ComputerVO>{

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

    private int cateGory=1;

    private int num;

    private int inventory;

    private boolean checked=true;

    private int sales;

    @Override
    public int compareTo(ComputerVO computerVO) {
        return computerVO.getSales()-this.sales;
    }
}