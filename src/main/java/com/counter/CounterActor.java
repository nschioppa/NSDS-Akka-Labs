package com.counter;

import akka.actor.AbstractActor;
import akka.actor.AbstractActorWithStash;
import akka.actor.Props;

public class CounterActor extends AbstractActorWithStash {

	private int counter;

	public CounterActor() {
		this.counter = 0;
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(SimpleMessage.class,
						msg -> msg.getValue() == 1,
						this::onIncrement)
				.match(SimpleMessage.class,
						msg -> msg.getValue() == -1,
						this::onDecrement)
				.build();
	}

	void onIncrement(SimpleMessage msg) {
		++counter;
		System.out.println("Counter increased to " + counter);

		unstashAll();
	}

	void onDecrement(SimpleMessage msg) {
		if (counter > 0) {
			--counter;
			System.out.println("Counter decremented to " + counter);
		} else {
			stash();
		}
	}

	static Props props() {
		return Props.create(CounterActor.class);
	}

}
