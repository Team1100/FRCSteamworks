package org.usfirst.frc.team1100.robot.commands.gear2;

import org.usfirst.frc.team1100.robot.subsystems.Gear2;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gear2Default extends Command{

	public Gear2Default(){
		requires(Gear2.getInstance());
	}
	
	@Override
	public void execute(){
		SmartDashboard.putBoolean("Peg", Gear2.getInstance().isPegIn());
		SmartDashboard.putNumber("PegU", Gear2.getInstance().getU());
		
		if(Gear2.getInstance().isPegIn()){
			//Gear2.getInstance().openCatcher();
		}
		
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
