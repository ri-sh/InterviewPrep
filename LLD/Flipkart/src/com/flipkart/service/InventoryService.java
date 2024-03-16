package com.flipkart.service;

import com.flipkart.OrderBy;
import com.flipkart.PriceUpdateType;
import com.flipkart.exceptions.ItemNotPresentException;
import com.flipkart.models.Item;
import com.flipkart.strategy.OrderByPriceStragegy;
import com.flipkart.strategy.OrderByStrategy;
import com.flipkart.strategy.DefaultSearchStrategy;
import com.flipkart.strategy.SearchStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryService {
    private static int itemId = 0;
    Map<String, Map<String, Item>> items = new HashMap<>();
    List<Item> itemsList = new ArrayList<>();
    SearchStrategy searchStrategy;
    OrderByStrategy orderByStrategy;


    public InventoryService(){
        searchStrategy = new DefaultSearchStrategy(this);
        orderByStrategy = new OrderByPriceStragegy();

    }

    public Item getItem(String category, String brand){
        Map<String, Item> brandItem = items.get(category);
        if (brandItem == null || brandItem.get(brand) == null) {
            throw new ItemNotPresentException();
        }
        Item item = brandItem.get(brand);
        return  item;
    }
    public Item addItem(String category, String brand, double mrp, double price){
        Item item = new Item(++itemId, category, brand, mrp, price, 0);
        Map<String, Item> brandItem = items.getOrDefault(category, new HashMap<>());
        brandItem.put(brand, item);
        itemsList.add(item);
        items.put(category, brandItem);
        return item;
    }
    public void addInventory(String category, String brand, int quantity){
        Map<String, Item> brandItem = items.get(category);
        if (brandItem.get(brand) == null) {
            throw new ItemNotPresentException();
        }
        Item item = brandItem.get(brand);
        item.setQuantity(quantity);
    }
    public void updatePrice(String category, String brand, PriceUpdateType priceUpdateType, double changePercentage){
        Item item = getItem(category, brand);
        switch (priceUpdateType) {
            case FLAT_INCREASE_ON_PRICE :
                item.setPrice(item.getPrice() + item.getPrice() * changePercentage / 100);
                break;
            case FLAT_DECREASE_ON_PRICE:
                item.setPrice(item.getPrice() + item.getPrice() * changePercentage / 100);
                break;
            case DECREASE_PRICE_ON_MRP:
                item.setMrp(item.getMrp() - item.getMrp() * changePercentage / 100);

        }
    }
    public void sellItem(String category, String brand, int quantity){
        Item item = getItem(category, brand);
        if (item.getQuantity() < quantity) {
            throw new ItemNotPresentException();
        }
        item.setQuantity(item.getQuantity() - quantity);
    }
    public List<Item> searchItems(List<String> brands, List<String> categories, double[] priceRange, int[] quantityRange) {
        return  this.searchStrategy.searchItems(brands, categories, priceRange, quantityRange);
    }
    public Map<String, Map<String, Item>> getItems(){
        return items;
    }
}
