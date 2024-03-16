package com.flipkart.strategy;

import com.flipkart.models.Item;

import java.util.List;

public interface OrderByStrategy {
    List<Item>order(List<Item> items);
}
