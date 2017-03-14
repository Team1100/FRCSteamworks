package org.usfirst.frc.team1100.robot.commands.drive.user;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The command which allows the user control of the drive train
 * @author supremesteak1
 */
public class UserDriveJoysticks extends Command {
	
	private final double ROTATION_CORRECTION = -.75;
	
	/**
	 * The constructor for the UserDrive command
	 */
	public UserDriveJoysticks() {
		requires(Drive.getInstance());
	}
	
	/**
	 * Called right before the command is run for the first time
	 */
	protected void initialize() {
		
	}

	/**
	 * Called many times a second while the command is running
	 */
	protected void execute() {
		System.err.println("Driving!");
		double x = OI.getInstance().getStick().getAxis(Joystick.AxisType.kX);
		double y = OI.getInstance().getStick().getAxis(Joystick.AxisType.kY);
		double z = OI.getInstance().getStick().getAxis(Joystick.AxisType.kZ);
		
		y *= Drive.getInstance().isReversed()? 1:-1;
		
		
		Drive.getInstance().driveMecanum(x, y, z*ROTATION_CORRECTION);
	}
	
	/**
	 * Returns if the command is finished or not
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	/**
	 * Called if the command is interrupted
	 */
	protected void interrupted() {
		
	}
	
}
