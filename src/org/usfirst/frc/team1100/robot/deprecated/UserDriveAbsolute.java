package org.usfirst.frc.team1100.robot.deprecated;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * The command which allows the user control of the drive train
 * @author supremesteak1
 */
public class UserDriveAbsolute extends Command {
	
	/**
	 * The constructor for the UserDrive command
	 */
	public UserDriveAbsolute() {
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
		// leftJVX means "LEFT Joystick Value X"
		// Get the values for all the joystick axis that we will use for mecanum drive
		/*double leftJVX = OI.getInstance().getLeftStick().getAxis(Joystick.AxisType.kX);
		double leftJVY = OI.getInstance().getLeftStick().getAxis(Joystick.AxisType.kY);
		double rightJVX = OI.getInstance().getRightStick().getAxis(Joystick.AxisType.kX);*/
		
		double x = OI.getInstance().getStick().getAxis(Joystick.AxisType.kX);
		double y = OI.getInstance().getStick().getAxis(Joystick.AxisType.kY);
		double z = OI.getInstance().getStick().getAxis(Joystick.AxisType.kZ);
		
		
		Drive.getInstance().driveAbsoluteMecanum(x, -y, -z/2); // In the future we should add in the proper gyro support
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
