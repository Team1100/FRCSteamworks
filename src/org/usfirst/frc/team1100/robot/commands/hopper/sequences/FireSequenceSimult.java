package org.usfirst.frc.team1100.robot.commands.hopper.sequences;

import org.usfirst.frc.team1100.robot.commands.hopper.OpenThenClose2;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class FireSequenceSimult extends CommandGroup {

	/**
	 * Fires the shooter penumatics
	 */
	public FireSequenceSimult() {
		addSequential(new OpenThenClose2(true));
		addSequential(new OpenThenClose2(false));
	}
}