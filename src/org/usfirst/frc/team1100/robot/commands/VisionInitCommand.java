package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.subsystems.GripPipeline;
import org.usfirst.frc.team1100.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionInitCommand extends Command{

	private static NetworkTable table;
	private static GripPipeline gp;
	
	public VisionInitCommand(NetworkTable table, GripPipeline gp) {
		table = NetworkTable.getTable("GRIP/conts");
		this.gp = gp;
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
	
	
	
}
