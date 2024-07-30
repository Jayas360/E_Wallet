package com.jbdl.transaction_service.consumer;

import com.jbdl.transaction_service.enums.TransactionStatus;
import com.jbdl.transaction_service.service.TransactionService;
import com.jbdl.wallet_service.Commons;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionUpdateConsumer {

    @Autowired
    TransactionService transactionService;

    @KafkaListener(topics = Commons.TXN_PROCESSED_TOPIC, groupId = "transaction_update")
    public void listenTransactionUpdate(String data){
        System.out.println("listening for transaction updates!");

        JSONObject txnUpdate = new JSONObject(data);

        String txnId = txnUpdate.optString(Commons.TXN_ID);
        String status = txnUpdate.optString(Commons.TXN_STATUS);
        String message = txnUpdate.optString(Commons.TXN_MESSAGE);

        transactionService.updateTransactionStatus(txnId, TransactionStatus.valueOf(status), message);
        System.out.println("transaction updated successfully!");
    }
}
