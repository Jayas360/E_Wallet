package com.jbdl.transaction_service.Repository;

import com.jbdl.transaction_service.enums.TransactionStatus;
import com.jbdl.transaction_service.model.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

    @Transactional
    @Modifying
    @Query("update transaction t set t.transactionStatus=:status, t.transactionMessage=:message where t.transactionId=:txnId")
    public void updateStatus(String txnId, TransactionStatus status, String message);

    List<Transaction> findBySenderId(String senderId);
}
