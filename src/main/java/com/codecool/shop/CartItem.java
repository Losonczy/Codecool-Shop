package com.codecool.shop;

public class CartItem {
    private String name;
    private String quantity;
    private int price;

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price=" + price +
                '}';
    }
}
