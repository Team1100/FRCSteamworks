package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Intake {
	private static Intake intake;
	
	private SpeedController roller;
	
	public Intake getInstance() {
		if(intake == null) {
			intake = new Intake();
		}
		return intake;
	}
	
	public Intake() {
		roller = new Talon(RobotMap.I_ROLLER);
	}
	
	protected void initDefaultCommand() {
		
	}
}
