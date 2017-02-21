package org.usfirst.frc.team1100.robot.deprecated;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

@Deprecated
public class Turn180Command extends Command {

	public boolean finished; //This is the sentinel of the abstract loop this command runs in
	
	@Deprecated
	public Turn180Command() {
		requires(Drive.getInstance()); //Gotta go fast
	}
	
	@Deprecated
	public void initialize() {
		finished = false; //Initialize variable
	}
	
	@Deprecated
	public void execute() {
		// I'll fix this if the git stuff works
	}
	
	@Deprecated
	public void interrupted() {
		finished = true;
	}
	
	@Deprecated
	@Override
	protected boolean isFinished() {
		
		return finished;
	}
	
}
