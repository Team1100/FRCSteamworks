package org.usfirst.frc.team1100.robot.commands.shooter;

import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterDefault extends Command {

	private double timeEnabled;

	public ShooterDefault() {
		requires(Shooter.getInstance());
	}

	public void execute() {
		if (Robot.tele) {
			// Display current values to the smart dash
			SmartDashboard.putNumber("Current A", Shooter.getInstance().getShooterCurrentA());
			SmartDashboard.putNumber("Current B", Shooter.getInstance().getShooterCurrentB());
			SmartDashboard.putNumber("Average Current", Shooter.getInstance().getAverageCurrent());

			// Blocks for controlling speed of wheel
			if (Shooter.getInstance().getOn()) {
				if (timeEnabled == 0)
					timeEnabled = System.currentTimeMillis();
				Shooter.getInstance().setSpeedToTarget();
				Shooter.getInstance().runConveyor();
			} else {
				timeEnabled = 0;
				Shooter.getInstance().stopShooter();
				Shooter.getInstance().stopConveyor();
			}

			// Prevent ball entry early on when speed is low
			if (Shooter.getInstance().getSpeed() < Shooter.MIN_SPEED
					&& System.currentTimeMillis() - timeEnabled < 1000) {
				//TODO Force agitator to stop
			}

			//Prevent current spikes
			if ((Shooter.getInstance().getShooterCurrentA() >= Shooter.CURRENT_THRESHOLD
					|| Shooter.getInstance().getShooterCurrentB() >= Shooter.CURRENT_THRESHOLD)
					&& (System.currentTimeMillis() - timeEnabled) > 1000) {
				Shooter.getInstance().setOn(false);
				System.err.println("Current hit Shooter.CURRENT_THRESHOLD");
			}

			//Stop shooter if encoder fails
			if(Shooter.getInstance().getSpeed()<=5
				&& (System.currentTimeMillis() - timeEnabled) > 1000) {
					Shooter.getInstance().setOn(false);
					System.err.println("Yeah so the encoder done broke");
				}
			
			// Block ball entry when shooter is off
			if (!Shooter.getInstance().getOn()) {
				//TODO
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}