package com.flipkart.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart {
    Map<Integer, List<Item>> cartItems;
    List<Item> getCartItems(int userId){
        return  cartItems.get(userId);
    }
    void addCartItems(int userId, Item item) {
        if (cartItems.get(userId) == null) {
            cartItems.put(userId, new ArrayList<>());
        }
        cartItems.get(userId).add(item);
    }
}
