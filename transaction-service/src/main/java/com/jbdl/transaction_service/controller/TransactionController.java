package com.jbdl.transaction_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @PostMapping
    @RequestMapping("/initiate/transaction")
    public String initiateTransaction(@RequestBody ){

    }
}
