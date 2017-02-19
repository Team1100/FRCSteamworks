package org.usfirst.frc.team1100.robot.commands.shooter;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@Deprecated
public class TestEncoderCommand extends Command {

	private Talon talon;
	private Encoder encoder;
	private double targetRate;
	private final double RATE_OF_CHANGE = 0.0001;
	private double pwmSpeed;
	
	@Deprecated
	public TestEncoderCommand(int time, double targetRate) {
		setTimeout(time);
		this.targetRate = targetRate;
		talon = new Talon(RobotMap.T_TALON_PORT);
		//encoder = new Encoder(RobotMap.T_ENCODER_PORT_1,RobotMap.T_ENCODER_PORT_2);
		System.err.println("YESSES CONSTRUCTOR");
	}
	
	@Deprecated
	@Override
	protected void initialize() {
		System.err.println("Test Encoder Command started");
		encoder.setMaxPeriod(0.1);
		encoder.setMinRate(10);
		encoder.setDistancePerPulse(5);
		encoder.setReverseDirection(true);
		encoder.setSamplesToAverage(7);
		pwmSpeed = talon.getSpeed();
	}
	
	@Deprecated
	protected void execute() {
		System.err.println("Runningngngngnnnngggggg");
		double rate = encoder.getRate();
		double rateDifference = targetRate - rate;
		if(rateDifference > 0) {
			talon.set(pwmSpeed+=RATE_OF_CHANGE);
		} else if(rateDifference < 0) {
			talon.set(pwmSpeed-=RATE_OF_CHANGE);
		}
		SmartDashboard.putNumber("Revolutions per second", rate);
		SmartDashboard.putNumber("Current PWM power", talon.getSpeed());
		SmartDashboard.putNumber("Target RPS", targetRate);
		SmartDashboard.putNumber("Rate difference", rateDifference);
	}
	
	@Deprecated
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

}
