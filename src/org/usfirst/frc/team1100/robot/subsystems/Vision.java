package org.usfirst.frc.team1100.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team1100.robot.commands.VisionInitCommand;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Vision extends Subsystem {
	
	public final static double ACCEPTABLE_ERROR = 10; //ten pixels is probably good enough at the distances we plan to operate from

	private static Vision vision; //Standard Team 1100 Style©
	private static GripPipeline gp;
	
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
	
	public ArrayList<double[]> getContours() {
		ArrayList<double[]> conts = new ArrayList<>();
		double[] currentCont;
		double[] error = new double[5];
		for(int i = 0; i < 5; i++) {
			error[i] = -i;
		}
		boolean finished = false;
		int i = -1;
		while(!finished) {
			++i;
			currentCont = new double[5];
			currentCont = table.getNumberArray("data" + i, error);
			if(currentCont.equals(error)) {
				finished = true;
				break;
			}
			conts.add(currentCont);
		}
		return conts;
	}
	
	public void centerContours() {
		
		
	}

}
