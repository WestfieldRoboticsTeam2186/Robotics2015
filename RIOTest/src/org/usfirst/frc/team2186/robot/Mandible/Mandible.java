package org.usfirst.frc.team2186.robot.Mandible;

import org.usfirst.frc.team2186.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
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
	
	
	boolean wheelsAreEnabled = false;
	public void update(Joystick j){
		double xJ = j.getAxis(AxisType.kX);
		double x = clamp(xJ, -0.5, 0.5);
		
		double zJ = j.getAxis(AxisType.kZ);
		double z = clamp(zJ, -0.5, 0.5);
		
		if(!leftMandibleMax.get()){
			leftMandible.set(x);
		} else if(leftMandibleMax.get()){
			leftMandible.set(0);
		} else if(leftMandibleMin.get()){
			leftMandible.set(0);
		}
		
		if(!rightMandibleMax.get()){
			rightMandible.set(z);
		} else if(rightMandibleMax.get() || rightMandibleMin.get()){
			rightMandible.set(0);
		}
		
		if(j.getRawButton(5)){
			wheelsAreEnabled = !wheelsAreEnabled;
		}
		
		if(wheelsAreEnabled){
			leftMandibleMotor.set(0.5);
			rightMandibleMotor.set(-0.5);
		}
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
