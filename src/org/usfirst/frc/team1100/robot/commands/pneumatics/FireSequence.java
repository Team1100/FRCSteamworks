package org.usfirst.frc.team1100.robot.commands.pneumatics;

import org.usfirst.frc.team1100.robot.commands.pneumatics.pistons.FirePiston;
import org.usfirst.frc.team1100.robot.commands.pneumatics.pistons.RetractPiston;
import org.usfirst.frc.team1100.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FireSequence extends CommandGroup {
	
	boolean finished;
	
	public FireSequence() {
		
		requires(Pneumatics.getInstance());
		for(int i = 0; i < 3; i++) {
			finished = false;
			addSequential(new FirePiston(i));
			while(!finished) {
				if(Pneumatics.getInstance().getFireers()[i].get().equals(DoubleSolenoid.Value.kOff)) {
					finished = true;
				}
			}
			addSequential(new RetractPiston(i));
			
		}
	}

}
