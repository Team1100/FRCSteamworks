package org.usfirst.frc.team1100.robot.commands.drive.util;

/**
 * A class for use wherever a Euler Approximation is needed.
 * @author Joshua-Gordon
 */
public class EulerApprox {
	
	private double[] currentVelocity = new double[2];
	private double[] currentPosition = new double[2];
	private double[] start = new double[2];
	
	/**
	 * Accepts a starting x and y coordinate in a 2d array.
	 * @param s the starting x and y coordinate in a 2d array
	 */
	public EulerApprox(double[] s) {
		start = s;
		currentPosition = start;
	}
	
	/**
	 * Step forward the Euler Approximation by a time, with a set of x and y accelerations.
	 * @param dtime the time since the last step or start of the command if this is the first step
	 * @param ac a 2d array of acceleration. The 0 index for x acceleration, and the 1 index for y acceleration
	 */
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

	/**
	 * Gets the current velocity according to the Euler Approximation.
	 * @return the approximated current velocity
	 */
	public double[] getCurrentVelocity() {
		return currentVelocity;
	}

	/**
	 * Gets the current displacement according to the Euler Approximation.
	 * @return the approximated current displacement
	 */
	public double[] getDisplacement() {
		return currentPosition;
	}
	
}
