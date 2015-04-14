package cs383.team1.server.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import com.badlogic.gdx.Gdx;

public class MessageQueue implements Subject {
	private Queue<String> msgs;
	private List<Observer> observers;

	public MessageQueue() {
		msgs = new ArrayDeque<String>();
		observers = new ArrayList<Observer>();
	}

	public void attach(Observer o) {
		observers.add(o);
	}

	public void detach(Observer o) {
		observers.remove(o);
	}

	public void update() {
		String msg;

		if ((msg = msgs.poll()) != null) {
			for (Observer o : observers) {
				o.update(msg);
			}
		}
	}

	public synchronized void addMessage(String msg) {
		msgs.offer(msg);
		update();
	}
}
