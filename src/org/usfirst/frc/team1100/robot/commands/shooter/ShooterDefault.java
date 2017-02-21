package org.usfirst.frc.team1100.robot.commands.shooter;

import org.usfirst.frc.team1100.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterDefault extends Command{

	public ShooterDefault(){
		requires(Shooter.getInstance());
	}
	
	public void execute(){
		if(Shooter.getInstance().getOn()){
			Shooter.getInstance().setSpeedToTarget();
		}else Shooter.getInstance().setFlywheelSpeed(0);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
