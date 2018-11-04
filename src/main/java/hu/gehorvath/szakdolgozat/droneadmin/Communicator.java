package hu.gehorvath.szakdolgozat.droneadmin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.LinkedBlockingQueue;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Communicator implements Runnable {

	private SerialPort droneSocket;

	private LinkedBlockingQueue<ControllerData> queue = new LinkedBlockingQueue<ControllerData>(1);

	private boolean enabled = true;

	public void run() {

		while (enabled) {
			try {
				droneSocket = new SerialPort("COM11");
				droneSocket.openPort();
				droneSocket.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);

				System.out.println("Connected");
				while (enabled && droneSocket.isOpened()) {
					if (!queue.isEmpty()) {
						calculateCommand(queue.take()).write(droneSocket);
					}
					
					Thread.sleep(350);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					droneSocket.closePort();
				} catch (SerialPortException e) {
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
		// thats some error handling, data shouldnt be null, but when is, lets just
		// hover
		Q31 roll = Q31.fromFloat(data.getRoll());
		Q31 pitch = Q31.fromFloat(data.getPitch());
		Q31 yaw = Q31.fromFloat(data.getYaw());

		ret = new Message(data.getThrottleConverted(), roll.getQ31(), pitch.getQ31(), yaw.getQ31());

		return ret;
	}

	public class CommunicatorDataReceivedCallback {

		public void controllerDataReceived(ControllerData data) {
			if (enabled) {
				boolean success = queue.offer(data);
				// if the queue is full, probably we should just throw the data away
				if (!success) {
					queue.clear();
				}
			}
		}

	}
	
	public CommunicatorDataReceivedCallback getDataCallback() {
		return new CommunicatorDataReceivedCallback();
	}

}
