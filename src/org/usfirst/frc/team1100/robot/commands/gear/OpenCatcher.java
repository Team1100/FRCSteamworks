package org.usfirst.frc.team1100.robot.commands.gear;

import org.usfirst.frc.team1100.robot.subsystems.Gear;

import edu.wpi.first.wpilibj.command.Command;

public class OpenCatcher extends Command{

	boolean finished;
	
	public OpenCatcher(){
		requires(Gear.getInstance());
		finished = false;
	}
	
	@Override
	protected void execute(){
		Gear.getInstance().openCatcher();
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}

}
