package org.usfirst.frc.team1100.robot.commands.intake;

import org.usfirst.frc.team1100.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetIntakeSpeedCommand extends Command {
	
	private double speed;
	private double timeout;

	public SetIntakeSpeedCommand(double speed) {
    	requires(Intake.getInstance());
    	this.speed = speed;
    	this.timeout = 0;
    }
	
    public SetIntakeSpeedCommand(double speed, double timeout) {
    	requires(Intake.getInstance());
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
    	Intake.getInstance().setRollerSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Intake.getInstance().stopRoller();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
