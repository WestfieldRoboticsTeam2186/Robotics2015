package org.usfirst.frc.team2186.robot.Mandible;

import org.usfirst.frc.team2186.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class Mandible {
	
	SpeedController leftMandible = new Victor(RobotMap.MOTOR_MANDIBLE_LEFT);
	SpeedController rightMandible = new Victor(RobotMap.MOTOR_MANDIBLE_RIGHT);
	
	SpeedController leftMandibleMotor = new Victor(RobotMap.MOTOR_MANDIBLE_MOTOR_LEFT);
	SpeedController rightMandibleMotor = new Victor(RobotMap.MOTOR_MANDIBLE_MOTOR_RIGHT);
	
	DigitalInput leftMandibleMin = new DigitalInput(RobotMap.LIMIT_SWITCH_LEFT_MIN);
	DigitalInput leftMandibleMax = new DigitalInput(RobotMap.LIMIT_SWITCH_LEFT_MAX);
	
	DigitalInput rightMandibleMin = new DigitalInput(RobotMap.LIMIT_SWITCH_RIGHT_MIN);
	DigitalInput rightMandibleMax = new DigitalInput(RobotMap.LIMIT_SWITCH_RIGHT_MAX);
	
	final int NOT_GRABBING = 0;
	final int GRABBING = 1;
	
	int state = NOT_GRABBING;
	boolean wheelsAreEnabled = false;
	public void update(Joystick j){
		double xJ = j.getRawAxis(0);
		SmartDashboard.putNumber("Xbox 1", xJ);
		/*
		SmartDashboard.putNumber("Xbox 2", j.getRawAxis(2));
		SmartDashboard.putNumber("Xbox 3", j.getRawAxis(3));
		SmartDashboard.putNumber("Xbox 4", j.getRawAxis(4));
		SmartDashboard.putNumber("Xbox 5", j.getRawAxis(5));
		SmartDashboard.putNumber("Xbox 6", j.getRawAxis(6));*/
		double x = clamp(xJ, -0.15, 0.15);
		
		switch(state){
		case NOT_GRABBING:
		{
			wheelsAreEnabled = false;
			leftMandible.set(0);
			rightMandible.set(0);
			break;
		}
		case GRABBING:
		{
			wheelsAreEnabled = true;
			leftMandible.set(x);
			rightMandible.set(-x);
			break;
		}
		}
		
		if(j.getRawButton(5)){
			state = GRABBING;
		}
		
		if(j.getRawButton(7)){
			state = NOT_GRABBING;
		}
		
		if(wheelsAreEnabled){
			wheels();
		} else {
			leftMandibleMotor.set(0.0);
			rightMandibleMotor.set(0.0);
		}
	}
	
	void wheels(){
		leftMandibleMotor.set(1);
		rightMandibleMotor.set(-1);
	}
	
	public void open(){
		leftMandible.set(-0.5);
		rightMandible.set(0.5);
	}
	
	
	public Mandible(){
	}
	
	static double clamp(double val, double min, double max){
		return Math.max(min, Math.min(max, val));
	}

}
