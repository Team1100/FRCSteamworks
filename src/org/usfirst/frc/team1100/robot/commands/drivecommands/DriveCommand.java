package org.usfirst.frc.team1100.robot.commands.drivecommands;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {
	
	private boolean finished;
	private double distance;
	
	/**
	 * Constructor for the DriveCommand
	 * @param distance distance to travel in inches
	 * @param timeout the time in seconds until the command is forcefully stopped
	 */
	public DriveCommand(double distance, int timeout) {
		setTimeout(timeout);
		this.distance = distance;
	}
	
	/**
     * Called right before the command runs
     */
    protected void initialize() {
    	finished = false;
    }

    /**
     * Called many times a second while the command is running
     */
    protected void execute() {
    	
    }

    /**
     * Returns if the command has either timed out or has finished running
     */
    protected boolean isFinished() {
        return finished | isTimedOut();
    }

    /**
     * Called when the command is finished
     */
    protected void end() {
    	
    }

    /**
     * Called if the command is interrupted
     */
    protected void interrupted() {
    	end();
    }
}
