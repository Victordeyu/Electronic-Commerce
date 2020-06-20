package com.ecommy.demo.Common.Enums;

public class EnumUtil {

    public static <T extends CodeEnum> T GetByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
