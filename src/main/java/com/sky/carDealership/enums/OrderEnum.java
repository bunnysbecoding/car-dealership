package com.sky.carDealership.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum OrderEnum {
    YEAR, PRICE, MILEAGE, BRAND, INVALID;

    public static OrderEnum orderEnumValueOf(String orderStr) {
        for (OrderEnum e : OrderEnum.values()) {
            if (orderStr.equalsIgnoreCase(e.name())) {
                return e;
            }
        }

        return INVALID;
    }

    public static String validOrdersStr() {
        List<OrderEnum> enumList = new ArrayList<>(Arrays.asList(OrderEnum.values()));
        enumList.removeIf(o -> o.equals(OrderEnum.INVALID));
        return enumList.toString();
    }

}
