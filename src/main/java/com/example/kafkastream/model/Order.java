package com.example.kafkastream.model;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private String id;
    private List<Item> items;
    private double totalAmount;


}
