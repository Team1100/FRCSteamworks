package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

/**
 * The Intake subsystem for the roller that intakes balls
 * @author supremesteak1
 *
 */
public class Intake extends Subsystem {
	
	private static Intake intake;
	
	public static final double ROLL_IN_SPEED = 1;
	public static final double ROLL_OUT_SPEED = -1;
	
	private SpeedController roller;
	
	public static Intake getInstance() {
		if(intake == null) {
			intake = new Intake();
		}
		return intake;
	}
	
	public Intake() {
		roller = new Talon(RobotMap.I_ROLLER);
	}
	
	protected void initDefaultCommand() {
		// Nothing right now as OI handles it all
	}
	
	/**
	 * Gets the roller LiveWindowSendable
	 * @return the roller LiveWindowSendable
	 */
	public LiveWindowSendable getRollerLWS() {
		return (LiveWindowSendable) roller;
	}
	
	/**
	 * Sets the rollers speed, often using the constants defined in this class as parameters
	 * @param speed a value between -1 and 1 to set the roller motor to
	 */
	public void setRollerSpeed(double speed) {
		roller.set(speed);
	}
	
	/**
	 * Sets the rollers speed to 0, stopping it
	 */
	public void stopRoller() {
		roller.set(0);
	}
}
