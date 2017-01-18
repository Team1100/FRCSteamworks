package org.usfirst.frc.team1100.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Vision extends Subsystem{

	private static Vision vision;
	private static GripPipeline gp;
	
	NetworkTable table;
	
	@Override
	protected void initDefaultCommand() {
		table = NetworkTable.getTable("GRIP/conts");
		gp = new GripPipeline();
		
	}
	
	public NetworkTable getTable() {
		return table;
	}
	
	public static Vision getInstance() {
		if(vision==null) {
			vision = new Vision();
		}
		return vision;
	}

}
