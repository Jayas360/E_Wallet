package com.jbdl.transaction_service.controller;

import com.jbdl.transaction_service.config.SecurityConfig;
import com.jbdl.transaction_service.request.TransactionRequest;
import com.jbdl.transaction_service.response.TransactionResponse;
import com.jbdl.transaction_service.service.TransactionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    @RequestMapping("/transaction/initiate")
    public ResponseEntity<TransactionResponse> initiateTransaction(@RequestBody TransactionRequest transactionRequest){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String senderId = userDetails.getUsername();
        System.out.println("sender id : " + senderId);

        TransactionResponse transactionResponse = transactionService.processTransaction(senderId, transactionRequest);
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/transaction/history")
    public ResponseEntity<List<TransactionResponse>> getTransactionHistory(@RequestParam(value = "mobile", required = false) String mobileNo){
        System.out.println("fetching history for " + mobileNo);
        List<TransactionResponse> responseList = transactionService.getTransaction(mobileNo);
        return new ResponseEntity<List<TransactionResponse>>(responseList, HttpStatus.OK);
    }
}
