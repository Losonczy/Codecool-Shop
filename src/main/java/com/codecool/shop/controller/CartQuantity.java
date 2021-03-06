package com.codecool.shop.controller;

import com.codecool.shop.Cart;
import com.codecool.shop.CartItem;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/cartQuantity"})
public class CartQuantity extends HttpServlet {
    Cart cart= Cart.getInstance();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        String response=req.getReader().lines().collect(Collectors.joining());

        CartItem cartItemId = gson.fromJson(response, CartItem.class);
        cart.changeQuantity(cartItemId.getId(),cartItemId.getQuantity());

    }
}
