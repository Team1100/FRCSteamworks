package org.usfirst.frc.team1100.robot.commands.drive;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command resets the gyro to 0 degrees
 */
public class ResetGyroCommand extends Command {

    public ResetGyroCommand() {
        requires(Drive.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Drive.getInstance().resetGyro();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.err.println("Drive reset.");
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
