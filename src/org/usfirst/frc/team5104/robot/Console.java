package org.usfirst.frc.team5104.robot;

import org.usfirst.frc.team5104.robot.partition.Core;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class Console {
	
	// Global Static Variables
	private static DriverStation ds;
	private static RobotDrive drive;
	private static Talon driveLeftFront;
	private static Talon driveRightFront;
	private static Talon driveLeftBack;
	private static Talon driveRightBack;
	private static Joystick controller;
	
	// Private Variables
	private double robotVolt;
	private boolean enableJoystick;
	private int errors;
	
	protected static void init(){
		controller = new Joystick(0);
		driveLeftFront = new Talon(0);
		driveLeftBack = new Talon(1);
		driveRightFront = new Talon(2);
		driveRightBack = new Talon(3);
		ds = DriverStation.getInstance();
		drive = new RobotDrive(driveLeftFront, driveLeftBack, driveRightFront, driveRightBack);
		enableJoystick = true;
		robotVolt = 12;
		checkVolt();
		print("Robot started @ " + ((int)robotVolt) + " volts.");
		errors = 0;
		print("Core System finished with {" + errors + "} errors.");
		print("Attempting to initialize /partitions/...");
		try {
			Core.init();
		} catch (Exception e) {
			print("WARNING: Exception! Dumping Stack Trace...");
			e.printStackTrace();
			System.exit(1);
		}
		print("Robot is ready!");
	}
	
	protected static void auto(){
		checkVolt();
		Core.auto();
	}
	
	protected static void tele(){
		if(enableJoystick){
			drive.mecanumDrive_Cartesian(controller.getX(), controller.getY(), controller.getRawAxis(5), 0);
		}
		
		
		
		checkVolt();
		Core.tele();
	}
	
//////////////////////////////////////////////////////////////////////////////////
	public static void print(String text){
		DriverStation.reportError("[INFO]: " + text + "\n", false);
	}
	
	private static void checkVolt(){
		robotVolt = ds.getBatteryVoltage();
		if(robotVolt <= 6){
			print("WARNING: Robot battery needs to be replaced soon!");
			print("WARNING: Voltage: " ((int)robotVolt) + "");
		} else if(robotVolt <= 3){
			print("WARNING: Robot going to die soon! Battery must be replaced!");
			print("WARNING: Voltage: " ((int)robotVolt) + "");
		} else if(robotVolt >= 15){
			print("WARNING: Over-charged battery may have un-predictable results!");
			print("WARNING: Voltage: " ((int)robotVolt) + "");
		}
	}

	public static void enableJoystick(boolean value){
		enableJoystick = value;
	}
	
	public static void setLeftFrontWheel(double value){
		if(value > 1){value=1;}
		if(value < -1){value=-1;}
		driveLeftFront.set(value);
	}
	
	public static void setRightFrontWheel(double value){
		if(value > 1){value=1;}
		if(value < -1){value=-1;}
		driveRightFront.set(value);
	}
	
	public static void setLeftBackWheel(double value){
		if(value > 1){value=1;}
		if(value < -1){value=-1;}
		driveLeftBack.set(value);
	}
	
	public static void setRightBackWheel(double value){
		if(value > 1){value=1;}
		if(value < -1){value=-1;}
		driveRightBack.set(value);
	}
	
}
