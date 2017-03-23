package org.usfirst.frc.team1100.robot.commands.gear2;

import org.usfirst.frc.team1100.robot.subsystems.Gear2;

import edu.wpi.first.wpilibj.command.Command;

public class OpenRamp extends Command{

	boolean finished;
	
	public OpenRamp(){
		requires(Gear2.getInstance());
		finished = false;
	}
	
	@Override
	protected void execute(){
		Gear2.getInstance().openRamp();
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}

}
