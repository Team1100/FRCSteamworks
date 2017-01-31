package org.usfirst.frc.team1100.robot.commands.vision;

import java.util.ArrayList;

import org.usfirst.frc.team1100.robot.commands.drivecommands.AutoDrive;
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
	
	public void execute() {
		Gyro.getInstance().resetGyro();
		ArrayList<double[]> conts = Vision.getInstance().getContours();
		double centerX = 0;
		for(double[] d : conts) {
			centerX+=d[1];
		}
		centerX/=conts.size();
		double trueCenterX = 640.0;
		double difference;
		
		if(Math.abs(centerX - trueCenterX) < Vision.getInstance().ACCEPTABLE_ERROR) {
			finished = true;
		}
		difference = centerX - trueCenterX;
		System.err.println("Diff: " + difference + " centerX: " + centerX + " trueCenterX: " + trueCenterX);
		if(difference > 0 ) {
			new AutoDrive(0.0, -1 + 3*(1/difference), 0.0, (long) 0.1).start();
		} else {
			new AutoDrive(0.0,  1 - 3*(1/difference), 0.0, (long) 0.1).start();
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
		return isTimedOut();
	}

}
