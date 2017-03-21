package org.usfirst.frc.team1100.robot.commands.drive.vision;

import java.util.ArrayList;

import org.usfirst.frc.team1100.robot.subsystems.vision.Vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateToContour extends Command {
	
	private double offset;
	
	public RotateToContour() {
		requires(org.usfirst.frc.team1100.robot.subsystems.vision.Vision.getInstance());
		requires(org.usfirst.frc.team1100.robot.subsystems.Drive.getInstance());
	}
	
	@Override
	protected void initialize() {
		offset = getImageOffset();
		System.err.println("Rotate command initialized!");
	}
	
	protected void execute() {
		offset = getImageOffset();
		System.err.println("Rotate command executing!");
		org.usfirst.frc.team1100.robot.subsystems.Drive.getInstance().driveMecanum(0, 0, offset);
	}
	
	private int getImageOffset() {
		ArrayList<double[]> contours = Vision.getInstance().requestContours();
		int perceivedCenterX = 0;
		if(contours.size()==1){
			/*perceivedCenterX = (int)contours.get(0)[1];
			int correction = (int)Math.ceil(contours.get(0)[3]*SINGLE_STRIP_CORRECTION_RATIO);
			Vision.getInstance();
			perceivedCenterX -= (perceivedCenterX>Vision.TRUE_CENTER_X)? correction:-correction;
			return Vision.TRUE_CENTER_X-perceivedCenterX;*/
			return 0;//(Vision.TRUE_CENTER_X-contours.get(0)[1])*2;
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
	
	@Override
	protected boolean isFinished() {
		return offset <= org.usfirst.frc.team1100.robot.subsystems.vision.Vision.ACCEPTABLE_ERROR;
	}

}
