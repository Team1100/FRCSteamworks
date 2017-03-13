/*
 * This is the fossilized version of robot.
 * 
 * By the time you are reading this, the robot will not reflect the contents 
 * of these classes. Do not change the code from this branch!!!
 */
package org.usfirst.frc.team1100.robot;

import org.opencv.core.Mat;
import org.usfirst.frc.team1100.robot.commands.auto.BallGearAutoBlue;
import org.usfirst.frc.team1100.robot.commands.auto.BallGearAutoRed;
import org.usfirst.frc.team1100.robot.commands.auto.GearAutoCenter;
import org.usfirst.frc.team1100.robot.commands.auto.GearAutoNoBoilerBlue;
import org.usfirst.frc.team1100.robot.commands.auto.GearAutoNoBoilerRed;
import org.usfirst.frc.team1100.robot.subsystems.Climber;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Gear;
import org.usfirst.frc.team1100.robot.subsystems.Hopper;
import org.usfirst.frc.team1100.robot.subsystems.Intake;
import org.usfirst.frc.team1100.robot.subsystems.Shooter;
import org.usfirst.frc.team1100.robot.subsystems.vision.Vision;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static boolean tele;
	
	private Thread t;
	
	//Subsystem init

	Command autonomousCommand;
	SendableChooser<Command> chooser ;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// PLEASE: remember to initialize all of the subsystems by calling their respective getInstance() method
		// If you fail to do this, it will not work and then it will be considered a software issue
		Drive.getInstance();
		Vision.getInstance();
		Intake.getInstance();
		Shooter.getInstance();
		OI.getInstance();
		Hopper.getInstance();
		Gear.getInstance();
		Climber.getInstance();
		
		
		chooser = new SendableChooser<Command>();
		
		chooser.addObject("Boiler Side Blue", new BallGearAutoBlue());
		chooser.addObject("Boiler Side Red", new BallGearAutoRed());
		chooser.addObject("Gear Straight Auto", new GearAutoCenter());
		
		chooser.addObject("Center", new GearAutoCenter());
		chooser.addDefault("Default", new GearAutoCenter());
		
		chooser.addObject("Loader Side Blue", new GearAutoNoBoilerBlue());
		chooser.addObject("Loader Side Red", new GearAutoNoBoilerRed());
		
		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putData("Auto Selector",chooser);
		
		// The following is the test mode stuff
		LiveWindow.addActuator("Intake", "Roller", Intake.getInstance().getRollerLWS());
		LiveWindow.addActuator("Intake", "Roller2", Intake.getInstance().getRoller2LWS());
		
		LiveWindow.addActuator("Shooter", "Flywheel", Shooter.getInstance().getFlywheelLWS());
		LiveWindow.addActuator("Shooter", "Flywheel2", Shooter.getInstance().getFlywheel2LWS());
		
		LiveWindow.addActuator("Climber", "Motor", Climber.getInstance().climbLWS());
		LiveWindow.addActuator("Climber", "Motor2", Climber.getInstance().climb2LWS());
		
		LiveWindow.addActuator("Hopper", "Piston 1", Hopper.getInstance().hopperLWS(0));
		LiveWindow.addActuator("Hopper", "Piston 2", Hopper.getInstance().hopperLWS(1));
		LiveWindow.addActuator("Hopper", "Piston 3", Hopper.getInstance().hopperLWS(2));
		LiveWindow.addActuator("Hopper", "Piston 4", Hopper.getInstance().hopperLWS(3));
		
		LiveWindow.addActuator("Gear", "Catcher", Gear.getInstance().gearLWS());
		
		LiveWindow.addActuator("Drive", "Paul", Drive.getInstance().driveLWS()[0]);
		LiveWindow.addActuator("Drive", "John", Drive.getInstance().driveLWS()[1]);
		LiveWindow.addActuator("Drive", "Ringo", Drive.getInstance().driveLWS()[2]);
		LiveWindow.addActuator("Drive", "George", Drive.getInstance().driveLWS()[3]);
		
		this.t = new Thread(() -> {
			 
             UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("cam0",0);
             camera.setExposureManual(30);
             camera.setResolution(640, 480);
                          
             CvSink cvSink = CameraServer.getInstance().getVideo();
             
             Mat source = new Mat();
             
             while(!Thread.interrupted()) {
            	 if(Vision.isImageRequested()) {
                 cvSink.grabFrame(source);
                 //Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
                // outputStream.putFrame(output);
                 //System.err.println("This is the first test");
                 Vision.getInstance().process(source);
                 Vision.imageRequested = false;
            	 }
             }
         });
		//Start camera
			try{
				if(!t.isAlive())t.start();
			}catch(IllegalThreadStateException e){
				System.err.println("Illegal Thread State Exception Bruv");
			}catch(Exception e){
				e.printStackTrace(System.err);
			}		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		//Shooter.getInstance().writeData();
		//Shooter.getInstance().setOn(false);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		tele = false;
		
		//Shooter.getInstance().setOn(true);
		
		autonomousCommand =  chooser.getSelected();

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	private UsbCamera teleCam;
	
	@Override
	public void teleopInit() {
		Drive.getInstance();
		//Vision.getInstance();
		Intake.getInstance();
		Shooter.getInstance();
		OI.getInstance();
		Hopper.getInstance();
		Gear.getInstance();
		Climber.getInstance();
		
		System.err.println("Teleop init running");
		tele = true;
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		try {
			for(DoubleSolenoid f :Hopper.getInstance().getFirers()){
				f.set(DoubleSolenoid.Value.kForward);
			}
		} catch (Exception e) {
			System.err.println("Problem with DoubleSolenoid Loop");
		}
		try {
			Shooter.getInstance().setOn(false);
		} catch(Exception e) {
			System.err.println("Problem with shooter set on");
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
