package cs383.team1.server.net;

import java.io.*;
import java.net.*;
import com.badlogic.gdx.Gdx;
import cs383.team1.server.util.MessageQueue;

public class Server implements Runnable {
	public static final int PORT = 13370;

	MessageQueue incoming;
	MessageQueue outgoing;
	ServerSocket sock;

	public Server(MessageQueue in, MessageQueue out) {
		incoming = in;
		outgoing = out;

		try {
			sock = new ServerSocket(PORT);
		} catch (IOException e) {
			Gdx.app.error("Server:Server", "Error!");
			/* ignore error */
		}
	}

	public void run() {
		Socket s;
		ServerThread thread;

		while (true) {
			try {
				s = sock.accept();

				Gdx.app.debug("Server:run", "New connection");

				thread = new ServerThread(incoming, s);

				outgoing.attach(thread);

				(new Thread(thread)).start();
			} catch (Exception e) {
				Gdx.app.error("Server:run", "Error!");
				/* ignore error */
			}
		}
	}
}
