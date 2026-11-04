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
		.match(IncrementeMessage.class, this::onIncrement)
		.match(DecrementMessage.class, this::onDecrement)
		.build();
	}

	void onMessage(SimpleMessage msg) {
		++counter;
		System.out.println("Counter increased to " + counter);
	}

	void onIncrement(IncrementeMessage msg) {
		++counter;
		System.out.println("Counter increased to " + counter);
	}

	void onDecrement(DecrementMessage msg) {
		--counter;
		System.out.println("Counter decremented to " + counter);
	}

	static Props props() {
		return Props.create(CounterActor.class);
	}

}
