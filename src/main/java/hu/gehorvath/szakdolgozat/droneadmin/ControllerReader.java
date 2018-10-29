package hu.gehorvath.szakdolgozat.droneadmin;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

import hu.gehorvath.szakdolgozat.droneadmin.Communicator.CommunicatorDataReceivedCallback;
import hu.gehorvath.szakdolgozat.droneadmin.MainWindow.WindowDataCallback;

public class ControllerReader implements Runnable {

	CommunicatorDataReceivedCallback communicatorDataReceivedCallback; 
	WindowDataCallback windowDataCallback;

	public ControllerReader(WindowDataCallback windowDataCallback, CommunicatorDataReceivedCallback communicatorDataReceivedCallback) {
		this.windowDataCallback = windowDataCallback;
		this.communicatorDataReceivedCallback = communicatorDataReceivedCallback;
	}

	public void run() {
		ControllerManager controllers = new ControllerManager();
		controllers.initSDLGamepad();

		// Print a message when the "A" button is pressed. Exit if the "B" button is
		// pressed
		// or the controller disconnects.
		while (true) {
			ControllerState currState = controllers.getState(0);

			communicatorDataReceivedCallback.controllerDataReceived(ControllerData.fromControllerState(currState));
			ControllerData fromControllerState = ControllerData.fromControllerState(currState);
			windowDataCallback.controllerDataReceived(fromControllerState);

			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
