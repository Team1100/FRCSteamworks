package org.usfirst.frc.team1100.robot.commands.hopper;

import org.usfirst.frc.team1100.robot.subsystems.Hopper;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenThenClose2 extends Command {

	private int i1,i2;
	boolean finished = false;
	DoubleSolenoid p1,p2;
    public OpenThenClose2(boolean first) {
    	requires(Hopper.getInstance());
        i1=0;
        i2=2;
        if(!first){
        	i1++;
        	i2++;
        }
        p1 = Hopper.getInstance().getFirers()[i1];
    	p2 = Hopper.getInstance().getFirers()[i2];
    }
    
    @Override
    public void initialize(){
    	setTimeout(Hopper.HOP_DELAY);
    	p1.set(DoubleSolenoid.Value.kReverse);
    	p2.set(DoubleSolenoid.Value.kReverse);
    }
    
    @Override
    public void execute(){
    	if(isTimedOut()){
    		p1.set(DoubleSolenoid.Value.kForward);
        	p2.set(DoubleSolenoid.Value.kForward);
    		finished = true;
    	}
    }
    @Override
    public void interrupted(){
    	p1.set(DoubleSolenoid.Value.kForward);
    	p2.set(DoubleSolenoid.Value.kForward);
    }
    
	@Override
	protected boolean isFinished() {
		return finished;
	}
}
