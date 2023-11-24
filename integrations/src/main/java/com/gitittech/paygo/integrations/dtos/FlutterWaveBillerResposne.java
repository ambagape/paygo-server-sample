package com.gitittech.paygo.integrations.dtos;

import java.util.ArrayList;
import java.util.List;

public class FlutterWaveBillerResposne {
    public String status;
    public String message;
    public List<Bill> data = new ArrayList<>();

    public static class Bill {
        public Long id;
        public String biller_code;
        public String name;
        public String country;
        public Boolean is_airtime;
        public String biller_name;
        public String item_code;
        public String short_name;
        public String label_name;
        public Long amount;
    }
}
