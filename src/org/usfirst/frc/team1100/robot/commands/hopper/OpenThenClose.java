package org.usfirst.frc.team1100.robot.commands.hopper;

import org.usfirst.frc.team1100.robot.commands.hopper.pistons.FirePiston;
import org.usfirst.frc.team1100.robot.commands.hopper.pistons.RetractPiston;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *@author NotSupremeSteak1
 * Opens and then closes. What is it opening and closing, you ask? Well...
 */
public class OpenThenClose extends CommandGroup {

    public OpenThenClose(int index) {
        addSequential(new RetractPiston(index));
        addSequential(new WaitCommand(.2));
        addSequential(new FirePiston(index));
    }
}