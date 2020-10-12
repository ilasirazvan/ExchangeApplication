package com.exchange.utils;

import com.exchange.entities.Item;
import org.json.JSONObject;

public class ItemToJson {

    private ItemToJson() {
    }

    public static String getJson(Item item) {

        JSONObject json = new JSONObject();
        json.put("description", item.getDescription());
        json.put("exchangeRate", Double.toString(item.getExchangeRate()));
        json.put("publishDate", item.getPubDate());
        return json.toString();
    }
}
