package org.usfirst.frc.team1100.robot.commands.drivecommands;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.command.Command;
// ^
public class AutoDrive extends Command {
	public boolean finished; //This is the sentinel of the abstract loop this command runs in
	public long startTime; //This is the time the command starts to run. Approximately.
	
	public AutoDrive() {
		requires(Drive.getInstance()); //Gotta go fast
	}
	
	public void initialize() {
		finished = false; //Initialize variable
		startTime = System.currentTimeMillis(); //At this point, the time starts
	}
	
	public void execute(double x, double y, double rotation, long time) {
		
		Drive.getInstance().driveMecanum(x,y,rotation); //Move
		if(System.currentTimeMillis() - startTime > time) { //Check if enough time has passed
			finished = true; //If so, stop
		}
		
	}
	
	public void interrupted() {
		end();
	}
	
	@Override
	protected boolean isFinished() {
		
		return finished;
	}

}