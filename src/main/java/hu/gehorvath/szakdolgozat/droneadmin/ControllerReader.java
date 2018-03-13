package hu.gehorvath.szakdolgozat.droneadmin;

import java.util.concurrent.BlockingQueue;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

public class ControllerReader implements Runnable {

	private BlockingQueue<ControllerData> controllerDataQueue;

	public ControllerReader(BlockingQueue<ControllerData> controllerDataQueue) {
		this.controllerDataQueue = controllerDataQueue;
	}

	public void run() {
		ControllerManager controllers = new ControllerManager();
		controllers.initSDLGamepad();

		// Print a message when the "A" button is pressed. Exit if the "B" button is
		// pressed
		// or the controller disconnects.
		while (true) {
			ControllerState currState = controllers.getState(0);
			
			try {
				controllerDataQueue.put(ControllerData.fromControllerState(currState));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
