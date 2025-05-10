package com.breakeven.backend.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Transaction {
    public UUID TransactionId;
    public String TransactionName;
    public LocalDateTime TransactionDateTime;
    public Double AmountBeforeGst;
    public Double GstAmount;
    public Double AmountAfterGst;
    public TransactionStatus TransactionStatus;

    public List<PersonTransaction> Payers;
    public List<PersonTransaction> Payees;
    public enum TransactionStatus {
        Pending, Settled;
    }

}
