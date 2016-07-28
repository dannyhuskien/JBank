package com.allstate;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by localadmin on 7/28/16.
 */
public class Bank {
    private String id;
    private String name;
    private ArrayList<Client> clients;

    public Bank(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.clients = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void addClient(String name){
        Client c = new Client(name);
        this.clients.add(c);
    }
    public void removeClient(String cId){
        Optional<Client> c = this.getClients().stream().filter(client -> client.getId() == cId)
                .findFirst();//Collectors);
        if (c.isPresent()){
            c.get().getAccounts().forEach(account -> account.closeAccount());
        }
        c.get().setIsActive(false);
    }
}
