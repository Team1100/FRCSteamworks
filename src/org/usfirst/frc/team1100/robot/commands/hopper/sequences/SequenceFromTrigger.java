package org.usfirst.frc.team1100.robot.commands.hopper.sequences;

import org.usfirst.frc.team1100.robot.subsystems.Hopper;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class SequenceFromTrigger extends Command {
	
	DoubleSolenoid[] p;
	int index;
	double delay;
	boolean fired;
	
	public SequenceFromTrigger() {
		requires(Hopper.getInstance());
	}
	
	@Override
	public void initialize(){
		this.p = Hopper.getInstance().getFirers();
		index = 0;
		this.delay = Hopper.HOP_DELAY;
	}
	
	public void execute(){
		if(!fired){
			p[index].set(DoubleSolenoid.Value.kReverse);
			fired = true;
			setTimeout(delay);
		}
		if(isTimedOut()){
			p[index].set(DoubleSolenoid.Value.kForward);
			if(index<3)index++; else index=0;
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