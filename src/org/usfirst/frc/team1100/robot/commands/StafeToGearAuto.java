package org.usfirst.frc.team1100.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class StafeToGearAuto extends Command {

	private final double P = .3;
	
	public StafeToGearAuto() {
		requires(org.usfirst.frc.team1100.robot.subsystems.Drive.getInstance());
		org.usfirst.frc.team1100.robot.subsystems.Drive.getInstance().resetGyro();
	}
	
	@Override
	public void execute() {
		org.usfirst.frc.team1100.robot.subsystems.Drive.getInstance().resetGyro();
		
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public double errorFunction(double error) {
		return error *= -P;
	}
	
	public double getError() {
		return org.usfirst.frc.team1100.robot.subsystems.Drive.getInstance().getAngle0();
	}
	
}