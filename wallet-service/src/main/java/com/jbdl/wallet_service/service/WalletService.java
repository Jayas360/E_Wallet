package com.jbdl.wallet_service.service;

import com.jbdl.wallet_service.Commons;
import com.jbdl.wallet_service.enums.UserIdentifier;
import com.jbdl.wallet_service.model.Wallet;
import com.jbdl.wallet_service.repository.WalletRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Value("${wallet.initial.balance}")
    private double initialAmount;

    public void createWalletAccount(String data){
        JSONObject userDetail = new JSONObject(data);
        String userName = userDetail.optString(Commons.USER_NAME);
        String mobileNo = userDetail.optString(Commons.USER_MOBILE);
        String userIdentifier = userDetail.optString(Commons.USER_IDENTIFIER);
        String identifierValue = userDetail.optString(Commons.USER_IDENTIFIER_VALUE);

        Wallet wallet = Wallet.builder().userName(userName).mobileNo(mobileNo).userIdentifier(UserIdentifier.valueOf(userIdentifier))
                .userIdentifierValue(identifierValue).balance(initialAmount).build();

        System.out.println("wallet details saved in db");
        walletRepository.save(wallet);

    }
}
