package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.intake.RollOutFromTrigger;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
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
	private SpeedController roller2;
	
	public static Intake getInstance() {
		if(intake == null) {
			intake = new Intake();
		}
		return intake;
	}
	
	private Intake() {
		//roller = new Victor(RobotMap.I_ROLLER);
		//roller2 = new Victor(RobotMap.I_ROLLER_2);
		
		//roller2.setInverted(true);
	}
	
	protected void initDefaultCommand() {
		//setDefaultCommand(new RollOutFromTrigger());
	}
	
	/**
	 * Gets the roller LiveWindowSendable
	 * @return the roller LiveWindowSendable
	 */
	public LiveWindowSendable getRollerLWS() {
		return (LiveWindowSendable) roller;
	}
	
	public LiveWindowSendable getRoller2LWS(){
		return (LiveWindowSendable) roller2;
	}
	
	/**
	 * Sets the rollers speed, often using the constants defined in this class as parameters
	 * @param speed a value between -1 and 1 to set the roller motor to
	 */
	public void setRollerSpeed(double speed) {
		//roller.set(speed);
		//roller2.set(speed);
	}
	
	/**
	 * Returns roller speed
	 */
	
	public double getRollerSpeed(){
		return 0/*roller.get()*/;
	}
	
	/**
	 * Sets the rollers speed to 0, stopping it
	 */
	public void stopRoller() {
		/*roller.set(0);
		roller2.set(0);*/
	}
}
