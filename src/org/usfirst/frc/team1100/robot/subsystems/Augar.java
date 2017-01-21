package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

public class Augar extends Subsystem {
	
	private static Augar augar;
	
	public static final double AUGAR_OUT_SPEED = 1;
	public static final double AUGAR_UNJAM_SPEED = -0.7;
	
	private SpeedController augarMotor;
	
	public static Augar getInstance() {
		if(augar != null) {
			augar = new Augar();
		}
		return augar;
	}
	
	public Augar() {
		augarMotor = new Talon(RobotMap.A_AUGAR);
	}
	
    public void initDefaultCommand() {
        //Nothing right now as OI handles it all
    }
    /**
	 * Gets the augar LiveWindowSendable
	 * @return the augar LiveWindowSendable
	 */
    public LiveWindowSendable getAugarLWS() {
		return (LiveWindowSendable) augar;
	}
    
    public void setAugarSpeed(double speed) {
    	augarMotor.set(speed);
    }
    
    public void stopAugar() {
    	augarMotor.set(0);
    }
}

