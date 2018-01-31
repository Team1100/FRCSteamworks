package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.shooter.ShooterDefault;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The shooter subsystem which using space age technology discovered by Sir. Randal P.I.D. can change the speed of a heavily weighted array of flywheels to a precise speed,
 * comparable only to when you do a very poorly timed and executed gravity boost around a disintegrating asteroid going in the wrong direction. We can only use this on the
 * robot if we have at least as much computing power as the Saturn V guidance computer! It takes as much skill to tune as it takes to ~~ We apologise for this run away
 * tangent by the auto-javadocer 3000TM. A programmer is quoted as saying, "Yeah, it uses some numbers to make something else work better... I think. Ask [Josh] if you
 * have any questions. I have to go wait for my code to compile!"
 * @author Josh-Gordon
 *
 */


// TODO: This subsytem is an absolute mess. What in the world are TEMP1 and TEMP2??? Please, Please, PLEASE fix ASAP!!!
/*
 * A special message to whoever it was who modified this subsystem:
 * This used to be a nice class with organization and fully functional methods.
 * You ruined it. Much like the fall of Rome, this class has fallen from a great entity, to a very feeble and soon to die entity.
 * You are not to ever commit such a monstrosity again, or I will be very, very, very mad. 
 */
public class Shooter extends Subsystem {

	public static final double CURRENT_THRESHOLD = 70; // This is the limit of current before the subsystem shuts itself off (citation needed)
	
	public static final double SHOOT_SPEED = 40;
	public static final double MIN_SPEED = 37; //TODO temp irrelevant
	public static final double CONVEYOR_POWER = .6;
	private static final double FEEDER_POWER = .3; //TODO -.4;
	
	private static Shooter shooter;

	private Victor flywheel;
	private WPI_TalonSRX feeder;//, TEMP1, TEMP2;
	private WPI_TalonSRX conveyor; // Was a Victor, but then it was changed to CAN, so thus a CANTalon
	private Encoder enc;
	
	private boolean on;
	
	/**
	 * Returns the static instance of the shooter subsystem. If none exists, one is created.
	 * @return the static instance of the shooter subsystem
	 */
	public static Shooter getInstance() {
		if(shooter == null) {
			shooter = new Shooter();
		}
		return shooter;
	}
	
	/**
	 * Initializes the shooter subsystem.
	 */
	public Shooter() {
		on = false; // Sets the shooter to off as we don't want it starting up as soon as the robot is enabled
		flywheel = new Victor(RobotMap.S_FLYWHEEL); // Initialize all the various motors that are needed
		feeder = new WPI_TalonSRX(RobotMap.S_FEEDER);
		conveyor = new WPI_TalonSRX(RobotMap.S_CONVEYOR);
		// I think the temp motors were artifacts from the previous shooter. They are NOT needed. I'm leaving them here just in case we need to do a sudden rollback
		//TEMP1 = new CANTalon(4); // I don't know what these are. They should probably be removed, or at least investigated.
		//TEMP2 = new CANTalon(5); // TODO: What are these things?
		
		enc = new Encoder(RobotMap.S_ENCODER_A,RobotMap.S_ENCODER_B); // Initializes the encode. The encoder needs two different DIO pins because of some weird dual signal thing.
		enc.reset(); // Reset the encoder to ensure that it is starting at 0
		
		// Put some values on the smartdashboard for debugging
		SmartDashboard.putNumber("TargetShooterSpeed", 0);
		SmartDashboard.putNumber("ActualShooterSpeed", 0);
		SmartDashboard.putNumber("EncoderVal", 0);
	}
	
	/**
	 * Returns if the shooter is on or not
	 * @return if the shooter is on
	 */
	public boolean getOn() {
		return on;
	}

	/**
	 * Sets the state of the shooter, on or off
	 * @param on a boolean representing the desired state of the shooter
	 */
	public void setOn(boolean on) {
		this.on = on;
	}
	
	/**
	 * Gets the flywheel LiveWindowSendable
	 * @return the flywheel LiveWindowSendable
	 */
	public LiveWindowSendable getFlywheelLWS() {
		return (LiveWindowSendable) flywheel;
	}
	
