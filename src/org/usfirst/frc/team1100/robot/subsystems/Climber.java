package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

public class Climber extends Subsystem {

	private static Climber climber;
	
	private static CANTalon talon1;
	private static CANTalon talon2;
	
	private int P;
	private int I;
	private int D;
	
	public Climber() {
	talon1 = new CANTalon(RobotMap.C_MOTOR_1);
	talon2 = new CANTalon(RobotMap.C_MOTOR_2);
	
	talon1.setInverted(true);
	
	P=I=D=1;
	talon1.setPID(P, I, D);
	talon2.setPID(P, I, D);
	}
	
	public static Climber getInstance() {
		if(climber == null) {
			climber = new Climber();
		}
		return climber;
	}
	
	public void setSpeed(double value) {
		talon1.set(Math.max(value,-0.2));
		talon2.set(Math.max(value,-0.2));
	}
	
	public LiveWindowSendable climbLWS(){
		return (LiveWindowSendable) talon1;
	}
	public LiveWindowSendable climb2LWS(){
		return (LiveWindowSendable) talon2;
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
