package org.usfirst.frc.team1100.robot.commands.pneumatics.pistons;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FirePiston extends Command {

	private boolean finished;
	private int piston;
	
	public FirePiston(int piston) {
		requires(Pneumatics.getInstance());
		finished = false;
		this.piston = piston;
	}
	public FirePiston(int piston, long timeout) {
		requires(Pneumatics.getInstance());
		setTimeout(timeout);
		finished = false;
	}
	
	public void execute() {
		Pneumatics.getInstance().getFireers()[piston].set(DoubleSolenoid.Value.kForward);
	}

	
	@Override
	protected boolean isFinished() {
		return finished || isTimedOut();
	}

}
