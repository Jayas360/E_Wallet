package com.jbdl.transaction_service.service;

import com.jbdl.wallet_service.Commons;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("inside custom detail service!");
        String response = restTemplate.getForObject("http://localhost:8080/user-service/user/credentials?mobileNo=" + username, String.class);
        System.out.println(response);
        JSONObject credentials = new JSONObject(response);
        String userName = credentials.optString(Commons.USER_MOBILE);
        String password = credentials.optString(Commons.USER_PASSWORD);
        UserDetails userDetails = User.builder().username(userName).password(passwordEncoder.encode(password)).build();
        System.out.println(userDetails.toString());
        return userDetails;
    }
}
