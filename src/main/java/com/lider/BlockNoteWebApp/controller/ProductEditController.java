package com.lider.BlockNoteWebApp.controller;

import com.lider.BlockNoteWebApp.domain.OrderDetail;
import com.lider.BlockNoteWebApp.domain.Product;
import com.lider.BlockNoteWebApp.domain.User;
import com.lider.BlockNoteWebApp.repos.OrderDetailRepo;
import com.lider.BlockNoteWebApp.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Controller
public class ProductEditController {

    @Autowired
    private com.lider.BlockNoteWebApp.repos.ProductRepo ProductRepo;
    @Autowired
    private OrderDetailRepo OrderDetailRepo;

    @GetMapping("/main/{productId}/edit")
    public String viewEditPage(
            @PathVariable("productId") BigInteger productId,
            Model model
    ) {
        Product product = ProductRepo.findById(productId).get();
        model.addAttribute("product", product);

        return "productEdit";
    }

//     Это костыль. Т.к. я не подумал, что цена товара есть ещё и в табличке OrderDetails.
//     Price оттуда можно убрать, но придется пересмотреть много кода, чтобы заменить места, где он используется.
    private void changeProductPriceInOrderDetails (
            User user,
            BigInteger productId,
            int price
    ) {
        OrderDetail orderDetail = OrderDetailRepo.findByProductId(productId);
        orderDetail.setPrice(price);
        OrderDetailRepo.save(orderDetail);
    }

    @PostMapping("/main/{productId}/edit")
    public String updateProductDetail(
            @AuthenticationPrincipal User user,
            @PathVariable("productId") BigInteger productId,
            @RequestParam String productName,
            @RequestParam int productCost,
            @RequestParam String productShortDescription,
            @RequestParam String productLongDescription,
            Model model
    ) {
        //changeProductPriceInOrderDetails(user, productId, productCost);

        Product product = ProductRepo.findById(productId).get();
        product.setName(productName);
        product.setCost(productCost);
        product.setShortDescription(productShortDescription);
        product.setLongDescription(productLongDescription);

        ProductRepo.save(product);

        model.addAttribute("product", product);

        return "productEdit";
    }
}
