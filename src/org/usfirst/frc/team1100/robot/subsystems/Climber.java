package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.climber.ClimberDefault;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

public class Climber extends Subsystem {

	private static Climber climber;
	private  CANTalon motor1;
	private  CANTalon motor2;


	public Climber() {
	motor1 = new CANTalon(RobotMap.C_MOTOR_1);
	motor2 = new CANTalon(RobotMap.C_MOTOR_2);
	
	motor2.setInverted(true);
	}
	
	public static Climber getInstance() {
		if(climber == null) {
			climber = new Climber();
		}
		return climber;
	}
	
	public void setSpeed(double value) {
		motor1.set(Math.min(value,0));
		motor2.set(Math.min(value,0));
	}
	
	public LiveWindowSendable climbLWS(){
		return (LiveWindowSendable) motor1;
	}
	public LiveWindowSendable climb2LWS(){
		return (LiveWindowSendable) motor2;
	}
	
	public double getClimberCurrentA(){
		return Robot.getPDP().getCurrent(RobotMap.P_CLIMBER_A);
	}
	
	public double getClimberCurrentB(){
		return Robot.getPDP().getCurrent(RobotMap.P_CLIMBER_B);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ClimberDefault());
	}

}
