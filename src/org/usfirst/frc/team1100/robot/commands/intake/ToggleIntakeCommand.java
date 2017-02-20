package org.usfirst.frc.team1100.robot.commands.intake;

import org.usfirst.frc.team1100.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleIntakeCommand extends Command {

	private double speed;
	private boolean done;
	
    public ToggleIntakeCommand(double speed) {
        requires(Intake.getInstance());
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	done = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Intake.getInstance().getRollerSpeed()==0){
    		Intake.getInstance().setRollerSpeed(speed);
    	}else Intake.getInstance().stopRoller();
    	done = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
