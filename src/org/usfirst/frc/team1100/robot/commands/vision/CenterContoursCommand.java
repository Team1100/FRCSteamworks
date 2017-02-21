package org.usfirst.frc.team1100.robot.commands.vision;

import java.util.ArrayList;

import org.usfirst.frc.team1100.robot.deprecated.Gyro;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.vision.Vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CenterContoursCommand extends Command {

	private boolean finished;
	
	public CenterContoursCommand(double time) {
		requires(Vision.getInstance());
		requires(Drive.getInstance());
		//requires(Gyro.getInstance());
		setTimeout(time);
		finished = false;
	}
	
	public void execute() {
		System.err.println("Centering contours!!!");
		ArrayList<double[]> conts = Vision.getInstance().requestContours();
		double centerX = 0;
		for(double[] d : conts) {	
			centerX+=d[1];
		}
		centerX/=conts.size(); /*average of the x values (d[1]) from the contour*/
		double trueCenterX = 320;
		double difference = centerX - trueCenterX;
		
		
		//SmartDashboard.putNumber("Difference", difference);
		//SmartDashboard.putNumber("CenterX", centerX);
		
		/*
		if(difference > 0) {
			Drive.getInstance().driveMecanum(0, 0.3, 0);
		} else {
			Drive.getInstance().driveMecanum(0, -0.3, 0);
		}
		*/
		
		
		
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
    	//SmartDashboard.putNumber("Power", power);
    	
		/*
		if(Math.abs(centerX - trueCenterX) < Vision.getInstance().ACCEPTABLE_ERROR) {
			finished = true;
			return;
		}
		difference = centerX - trueCenterX;
		System.err.println("Diff: " + difference + " centerX: " + centerX + " trueCenterX: " + trueCenterX);
		difference /= 320.0;*/
		//Drive.getInstance().driveMecanum(0, difference, 0); /*parameters: x,y,rotation*/
		//new RotateCommand(centerX - trueCenterX);
		if(Math.abs(difference) < Vision.ACCEPTABLE_ERROR) {
			Drive.getInstance().driveMecanum(FORWARD_SPEED, 0, COUNTER_ROTATION);
			//finished = true;
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
		return finished || isTimedOut();
	}

}
