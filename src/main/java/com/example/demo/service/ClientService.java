package com.example.demo.service;

import com.example.demo.domain.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    List<Client> clients = new ArrayList<>();

    public List<Client> getClients(){

        Client client1 = new Client();
        client1.setId(1);
        client1.setNom("imane");
        client1.setPrenom("erguiti");

        Client client2 = new Client();
        client2.setId(2);
        client2.setNom("oussama");
        client2.setPrenom("makhlouk");

        Client client3 = new Client();
        client3.setId(3);
        client3.setNom("yassine");
        client3.setPrenom("jorti");

        clients.add(client1);
        clients.add(client2);
        clients.add(client3);

        return clients;

    }

    public void addUser(Client client) {
        clients.add(client);
    }

    public void delete(Long id) {
        clients.removeIf(client -> id.equals(client.getId()));
    }

}
