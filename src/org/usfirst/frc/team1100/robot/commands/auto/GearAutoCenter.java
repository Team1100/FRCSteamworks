package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.AutoDrive;
import org.usfirst.frc.team1100.robot.commands.drive.RotateCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class GearAutoCenter extends CommandGroup{
	public GearAutoCenter(){
		addSequential(new AutoDrive(0,-8,0,0.5)); //Get to airship
		addSequential(new RotateCommand(90)); //Rotate 90 counterclockwise
		addSequential(new AutoDrive(-1,0,0,5)); //Strafe to peg
		
	}
}