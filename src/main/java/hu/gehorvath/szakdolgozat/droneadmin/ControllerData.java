package hu.gehorvath.szakdolgozat.droneadmin;

import com.studiohartman.jamepad.ControllerState;

import lombok.Value;

public class ControllerData {

	private float roll;

	private float pitch;

	private float yaw;

	private float throttle;

	private boolean start;

	public static ControllerData fromControllerState(ControllerState state) {
		return new ControllerData(state.rightStickX, state.rightStickY, state.leftStickX, state.leftStickY, state.a);
	}

	public ControllerData(float roll, float pitch, float yaw, float height, boolean start) {
		this.roll = roll;
		this.pitch = pitch;
		this.yaw = yaw;
		this.throttle = height;
		this.start = start;
	}

	public float getRoll() {
		return roll;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getThrottle() {
		return throttle;
	}

	/**
	 * Scaled up to 1000-2000 boundries, MSB first.
	 * 
	 * @return
	 */
	public byte[] getThrottleConverted() {
		if (start) {
			byte[] throttle = new byte[4];

			int temp = (int) (this.throttle * 1000.0f);
			// int temp = (int) (0.5f * 1000.0f);
			if (temp < 0) {
				temp = 0;
			}
			temp = (temp / 2) + 1000;

			throttle[0] = (byte) ((temp >> 24) & 0xff);
			throttle[1] = (byte) ((temp >> 16) & 0xff);
			throttle[2] = (byte) ((temp >> 8) & 0xff);
			throttle[3] = (byte) ((temp) & 0xff);

			return throttle;
		}else {
			return new byte[] {0x00, 0x00, 0x00, 0x00};
		}

	}

	public String toString() {
		return "State:" + roll + "," + pitch + "," + yaw + "," + throttle;
	}
}
