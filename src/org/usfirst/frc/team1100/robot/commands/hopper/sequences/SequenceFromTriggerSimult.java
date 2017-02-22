package org.usfirst.frc.team1100.robot.commands.hopper.sequences;

import org.usfirst.frc.team1100.robot.subsystems.Hopper;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class SequenceFromTriggerSimult extends Command {
	
	DoubleSolenoid[] p;
	boolean first;
	double delay;
	boolean fired;
	int i1;
	int i2;
	
	public SequenceFromTriggerSimult() {
		requires(Hopper.getInstance());
	}
	
	@Override
	public void initialize(){
		this.p = Hopper.getInstance().getFirers();
		this.delay = Hopper.HOP_DELAY;
	}
	
	public void execute(){
		if(first){
			i1 = 0;
			i2 = 2;
		}else{
			i1 = 1;
			i2 = 2;
		}
		if(!fired){
			p[i1].set(DoubleSolenoid.Value.kReverse);
			p[i2].set(DoubleSolenoid.Value.kReverse);
			fired = true;
			setTimeout(delay);
		}
		if(isTimedOut()){
			p[i1].set(DoubleSolenoid.Value.kForward);
			p[i2].set(DoubleSolenoid.Value.kForward);
			first=!first;
			fired = false;
		}
	}
	
	public void end(){
		for(int i=0;i<4;i++)p[i].set(DoubleSolenoid.Value.kForward);
	}
	public void interrupted(){
		end();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}