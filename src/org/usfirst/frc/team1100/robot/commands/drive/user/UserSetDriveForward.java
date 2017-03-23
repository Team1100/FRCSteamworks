package org.usfirst.frc.team1100.robot.commands.drive.user;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UserSetDriveForward extends Command{

	private boolean finished;
	
	public UserSetDriveForward(){
		requires(Drive.getInstance());
	}
	
	@Override
	public void initialize(){
		this.finished = false;
	}
	
	@Override
	public void execute(){
		Drive.getInstance().setReversed(false);
		SmartDashboard.putBoolean("REVERSED", Drive.getInstance().isReversed());
		this.finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}

}
