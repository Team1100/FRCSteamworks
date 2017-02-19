package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.subsystems.vision.GripPipeline;
import org.usfirst.frc.team1100.robot.subsystems.vision.Vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

@Deprecated
public class VisionInitCommand extends Command{

	private static NetworkTable table;
	private static GripPipeline gp;
	
	@Deprecated
	public VisionInitCommand(NetworkTable table, GripPipeline gp) {
		requires(Vision.getInstance());
		
	}
	
	@Deprecated
	public void execute() {
		
	}
	
	@Deprecated
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	
	
}
