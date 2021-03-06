package org.usfirst.frc.team1100.robot;

import org.opencv.core.Mat;
import org.usfirst.frc.team1100.robot.commands.climber.SetClimberSpeedCommand;
import org.usfirst.frc.team1100.robot.commands.drive.AutoDrive;
import org.usfirst.frc.team1100.robot.commands.drive.vision.GearTrackCommand;
import org.usfirst.frc.team1100.robot.subsystems.Climber;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Gear;
import org.usfirst.frc.team1100.robot.subsystems.Gear2;
import org.usfirst.frc.team1100.robot.subsystems.Intake;
import org.usfirst.frc.team1100.robot.subsystems.Shooter;
import org.usfirst.frc.team1100.robot.subsystems.vision.Vision;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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
	
	private static PowerDistributionPanel panel;
	
	public static PowerDistributionPanel getPDP(){
		if(panel==null)panel = new PowerDistributionPanel(RobotMap.P_PANEL);
		return panel;
	}

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
		Gear2.getInstance();
		Gear.getInstance();
		//TOnotDO oooooo
		Climber.getInstance();
		
		
		chooser = new SendableChooser<Command>();
		
		chooser.addObject("Center Gear", new GearTrackCommand());
		chooser.addObject("Line Cross", new AutoDrive(0,.5,0,3));
		
		SmartDashboard.putData("Auto mode", chooser);
		
		// Add actuators to test mode
		//LiveWindow.addActuator("Intake", "Roller", Intake.getInstance().getRollerLWS());
		//LiveWindow.addActuator("Intake", "Roller2", Intake.getInstance().getRoller2LWS());
		
		LiveWindow.addActuator("Shooter", "Flywheel", Shooter.getInstance().getFlywheelLWS());
		LiveWindow.addActuator("Shooter", "Flywheel2", Shooter.getInstance().getFeederLWS());
		LiveWindow.addActuator("SHooter", "Conveyor", Shooter.getInstance().getConveyorLWS());
		
		//LiveWindow.addActuator("Climber", "Motor", Climber.getInstance().climbLWS());
		//LiveWindow.addActuator("Climber", "Motor2", Climber.getInstance().climb2LWS());
		
		LiveWindow.addActuator("Gear", "Catcher", Gear.getInstance().gearLWS());
		
		LiveWindow.addActuator("Gear2", "Catcher2.1", Gear2.getInstance().gear2LWS1());
		LiveWindow.addActuator("Gear2", "Catcher2.2", Gear2.getInstance().gear2LWS2());
		
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
			t.start();
		}catch(Exception e){
			System.err.println("Camera failed to start");
		}		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Shooter.getInstance().setOn(false);
		Gear2.getInstance().closeCatcher();
		Gear2.getInstance().closeRamp();
		Gear.getInstance().closeCatcher();
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
		
		autonomousCommand =  chooser.getSelected();
		
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
		
		tele = true;
		
		
		Drive.getInstance().setReversed(false);
		Gear2.getInstance().openRamp();
	 
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
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
