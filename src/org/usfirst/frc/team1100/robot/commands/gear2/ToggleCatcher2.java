package org.usfirst.frc.team1100.robot.commands.gear2;

import org.usfirst.frc.team1100.robot.subsystems.Gear2;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ToggleCatcher2 extends Command{

boolean finished;
	
	public ToggleCatcher2(){
		requires(Gear2.getInstance());
		finished = false;
	}
	
	@Override
	protected void execute(){
		Gear2.getInstance().toggleCatcher();
		SmartDashboard.putBoolean("Gear2", Gear2.getInstance().get());
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}
