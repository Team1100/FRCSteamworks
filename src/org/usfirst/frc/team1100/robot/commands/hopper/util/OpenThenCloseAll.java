package org.usfirst.frc.team1100.robot.commands.hopper.util;

import org.usfirst.frc.team1100.robot.subsystems.Hopper;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class OpenThenCloseAll extends CommandGroup{
	public OpenThenCloseAll(){
		addSequential(new OpenAll());
		addSequential(new WaitCommand(Hopper.HOP_DELAY));
		addSequential(new CloseAll());
	}
}