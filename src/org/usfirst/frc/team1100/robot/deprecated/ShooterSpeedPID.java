package org.usfirst.frc.team1100.robot.deprecated;

import org.usfirst.frc.team1100.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
@Deprecated
public class ShooterSpeedPID extends Command {

	private double speed;
	private final double ACCEPTABLE_ERROR = 500;
	
    public ShooterSpeedPID(double speed) {
    	System.err.println("Jx1Ox6Sx6Hx7!x3");
        requires(Shooter.getInstance());
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.err.println("Shooter speed set.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Yeah, nothing here now
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.err.println("Finished " + this.getName());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
