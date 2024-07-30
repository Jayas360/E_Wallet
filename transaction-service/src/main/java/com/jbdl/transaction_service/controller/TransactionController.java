package com.jbdl.transaction_service.controller;

import com.jbdl.transaction_service.config.SecurityConfig;
import com.jbdl.transaction_service.request.TransactionRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @PostMapping
    @RequestMapping("/initiate/transaction")
    public String initiateTransaction(@RequestBody TransactionRequest transactionRequest){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String senderId = userDetails.getUsername();
        System.out.println("sender id : " + senderId);
        return "transaction initiated!";
    }
}
