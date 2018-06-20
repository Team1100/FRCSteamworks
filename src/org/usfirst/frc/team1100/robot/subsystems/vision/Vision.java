package org.usfirst.frc.team1100.robot.subsystems.vision;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Vision extends Subsystem {
	
	public static boolean imageRequested;
	
	public final static double ACCEPTABLE_ERROR = 10; //ten pixels is probably good enough at the distances we plan to operate from


	public static final int TRUE_CENTER_X = 320;
	
	private static Vision vision; //Standard Team 1100 Style
	private static GripPipeline gp; //This allows us to to do actual vision processing by 
	
	private AnalogInput ultrasound;
	
	NetworkTable table;
	
	private Vision() {
		//setDefaultCommand(new VisionInitCommand(table, gp));
		table = NetworkTable.getTable("GRIP/conts");
		imageRequested = false;
		gp = new GripPipeline();
		
		ultrasound = new AnalogInput(RobotMap.V_ULTRASOUND);
	}
	
	public int getUSound(){
		return ultrasound.getValue();
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
		if(gp == null) {
			System.err.println("Its gracious professionalism!!!!");
		}
		if(image == null) {
			System.err.println("Its the image!!!!");
		}
		gp.process(image);
	}
	
	@Deprecated
	public synchronized ArrayList<double[]> getContours() { //This gets the contours from the network table
		/*ArrayList<double[]> conts = new ArrayList<>(); //Conts m8
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
			if(currentCont.equals(error)) { //Checking for error
				finished = true; //Exit loop
				System.err.println("Error contour");
				break;
			}
			currentCont = table.getNumberArray("data" + i, error); //Fishing out said steaming dump.
			System.err.println(table.getNumberArray("data" + i, error)[1]);
			table.delete("data" + i);
			i--;
			conts.add(currentCont); //Add onto arraylist
		}
		return conts; //Return*/
		return gp.getContourList();
	}
	
	public synchronized ArrayList<double[]> requestContours() {
		imageRequested = true;
		for(int i = 0; i < 100; i++) {
			i++;
			i--; //This is how I wait
		}
		return gp.getContourList();
	}

	public static synchronized boolean isImageRequested() {
		return imageRequested;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
