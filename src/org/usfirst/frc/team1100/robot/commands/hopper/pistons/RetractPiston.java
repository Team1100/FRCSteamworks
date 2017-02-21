package org.usfirst.frc.team1100.robot.commands.hopper.pistons;

import org.usfirst.frc.team1100.robot.subsystems.Hopper;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class RetractPiston extends Command {

	private boolean finished;
	private int piston;
	
	public RetractPiston(int piston) {
		requires(Hopper.getInstance());
		finished = false;
		this.piston = piston;
	}
	
	public void execute() {
		if(Hopper.getInstance().getFirers()[piston].get()==DoubleSolenoid.Value.kForward)
			Hopper.getInstance().getFirers()[piston].set(DoubleSolenoid.Value.kReverse);
		finished = true;
	}

	
	@Override
	protected boolean isFinished() {
		return finished;
	}

}
