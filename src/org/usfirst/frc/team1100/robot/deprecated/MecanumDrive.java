package org.usfirst.frc.team1100.robot.deprecated;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Joshua-Gordon
 * 
 */
//@Defecated
@Deprecated
public class MecanumDrive extends Command {
	
	@Deprecated
	public MecanumDrive() {
		requires(Drive.getInstance());
	}
	
	@Deprecated
	@Override
	public void execute() {
		//Drive.getInstance().driveMecanum(OI.getInstance().getLeftStick().getX(), OI.getInstance().getLeftStick().getY(),OI.getInstance().getRightStick().getY());
	}
	
	@Deprecated
	@Override
	protected boolean isFinished() {
		return false;
	}

}
