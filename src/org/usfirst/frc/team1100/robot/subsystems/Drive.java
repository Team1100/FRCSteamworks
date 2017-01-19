package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.drivecommands.UserDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	
	private static Drive drive;
	private RobotDrive driveTrain;
	
	public static Drive getInstance() {
		if(drive==null) {
			drive = new Drive();
		}
		return drive;
	}
	
	public Drive(){
		driveTrain = new RobotDrive(RobotMap.D_FRONT_LEFT, RobotMap.D_BACK_LEFT, RobotMap.D_FRONT_RIGHT, RobotMap.D_BACK_RIGHT);
	}
	
	public void driveMecanum(double x, double y, double rotation){
		//driveTrain.mecanumDrive_Cartesian(x, y, rotation,0.0);
		driveTrain.tankDrive(x,rotation);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UserDrive());
	}

}
