package org.usfirst.frc.team1100.robot.commands.gear2;

import org.usfirst.frc.team1100.robot.subsystems.Gear2;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleRamp extends Command{

boolean finished;
	
	public ToggleRamp(){
		requires(Gear2.getInstance());
		finished = false;
	}
	
	@Override
	protected void execute(){
		Gear2.getInstance().toggleRamp();
		System.err.println("ROMP");
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}
