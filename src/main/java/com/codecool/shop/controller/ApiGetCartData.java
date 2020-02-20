package com.codecool.shop.controller;

import com.codecool.shop.Cart;
import com.codecool.shop.CartItemId;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/apiGetCartData"})
public class ApiGetCartData extends HttpServlet {

    Cart cart= Cart.getInstance();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String response = gson.toJson(cart.getCountedProduct());
        System.out.println(cart.getCountedProduct());
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(response);
        out.flush();
    }


}
