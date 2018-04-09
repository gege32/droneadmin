package hu.gehorvath.szakdolgozat.droneadmin;

import com.studiohartman.jamepad.ControllerState;

import lombok.Value;

public class ControllerData {
	
	private float roll;
	
	private float pitch;
	
	private float yaw;
	
	private float height;

	public static ControllerData fromControllerState(ControllerState state) {
		return new ControllerData(state.rightStickX, state.rightStickY, state.leftStickX, state.leftStickY);
	}

	public ControllerData(float roll, float pitch, float yaw, float height) {
		this.roll = roll;
		this.pitch = pitch;
		this.yaw = yaw;
		this.height = height;
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

	public float getHeight() {
		return height;
	}
	
	public String toString() {
		return "State:" + roll + "," + pitch + "," + yaw + "," + height;
	}
}
