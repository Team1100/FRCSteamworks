package org.usfirst.frc.team1100.robot.commands.intake;

import org.usfirst.frc.team1100.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A command which sets the speed of the intake
 * @author supremesteak1
 */
public class SetIntakeSpeedCommand extends Command {
	
	private double speed;
	private double timeout;

	/**
	 * The constructor for the SetIntakeSpeedCommand
	 * @param speed the speed for the intake to be set too
	 */
	public SetIntakeSpeedCommand(double speed) {
    	requires(Intake.getInstance());
    	this.speed = speed;
    	this.timeout = 0;
    }

    /**
     * Called just before the command is run the first time
     */
    protected void initialize() {
    	if(timeout != 0) {
    		setTimeout(timeout);
    	}
    }

    /**
     * Called many times a second while the command is running
     */
    protected void execute() {
    	Intake.getInstance().setRollerSpeed(speed);
    }

    /**
     * Returns if the command is finished or not
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Called when the command ends
     */
    protected void end() {
    	Intake.getInstance().stopRoller();
    }

    /**
     * Called if the command is interrupted
     */
    protected void interrupted() {
    	end();
    }
}
