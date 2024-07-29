package com.jbdl.wallet_service.config;

import com.jbdl.wallet_service.Commons;
import com.jbdl.wallet_service.service.WalletService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserDetailListener {

    @Autowired
    WalletService walletService;

    @KafkaListener(topics = Commons.USER_DETAILS_TOPIC, groupId = "wallet_creation")
    public void listenUserDetail(String data){
        System.out.println("user details listened");
        walletService.createWalletAccount(data);
    }
}
