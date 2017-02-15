package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

public class Shooter extends PIDSubsystem {
	private static Shooter shooter;
	
	public static final double SHOOT_OUT_SPEED = 1;
	public static final double SHOOT_REVERSE_SPEED = -0.5; // In case something happens
	
	
	public static double P = 1.0;
	public static double I = 1.0; //Integrate by parts
	public static double D = 1.0;
	
	private CANTalon flyWheel;
	
	private Encoder encoder;
	
	public static Shooter getInstance() {
		if(shooter == null) {
			shooter = new Shooter();
		}
		return shooter;
	}
	
	public Shooter() {
		super("Shooter",P,I,D); //TODO: Figure out constants later
		flyWheel = new CANTalon(RobotMap.S_FLYWHEEL);
		encoder = new Encoder(RobotMap.S_ENCODER_A,RobotMap.S_ENCODER_B);
		encoder.setMaxPeriod(0.1);
		encoder.setMinRate(10);
		encoder.setDistancePerPulse(5);
		encoder.setReverseDirection(true);
		encoder.setSamplesToAverage(7);
		flyWheel.setPID(P, I, D);
	}
	
	/**
	 * Gets the flywheel LiveWindowSendable
	 * @return the flywheel LiveWindowSendable
	 */
	public LiveWindowSendable getFlywheelLWS() {
		return (LiveWindowSendable) flyWheel;
	}
	
	public void setFlywheelSpeed(double speed) {
		flyWheel.setSetpoint(speed);
	}
	
	public void stopFlywheel() {
		flyWheel.set(0);
	}

	@Override
	protected double returnPIDInput() {
		return encoder.getRate();
	}

	@Override
	protected void usePIDOutput(double output) {
		flyWheel.setSetpoint(output);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
