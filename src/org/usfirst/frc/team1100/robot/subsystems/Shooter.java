package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

public class Shooter extends PIDSubsystem {
	private static Shooter shooter;
	
	public static final double SHOOT_OUT_SPEED = 1;
	public static final double SHOOT_REVERSE_SPEED = -0.5; // In case something happens
	
	private SpeedController flywheel;
	private Encoder flyShaftEncoder = new Encoder(RobotMap.S_ENCODER,RobotMap.S_ENCODER);
	
	public static Shooter getInstance() {
		if(shooter == null) {
			shooter = new Shooter();
		}
		return shooter;
	}
	
	public Shooter() {
		super("Shooter",0.0,0.0,0.0); //TODO: Figure out constants later
		flywheel = new Talon(RobotMap.S_FLYWHEEL);
		}
	
	/**
	 * Gets the flywheel LiveWindowSendable
	 * @return the flywheel LiveWindowSendable
	 */
	public LiveWindowSendable getFlywheelLWS() {
		return (LiveWindowSendable) flywheel;
	}
	
	public void setFlywheelSpeed(double speed) {
		flywheel.set(speed);
	}
	
	public void stopFlywheel() {
		flywheel.set(0);
	}

	@Override
	protected double returnPIDInput() {
		
		return flyShaftEncoder.getRate();
	}

	@Override
	protected void usePIDOutput(double output) {
		flywheel.set(output);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
