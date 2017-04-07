package org.usfirst.frc.team1100.robot.commands.gear2;

import org.usfirst.frc.team1100.robot.subsystems.Gear2;

import edu.wpi.first.wpilibj.command.Command;

public class CloseCatcher2 extends Command{
boolean finished;
	
	public CloseCatcher2(){
		requires(Gear2.getInstance());
	}
	
	@Override
	public void initialize(){
		finished = false;
	}
	
	@Override
	protected void execute(){
		Gear2.getInstance().closeCatcher();
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
}
