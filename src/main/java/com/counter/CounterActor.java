package com.counter;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class CounterActor extends AbstractActor {

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
	}

	void onDecrement(SimpleMessage msg) {
		--counter;
		System.out.println("Counter decremented to " + counter);
	}

	static Props props() {
		return Props.create(CounterActor.class);
	}

}
