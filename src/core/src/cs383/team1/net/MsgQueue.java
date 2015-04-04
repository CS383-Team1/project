package cs383.team1.net;

import java.util.*;
import com.badlogic.gdx.Gdx;

public class MsgQueue implements Subject {
	private Queue<String> msgs;
	private List<Observer> observers;

	public MsgQueue() {
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

	public synchronized void addMsg(String msg) {
		msgs.offer(msg);
		update();
	}
}
