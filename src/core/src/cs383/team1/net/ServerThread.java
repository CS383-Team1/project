package cs383.team1.net;

import java.io.*;
import java.net.*;

public class ServerThread implements Runnable, Observer {
	MsgQueue msgs;
	Socket sock;
	PrintWriter out;
	BufferedReader in;

	public ServerThread(MsgQueue mq, Socket s) throws IOException {
		msgs = mq;
		sock = s;

		out = new PrintWriter(sock.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(
			sock.getInputStream()));
	}

	public void run() {
		String line;

		try {
			while ((line = in.readLine()) != null) {
				msgs.addMsg(line);
			}
		} catch (Exception e) {
			/* do nothing */
		}
	}

	public void update(String str) {
		out.println(str);
	}
}
