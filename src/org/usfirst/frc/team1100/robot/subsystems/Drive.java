package org.usfirst.frc.team1100.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.drivecommands.EulerApprox;
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
	private static EulerApprox positionTracker;
	private static long lastTimeEuler;
	private static ArrayList<double[]> prevValues;
	
	//These constants were calculated from a few seconds of readout from a stationary robot
	private final static double ACCEL_X_FIX = 0.02734375;
	private final static double ACCEL_Y_FIX = 0.01171875;
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
		driveTrain.setSafetyEnabled(false);
		accel = new BuiltInAccelerometer();
		lastTime = System.currentTimeMillis();
		lastTimeDist = System.currentTimeMillis();
		double[] start = new double[2];
		start[0] = 0;
		start[1] = 0;
		positionTracker = new EulerApprox(start);
		lastTimeEuler = System.currentTimeMillis();
		prevValues = new ArrayList<double[]>();
		prevValues.add(new double[]{0.0,0.0});
		prevValues.add(new double[]{0.0,0.0});
		prevValues.add(new double[]{0.0,0.0});
		prevValues.add(new double[]{0.0,0.0});
	}
	
	/**
	 * This drives the robot with mecanum
	 * @param x x distance
	 * @param y y distance
	 * @param rotation is it radians or degrees? We just do not know.
	 */
	public void driveMecanum(double x, double y, double rotation){
		driveTrain.mecanumDrive_Cartesian(x, y, rotation,0);
		double[] ac = new double[2];
		ac[0] = this.getAccelX();
		ac[1] = this.getAccelY();
		prevValues.add(ac);
		prevValues.remove(0);
		//System.err.println("Input time: " + (System.currentTimeMillis()-lastTimeEuler));
		positionTracker.step((System.currentTimeMillis()-lastTimeEuler)/1000.0, averageQueue());
		lastTimeEuler = System.currentTimeMillis();
		//System.err.println(Gyro.getInstance().getGyroDriftPerMillisecond());
		//System.err.println(System.currentTimeMillis() + "	" + Gyro.getInstance().getAngleAverage()%360);
	}
	
	@SuppressWarnings({ "rawtypes" })
	private double[] averageQueue() {
		double[] average = new double[2];
		double xSum = 0;
		double ySum = 0;
		for(int i = 0; i < prevValues.size(); i++) {
			xSum += prevValues.get(i)[0];
			ySum += prevValues.get(i)[1];
		}
		average[0] = xSum/prevValues.size();
		average[1] = ySum/prevValues.size();
		//System.err.println(arrayListTostring(prevValues));
		return average;
	}
	private String arrayListTostring(ArrayList<double[]> list) {
		String total = "{";
		for(double[] o : list) {
			for(double d : o) {
				total += d + ",";
			}
		}
		return total.substring(0,total.length()-1) + "}";
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
		if(Math.abs(accel.getX() + ACCEL_X_FIX)*9.8 < 0.2) {
			return 0;
		}
		return (accel.getX() + ACCEL_X_FIX)*9.8;
	}
	
	public double getAccelY() {
		if(Math.abs(accel.getY() + ACCEL_Y_FIX)*9.8 < 0.29) {
			return 0;
		}
		return (accel.getY() + ACCEL_Y_FIX)*9.8;
	}
	
	public double getAccelZ() {
		return (accel.getZ() + ACCEL_Z_FIX)*9.8;
	}
	
	public double getYVelocity() {
		return positionTracker.getCurrentVelocity()[1];
		/*
		if(System.currentTimeMillis()-lastTime != 0) {
			double vel1000 = (1000*getAccelY())/((System.currentTimeMillis()-lastTime)*1000);
			//System.err.println("Accel: " + getAccelY() + ", Time(s) " + (System.currentTimeMillis()-lastTime));
			lastTime = System.currentTimeMillis();
			currentVelocity += vel1000/1000;
			return vel1000 / 1000;
		} else {
			return 0.0;
		}
		*/
	}
	
	public double getDistance() {
		return positionTracker.getDisplacement()[1];
		/*
		double deltaD = getYVelocity()*(System.currentTimeMillis()-lastTimeDist)*1000;
		totalDistance += deltaD;
		return deltaD;
		*/
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UserDrive());
	}

}
//Here we can write actual words.
//Hello Sven
