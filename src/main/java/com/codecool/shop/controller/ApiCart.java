package com.codecool.shop.controller;

import com.codecool.shop.Cart;
import com.codecool.shop.CartItem;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = {"/apiCart"})
public class ApiCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart=new Cart();
        String response=req.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();
        CartItem cartItem = gson.fromJson(response, CartItem.class);
        System.out.println(cartItem);
        cart.addProduct(cartItem);
        System.out.println(cart.getProductsInCart());
    }

}
