package org.usfirst.frc.team1100.robot.commands.hopper.pistons;

import org.usfirst.frc.team1100.robot.subsystems.Hopper;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class FirePiston extends Command {

	private boolean finished;
	private int piston;
	
	/**
	 * Constructor for the FirePiston command
	 * @param piston the piston to fire
	 */
	public FirePiston(int piston) {
		requires(Hopper.getInstance());
		finished = false;
		this.piston = piston;
	}
	
	/**
	 * Called many times a second while the FirePiston command is being run
	 */
	public void execute() {
		Hopper.getInstance().getFirers()[piston].set(DoubleSolenoid.Value.kForward);
		finished = true;
	}

	/**
	 * Called to check of the command is finished or not
	 */
	@Override
	protected boolean isFinished() {
		return finished;
	}

}
