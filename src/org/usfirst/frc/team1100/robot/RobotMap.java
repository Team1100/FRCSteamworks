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
	
	private final static int PWM_0 = 0;
	private final static int PWM_1 = 1;
	private final static int PWM_2 = 2;
	private final static int PWM_3 = 3;
	private final static int PWM_4 = 4;
	private final static int PWM_5 = 5;
	private final static int PWM_6 = 6;
	private final static int PWM_7 = 7;
	private final static int PWM_8 = 8;
	private final static int PWM_9 = 9;
	
	public final static int CAN_0 = 0;
	public final static int CAN_1 = 1;
	public final static int CAN_2 = 2;
	public final static int CAN_3 = 3;
	public final static int CAN_4 = 4;
	public final static int CAN_5 = 5;
	public final static int CAN_6 = 6;
	public final static int CAN_7 = 7;
	
	private final static int ANALOG_0 = 0;
	private final static int ANALOG_1 = 1;
	private final static int ANALOG_2 = 2;
	private final static int ANALOG_3 = 3;
	
	private final static int RELAY_0 = 0;
	private final static int RELAY_1 = 1;
	private final static int RELAY_2 = 2;
	private final static int RELAY_3 = 3;
	
	private final static int DIO_0 = 0;
	private final static int DIO_1 = 1;
	private final static int DIO_2 = 2;
	private final static int DIO_3 = 3;
	private final static int DIO_4 = 4;
	private final static int DIO_5 = 5;
	private final static int DIO_6 = 6;
	private final static int DIO_7 = 7;
	private final static int DIO_8 = 8;
	private final static int DIO_9 = 9;
	
	private static final int USB_0 = 0;
	private static final int USB_1 = 1;
	private static final int USB_2 = 2;
	
	//[D]rive
	public final static int D_BACK_RIGHT = PWM_0;
	public final static int D_FRONT_RIGHT = PWM_1;
	public final static int D_FRONT_LEFT = PWM_2;
	public final static int D_BACK_LEFT = PWM_3;
	public final static int D_GYRO0 = ANALOG_0;
	public final static int D_GYRO1 = ANALOG_1;
	public final static int D_ACCEL_1 = ANALOG_2;
	public final static int D_ENC_A = DIO_0;
	public final static int D_ENC_B = DIO_0;
	//[U]ser Input
	public static final int U_LEFT = USB_0; //Also serves as steering wheel
	public static final int U_RIGHT = USB_1;
	public static final int U_XBOX = USB_2;
	//[I]ntake
	public static final int I_ROLLER = PWM_4;
	//[S]hooter
	public static final int S_ENCODER = ANALOG_3;

	public static final int S_FLYWHEEL = PWM_8;
	public static final int S_ENCODER_A = DIO_2;
	public static final int S_ENCODER_B = DIO_3;

	//[P]neumatics
	public static final int P_0 = 0;
	public static final int P_1 = 1;
	public static final int P_2 = 2;
	public static final int P_3 = 3;
	public static final int P_4 = 4;
	public static final int P_5 = 5;
	public static final int P_6 = 6;
	public static final int P_7 = 7;
	
	public static final int P_FIRER_0_FORWARD = P_0;
	public static final int P_FIRER_0_REVERSE = P_1;
	public static final int P_FIRER_1_FORWARD = P_2;
	public static final int P_FIRER_1_REVERSE = P_3;
	public static final int P_FIRER_2_FORWARD = P_4;
	public static final int P_FIRER_2_REVERSE = P_5;
	public static final int P_FIRER_3_FORWARD = P_6;
	public static final int P_FIRER_3_REVERSE = P_7;
	
	//[T]est
	public static final int T_TALON_PORT = PWM_9;
	
	/*@Deprecated
	 
	//[A]ugar*/
	@Deprecated
	public static final int A_AUGER = PWM_7;
}