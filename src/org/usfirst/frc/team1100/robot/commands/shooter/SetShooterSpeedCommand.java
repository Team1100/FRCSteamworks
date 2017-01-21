package org.usfirst.frc.team1100.robot.commands.shooter;

import org.usfirst.frc.team1100.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetShooterSpeedCommand extends Command {
	
	private double speed;
	private double timeout;
	
	public SetShooterSpeedCommand(double speed) {
        requires(Shooter.getInstance());
        this.speed = speed;
        this.timeout = 0;
    }
	
    public SetShooterSpeedCommand(double speed, double timeout) {
        requires(Shooter.getInstance());
        this.speed = speed;
        this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(timeout != 0) {
    		setTimeout(timeout);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Shooter.getInstance().setFlywheelSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Shooter.getInstance().stopFlywheel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
