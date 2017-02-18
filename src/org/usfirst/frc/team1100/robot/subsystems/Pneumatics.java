package org.usfirst.frc.team1100.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {
	
	private static Pneumatics p;

	private DoubleSolenoid[] firers;
	
	/**
	 * The constructor for Pneumatics which sets up firers 1-4 in order with 0-1+2x as the PCM spots
	 */
	public Pneumatics() {
		firers = new DoubleSolenoid[4];
		firers[0] = new DoubleSolenoid(0,1);
		firers[1] = new DoubleSolenoid(2,3);
		firers[2] = new DoubleSolenoid(4,5);
		firers[3] = new DoubleSolenoid(6,7);
	}
	
	public static Pneumatics getInstance() {
		if(p == null) {
			p = new Pneumatics();
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
