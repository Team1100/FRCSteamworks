package org.usfirst.frc.team1100.robot.commands.hopper.sequences;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.commands.hopper.util.CloseAll;
import org.usfirst.frc.team1100.robot.input.XboxController.XboxAxis;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SequenceFromTrigger extends CommandGroup {

	public SequenceFromTrigger() {
		if (OI.getInstance().getXbox().getAxis(XboxAxis.kRightTrigger) > 0) {
			addSequential(new FireSequence());
		}else{
			addSequential(new CloseAll());
		}
	}
}