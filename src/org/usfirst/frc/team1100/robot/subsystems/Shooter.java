package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

public class Shooter extends Subsystem {
	private static Shooter shooter;
	
	public static final double SHOOT_OUT_SPEED = 1;
	public static final double SHOOT_REVERSE_SPEED = -0.5; // In case something happens
	
	private CANTalon flywheel;
	private CANTalon flywheelFollower;
	
	private double P = 1.0;
	private double I = 1.0;
	private double D = 1.0;
	
	private AnalogInput flyShaftEncoder = new AnalogInput(RobotMap.S_ENCODER); // Encoder(RobotMap.S_ENCODER,);
	
	public static Shooter getInstance() {
		if(shooter == null) {
			shooter = new Shooter();
		}
		return shooter;
	}
	
	public Shooter() {
		flywheel = new CANTalon(RobotMap.CAN_0);
		flywheel.setPID(P,I,D);
		
		flywheelFollower = new CANTalon(RobotMap.CAN_1);
		
		flywheel.changeControlMode(TalonControlMode.Position);
		flywheel.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		
		flywheelFollower.set(flywheel.getDeviceID());
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
	
	/**
	 * Makes the flywheel stop spinning.
	 */
	public void stopFlywheel() {
		flywheel.set(0);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
