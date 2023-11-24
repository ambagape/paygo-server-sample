package com.gitittech.paygo.integrations.dtos;

import java.util.ArrayList;
import java.util.List;

public class AllBankRequest {

    public Boolean status;
    public String message;
    public List<Bank> data = new ArrayList<>();

    public class Bank {
        public String name;
        public String slug;
        public String code;
        public String longcode;
        public String gateway;
        public Boolean pay_with_bank;
        public Boolean active;
        public String country;
        public String currency;
        public String type;
        public Long id;
    }
}
