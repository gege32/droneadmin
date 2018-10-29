package hu.gehorvath.szakdolgozat.droneadmin;

import java.io.IOException;
import java.nio.charset.Charset;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Message {

	private static String MESSAGE_START = "<";
	private static String MESSAGE_END = ">";
	
	private static Charset CHARSET = Charset.forName("ASCII");

	/**
	 * START, HOVER, FULLSTOP: No value ROLL, PITCH, YAW: RAD scaled down with PI/2
	 * HEIGHT: cm/s
	 * 
	 * @author Horvath_Gergo
	 *
	 */
	public enum MessageType {
		START, FLIGHT_DATA, HOVER, FULLSTOP;
	}

	private MessageType messageType;

	// value should be a 4 byte number in Q31 number format
	private byte[] throttle;
	private byte[] roll;
	private byte[] pitch;
	private byte[] yaw;

	private byte[] value;

	public Message(MessageType messageType, byte[] value) {

	}

	public Message(byte[] throttle, byte[] roll, byte[] pitch, byte[] yaw) {
		this.throttle = throttle;
		this.roll = roll;
		this.pitch = pitch;
		this.yaw = yaw;
		messageType = MessageType.FLIGHT_DATA;
	}

	public void write(SerialPort port) throws IOException, SerialPortException {

		port.writeBytes(MESSAGE_START.getBytes(CHARSET));
		port.writeByte((byte) (messageType.ordinal()));
		if (messageType == MessageType.FLIGHT_DATA) {
			port.writeByte((throttle[0]));
			port.writeByte((throttle[1]));
			port.writeByte((throttle[2]));
			port.writeByte((throttle[3]));
			
			port.writeByte((roll[0]));
			port.writeByte((roll[1]));
			port.writeByte((roll[2]));
			port.writeByte((roll[3]));
			
			port.writeByte((pitch[0]));
			port.writeByte((pitch[1]));
			port.writeByte((pitch[3]));
			port.writeByte((pitch[3]));
			
			port.writeByte((yaw[0]));
			port.writeByte((yaw[1]));
			port.writeByte((yaw[2]));
			port.writeByte((yaw[3]));
		}
		port.writeBytes(MESSAGE_END.getBytes(CHARSET));
	}

}
