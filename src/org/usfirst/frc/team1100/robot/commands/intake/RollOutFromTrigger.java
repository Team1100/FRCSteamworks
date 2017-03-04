package org.usfirst.frc.team1100.robot.commands.intake;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.input.XboxController;
import org.usfirst.frc.team1100.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class RollOutFromTrigger extends Command{


	public RollOutFromTrigger(){
		requires(Intake.getInstance());
		
	}
	
	public void execute(){
		if(OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kLeftTrigger)!=0){
			Intake.getInstance().setRollerSpeed(-1);
		}else Intake.getInstance().setRollerSpeed(0);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
