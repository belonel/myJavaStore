package com.lider.BlockNoteWebApp.Service;

import com.lider.BlockNoteWebApp.domain.Role;
import com.lider.BlockNoteWebApp.domain.User;
import com.lider.BlockNoteWebApp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender mailSender;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        userRepo.save(user);

        return true;
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false; //Пользователь не добавлен, т.к. уже найден в базе данных
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setMoney(1000);
        user.setActivationCode(UUID.randomUUID().toString());

        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) { //будем отправлять сообщение только для непустой строчки
            String message = String.format(
                    "Привет, %s! \n" +
                            "Добро пожаловать в маркетплейс мемов и картинок JavaStore." +
                            "Для активации профиля перейди по ссылке: " +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }

        return true;
    }
}
