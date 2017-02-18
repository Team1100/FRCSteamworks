package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

/**
 * The shooter subsystem which using space age technology discovered by Sir. Randal P.I.D. can change the speed of a heavily weighted array of flywheels to a precise speed,
 * comparable only to when you do a very poorly timed and executed gravity boost around a disintegrating asteroid going in the wrong direction. We can only use this on the
 * robot if we have at least as much computing power as the Saturn V guidance computer! It takes as much skill to tune as it takes to ~~ We apologise for this run away
 * tangent by the auto-javadocer 3000TM. A programmer is quoted as saying, "Yeah, it uses some numbers to make something else work better... I think. Ask [Josh] if you
 * have any questions. I have to go wait for my code to compile!"
 * @author Josh-Gordon
 *
 */
public class Shooter extends Subsystem {

	private static Shooter shooter;
	
	public static final double SHOOT_OUT_SPEED = 1;
	public static final double SHOOT_REVERSE_SPEED = -0.5; // In case something happens
	

	private CANTalon flywheel;
	private CANTalon flywheelFollower;
	
	private double P = 1.0;
	private double I = 1.0;
	private double D = 1.0;
	
	private AnalogInput flyShaftEncoder = new AnalogInput(RobotMap.S_ENCODER);
	
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
	
	/**
	 * Sets the flywheel speed using a PID loop (hopefully)
	 * @param speed the speed between -1 and 1 to set the motors to
	 */
	public void setFlywheelSpeed(double speed) {
		flywheel.setSetpoint(speed);
	}
	
	/**
<<<<<<< HEAD
	 * Makes the flywheel stop spinning.
=======
	 * Sets the flywheel speed to 0, stopping it
>>>>>>> eea4e71ed87286c369c3fc45ffd6a5953085cfc5
	 */
	public void stopFlywheel() {
		flywheel.set(0);
	}
	@Override
	protected void initDefaultCommand() {
		
	}
}
