package com.ecommy.demo.Common.Unit;

import java.util.Random;

public class Key {

    public static synchronized String getKey(){
        Random random= new Random();
        Integer n=random.nextInt(500)+100;
        return System.currentTimeMillis()+String.valueOf(n);
    }
}
