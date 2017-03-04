package org.usfirst.frc.team1100.robot;

import org.usfirst.frc.team1100.robot.commands.climber.SetClimberSpeedCommand;
import org.usfirst.frc.team1100.robot.commands.drive.CorrectedStrafe;
import org.usfirst.frc.team1100.robot.commands.drive.RotateCommand;
import org.usfirst.frc.team1100.robot.commands.drive.vision.CenterContoursCommand;
import org.usfirst.frc.team1100.robot.commands.gear.CloseCatcher;
import org.usfirst.frc.team1100.robot.commands.gear.OpenCatcher;
import org.usfirst.frc.team1100.robot.commands.gear.ToggleCatcher;
import org.usfirst.frc.team1100.robot.commands.hopper.sequences.FireSequence;
import org.usfirst.frc.team1100.robot.commands.hopper.util.CloseAll;
import org.usfirst.frc.team1100.robot.commands.hopper.util.OpenThenCloseAll;
import org.usfirst.frc.team1100.robot.commands.intake.SetIntakeSpeedCommand;
import org.usfirst.frc.team1100.robot.commands.intake.ToggleIntakeCommand;
import org.usfirst.frc.team1100.robot.commands.shooter.ToggleShooterCommand;
import org.usfirst.frc.team1100.robot.input.Extreme3DPro;
import org.usfirst.frc.team1100.robot.input.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
private static OI instance;
	
	private Extreme3DPro stick;
	private XboxController xbox;
	
	public static OI getInstance() {
		if(instance == null) {
			instance = new OI();
		}
		return instance;
	}
	
	private OI() {
		//Initialize input devices
		xbox = new XboxController(RobotMap.U_XBOX, 0.1);
		stick = new Extreme3DPro(RobotMap.U_STICK,.15,.2);
		
		// Button assignments
		xbox.getButtonLeftStick().whileHeld(new SetClimberSpeedCommand(1));
		xbox.getButtonA().whenPressed(new OpenCatcher());
		xbox.getButtonB().whenPressed(new CloseCatcher());
		xbox.getButtonLeftBumper().whenPressed(new ToggleIntakeCommand(1));
		xbox.getButtonRightBumper().whenPressed(new ToggleShooterCommand());
		//Right trigger runs hopper sequence- see SequenceFromTriggrer() 
		//Left trigger reverses intake- see RollOutFromTrigger()
		
		
	}
	
	/**
	 * Returns the Joystick
	 * @return the joystick
	 */
	
	public Extreme3DPro getStick(){
		return stick;
	}
	
	/**
	 * Returns the Xbox Controller
	 * @return the Xbox Controller
	 */
	public XboxController getXbox() {
		return xbox;
	}
	
}
