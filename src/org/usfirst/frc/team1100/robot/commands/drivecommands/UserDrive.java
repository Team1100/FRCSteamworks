package org.usfirst.frc.team1100.robot.commands.drivecommands;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Gyro;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class UserDrive extends Command {
	
	private int speedLevel;
	private boolean prevUpVal;
	private boolean prevDownVal;
	
	public UserDrive() {
		requires(Drive.getInstance());
		requires(Gyro.getInstance());
	}
	
	// Initializes the command right before it is run
	protected void initialize() {
		speedLevel = 1;
		prevUpVal = false;
		prevDownVal = false;
	}

	protected void execute() {
		// leftJVX means "LEFT Joystick Value X"
		// Get the values for all the joystick axis that we will use for mecanum drive
		//double leftJVX = OI.getInstance().getLeftStick().getAxis(Joystick.AxisType.kX);
		//double leftJVY = OI.getInstance().getLeftStick().getAxis(Joystick.AxisType.kY);
		double rightJVY = OI.getInstance().getRightStick().getAxis(Joystick.AxisType.kX);
		
		double forwardSpeed = 0.0;
		double strafeSpeed = 0.0;
		
		if(OI.getInstance().getRightStick().getButton(7).get() && !prevDownVal) { // Left paddle, down speed
			// Speed down
			speedLevel--;
			if(speedLevel < 1) {
				speedLevel = 1;
			}
			prevDownVal = true;
		} else if(!OI.getInstance().getRightStick().getButton(7).get()) {
			prevDownVal = false;
		}
		if(OI.getInstance().getRightStick().getButton(8).get() && !prevUpVal) { // Right paddle, up speed
			// Speed up
			speedLevel++;
			if(speedLevel > 4) {
				speedLevel = 4;
			}
			prevUpVal = true;
		} else if(!OI.getInstance().getRightStick().getButton(8).get()) {
			prevUpVal = false;
		}
		if(OI.getInstance().getRightStick().getButton(9).get()) { // The select button, make speed full
			speedLevel = 4;
		}
		if(OI.getInstance().getRightStick().getButton(10).get()) { // The start button, make speed low
			speedLevel = 1;
		}
		if(OI.getInstance().getRightStick().getButton(4).get()) { // Brake, backwards
			// Reverse
			switch(speedLevel) {
				case 1:
					forwardSpeed = -0.2;
					break;
				case 2:
					forwardSpeed = -0.4;
					break;
				case 3:
					forwardSpeed = -0.7;
					break;
				case 4:
					forwardSpeed = -1.0;
					break;
			}
		}
		if(OI.getInstance().getRightStick().getButton(3).get()) { // Accelerator, forward
			// Accelerator
			switch(speedLevel) {
				case 1:
					forwardSpeed = 0.2;
					break;
				case 2:
					forwardSpeed = 0.4;
					break;
				case 3:
					forwardSpeed = 0.7;
					break;
				case 4:
					forwardSpeed = 1.0;
					break;
			}
		}
		if(OI.getInstance().getRightStick().getButton(2).get()) { // Triangle button, left translate
			// Strafe left
			switch(speedLevel) {
				case 1:
					strafeSpeed = -0.3;
					break;
				case 2:
					strafeSpeed = -0.5;
					break;
				case 3:
					strafeSpeed = -0.7;
					break;
				case 4:
					strafeSpeed = -1.0;
					break;
			}
		}
		if(OI.getInstance().getRightStick().getButton(1).get()) { // Circle button, right translate
			// Strafe right
			switch(speedLevel) {
				case 1:
					strafeSpeed = 0.3;
					break;
				case 2:
					strafeSpeed = 0.5;
					break;
				case 3:
					strafeSpeed = 0.7;
					break;
				case 4:
					strafeSpeed = 1.0;
					break;
			}
		}
		System.err.println("Forward speed: " + forwardSpeed + ", Strafe speed: " + strafeSpeed + ", Speed level: " + speedLevel);
		Drive.getInstance().driveMecanum(strafeSpeed, forwardSpeed, rightJVY); // In the future we should add in the proper gyro support
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
