package hu.gehorvath.szakdolgozat.droneadmin;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class Message {
	
	private static String MESSAGE_START = "<";
	private static String MESSAGE_END = ">";
	
	private static Charset CHARSET = Charset.forName("ASCII");
	
	/**
	 * START, HOVER, FULLSTOP: No value
	 * ROLL, PITCH, YAW: RAD scaled down with PI/2
	 * HEIGHT: cm/s
	 * @author Horvath_Gergo
	 *
	 */
	public enum MessageType{
		START, HOVER, ROLL, PITCH, YAW, HEIGHT, FULLSTOP;
	}
	
	private byte messageType;
	
	private int value;

	public Message(byte messageType, int value) {
		super();
		this.messageType = messageType;
		this.value = value;
	}
	
	public void write(BufferedOutputStream os) throws IOException{
		os.write(MESSAGE_START.getBytes(CHARSET));
		os.write(messageType);
		os.write((byte)(value >> 24));
		os.write((byte)(value >> 16));
		os.write((byte)(value >> 8));
		os.write((byte)(value));
		os.write(MESSAGE_END.getBytes(CHARSET));
		os.flush();
	}

}
