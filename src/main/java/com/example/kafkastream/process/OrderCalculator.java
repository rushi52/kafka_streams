package com.example.kafkastream.process;


import com.example.kafkastream.model.Order;

public class OrderCalculator {

    public static double calculateTotalAmount(Order order) {
        return order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}
