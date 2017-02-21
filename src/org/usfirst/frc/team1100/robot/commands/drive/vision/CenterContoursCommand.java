package org.usfirst.frc.team1100.robot.commands.drive.vision;

import java.util.ArrayList;

import org.usfirst.frc.team1100.robot.deprecated.Gyro;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.vision.Vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CenterContoursCommand extends Command {

	private boolean finished;
	private boolean noContours;
	
	public CenterContoursCommand(double time) {
		requires(Vision.getInstance());
		requires(Drive.getInstance());
		//requires(Gyro.getInstance());
		setTimeout(time);
		finished = false;
		noContours = false;
	}
	
	public void execute() {
		System.err.println("Centering contours!!!");
		try{
			ArrayList<double[]> conts = Vision.getInstance().requestContours();
			double centerX = 0;
			for(double[] d : conts) {	
				centerX+=d[1];
			}
			centerX/=conts.size(); /*average of the x values (d[1]) from the contour*/
			double trueCenterX = 320;
			double difference = centerX - trueCenterX;
			
			final double RAMP_FACTOR = 300;
			final double SPEED_LIMIT = 0.20;
			final double SPEED_MINIMUM = 0.1;
			final double BRUTE_FORCE_DIVIDE = 2.5;
			final double FORWARD_SPEED = -0.3;
			final double COUNTER_ROTATION = 0.05;
			double power = 0;
			
	    	if(1 - (Math.pow(Math.E, difference/RAMP_FACTOR)) > 0) {
	    		power = Math.min((1 - (Math.pow(Math.E, difference/RAMP_FACTOR)))/BRUTE_FORCE_DIVIDE,SPEED_LIMIT);
	    		power = Math.max(power, SPEED_MINIMUM);
	    	} else {
	    		power = Math.max((1 - (Math.pow(Math.E, difference/RAMP_FACTOR)))/BRUTE_FORCE_DIVIDE,-SPEED_LIMIT);
	    		power = Math.min(power, -SPEED_MINIMUM);
	    	}
	    	Drive.getInstance().driveMecanum(0, power, 0);
	    	
			if(Math.abs(difference) < Vision.ACCEPTABLE_ERROR) {
				Drive.getInstance().driveMecanum(FORWARD_SPEED, 0, COUNTER_ROTATION);
				//finished = true;
			}
		}catch(Exception fightMe){
			System.err.println("THE CENTERING DIED AND THERE PROBABLY ARENT CONTOURS WTF GUYS");
			noContours = true;
		}
		
	}
	
	public void interrupted() {
		end();
	}
	
	public void end() {
		Drive.getInstance().driveMecanum(0, 0, 0);
	}
	
	@Override
	protected boolean isFinished() {
		return finished || isTimedOut()||noContours;
	}

}
