package org.usfirst.frc.team1100.robot.commands.auto;


import org.usfirst.frc.team1100.robot.commands.drive.AutoDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearAutoCenter extends CommandGroup {
	
	public GearAutoCenter() {
		addSequential(new AutoDrive(-AutoMap.GEAR_CENTER_SPEED,0,0,AutoMap.GEAR_CENTER_TIMEOUT));
		addSequential(new WaitCommand(3));
		addSequential(new AutoDrive(AutoMap.GEAR_CENTER_SPEED,0,0,AutoMap.GEAR_CENTER_REVERSE_TIMEOUT));
		addSequential(new AutoDrive(0,-AutoMap.AROUND_AIRSHIP_CENTER_POWER,0,AutoMap.AROUND_AIRSHIP_CENTER_TIMEOUT));
		addSequential(new AutoDrive(AutoMap.STRAFE_OVER_LINE_POWER,0,0,AutoMap.STRAFE_OVER_LINE_TIMEOUT));
	}
	
}
