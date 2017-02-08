package org.usfirst.frc.team1100.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {
	
	private static Pneumatics p;

	private DoubleSolenoid[] firers;
	
	public Pneumatics() {
		for(int i = 0; i < 4; i++) {
			firers[i] = new DoubleSolenoid(i,i+4); //Efficiency mofongos
		}
		p = new Pneumatics();
	}
	
	public static synchronized Pneumatics getInstance() {
		return p;
	}
	
	public DoubleSolenoid[] getFireers() {
		return firers;
	}
	
	@Override
	protected void initDefaultCommand() {
		
		//This is also known as the Wire Management Memorial Subsystem. Rest in peace.
		
	}

	
	
}
