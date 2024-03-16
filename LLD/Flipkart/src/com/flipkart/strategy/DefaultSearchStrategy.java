package com.flipkart.strategy;

import com.flipkart.models.Item;
import com.flipkart.service.InventoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultSearchStrategy implements SearchStrategy{

    InventoryService inventory;
    public DefaultSearchStrategy(InventoryService inventoryService) {
        this.inventory = inventoryService;
    }

    public List<Item> searchItems(List<String> brands, List<String> categories, double[] priceRange, int[] quantityRange) {
        List<Item> filteredItems = new ArrayList<>();
        for (Map.Entry<String, Map<String, Item>> categoryEntry : inventory.getItems().entrySet()) {
            String category = categoryEntry.getKey();
            if (categories != null && !categories.contains(category)) {
                continue;
            }
            for (Item item : categoryEntry.getValue().values()) {
                if (brands != null && !brands.contains(item.getBrand())) {
                    continue;
                }
                if (priceRange != null && (item.getPrice() < priceRange[0] || item.getPrice() > priceRange[1])) {
                    continue;
                }
                if (quantityRange != null && (item.getQuantity() < quantityRange[0] || item.getQuantity() > quantityRange[1])) {
                    continue;
                }
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }

}
