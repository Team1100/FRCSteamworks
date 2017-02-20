package org.usfirst.frc.team1100.robot.commands.shooter;

import org.usfirst.frc.team1100.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The SetShooterSpeedCommand sets the speed of the shooter
 */
public class SetShooterSpeedCommand extends Command {
	
	private double speed;
	
	/**
	 * Creates a new SetShooterSpeedCommand
	 * @param speed the speed to set the shooter (between -1 and 1)
	 * @param timeout the time in second until the command is force stopped
	 */
    public SetShooterSpeedCommand(double speed, double timeout) {
        requires(Shooter.getInstance());
        this.speed = speed;
        setTimeout(timeout);
    }

    public SetShooterSpeedCommand(double speed) {
        requires(Shooter.getInstance());
        this.speed = speed;
    }
    /**
     * Called once just before the command starts
     */
    protected void initialize() {
    	
    }

    /**
     * Called many times a second while the command is running
     */
    protected void execute() {
    	//Shooter.getInstance().setFlywheelSpeed(speed);
    	Shooter.getInstance().setSpeedFromDash();
    }

    /**
     * Returns if the command is finished or not
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Called when the command is finished according to the isFinished() command
     */
    protected void end() {
    	Shooter.getInstance().stopFlywheel();
    }

    /**
     * Called when the command is interrupted
     */
    protected void interrupted() {
    	end();
    }
}
