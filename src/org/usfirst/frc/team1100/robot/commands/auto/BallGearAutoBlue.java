package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.AutoDrive;
import org.usfirst.frc.team1100.robot.commands.drive.RotateCommand;
import org.usfirst.frc.team1100.robot.commands.drive.vision.CenterContoursCommand;
import org.usfirst.frc.team1100.robot.commands.hopper.sequences.FireSequenceSimult;
import org.usfirst.frc.team1100.robot.commands.shooter.auto.AutoWheel;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BallGearAutoBlue extends CommandGroup {
	public BallGearAutoBlue(){
		//Start wheel spinup
		addParallel(new AutoWheel(5));
		//Boiler is on the right of drive wall
		addSequential(new AutoDrive(-AutoMap.BOILER_STRAFE_POWER,0,0,AutoMap.BOILER_STRAFE_TIMEOUT));
		//Wait for spinup
		addSequential(new WaitCommand(AutoMap.WHEEL_SPINUP_DELAY));
		//ShootTheDoot
		for(int i=0;i<6;i++){
			addSequential(new FireSequenceSimult());
		}
		//Back Up
		addSequential(new AutoDrive(0,AutoMap.REVERSE_FROM_BOILER_POWER,0,AutoMap.REVERSE_FROM_BOILER_TIMEOUT));
		//Turn to have gear facing
		addSequential(new RotateCommand(-AutoMap.FACE_GEAR_ANGLE));
		//Line up
		addSequential(new CenterContoursCommand(AutoMap.CONTOUR_TIMEOUT));
		//Move in
		//addSequential(new CorrectedStrafe(-AutoMap.GEAR_STRAFE_POWER,AutoMap.GEAR_STRAFE_TIMEOUT));
		addSequential(new AutoDrive(-AutoMap.GEAR_STRAFE_POWER,0,0,AutoMap.GEAR_STRAFE_TIMEOUT));
		//Wait for gear to be removed
		addSequential(new WaitCommand(AutoMap.GEAR_RETRIEVAL_DELAY));
		//Strafe out
		//addSequential(new CorrectedStrafe(AutoMap.GEAR_STRAFE_POWER,AutoMap.GEAR_STRAFE_TIMEOUT));
		addSequential(new AutoDrive(AutoMap.GEAR_STRAFE_POWER,0,0,AutoMap.GEAR_STRAFE_TIMEOUT));
		//In for the kill
		addSequential(new AutoDrive(0,-AutoMap.LINE_CHARGE_POWER,0,AutoMap.LINE_CHARGE_TIMEOUT));
		//Final Strage
		addSequential(new AutoDrive(AutoMap.BALL_STRAFE_POWER,0,0,AutoMap.BALL_STRAFE_TIMEOUT));
	}
}