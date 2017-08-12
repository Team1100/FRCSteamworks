package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.climber.ClimberDefault;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

public class Climber extends Subsystem {

	private static Climber climber;
	private CANTalon motor1; // What even is this?
	private CANTalon motor2,motor3,motor4; // And this???
	private Talon motor1a, motor1b;

	// this is a message brought to you by Grant

	public Climber() {
		// I have no idea what any of these motors are for. Please remove.
		//motor1 = new CANTalon(RobotMap.C_MOTOR_1); // This is the same motor as 1a, just being used in a CAN context
		//motor2 = new CANTalon(RobotMap.C_MOTOR_2); // This is the same motor as 1b
		//motor3 = new CANTalon(3); // I don't know what this is
		//motor4 = new CANTalon(4); // I don;t know what this is either
		
		
		// These are the motors which the climber actually use.
		motor1a = new Talon(4);
		motor1b = new Talon(5); // This one is mechanically inverted.
		
		//motor2.setInverted(true); // Invert motor2 apparently.
	}
	
	/**
	 * Gets the static instance of the climber subsystem. If none exists, one is created.
	 * @return the static instance of the climber subsystem
	 */
	public static Climber getInstance() {
		if(climber == null) {
			climber = new Climber();
		}
		return climber;
	}
	
	/**
	 * Sets the speed of the climber motors
	 * @param value the speed to set the climber motors to
	 */
	public void setSpeed(double value) {
		//motor1.set(value);
		//motor2.set(value);
		//motor3.set(value);
		//motor4.set(value);
		
		motor1a.set(value);
		motor1b.set(value);
	}
	
	/**
	 * Used for the test mode smartdashboard display
	 * @return the LiveWindowSendable instances of motor1
	 */
	public LiveWindowSendable climbLWS(){
		return (LiveWindowSendable) motor1;
	}
	
	/**
	 * Used for the test mode smartdashboard display
	 * @return the LiveWindowSendable instances of motor2
	 */
	public LiveWindowSendable climb2LWS(){
		return (LiveWindowSendable) motor2;
	}
	
	/**
	 * Used for getting the current of climber motor A
	 * @return the current current (ha) of climber motor A
	 */
	public double getClimberCurrentA(){
		return Robot.getPDP().getCurrent(RobotMap.P_CLIMBER_A);
	}
	
	/**
	 * Used for getting the current of climber motor B
	 * @return the current current (ha) of climber motor B
	 */
	public double getClimberCurrentB(){
		return Robot.getPDP().getCurrent(RobotMap.P_CLIMBER_B);
	}
	
	@Override
	/**
	 * Initializes the default command
	 */
	protected void initDefaultCommand() {
		setDefaultCommand(new ClimberDefault());
	}
}
