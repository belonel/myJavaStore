package com.lider.BlockNoteWebApp.controller;

import com.lider.BlockNoteWebApp.Service.MailSender;
import com.lider.BlockNoteWebApp.domain.Order;
import com.lider.BlockNoteWebApp.domain.OrderDetail;
import com.lider.BlockNoteWebApp.domain.Product;
import com.lider.BlockNoteWebApp.domain.User;
import com.lider.BlockNoteWebApp.repos.OrderDetailRepo;
import com.lider.BlockNoteWebApp.repos.OrderRepo;
import com.lider.BlockNoteWebApp.repos.ProductRepo;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart") //чтобы в каждом методе отдельно не прописывать
public class CartController {

    @Autowired // This means to get the bean called ProductRepo
    // Which is auto-generated by Spring, we will use it to handle the data
    private OrderRepo OrderRepo;
    @Autowired
    private OrderDetailRepo OrderDetailRepo;
    @Autowired
    private MailSender mailSender;
    @Value("${upload.path}") // Using Spring Expression language. Searching for upload path in properties
    private String uploadPath; //

    @GetMapping
    public String cartView(
            @AuthenticationPrincipal User user,
            Map<String, Object> model
    ) {
        Order order = OrderRepo.findByCustomer(user);
        List<OrderDetail> orderDetails = OrderDetailRepo.findAllByOrder(order);
        if (order == null) {
            order = new Order(new Date(), 0, user, user.getEmail());
            OrderRepo.save(order);
        }

        model.put("user", user);
        model.put("order", order);
        model.put("orderdetails", orderDetails);

        //Проверяем, достаточно ли средств, если нет, isNotEnoughCoins = true);
        if (user.getMoney() < order.getAmount()) {
            model.put("isNotEnoughCoins", true);
        }

        return "cart";
    }

    @PostMapping
    public String updateCart(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) BigInteger productId,
            @RequestParam(required = false) BigInteger orderId,
            @RequestParam(required = false, defaultValue = "-1") Integer count,
            @RequestParam(required = false) String email,
            @RequestParam(required = false, defaultValue = "false") boolean isCheckoutRequest,
            @RequestParam(required = false, defaultValue = "false") boolean isDeleteRequest,
            @RequestParam(required = false, defaultValue = "false") boolean isUpdateRequest,
            @RequestParam(required = false, defaultValue = "false") boolean isSetEmailRequest,
            Model model
    ) {
        Order order = OrderRepo.findByCustomer(user);
        if (isSetEmailRequest && email != null) {// Была нажата Да рядом с полем ввода e-mail
            order.setCustomerEmail(email);
            OrderRepo.save(order);
        }
        else if (isCheckoutRequest) {// Была нажата кнопка Отправить
            if (order.getAmount() <= 0)
                model.addAttribute("isNotEnoughCoins", true);
            else {
                //отправляем письмо с картинкой на почту.
                findAndSendAllOrderImages(user, order, orderId);

                Integer currentCash = user.getMoney();
                user.setMoney(currentCash - order.getAmount());

                List<OrderDetail> ods = OrderDetailRepo.findAllByOrder(order);
                for (OrderDetail od : ods) {
                    OrderDetailRepo.delete(od);
                }
                OrderRepo.delete(order);
                return "success";
            }
        }
        else if (count == -1 || isDeleteRequest) {//DELETE REQUEST. Была нажата кнопка Remove
            updateOrder(productId,0, user, model);
//            OrderDetailRepo.deleteByProductId(productId);
            OrderDetail orderDetail = OrderDetailRepo.findByProductId(productId);
            OrderDetailRepo.delete(orderDetail);
        }
        else if (isUpdateRequest) { //Была нажата кнопка OK
            updateOrder(productId, count, user, model);
        }

        List<OrderDetail> orderDetails = OrderDetailRepo.findAllByOrder(order);
        model.addAttribute("order", order);
        model.addAttribute("orderdetails", orderDetails);

        model.addAttribute("user", user);
        //Проверяем, достаточно ли средств, если нет, isNotEnoughCoins = true);
        if (user.getMoney() < order.getAmount()) {
            model.addAttribute("isNotEnoughCoins", true);
        }
        //возвращаем на страницу Корзины
        return "cart";
    }

    private void updateOrder(
            BigInteger productId,
            Integer count,
            User user,
            Model model
    ) {
        //Updating Order details
        OrderDetail productDetails = OrderDetailRepo.findByProductId(productId);

        productDetails.setQuanity(count);
        Integer prevProductAmount = productDetails.getAmount();
        Integer newProductAmount = count * productDetails.getPrice();
        productDetails.setAmount(newProductAmount);

        OrderDetailRepo.save(productDetails);

        //Updating OrderS
        Order order = OrderRepo.findByCustomer(user);
        Integer prevOrderAmount = order.getAmount();
        order.setAmount(prevOrderAmount - prevProductAmount + newProductAmount);

        OrderRepo.save(order);

        //Adding everything on th page
//        List<OrderDetail> orderDetails = OrderDetailRepo.findAllByOrder(order);
//
//        model.addAttribute("order", order);
//        model.addAttribute("orderdetails", orderDetails);
    }

    private void findAndSendAllOrderImages(User user, Order order, BigInteger orderId) {
        List<OrderDetail> orderDetails = OrderDetailRepo.findAllByOrder(order);
        List<String> fileNames = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            String filename = orderDetail.getProduct().getImageFileName();
            fileNames.add(uploadPath + "/" + filename);
        }
        mailSender.sendWithFiles(
                order.getCustomerEmail(),
                "Ваш заказ с JavaStore",
                "Привет!" +
                        "Во вложении твой заказ.\n" +
                        "Ты можешь покупать лучшие мемы и картинки без авторских прав на нашем маркетплейсе.\n" +
                        "Если хочешь получать больше первоклассного контента, размещай свои товары, " +
                        "получай 100% койнов с каждой покупки и наслаждайся своим продвижением!",
                fileNames
        );
    }
}
