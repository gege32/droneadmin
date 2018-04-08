package hu.gehorvath.szakdolgozat.droneadmin;

import com.studiohartman.jamepad.ControllerState;

import lombok.Value;

public class ControllerData {
	
	private float roll;
	
	private float pitch;
	
	private float yaw;
	
	private float height;

	public static ControllerData fromControllerState(ControllerState state) {
		return new ControllerData(state.rightStickX, state.rightStickY, state.leftStickY, state.leftStickX);
	}

	public ControllerData(float roll, float pitch, float yaw, float height) {
		this.roll = roll;
		this.pitch = pitch;
		this.yaw = yaw;
		this.height = height;
	}
	
	
}
