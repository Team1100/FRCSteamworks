package org.usfirst.frc.team1100.robot.commands.hopper.util;

import org.usfirst.frc.team1100.robot.commands.hopper.pistons.RetractPiston;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OpenAll extends CommandGroup {

    public OpenAll() {
        for(int i =0; i<4; i++){
        	addSequential(new RetractPiston(i));
        }
    }
}
