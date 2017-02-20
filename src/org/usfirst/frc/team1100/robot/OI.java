package org.usfirst.frc.team1100.robot;

import org.usfirst.frc.team1100.robot.commands.climber.SetClimberSpeedCommand;
import org.usfirst.frc.team1100.robot.commands.gear.ToggleCatcher;
import org.usfirst.frc.team1100.robot.commands.hopper.CloseAll;
import org.usfirst.frc.team1100.robot.commands.hopper.FireSequence;
import org.usfirst.frc.team1100.robot.commands.hopper.OpenThenClose;
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
		
		stick.getButton(9).whenPressed(new ToggleShooterCommand(.5));
		
		stick.getButton(2).whileHeld(new SetIntakeSpeedCommand(1));
		
		stick.getButton(7).whileHeld(new SetClimberSpeedCommand(1));
		stick.getButton(8).whileHeld(new SetClimberSpeedCommand(-1));
		
		stick.getButton(6).whenPressed(new OpenThenClose(0));
		stick.getButton(4).whenPressed(new OpenThenClose(1));
		stick.getButton(3).whenPressed(new OpenThenClose(2));
		stick.getButton(5).whenPressed(new OpenThenClose(3));
		
		stick.getButton(1).whileHeld(new FireSequence());
		stick.getButton(1).whenReleased(new CloseAll());
		
		xbox.getButtonLeftStick().whileHeld(new SetClimberSpeedCommand(1));
		xbox.getButtonA().whenPressed(new ToggleCatcher());
		xbox.getButtonLeftBumper().whenPressed(new ToggleIntakeCommand(1));
		//HOLD DOWN OUTAKE- left trigger
		xbox.getButtonRightBumper().whenPressed(new ToggleShooterCommand(.6));
		//RIGHT TRIGGER - piston sequence
		// Now the assignments
		
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
