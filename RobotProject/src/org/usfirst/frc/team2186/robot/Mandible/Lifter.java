package org.usfirst.frc.team2186.robot.Mandible;

import org.usfirst.frc.team2186.robot.IO;
import org.usfirst.frc.team2186.robot.RobotMap;
import org.usfirst.frc.team2186.robot.RobotStates;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Lifter {
	
	Talon motor = new Talon(RobotMap.MOTOR_LIFTER);
	
	IO io = IO.getInstance();
	
	RobotStates state = RobotStates.LIFTER_NEUTRAL;
	
	public void setState(RobotStates state){
		this.state = state;
	}
	
	public void update(){
		switch(state){
		case LIFTER_NEUTRAL:
			motor.set(0);
			break;
		case LIFTER_UP:
				motor.set(1.0);
				state = RobotStates.LIFTER_NEUTRAL;
			break;
		case LIFTER_DOWN:
				motor.set(-1.0);
				state = RobotStates.LIFTER_NEUTRAL;
			break;
		default:
			break;
		}
	}
	
	public void setMotor(double motor){
		this.motor.set(motor);
	}
	
	public Lifter(){
		
	}
	

}
