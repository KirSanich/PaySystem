package com.example.paysystem.config;

import com.example.paysystem.entity.Role;
import com.example.paysystem.entity.User;
import com.example.paysystem.exception.UserWithUsernameNotFound;
import com.example.paysystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("securityService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UserWithUsernameNotFound("No found user with username:" + username));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        UserDetails build = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(mapRolesToAuthorities(user.getRoles()))
                .build();
        System.out.println(build.getAuthorities());
        System.out.println(build.getUsername());
        return build;

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getAuthorityCollection().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getDescription()))
                    .forEachOrdered(authorities::add);
        }

        return authorities;
    }
}
