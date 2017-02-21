package org.usfirst.frc.team1100.robot.commands.drive;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A command to rotate the robot an number of degrees with a fake PID like loop
 * @author supremesteak1
 */
public class RotateCommand extends Command {

	private double endAngle;
	private final double DEADZONE = 30;
	private final double RAMP_FACTOR = 70;
	private final double SPEED_LIMIT = 0.5;
	private boolean finished;
	
	/**
	 * The constructor for the RotateCommand
	 * @param angle the angle in degrees to rotate. Positive is clockwise
	 */
    public RotateCommand(double angle) {
    	requires(Drive.getInstance());
    	requires(Drive.getInstance());
    	endAngle = Drive.getInstance().getAngleAverage() + angle;
    	setTimeout(3); // Ensure the gyro doesn't get stuck
    }

    /**
     * Called right before the command runs
     */
    protected void initialize() {
    	//Drive.getInstance().resetGyro();
    	finished = false;
    }

    /**
     * Called many times a second while the command is running
     */
    protected void execute() {
    	System.err.println("Rotate!!!");
    	
    	double currentAngleDifference = Drive.getInstance().getAngleAverage() + endAngle;
    	double power = 1 - (Math.pow(Math.E, -currentAngleDifference/RAMP_FACTOR));
    	if(1 - (Math.pow(Math.E, -currentAngleDifference/RAMP_FACTOR)) > 0) {
    		power = Math.min(1 - (Math.pow(Math.E, -currentAngleDifference/RAMP_FACTOR)),SPEED_LIMIT);
    	} else {
    		power = Math.max(1 - (Math.pow(Math.E, -currentAngleDifference/RAMP_FACTOR)),-SPEED_LIMIT);
    	}
    	if(Math.abs(currentAngleDifference) > DEADZONE) {
    		Drive.getInstance().driveMecanum(0, 0, -power);
    	} else {
    		//new AutoDrive(0, 0, -power, 0.1).start();
    		System.err.println("All set");
    		finished = true;
    	}
    	//System.err.println("Current angle: " + Drive.getInstance().getAngleAverage() + ", Difference: " + currentAngleDifference + ", Power: " + power);
    }

    /**
     * Returns if the command has either timed out or has finished running
     */
    protected boolean isFinished() {
        return finished | isTimedOut();
    }

    /**
     * Called when the command is finished
     */
    protected void end() {
    	
    }

    /**
     * Called if the command is interrupted
     */
    protected void interrupted() {
    	end();
    }
}
