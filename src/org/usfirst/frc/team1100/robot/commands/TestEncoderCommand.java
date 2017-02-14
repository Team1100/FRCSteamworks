package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestEncoderCommand extends Command {

	private Talon talon;
	private Encoder encoder;
	private double targetRate;
	private final double RATE_OF_CHANGE = 0.0001;
	private double pwmSpeed;
	
	public TestEncoderCommand(int time, double targetRate) {
		setTimeout(time);
		this.targetRate = targetRate;
	}
	
	@Override
	protected void initialize() {
		System.err.println("Test Encoder Command started");
		talon = new Talon(RobotMap.T_TALON_PORT);
		encoder = new Encoder(RobotMap.T_ENCODER_PORT_1,RobotMap.T_ENCODER_PORT_2);
		encoder.setDistancePerPulse(Math.PI/1024); // Setting the distance per pulse in units of radians per pulse (2*PI per 2048 pulses)
		pwmSpeed = talon.getSpeed();
	}
	
	protected void execute() {
		System.err.println("OH THANK GOODNESS!!!");
		double rate = encoder.getRate(); // Get the radians per second it's moving at
		double rateDifference = targetRate - rate;
		if(rateDifference > 0) {
			talon.set(pwmSpeed+=RATE_OF_CHANGE);
		} else if(rateDifference < 0) {
			talon.set(pwmSpeed-=RATE_OF_CHANGE);
		}
		SmartDashboard.putNumber("Revolutions per second", rate/2*Math.PI);
		SmartDashboard.putNumber("Current PWM power", talon.getSpeed());
		SmartDashboard.putNumber("Target RPS", targetRate/2*Math.PI);
		SmartDashboard.putNumber("Rate difference", rateDifference);
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

}
