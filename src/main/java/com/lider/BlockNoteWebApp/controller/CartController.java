package com.lider.BlockNoteWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/cart") //чтобы в каждом методе отдельно не прописывать
public class CartController {

    @GetMapping
    public String cartView(Map<String, Object> model) {
        //model.put();
        return "cart";
    }

    @PostMapping
    public String addToCart(Model model) {
        return "cart";
    }
}
