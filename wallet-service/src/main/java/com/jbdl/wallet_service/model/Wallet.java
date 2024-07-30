package com.jbdl.wallet_service.model;

import com.jbdl.wallet_service.enums.UserIdentifier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Entity(name = "wallet")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String mobileNo;

    @Enumerated(value = EnumType.STRING)
    private UserIdentifier userIdentifier;

    @Column(nullable = false)
    private String userIdentifierValue;

    @Value(value = "${account.balance.initial}")
    private double balance;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;
}
