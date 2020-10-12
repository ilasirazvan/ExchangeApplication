package com.exchange.service.interfaces;

import com.exchange.entities.Channel;
import com.exchange.entities.Item;

public  interface IQuerryDataSet {

    public String getJsonForCurrencies(String currency1, String currency2, Channel chanel);
}
