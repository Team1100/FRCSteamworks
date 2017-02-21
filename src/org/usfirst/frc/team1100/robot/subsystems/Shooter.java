package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.shooter.ShooterDefault;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
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



public class Shooter extends Subsystem {

	public static final double SHOOT_SPEED = 40;
	
	private static Shooter shooter;

	private CANTalon flywheel;
	private CANTalon flywheel2;
	private Encoder enc;
	
	private boolean on;
	
	public boolean getOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public static Shooter getInstance() {
		if(shooter == null) {
			shooter = new Shooter();
		}
		return shooter;
	}
	
	public Shooter() {
		on = false;
		flywheel = new CANTalon(RobotMap.S_FLYWHEEL);
		flywheel2 = new CANTalon(RobotMap.S_FLYWHEEL_2);
		
		flywheel.setInverted(true);
		
		enc = new Encoder(RobotMap.S_ENCODER_A,RobotMap.S_ENCODER_B);
		enc.reset();
		
		SmartDashboard.putNumber("TargetShooterSpeed", 0);
		SmartDashboard.putNumber("ActualShooterSpeed", 0);
		SmartDashboard.putNumber("EncoderVal", 0);

	}
	
	/**
	 * Gets the flywheel LiveWindowSendable
	 * @return the flywheel LiveWindowSendable
	 */
	public LiveWindowSendable getFlywheelLWS() {
		return (LiveWindowSendable) flywheel;
	}
	
	public LiveWindowSendable getFlywheel2LWS(){
		return (LiveWindowSendable) flywheel2;
	}
	
	/**
	 * Sets the flywheel speed using a PID loop (hopefully)
	 * @param speed the speed between -1 and 1 to set the motors to
	 */
	public void setFlywheelSpeed(double speed) {
		flywheel.set(speed);
		flywheel2.set(speed);
	}
	
	/**
	 * Makes the flywheel stop spinning.
	 * Sets the flywheel speed to 0, stopping it

	 */
	public void stopFlywheel() {
		flywheel.set(0);
		flywheel2.set(0);
	}
	
	public void setSpeedToTarget(){
		if (getSpeed()>=SHOOT_SPEED){
			setFlywheelSpeed(0);
		}else{
			setFlywheelSpeed(1);
		}
	}
	
	public double getSpeed(){
		SmartDashboard.putNumber("ActualShooterSpeed", -enc.getRate()/2048);
		SmartDashboard.putNumber("Encoder Val", enc.get());
		return -enc.getRate()/2048;
	}
	
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ShooterDefault());
	}
}
