package org.usfirst.frc.team2186.robot.Vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;


public class VisionManager {
	static {
		System.load("/usr/local/lib/lib_OpenCV/java/libopencv_java2410.so");
	}
	
	private double PI = 3.141592653589793;
	int minR = 0;
	int maxR = 30;
	int minG = 80;
	int maxG = 255;
	int minB = 0;
	int maxB = 30;
	
	double minHRatio = 1.5;
	double maxHRatio = 6.6;
	double minVRatio = 1.5;
	double maxVRatio = 8.5;
	
	int MAX_SIZE = 255;
	
	Scalar RED = new Scalar(0, 0, 255);
	Scalar BLUE = new Scalar(255, 0, 0);
	Scalar GREEN = new Scalar(0, 255, 0);
	Scalar ORANGE = new Scalar(0, 128, 255);
	Scalar YELLOW = new Scalar(0, 255, 255);
	Scalar PINK = new Scalar(255, 0, 255);
	Scalar WHITE = new Scalar(255, 255, 255);
	
	
	static CameraCapture capture;
	ImageProcessor proc;
	DSInterface dinterface;
	
	public void initialize() throws InterruptedException{
		try {
			capture = new CameraCapture(20);
			proc = new ImageProcessor();
			dinterface = new DSInterface();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void update(){
		try{
			Mat frame = capture.getCurrentFrame();
			Rect[] faces = proc.findFaces(frame);
			for(int i = 0; i < faces.length; i++){
				Rect face = faces[i];
				Core.rectangle(frame, new Point(face.x, face.y), new Point(face.x + face.width, face.y + face.height), GREEN);
			}
			dinterface.sendMat(frame);
		} catch(NullPointerException e){
			System.out.println("Could not get the current frame!");
			e.printStackTrace();
		}
			
	}
}
