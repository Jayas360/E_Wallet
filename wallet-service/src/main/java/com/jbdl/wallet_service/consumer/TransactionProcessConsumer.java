package com.jbdl.wallet_service.consumer;

import com.jbdl.wallet_service.Commons;
import com.jbdl.wallet_service.worker.TransactionWorker;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionProcessConsumer {

    @Autowired
    TransactionWorker transactionWorker;

    @KafkaListener(topics = Commons.TXN_INITIATION_TOPIC, groupId = "txn_initiated_group")
    public void listenInitiatedTransaction(String data) {
        JSONObject txnDetail = new JSONObject(data);
        String senderId = txnDetail.optString(Commons.SENDER_ID);
        String receiverId = txnDetail.optString(Commons.RECEIVER_ID);
        Double amount = txnDetail.optDouble(Commons.TXN_AMOUNT);
        String txnId = txnDetail.optString(Commons.TXN_ID);

        transactionWorker.processTransaction(senderId, receiverId, amount, txnId);
    }
}
