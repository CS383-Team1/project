package cs383.team1.net;

import java.io.*;
import java.net.*;

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
				(new Thread(new ServerThread(msgs, s))).start();
			} catch (Exception e) {
				/* ignore error */
			}
		}
	}
}
