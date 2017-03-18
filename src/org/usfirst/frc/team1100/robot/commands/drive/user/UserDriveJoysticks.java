package org.usfirst.frc.team1100.robot.commands.drive.user;

import java.util.ArrayList;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.vision.Vision;

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
		requires(org.usfirst.frc.team1100.robot.subsystems.vision.Vision.getInstance());
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
		
		double x = OI.getInstance().getStick().getAxis(Joystick.AxisType.kX);
		double y = OI.getInstance().getStick().getAxis(Joystick.AxisType.kY);
		double z = OI.getInstance().getStick().getAxis(Joystick.AxisType.kZ);
		
		y *= Drive.getInstance().isReversed()? 1:-1;
		
		System.err.println(y);
		Drive.getInstance().driveMecanum(x, y, z*ROTATION_CORRECTION);
		
		SmartDashboard.putNumber("CenterX", getImageOffset());
		SmartDashboard.putNumber("USound",Vision.getInstance().getUSound());
	}
	
	private double getImageOffset() {
		ArrayList<double[]> contours = Vision.getInstance().requestContours();
		int perceivedCenterX = 0;
		try{
		for(int i = 0; i<contours.size(); i++) {
			perceivedCenterX += contours.get(i)[1];
		}
		perceivedCenterX /= contours.size();
		System.err.println("Perceived Center X: " + perceivedCenterX);
		return (perceivedCenterX);
		} catch(ArithmeticException e) {
			System.err.println("No contours found!");
		} catch (NullPointerException e){
			System.err.println("Contours be null or something");
		} catch (IndexOutOfBoundsException e){
			System.err.println("EEEEeeeEEEeeAweeeawimabaayy");
		}
		return 0;
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
