package com.flipkart.strategy;

import com.flipkart.models.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SearchStrategy {


    List<Item> searchItems(List<String> category, List<String> brands, double[] priceRange, int[] quantityRange);
}
