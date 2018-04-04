package hu.gehorvath.szakdolgozat.droneadmin;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JSlider;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.util.concurrent.Callable;

import javax.swing.JCheckBox;

public class MainWindow {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 597, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblRoll = new JLabel("ROLL");
		GridBagConstraints gbc_lblRoll = new GridBagConstraints();
		gbc_lblRoll.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoll.gridx = 0;
		gbc_lblRoll.gridy = 0;
		frame.getContentPane().add(lblRoll, gbc_lblRoll);
		
		JSlider slider = new JSlider();
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 0;
		frame.getContentPane().add(slider, gbc_slider);
		
		JCheckBox chckbxConnected = new JCheckBox("CONNECTED");
		GridBagConstraints gbc_chckbxConnected = new GridBagConstraints();
		gbc_chckbxConnected.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxConnected.gridx = 8;
		gbc_chckbxConnected.gridy = 0;
		frame.getContentPane().add(chckbxConnected, gbc_chckbxConnected);
		
		JLabel lblPitch = new JLabel("PITCH");
		GridBagConstraints gbc_lblPitch = new GridBagConstraints();
		gbc_lblPitch.insets = new Insets(0, 0, 5, 5);
		gbc_lblPitch.gridx = 0;
		gbc_lblPitch.gridy = 1;
		frame.getContentPane().add(lblPitch, gbc_lblPitch);
		
		JSlider slider_1 = new JSlider();
		GridBagConstraints gbc_slider_1 = new GridBagConstraints();
		gbc_slider_1.insets = new Insets(0, 0, 5, 5);
		gbc_slider_1.gridx = 1;
		gbc_slider_1.gridy = 1;
		frame.getContentPane().add(slider_1, gbc_slider_1);
		
		JCheckBox chckbxSensorCalibrated = new JCheckBox("SENSOR CALIBRATED");
		GridBagConstraints gbc_chckbxSensorCalibrated = new GridBagConstraints();
		gbc_chckbxSensorCalibrated.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSensorCalibrated.gridx = 8;
		gbc_chckbxSensorCalibrated.gridy = 1;
		frame.getContentPane().add(chckbxSensorCalibrated, gbc_chckbxSensorCalibrated);
		
		JLabel lblYaw = new JLabel("YAW");
		GridBagConstraints gbc_lblYaw = new GridBagConstraints();
		gbc_lblYaw.insets = new Insets(0, 0, 5, 5);
		gbc_lblYaw.gridx = 0;
		gbc_lblYaw.gridy = 2;
		frame.getContentPane().add(lblYaw, gbc_lblYaw);
		
		JSlider slider_2 = new JSlider();
		GridBagConstraints gbc_slider_2 = new GridBagConstraints();
		gbc_slider_2.insets = new Insets(0, 0, 5, 5);
		gbc_slider_2.gridx = 1;
		gbc_slider_2.gridy = 2;
		frame.getContentPane().add(slider_2, gbc_slider_2);
		
		JCheckBox chckbxEscCalibrated = new JCheckBox("ESC CALIBRATED");
		GridBagConstraints gbc_chckbxEscCalibrated = new GridBagConstraints();
		gbc_chckbxEscCalibrated.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxEscCalibrated.gridx = 8;
		gbc_chckbxEscCalibrated.gridy = 2;
		frame.getContentPane().add(chckbxEscCalibrated, gbc_chckbxEscCalibrated);
		
		JLabel lblHeight = new JLabel("HEIGHT");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 0;
		gbc_lblHeight.gridy = 3;
		frame.getContentPane().add(lblHeight, gbc_lblHeight);
		
		JSlider slider_3 = new JSlider();
		GridBagConstraints gbc_slider_3 = new GridBagConstraints();
		gbc_slider_3.insets = new Insets(0, 0, 5, 5);
		gbc_slider_3.gridx = 1;
		gbc_slider_3.gridy = 3;
		frame.getContentPane().add(slider_3, gbc_slider_3);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
	
	public class WindowDataCallback{
		
		public void ControllerDataReceived(ControllerData data) {
			
		}
		
		public void DroneDataReceived(DroneData data) {
			
		}
		
	}

}
