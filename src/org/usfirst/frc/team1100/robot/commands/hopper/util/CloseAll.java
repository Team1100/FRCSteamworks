package org.usfirst.frc.team1100.robot.commands.hopper.util;

import org.usfirst.frc.team1100.robot.commands.hopper.pistons.FirePiston;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CloseAll extends CommandGroup {

    public CloseAll() {
        for(int i =0; i<4; i++){
        	addSequential(new FirePiston(i));
        }
    }
}
