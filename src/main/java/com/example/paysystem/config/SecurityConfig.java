package com.example.paysystem.config;

import com.example.paysystem.security.PaySystemPasswordEncoder;
import com.example.paysystem.security.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final UserService userService;

    @Autowired
    private final PaySystemPasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users/{id}/**").authenticated()
                .antMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/users/update/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/save/**").hasAuthority("WRITE")
                .antMatchers(HttpMethod.DELETE, "/users/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/accounts/{id}/**").authenticated()
                .antMatchers(HttpMethod.GET, "/accounts/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/accounts/create/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and().logout().logoutSuccessUrl("/");

    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder.passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }
}
