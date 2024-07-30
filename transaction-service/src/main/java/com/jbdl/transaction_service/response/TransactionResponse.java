package com.jbdl.transaction_service.response;

import com.jbdl.transaction_service.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
    private double amount;
    private TransactionStatus status;
    private String sentTo;
    private String purpose;
}
