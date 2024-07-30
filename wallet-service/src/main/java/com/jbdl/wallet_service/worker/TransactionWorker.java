package com.jbdl.wallet_service.worker;

import com.jbdl.wallet_service.Commons;
import com.jbdl.wallet_service.model.Wallet;
import com.jbdl.wallet_service.service.WalletService;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionWorker {

    @Autowired
    WalletService walletService;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public void processTransaction(String senderId, String receiverId, Double amount, String txnId){

        Wallet senderWallet = walletService.getWalletByMobileNo(senderId);
        Wallet receiverWallet = walletService.getWalletByMobileNo(receiverId);

        Double senderBalance = senderWallet.getBalance();

        String status = "";
        String message = "";

        if(senderWallet == null){
            status = "FAILED";
            message = "sender wallet account does not exist!";
        } else if(receiverWallet == null){
            status = "FAILED";
            message = "receiver wallet account does not exist!";
        } else if(senderBalance < amount) {
            status = "FAILED";
            message = "not sufficient balance!";
        }else{
            processWalletUpdation(senderId, receiverId, amount);
            status = "SUCCESS";
            message = "transaction successfull!";
        }

        JSONObject txnUpdate = new JSONObject();
        txnUpdate.put(Commons.TXN_ID, txnId);
        txnUpdate.put(Commons.TXN_STATUS, status);
        txnUpdate.put(Commons.TXN_MESSAGE, message);

        kafkaTemplate.send(Commons.TXN_PROCESSED_TOPIC, txnUpdate.toString());
        System.out.println("updated transaction details pushed to kafka topic : " + Commons.TXN_PROCESSED_TOPIC);
    }

    @Transactional
    public void processWalletUpdation(String senderId, String receiverId, Double amount) {
        walletService.updateWalletBalance(senderId, -amount);
        walletService.updateWalletBalance(receiverId, amount);
    }
}


