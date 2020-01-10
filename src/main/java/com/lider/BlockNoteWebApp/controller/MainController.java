package com.lider.BlockNoteWebApp.controller;

import com.lider.BlockNoteWebApp.domain.*;
import com.lider.BlockNoteWebApp.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

@Controller
public class MainController {

    @Autowired // This means to get the bean called ProductRepo
                // Which is auto-generated by Spring, we will use it to handle the data
    private ProductRepo ProductRepo;
    @Autowired // This means to get the bean called ProductRepo
    // Which is auto-generated by Spring, we will use it to handle the data
    private com.lider.BlockNoteWebApp.repos.OrderRepo OrderRepo;
    @Autowired
    private com.lider.BlockNoteWebApp.repos.UserRepo UserRepo;
    @Autowired
    private com.lider.BlockNoteWebApp.repos.OrderDetailRepo OrderDetailRepo;

    //получаем переменную пути для картинок
    @Value("${upload.path}") // Using Spring Expression language. Searching for upload path in properties
    private String uploadPath; //


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @AuthenticationPrincipal User user,
            @CookieValue String incart,
            HttpServletResponse response,
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false) boolean onlyMyProducts,
            Model model
    ) {
        Iterable<Product> products = ProductRepo.findAll();

        if (filter != null && !filter.isEmpty())
            products = ProductRepo.findByName(filter);
        else if(onlyMyProducts) {
            products = ProductRepo.findAllByAuthor(user);
        }
        else
            products = ProductRepo.findAll();


        if (incart == "") {
            // create a cookie
            Cookie cookie = new Cookie("incart", "0");
            cookie.setPath("/");
            //add cookie to response
            response.addCookie(cookie);
        }

        model.addAttribute("products", products);
        model.addAttribute("filter", filter);
        model.addAttribute("user", user);

        return "main";
    }

    private void deleteProductFromEverywhere(
            BigInteger productId,
            User user
    ) {
        //Changing Order details count and amount to 0
        OrderDetail productDetails = OrderDetailRepo.findByProductId(productId);
        if(productDetails != null) { //Если товар есть у кого-то в заказах.
            Integer prevProductAmount = productDetails.getAmount();
            productDetails.setAmount(0);

//        OrderDetailRepo.save(productDetails);

            //Changing Order Amount to newAmount without productAmount
            Order order = OrderRepo.findByCustomer(user);
            Integer prevOrderAmount = order.getAmount();
            order.setAmount(prevOrderAmount - prevProductAmount);

            //now deleting all OrderDetails with this Product
            OrderDetailRepo.delete(productDetails);
        }

        //then deleting the Product
        Product product = ProductRepo.findById(productId).get();
        ProductRepo.delete(product);
    }

    //Adding new message to the system
    @PostMapping("/main")
    public String add(
//            @AuthenticationPrincipal User user,
//            @RequestParam String text,
//            @RequestParam String tag,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String shortDescr,
            @RequestParam(required = false, defaultValue = "") String longDescr,
            @RequestParam(required = false, defaultValue = "0") int cost,
            @RequestParam(required = false, name="file") MultipartFile file,
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "0") BigInteger productId,
            @RequestParam(required = false, defaultValue = "false") boolean isDeleteRequest,
            @RequestParam(required = false, defaultValue = "false") boolean isAddToCartRequest,
            @RequestParam(required = false, defaultValue = "false") boolean isAddProductRequest
    ) throws IOException {
        if (isDeleteRequest) {//DELETE REQUEST
            //deliting all instances containing this product... and the product itself
            deleteProductFromEverywhere(productId, user);
        }
        else if(isAddToCartRequest) {
            addToCart(user, productId, model);
        }
        else if (isAddProductRequest) { //запрос на добавление товара
            //Message message = new Message(text, tag, user);
            Product product = new Product(name, shortDescr, longDescr, cost, user, new Date());

            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                //Universe Unique Identificator
                String uuid = UUID.randomUUID().toString();
                String resultFileName = uuid + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFileName)); //загружаем файл
                product.setImageFileName(resultFileName);
            }

            ProductRepo.save(product);
        }

        Iterable<Product> products = ProductRepo.findAll();
        model.addAttribute("products", products);
        model.addAttribute("user", user);
        return "main";
    }

//    @GetMapping(path="/all")
//    public @ResponseBody Iterable<User> getAllUsers() {
//        // This returns a JSON or XML with the users
//        return userRepository.findAll();
//    }

    private void addToCart(
            User user,
            BigInteger productId,
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
            orderdetails.setOrder(order);
            quanity += orderdetails.getQuanity();
            orderdetails.setQuanity(quanity);
            orderdetails.setAmount(quanity*price);
        }

        Integer am = order.getAmount();
        order.setAmount(am + price);

        OrderRepo.save(order);
        OrderDetailRepo.save(orderdetails);
    }
}