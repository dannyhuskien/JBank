package com.allstate;

import java.util.Date;
import java.util.Random;

/**
 * Created by localadmin on 7/28/16.
 */
public class Transaction {
    private String id;
    private TransactionType transactionType;
    private float amount;
    private Date date;

    public Transaction(float amount, TransactionType transactionType) {
        Random rand = new Random();
        this.id = String.valueOf(Math.abs(rand.nextLong()));
        this.date = new Date();
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TransactionId: " + this.id + " date: " + this.date + " Type: " + this.transactionType + " amount: " + this.amount;
    }
}
