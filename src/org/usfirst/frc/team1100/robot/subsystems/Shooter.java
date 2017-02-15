package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
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
	
	/**
	 * Sets the flywheel speed using a PID loop (hopefully)
	 * @param speed the speed between -1 and 1 to set the motors to
	 */
	public void setFlywheelSpeed(double speed) {
		flyWheel.setSetpoint(speed);
	}
	
	/**
	 * Sets the flywheel speed to 0, stopping it
	 */
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
