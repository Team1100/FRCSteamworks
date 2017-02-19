package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gear extends Subsystem{

	private static Gear instance;
	
	private DoubleSolenoid catcher;
	
	public static Gear getInstance(){
		if(instance==null)instance=new Gear();
		return instance;
	}
	
	public Gear(){
		catcher = new DoubleSolenoid(RobotMap.G_PCM, RobotMap.G_CATCHER_A, RobotMap.G_CATCHER_B);
	}
	
	public void openCatcher(){
		catcher.set(DoubleSolenoid.Value.kForward);
	}
	public void closeCatcher(){
		catcher.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void toggleCatcher(){
		if(catcher.get()==DoubleSolenoid.Value.kForward){
			closeCatcher();
		}else openCatcher();
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
