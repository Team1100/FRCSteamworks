package org.usfirst.frc.team1100.robot.commands.climber;

import org.usfirst.frc.team1100.robot.subsystems.Climber;
import org.usfirst.frc.team1100.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetClimberSpeedCommand extends Command {

    private double speed;
    private long timeEnabled;

	public SetClimberSpeedCommand(double speed) {
        requires(Climber.getInstance());
        requires(Intake.getInstance());
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeEnabled = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    
    	//Prevent Damaging Climber
    	if(System.currentTimeMillis()-timeEnabled>1000
    			&&(Climber.getInstance().getClimberCurrentA()>45
    			||Climber.getInstance().getClimberCurrentB()>45)){
    		Climber.getInstance().setSpeed(0);
    	}else{
    		Climber.getInstance().setSpeed(speed);
    	}
    	
    	Intake.getInstance().stopRoller();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Climber.getInstance().setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
