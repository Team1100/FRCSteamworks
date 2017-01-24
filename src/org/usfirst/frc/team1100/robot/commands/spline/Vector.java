package org.usfirst.frc.team1100.robot.commands.spline;

public class Vector {

	private double x, y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector scalarMultiply(double s){
		return new Vector(s*x,s*y);
	}
	
	public static Vector add(Vector v1, Vector v2) {
		return new Vector(v1.getX()+v2.getX(),v1.getY()+v2.getY());
	}
	
	public static double normalizedDotProduct(Vector v1, Vector v2) {
		return v1.x/v1.getMagnitude()*v2.x/v2.getMagnitude() + v1.y/v1.getMagnitude()*v2.y/v2.getMagnitude();	
	}
	
	public static Vector getNormalizedVectorFromAngle(double angle) {
		return new Vector(Math.cos(angle),Math.sin(angle));
	}
	
	/**
	 * 
	 * @param v1 the first vector
	 * @param v2 the second vector
	 * @return true if v1 is clockwise of v2
	 */
	public static boolean isClockwise(Vector v1, Vector v2) {
		return Math.atan2(v1.getY(), v1.getX()) <= Math.atan2(v2.getY(), v2.getX());
	}
	
	public double getMagnitude() {
		return Math.sqrt(Math.pow(x,2) + Math.pow(y, 2));
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
	
}
