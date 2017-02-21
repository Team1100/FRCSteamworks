package org.usfirst.frc.team1100.robot.commands.shooter;

import org.usfirst.frc.team1100.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetShooterState extends Command {

	private boolean value;
	private boolean done;

	public SetShooterState(boolean value) {
        requires(Shooter.getInstance());
        this.value = value;
    }

	public void initialize(){
		done = false;
	}
	
    public void execute(){
    	Shooter.getInstance().setOn(value);
    	done = true;
    }
    
	@Override
	protected boolean isFinished() {
		
		return done;
	}
}
