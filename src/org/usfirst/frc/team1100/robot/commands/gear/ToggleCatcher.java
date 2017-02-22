package org.usfirst.frc.team1100.robot.commands.gear;

import org.usfirst.frc.team1100.robot.subsystems.Gear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ToggleCatcher extends Command{

boolean finished;
	
	public ToggleCatcher(){
		requires(Gear.getInstance());
		finished = false;
	}
	
	@Override
	protected void execute(){
		Gear.getInstance().toggleCatcher();
		SmartDashboard.putBoolean("Gear", Gear.getInstance().get());
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}
