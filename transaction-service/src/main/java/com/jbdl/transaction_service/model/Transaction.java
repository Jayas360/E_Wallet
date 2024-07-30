package com.jbdl.transaction_service.model;

import com.jbdl.transaction_service.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String senderId;

    @Column(nullable = false)
    private String receiverId;

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private double amount;


    private String purpose;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    private String transactionMessage;
}
