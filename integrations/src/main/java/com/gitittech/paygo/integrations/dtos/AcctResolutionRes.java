package com.gitittech.paygo.integrations.dtos;

public class AcctResolutionRes {

    public Boolean status;
    public String message;
    public Data data;

    public static class Data {
        public String account_number;
        public String account_name;
    }
}
