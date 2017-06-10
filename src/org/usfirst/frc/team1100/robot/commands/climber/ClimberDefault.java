package org.usfirst.frc.team1100.robot.commands.climber;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.input.XboxController;
import org.usfirst.frc.team1100.robot.subsystems.Climber;
import org.usfirst.frc.team1100.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberDefault extends Command{

	/**
	 * Initializes the command. This is where all the requires statements go.
	 * This command requires the climber and the intake subsystems.
	 */
	public ClimberDefault(){
		requires(Climber.getInstance());	
		requires(Intake.getInstance()); //TODO: I'm fairly certain that the intake is not needed here, but I don't want to risk in at battlecry. Look into.
		// Change javadoc above if you do remove this.
	}
	
	/**
	 * Execute the command many times per second. This is where all the logic goes.
	 */
	public void execute(){
		double speed = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYRight); // Get the value of the y axis of the right joystick on the xbox controller.
		if(speed > 0) { // Prevent the climber from going backwards against the rachet.
			speed = 0;
		}
		Climber.getInstance().setSpeed(speed); // Set the speed of the climber to whatever the joystick is at (assuming it was going in the right direction).
	}
	
	@Override
	/**
	 * Return if the command is finished or not. Because it is a default command, we never want it to be finished, thus the return false.
	 * @return false, because it is a default command that should never be finished
	 */
	protected boolean isFinished() {
		return false;
	}
}
