package cs383.team1.server.net;

import java.io.*;
import java.net.*;
import com.badlogic.gdx.Gdx;
import cs383.team1.server.util.MessageQueue;
import cs383.team1.server.util.Observer;

public class ServerThread implements Runnable, Observer {
	MessageQueue msgs;
	Socket sock;
	PrintWriter out;
	BufferedReader in;

	public ServerThread(MessageQueue mq, Socket s) throws IOException {
		Gdx.app.debug("ServerThread:ServerThread", "New thread");
		msgs = mq;
		sock = s;

		msgs.attach(this);

		out = new PrintWriter(sock.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(
			sock.getInputStream()));
	}

	public void run() {
		String line;

		try {
			while ((line = in.readLine()) != null) {
				Gdx.app.debug("ServerThread",
					"New message: " + line);
				msgs.addMessage(line);
			}
		} catch (Exception e) {
			Gdx.app.error("ServerThread:run", "Error!");
			/* do nothing */
		}
	}

	public void update(String str) {
		out.println(str);
	}
}
