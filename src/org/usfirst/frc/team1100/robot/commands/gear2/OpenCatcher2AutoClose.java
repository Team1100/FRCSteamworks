package org.usfirst.frc.team1100.robot.commands.gear2;

import org.usfirst.frc.team1100.robot.subsystems.Gear2;

import edu.wpi.first.wpilibj.command.Command;

public class OpenCatcher2AutoClose extends Command{

	boolean finished;
	long timeOpened;
	
	public OpenCatcher2AutoClose(){
		requires(Gear2.getInstance());
		finished = false;
	}
	
	@Override
	protected void initialize(){
		timeOpened = 0;
	}
	
	@Override
	protected void execute(){
		if(timeOpened==0){
			Gear2.getInstance().openCatcher();
			timeOpened=System.currentTimeMillis();
		}
		else if(System.currentTimeMillis()-timeOpened>=1000){
			Gear2.getInstance().closeCatcher();
			finished = true;
		}
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}

}
