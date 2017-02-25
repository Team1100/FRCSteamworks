package org.usfirst.frc.team1100.robot.commands.shooter;

import org.usfirst.frc.team1100.robot.commands.hopper.util.OpenThenCloseAll;
import org.usfirst.frc.team1100.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterDefault extends Command{

	
	
	public ShooterDefault(){
		requires(Shooter.getInstance());
	}
	
	public void execute(){
		SmartDashboard.putNumber("Current A", Shooter.getInstance().getShooterCurrentA());
		SmartDashboard.putNumber("Current B", Shooter.getInstance().getShooterCurrentB());
		SmartDashboard.putNumber("Average Current", Shooter.getInstance().getAverageCurrent());
		
		if(Shooter.getInstance().getShooterCurrentA()>=Shooter.CURRENT_THRESHOLD
				||Shooter.getInstance().getShooterCurrentB()>=Shooter.CURRENT_THRESHOLD){
			new OpenThenCloseAll().start();
		}
		
		if(Shooter.getInstance().getOn()){
			Shooter.getInstance().setSpeedToTarget();
			Shooter.getInstance().setFlap(true);
		}else{
			Shooter.getInstance().setFlywheelSpeed(0);
			Shooter.getInstance().setFlap(false);
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
