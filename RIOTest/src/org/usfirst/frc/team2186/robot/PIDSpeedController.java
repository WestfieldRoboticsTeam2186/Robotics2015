package org.usfirst.frc.team2186.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDSpeedController implements SpeedController {
	private PIDController pidController;
	private Encoder in;
	private SpeedController speedController;
	double value;
	
	public PIDSpeedController(SpeedController sc, Encoder ps, double kP, double kI, double kD){
		speedController = sc;
		in = ps;
		pidController = new PIDController(kP, kI, kD, ps, sc);
		pidController.setInputRange(-5000, 5000);
		pidController.setOutputRange(-1.0d,  1.0d);
		pidController.enable();
		pidController.setSetpoint(0);
	}
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		set(output);
	}

	@Override
	public double get() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void set(double speed, byte syncGroup) {
		// TODO Auto-generated method stub
		set(speed);
	}

	@Override
	public void set(double speed) {
		// TODO Auto-generated method stub
		pidController.setSetpoint(speed * 5000);
		value = speed;
		SmartDashboard.putNumber("Encoder", in.getRaw());
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		pidController.disable();
	}
	
	public void enable(){
		pidController.enable();
	}
	
	public double getMotorSetting(){
		return speedController.get();
	}

}
