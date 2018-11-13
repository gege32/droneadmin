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
		if(start) {
			return 0.125f + (this.throttle / 3f);
		}else{
			return 0.0f;
		}
	}

	public String toString() {
		return "State:" + roll + "," + pitch + "," + yaw + "," + throttle;
	}
}
