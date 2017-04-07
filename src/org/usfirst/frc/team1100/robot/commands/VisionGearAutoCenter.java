package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.commands.drive.vision.GearTrackCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VisionGearAutoCenter extends CommandGroup {

    public VisionGearAutoCenter() {
        addSequential(new GearTrackCommand());
        
    }
}
