package com.allstate;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by localadmin on 7/28/16.
 */
public class Client {
    private String id;
    private String name;
    private boolean isActive;
    private ArrayList<Account> accounts;

    public Client(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.isActive = true;
        this.accounts = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void openAccount(AccountType aType){
        Account a = new Account(aType);
        accounts.add(a);
    }

    public void closeAccount(String acctId){
       Optional<Account> a = this.getAccounts().stream().filter(acct -> acct.getId() == acctId)
               .findFirst();
        a.ifPresent(acct -> acct.closeAccount());

    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}
