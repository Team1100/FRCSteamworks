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
		for(int i = 0; i < ac.length; i++) {
			currentVelocity[i] += ac[i] * dtime;
			currentPosition[i] += currentVelocity[i] * dtime;
		}
	}

	public double[] getCurrentVelocity() {
		return currentVelocity;
	}

	public double[] getDisplacement() {
		double[] displacement = new double[2];
		for(int i = 0; i < 2; i++) {
			displacement[i] = start[i] - currentPosition[i];
		}
		return displacement;
	}
	
}
