package org.usfirst.frc.team1100.robot.commands.vision;

import java.util.ArrayList;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

public class CenterContoursCommand extends Command {

	private boolean finished;
	
	public CenterContoursCommand() {
		finished = false;
	}
	
public void execute() {
	requires(Vision.getInstance());
	requires(Drive.getInstance());
	ArrayList<double[]> conts = Vision.getInstance().getContours();
	double centerX = 0;
	for(double[] d : conts) {
		centerX+=d[1];
	}
	centerX/=conts.size();
	double trueCenterX = 640.0;
	double difference;
	while(Math.abs(centerX - trueCenterX) > Vision.getInstance().ACCEPTABLE_ERROR) {
		difference = centerX - trueCenterX;
		//TODO: Implement rotation with actual robot
		//This will need much tweaking once the robot is built
	}
	finished = true;
}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}

}
