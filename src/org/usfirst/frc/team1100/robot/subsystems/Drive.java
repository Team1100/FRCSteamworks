package org.usfirst.frc.team1100.robot.subsystems;

public class Drive {
	
	private static Drive drive;
	
	public static Drive getInstance() {
		if(drive==null) {
			drive = new Drive();
		}
		return drive;
	}

}
