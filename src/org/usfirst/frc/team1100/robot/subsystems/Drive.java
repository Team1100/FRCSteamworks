package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.drivecommands.UserDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	
	private static Drive drive;
	private RobotDrive driveTrain;
	
	private AnalogGyro gyro;
	
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
		
		gyro = new AnalogGyro(RobotMap.D_GYRO);
		resetGyro();
	}
	
	/**
	 * This drives the robot with mecanum
	 * @param x x distance
	 * @param y y distance
	 * @param rotation is it radians or degrees? We just do not know.
	 */
	public void driveMecanum(double x, double y, double rotation){
		driveTrain.mecanumDrive_Cartesian(x, y, rotation,0);
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
		setDefaultCommand(new UserDrive());
	}
	
	/**
	 * Gets the current angle of the gyro
	 * @return the current angle of the gyro
	 */
	public double getAngle() {
		return gyro.getAngle();
	}
	
	/**
	 * Resets the gyro to 0
	 * Mainly useful at the beginning of the match when we are in a known position
	 */
	public void resetGyro() {
		gyro.reset();
	}

}
//Here we can write actual words.
//Hello Sven
