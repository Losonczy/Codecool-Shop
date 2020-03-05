package com.codecool.shop.controller;

import com.codecool.shop.Cart;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.w3c.dom.ls.LSOutput;
import com.codecool.shop.dao.implementation.CheckoutDaoMem;
import com.codecool.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {
    Cart cart = Cart.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("itemsInCart", cart.getCountedProduct());
        context.setVariable("quantityOfItems", cart.getCountOfCart());
        context.setVariable("costOfCart", cart.getCostOfCart());

        engine.process("product/checkout.html", context, resp.getWriter());
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getReader().lines().collect(Collectors.joining());

        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");

       /* String cardname = req.getParameter("cardname");
        String cardnumber = req.getParameter("cardnumber");
        String month = req.getParameter("month");
        String year = req.getParameter("year");
        String cvv = req.getParameter("cvv");*/

        ArrayList<String> personal_data = new ArrayList<>();
        personal_data.add(fullname);
        personal_data.add(email);
        personal_data.add(address);
        personal_data.add(city);
        personal_data.add(zip);

        try {
            CheckoutDaoMem.getInstance().add(personal_data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<String> items = new ArrayList<>();

        String purchasedItems = "";
        for (Product item : cart.getAllProductsInCart()) {
            if (cart.getAllProductsInCart().indexOf(item) == cart.getAllProductsInCart().size() - 1) {
                purchasedItems += String.valueOf(item.getId());
            } else {
                purchasedItems += String.valueOf(item.getId()) + ",";

            }
        }
        items.add(purchasedItems);
        items.add(String.valueOf(cart.getCostOfCart()));

        System.out.println(items);

        try {
            CheckoutDaoMem.getInstance().addToHistory(items);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
