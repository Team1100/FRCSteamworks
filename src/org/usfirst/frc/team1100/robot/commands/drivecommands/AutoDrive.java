package org.usfirst.frc.team1100.robot.commands.drivecommands;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.command.Command;
// ^
public class AutoDrive extends Command {
	
	private double x;
	private double y;
	private double rotation;
	
	public AutoDrive(double x, double y, double rotation, double time) {
		requires(Drive.getInstance()); //Gotta go fast
		setTimeout(time);
		this.x = x;
		this.y = y;
		this.rotation = rotation;
	}
	
	public void initialize() {
		
	}
	
	public void execute() {
		Drive.getInstance().driveMecanum(x,y,rotation); //Move
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