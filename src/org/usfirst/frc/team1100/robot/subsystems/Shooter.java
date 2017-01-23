package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

public class Shooter extends Subsystem {
	private static Shooter shooter;
	
	public static final double SHOOT_OUT_SPEED = 1;
	public static final double SHOOT_REVERSE_SPEED = -0.5; // In case something happens
	
	private SpeedController flywheel;
	
	public static Shooter getInstance() {
		if(shooter == null) {
			shooter = new Shooter();
		}
		return shooter;
	}
	
	public Shooter() {
		flywheel = new Talon(RobotMap.S_FLYWHEEL);
	}

	@Override
	protected void initDefaultCommand() {
		// Nothing right now as OI handles it all
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
}
