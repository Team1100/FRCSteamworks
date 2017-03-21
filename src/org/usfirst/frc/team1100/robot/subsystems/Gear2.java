package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

public class Gear2 extends Subsystem{

	private static Gear2 instance;
	
	private DoubleSolenoid clamp;
	private DoubleSolenoid clamp2;
	
	public static Gear2 getInstance(){
		if(instance==null)instance=new Gear2();
		return instance;
	}
	
	public Gear2(){
		clamp = new DoubleSolenoid(RobotMap.G2_PCM, RobotMap.G_FIRER_0_FORWARD,RobotMap.G_FIRER_0_REVERSE);
		clamp2 = new DoubleSolenoid(RobotMap.G2_PCM, RobotMap.G_FIRER_1_FORWARD, RobotMap.G_FIRER_1_REVERSE);
	}
	
	private void setDirection(Value value){
		clamp.set(value);
		clamp2.set(value);
	}
	
	public void openCatcher(){
		setDirection(Value.kForward);
	}
	public void closeCatcher(){
		setDirection(Value.kReverse);
	}
	
	public void toggleCatcher(){
		setDirection(clamp.get().equals(Value.kForward)? Value.kReverse:Value.kForward);
	}
	
	public LiveWindowSendable gear2LWS1(){
		return (LiveWindowSendable) clamp;
	}
	
	public LiveWindowSendable gear2LWS2(){
		return (LiveWindowSendable) clamp2;
	}
	/**
	 * 
	 * @return True is open, False is closed
	 */
	public boolean get(){
		return clamp.get().equals(Value.kForward);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
