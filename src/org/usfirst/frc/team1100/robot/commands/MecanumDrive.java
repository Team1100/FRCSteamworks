package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

public class MecanumDrive extends Command {
	
	private static RobotDrive robotDrive;
	
	public MecanumDrive() {
		requires(Drive.getInstance());
	}
	
	@Override
	public void execute() {
		Drive.getInstance().driveMecanum(OI.getInstance().getLeftStick().getX(), OI.getInstance().getLeftStick().getY(),OI.getInstance().getRightStick().getY(), 0.0);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}