package org.usfirst.frc.team2186.robot.Mandible;

import org.usfirst.frc.team2186.robot.IO;
import org.usfirst.frc.team2186.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lifter {
	
	SpeedController motor = new Talon(RobotMap.MOTOR_LIFTER);
	
	IO io = IO.getInstance();
	double lifterPos = 0.0;
	
	public void update(Joystick j){
		SmartDashboard.putNumber("Lifter Position", lifterPos);
		if(j.getRawButton(6)){
			motor.set(-1);
			lifterPos += 1;
		}
		SmartDashboard.putBoolean("Is at top", io.getLifterMax());
		
		if(j.getRawButton(8)){
			motor.set(0.75);
			lifterPos -= 0.75;
		}
		
		SmartDashboard.putBoolean("Is at bottom", io.getLifterMin());
		

		if(j.getRawButton(2) || io.getLifterMin() || io.getLifterMax()){
			motor.set(0);
		}
		
		
	}
	
	public void update(boolean up, boolean down, boolean stop){
		if(up){
			motor.set(-1);
			lifterPos += 1;
		}
		if(down){
			motor.set(1);
			lifterPos -= 0.75;
		}
		if(stop){
			motor.set(0);
		}
	}
	
	public Lifter(){
		
	}
	

}
