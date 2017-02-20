package org.usfirst.frc.team1100.robot.commands.hopper.pistons;

import org.usfirst.frc.team1100.robot.subsystems.Hopper;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TogglePiston extends Command {

    private int index;
    boolean done;

	public TogglePiston(int index) {
		requires(Hopper.getInstance());
        this.index = index;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	done = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DoubleSolenoid p = Hopper.getInstance().getFirers()[index];
    	if(p.get()==DoubleSolenoid.Value.kForward||p.get()==DoubleSolenoid.Value.kOff){
    		p.set(DoubleSolenoid.Value.kReverse);
    	}else p.set(DoubleSolenoid.Value.kForward);
    	if(p.get()==DoubleSolenoid.Value.kForward||p.get()==DoubleSolenoid.Value.kOff){
    		p.set(DoubleSolenoid.Value.kReverse);
    	}else p.set(DoubleSolenoid.Value.kForward);
    	done = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
