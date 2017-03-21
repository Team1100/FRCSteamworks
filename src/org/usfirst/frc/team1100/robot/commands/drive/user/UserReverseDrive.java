package org.usfirst.frc.team1100.robot.commands.drive.user;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UserReverseDrive extends Command{

	private boolean finished;
	
	public UserReverseDrive(){
		requires(Drive.getInstance());
	}
	
	@Override
	public void initialize(){
		this.finished = false;
	}
	
	@Override
	public void execute(){
		Drive.getInstance().setReversed(!Drive.getInstance().isReversed());
		SmartDashboard.putBoolean("REVERSED", true);
		this.finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}

}
