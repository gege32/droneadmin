package hu.gehorvath.szakdolgozat.droneadmin;

import java.awt.EventQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private ExecutorService newFixedThreadPool;
	private static MainWindow window = new MainWindow();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		Communicator communicator = new Communicator();
		newFixedThreadPool.submit(communicator);
		newFixedThreadPool.submit(new ControllerReader(window.getCallback(), communicator.getDataCallback()));
	}
}
