package org.usfirst.frc.team1100.robot.commands.hopper.sequences;

import org.usfirst.frc.team1100.robot.commands.hopper.OpenThenClose2;
import org.usfirst.frc.team1100.robot.subsystems.Hopper;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class FireSequenceSimult extends CommandGroup {

	/**
	 * Fires the shooter penumatics
	 */
	public FireSequenceSimult() {
		addSequential(new OpenThenClose2(true));
		addSequential(new WaitCommand(Hopper.HOP_DELAY));
		addSequential(new OpenThenClose2(false));
		addSequential(new WaitCommand(Hopper.HOP_DELAY));
	}
}