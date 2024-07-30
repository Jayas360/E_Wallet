package com.jbdl.transaction_service.service;

import com.jbdl.transaction_service.Repository.TransactionRepo;
import com.jbdl.transaction_service.enums.TransactionStatus;
import com.jbdl.transaction_service.model.Transaction;
import com.jbdl.transaction_service.request.TransactionRequest;
import com.jbdl.transaction_service.response.TransactionResponse;
import com.jbdl.wallet_service.Commons;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public TransactionResponse processTransaction(String senderId, TransactionRequest transactionRequest) {
        String uniqueId = UUID.randomUUID().toString();
        Transaction transaction = Transaction.builder().senderId(senderId).receiverId(transactionRequest.getReceiverId()).transactionId(uniqueId)
                .amount(transactionRequest.getAmount()).purpose(transactionRequest.getPurpose()).transactionStatus(TransactionStatus.INITIATED)
                .transactionMessage("test transaction").build();

        JSONObject txnDetail = new JSONObject();
        txnDetail.put(Commons.SENDER_ID, senderId);
        txnDetail.put(Commons.RECEIVER_ID, transactionRequest.getReceiverId());
        txnDetail.put(Commons.TXN_AMOUNT, transactionRequest.getAmount());
        txnDetail.put(Commons.TXN_ID, uniqueId);

        TransactionResponse transactionResponse = TransactionResponse.builder().amount(transactionRequest.getAmount()).status(TransactionStatus.INITIATED)
                .purpose(transactionRequest.getPurpose()).sentTo(transactionRequest.getReceiverId()).build();

        try {
            Transaction txn = transactionRepo.save(transaction);
            kafkaTemplate.send(Commons.TXN_INITIATION_TOPIC, txnDetail.toString());
            System.out.println("txn data pushed to kafka topic : " + Commons.TXN_INITIATION_TOPIC);
        }catch (Exception e){
            transactionResponse.setStatus(TransactionStatus.FAILED);
            System.out.println(e.getMessage());
        }
        return transactionResponse;
    }

    public void updateTransactionStatus(String transactionId, TransactionStatus status, String message){
        transactionRepo.updateStatus(transactionId, status, message);
    }

    public List<TransactionResponse> getTransaction(String mobileNo){
        List<Transaction> transactions = transactionRepo.findBySenderId(mobileNo);

        List<TransactionResponse> responseList = new ArrayList<>();
        for(Transaction transaction : transactions) {
            TransactionResponse txnResponse = TransactionResponse.builder().amount(transaction.getAmount()).status(transaction.getTransactionStatus())
                    .sentTo(transaction.getReceiverId()).purpose(transaction.getPurpose()).build();
            responseList.add(txnResponse);
        }

        return responseList;
    }

}
