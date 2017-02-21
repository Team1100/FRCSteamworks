package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.AutoDrive;
import org.usfirst.frc.team1100.robot.commands.drive.CorrectedStrafe;
import org.usfirst.frc.team1100.robot.commands.drive.RotateCommand;
import org.usfirst.frc.team1100.robot.commands.hopper.sequences.FireSequenceSimult;
import org.usfirst.frc.team1100.robot.commands.shooter.SetShooterState;
import org.usfirst.frc.team1100.robot.commands.vision.CenterContoursCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BallGearAutoBlue extends CommandGroup {
	public BallGearAutoBlue(){
		//Start wheel spinup
		addSequential(new SetShooterState(true));
		//Boiler is on the right of drive wall
		addSequential(new CorrectedStrafe(AutoMap.BOILER_STRAFE_POWER,AutoMap.BOILER_STRAFE_TIMEOUT));
		//ShootTheDoot
		for(int i=3;i<0;i++)addSequential(new FireSequenceSimult());
		//Turn Shooter off
		addSequential(new SetShooterState(false));
		//Back Up
		addSequential(new AutoDrive(AutoMap.REVERSE_FROM_BOILER_POWER,0,0,AutoMap.REVERSE_FROM_GEAR_TIMEOUT));
		//Turn to have gear facing
		addSequential(new RotateCommand(AutoMap.FACE_GEAR_ANGLE));
		//Line up
		addSequential(new CenterContoursCommand(AutoMap.CONTOUR_TIMEOUT));
		//Move in
		addSequential(new CorrectedStrafe(AutoMap.GEAR_STRAFE_POWER,AutoMap.GEAR_STRAFE_TIMEOUT));
		//Wait for gear to be removed
		addSequential(new WaitCommand(AutoMap.GEAR_RETRIEVAL_DELAY));
		//Strafe out
		addSequential(new CorrectedStrafe(-AutoMap.GEAR_STRAFE_POWER,AutoMap.GEAR_STRAFE_TIMEOUT));
		//In for the kill
		addSequential(new AutoDrive(-AutoMap.LINE_CHARGE_POWER,0,0,AutoMap.LINE_CHARGE_TIMEOUT));
	}
}