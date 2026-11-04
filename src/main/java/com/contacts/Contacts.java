
package com.contacts;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Contacts {

    public static void main(String[] args) throws Exception {

        // Create the ActorSystem
        final ActorSystem sys = ActorSystem.create("ContactsSystem");

        // Create the server actor
        final ActorRef server = sys.actorOf(
            ServerActor.props(), "server"
        );

        // Create the client actor
        final ActorRef client = sys.actorOf(
            ClientActor.props(server), "client"
        );

        // Add contacts
        client.tell(new PutMsg("Nico", "nico@gmail.com"), ActorRef.noSender());
        client.tell(new PutMsg("Marco", "marco@gmail.com"), ActorRef.noSender());
        client.tell(new PutMsg("Luca", "luca@gmail.com"), ActorRef.noSender());

        // Search contacts
        client.tell(new GetMsg("Nico"), ActorRef.noSender());
        client.tell(new GetMsg("Marco"), ActorRef.noSender());
        client.tell(new GetMsg("Unknown"), ActorRef.noSender());

        // Wait before terminating
        System.in.read();

        sys.terminate();
    }
}
