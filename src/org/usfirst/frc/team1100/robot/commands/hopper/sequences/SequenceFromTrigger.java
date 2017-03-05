package org.usfirst.frc.team1100.robot.commands.hopper.sequences;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.commands.hopper.util.CloseAll;
import org.usfirst.frc.team1100.robot.input.XboxController;
import org.usfirst.frc.team1100.robot.subsystems.Hopper;

import edu.wpi.first.wpilibj.command.Command;

public class SequenceFromTrigger extends Command {

	private Command sequence;

	public SequenceFromTrigger() {
		requires(Hopper.getInstance());
	}

	@Override
	protected void initialize() {
		sequence = new FireSequence();
	}

	protected void execute() {
		if (Robot.tele) {
			if (!(OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kRightTrigger) == 0
					|| sequence.isRunning())) {
				sequence.start();
			} else {
				sequence.cancel();
				new CloseAll().start();
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}