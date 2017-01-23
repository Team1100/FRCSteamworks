package org.usfirst.frc.team1100.robot.commands.augar;

import org.usfirst.frc.team1100.robot.subsystems.Augar;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetAugarSpeedCommand extends Command {

	private double speed;
	private double timeout;
	
    public SetAugarSpeedCommand(double speed) {
        requires(Augar.getInstance());
        this.speed = speed;
        this.timeout = 0;
    }
    
    public SetAugarSpeedCommand(double speed, double timeout) {
        requires(Augar.getInstance());
        this.speed = speed;
        this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(timeout !=0) {
    		setTimeout(timeout);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Augar.getInstance().setAugarSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Augar.getInstance().stopAugar();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
