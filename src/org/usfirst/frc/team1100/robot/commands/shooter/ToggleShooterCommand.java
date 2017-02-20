package org.usfirst.frc.team1100.robot.commands.shooter;

import org.usfirst.frc.team1100.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleShooterCommand extends Command {

    private double speed;
	private boolean done;

	public ToggleShooterCommand(double speed) {
        requires(Shooter.getInstance());
        this.speed = speed;
        this.done = false;
    }

    public void execute(){
    	if(Shooter.getInstance().getSpeed()==0){
    		Shooter.getInstance().setSpeedFromDash();
    	}else Shooter.getInstance().stopFlywheel();
    	done = true;
    }
    
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return done;
	}
}
