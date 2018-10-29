package hu.gehorvath.szakdolgozat.droneadmin;

public class Q31 {
	
	private static float SHIFT_NUMBER = 2147483648.0f;
	
	//this class represents the 32 bit q31 number format
	private byte[] data = new byte[4];
	
	public Q31(float number) {
		int temp;
		if(number > 1) {
			temp = 1;
		}else if(number < -1) {
			temp = -1;
		}
		temp = (int)((number * SHIFT_NUMBER));
		
		data[0] = (byte)((temp >> 24) & 0xff);
		data[1] = (byte)((temp >> 16) & 0xff);
		data[2] = (byte)((temp >> 8) & 0xff);
		data[3] = (byte)((temp) & 0xff);
	}

	public byte[] getQ31() {
		return data;
	}
	
	public static Q31 fromFloat(float number) {
		return new Q31(number);
	}
	
}
