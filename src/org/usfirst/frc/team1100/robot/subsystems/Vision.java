package org.usfirst.frc.team1100.robot.subsystems;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.usfirst.frc.team1100.robot.commands.vision.VisionInitCommand;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Vision extends Subsystem {
	
	public final static double ACCEPTABLE_ERROR = 10; //ten pixels is probably good enough at the distances we plan to operate from

	private static Vision vision; //Standard Team 1100 Style
	private static GripPipeline gp; //This allows us to to do actual vision processing by 
	
	NetworkTable table;
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new VisionInitCommand(table, gp));
	}
	
	public NetworkTable getTable() {
		return table;
	}
	
	public static Vision getInstance() {
		if(vision==null) {
			vision = new Vision();
		}
		return vision;
	}
	
	public void process(Mat image) {
		gp.process(image);
	}
	
	public ArrayList<double[]> getContours() { //This gets the contours from the network table
		ArrayList<double[]> conts = new ArrayList<>(); //Conts m8
		double[] currentCont; //This represents the contour currently being iterated over in the network table
		double[] error = new double[5]; //This is the default set of values that we can use to detect error. If this ever comes up, we know something is on
		for(int i = 0; i < 5; i++) {
			error[i] = -i; //Negative values should not come up with our vision
		}
		boolean finished = false; //Loop sentinel. Sort of.
		int i = -1; //Loop index.
		while(!finished) { //Loop
			++i; //Saves one CPU cycle AND looks cool.
			currentCont = new double[5]; //Reset or initialize the array
			currentCont = table.getNumberArray("data" + i, error); //Fishing out said steaming dump.
																   //If there is nothing here, it will return the error array
			if(currentCont.equals(error)) { //Checking for error
				finished = true; //Exit loop
				break;
			}
			conts.add(currentCont); //Add onto arraylist
		}
		System.err.println("Test working!");
		return conts; //Return
	}

}
