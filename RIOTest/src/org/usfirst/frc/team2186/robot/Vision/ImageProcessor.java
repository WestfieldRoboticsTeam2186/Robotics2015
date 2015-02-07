package org.usfirst.frc.team2186.robot.Vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.*;

public class ImageProcessor {
	
	public Rect[] findFaces(Mat img){
		CascadeClassifier faceDetector = new CascadeClassifier(getClass().getResource("/lbpcascade_frontalface.xml").getPath());
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(img, faceDetections);
		Rect[] faces = faceDetections.toArray();
		return faces;
	}
}
