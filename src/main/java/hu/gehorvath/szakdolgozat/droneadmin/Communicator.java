package hu.gehorvath.szakdolgozat.droneadmin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Communicator implements Runnable {

	private Socket drone;
	private InputStream inputStream;
	private OutputStream outputStream;

	private boolean enabled = true;

	public void run() {

		while (enabled) {
			try {
				drone = new Socket("192.168.4.1", 22000);

				System.out.println("Connected");
				inputStream = drone.getInputStream();
				outputStream = drone.getOutputStream();
				byte buffer;
				while (true) {
					if (inputStream.available() > 0) {
						buffer = (byte) inputStream.read();
						System.out.println(Byte.valueOf(buffer));
						outputStream.write("e".getBytes());
					}
					Thread.sleep(300);
				}

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					drone.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void stop() {
		this.enabled = false;
	}

}
