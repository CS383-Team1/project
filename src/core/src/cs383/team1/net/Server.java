package cs383.team1.net;

import java.io.*;
import java.net.*;
import com.badlogic.gdx.Gdx;

public class Server implements Runnable {
	public static final int PORT = 13370;

	MsgQueue msgs;
	ServerSocket sock;

	public Server() throws IOException {
		msgs = new MsgQueue();
		sock = new ServerSocket(PORT);
	}

	public void run() {
		Socket s;

		while (true) {
			try {
				s = sock.accept();
				Gdx.app.debug("Server:run", "New connection");
				(new Thread(
					new ServerThread(msgs, s))).start();
			} catch (Exception e) {
				Gdx.app.debug("Server:run", "Error!");
				/* ignore error */
			}
		}
	}

	public static void main(String[] args) {
		try {
			(new Thread(new Server())).start();
		} catch (Exception e) {
			Gdx.app.error("Server:main", "No, you're an exception!");
		}
	}
}
