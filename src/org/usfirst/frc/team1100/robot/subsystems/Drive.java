package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.drive.UserDriveJoysticks;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	
	private static Drive drive;
	private static RobotDrive driveTrain;
	
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
		setDefaultCommand(new UserDriveJoysticks()); //TODO: Change to UserDriveJoysticks as soon as we switch to, well... joysticks
	}

}
//Here we can write actual words.
//Hello Sven
