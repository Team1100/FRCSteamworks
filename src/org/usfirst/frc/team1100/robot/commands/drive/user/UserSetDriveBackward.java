package org.usfirst.frc.team1100.robot.commands.drive.user;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UserSetDriveBackward extends Command{

	private boolean finished;
	
	public UserSetDriveBackward(){
		requires(Drive.getInstance());
	}
	
	@Override
	public void initialize(){
		this.finished = false;
	}
	
	@Override
	public void execute(){
		Drive.getInstance().setReversed(true);
		SmartDashboard.putBoolean("REVERSED", Drive.getInstance().isReversed());
		this.finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}

}
