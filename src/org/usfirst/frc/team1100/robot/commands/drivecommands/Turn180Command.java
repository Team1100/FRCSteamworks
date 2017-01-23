package org.usfirst.frc.team1100.robot.commands.drivecommands;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

public class Turn180Command extends Command {

	private boolean finished; //This is the sentinel of the abstract loop this command runs in
	private double gyroAngle;
	
	public Turn180Command() {
		requires(Drive.getInstance()); //Gotta go fast
	}
	
	public void initialize() {
		finished = false; //Initialize variable
		gyroAngle = (Drive.getInstance().getAngle()+180)%360;
	}
	
	public void execute() {
<<<<<<< HEAD
		
		Drive.getInstance().driveMecanum(0,0,Math.PI); //Move TODO: Check if this actually uses radians.
		finished = true;
=======
		double gyroDifference = gyroAngle - Drive.getInstance().getAngle();
		Drive.getInstance().driveMecanum(0,0,0.5);
		if(Math.abs(gyroDifference) < 10) {
			finished = true;
		}
>>>>>>> origin/dev
		
	}
	
	public void interrupted() {
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}
