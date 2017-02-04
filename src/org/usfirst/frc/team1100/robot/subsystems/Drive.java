package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.drivecommands.UserDrive;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	
	private static Drive drive;
	private static RobotDrive driveTrain;
	private static BuiltInAccelerometer accel;
	private static long lastTime;
	private static long lastTimeDist;
	private static double totalDistance;
	private static double currentVelocity;
	
	private final static double ACCEL_X_FIX = 0.0233020833333333333;
	private final static double ACCEL_Y_FIX = 0.00018601190476190475;
	private final static double ACCEL_Z_FIX = 0.0209300595238095238;
	
	public static Drive getInstance() {
		if(drive==null) {
			drive = new Drive();
		}
		return drive;
	}
	public Drive(){
		driveTrain = new RobotDrive(RobotMap.D_FRONT_LEFT, RobotMap.D_BACK_LEFT, RobotMap.D_FRONT_RIGHT, RobotMap.D_BACK_RIGHT);
		driveTrain.setInvertedMotor(edu.wpi.first.wpilibj.RobotDrive.MotorType.kFrontLeft, true); // The left side has to be inverted for mecanum to work
		driveTrain.setInvertedMotor(edu.wpi.first.wpilibj.RobotDrive.MotorType.kRearLeft, true);
		accel = new BuiltInAccelerometer();
		lastTime = System.currentTimeMillis();
		lastTimeDist = System.currentTimeMillis();
	}
	
	/**
	 * This drives the robot with mecanum
	 * @param x x distance
	 * @param y y distance
	 * @param rotation is it radians or degrees? We just do not know.
	 */
	public void driveMecanum(double x, double y, double rotation){
		driveTrain.mecanumDrive_Cartesian(x, y, rotation,0);
		//System.err.println(Gyro.getInstance().getGyroDriftPerMillisecond());
		//System.err.println(System.currentTimeMillis() + "	" + Gyro.getInstance().getAngleAverage()%360);
	}
	
	/**
	 * This drives the robot with tank (in case you want that)
	 * @param left the left joystick value for the left side of the drive train
	 * @param right the right joystick value for the right side of the drive train
	 */
	public void driveTank(double left, double right) {
		driveTrain.tankDrive(left, right);
	}
	
	public double getAccelX() {
		return accel.getX() + ACCEL_X_FIX;
	}
	
	public double getAccelY() {
		if(Math.abs(accel.getY() + ACCEL_Y_FIX)*9.8 < 0.1) {
			return 0;
		}
		return (accel.getY() + ACCEL_Y_FIX)*9.8;
	}
	
	public double getAccelZ() {
		return accel.getZ() + ACCEL_Z_FIX;
	}
	
	public double getYVelocity() {
		if(System.currentTimeMillis()-lastTime != 0) {
			double vel1000 = (1000*getAccelY())/((System.currentTimeMillis()-lastTime)*1000);
			//System.err.println("Accel: " + getAccelY() + ", Time(s) " + (System.currentTimeMillis()-lastTime));
			lastTime = System.currentTimeMillis();
			currentVelocity += vel1000/1000;
			return vel1000 / 1000;
		} else {
			return 0.0;
		}
	}
	
	public double getDistance() {
		double deltaD = getYVelocity()*(System.currentTimeMillis()-lastTimeDist)*1000;
		totalDistance += deltaD;
		return deltaD;
	}
	
	public double getCurrentVelocity() {
		return currentVelocity;
	}
	
	public double getTotalDistance() {
		return totalDistance;
	}
	
	public void resetDistance() {
		totalDistance = 0;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UserDrive());
		//setDefaultCommand(new CenterContoursCommand());
	}

}
//Here we can write actual words.
//Hello Sven
