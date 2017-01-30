package org.usfirst.frc.team1100.robot;

import org.usfirst.frc.team1100.robot.commands.ResetGyroCommand;
import org.usfirst.frc.team1100.robot.commands.auger.SetAugerSpeedCommand;
import org.usfirst.frc.team1100.robot.commands.drivecommands.RotateCommand;
import org.usfirst.frc.team1100.robot.commands.intake.SetIntakeSpeedCommand;
import org.usfirst.frc.team1100.robot.commands.shooter.SetShooterSpeedCommand;
import org.usfirst.frc.team1100.robot.input.AttackThree;
import org.usfirst.frc.team1100.robot.input.XboxController;
import org.usfirst.frc.team1100.robot.subsystems.Auger;
import org.usfirst.frc.team1100.robot.subsystems.Intake;
import org.usfirst.frc.team1100.robot.subsystems.Shooter;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
private static OI instance;
	
	// These are the two joysticks we have on the driver station; the left one and the right one.
	private AttackThree leftStick;
	private AttackThree rightStick;
	private XboxController xbox;
	
	public static OI getInstance() {
		if(instance == null) {
			instance = new OI();
		}
		return instance;
	}
	
	private OI() {
		leftStick = new AttackThree(RobotMap.U_LEFT, 0.1);
		rightStick = new AttackThree(RobotMap.U_RIGHT, 0.05);
		xbox = new XboxController(RobotMap.U_XBOX, 0.1);
		
		// Now the assignments
		xbox.getButtonA().whileHeld(new SetIntakeSpeedCommand(Intake.ROLL_IN_SPEED)); // Roll in the fuel while the A button is pressed
		xbox.getButtonB().whileHeld(new SetIntakeSpeedCommand(Intake.ROLL_OUT_SPEED)); // Roll out the fuel while the B button is pressed
		xbox.getButtonX().whileHeld(new SetShooterSpeedCommand(Shooter.SHOOT_OUT_SPEED)); // Spin up the flywheel to shoot fuel while the X button is pressed
		xbox.getButtonY().whileHeld(new SetAugerSpeedCommand(Auger.AUGER_OUT_SPEED)); // Spin the Auger outwards to dispense fuel while the Y button is pressed
		
		//rightStick.getButton(3).whenPressed(new ResetGyroCommand());
		//rightStick.getButton(4).whenPressed(new RotateCommand(90));
	}
	
	/**
	 * Returns the Left Joystick.
	 * @return the Left AttackThree
	 */
	public AttackThree getLeftStick() {
		return leftStick;
	}

	/**
	 * Returns the Right Joystick
	 * @return the Right AttackThree
	 */
	public AttackThree getRightStick() {
		return rightStick;
	}
	
	/**
	 * Returns the Xbox Controller
	 * @return the Xbox Controller
	 */
	public XboxController getXbox() {
		return xbox;
	}
	
}
