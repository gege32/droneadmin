package hu.gehorvath.szakdolgozat.droneadmin;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Communicator implements Runnable{

	public void run() {
		
		try {
			Socket drone = new Socket("192.168.4.1", 22000);
			if(drone.isConnected()) {
				
				System.out.println("Connected");
				
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}

}
