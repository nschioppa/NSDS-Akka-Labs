package com.contacts;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class ClientActor extends AbstractActor {

    private final ActorRef server;

    public ClientActor(ActorRef server) {
        this.server = server;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(PutMsg.class, msg -> {
                server.tell(msg, getSelf());
            })
            .match(GetMsg.class, msg -> {
                server.tell(msg, getSelf());
            })
            .match(String.class, response -> {
                System.out.println("Server response: " + response);
            })
            .build();
    }

    public static Props props(ActorRef server) {
        return Props.create(ClientActor.class, () -> new ClientActor(server));
    }
}