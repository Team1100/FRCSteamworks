package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.AutoDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoStraightAuto extends CommandGroup {
	
	public GoStraightAuto() {
		
		addSequential(new AutoDrive(0, -AutoMap.REVERSE_FROM_BOILER_POWER,0,3));
		
	}

}
