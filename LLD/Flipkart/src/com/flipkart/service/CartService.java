package com.flipkart.service;

import com.flipkart.exceptions.InsufficientWalletAmountException;
import com.flipkart.models.Item;
import com.flipkart.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {
    private static Map<String, List<Item>> carts = new HashMap<>();
    InventoryService inventoryService;
    UserService userService;
    public CartService(UserService userService, InventoryService inventoryService){
        this.inventoryService = inventoryService ;
       this.userService = userService;
       carts = new HashMap<>();
    }
    public void addToCart(String category, String brand, String userName, int quantity) {
        Item inventoryItem = inventoryService.getItem(category, brand);
        User user = userService.getUser(userName);
        if (quantity * inventoryItem.getMrp() >= user.getWallet().getAmount()) {
            throw new InsufficientWalletAmountException();
        }
        if (carts.get(userName) == null) {
            carts.put(userName, new ArrayList<>());
        }
        carts.get(userName).add(inventoryItem);
        inventoryService.sellItem(category, brand, quantity);
    }

    public List<Item> getAllItemsForAUser(String userName) {
        return carts.get(userName);
    }

}
