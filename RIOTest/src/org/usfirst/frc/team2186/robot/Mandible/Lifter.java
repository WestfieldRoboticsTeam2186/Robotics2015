package org.usfirst.frc.team2186.robot.Mandible;

import org.usfirst.frc.team2186.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lifter {
	
	SpeedController motor = new Talon(RobotMap.MOTOR_LIFTER);
	
	DigitalInput lifterMin = new DigitalInput(RobotMap.LIMIT_SWITCH_LIFT_MIN);
	DigitalInput lifterMax = new DigitalInput(RobotMap.LIMIT_SWITCH_LIFT_MAX);
	
	double lifterPos = 0.0;
	
	public void reset(Joystick j){
		while(lifterMin.get()){
			motor.set(0.5);
			if(j.getRawButton(2)){
				break;
			}
		}
		motor.set(0);
	}
	public void update(Joystick j){
		SmartDashboard.putNumber("Lifter Position", lifterPos);
		if(j.getRawButton(6)){
			motor.set(-1);
			lifterPos += 1;
		}
		SmartDashboard.putBoolean("Is at top", !lifterMax.get());
		/*if(!lifterMax.get()){
			motor.set(0);
		}*/
		
		if(j.getRawButton(8)){
			motor.set(0.75);
			lifterPos -= 0.75;
		}
		/*if(j.getRawButton(3)){
			reset(j);
		}*/
		SmartDashboard.putBoolean("Is at bottom", !lifterMin.get());
		/*if(!lifterMin.get()){
			motor.set(0);
		}*/

		if(j.getRawButton(2) || !lifterMin.get() || !lifterMax.get()){
			motor.set(0);
		}
		
		
	}
	
	public Lifter(){
		
	}
	

}
