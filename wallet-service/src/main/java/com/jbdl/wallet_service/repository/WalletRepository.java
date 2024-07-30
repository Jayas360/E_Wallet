package com.jbdl.wallet_service.repository;

import com.jbdl.wallet_service.model.Wallet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    public Wallet findByMobileNo(String mobileNo);

    @Modifying
    @Transactional
    @Query("update wallet w set w.balance=w.balance+:amount where w.mobileNo=:mobileNo")
    public void updateBalance(String mobileNo, Double amount);
}
