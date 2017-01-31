package org.usfirst.frc.team1100.robot.commands.drivecommands;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Gyro;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateCommand extends Command {

	private double endAngle;
	private final double DEADZONE = 3;
	private final double RAMP_FACTOR = 90;
	private final double SPEED_LIMIT = 0.5;
	private boolean finished;
	
    public RotateCommand(double angle) {
    	requires(Drive.getInstance());
    	requires(Gyro.getInstance());
    	endAngle = Gyro.getInstance().getAngleAverage() + angle;
    	setTimeout(3); // Ensure the gyro doesn't get stuck
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Gyro.getInstance().resetGyro();
    	finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentAngleDifference = Gyro.getInstance().getAngleAverage() + endAngle;
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
    		finished = true;
    	}
    	System.err.println("Current angle: " + Gyro.getInstance().getAngleAverage() + ", Difference: " + currentAngleDifference + ", Power: " + power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
