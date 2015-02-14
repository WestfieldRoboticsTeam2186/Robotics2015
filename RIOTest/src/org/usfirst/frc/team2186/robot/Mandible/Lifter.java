package org.usfirst.frc.team2186.robot.Mandible;

import org.usfirst.frc.team2186.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class Lifter {
	
	SpeedController motor = new Talon(RobotMap.MOTOR_LIFTER);
	
	DigitalInput lifterMin = new DigitalInput(RobotMap.LIMIT_SWITCH_LIFT_MIN);
	DigitalInput lifterMax = new DigitalInput(RobotMap.LIMIT_SWITCH_LIFT_MAX);
	
	
	public void update(Joystick j){
		if(j.getRawButton(6)){
			if(!lifterMax.get()){
				motor.set(0.5);
			} else {
				motor.set(0);
			}

		} else if(j.getRawButton(8)){
			if(!lifterMin.get()){
				motor.set(-0.5);
			} else {
				motor.set(0);
			}
		}
	}
	
	public Lifter(){
		
	}
	

}
