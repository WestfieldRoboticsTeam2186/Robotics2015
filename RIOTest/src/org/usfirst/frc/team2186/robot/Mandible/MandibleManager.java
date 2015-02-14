package org.usfirst.frc.team2186.robot.Mandible;

import org.usfirst.frc.team2186.robot.RobotMap;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class MandibleManager {
	Lifter lifter;
	Mandible mandible;
	public MandibleManager(){
		lifter = new Lifter();
		mandible = new Mandible();
	}
	
	public void update(Joystick j){
		lifter.update(j);
		mandible.update(j);
	}
}
