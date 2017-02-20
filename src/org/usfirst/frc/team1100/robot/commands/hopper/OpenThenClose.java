package org.usfirst.frc.team1100.robot.commands.hopper;

import org.usfirst.frc.team1100.robot.commands.hopper.pistons.FirePiston;
import org.usfirst.frc.team1100.robot.commands.hopper.pistons.RetractPiston;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class OpenThenClose extends CommandGroup {

    public OpenThenClose(int index) {
        addSequential(new RetractPiston(index));
        addSequential(new WaitCommand(.25));
        addSequential(new FirePiston(index));
    }
}
