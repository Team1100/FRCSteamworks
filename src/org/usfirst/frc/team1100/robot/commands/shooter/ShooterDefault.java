package org.usfirst.frc.team1100.robot.commands.shooter;

import org.usfirst.frc.team1100.robot.commands.hopper.util.CloseAll;
import org.usfirst.frc.team1100.robot.commands.hopper.util.OpenThenCloseAll;
import org.usfirst.frc.team1100.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterDefault extends Command{

	private double timeEnabled;
	
	
	public ShooterDefault(){
		requires(Shooter.getInstance());
	}
	
	public void execute(){
		// Display current values to the smart dash
		SmartDashboard.putNumber("Current A", Shooter.getInstance().getShooterCurrentA());
		SmartDashboard.putNumber("Current B", Shooter.getInstance().getShooterCurrentB());
		SmartDashboard.putNumber("Average Current", Shooter.getInstance().getAverageCurrent());
		
		// Blocks for controlling speed of wheel and flap
		if(Shooter.getInstance().getOn()){
			if(timeEnabled==0)timeEnabled = System.currentTimeMillis();
			Shooter.getInstance().setSpeedToTarget();
			Shooter.getInstance().setFlap(true);
		}else{
			timeEnabled = 0;
			Shooter.getInstance().setFlywheelSpeed(0);
			Shooter.getInstance().setFlap(false);
		}
		
		// Prevent hopper pistons from firing early on when speed is low
		if(Shooter.getInstance().getSpeed()<Shooter.MIN_SPEED
				&&System.currentTimeMillis()-timeEnabled<1000){
			new CloseAll().start();
		}
		
		// Open all valves if a ball jams
		if((Shooter.getInstance().getShooterCurrentA()>=Shooter.CURRENT_THRESHOLD
				||Shooter.getInstance().getShooterCurrentB()>=Shooter.CURRENT_THRESHOLD)
				&&(System.currentTimeMillis()-timeEnabled)>1000){
			new OpenThenCloseAll().start();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}