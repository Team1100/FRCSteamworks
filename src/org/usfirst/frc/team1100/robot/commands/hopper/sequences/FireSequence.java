package org.usfirst.frc.team1100.robot.commands.hopper.sequences;

import org.usfirst.frc.team1100.robot.commands.hopper.OpenThenClose;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class FireSequence extends CommandGroup {

	/**
	 * Fires the shooter penumatics
	 */
	public FireSequence() {
		for(int i = 0; i<4;i++){
			addSequential(new OpenThenClose(i));
		}
	}
}