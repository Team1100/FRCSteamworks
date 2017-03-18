package org.usfirst.frc.team1100.robot.commands.drive.vision;

import java.util.ArrayList;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.vision.Vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearTrackCommand extends Command {

	private double errorSum;
	private double proportionConstant;
	private double integralConstant;
	
	private final double DRIVE_SPEED = .45;
	private final double ERROR_THRESHOLD = 0.05;
	private final double SPEED_LIMIT = 0.2; //Math.floor(Math.E)/10;
	
	public GearTrackCommand() {
		requires(Vision.getInstance());
		requires(Drive.getInstance());
		
		System.out.println("Gear tracking constructed!");
	}
	
	@Override
	public void initialize(){
		errorSum = 0;
		proportionConstant = 1;
		integralConstant = .1;
	}
	
	@Override
	public void execute() {
		SmartDashboard.putNumber("USound",Vision.getInstance().getUSound()); 
		
		double error = getError();
		if(error < ERROR_THRESHOLD) end();
		correctOffset(error);
	}

	/**
	 * Gotta compartmentalize fast
	 * @param error the current error from PIDing the offset
	 */
	private void correctOffset(double error) {
		System.err.println(error);
		Drive.getInstance().driveTank(DRIVE_SPEED -(error<0? error:0),-DRIVE_SPEED -(error>0? error:0));
	}
	
	private double getImageOffset() {
		ArrayList<double[]> contours = Vision.getInstance().requestContours();
		int perceivedCenterX = 0;
		try{
			int max1 = 0;
			int max2 = 0;
			for(int i = 0; i<contours.size();i++) {
				if(contours.get(i)[3]>contours.get(i)[4]){
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
		System.err.println("CENTER X FOUND");
		return (Vision.TRUE_CENTER_X - perceivedCenterX);
		} catch(ArithmeticException e) {
			System.err.println("No contours found!");
		} catch (NullPointerException e){
			System.err.println("Contours be null or something");
		} catch (IndexOutOfBoundsException e){
			System.err.println("EEEEeeeEEEeeAweeeawimabaayy " + contours.size());
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
		errorSum += Math.abs(tempError);
		double propError = tempError * proportionConstant;
		return tempError/650;
		//return (errorSum * integralConstant + propError)/10000;
	}
	
	@Override
	protected boolean isFinished() {
		return Vision.getInstance().getUSound()<60;
	}

}
