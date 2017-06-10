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
	
	private final static int CAN_0 = 0;
	private final static int CAN_1 = 1;
	private final static int CAN_2 = 2;
	private final static int CAN_3 = 3;
	private final static int CAN_4 = 4;
	private final static int CAN_5 = 5;
	private final static int CAN_6 = 6;
	private final static int CAN_7 = 7;
	
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
	private static final int USB_3 = 3;
	
	private static final int P_0 = 0;
	private static final int P_1 = 1;
	private static final int P_2 = 2;
	private static final int P_3 = 3;
	private static final int P_4 = 4;
	private static final int P_5 = 5;
	private static final int P_6 = 6;
	private static final int P_7 = 7;
	
	private static final int PDP_0 = 0;
	private static final int PDP_1 = 1;
	private static final int PDP_2 = 2;
	private static final int PDP_3 = 3;
	private static final int PDP_4 = 4;
	private static final int PDP_5 = 5;
	private static final int PDP_6 = 6;
	private static final int PDP_7 = 7;
	private static final int PDP_8 = 8;
	private static final int PDP_9 = 9;
	private static final int PDP_10 = 10;
	private static final int PDP_11 = 11;
	private static final int PDP_12 = 12;
	private static final int PDP_13 = 13;
	private static final int PDP_14 = 14;
	private static final int PDP_15 = 15;
	private static final int PDP_16 = 16;
	
	//[D]rive
	public final static int D_BACK_RIGHT = PWM_6;//George
	public final static int D_FRONT_RIGHT = PWM_7;//Ringo
	public final static int D_FRONT_LEFT = PWM_9;//Paul
	public final static int D_BACK_LEFT = PWM_8;//John
	public final static int D_GYRO0 = ANALOG_0;
	public final static int D_GYRO1 = ANALOG_1;
	//[U]ser Input
	public static final int U_XBOX = USB_1;
	public static final int U_STICK = USB_0;
	//[I]ntake
	//public static final int I_ROLLER = PWM_4;
	//public static final int I_ROLLER_2 = PWM_5;
	//[S]hooter
	public static final int S_FLYWHEEL = PWM_3; // Was CAN_2 before
	public static final int S_FEEDER = CAN_5; // Was CAN_3 before
	public static final int S_CONVEYOR = CAN_3; // Was PWM_0 before
	public static final int S_ENCODER_A = DIO_2;
	public static final int S_ENCODER_B = DIO_3;
	public static final int S_FLAP = PWM_3;
	//[G]ear 2
	public static final int G2_PCM = CAN_0;
	public static final int G2_PLATE_0 = DIO_0;
	public static final int G2_PLATE_1 = DIO_1;
	public static final int G2_CLAMP_0_FORWARD = P_2;
	public static final int G2_CLAMP_0_REVERSE = P_3;
	public static final int G2_RAMP_1_FORWARD = P_1;
	public static final int G2_RAMP_1_REVERSE = P_0;
	//[C]limber
	public static final int C_MOTOR_1 = PWM_4;
	public static final int C_MOTOR_2 = PWM_5;
	//[G]ear
	public static final int G_PCM = CAN_1;
	public static final int G_CATCHER = P_0;
	//[L]ights
	public static final int L_PCM = G_PCM;
	//[V]ision
	public static final int V_ULTRASOUND = ANALOG_3;
	//[P]ower Distribution Panel
	public static final int P_PANEL = CAN_6; 
	public static final int P_SHOOTER_A = PDP_2;
	public static final int P_SHOOTER_B = PDP_3;
	public static final int P_CLIMBER_A = PDP_12;
	public static final int P_CLIMBER_B = PDP_13;
	
}