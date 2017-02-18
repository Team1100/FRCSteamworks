package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

	private static Climber climber;
	
	private static CANTalon talon1;
	private static CANTalon talon2;
	
	private int P;
	private int I;
	private int D;
	
	public Climber() {
	talon1 = new CANTalon(RobotMap.CAN_2);
	talon2 = new CANTalon(RobotMap.CAN_3);
	
	talon1.setPID(P, I, D);
		
	talon2.changeControlMode(TalonControlMode.Follower);
	talon2.set(talon1.getDeviceID());
	}
	
	public static Climber getInstance() {
		if(climber == null) {
			climber = new Climber();
		}
		return climber;
	}
	
	public void setSpeed(double value) {
		talon1.set(Math.max(value,-0.2));
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
