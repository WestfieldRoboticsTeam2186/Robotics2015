package org.usfirst.frc.team2186.robot.Mandible;

import org.usfirst.frc.team2186.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class Mandible {
	
	SpeedController leftMandible = new Victor(RobotMap.MOTOR_MANDIBLE_LEFT);
	SpeedController rightMandible = new Victor(RobotMap.MOTOR_MANDIBLE_RIGHT);
	
	SpeedController leftMandibleMotor = new Victor(RobotMap.MOTOR_MANDIBLE_MOTOR_LEFT);
	SpeedController rightMandibleMotor = new Victor(RobotMap.MOTOR_MANDIBLE_MOTOR_RIGHT);
	
	
	
	final int NOT_GRABBING = 0;
	final int GRABBING = 1;
	
	int state = NOT_GRABBING;
	boolean wheelsAreEnabled = false;
	boolean inverted = false;
	double amt = 0.25;
	public void setOn(boolean on){
		if(on){
			if(!inverted){
			leftMandibleMotor.set(-amt);
			rightMandibleMotor.set(amt);
			}else {
				leftMandibleMotor.set(amt);
				rightMandibleMotor.set(-amt);
			}
		} else {
			leftMandibleMotor.set(0);
			rightMandibleMotor.set(0);
		}
	}
	public void setInverted(boolean invert){
		inverted = invert;
	}
	public void update(){
		switch(state){
		case GRABBING:
			leftMandible.set(0.15);
			rightMandible.set(-0.15);
			break;
		case NOT_GRABBING:
			leftMandible.set(0);
			rightMandible.set(0);
			break;
		}
	}
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
	
	public void setState(int state){
		this.state = state;
	}
	
	
	public Mandible(){
	}
	
	static double clamp(double val, double min, double max){
		return Math.max(min, Math.min(max, val));
	}

}
