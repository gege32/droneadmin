package hu.gehorvath.szakdolgozat.droneadmin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.LinkedBlockingQueue;

public class Communicator implements Runnable {

	private Socket droneSocket;
	private InputStream inputStream;
	private OutputStream outputStream;
	
	private LinkedBlockingQueue<ControllerData> queue = new LinkedBlockingQueue<ControllerData>(5);

	private boolean enabled = true;

	public void run() {

		while (enabled) {
			try {
				droneSocket = new Socket("192.168.4.1", 22000);

				System.out.println("Connected");
				inputStream = droneSocket.getInputStream();
				outputStream = droneSocket.getOutputStream();
				byte buffer;
				while (enabled) {
					if (inputStream.available() > 0) {
						buffer = (byte) inputStream.read();
						System.out.println(Byte.valueOf(buffer));
						outputStream.write("e".getBytes());
					}
					
					Thread.sleep(200);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					inputStream.close();
					inputStream = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					outputStream.close();
					outputStream = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					droneSocket.close();
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
	
	private Message calculateCommand(ControllerData data) {
		Message ret;
		//thats some error handling, data shouldnt be null, but when is, lets just hover
		if(data == null) {
			ret = new Message(Message.MessageType.HOVER, 0);
		}
		
		return ret;
	}
	
	public class CommunicatorDataReceivedCallback{
		
		public void controllerDataReceived(ControllerData data) {
			if(enabled) {
				boolean success = queue.offer(data);
				//if the queue is full, probably we should just throw the data away
				if(!success) {
					queue.clear();
				}
			}
		}
		
	}

}
