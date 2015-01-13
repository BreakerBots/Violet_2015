package org.usfirst.frc.team5104.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class Console {
	
	private static DriverStation ds;
	private static double robotVolt;
	private static RobotDrive drive;
	private static Talon driveLeftFront;
	private static Talon driveRightFront;
	private static Talon driveLeftBack;
	private static Talon driveRightBack;
	private static Joystick controller;
	
	protected static void init(){
		print("Robot Initialized");
		ds = DriverStation.getInstance();
		controller = new Joystick(0);
		driveLeftFront = new Talon(0);
		driveLeftBack = new Talon(1);
		driveRightFront = new Talon(2);
		driveRightBack = new Talon(3);
		drive = new RobotDrive(driveLeftFront, driveLeftBack, driveRightFront, driveRightBack);
		
		
	}
	
	protected static void auto(){
		
		
	}
	
	protected static void tele(){
		
		drive.mecanumDrive_Cartesian(controller.getX(), controller.getY(), controller.getRawAxis(5), 0);
		robotVolt = ds.getBatteryVoltage();
		print("Voltage: " + robotVolt);
			
		
	}
	
	public static void print(String text){
		DriverStation.reportError("[INFO]: " + text + "\n", false);
	}
	
	
}
