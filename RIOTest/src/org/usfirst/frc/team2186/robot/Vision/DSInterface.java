package org.usfirst.frc.team2186.robot.Vision;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DSInterface {
	DriverStation station = DriverStation.getInstance();
	NetworkTable table;
	public DSInterface(){
		table = NetworkTable.getTable("camera");
		table.putValue("output", null);
	}
	
	public void sendMat(Mat img){
		Timer.delay(0.6);
		
		table.putValue("cameraOutput", img);
	}
}
