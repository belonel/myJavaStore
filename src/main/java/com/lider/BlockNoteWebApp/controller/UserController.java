package com.lider.BlockNoteWebApp.controller;

import com.lider.BlockNoteWebApp.domain.Role;
import com.lider.BlockNoteWebApp.domain.User;
import com.lider.BlockNoteWebApp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user") //чтобы в каждом методе отдельно не прописывать
@PreAuthorize("hasAuthority('ADMIN')") //дает доступ только Админу
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList"; //Возвращаем имя файла
    }

    @GetMapping("/{user}")
    public String userEditForm(
            @PathVariable User user,
            Model model
    ) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form, //for role
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear(); //очищаем роли

        //пробегаем по всем ролям пользователя
        for (String key : form.keySet()) {
            if (roles.contains(key)) { // в form попадают только роли с галочкой.
                user.getRoles().add(Role.valueOf(key)); // задаем те роли, которые обнаружили в form
            }
        }
        userRepo.save(user);
        return "redirect:/user";
    }
}
