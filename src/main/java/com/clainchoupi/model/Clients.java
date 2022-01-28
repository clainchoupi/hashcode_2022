package com.clainchoupi.model;

import java.util.ArrayList;

public class Clients {

    private ArrayList<Client> clients;
    private Integer clientCount;

    public Clients() {
        clients = new ArrayList<Client>();
        clientCount = 0;
    }

    public Integer getClientCount() {
        return clientCount;
    }

    public void setClientCount(Integer clientCount) {
        this.clientCount = clientCount;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void printClients() {
        for (Client client : clients) {
            System.out.println("Likes: " + client.getLikes());
            System.out.println("Dislikes: " + client.getDislikes());
        }
    }

    public void addClient(Client client) {
        clients.add(client);
    }
}
