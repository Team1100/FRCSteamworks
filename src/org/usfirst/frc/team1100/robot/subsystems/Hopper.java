package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hopper extends Subsystem {
	
	private static Hopper p;

	private DoubleSolenoid[] firers;
	
	/**
	 * The constructor for Pneumatics which sets up firers 1-4 in order with 0-1+2x as the PCM spots
	 */
	public Hopper() {
		firers = new DoubleSolenoid[4];
		firers[0] = new DoubleSolenoid(RobotMap.H_PCM,RobotMap.H_FIRER_0_FORWARD,RobotMap.H_FIRER_0_REVERSE);
		firers[1] = new DoubleSolenoid(RobotMap.H_PCM,RobotMap.H_FIRER_1_FORWARD,RobotMap.H_FIRER_1_REVERSE);
		firers[2] = new DoubleSolenoid(RobotMap.H_PCM,RobotMap.H_FIRER_2_FORWARD,RobotMap.H_FIRER_2_REVERSE);
		firers[3] = new DoubleSolenoid(RobotMap.H_PCM,RobotMap.H_FIRER_3_FORWARD,RobotMap.H_FIRER_3_REVERSE);
	}
	
	public static Hopper getInstance() {
		if(p == null) {
			p = new Hopper();
		}
		return p;
	}
	
	/**
	 * Returns the array of firers
	 * @return the array of firers
	 */
	public DoubleSolenoid[] getFirers() {
		return firers;
	}
	
	@Override
	protected void initDefaultCommand() {
		
		//This is also known as the Wire Management Memorial Subsystem. Rest in peace.
		
	}

	
	
}
