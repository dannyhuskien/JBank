package com.allstate;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.List;

/**
 * Created by localadmin on 7/28/16.
 */
public class Account {
    private String id;
    private AccountType type;
    private float balance;
    private boolean isClosed;
    private ArrayList<Transaction> transactions;
    private byte overdraftCount;
    public static final float overDraftFee = -25f;

    public Account(AccountType type) {
        this.type = type;
        this.id = String.valueOf(UUID.randomUUID());
        this.balance = 0f;
        this.transactions = new ArrayList<>();
        //this.isClosed = false;
        this.overdraftCount = 0;

    }

    public String getId() {
        return id;
    }

    public AccountType getType() {
        return type;
    }

    public float getBalance() {
        return balance;
    }

    public boolean getIsClosed() {
        return isClosed;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void withdrawal(float amount) {
        if (this.isClosed){
            return;
        }
        if (amount > this.balance){
            this.balance += overDraftFee;
            Transaction t1 = new Transaction(overDraftFee, TransactionType.FEE);
            this.transactions.add(t1);
            this.overdraftCount += 1;
            if(this.overdraftCount >= 3){
                this.isClosed = true;
            }
        } else {
            this.balance -= amount;
            Transaction t1 = new Transaction(amount, TransactionType.WITHDRAWAL);
            this.transactions.add(t1);
        }
    }

    public void deposit(float amount){
        if (this.isClosed){
            return;
        }
        this.balance += amount;
        Transaction t1 = new Transaction(amount, TransactionType.DEPOSIT);
        this.transactions.add(t1);
    }

    public  List<Float> filterTransactions(TransactionType transType){

        List<Float> filteredAmounts = this.transactions.stream()
                .filter(transaction -> transaction.getTransactionType() == transType)
                .map(transaction -> transaction.getAmount()).collect(Collectors.toList());
        return filteredAmounts;

    }

    public void closeAccount(){
        withdrawal(this.balance);
        isClosed = true;
    }

}
