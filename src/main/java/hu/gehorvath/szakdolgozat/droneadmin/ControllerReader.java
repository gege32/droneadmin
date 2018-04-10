package hu.gehorvath.szakdolgozat.droneadmin;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

import hu.gehorvath.szakdolgozat.droneadmin.MainWindow.WindowDataCallback;

public class ControllerReader implements Runnable {

	private BlockingQueue<ControllerData> controllerDataQueue = new LinkedBlockingQueue<ControllerData>();
	WindowDataCallback windowDataCallback;

	public ControllerReader(WindowDataCallback windowDataCallback) {
		this.windowDataCallback = windowDataCallback;
	}

	public void run() {
		ControllerManager controllers = new ControllerManager();
		controllers.initSDLGamepad();

		// Print a message when the "A" button is pressed. Exit if the "B" button is
		// pressed
		// or the controller disconnects.
		while (true) {
			ControllerState currState = controllers.getState(0);

			// try {
			// controllerDataQueue.put(ControllerData.fromControllerState(currState));
			ControllerData fromControllerState = ControllerData.fromControllerState(currState);
			windowDataCallback.ControllerDataReceived(fromControllerState);
//			System.out.println(fromControllerState.toString());
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
