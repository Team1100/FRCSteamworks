package org.usfirst.frc.team1100.robot;

import org.usfirst.frc.team1100.robot.commands.hopper.FireSequence;
import org.usfirst.frc.team1100.robot.commands.intake.SetIntakeSpeedCommand;
import org.usfirst.frc.team1100.robot.commands.shooter.SetShooterSpeedCommand;
import org.usfirst.frc.team1100.robot.commands.vision.CenterContoursCommand;
import org.usfirst.frc.team1100.robot.input.AttackThree;
import org.usfirst.frc.team1100.robot.input.Extreme3DPro;
import org.usfirst.frc.team1100.robot.input.XboxController;
import org.usfirst.frc.team1100.robot.subsystems.Intake;
import org.usfirst.frc.team1100.robot.subsystems.Shooter;

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
		
		// Now the assignments
		xbox.getButtonA().whileHeld(new SetIntakeSpeedCommand(Intake.ROLL_IN_SPEED)); // Roll in the fuel while the A button is pressed
		xbox.getButtonB().whileHeld(new SetIntakeSpeedCommand(Intake.ROLL_OUT_SPEED)); // Roll out the fuel while the B button is pressed
		xbox.getButtonX().whileHeld(new SetShooterSpeedCommand(Shooter.SHOOT_OUT_SPEED,1)); // Spin up the flywheel to shoot fuel while the X button is pressed
		//xbox.getButtonY().whenPressed(new TestEncoderCommand(100, 2*Math.PI));
		
		//rightStick.getButton(3).whenPressed(new ResetGyroCommand());
		//rightStick.getButton(5).whenPressed(new RotateCommand(90));
		stick.getButton(6).whenPressed(new CenterContoursCommand(20));
		stick.getButton(4).whenPressed(new FireSequence());
		//rightStick.getButton(5).whenPressed(new ShooterSpeedPID(2048*5*2));
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
