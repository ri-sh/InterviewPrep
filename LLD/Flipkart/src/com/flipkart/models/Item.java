package com.flipkart.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {
    int id;
    private String category;
    private String brand;
    private double mrp;
    private double price;
    private int quantity;

}
