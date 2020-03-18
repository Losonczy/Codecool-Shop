package com.codecool.shop.Registration;

import com.codecool.shop.Cart;
import com.codecool.shop.User;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.RegisterDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.RegisterDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/registration"})
public class RegisterController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        Cart cart = Cart.getInstance();

        try {
            context.setVariable("products", productDataStore.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        context.setVariable("itemsInCart", cart.getAllProductsInCart());

        RegisterDao registration = RegisterDaoMem.getINSTANCE();

        String email = req.getParameter("email");
        String username = req.getParameter("uname");
        String password = req.getParameter("pword");

        User user = new User(username, password);
        try {
            if (registration.find(username) != null) {
                registration.add(user);
                context.setVariable("registration_feedback", "true");
            }else{
                context.setVariable("registration_feedback", "false");

            }
            engine.process("product/index.html", context, resp.getWriter());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
