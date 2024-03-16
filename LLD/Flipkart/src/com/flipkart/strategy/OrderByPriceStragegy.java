package com.flipkart.strategy;

import com.flipkart.models.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderByPriceStragegy implements  OrderByStrategy{

    @Override
    public List<Item> order(List<Item> items) {
        List<Item> sortedList = new ArrayList<>(items);
        sortedList.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
        return  sortedList;
    }
}
