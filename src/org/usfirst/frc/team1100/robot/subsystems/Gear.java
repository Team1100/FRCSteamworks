package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

public class Gear extends Subsystem{

	private static Gear instance;
	
	private Solenoid catcher;
	
	public static Gear getInstance(){
		if(instance==null)instance=new Gear();
		return instance;
	}
	
	public Gear(){
		catcher = new Solenoid(RobotMap.G_PCM, RobotMap.G_CATCHER);
	}
	
	public void openCatcher(){
		catcher.set(false);
	}
	public void closeCatcher(){
		catcher.set(true);
	}
	
	public void toggleCatcher(){
		catcher.set(!catcher.get());
	}
	
	public LiveWindowSendable gearLWS(){
		return (LiveWindowSendable) catcher;
	}
	
	public boolean get(){
		return catcher.get();
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
