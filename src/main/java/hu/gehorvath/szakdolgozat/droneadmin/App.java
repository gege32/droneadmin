package hu.gehorvath.szakdolgozat.droneadmin;

import java.awt.EventQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private ExecutorService newFixedThreadPool;
	
	private BlockingQueue<ControllerData> controllerDataQueue;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		App app = new App();
		app.start();
	}
	
	private void start() {
		newFixedThreadPool = Executors.newFixedThreadPool(10);
		newFixedThreadPool.submit(new ControllerReader(controllerDataQueue));
		newFixedThreadPool.submit(new Communicator());
	}
}
