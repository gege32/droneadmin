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
	
	//value should be a 4 byte number in Q31 number format
	private byte[] value;

	public Message(byte messageType, byte[] value) {
		super();
		this.messageType = messageType;
		this.value = value;
	}
	
	public void write(BufferedOutputStream os) throws IOException{
		os.write(MESSAGE_START.getBytes(CHARSET));
		os.write(messageType);
		os.write((value[3]));
		os.write((value[2]));
		os.write((value[1]));
		os.write((value[0]));
		os.write(MESSAGE_END.getBytes(CHARSET));
		os.flush();
	}

}
