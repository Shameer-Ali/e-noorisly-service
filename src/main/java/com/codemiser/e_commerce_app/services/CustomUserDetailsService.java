package com.codemiser.e_commerce_app.services;

import com.codemiser.e_commerce_app.modals.AppUser;
import com.codemiser.e_commerce_app.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findUserByUsername(username).orElseThrow(()->new  UsernameNotFoundException("user no found"));
        return User.builder().username(user.getUsername()).password(user.getPassword()).roles(user.getRole().toUpperCase()).build();
    }
}
