package cs383.team1.net;

import java.io.*;
import java.net.*;
import com.badlogic.gdx.Gdx;
 
public class Client {
	private String line;
	private Socket sock;
	private PrintWriter out;
	private BufferedReader in;

	public Client(String host, int port) throws IOException {
		line = new String();
		sock = new Socket(host, port);
		out = new PrintWriter(sock.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(
			sock.getInputStream()));
	}

	private void sendLine(String l) {
		Gdx.app.debug("Client.sendLine", "sending string: " + line);

		out.println(line);
	}

	public void addChar(char ch) {
		if (ch == '\r') {
			sendLine(line + "\n");
			line = "";
		} else {
			line = line.concat(Character.toString(ch));
		}
	}

	public String getLine() {
		return line;
	}

	public String update() {
		String str;

		str = "";

		try {
			if (in.ready()) {
				str = in.readLine();
				Gdx.app.debug("Client:update",
					"recieved message: " + str);
			}
		} catch (Exception e) {
			Gdx.app.error("Client:update", "Error!");
			/* ignore errors */
		}

		return str;
	}
}
