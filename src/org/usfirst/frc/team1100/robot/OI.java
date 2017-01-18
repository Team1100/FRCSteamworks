package org.usfirst.frc.team1100.robot;

import org.usfirst.frc.team1100.robot.input.AttackThree;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
private static OI instance;
	
	private AttackThree leftStick;
	private AttackThree rightStick;
	
	public static OI getInstance() {
		if(instance == null) {
			instance = new OI();
		}
		return instance;
	}
	
	private OI() {
		leftStick = new AttackThree(RobotMap.J_LEFT, .1);
		rightStick = new AttackThree(RobotMap.J_RIGHT, .1);
	}
	
	/**
	 * Returns the Left Joystick.
	 * @return the Left AttackThree
	 */
	public AttackThree getLeftStick() {
		return leftStick;
	}

	/**
	 * Returns the Right Joystick
	 * @return the Right AttackThree
	 */
	public AttackThree getRightStick() {
		return rightStick;
	}
	
}
