package com.lider.BlockNoteWebApp.config;

import com.lider.BlockNoteWebApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/registration", "/static/**", "/cart").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

//                jdbcAuthentication()
//                .dataSource(dataSource)  //нужен для того, чтобы менеджер мог ходить в базу данных и искать пользователей и их роли.
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//чтобы система могла найти пользователя по его имени. Порядок и набор полей именно такие:
//                .usersByUsernameQuery("SELECT username, password, active FROM usr WHERE username=?")
// запрос, который помогает спрингу получить список пользователей с их ролями
//                .authoritiesByUsernameQuery("SELECT u.username, ur.roles from usr u INNER JOIN user_role ur ON u.id = ur.user_id WHERE u.username=?");
    }
}