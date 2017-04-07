package org.usfirst.frc.team1100.robot.commands.gear2;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Gear2;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gear2Default extends Command{

	private boolean triggered = false;
	private final double DRIVE_SPEED = .4;
	private final long TIME = 700;
	private long driveTime; 
	
	public Gear2Default(){
		requires(Gear2.getInstance());
	}
	
	@Override
	public void execute(){
		SmartDashboard.putBoolean("Peg", Gear2.getInstance().isPegIn());
		if(Gear2.getInstance().isPegIn()){
			Gear2.getInstance().openCatcher();
			//Drive.getInstance().driveTank(.6, -.6);
			
		}else {
			Gear2.getInstance().closeCatcher();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
