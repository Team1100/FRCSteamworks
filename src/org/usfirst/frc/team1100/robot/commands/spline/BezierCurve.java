package org.usfirst.frc.team1100.robot.commands;

public class BezierCurve {
	
	private Vector p0, p1, p2;
	//Quadratic
	public BezierCurve(Vector p0, Vector p1, Vector p2) {
		this.p0 = p0;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Vector getPoint(double parameter) {
		return Vector.add(Vector.add(p0.scalarMultiply(Math.pow((1-parameter),2)), p1.scalarMultiply(2-2*parameter)), p2.scalarMultiply(Math.pow(parameter, 2)));
	}
	
	public Vector getDerivativeAtPoint(double parameter) {
		return Vector.add(Vector.add(p1, p0.scalarMultiply(-1)).scalarMultiply(2-2*parameter), Vector.add(p2, p1.scalarMultiply(-1)).scalarMultiply(2*parameter));
	}

}
