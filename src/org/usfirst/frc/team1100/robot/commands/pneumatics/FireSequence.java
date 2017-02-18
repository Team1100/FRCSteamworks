package org.usfirst.frc.team1100.robot.commands.pneumatics;

import org.usfirst.frc.team1100.robot.commands.pneumatics.pistons.FirePiston;
import org.usfirst.frc.team1100.robot.commands.pneumatics.pistons.RetractPiston;
import org.usfirst.frc.team1100.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FireSequence extends CommandGroup {
	
	/**
	 * Fires the shooter penumatics in order, with a certain wait time in between as specified by the SmartDashboard
	 */
	public FireSequence() {
		
		requires(Pneumatics.getInstance());
		int i = 0;
		
		while(i != 4) {
			addSequential(new FirePiston(i,2)); // Start off by firing the piston
				
			double wait = SmartDashboard.getNumber("Fire sequence wait value", 1); // Get the wait time specified on the SmartDashboard
			addSequential(new WaitCommand(wait)); // Wait for the time gotten in the previous line
				
			addSequential(new RetractPiston(i)); // Retract the piston when the wait is over
			switch(i) {
				case 0:
					i = 2;
					break;
				case 1:
					i = 3;
					break;
				case 2:
					i = 1;
					break;
				case 3:
					i = 4;
					break;
				default:
					i = 0;
					break;
			}
		}
	}

}
