package org.usfirst.frc.team1100.robot.commands.drive;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.command.Command;
/**
 * A command which autonomously drives the robot with the given parameters.
 * @author supremesteak1
 *
 */
public class AutoDrive extends Command {
	
	private double x;
	private double y;
	private double rotation;
	
	
	/**
	 * Constructor for AutoDrive command.
	 * @param x the speed to drive in the x direction
	 * @param y the speed to drive in the y direction
	 * @param rotation the number of degrees to drive, in degrees, NOT radians
	 * @param time the time in seconds until the command should timeout
	 */
	public AutoDrive(double x, double y, double rotation, double time) {
		requires(Drive.getInstance());
		setTimeout(time);
		this.x = x;
		this.y = y;
		this.rotation = rotation;
	}
	
	/**
	 * Called when the command is first initialized. Currently unused.
	 */
	public void initialize() {
		
	}
	
	/**
	 * Called many times a second, to update the command. Simply drives the robot with the parameters given in the constructor.
	 */
	public void execute() {
		Drive.getInstance().driveMecanum(x,y,rotation);
	}
	
	/**
	 * Called when the command is interrupted
	 */
	public void interrupted() {
		end();
	}
	
	/**
	 * Called when isFinished() returns false
	 */
	public void end() {
		Drive.getInstance().driveMecanum(0, 0, 0);
	}
	
	/**
	 * Returns if the command is finished or not
	 */
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

}