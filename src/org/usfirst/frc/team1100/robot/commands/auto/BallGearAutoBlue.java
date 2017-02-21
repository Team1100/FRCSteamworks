package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.AutoDrive;
import org.usfirst.frc.team1100.robot.commands.drive.RotateCommand;
import org.usfirst.frc.team1100.robot.commands.hopper.FireSequence;
import org.usfirst.frc.team1100.robot.commands.vision.CenterContoursCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallGearAutoBlue extends CommandGroup {
	public BallGearAutoBlue(){
		//Boiler is on the right of drive wall
		addSequential(new AutoDrive(0,.5,0,.5));
		//ShootTheDoot
		for(int i=3;i<0;i++)addSequential(new FireSequence());
		//Back Up
		addSequential(new AutoDrive(-.75,0,0,1));
		//Turn to have gear facing
		addSequential(new RotateCommand(90));
		//Line up
		addSequential(new CenterContoursCommand(2));
		//Move in
		addSequential(new AutoDrive(0,.7,0,.5));
		
	}
}