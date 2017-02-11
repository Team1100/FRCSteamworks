package org.usfirst.frc.team1100.robot.commands.pneumatics.pistons;

import org.usfirst.frc.team1100.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class FirePiston extends Command {

	private boolean finished;
	private int piston;
	
	/**
	 * Constructor for the FirePiston command
	 * @param piston the piston to fire
	 * @param timeout the time until the command stops
	 */
	public FirePiston(int piston, long timeout) {
		requires(Pneumatics.getInstance());
		setTimeout(timeout);
		finished = false;
	}
	
	/**
	 * Called many times a second while the FirePiston command is being run
	 */
	public void execute() {
		Pneumatics.getInstance().getFireers()[piston].set(DoubleSolenoid.Value.kForward);
		finished = true;
	}

	/**
	 * Called to check of the command is finished or not
	 */
	@Override
	protected boolean isFinished() {
		return finished || isTimedOut();
	}

}
