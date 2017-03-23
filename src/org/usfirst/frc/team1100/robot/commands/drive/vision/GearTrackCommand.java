package org.usfirst.frc.team1100.robot.commands.drive.vision;

import java.util.ArrayList;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.vision.Vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearTrackCommand extends Command {

	private double errorSum;
	private double proportionConstant;
	private double integralConstant;
	private double motorCorrection;
	private double turningCorrection;
	
	private double exponentialTurningCorrectorCorrector;
	
	private final double DRIVE_SPEED = .5;
	
	public GearTrackCommand() {
		requires(Vision.getInstance());
		requires(Drive.getInstance());
		
		proportionConstant = 3;
		integralConstant = .01;
		motorCorrection = 1;
		turningCorrection = 3;
		exponentialTurningCorrectorCorrector = 50;
		SmartDashboard.putNumber("P", proportionConstant);
		SmartDashboard.putNumber("I", integralConstant);
		SmartDashboard.putNumber("MC", motorCorrection);
		SmartDashboard.putNumber("TC", turningCorrection);
		SmartDashboard.putNumber("ETCC", exponentialTurningCorrectorCorrector);
		
		System.out.println("Gear tracking constructed!");
	}
	
	@Override
	public void initialize(){
		errorSum = 0;
		this.proportionConstant = SmartDashboard.getNumber("P", this.proportionConstant);
		this.integralConstant = SmartDashboard.getNumber("I",this.integralConstant);
		this.motorCorrection = SmartDashboard.getNumber("MC", this.motorCorrection);
		this.turningCorrection = SmartDashboard.getNumber("TC",this.turningCorrection);
		this.exponentialTurningCorrectorCorrector = SmartDashboard.getNumber("ETCC", this.exponentialTurningCorrectorCorrector);
		
		Drive.getInstance().setReversed(false);
	}
	
	@Override
	public void execute() {
		SmartDashboard.putNumber("USound",Vision.getInstance().getUSound()); 
		
		
		double error = getError();
		correctOffset(error);
	}
	
	/**
	 * Gotta compartmentalize fast
	 * @param error the current error from PIDing the offset
	 */
	private void correctOffset(double error) {
		Drive.getInstance().driveTank((-DRIVE_SPEED -(error>0? error:-error/turningCorrection)),(DRIVE_SPEED -(error<0? error:-error/turningCorrection))*motorCorrection);
	}
	
	private double getImageOffset() {
		ArrayList<double[]> contours = Vision.getInstance().requestContours();
		int perceivedCenterX = 0;
		if(contours.size()<2){
			return 0;
		}
		try{
			int max1 = 0;
			int max2 = 0;
			for(int i = 0; i<contours.size();i++) {
				if(contours.get(i)[3]>=contours.get(i)[4]){
					continue;
				}
				if(contours.get(i)[2] > contours.get(max1)[2]) {
					max1 = i;
				} else if(contours.get(i)[2] > contours.get(max2)[2]){
					max2 = i;
				}
			}
			double[] contour1 = contours.get(max1);
			double[] contour2 = contours.get(max2);
		perceivedCenterX = (int)(contour1[1] + contour2[1]);
		perceivedCenterX /= 2;
		System.err.println("Perceived Center X: " + perceivedCenterX);
		SmartDashboard.putNumber("CenterX", perceivedCenterX);
		SmartDashboard.putNumber("Area 1", contour1[0]);
		SmartDashboard.putNumber("Area 2", contour2[0]);
		System.err.println("CENTER X FOUND");
		return (Vision.TRUE_CENTER_X - perceivedCenterX);
		} catch(ArithmeticException e) {
			System.err.println("No contours found!");
		} catch (NullPointerException e){
			System.err.println("Contours be null or something");
		} catch (IndexOutOfBoundsException e){
			System.err.println("WE'RE BREAKIN THE INDEXES " + contours.size());
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Uses P and I to compute the error function (No, not erf)
	 * @return the error computed from PID
	 */
	private double getError() {
		double tempError = getImageOffset();
		errorSum += tempError;
		turningCorrection -= Math.exp(errorSum/exponentialTurningCorrectorCorrector); //TODO: Test this value
		turningCorrection = Math.max(turningCorrection, 1);
		double propError = tempError * proportionConstant;
		//return tempError/1000;
		return (errorSum * integralConstant + propError)/10000;
	}
	
	@Override
	protected boolean isFinished() {
		return Vision.getInstance().getUSound()<100//TODO
				|| OI.getInstance().getStick().getButton(5).get();
	}

	@Override
	protected void end(){
		Drive.getInstance().setReversed(false);
		Drive.getInstance().driveTank(0, 0);
	}
	
}
