package org.usfirst.frc.team2186.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class OpticalSensor {
	AnalogInput resistor;
	
	public OpticalSensor(){
		resistor = new AnalogInput(0);
	}
	//Returns value out of 1
	public double getBrightness(){
		double val = 0;
		val = 1/resistor.getAverageVoltage();
		return val;
	}
}
