package org.usfirst.frc.team1100.robot.commands.drive.util;

public class ImprovedEulerApprox {
	
	private final double PI = 3.14;
	
	private double[] prevVelocity = new double[2];
	private double[] currentVelocity = new double[2];
	private double[] currentPosition = new double[2];
	private double[] start = new double[2];
	
	public ImprovedEulerApprox(double[] s) {
		start = s;
		currentPosition = start;
	}
	
	public void step(double dtime, int speed, double[] direction) {
		double input = dtime*dtime * PI / speed;
		scalarMultiply(direction, input);
		currentVelocity = addVectors(prevVelocity, direction);
		
		currentPosition = addVectors(currentPosition, scalarMultiply(addVectors(prevVelocity,currentVelocity),0.5 * dtime,true));	
		prevVelocity = currentVelocity;
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
	
	private double[] addVectors(double[] v1, double[] v2) {
		double x = v1[0] + v2[0];
		double y = v1[1] + v2[1];
		return new double[]{x,y};
	}
	
	private void scalarMultiply(double[] v, double s) {
		v[0] *= s;
		v[1] *= s;
	}
	
	private double[] scalarMultiply(double[] v, double s, boolean placeholder) {
		return new double[]{v[0]*s,v[1]*s};
	}

}
