package org.usfirst.frc.team1100.robot.commands.hopper;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class OpenThenCloseAll extends CommandGroup{
	public OpenThenCloseAll(){
		addSequential(new OpenAll());
		addSequential(new WaitCommand(.5));
		addSequential(new CloseAll());
	}
}
