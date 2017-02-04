package org.usfirst.frc.team1100.robot.commands.vision;

import java.util.ArrayList;

import org.usfirst.frc.team1100.robot.commands.drivecommands.AutoDrive;
import org.usfirst.frc.team1100.robot.commands.drivecommands.RotateCommand;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Gyro;
import org.usfirst.frc.team1100.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

public class CenterContoursCommand extends Command {

	private boolean finished;
	
	public CenterContoursCommand(double time) {
		requires(Vision.getInstance());
		requires(Drive.getInstance());
		requires(Gyro.getInstance());
		setTimeout(time);
		finished = false;
	}
	
	public CenterContoursCommand() {
		requires(Vision.getInstance());
		requires(Drive.getInstance());
		requires(Gyro.getInstance());
		finished = false;
	}
	
	public void execute() {
		System.err.println("Centering contours!!!");
		Gyro.getInstance().resetGyro();
		ArrayList<double[]> conts = Vision.getInstance().getContours();
		double centerX = 0;
		for(double[] d : conts) {	
			centerX+=d[1];
		}
		centerX/=conts.size(); /*average of the x values (d[1]) from the contour*/
		double trueCenterX = 320;
		double difference = centerX - trueCenterX;
		final double RAMP_FACTOR = 70;
		final double SPEED_LIMIT = 0.3;
		double power = 1 - (Math.pow(Math.E, -difference/RAMP_FACTOR));
    	if(1 - (Math.pow(Math.E, -difference/RAMP_FACTOR)) > 0) {
    		power = Math.min(1 - (Math.pow(Math.E, -difference/RAMP_FACTOR)),SPEED_LIMIT);
    	} else {
    		power = Math.max(1 - (Math.pow(Math.E, -difference/RAMP_FACTOR)),-SPEED_LIMIT);
    	}
		/*
		if(Math.abs(centerX - trueCenterX) < Vision.getInstance().ACCEPTABLE_ERROR) {
			finished = true;
			return;
		}
		difference = centerX - trueCenterX;
		System.err.println("Diff: " + difference + " centerX: " + centerX + " trueCenterX: " + trueCenterX);
		difference /= 320.0;*/
		Drive.getInstance().driveMecanum(0, difference, 0); /*parameters: x,y,rotation*/
		//new RotateCommand(centerX - trueCenterX);
		if(difference < Vision.ACCEPTABLE_ERROR) {
			finished = true;
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
		return finished | isTimedOut();
	}

}
