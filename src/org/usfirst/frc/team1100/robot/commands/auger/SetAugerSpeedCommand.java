package org.usfirst.frc.team1100.robot.commands.auger;

import org.usfirst.frc.team1100.robot.subsystems.Auger;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetAugerSpeedCommand extends Command {

	private double speed;
	private double timeout;
	
    public SetAugerSpeedCommand(double speed) {
        requires(Auger.getInstance());
        this.speed = speed;
        this.timeout = 0;
    }
    
    public SetAugerSpeedCommand(double speed, double timeout) {
        requires(Auger.getInstance());
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
    	Auger.getInstance().setAugarSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Auger.getInstance().stopAugar();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
