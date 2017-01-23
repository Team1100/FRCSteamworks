package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

public class Auger extends Subsystem {
	
	private static Auger auger;
	
	public static final double AUGER_OUT_SPEED = 1;
	public static final double AUGER_UNJAM_SPEED = -0.7;
	
	private SpeedController augarMotor;
	
	public static Auger getInstance() {
		if(auger == null) {
			auger = new Auger();
		}
		return auger;
	}
	
	public Auger() {
		augarMotor = new Talon(RobotMap.A_AUGER);
	}
	
    public void initDefaultCommand() {
        //Nothing right now as OI handles it all
    }
    /**
	 * Gets the augar LiveWindowSendable
	 * @return the augar LiveWindowSendable
	 */
    public LiveWindowSendable getAugarLWS() {
		return (LiveWindowSendable) augarMotor;
	}
    
    public void setAugarSpeed(double speed) {
    	augarMotor.set(speed);
    }
    
    public void stopAugar() {
    	augarMotor.set(0);
    }
}

