package com.bigbank.portfolio.api.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  AlphaAvantageEnum {
    SYMBOL_SEARCH("?function=SYMBOL_SEARCH&keywords=%s&apikey=%s");


    private String params;

}
