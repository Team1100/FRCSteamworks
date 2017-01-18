package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

public class MecanumDrive extends Command {
	
	private static RobotDrive robotDrive;
	
	public MecanumDrive() {
		robotDrive = new RobotDrive(RobotMap.D_MOTOR_0,RobotMap.D_MOTOR_1,RobotMap.D_MOTOR_2,RobotMap.D_MOTOR_3);
	}
	
	@Override
	public void execute() {
		robotDrive.mecanumDrive_Cartesian(OI.getInstance().getLeftStick().getX(), OI.getInstance().getLeftStick().getY(), OI.getInstance().getLeftStick().getTwist(), 0);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
