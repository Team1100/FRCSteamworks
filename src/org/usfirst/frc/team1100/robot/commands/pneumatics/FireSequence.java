package org.usfirst.frc.team1100.robot.commands.pneumatics;

import org.usfirst.frc.team1100.robot.commands.pneumatics.pistons.FirePiston;
import org.usfirst.frc.team1100.robot.commands.pneumatics.pistons.RetractPiston;
import org.usfirst.frc.team1100.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FireSequence extends CommandGroup {
	
	boolean finished;
	
	public FireSequence() {
		
		requires(Pneumatics.getInstance());
		for(int i = 0; i < 3; i++) {
			finished = false;
			addSequential(new FirePiston(i));
			
			double wait = (SmartDashboard.getNumber("Fire sequence wait value", 1));
				addSequential(new WaitCommand(wait));
			
			addSequential(new RetractPiston(i));
			
		}
		
	}

}
