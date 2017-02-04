package org.usfirst.frc.team1100.robot.commands.drivecommands;

public class EulerApprox {
	
	private double[] currentVelocity = new double[2];
	private double[] currentPosition = new double[2];
	private double[] start = new double[2];
	
	public EulerApprox(double[] s) {
		start = s;
		currentPosition = start;
	}
	
	public void step(double dtime, double[] ac) {
		//System.err.println("Time step: " + dtime);
		for(int i = 0; i < ac.length; i++) {
			currentVelocity[i] += ac[i] * dtime;
			currentPosition[i] += currentVelocity[i] * dtime;
		}
		//Correction factor. This is why I'm not a real real analyst
		currentVelocity[0]/=1.5;
		currentVelocity[1] /=1.5;
	}

	public double[] getCurrentVelocity() {
		return currentVelocity;
	}

	public double[] getDisplacement() {
		return currentPosition;
	}
	
}
