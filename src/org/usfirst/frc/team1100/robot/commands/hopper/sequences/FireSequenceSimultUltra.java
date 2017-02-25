package org.usfirst.frc.team1100.robot.commands.hopper.sequences;

import org.usfirst.frc.team1100.robot.commands.hopper.util.OpenThenCloseAll;
import org.usfirst.frc.team1100.robot.subsystems.Hopper;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class FireSequenceSimultUltra extends CommandGroup {

	/**
	 * Fires the shooter penumatics
	 */
	public FireSequenceSimultUltra() {
		addSequential(new OpenThenCloseAll());
		addSequential(new WaitCommand(Hopper.HOP_DELAY+.05));
	}
}