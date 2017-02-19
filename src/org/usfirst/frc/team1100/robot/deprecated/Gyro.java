package org.usfirst.frc.team1100.robot.deprecated;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
@Deprecated
public class Gyro extends Subsystem {
	
	private static Gyro gyro;
	
	private final double DRIFT = 0.00005;//-0.0001;
	
	private AnalogGyro gyro0;
	private AnalogGyro gyro1;
	private long resetTime; // The time the gyro was last reset

	@Deprecated
	public static Gyro getInstance() {
		if(gyro == null) {
			gyro = new Gyro();
		}
		return gyro;
	}
	
	public Gyro() {
		gyro0 = new AnalogGyro(RobotMap.D_GYRO0);
		gyro1 = new AnalogGyro(RobotMap.D_GYRO1);
		//System.err.println("Test");
		resetGyro();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	/**
	 * Gets the current angle of gyro0
	 * @return the current angle of gyro0
	 */
	public double getAngle0() {
		return (gyro0.getAngle());
	}
	
	/**
	 * Gets the current angle of gyro1
	 * @return the current angle of gyro1
	 */
	public double getAngle1() {
		return (gyro1.getAngle());
	}
	
	/**
	 * Gets the current average angle of the gyros
	 * @return the current average angle of the gyros
	 */
	public double getAngleAverage() {
		return ((gyro0.getAngle()/* + gyro1.getAngle()) / 2.0*/)) + (DRIFT*(System.currentTimeMillis()-resetTime));
	}
	
	public double getGyroDriftPerMillisecond() {
		return ((gyro0.getAngle()/* + gyro1.getAngle())/2.0*/))/(System.currentTimeMillis()-resetTime);
	}
	
	/**
	 * Resets the gyro to 0
	 * Mainly useful at the beginning of the match when we are in a known position
	 */
	public void resetGyro() {
		//System.err.println("Gyro reset");
		gyro0.reset();
		gyro1.reset();
		resetTime = System.currentTimeMillis();
	}
}

