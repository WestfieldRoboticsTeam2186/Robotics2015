package org.usfirst.frc.team2186.robot.autonomous;

import org.usfirst.frc.team2186.robot.DriveManager;

import edu.wpi.first.wpilibj.*;



public class AutonomousManager {
	DriveManager drive = DriveManager.getInstance();
	Timer timer;
	
	public void driveForward(double secs){
		this.timer.start();
	}
}
