package cs383.team1.net;

import java.util.*;

public class MsgQueue implements Subject {
	private Queue<String> msgs;
	private List<Observer> observers;

	public MsgQueue() {
		msgs = new LinkedList<String>();
	}

	public void attach(Observer o) {
		observers.add(o);
	}

	public void detach(Observer o) {
		observers.remove(o);
	}

	public void update() {
		String msg;

		msg = msgs.remove();

		for (Observer o : observers) {
			o.update(msg);
		}
	}

	public synchronized void addMsg(String msg) {
		msgs.add(msg);
		update();
	}
}
