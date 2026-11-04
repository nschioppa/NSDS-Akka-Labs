package com.contacts;

import java.util.HashMap;
import java.util.Map;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class ServerActor extends AbstractActor {

    private final Map<String, String> contacts = new HashMap<>();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(PutMsg.class, this::onPut)
            .match(GetMsg.class, this::onGet)
            .build();
    }

    void onPut(PutMsg msg) {
        contacts.put(msg.getName(), msg.getEmail());

        System.out.println("Contact saved: " + msg.getName());
    }

    void onGet(GetMsg msg) {
        String email = contacts.get(msg.getName());

        getSender().tell(email != null ? email : "Contact not found", getSelf());
    }

    public static Props props() {
        return Props.create(ServerActor.class);
    }
}