package org.usfirst.frc.team1100.robot.commands.climber;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.input.XboxController;
import org.usfirst.frc.team1100.robot.subsystems.Climber;
import org.usfirst.frc.team1100.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberDefault extends Command{

	public ClimberDefault(){
		requires(Climber.getInstance());	
		requires(Intake.getInstance());
	}
	
	public void execute(){
		double speed = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYLeft);
		Climber.getInstance().setSpeed(speed);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
