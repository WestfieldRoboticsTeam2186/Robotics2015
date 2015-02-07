package org.usfirst.frc.team2186.robot.Vision;

import org.opencv.core.*;
import org.opencv.highgui.*;

public class CameraCapture {
	private Mat image;
	private int width, height;
	
	private Thread VideoCaptureStream;
	private VideoCaptureThread captureThread;
	
	
	public Mat getCurrentFrame(){
		return captureThread.getImage();
	}
	public CameraCapture(int finalIP) throws InterruptedException{
		StringBuilder sb = new StringBuilder();
		String address;
		sb.append("http://10.21.86.");
		sb.append(finalIP);
		sb.append("/mjpg/video.mjpg");
		address = sb.toString();
		
		System.out.println("Connecting to Camera");
		int count = 1;
		
		System.out.println("Connected to camera!");
		captureThread = new VideoCaptureThread(address);
		VideoCaptureStream = new Thread(captureThread);
		VideoCaptureStream.run();
	}
	
	
}

class VideoCaptureThread implements Runnable {
	private Mat currImage;
	VideoCapture vcap;
	public VideoCaptureThread(String ip){
		while(!vcap.open(ip)){
			System.out.println("Could not connect to camera, retrying");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public synchronized Mat getImage(){
		return currImage;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		System.load("/usr/local/lib/lib_OpenCV/java/libopencv_java2410.so");
		
		while(true){
			vcap.read(currImage);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}
	
}
