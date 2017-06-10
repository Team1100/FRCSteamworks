package org.usfirst.frc.team1100.robot.commands.shooter;

import org.usfirst.frc.team1100.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A command which toggles the shooter between its on and off states
 */
public class ToggleShooterCommand extends Command {

	private boolean done;

	/**
	 * Initialize the toggle shooter command
	 */
	public ToggleShooterCommand() {
		// Make sure the shooter subsystem is available
        requires(Shooter.getInstance());
    }
	
	/**
	 * Initialize the command
	 */
	public void initialize() {
		// Ensure that the command is not done yet as nothing has been done yet
		done = false;
	}
	
	/**
	 * Execute the toggling command
	 */
    public void execute() {
    	// Set the shooter state to the opposite of what it currently is
    	Shooter.getInstance().setOn(!Shooter.getInstance().getOn());
    	//Just turn it on all the time to test
    	//Shooter.getInstance().setOn(true);
    	// Print out the current state of the shooter
    	System.err.println("Shooter is: " + Shooter.getInstance().getOn());
    	// Set the command to done so it only toggles once
    	done = true;
    }
    
	@Override
	/**
	 * Returns if the toggling is done or not
	 * @return if the toggling is done or not
	 */
	protected boolean isFinished() {
		return done;
	}
}
