package org.usfirst.frc.team1100.robot.commands.drive;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

public class CorrectedStrafe extends Command{

	private double timeout;
	private double speed;
	private double angle;
	
	/**
	 * 
	 * @param speed
	 * @param timeout
	 */
	public CorrectedStrafe (double speed, double timeout){
		requires(Drive.getInstance());
		this.timeout = timeout;
		this.speed = speed;
	}
	
	@Override
	public void initialize(){
		setTimeout(this.timeout);
		this.angle = Drive.getInstance().getAngleAverage();
	}
	
	@Override
	public void execute(){
		double y = 0;
		double x = speed;
		double z = 0;
		if(this.angle>Drive.getInstance().getAngleAverage()){
			z = .1;
		}else if(this.angle<Drive.getInstance().getAngleAverage()){
			z = -.1;
		}
		Drive.getInstance().driveAbsoluteMecanum(x, y, z);
	}
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
	
	@Override
	public void end(){
		Drive.getInstance().driveMecanum(0, 0, 0);
	}
	@Override
	public void interrupted(){
		end();
	}

}
