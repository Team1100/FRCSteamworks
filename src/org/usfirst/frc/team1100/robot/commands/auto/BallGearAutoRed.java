package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.AutoDrive;
import org.usfirst.frc.team1100.robot.commands.drive.RotateCommand;
import org.usfirst.frc.team1100.robot.commands.drive.vision.CenterContoursCommand;
import org.usfirst.frc.team1100.robot.commands.hopper.sequences.FireSequenceSimult;
import org.usfirst.frc.team1100.robot.commands.shooter.auto.AutoWheel;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BallGearAutoRed extends CommandGroup {
	
	public BallGearAutoRed() {
		
		addParallel(new AutoWheel(5));
		
		addSequential(new AutoDrive(AutoMap.BOILER_STRAFE_POWER, 0, 0, AutoMap.BOILER_STRAFE_TIMEOUT));
		
		addSequential(new WaitCommand(AutoMap.WHEEL_SPINUP_DELAY));
		
		for(int i = 6; i > 0; i--) {
			addSequential(new FireSequenceSimult());
		}
		
		addSequential(new AutoDrive(0,AutoMap.REVERSE_FROM_BOILER_POWER, 0, AutoMap.REVERSE_FROM_BOILER_TIMEOUT));
		
		addSequential(new RotateCommand(AutoMap.FACE_GEAR_ANGLE));
		
		addSequential(new CenterContoursCommand(AutoMap.CONTOUR_TIMEOUT));
	
		addSequential(new AutoDrive(AutoMap.GEAR_STRAFE_POWER,0,0,AutoMap.GEAR_STRAFE_TIMEOUT));
		
		addSequential(new WaitCommand(AutoMap.GEAR_RETRIEVAL_DELAY));
		
		addSequential(new AutoDrive(-AutoMap.GEAR_STRAFE_POWER,0,0,AutoMap.GEAR_STRAFE_TIMEOUT));
		
		addSequential(new AutoDrive(0,AutoMap.LINE_CHARGE_POWER,0,AutoMap.LINE_CHARGE_TIMEOUT));
		
		addSequential(new AutoDrive(-AutoMap.BALL_STRAFE_POWER,0,0,AutoMap.BALL_STRAFE_TIMEOUT));
		
	}

}
