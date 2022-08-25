package com.example.uzkassatask.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCurrencyRate {

    private String title;
    private String code;
    private String cb_price;
    private String nbu_buy_price;
    private String nbu_cell_price;
    private String date;

    public ResponseCurrencyRate(String title, String code, String cb_price, String nbu_buy_price, String nbu_cell_price, String date) {
        this.title = title;
        this.code = code;
        this.cb_price = cb_price;
        this.nbu_buy_price = nbu_buy_price;
        this.nbu_cell_price = nbu_cell_price;
        this.date = date;
    }

}
