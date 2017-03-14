package org.usfirst.frc.team1100.robot.commands.drive.user;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

public class UserReverseDrive extends Command{

	public UserReverseDrive(){
		requires(Drive.getInstance());
	}
	
	@Override
	public void execute(){
		Drive.getInstance().setReversed(Drive.getInstance().isReversed());
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
