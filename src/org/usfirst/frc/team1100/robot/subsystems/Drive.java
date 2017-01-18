package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.MecanumDrive;

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
		driveTrain = new RobotDrive(RobotMap.D_MOTOR_0, RobotMap.D_MOTOR_1, RobotMap.D_MOTOR_2, RobotMap.D_MOTOR_3);
	}
	
	public void driveMecanum(double x, double y, double rotation, double gyroAngle ){
		driveTrain.mecanumDrive_Cartesian(x, y, rotation, gyroAngle);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new MecanumDrive());
	}

}
