package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterSpeedPID extends Command {

	private double speed;
	private final double ACCEPTABLE_ERROR = 500;
	
    public ShooterSpeedPID(double speed) {
    	System.err.println("JOOOOOOSSSSSSHHHHHHH!!!");
        requires(Shooter.getInstance());
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.err.println("The shooter speed is set!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Shooter.getInstance().setSetpoint(speed);
    	try {
			wait((long) 0.02);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Shooter.getInstance().getSetpoint() - Shooter.getInstance().getPosition()) < ACCEPTABLE_ERROR;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.err.println("Finished");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
