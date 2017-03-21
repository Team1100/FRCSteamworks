package org.usfirst.frc.team1100.robot;

import org.usfirst.frc.team1100.robot.commands.climber.SetClimberSpeedCommand;
import org.usfirst.frc.team1100.robot.commands.drive.user.UserReverseDrive;
import org.usfirst.frc.team1100.robot.commands.drive.vision.GearTrackCommand;
import org.usfirst.frc.team1100.robot.commands.drive.vision.RotateToContour;
import org.usfirst.frc.team1100.robot.commands.gear.CloseCatcher;
import org.usfirst.frc.team1100.robot.commands.gear.OpenCatcher;
import org.usfirst.frc.team1100.robot.commands.gear2.CloseCatcher2;
import org.usfirst.frc.team1100.robot.commands.gear2.OpenCatcher2;
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
		stick = new Extreme3DPro(RobotMap.U_STICK,.2,.2);
		
		// Button assignments
		xbox.getButtonLeftStick().whileHeld(new SetClimberSpeedCommand(1));
		
		xbox.getButtonA().whenPressed(new OpenCatcher());
		xbox.getButtonB().whenPressed(new CloseCatcher());
		
		xbox.getButtonX().whenPressed(new OpenCatcher2());
		xbox.getButtonY().whenPressed(new CloseCatcher2());
		
		xbox.getButtonLeftBumper().whenPressed(new ToggleIntakeCommand(1));
		xbox.getButtonRightBumper().whenPressed(new ToggleShooterCommand());
		
		//Right trigger runs hopper sequence- see SequenceFromTriggrer() 
		//Left trigger reverses intake- see RollOutFromTrigger()
		
		stick.getButton(1).whenPressed(new UserReverseDrive());
		stick.getButton(11).whenPressed(new UserReverseDrive());
		
		stick.getButton(2).whenPressed(new GearTrackCommand());
		stick.getButton(3).whenPressed(new RotateToContour());
		
		@Deprecated // its been longer than 5 seconds
		int floorPi = (int)Math.floor(Math.E) + 1; 
		
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
