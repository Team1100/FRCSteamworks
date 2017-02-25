package org.usfirst.frc.team1100.robot.commands.auto;


import org.usfirst.frc.team1100.robot.commands.drive.AutoDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearAutoCenter extends CommandGroup {
	
	public GearAutoCenter() {
		addSequential(new AutoDrive(AutoMap.GEAR_CENTER_SPEED,0,0,AutoMap.GEAR_CENTER_TIMEOUT));
	}
	
}
