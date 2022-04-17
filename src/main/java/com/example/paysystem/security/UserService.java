package com.example.paysystem.security;

import com.example.paysystem.entity.Role;
import com.example.paysystem.entity.User;
import com.example.paysystem.exception.NoHasAccessForThisInformation;
import com.example.paysystem.exception.user.UserWithUsernameNotFound;
import com.example.paysystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import java.util.Objects;

@Service("securityService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final com.example.paysystem.service.UserService userCommonService;

    @Autowired
    public UserService(UserRepository userRepository, com.example.paysystem.service.UserService userCommonService) {
        this.userRepository = userRepository;
        this.userCommonService = userCommonService;
    }

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UserWithUsernameNotFound("No found user with username:" + username));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(mapRolesToAuthorities(user.getRoles()))
                .build();

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

    public boolean verifyId(Authentication authentication, Long id) {
        UserDetails searcher = loadUserByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
        User user = userCommonService.findUserByUsername(searcher.getUsername());
        System.out.println(searcher.getUsername());
        if(!Objects.equals(user.getId(), id))
        {
            throw new NoHasAccessForThisInformation("You dont have permission for this information");
        }
        return true;
    }
}
