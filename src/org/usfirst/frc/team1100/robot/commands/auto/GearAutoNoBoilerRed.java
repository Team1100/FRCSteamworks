package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.AutoDrive;
import org.usfirst.frc.team1100.robot.commands.drive.RotateCommand;
import org.usfirst.frc.team1100.robot.commands.drive.vision.CenterContoursCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearAutoNoBoilerRed extends CommandGroup {

	public GearAutoNoBoilerRed() {
		
		addSequential(new AutoDrive(0,-AutoMap.AIRSHIP_DRIVE_POWER,0,AutoMap.AIRSHIP_DRIVE_TIMEOUT));
		
	  //addParallel(new SetProperTextEditorCommand(GoodEditors.VIM));
		
		addSequential(new RotateCommand(AutoMap.FACE_AIRSHIP_ANGLE));
		
		addSequential(new CenterContoursCommand(AutoMap.CONTOUR_TIMEOUT));
		//Move in
		addSequential(new AutoDrive(-AutoMap.GEAR_STRAFE_POWER,0,0,AutoMap.GEAR_STRAFE_TIMEOUT));
		//Wait for gear to be removed
		addSequential(new WaitCommand(AutoMap.GEAR_RETRIEVAL_DELAY));
		//Strafe out
		addSequential(new AutoDrive(AutoMap.GEAR_STRAFE_POWER,0,0,AutoMap.GEAR_STRAFE_TIMEOUT));
		
		addSequential(new AutoDrive(0,-AutoMap.LINE_CHARGE_POWER,0,AutoMap.LINE_CHARGE_TIMEOUT));
		
		
	}
	
}
