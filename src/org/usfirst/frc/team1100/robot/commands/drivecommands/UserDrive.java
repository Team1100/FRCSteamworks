package org.usfirst.frc.team1100.robot.commands.drivecommands;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class UserDrive extends Command {
	
	public UserDrive() {
		requires(Drive.getInstance());
	}
	
	// Initializes the command right before it is run
	protected void initialize() {
		
	}

	protected void execute() {
		// leftJVX means "LEFT Joystick Value X"
		// Get the values for all the joystick axis that we will use for mecanum drive
		double leftJVX = OI.getInstance().getLeftStick().getAxis(Joystick.AxisType.kX);
		double leftJVY = OI.getInstance().getLeftStick().getAxis(Joystick.AxisType.kY);
		double rightJVY = OI.getInstance().getLeftStick().getAxis(Joystick.AxisType.kY);
		
		Drive.getInstance().driveMecanum(leftJVX, leftJVY, rightJVY, 0); // In the future we should add in the proper gyro support
	}
	
	// This is always going to be false because we will always be running it unless we are specifically running something else
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	// Called when another command force stops this one to use the subsystem for something else
	protected void interrupted() {
		
	}
	
}
