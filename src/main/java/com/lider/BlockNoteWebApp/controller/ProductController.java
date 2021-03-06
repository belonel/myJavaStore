package com.lider.BlockNoteWebApp.controller;

import com.lider.BlockNoteWebApp.domain.Order;
import com.lider.BlockNoteWebApp.domain.OrderDetail;
import com.lider.BlockNoteWebApp.domain.Product;
import com.lider.BlockNoteWebApp.domain.User;
import com.lider.BlockNoteWebApp.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.Date;

@Controller
public class ProductController {

    @Autowired // This means to get the bean called ProductRepo
    // Which is auto-generated by Spring, we will use it to handle the data
    private ProductRepo ProductRepo;

    @Autowired // This means to get the bean called ProductRepo
    // Which is auto-generated by Spring, we will use it to handle the data
    private com.lider.BlockNoteWebApp.repos.OrderRepo OrderRepo;
    @Autowired
    private com.lider.BlockNoteWebApp.repos.OrderDetailRepo OrderDetailRepo;

    @GetMapping("/main/{productId}")
    public String watchProduct(
            @PathVariable("productId") BigInteger productId,
            Model model
    ) {
        Product product = ProductRepo.findById(productId).get();

        model.addAttribute("product", product);

        return "productDetails";
    }

    @PostMapping("/main/{productId}")
    public String addProduct(
            @AuthenticationPrincipal User user,
            @PathVariable("productId") BigInteger productId,
            Model model
    ) {
        Order order = OrderRepo.findByCustomer(user);

        Product product = ProductRepo.findById(productId).get();

        Date date = new Date();
        if (order == null) {
            //Проверить user на наличие. Если не зарегистрирован непонятно что делать
            order = new Order(date, 0, user, user.getEmail());
        }

        int price = product.getCost();
        int quanity = 1;

        OrderDetail orderdetails = OrderDetailRepo.findByProductId(productId);
        if (orderdetails == null) {
            orderdetails = new OrderDetail(quanity*price, price, quanity, order, product);
        }
        else {
            quanity += orderdetails.getQuanity();
            orderdetails.setQuanity(quanity);
            orderdetails.setAmount(quanity*price);
        }
        Integer am = order.getAmount();
        order.setAmount(am + price);

        OrderRepo.save(order);
        OrderDetailRepo.save(orderdetails);

        model.addAttribute("product", product);
        return "productDetails";
    }
}
