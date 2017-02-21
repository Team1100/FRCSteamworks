package org.usfirst.frc.team1100.robot.commands.shooter;

import org.usfirst.frc.team1100.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleShooterCommand extends Command {

    private double speed;
	private boolean done;

	public ToggleShooterCommand() {
        requires(Shooter.getInstance());
    }

	public void initialize(){
		done = false;
	}
	
    public void execute(){
    	Shooter.getInstance().setOn(!Shooter.getInstance().getOn());
    	done = true;
    }
    
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return done;
	}
}
