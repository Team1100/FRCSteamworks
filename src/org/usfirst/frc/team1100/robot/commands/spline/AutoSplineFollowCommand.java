package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

public class AutoSplineFollowCommand extends Command {
	
	BezierCurve bc;
	long startTime, currentTime, totalTime;
	boolean finished;
	
	public AutoSplineFollowCommand(BezierCurve bc, long time) {
		// Should require drive subsystem
		//requires(Drive.getInstance());
		this.bc = bc;
		totalTime = time;
		finished = false;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double param = (currentTime-startTime)/totalTime;
		if(param>=1) {
			finished = true;
		}
		Vector tangent = bc.getDerivativeAtPoint(param);
		Vector bearing = Vector.getNormalizedVectorFromAngle(0/*Get gyro reading here*/);
		double ratio = Vector.normalizedDotProduct(tangent, bearing);
		if(Vector.isClockwise(bearing, tangent)) {
			//Left wheel is speed, right wheel is speed * ratio
		} else {
			//Right wheel is speed, left wheel is speed * ratio
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return finished;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
	

}

