package org.usfirst.frc.team1100.robot.commands.shooter.auto;

import org.usfirst.frc.team1100.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class AutoWheel extends Command{

	private double timeout;

	public AutoWheel(double timeout){
		requires(Shooter.getInstance());
		this.timeout=timeout;
	}
	
	public void initialize(){
		setTimeout(this.timeout);
	}
	
	public void execute(){
		Shooter.getInstance().setSpeedToTarget();
		Shooter.getInstance().setFlap(true);
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
	
	protected void end(){
		Shooter.getInstance().setFlap(false);
	}

}
