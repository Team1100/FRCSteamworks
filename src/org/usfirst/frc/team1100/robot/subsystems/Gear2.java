package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.gear2.Gear2Default;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

public class Gear2 extends Subsystem{

	private static Gear2 instance;
	
	private DoubleSolenoid clamp;
	private DoubleSolenoid ramp;
	
	private AnalogInput ultrasound;
	
	public static Gear2 getInstance(){
		if(instance==null)instance=new Gear2();
		return instance;
	}
	
	public Gear2(){
		clamp = new DoubleSolenoid(RobotMap.G2_PCM, RobotMap.G_FIRER_0_FORWARD,RobotMap.G_FIRER_0_REVERSE);
		ramp = new DoubleSolenoid(RobotMap.G2_PCM, RobotMap.G_FIRER_1_FORWARD, RobotMap.G_FIRER_1_REVERSE);
		
		ultrasound = new AnalogInput(RobotMap.G_ULTRASOUND);
	}
	
	private void setClamp(Value value){
		clamp.set(value);
	}
	
	private void setRamp(Value value){
		ramp.set(value);
	}
	
	public boolean isPegIn() {
		return ultrasound.getValue() < 290;
	}
	
	public int getU(){
		return ultrasound.getValue();
	}
	
	public void openCatcher(){
		setClamp(Value.kReverse);
	}
	public void closeCatcher(){
		setClamp(Value.kForward);
	}
	
	public void toggleCatcher(){
		setClamp(clamp.get().equals(Value.kReverse)? Value.kForward:Value.kReverse);
	}
	
	public void openRamp(){
		setRamp(Value.kReverse);
	}
	public void closeRamp(){
		setRamp(Value.kForward);
	}
	
	public void toggleRamp(){
		setRamp(clamp.get().equals(Value.kReverse)? Value.kForward:Value.kReverse);
	}
	
	public LiveWindowSendable gear2LWS1(){
		return (LiveWindowSendable) clamp;
	}
	
	public LiveWindowSendable gear2LWS2(){
		return (LiveWindowSendable) ramp;
	}
	/**
	 * 
	 * @return True is open, False is closed
	 */
	public boolean get(){
		return clamp.get().equals(Value.kReverse);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Gear2Default());
	}

}
