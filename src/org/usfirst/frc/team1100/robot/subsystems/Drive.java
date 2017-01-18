package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.commands.MecanumDrive;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	
	private static Drive drive;
	
	public static Drive getInstance() {
		if(drive==null) {
			drive = new Drive();
		}
		return drive;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new MecanumDrive());
	}

}