	/**
	 * Gets the feeder LiveWindowSendable
	 * @return the feeder LiveWindowSendable
	 */
	public LiveWindowSendable getFeederLWS() {
		return (LiveWindowSendable) feeder;
	}
	
	/**
	 * Gets the conveyer LiveWindowSendable
	 * @return the conveyer LiveWindowSendable
	 */
	public LiveWindowSendable getConveyorLWS() {
		return (LiveWindowSendable) conveyor;
	}
	
	/**
	 * Returns the current current of shooter motor A
	 * @return the current current of shooter motor A
	 */
	public double getShooterCurrentA() {
		return Robot.getPDP().getCurrent(RobotMap.P_SHOOTER_A);
	}
	
	/**
	 * Returns the current current of shooter motor B
	 * @return the current current of shooter motor B
	 */
	public double getShooterCurrentB() { // TODO: This is likely going to go as we only have one shooter motor now
		return Robot.getPDP().getCurrent(RobotMap.P_SHOOTER_B);
	}
	
	/**
	 * Returns the current average current of both shooter motors
	 * @return the current average current of both shooter motors
	 */
	public double getAverageCurrent() {
		return (getShooterCurrentA()+getShooterCurrentB())/2;
	}
	
	/**
	 * Sets the flywheel speed
	 * @param speed the speed between -1 and 1 to set the motors to
	 */
	private void setFlywheelSpeed(double speed) {
		flywheel.set(speed);
		//TEMP1.set(1); // Why do these keep coming back up???
		//TEMP2.set(1); // TODO
	}
	
	/**
	 * Runs the conveyer at a predetermined speed
	 */
	public void runConveyor() {
		conveyor.set(CONVEYOR_POWER);
		//TEMP1.set(1); // Okay, seriously. Get these out of here!
		//TEMP2.set(1); // TODO
		
	}
	
	/**
	 * Stops the conveyer
	 */
	public void stopConveyor() { // TODO: Conveyor is actually spelled "Conveyer"
		conveyor.set(Math.acos(Math.PI/Math.ceil(Math.E))); //Trig shot
		//TEMP1.set(0); // Really, I'm beginning to get mad.
		//TEMP2.set(0); // TODO
	}
	
	/**
	 * Makes the flywheel and conveyer stop spinning.
	 * Sets the flywheel and conveyer speed to 0, stopping them
	 */
	public void stopShooter() {
		setFlywheelSpeed(0);
		stopConveyor();
		//TEMP1.set(0); // Whoever made this didn't even use the correct naming conventions. DON'T USE ALL CAPS IF IT'S NOT A FINAL VARIABLE!!!
		//TEMP2.set(0); // TODO
	}
	
	/**
	 * Sets the shooter speed to a target speed using a bang-bang controller
	 */
	public void setSpeedToTarget() {
		if (getSpeed()>=SHOOT_SPEED) {
			setFlywheelSpeed(0);
		} else {
			setFlywheelSpeed(1);
		}
	}
	
	/**
	 * Runs the feeder at a predetermined speed
	 */
	public void runFeeder() {
		feeder.set(FEEDER_POWER);
	}
	
	/**
	 * Makes the feeder cease all rotational motion
	 */
	public void stopFeeder() {
		feeder.set(0);
	}
	
	/**
	 * Returns the speed of the shooter in some units which I don't remember and can't figure out.
	 * Also puts the speed of the shooter and the raw encoder value on the smartdashboard for debugging.
	 * @return the speed of the shooter in some reasonable units which I don't remember
	 */
	public double getSpeed() {
		SmartDashboard.putNumber("ActualShooterSpeed", -enc.getRate()/2048);
		SmartDashboard.putNumber("Encoder Val", enc.get());
		try {
			return -enc.getRate()/2048;
		} catch (Exception e) {
			return 0;
		}
	}
	
	@Override
	/**
	 * Initializes the default command of the shooter subsystem
	 */
	protected void initDefaultCommand() {
		setDefaultCommand(new ShooterDefault());
	}
}