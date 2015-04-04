package cs383.team1.net;

import java.io.*;
import java.net.*;
import com.badlogic.gdx.Gdx;

public class ServerThread implements Runnable, Observer {
	MsgQueue msgs;
	Socket sock;
	PrintWriter out;
	BufferedReader in;

	public ServerThread(MsgQueue mq, Socket s) throws IOException {
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
				msgs.addMsg(line);
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
