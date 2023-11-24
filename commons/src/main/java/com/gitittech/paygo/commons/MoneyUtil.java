package com.gitittech.paygo.commons;

import java.math.BigDecimal;

public class MoneyUtil {

    public static Long getKoboFromNaira(BigDecimal naira) {
        BigDecimal kobo = naira.multiply(new BigDecimal(100));
        return kobo.longValue();
    }

    public static BigDecimal getNairaFromKobo(Double kobo) {
        Double naira = kobo / 100l;
        return new BigDecimal(naira);
    }
}
