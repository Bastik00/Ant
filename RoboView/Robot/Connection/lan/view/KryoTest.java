package de.hska.lat.robot.connection.lan.view;


import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class KryoTest {

	String host = "192.168.178.42";
	int port = 2330;
	int timeout = 3000;

	public KryoTest() throws Exception {
		Server server = new Server();

		server.addListener(new Listener() {
			@Override
			public void connected(Connection connection) {
				System.out.println("connected "
						+ connection.getRemoteAddressTCP().getHostName());
			}
		});
		server.start();
		server.bind(port);

		Client client = new Client();
		client.start();
		client.connect(timeout, host, port);
		Thread.sleep(1000);
	}

	public static void main(String[] args) throws Exception {
		new KryoTest();
	}

}
