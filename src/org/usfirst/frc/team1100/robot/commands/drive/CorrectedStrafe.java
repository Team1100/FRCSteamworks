package org.usfirst.frc.team1100.robot.commands.drive;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author MediocreSteak2
 * 
 */
public class CorrectedStrafe extends Command{

	private double timeout;
	private double speed;
	private double angle;
	
	/**
	 * This command allows you to strafe with correction for rotation errors.
	 * @param speed
	 * @param timeout
	 */
	public CorrectedStrafe (double speed, double timeout){
		requires(Drive.getInstance());
		this.timeout = timeout;
		this.speed = speed;
	}
	
	/**
	 * Resets the gyro and handles the timeout. Initializes the angle instance field.
	 */
	@Override
	public void initialize(){
		Drive.getInstance().resetGyro();
		setTimeout(this.timeout);
		this.angle = Drive.getInstance().getAngleAverage();
	}
	
	/**
	 * Initializes y and z to 0, and x to speed. Checks if angle is above the average gyro reading + 5 or below the reading -5
	 * and then sets z to .1 or -.1 respectively. Calls driveAbsoluteMecanum(x,y,z)
	 */
	@Override
	public void execute(){
		double y = 0;
		double x = speed;
		double z = 0;
		if(this.angle> Drive.getInstance().getAngleAverage()+5){
			z = .1;
		}else if(this.angle<Drive.getInstance().getAngleAverage()-5){
			z = -.1;
		}
		Drive.getInstance().driveAbsoluteMecanum(x, y, z);
	}
	/**
	 * Returns isTimedOut() only.
	 */
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
	
	/**
	 * Calls driveMecanum(0,0,0)
	 */
	@Override
	public void end(){
		Drive.getInstance().driveMecanum(0, 0, 0);
	}
	/**
	 * end()
	 */
	@Override
	public void interrupted(){
		end();
	}

}
