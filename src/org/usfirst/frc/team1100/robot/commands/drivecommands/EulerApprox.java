package org.usfirst.frc.team1100.robot.commands.drivecommands;

public class EulerApprox {
	
	private double[] currentVelocity = new double[2];
	private double[] currentPosition = new double[2];
	
	public EulerApprox() {
		
	}
	
	public void step(double dtime, double[] ac) {
		for(int i = 0; i < ac.length; i++) {
			currentVelocity[i] += ac[i] * dtime;
			currentPosition[i] += currentVelocity[i] * dtime;
		}
	}

	public double[] getCurrentVelocity() {
		return currentVelocity;
	}

	public double[] getCurrentPosition() {
		return currentPosition;
	}
	
}
