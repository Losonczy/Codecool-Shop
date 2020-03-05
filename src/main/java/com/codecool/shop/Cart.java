package com.codecool.shop;

import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Cart {
    private List<Product> productsInCart = new ArrayList<>();
    private static final Cart INSTANCE = new Cart();

    public void addProduct(Product product) {

        boolean isContain = false;

        for (Product value : productsInCart) {
            if (product.getName().equals(value.getName())) {
                isContain = true;
                float price = value.getDefaultPrice();
                int quantity = value.getQuantity();
                value.setDefaultPrice(price + product.getDefaultPrice());
                value.setQuantity(quantity + 1);

            }
        }
        if (!isContain) {
            productsInCart.add(product);
        }

    }

    public void removeProduct(Product product) {

        int productIndex = 0;
        for(Product item :productsInCart){
            if(product.getId() == item.getId()){
                productIndex = productsInCart.indexOf(item);
            }
        }

        productsInCart.remove(productIndex);

    }


    public void changeQuantity(int id, int quantity) {
        for (Product item : productsInCart) {
            if (item.getId() == id) {
                item.setQuantity(quantity);

            }
        }
    }

    public Product getNewItem() {
        return productsInCart.get(productsInCart.size() - 1);
    }

    public List<Product> getAllProductsInCart() {
        return productsInCart;
    }


    public HashMap<Product, Integer> getCountedProduct() {
        HashMap<Product, Integer> countedProducts = new HashMap<>();
        for (Product product : productsInCart) {
            countedProducts.put(product, product.getQuantity());
        }
        return countedProducts;
    }

    public int getCostOfCart() {
        HashMap<Product, Integer> countedProducts = getCountedProduct();
        int sum = 0;
        for (Product product : countedProducts.keySet()) {
            sum += (product.getDefaultPrice() * countedProducts.get(product));
        }
        return sum;
    }

    public int getCountOfCart() {
//        HashMap<Product, Integer> countedProducts = getCountedProduct();
        int count = 0;
//        for (Product product : countedProducts.keySet()) {
//            count += (countedProducts.get(product));
//        }
        for(Product item: productsInCart){
            count += item.getQuantity();
        }
        return count;
    }

    public static Cart getInstance() {
        return INSTANCE;
    }

}