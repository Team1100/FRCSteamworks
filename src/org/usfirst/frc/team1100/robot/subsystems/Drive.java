package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.drive.user.UserDriveJoysticks;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem {
	
	private static Drive drive;
	private static RobotDrive driveTrain;
	
	private final double DRIFT = 0.00005;//-0.0001;
	
	private AnalogGyro gyro0;
	private AnalogGyro gyro1;
	private long resetTime; // The time the gyro was last reset
	
	private Victor frontLeft, frontRight, backLeft, backRight;
	
	public static Drive getInstance() {
		if(drive==null) {
			drive = new Drive();
		}
		return drive;
	}

	public Drive(){
		frontLeft = new Victor(RobotMap.D_FRONT_LEFT);
		frontRight = new Victor(RobotMap.D_FRONT_RIGHT);
		backLeft = new Victor(RobotMap.D_BACK_LEFT);
		backRight = new Victor(RobotMap.D_BACK_RIGHT);
		
		backRight.setInverted(true);
		frontRight.setInverted(true);
		
		driveTrain = new RobotDrive(backLeft, frontLeft, backRight, frontRight);
		driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		
		
		driveTrain.setSafetyEnabled(false);
		
		gyro0 = new AnalogGyro(RobotMap.D_GYRO0);
		gyro1 = new AnalogGyro(RobotMap.D_GYRO1);
		//System.err.println("Test");
		resetGyro();
	}
	
	public void displayGyroValues(){
		SmartDashboard.putNumber("Gyro0", getAngle0());
		SmartDashboard.putNumber("Gyro1", getAngle1());
		SmartDashboard.putNumber("GyroAverage", getAngleAverage());
	}
	
	public LiveWindowSendable[] driveLWS(){
		LiveWindowSendable[] lws = new LiveWindowSendable[4];
		lws[0] = (LiveWindowSendable) frontLeft;
		lws[1] = (LiveWindowSendable) backLeft;
		lws[2] = (LiveWindowSendable) frontRight;
		lws[3] = (LiveWindowSendable) backRight;
		return lws;
	}
	
	/**
	 * This drives the robot with mecanum
	 * @param x x power
	 * @param y y power
	 * @param rotation is it radians or degrees? We just do not know.
	 * ^whatever it is touchy so i cut the joysitck in half in UserDrive
	 */
	public void driveMecanum(double x, double y, double rotation){
		driveTrain.mecanumDrive_Cartesian(x, y, rotation,0);
		displayGyroValues();
	}
	
	/**
	 * Drive the robot in the more fancy fashion with absolute direction
	 * @param x power
	 * @param y power
	 * @param rotation power
	 */
	public void driveAbsoluteMecanum(double x, double y, double rotation){
		driveTrain.mecanumDrive_Cartesian(x, y, rotation, getAngleAverage());
		displayGyroValues();
	}
	
	/**
	 * This drives the robot with tank (in case you want that)
	 * @param left the left joystick value for the left side of the drive train
	 * @param right the right joystick value for the right side of the drive train
	 */
	public void driveTank(double left, double right) {
		driveTrain.tankDrive(left, right);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UserDriveJoysticks()); //TODO: Change to UserDriveJoysticks as soon as we switch to, well... joysticks
	}

	//GYRO COMMANDS
    
	/**
	 * Gets the current angle of gyro0
	 * @return the current angle of gyro0
	 */
	public double getAngle0() {
		return (gyro0.getAngle());
	}
	
	/**
	 * Gets the current angle of gyro1
	 * @return the current angle of gyro1
	 */
	public double getAngle1() {
		return (-gyro1.getAngle());
	}
	
	/**
	 * Gets the current average angle of the gyros
	 * @return the current average angle of the gyros
	 */
	public double getAngleAverage() {
		return ((gyro0.getAngle() - gyro1.getAngle()) / 2.0) + (DRIFT*(System.currentTimeMillis()-resetTime));
	}
	
	public double getGyroDriftPerMillisecond() {
		return ((gyro0.getAngle()/* + gyro1.getAngle())/2.0*/))/(System.currentTimeMillis()-resetTime);
	}
	
	/**
	 * Resets the gyro to 0
	 * Mainly useful at the beginning of the match when we are in a known position
	 */
	public void resetGyro() {
		//System.err.println("Gyro reset");
		gyro0.reset();
		gyro1.reset();
		resetTime = System.currentTimeMillis();
	}
}
//Here we can write actual words.
//Hello Sven
