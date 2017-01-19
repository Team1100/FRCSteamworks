package org.usfirst.frc.team1100.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public final static int PWM_0 = 0;
	public final static int PWM_1 = 1;
	public final static int PWM_2 = 2;
	public final static int PWM_3 = 3;
	
	private static final int USB_0 = 0;
	private static final int USB_1 = 1;
	private static final int USB_2 = 2;
	
	//[D]rive
	public final static int D_BACK_RIGHT = PWM_0;
	public final static int D_FRONT_RIGHT = PWM_1;
	public final static int D_FRONT_LEFT = PWM_2;
	public final static int D_BACK_LEFT = PWM_3;
	//[I]nput
	public static final int I_LEFT = USB_0;
	public static final int I_RIGHT = USB_1;
	public static final int I_XBOX = USB_2;
}
