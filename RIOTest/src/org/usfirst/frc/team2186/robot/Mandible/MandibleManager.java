package org.usfirst.frc.team2186.robot.Mandible;

import org.usfirst.frc.team2186.robot.RobotMap;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class MandibleManager {
	Encoder lifterEncoder = new Encoder(RobotMap.ENCODER_LIFTER_A, RobotMap.ENCODER_LIFTER_B, false, EncodingType.k4X);
	SpeedController motor = new Victor(RobotMap.MOTOR_LIFTER);
	PIDController lifterController = new PIDController(0.0, 0.0, 0.0, lifterEncoder, motor);
	private double maxPos;
	private double minPos;
	public void moveToPos(double pos){
		
	}
}
