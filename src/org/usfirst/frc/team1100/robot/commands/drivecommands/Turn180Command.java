package org.usfirst.frc.team1100.robot.commands.drivecommands;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

public class Turn180Command extends Command {

	public boolean finished; //This is the sentinel of the abstract loop this command runs in
	
	public Turn180Command() {
		requires(Drive.getInstance()); //Gotta go fast
	}
	
	public void initialize() {
		finished = false; //Initialize variable
	}
	
	public void execute() {
		// I'll fix this if the git stuff works
	}
	
	public void interrupted() {
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		
		return finished;
	}
	
}
