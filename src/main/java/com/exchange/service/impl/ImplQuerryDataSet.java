package com.exchange.service.impl;

import com.exchange.entities.Channel;
import com.exchange.entities.Item;
import com.exchange.service.interfaces.IQuerryDataSet;
import com.exchange.utils.ItemToJson;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;

@RequestScoped
public class ImplQuerryDataSet implements IQuerryDataSet {

    @Override
    public String getJsonForCurrencies(String currency1, String currency2, Channel chanel) {

        ArrayList<Item> items = (ArrayList<Item>) chanel.getItem();
        for (Item item : items) {
            if (item.getBaseCurrency().equals(currency1) && item.getTargetCurrency().equals(currency2)) {
                return ItemToJson.getJson(item);
            }
        }
        return null;
    }
}
