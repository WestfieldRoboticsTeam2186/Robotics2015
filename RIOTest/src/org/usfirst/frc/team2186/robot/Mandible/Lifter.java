package org.usfirst.frc.team2186.robot.Mandible;

import org.usfirst.frc.team2186.robot.IO;
import org.usfirst.frc.team2186.robot.RobotMap;
import org.usfirst.frc.team2186.robot.RobotStates;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Lifter {
	
	SpeedController motor = new Talon(RobotMap.MOTOR_LIFTER);
	
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
			if(!io.getLifterMax()){
				motor.set(1.0);
			} else if(io.getLifterMax()){
				state = RobotStates.LIFTER_NEUTRAL;
			}
			break;
		case LIFTER_DOWN:
			if(!io.getLifterMin()){
				motor.set(-1.0);
			} else if(io.getLifterMin()){
				state = RobotStates.LIFTER_NEUTRAL;
			}
			break;
		default:
			break;
		}
	}
	
	public Lifter(){
		
	}
	

}
